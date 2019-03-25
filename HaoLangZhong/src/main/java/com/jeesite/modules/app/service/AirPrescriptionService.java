package com.jeesite.modules.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.app.dao.AirPrescriptionDao;
import com.jeesite.modules.app.entity.AirPrescription;

/**
 *   药方service
 * @author 1111111
 *
 */

@Service
@Transactional(readOnly=true)
public class AirPrescriptionService extends CrudService<AirPrescriptionDao, AirPrescription>  {
	
	@Autowired
	private  AirPrescriptionDao airPrescriptionDao ;
	//插入数据
	@Transactional(propagation = Propagation.REQUIRED ,isolation = Isolation.DEFAULT,rollbackFor=Exception.class)
	public void insertAirPrescription(AirPrescription insertAirPrescription) {
		// TODO Auto-generated method stub
		airPrescriptionDao.insertAirPrescription(insertAirPrescription);
	}
	public static List<Map> getlistByPrescriptionIds(Map parmMap) {
		// TODO Auto-generated method stub
		return null;
	}

}
