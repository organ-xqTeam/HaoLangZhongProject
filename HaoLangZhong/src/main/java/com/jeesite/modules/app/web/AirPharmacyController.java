package com.jeesite.modules.app.web;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.entity.AirAdPic;
import com.jeesite.modules.app.entity.AirDrug;
import com.jeesite.modules.app.entity.AirDrugCategory;
import com.jeesite.modules.app.entity.AirDrugComment;
import com.jeesite.modules.app.entity.AirPrescription;
import com.jeesite.modules.app.service.AirAdPicService;
import com.jeesite.modules.app.service.AirDrugCategoryService;
import com.jeesite.modules.app.service.AirDrugCommentSercvie;
import com.jeesite.modules.app.service.AirDrugService;
import com.jeesite.modules.app.service.AirPrescriptionService;
import com.jeesite.modules.app.service.FileInfoService;
import com.jeesite.modules.app.utils.ChangeImageSize;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.PageModel;
import com.jeesite.modules.app.utils.PictureJudgment;
import com.jeesite.modules.app.utils.Result;

import org.apache.http.HttpResponse;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * 
 * 
 * @author 范耘诚
 * 空中药房Controller
 * @version 2019-03-05
 *
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/sys/airPharmacy")
@PropertySource({"classpath:config/application.yml"})
@PropertySource({"classpath:config/config.properties"})
@CrossOrigin(origins = "*", maxAge = 3600)
public class AirPharmacyController extends BaseController  implements BeanFactoryAware {
	
	@Autowired
	private AirAdPicService airAdPicService;
	@Autowired
	private AirDrugService airDrugService;
	@Autowired
	private AirDrugCommentSercvie 	airDrugCommentSercvie;
	@Autowired 
	private AirDrugCategoryService  airDrugCategoryService;
	
	@Autowired
	private AirPrescriptionService airPrescriptionService;
	
	@Autowired
	private BeanFactory factory;
	@Value("${baseDir}")
	private String baseDir;
	// 文件上传路径
	@Value("${fileurl}")
	public String fileurl;
	
