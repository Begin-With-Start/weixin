package com.dxj.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dxj.po.DxjTravelRoute;
import com.xxcb.util.GenericHibernateDao;
import com.xyt.po.XytCourse;

@Transactional
@Component("DxjTravelRouteDao")
public class DxjTravelRouteDao  extends GenericHibernateDao{
	/**
	 * 获取所有的旅游线路信息
	 */
	@SuppressWarnings("unchecked")
	public List<DxjTravelRoute> getAllTravelRoute()
	{
		return this.findBySql(" from DxjTravelRoute where isgroup = 0 order by id", false);
	}
	
	/**
	 * 获取所有的社团信息
	 */
	@SuppressWarnings("unchecked")
	public List<DxjTravelRoute> getAllGroup()
	{
		return this.findBySql(" from DxjTravelRoute where isgroup = 1 order by appliedNumber desc", false);
	}
	
	/**
	 * 根据rid查找课程信息
	 */
	@SuppressWarnings("unchecked")
	public List<DxjTravelRoute> getTravelRouteById(Integer id)
	{
		return this.findBySql(" from DxjTravelRoute where id="+id , false);
	}
}
