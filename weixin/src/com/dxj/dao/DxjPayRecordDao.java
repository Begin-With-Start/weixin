package com.dxj.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxcb.util.GenericHibernateDao;

@Transactional
@Component("DxjPayRecordDao")
public class DxjPayRecordDao  extends GenericHibernateDao{

}
