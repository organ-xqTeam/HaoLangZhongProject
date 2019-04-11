package com.jeesite.modules.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.app.dao.AirDrugDao;
import com.jeesite.modules.app.entity.AirDrug;


@Service
@Transactional(readOnly=true)
public class AirDrugService extends CrudService<AirDrugDao, AirDrug> {
	//AirDrugService.getAirDrugFirstList(AirDrug);
	@Autowired
	private  AirDrugDao airDrugDao;
	/**热销优先显示5条*/
	public List<Map> getAirDrugFirstList(AirDrug airDrug) {
		// TODO Auto-generated method stub
		return airDrugDao.getAirDrugFirstList(airDrug);
	}

	/**通过搜索内容进行各种关键词模糊查询药品*/
	public List<Map> queryListLike(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return airDrugDao.queryListLike(paramMap);
	}
	/**通过id获得商品详情信息和库存*/
	public Map findOneAirDrugAndInventoryById(AirDrug airDrug) {
		// TODO Auto-generated method stub
		return airDrugDao.findOneAirDrugAndInventoryById(airDrug);
	}
	/**通过药品id获取此药品的类别显示出相关推荐的药品  drugId*/
	public List<Map> findCommendAirDrugListBydrugId(Map<String, Object> commendDrugParmMap) {
		// TODO Auto-generated method stub
		return airDrugDao.findCommendAirDrugListBydrugId(commendDrugParmMap);
	}
	/**搜索中医药模糊搜索和选择类别之后分页**/
	public List<Map<String,String>> findAirDrugSearch(Map<String, Object> airDrugSearchParmMap) {
		// TODO Auto-generated method stub
		return airDrugDao.findAirDrugSearch(airDrugSearchParmMap);
	}

}
