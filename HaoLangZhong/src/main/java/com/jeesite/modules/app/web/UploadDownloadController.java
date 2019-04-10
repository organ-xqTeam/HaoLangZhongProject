package com.jeesite.modules.app.web;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONArray;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.service.FileInfoService;
import com.jeesite.modules.app.utils.ChangeImageSize;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.PictureJudgment;
import com.jeesite.modules.app.utils.Result;

/**
 * 文件上传下载
 * */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/sys/fileInfo")
@PropertySource({"classpath:config/application.yml"})
@PropertySource({"classpath:config/config.properties"})
public class UploadDownloadController extends BaseController {
	
	@Autowired
	private FileInfoService fileInfoService;


	// 文件上传路径
	@Value("${fileurl}")
	public  String fileurl ;


	

	@Value("${baseDir}")
	public String baseDir;

	/**
	 * 显示图片
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * ${frontPath}/sys/fileInfo
	 * 
	 */
	@RequestMapping(value = "/showPic/{id}")
	@ResponseBody
   public void showPic(HttpServletResponse response,@PathVariable String id) throws FileNotFoundException, IOException {
		// 写给浏览器
		response.setContentType("image/jpeg");
		List<Map<String, Object>> filedatatables= fileInfoService.getFileInfo(id);
		if(filedatatables.size()!=0) {
			Map<String, Object> file=filedatatables.get(0);
			String downloadPath=file.get("filepath").toString();
			response.setDateHeader("expries", -1);
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			/*BufferedImage buffImg = ImageIO.read(new FileInputStream(downloadPath));*/

			Image src = Toolkit.getDefaultToolkit().getImage(downloadPath);
			BufferedImage image = this.toBufferedImage(src);
			ImageIO.write(image, "jpg", response.getOutputStream());
		}

	}
	public BufferedImage toBufferedImage(Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}
		// This code ensures that all the pixels in the image are loaded
		image = new ImageIcon(image).getImage();
		BufferedImage bimage = null;
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			int transparency = Transparency.OPAQUE;
			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
		} catch (HeadlessException e) {
			// The system does not have a screen
		}
		if (bimage == null) {
			// Create a buffered image using the default color model
			int type = BufferedImage.TYPE_INT_RGB;
			bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
		}
		// Copy image to buffered image
		Graphics g = bimage.createGraphics();
		// Paint the image onto the buffered image
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return bimage;
	}

	
	
	
	/**
	 * 文件的转发显示
	 * js/f/sys/fileInfo/viewPic/{filename:.+}
	 * @return
	 */

	@RequestMapping(value = "/viewPic/{filename:.+}")
	@ResponseBody
	public void getImage(HttpServletResponse response,@PathVariable String filename) throws IOException {
		// 写给浏览器
		response.setContentType("image/jpeg");
		String DownloadPath=this.baseDir;
		// 浏览器不要缓存
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		BufferedImage buffImg = ImageIO.read(new FileInputStream(DownloadPath+"/"+filename));
		ImageIO.write(buffImg, "jpg", response.getOutputStream());
	}


	/**
	 * 文件上传
	 * hlz/sys/fileInfo/upload
	 * */
	@ResponseBody
	@RequestMapping(value = "/upload", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Result upload(@RequestParam("file") MultipartFile[] files) throws Exception {
		JSONArray result = new JSONArray();
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		String fileName = null;
		String fileAbbreviations = null;
		String msg = "";
		String returnUrl = fileurl;
		try {
			if (files != null && files.length > 0) {
				for(int i = 0; i < files.length; i++) {
					int w = 0;
					int h = 0;
					fileName = files[i].getOriginalFilename();
					String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
					if (PictureJudgment.imagesC(prefix)) {
						File targetFile = null;
						int code = 1;
						fileName = files[i].getOriginalFilename();// 获取文件名加后缀
						String oldName = fileName;
						if (fileName != null && fileName != "") {
							fileName = new Date().getTime() + "_" + new Random().nextInt(1000) + "." + prefix;// 新的文件名
							fileAbbreviations = new Date().getTime() + "_" + new Random().nextInt(1000) + "." + prefix;// 新的文件名
							targetFile = new File(returnUrl, fileName);
							if(!targetFile.exists()) {
								targetFile.mkdirs();
							}
							try {
								BufferedImage image = ImageIO.read(files[i].getInputStream());
								if (image != null) {// 如果image=null 表示上传的不是图片格式
									w = image.getWidth();// 获取图片宽度，单位px
									h = image.getHeight();// 获取图片高度，单位px
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
							try {
								files[i].transferTo(targetFile);
								msg = returnUrl + fileName;
								code = 0;
								ChangeImageSize.scale(msg, returnUrl + fileAbbreviations, 2, false);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("filename", oldName);
						map.put("filepath", msg);
						map.put("thumb", fileName);
						map.put("type", prefix);
						map.put("weight", w + "");
						map.put("height", h + "");
						map.put("abbreviated", returnUrl + fileAbbreviations);
						map.put("create_date", new Date().getTime());
						map.put("create_by", null);
						items.add(map);
					} else {
						byte[] bytes = files[i].getBytes();
						String oldName = fileName;
						fileName = new Date().getTime() + "_" + new Random().nextInt(1000) + "." + prefix;// 新的文件名
						BufferedOutputStream buffStream = new BufferedOutputStream(
								new FileOutputStream(new File(returnUrl + fileName)));
						buffStream.write(bytes);
						buffStream.close();
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("filename", oldName);
						map.put("filepath", returnUrl + fileName);
						map.put("thumb", fileName);
						map.put("type", prefix);
						map.put("create_date", new Date().getTime());
						map.put("create_by", null);
						items.add(map);
					}
				}
			} else {
				return Result.error(CodeMsg.UPLOAD_FAIL1);
			}
			if (items != null && !items.isEmpty()) {
				for(Map<String, Object> map : items) {
					fileInfoService.save(map);
					result.add(map);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.UPLOAD_FAIL2);
		}
		return Result.success(result);
	}
	/**
	 * 文件下载
	 * */
	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
	public Result Download(HttpServletResponse response, @PathVariable String id) {
		List<Map<String, Object>> filedatatables = fileInfoService.getFileInfo(id);
		String filePath = "";
		String fileName = "";
		for(int i = 0; i < filedatatables.size(); i++) {
			Map<String, Object> filedatatable = filedatatables.get(i);
			filePath = filedatatable.get("filepath").toString();
			fileName = filedatatable.get("filename").toString();
		}
		try {
			ServletOutputStream out = response.getOutputStream();
			File file = new File(filePath);
			FileInputStream fileInputStream = null;
			try {
				fileInputStream = new FileInputStream(file);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return Result.error(CodeMsg.DOWNLOAD_FAIL);
			}
			if (fileName != null && fileName.length() > 0) {
				response.setHeader("Content-Disposition", "attachment; filename="
						+ new String(fileName.getBytes("utf-8"), "iso8859-1"));
				if (fileInputStream != null) {
					int filelen = fileInputStream.available();
					byte a[] = new byte[filelen];
					fileInputStream.read(a);
					out.write(a);
				}
				fileInputStream.close();
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Result.success(true);
	}
		
}
