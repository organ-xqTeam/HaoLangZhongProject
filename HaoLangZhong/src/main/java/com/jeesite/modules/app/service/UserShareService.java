package com.jeesite.modules.app.service;

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
import com.jeesite.modules.app.dao.DiscountDao;
import com.jeesite.modules.app.dao.UserInfoDao;
import com.jeesite.modules.app.dao.UserShareDao;
import com.jeesite.modules.app.entity.Discount;
import com.jeesite.modules.app.entity.DiscountDetail;
import com.jeesite.modules.app.entity.User;
import com.jeesite.modules.app.entity.UserInfo;
import com.jeesite.modules.app.entity.UserShare;


/**
 * 
 * @author 用户分享service
 *
 */
@Transactional(propagation = Propagation.REQUIRED ,isolation = Isolation.DEFAULT,rollbackFor=Exception.class)
@Service
public class UserShareService extends CrudService<UserShareDao, UserShare>  {
	@Autowired
	private UserShareDao userShareDao;
	@Autowired
	private  UserInfoDao userInfoDao;
	@Autowired
	private DiscountDao discountDao;
	@Autowired
	private DiscountDetailService discountDetailService;
	/**插入新的分享数据*/
	public void insertUserShareMap(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		userShareDao.insertUserShareMap(paramMap);
	}
	
	/**用户注册后填写分享码后分享人得红包的操作*/
	public Map UserRegisterShareGetDiscount(User user) {
		Map<String,Object> returnMap =new HashMap<>();
		String mobile=user.getMobile();
		String shareCode=user.getShareCode();
		//通过电话查找唯一用户
		Map pamMap=new HashMap<>();
		pamMap.put("mobile", mobile);
		pamMap.put("shareCode", shareCode);
		//通过电话的到userInfo信息
		Map userInfoMap= userInfoDao.getByMobile(pamMap);
		//查看此用户是否已经填写过邀请码
		String isInvite="";
		if(userInfoMap!=null) {
			isInvite= (String) userInfoMap.get("is_invite");
		}
		if(shareCode!=null&&!shareCode.equals("")&&(userInfoMap==null||isInvite.equals("0"))) {
			//未填写过邀请码
			//验证该邀请码的用户是谁
			Map shareUserInfoMap= userInfoDao.getByshareCode(shareCode);
			if(shareUserInfoMap!=null) {
				String shareMobile=(String) shareUserInfoMap.get("mobile");
				if(!shareMobile.equalsIgnoreCase(mobile)) {
					//如果查出来的手机和发过来的手机执行邀请邀请成功的操作
					//赠送5块钱
					String shareGivePrice="500";
					//得到该用户的id
					String shareUserId=(String) shareUserInfoMap.get("id");
					Discount discount =new Discount();
					discount.setUserId(shareUserId);
					//通过用户id查找折扣红包表
					List<Discount> discountList= discountDao.findList(discount);
					//获得该用户当前的折扣金额
					if(discountList==null||discountList.size()==0) {
						//如果没创建discount表就开始创建
						Map discountParmMap= new HashMap<>();
						discountParmMap.put("userId", shareUserId);
						discountParmMap.put("discountPrice", "0");
						discountParmMap.put("createDate", new Date());
						discountDao.insertDiscount(discountParmMap);
						discount= discountDao.findList(discount).get(0);
					}else {
						discount=discountList.get(0);
					}
					String discountPriceStr= discount.getDiscountPrice();
					//更新后的价格
					String updateDiscountPrice= ((Integer)(Integer.valueOf(discountPriceStr)+Integer.valueOf(shareGivePrice))).toString();
					discount.setDiscountPrice(updateDiscountPrice);
					discount.setUpdateDate(new Date());
					//折扣主表的更新操作
					discountDao.update(discount);
					//增加折扣子表的操作
					Map discountDetailMap =new HashMap<>();
					//折扣主表的id
					discountDetailMap.put("discountId", discount.getId());
					//明细
					discountDetailMap.put("remarks", "邀请用户成功");
					//得到的价格
					discountDetailMap.put("detailPrice", shareGivePrice);
					// 0收入,1支出
					discountDetailMap.put("detailState", "0");
					discountDetailMap.put("createDate", new Date());
					//增加折扣从详情表的操作
					discountDetailService.insertDiscountDetailMap(discountDetailMap);
					//修改用户标注为已经填写过邀请码不能继续填写了
					pamMap.put("isInvite", "1");
					//通过输入的验证码
					pamMap.put("inviteUserId", shareUserInfoMap.get("id").toString());
					//通过电话修改已经填写过邀请码了
					userInfoDao.updateIsInviteByMobile(pamMap);
					
					
					String inviteUserId=shareUserInfoMap.get("id").toString();
					UserInfo userInfo =new UserInfo();
					userInfo.setId(inviteUserId);
					userInfo=userInfoDao.get(userInfo);
					String selectInviteUserId= userInfo.getInviteUserId();
					if(selectInviteUserId!=null&&!selectInviteUserId.equals("")) {
						//进入二级分销方法,可能有逻辑bug
						erjiShare(selectInviteUserId);
					}
					
					returnMap.put("code", "200");
					returnMap.put("msg", "分享码填写成功");
					return returnMap;
				}else {
					//分享人为自己
					returnMap.put("code", "300");
					returnMap.put("msg", "不能分享自己的邀请码");
					return returnMap;
				}
			}else {
				//邀请码对应的用户为空不进行操作
				returnMap.put("code", "300");
				returnMap.put("msg", "分享码不存在");
				return returnMap;
			}
		}else{
			//填写过邀请码
			returnMap.put("code", "300");
			returnMap.put("msg", "该用户已填写过分享码");
			return returnMap;
		}
	}

	private void erjiShare(String selectInviteUserId) {
		String shareGivePrice="200";
		// TODO Auto-generated method stub
		Discount discounterji =new Discount();
		discounterji.setUserId(selectInviteUserId);
		//通过用户id查找折扣红包表
		List<Discount> discounterjiList= discountDao.findList(discounterji);
		//获得该用户当前的折扣金额
		if(discounterjiList==null||discounterjiList.size()==0) {
			//如果没创建discount表就开始创建
			Map discountParmMap= new HashMap<>();
			discountParmMap.put("userId", selectInviteUserId);
			discountParmMap.put("discountPrice", "0");
			discountParmMap.put("createDate", new Date());
			discountDao.insertDiscount(discountParmMap);
			discounterji= discountDao.findList(discounterji).get(0);
		}else {
			discounterji=discounterjiList.get(0);
		}
		String discountPriceStr= discounterji.getDiscountPrice();
		//更新后的价格
		String updateDiscountPrice= ((Integer)(Integer.valueOf(discountPriceStr)+Integer.valueOf(shareGivePrice))).toString();
		discounterji.setDiscountPrice(updateDiscountPrice);
		discounterji.setUpdateDate(new Date());
		//折扣主表的更新操作
		discountDao.update(discounterji);
		//增加折扣子表的操作
		Map discountDetailMap =new HashMap<>();
		//折扣主表的id
		discountDetailMap.put("discountId", discounterji.getId());
		//明细
		discountDetailMap.put("remarks", "二级分销邀请用户成功");
		//得到的价格
		discountDetailMap.put("detailPrice", shareGivePrice);
		// 0收入,1支出
		discountDetailMap.put("detailState", "0");
		discountDetailMap.put("createDate", new Date());
		//增加折扣从详情表的操作
		discountDetailService.insertDiscountDetailMap(discountDetailMap);
	}
	
	

}
