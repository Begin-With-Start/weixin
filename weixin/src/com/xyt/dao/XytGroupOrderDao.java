package com.xyt.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxcb.util.GenericHibernateDao;
import com.xyt.po.XytGroupOrder;

@Transactional
@Component("XytGroupOrderDao")
public class XytGroupOrderDao extends GenericHibernateDao{
	/**
	 * 查找所有的正在开团的订单
	 * @param button
	 * @author lidu
	 * @date 2016.7.13
	 */
	@SuppressWarnings("unchecked")
	public List<XytGroupOrder> getXytGroupOrderInProceed()
	{
		return this.findBySql(" from XytGroupOrder where proceed = 1 and remove = 1 order by rid desc", false);
	}
	
	/**
	 * 根据groupid查找对应的grouporder
	 */
	
	@SuppressWarnings("unchecked")
	public List<XytGroupOrder> getGroupOrderByGroupId(Integer groupId){
		
		return this.findBySql(" from XytGroupOrder where rid = "+ groupId+ " order by rid desc", false);
	}
	
	/**
	 * 团队已有人数加1
	 */
	public boolean groupOrderAddMemberNumber(Integer groupId)
	{
		String sql ="update XytGroupOrder set memberNumber = memberNumber + 1 where rid = " + groupId;
		return this.executeSQL(sql, false);
	}
	
	/**
	 * 团队已有人数减1
	 */
	public boolean groupOrderSubtractMemberNumber(Integer groupId)
	{
		String sql ="update XytGroupOrder set memberNumber = memberNumber - 1 where rid = " + groupId;
		return this.executeSQL(sql, false);
	}
}
