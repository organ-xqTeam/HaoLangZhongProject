package com.jeesite.modules.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.sys.dao.SysDoctorInfoDao;
import com.jeesite.modules.sys.entity.SysDoctorInfo;
@Service
@Transactional(readOnly=true)
public class SysDoctorInfoService extends CrudService<SysDoctorInfoDao, SysDoctorInfo>  {

}