	@Autowired
	public FileInfoService fileInfoService;
	

	
	/**
	 *空中药房主页 数据
	 *   /js/f/sys/airPharmacy/index
	 *   
	 *   token
	 *   userId
	 */
	@ResponseBody
	@RequestMapping(value = "/index" ,method = {RequestMethod.POST,RequestMethod.OPTIONS})
	public Result airPharmacyIndex (/*@RequestBody Map<String, Object> requestParams ,*/String token,HttpServletRequest request,HttpServletResponse response) {
		JSONObject result;
		try {  
	
			//token 值
			/*String token = (String) requestParams.get("token");*/
			//String tokens= (String) requestParams.get("token");
			logger.debug(token);
			result = new JSONObject();
			//图片展示,各种医药分类
			AirAdPic airAdPic =new AirAdPic();
			airAdPic.setDelFlag("0");
			airAdPic.setIsUse("1");
			//广告pic
			List<Map> airAdPicList= airAdPicService.queryList(airAdPic);
			//热销中药
			AirDrug airDrug =new AirDrug();
			airDrug.setDelFlag("0");
			airDrug.setFirstFlag("1");
			airDrug.setLimit("5");
			//热销优先显示5条
			List<Map> airDrugFirstList=  airDrugService.getAirDrugFirstList(airDrug);
			//搜索中成药(药品类别)分类集合
			AirDrugCategory airDrugCategory =new AirDrugCategory();
			airDrugCategory.setDelFlag("0");
			List<Map> airDrugCategoryList =airDrugCategoryService.queryList(airDrugCategory);
			result.put("airAdPicList", airAdPicList);
			result.put("airDrugFirstList", airDrugFirstList);
			result.put("airDrugCategoryList", airDrugCategoryList);
			return Result.success(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
		
	}
	
	/**
	 * 通过搜索内容进行各种关键词模糊查询药品
	 * /js/f/sys/airPharmacy/airPharmacySearchLike
	 * token     token值
	 * userId    用户id
	 * drugSearchLike  模糊搜索
	 * pageNum       页数
	 * pageSize     每页显示条数
	*/
	@ResponseBody
	@RequestMapping(value = "/airPharmacySearchLike")
	public Result airPharmacySearchLike(HttpServletRequest request,@RequestBody Map<String, Object> requestParams) throws  Exception {
		try {
			String token = (String) requestParams.get("token");
			String drugSearchLike= (String) requestParams.get("drugSearchLike");
			String pageNum= (String) requestParams.get("pageNum");
			String pageSize= (String) requestParams.get("pageSize");
			//String tokens= (String) requestParams.get("token");
			JSONObject result = new JSONObject();
			logger.debug(token);
			PageModel  pageModel =new PageModel(Integer.valueOf(pageNum), Integer.valueOf(pageSize));
			Map<String, Object> paramMap =new HashMap<>();
			if(!StringUtils.isEmpty(drugSearchLike)) {
				paramMap.put("drugSearchLike", "%"+drugSearchLike+"%");
			}
			paramMap.put("pageModel", pageModel);
			//通过搜索内容进行各种关键词模糊查询药品*/
			List<Map> airDrugSearchList =airDrugService.queryListLike(paramMap);
			result.put("airDrugSearchList", airDrugSearchList);
			return Result.success(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	/**
	 * 按方抓药文件上传与存储
	 * token      token
	 * userId     用户id  
	 * uploadFile 上传文件
	 * content    药房内容
	 * /js/f/sys/airPharmacy/uploadPrescription
	 * 使用form表单形式传输
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadPrescription")
	public Result uploadPrescription(@RequestParam("uploadFile") MultipartFile[] files,HttpServletRequest request) {
		String token  = (String) request.getParameter("token");
		//验证token
		
		String content=(String)request.getParameter("content");
		String userId= (String)request.getParameter("userId");
		
		
		
		
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
						//不是图片的操作
						return Result.error(CodeMsg.UPLOAD_NO_PIC);
					}
				}
			} else {
				/*return Result.error(CodeMsg.UPLOAD_FAIL1);*/
			}
			
			
			AirPrescription airPrescription =new AirPrescription();
			airPrescription.setUserId(userId);
			airPrescription.setContent(content);
			airPrescription.setCreateDate(new Date());
			airPrescription.setCheckState("0");
			airPrescription.setCreateBy("创建人");
			airPrescription.setDelFlag("0");
			if (items != null && !items.isEmpty()) {
				int j=0;
				for(Map<String, Object> map : items) {
					fileInfoService.save(map);
					//通过路径得到唯一的一个文件id
					Map<String, Object> fileInfoMap= fileInfoService.getFileInfoByFilepathOne(map);
					result.add(map);
					String fileInfoId=(String) fileInfoMap.get("id");
					if(map!=null) {
						Method m=airPrescription.getClass().getDeclaredMethod("setPic"+(j+1), String.class);
						m.invoke(airPrescription, fileInfoId);
					}
					j++;
				}//插入数据
			}
			airPrescriptionService.insertAirPrescription(airPrescription);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.UPLOAD_FAIL2);
		}
		return  Result.success(CodeMsg.SUCCESS);
	}
	
	
	
	
	
	/**
	 * 按方抓药文件上传与存储
	 * token      token
	 * userId     用户id  
	 * uploadFile 上传文件
	 * content    药房内容
	 * /js/f/sys/airPharmacy/uploadPrescription
	 * 使用form表单形式传输
	 * 
	 */
/*	@ResponseBody
	@RequestMapping(value = "/uploadPrescription")
	public Result uploadPrescription(@RequestParam("uploadFile") MultipartFile[] files,HttpServletRequest request) {
		
		String token  = (String) request.getParameter("token");
		//验证token
		
		String content=(String)request.getParameter("content");
		String userId= (String)request.getParameter("userId");
		
		
		String fileName = null;
		String fileAbbreviations = null;
		
		String returnUrl = this.fileurl;// config.properties中的存储路径
		List<String> picList =new ArrayList<>();
		String viewUrl="/f/sys/fileInfo/viewPic/";
//		String returnUrl = fileurl;// linux存储路径
		//如果upload-dir目录不存在，则创建该目录
		 if (!Files.exists(Paths.get(returnUrl))) {
	            try {
					Files.createDirectory(Paths.get(returnUrl));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return Result.error(CodeMsg.UPLOAD_FAIL1);
				}
	        }
		try {
			if (files != null && files.length > 0) {
				//三张
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
								code = 0;
								//ChangeImageSize.scale(msg, returnUrl + fileAbbreviations, 2, false);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						//此处目前获取的是本地ip到时候需要更改
			            String host = InetAddress.getLocalHost().getHostAddress();
			            TomcatServletWebServerFactory tomcatServletWebServerFactory= (TomcatServletWebServerFactory) factory.getBean("tomcatServletWebServerFactory");
			            int port = tomcatServletWebServerFactory.getPort();
			            String contextPath = tomcatServletWebServerFactory.getContextPath();
			            String projectUrl="http://"+host+":"+port+contextPath+"/";
						String picUrl=projectUrl+viewUrl+"/"+fileName;
						picList.add(picUrl);
					} else {
						//不是文件的操作
						return Result.error(CodeMsg.UPLOAD_NO_PIC);
					}
				}
			} else {
				return Result.error(CodeMsg.UPLOAD_FAIL1);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.UPLOAD_FAIL2);
		}
		try {
			AirPrescription airPrescription =new AirPrescription();
			airPrescription.setUserId(userId);
			airPrescription.setContent(content);
			airPrescription.setCreateDate(new Date());
			airPrescription.setCheckState("0");
			airPrescription.setCreateBy("创建人");
			airPrescription.setDelFlag("0");
			for (int j = 0; j < picList.size(); j++) {
				if(picList.get(j)!=null) {
					Method m=airPrescription.getClass().getDeclaredMethod("setPic"+(j+1), String.class);
					m.invoke(airPrescription, picList.get(j));
				}
			}
			//插入数据
			airPrescriptionService.insertAirPrescription(airPrescription);
			return Result.success(CodeMsg.SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.SERVER_EXCEPTION);
		}
		
	}*/
	
	/**
	 * 商品详情页
	 * token    token     随便
	 * userId     用户id   123
	 * drugId     药品id   1字符串
	 * pageNum       页数
	 * pageSize     每页显示条数
	 * /js/f/sys/airPharmacy/airDrugDetail
	 * 
	 */
	@RequestMapping(value = "/airDrugDetail")
	@ResponseBody
	public Result  findOneAirDrugAndInventoryById(HttpServletRequest request,@RequestBody Map<String, Object> requestParams,HttpServletResponse response) {
		
		//
		try {

			String drugId=(String) requestParams.get("drugId");
			String userId=(String) requestParams.get("userId");
			String pageNum=(String) requestParams.get("pageNum");
			String pageSize=(String) requestParams.get("pageSize");
			AirDrug airDrug= new AirDrug();
			airDrug.setId(drugId);
			JSONObject result = new JSONObject();
			//通过id获得商品详情信息和库存 需要
			Map airDrugOne= airDrugService.findOneAirDrugAndInventoryById(airDrug);
			//通过id得到商品的评价
			AirDrugComment airDrugComment =new AirDrugComment();
			Map<String,Object>  airDrugCommentParmMap= new HashMap<>();
			airDrugCommentParmMap.put("drugId", drugId);
			airDrugCommentParmMap.put("userId", userId);
			/**通过drugId和userId得到评价信息*/
			//需要
			List<Map> airDrugCommentList=airDrugCommentSercvie.findAirDrugCommentListByGrugIdAndUserId(airDrugCommentParmMap);
			//通过 GrugId和UserId获取每个星级的数量 star_grade 星级 ,count数量 需要
			//starCounttotal 总数
			//fineOdds		好评率
			Map<String,Object> commentStarInfo=airDrugCommentSercvie.findAirDrugCommentStarCountByGrugIdAndUserId(airDrugCommentParmMap);
			//通过药品id获取此药品的类别显示出相关推荐的药品 drugId
			Map<String,Object>  commendDrugParmMap= new HashMap<>();
			commendDrugParmMap.put("drugId", drugId);
			PageModel  pageModel =new PageModel(Integer.valueOf(pageNum), Integer.valueOf(pageSize));
			commendDrugParmMap.put("pageModel", pageModel);
			List<Map> commendDrugMapList=airDrugService.findCommendAirDrugListBydrugId(commendDrugParmMap);
			//商品详情和库存
			result.put("airDrugOne", airDrugOne);
			//评价
			result.put("airDrugCommentList", airDrugCommentList);
			//评论数和评论率
			result.put("commentStarInfo", commentStarInfo);
			//推荐药品
			result.put("commendDrugMapList", commendDrugMapList);
			
			return Result.success(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	/**
	 * 中医药搜索页
	 * token    token     随便
	 * userId        用户id   123
	 * categoryId    类别id    1    null
	 * searchParam   搜索内容       like  null
	 * pageNum       第几页   
	 * pageSize      每页显示多少内容
	 * 
	 * /js/f/sys/airPharmacy/airDrugSearch
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value = "/airDrugSearch",method = RequestMethod.POST)
	@ResponseBody
	public Result airDrugSearch(HttpServletRequest request,@RequestBody Map<String, Object> requestParams) throws NumberFormatException, Exception {
		try {
			String token= (String) requestParams.get("token");
			String searchParam= (String) requestParams.get("searchParam");
			String userId= (String) requestParams.get("userId");
			String categoryId= (String) requestParams.get("categoryId");
			String pageNum=  (String) requestParams.get("pageNum");
			String pageSize=  (String) requestParams.get("pageSize");
			Map<String,Object>  airDrugSearchParmMap= new HashMap<>();
			if(!StringUtils.isEmpty(searchParam)) {
				//如果模糊搜索内容部位空的话
				airDrugSearchParmMap.put("searchParam", "%"+searchParam+"%");
				
			}else if(!StringUtils.isEmpty(categoryId)) {
				airDrugSearchParmMap.put("categoryId", categoryId);
			}else {
				
			}
			PageModel  pageModel =new PageModel(Integer.valueOf(pageNum), Integer.valueOf(pageSize));
			airDrugSearchParmMap.put("pageModel", pageModel);
			/**
			 *搜索中医药模糊搜索和选择类别之后分页
			 */
			List<Map<String,String>> airDrugSearchMapList= airDrugService.findAirDrugSearch(airDrugSearchParmMap);
			JSONObject result = new JSONObject();
		/*	for (Map map : airDrugSearchMapList) {
				
				
			}
			System.out.println();*/
			result.put("airDrugSearchMapList", airDrugSearchMapList);
			return Result.success(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	
	
	
	
	
	
	




	@Override
	public void setBeanFactory(BeanFactory f) throws BeansException {
		this.factory=f;
	}
	
	   
}
