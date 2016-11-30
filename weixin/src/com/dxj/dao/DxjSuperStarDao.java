package com.dxj.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dxj.po.DxjSuperStar;
import com.xxcb.util.GenericHibernateDao;

@Transactional
@Component("DxjSuperStarDao")
public class DxjSuperStarDao  extends GenericHibernateDao{
	/**
	 * 获取所有的网红信息
	 */
	@SuppressWarnings("unchecked")
	public List<DxjSuperStar> getAllSuperStar()
	{
		return this.findBySql(" from DxjSuperStar", false);
	}
	
	
}
