package com.xyt.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxcb.util.GenericHibernateDao;
import com.xyt.po.XytOrder;

@Transactional
@Component("XytOrderDao")
public class XytOrderDao extends GenericHibernateDao{
	
	/**
	 * 
	 */
	public boolean changeOrderPaindStatus(Integer rid)
	{
		String sql ="update XytOrder set Paied = 1 where rid = " + rid;
		return this.executeSQL(sql, false);
	}
	
	/**
	 * 根据rid查找对应的订单
	 */
	@SuppressWarnings("unchecked")
	public List<XytOrder> getXytOrderByRid(Integer rid)
	{
		return this.findBySql(" from XytOrder where rid = "+rid+" order by rid desc", false);
	}
	
	/**
	 * 根据xytuserinfo查找所有课程订单
	 */
	@SuppressWarnings("unchecked")
	public List<XytOrder> getAllXytOrdeByRid(Integer rid)
	{
		return this.findBySql(" from XytOrder where xytuserinfo_rid = "+rid+" order by rid desc", false);
	}
	
	/**
	 * 根据xytuserinfo查找所有的已报名课程订单
	 */
	@SuppressWarnings("unchecked")
	public List<XytOrder> getApplideXytOrdeByRid(Integer rid)
	{
		return this.findBySql(" from XytOrder where xytuserinfo_rid = "+rid+" and Audition = 0 order by rid desc", false);
	}
	
	/**
	 * 根据xytuserinfo查找所有的试听课程订单
	 */
	@SuppressWarnings("unchecked")
	public List<XytOrder> getAuditionXytOrdeByRid(Integer rid)
	{
		return this.findBySql(" from XytOrder where xytuserinfo_rid = "+rid+" and Audition = 1 order by rid desc", false);
	}
	
	/**
	 * 根据课程查找对应的已缴费报名课程订单
	 */
	@SuppressWarnings("unchecked")
	public List<XytOrder> getAppliedOrderByCourseRid(Integer courseRid)
	{
		
		return this.findBySql(" from XytOrder where xytCourse_rid = "+courseRid+" and Audition = 0 and Paied = 1 order by rid desc", false);
	}
	
	/**
	 * 根据课程查找对应的报名试听订单
	 */
	@SuppressWarnings("unchecked")
	public List<XytOrder> getAuditionOrderByCourseRid(Integer courseRid)
	{
		
		return this.findBySql(" from XytOrder where xytCourse_rid = "+courseRid+" and Audition = 1 order by rid desc", false);
	}
	
	/**
	 * 根据userRid、courseRid,判断是否已报名该课程
	 */
	@SuppressWarnings("unchecked")
	public List<XytOrder> getOrderByUserAndCourse(Integer userRid, Integer courseRid)
	{
		return this.findBySql(" from XytOrder where xytuserinfo_rid = "+userRid+" and xytCourse_rid = "+ courseRid +" order by rid desc", false);
	}
	
	/**
	 * 根据userRid、courseRid判断是否已经报名并且付费
	 */
	@SuppressWarnings("unchecked")
	public List<XytOrder> getAppliedOrderByUserAndCourse(Integer userRid, Integer courseRid)
	{
		return this.findBySql(" from XytOrder where xytuserinfo_rid = "+userRid+" and xytCourse_rid = "+ courseRid +" and audition = 0 and paied = 1 order by rid desc", false);
	}
}
