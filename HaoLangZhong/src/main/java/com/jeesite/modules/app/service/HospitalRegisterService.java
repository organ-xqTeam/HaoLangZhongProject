package com.jeesite.modules.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.app.dao.HospitalRegisterDao;
import com.jeesite.modules.app.entity.HospitalRegister;

@Transactional(propagation = Propagation.REQUIRED ,isolation = Isolation.DEFAULT,rollbackFor=Exception.class)
@Service
public class HospitalRegisterService extends CrudService<HospitalRegisterDao, HospitalRegister>  {

}
