package com.jeesite.modules.app.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jeesite.modules.app.dao.UserFeedbackDao;

/**
 * 用户反馈Service
 * @author 李昊翀
 * @version 2019-03-11
 */
@Service
@Transactional(readOnly=true)
public class UserFeedbackService {

	@Autowired 
	private UserFeedbackDao userFeedbackDao;
	
	/**
	 * 用户提交反馈
	 * */
	@Transactional(readOnly=false)
	public int saveFeedback(Map<String, Object> params) {
		return userFeedbackDao.saveFeedback(params);
	}
	
}
