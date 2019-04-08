package com.jeesite.modules.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.app.dao.MessageDao;
import com.jeesite.modules.app.entity.Message;

@Transactional(propagation = Propagation.REQUIRED ,isolation = Isolation.DEFAULT,rollbackFor=Exception.class)
@Service
public class MessageService  extends CrudService<MessageDao,Message> {

}
