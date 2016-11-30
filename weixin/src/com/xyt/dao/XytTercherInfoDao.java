package com.xyt.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxcb.util.GenericHibernateDao;
import com.xyt.po.XytCourse;
import com.xyt.po.XytTercherInfo;

@Transactional
@Component("XytTercherInfoDao")
public class XytTercherInfoDao  extends GenericHibernateDao{
	
	/**
	 * 根据教师名称查找对应的教师信息
	 */
	@SuppressWarnings("unchecked")
	public List<XytTercherInfo> getXytTercherInfoByName(String name)
	{
		return this.findBySql(" from XytTercherInfo where name = '"+name+"' order by rid desc", false);
	}
	
	/**
	 * 查找所有的教师信息
	 */
	@SuppressWarnings("unchecked")
	public List<XytTercherInfo> getAllXytTercherInfo()
	{
		return this.findBySql(" from XytTercherInfo order by rid desc", false);
	}
	
	/**
	 * 根据rid查找教师
	 */
	@SuppressWarnings("unchecked")
	public List<XytTercherInfo> getXytTercherInfoByRid(Integer rid)
	{
		return this.findBySql(" from XytTercherInfo where rid="+rid+" order by rid desc", false);
	}
}
