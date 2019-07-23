package com.jeesite.modules.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.sys.dao.SysOrderDetailDao;
import com.jeesite.modules.sys.entity.SysOrderDetail;
@Service
@Transactional(readOnly=false)
public class SysOrderDetailService extends CrudService<SysOrderDetailDao, SysOrderDetail> {

}
