package com.jeesite.modules.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.sys.dao.SysAirDrugDao;
import com.jeesite.modules.sys.dao.SysDoctorPicDao;
import com.jeesite.modules.sys.entity.SysAirDrug;
import com.jeesite.modules.sys.entity.SysDoctorPic;
@Service
@Transactional(readOnly=false)
public class SysDoctorPicService extends CrudService<SysDoctorPicDao, SysDoctorPic> {

}
