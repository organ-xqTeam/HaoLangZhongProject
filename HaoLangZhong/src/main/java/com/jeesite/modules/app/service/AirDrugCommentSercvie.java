package com.jeesite.modules.app.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.app.dao.AirDrugCommentDao;
import com.jeesite.modules.app.entity.AirDrugComment;
@Service
@Transactional(readOnly=true)
public class AirDrugCommentSercvie extends CrudService<AirDrugCommentDao,AirDrugComment> {
	@Autowired
	private AirDrugCommentDao airDrugCommentDao;
	/**通过drugId和userId得到评价信息*/
	public List<Map> findAirDrugCommentListByGrugIdAndUserId(Map<String, Object> airDrugCommentParmMap) {
		//通过drugId和userId得到评价信息
		return airDrugCommentDao.findAirDrugCommentListByGrugIdAndUserId(airDrugCommentParmMap);
		
	}
	/**通过 GrugId和UserId获取每个星级的数量 star_grade 星级 ,count数量*/
	public Map findAirDrugCommentStarCountByGrugIdAndUserId(Map<String, Object> airDrugCommentParmMap) {
		Map<String,Object> returnMap = new HashMap<>();
		// TODO Auto-generated method stub
		@SuppressWarnings("rawtypes")
		List<Map> StarCountMapList=  airDrugCommentDao.findAirDrugCommentStarCountByGrugIdAndUserId(airDrugCommentParmMap);
		Long  commentCountTotal= (long)0;
		Long fineCount=(long)0;
		for (int i = 0; i < StarCountMapList.size(); i++) {
			commentCountTotal+=(Long)StarCountMapList.get(i).get("count");
			if(StarCountMapList.get(i).get("star_grade").toString().trim().equals("4")||StarCountMapList.get(i).get("star_grade").toString().trim().equals("5")){
				fineCount+=(Long)StarCountMapList.get(i).get("count");
			}
		}
		//计算好评率
		//System.out.println(((Long)((fineCount/commentCountTotal)*100)).toString());
		//String fineOdds=((Long)((fineCount/starCounttotal)*100)).toString().substring(0, 2);
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(2);
		String fineOdds="0";
		try {
			fineOdds = numberFormat.format( (float)fineCount / (float)commentCountTotal * 100);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fineOdds="0";
		}
		System.out.println(fineOdds);
		returnMap.put("commentCountTotal", commentCountTotal);
		returnMap.put("fineOdds", fineOdds);
		return returnMap;
	}
	

}
