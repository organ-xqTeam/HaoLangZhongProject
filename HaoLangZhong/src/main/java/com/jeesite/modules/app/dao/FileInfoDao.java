package com.jeesite.modules.app.dao;

import java.util.List;
import java.util.Map;
import com.jeesite.common.mybatis.annotation.MyBatisDao;

@MyBatisDao
public interface FileInfoDao {

	int saveFileInfo(Map<String, Object> requestModel);
	
	List<Map<String, Object>> getFileInfo(String id);

	/**通过路径得到唯一的一个文件对象*/
	Map<String, Object> getFileInfoByFilepathOne(Map<String, Object> map);
	
}
