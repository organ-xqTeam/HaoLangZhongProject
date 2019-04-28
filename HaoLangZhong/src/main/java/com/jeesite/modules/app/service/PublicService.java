package com.jeesite.modules.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.app.dao.OrderDao;
import com.jeesite.modules.app.dao.PublicDao;
import com.jeesite.modules.app.dao.PublicDiscussDao;
import com.jeesite.modules.app.dao.PublicPicDao;
import com.jeesite.modules.app.dao.UserInfoDao;
import com.jeesite.modules.app.entity.ConsultationDiscuss;
import com.jeesite.modules.app.entity.Order;
import com.jeesite.modules.app.entity.Public;
import com.jeesite.modules.app.entity.PublicDiscuss;
import com.jeesite.modules.app.entity.PublicPic;
import com.jeesite.modules.app.entity.UserInfo;

/***
 * 公共咨询service
 * 
 * @author 1111111
 *
 */
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
@Service
public class PublicService extends CrudService<PublicDao, Public> {
	@Autowired
	private PublicDao publicDao;
	@Autowired
	private PublicDiscussDao publicDiscussDao;
	@Autowired
	private PublicPicDao publicPicDao;
	@Autowired
	private UserInfoDao userInfoDao;

	public Map<String, Object> savePublic(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		Public pub = new Public();
		/**
		 * private String num; // 咨询编号 private String body; // 身体部位 private String
		 * therapy; // 接受疗法 private String content; // 咨询内容 private String reply; //
		 * 专家回复 private String replytime; // 回复时间 private String delFlag; // del_flag
		 * private String userId; // 用户的主键id private String disease; // 病症id private
		 * String reviewFlag; // 是否审核成功 0 审核不通过 1审核通过
		 */
		if (requestMap.get("num") != null) {
			pub.setNum((String) requestMap.get("num"));
		}
		if (requestMap.get("body") != null) {
			pub.setBody((String) requestMap.get("body"));
		}
		if (requestMap.get("therapy") != null) {
			pub.setTherapy((String) requestMap.get("therapy"));
		}
		if (requestMap.get("content") != null) {
			pub.setContent((String) requestMap.get("content"));
		}
		if (requestMap.get("reply") != null) {
			pub.setReply((String) requestMap.get("reply"));
		}
		if (requestMap.get("replytime") != null) {
			pub.setReplytime((String) requestMap.get("replytime"));
		}
		if (requestMap.get("userId") != null) {
			pub.setUserId((String) requestMap.get("userId"));
		}
		if (requestMap.get("disease") != null) {
			pub.setDisease((String) requestMap.get("disease"));
		}
		if (requestMap.get("reviewFlag") != null) {
			pub.setReviewFlag((String) requestMap.get("reviewFlag"));
		}
		pub.setCreateDate(new Date());
		pub.setDelFlag("0");
		publicDao.insert(pub);
		System.out.println(pub);
		// 处理免费咨询图片
		List<String> picArray = null;
		List<PublicPic> picList = new ArrayList<PublicPic>();
		if (requestMap.containsKey("pic") && requestMap.get("pic") instanceof List) {
			picArray = (List<String>) requestMap.get("pic");
			for (String picid : picArray) {
				PublicPic publicPic = new PublicPic();
				publicPic.setPublicid(pub.getId());
				publicPic.setPic(picid);
				if (requestMap.get("create_by") != null) {
					publicPic.setCreateBy((String) requestMap.get("create_by"));
					publicPic.setCreateDate(new Date());
					publicPic.setDelFlag("0");
					picList.add(publicPic);
				}
			}
			if (picArray.size() > 0) {
				publicPicDao.insertBatch(picList);
				requestMap.put("pics", picList);
			}
		}
		return requestMap;

	}

	public Public findPublicById(String id) {
		// TODO Auto-generated method stub
		Public pub=new Public();
		pub.setId(id);
		return publicDao.get(pub);
	}

	public Object findPublicPic(String id) {
		// TODO Auto-generated method stub
		PublicPic publicPic =new PublicPic();
		publicPic.setPublicid(id);
		return publicPicDao.findList(publicPic);
	}
	/**
	 * 插入问答表
	 * @param publicDiscuss
	 */
	public void insertPublicDiscuss(PublicDiscuss publicDiscuss) {
		// TODO Auto-generated method stub
		publicDiscussDao.insert(publicDiscuss);
	}

	public void listAll() {
		// TODO Auto-generated method stub
		
	}

	public Object findconsultationDiscussList(PublicDiscuss publicDiscuss) {
		// TODO Auto-generated method stub
	 List<PublicDiscuss> publicDiscussesList=  publicDiscussDao.findList(publicDiscuss);
	 for (int i = 0; i < publicDiscussesList.size(); i++) {
		 String userId=publicDiscussesList.get(i).getUserId();
		 UserInfo userInfo =new UserInfo();
		 userInfo.setId(userId);
		 userInfo=userInfoDao.get(userInfo);
		 publicDiscussesList.get(i).setUserInfo(userInfo);
	}
	return publicDiscussesList;
	}
}
