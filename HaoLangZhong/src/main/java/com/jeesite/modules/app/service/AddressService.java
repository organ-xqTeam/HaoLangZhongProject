package com.jeesite.modules.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.app.dao.AddressDao;
import com.jeesite.modules.app.dao.AirDrugDao;
import com.jeesite.modules.app.dao.AirPrescriptionDao;
import com.jeesite.modules.app.dao.DiscountDao;
import com.jeesite.modules.app.entity.Address;

import com.jeesite.modules.app.entity.Discount;
@Transactional(propagation = Propagation.REQUIRED ,isolation = Isolation.DEFAULT,rollbackFor=Exception.class)
@Service
public class AddressService extends CrudService<AddressDao,Address> {
	
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private AirDrugDao airDrugDao;
	@Autowired
	private AirPrescriptionDao airPrescriptionDao;
	@Autowired
	private DiscountDao discountDao;
	/**增加地址*/
	public void insertAddress(Map parmMap) {
		// TODO Auto-generated method stub
		try {
			String isDefault=(String) parmMap.get("isDefault");
			if(isDefault.trim().equals("1")) {
				//将此用户下的地址isDefault全更新成0
				addressDao.updateIsDefaultByUserId(parmMap);
			}
			addressDao.insertAddress(parmMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**查看此用户下的所有地址*/
	public List<Map> showAddressByUserId(Map parmMap) {
		// TODO Auto-generated method stub
		return addressDao.showAddressByUserId(parmMap);
	}
	/**修改默认地址*/
	public void modifyAddressIsDefault(Map parmMap) {
		// TODO Auto-generated method stub
		//将此用户下的地址isDefault全更新成0
		addressDao.updateIsDefaultByUserId(parmMap);
		//将单独的addressId 的 isDefault更新成1
		addressDao.updateIsDefault1ById(parmMap);
		
	}
	/**删除地址*/
	public void delAddress(Map parmMap) {
		// TODO Auto-generated method stub
		addressDao.delAddress(parmMap);
	}
	/**修改地址*/
	public void updateAddress(Map parmMap) {
		// TODO Auto-generated method stub\
		
		addressDao.updateAddress(parmMap);
	}
	/**通过主键查找唯一地址 */
	public Map showAddressByPrimary(Map parmMap) {
		// TODO Auto-generated method stub
		return addressDao.showAddressByPrimary(parmMap);
	}
	/**得到此用户的默认收货地址*/
	public Map showAddressDefaultByUserId(Map parmMap) {
		// TODO Auto-generated method stub
		return addressDao.showAddressDefaultByUserId(parmMap);
	}
	public List<Map> showPitchProduct(Map parmMap) {
		// TODO Auto-generated method stub
		List<Map> productMapList= new ArrayList<>();
		List<Map> drugMapList= new ArrayList<>();
		List<Map> airPrescriptionMapList= new ArrayList<>();
		if(parmMap.get("drudIds")!=null) {
			String drudIds=parmMap.get("drudIds").toString();
			String[]  drudIdg=drudIds.split(",");
			List drudIdsList= new ArrayList<>(Arrays.asList(drudIdg));
			System.out.println(drudIdsList);
			//通过drudIds们找到药品列表
			drugMapList=airDrugDao.getlistByDrudIds(drudIdsList);
			productMapList.addAll(drugMapList);
		}
		if(parmMap.get("prescriptionIds")!=null) {
			String prescriptionIds=parmMap.get("prescriptionIds").toString();
			String[]  prescriptionIdg=prescriptionIds.split(",");
			List prescriptionIdsList= new ArrayList<>(Arrays.asList(prescriptionIdg));
			System.out.println(prescriptionIdsList);
			//通过prescriptionIds们找到药方列表
			airPrescriptionMapList=airPrescriptionDao.getlistByPrescriptionIds(prescriptionIdsList);
			productMapList.addAll(airPrescriptionMapList);
		}
		return productMapList;
	}
	/**红包的计算的操作
	 * @param productTotalPrice */
	public Integer showDiscountArithmetic(Map parmMap, Integer productTotalPrice) {
		/**通过userId查找*/
		Map discountMap= discountDao.selectByUserId(parmMap);
		Integer discountPrice=0;
		if(discountMap!=null) {
			discountPrice= Integer.parseInt(discountMap.get("discount_price").toString());
			if(discountPrice>=productTotalPrice) {
				//折扣金额大于商品总价格 返回折扣金额为 商品总金额
				return productTotalPrice;
			}else {
				//折扣金额小于商品总金额返回折扣金额
				return discountPrice;
			}
			
		}else {
			//为空时插入新表
			//插入新表
			Discount discount =new Discount();
			discount.setCreateDate(new Date());
			discount.setUpdateDate(new Date());
			discount.setUserId(parmMap.get("userId").toString());
			discountDao.insert(discount);
			//返回0
			return 0;
		}
		
		
	}
	

}
