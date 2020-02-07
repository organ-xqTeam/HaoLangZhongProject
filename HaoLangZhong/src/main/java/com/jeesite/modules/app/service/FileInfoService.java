package com.jeesite.modules.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.modules.app.dao.FileInfoDao;
import com.jeesite.modules.app.utils.DateUtil;

/**
 * 文件上传下载
 * @author 李昊翀
 * @version 2019-02-21
 */
@Service
@Transactional(readOnly=true)
public class FileInfoService {

	@Autowired
	private FileInfoDao fileInfoDao;
	
	@Transactional(readOnly=false)
	public int save(Map<String, Object> requestModel) {
		requestModel.put("create_date", DateUtil.getSysTime1());
		requestModel.put("del_flag", "0");
		return fileInfoDao.saveFileInfo(requestModel);
	}
	
	@Transactional(readOnly=false)
	public List<Map<String, Object>> getFileInfo(String id) {
		return fileInfoDao.getFileInfo(id);
	}
	/**通过路径得到唯一的一个文件对象*/
	public Map<String, Object> getFileInfoByFilepathOne(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return fileInfoDao.getFileInfoByFilepathOne(map);
	}
	
	
}
