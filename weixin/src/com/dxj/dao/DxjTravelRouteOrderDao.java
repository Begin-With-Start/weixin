package com.dxj.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dxj.po.DxjTravelRouteOrder;
import com.xxcb.util.GenericHibernateDao;

@Transactional
@Component("DxjTravelRouteOrderDao")
public class DxjTravelRouteOrderDao   extends GenericHibernateDao{
	/**
	 * 根据userRid、routeId,判断是否已报名该课程
	 */
	@SuppressWarnings("unchecked")
	public List<DxjTravelRouteOrder> getOrderByUserAndRoute(Integer userRid, Integer routeId)
	{
		return this.findBySql(" from DxjTravelRouteOrder where xytuserinfo_rid = "+userRid+" and route_id = "+ routeId +" order by id desc", false);
	}
	
	/**
	 * 根据routeId获取所有的该线路的订单
	 */
	@SuppressWarnings("unchecked")
	public List<DxjTravelRouteOrder> getOrderByRoute(Integer routeId)
	{
		return this.findBySql(" from DxjTravelRouteOrder where route_id = "+ routeId +" order by id desc", false);
	}
}
