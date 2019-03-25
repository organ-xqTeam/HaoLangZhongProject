/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.dao;

import java.util.List;
import java.util.Map;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.Address;

/**
 * 收货地址表DAO接口
 * @author 范耘诚
 * @version 2019-03-11
 */
@MyBatisDao
public interface AddressDao extends CrudDao<Address> {
	/**增加地址*/
	void insertAddress(Map parmMap);
	/**将此用户下的地址isDefault全更新成0*/
	void updateIsDefaultByUserId(Map parmMap);
	/**查看此用户下的所有地*/
	List<Map> showAddressByUserId(Map parmMap);
	/**将单独的addressId 的 isDefault更新成1*/
	void updateIsDefault1ById(Map parmMap);
	/**删除地址*/
	void delAddress(Map parmMap);
	/**修改地址*/
	void updateAddress(Map parmMap);
	/**通过主键查找唯一地址*/
	Map showAddressByPrimary(Map parmMap);
	/**得到此用户的默认收货地址*/
	Map showAddressDefaultByUserId(Map parmMap);
	
}