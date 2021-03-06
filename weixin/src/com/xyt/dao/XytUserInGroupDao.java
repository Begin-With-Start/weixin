package com.xyt.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxcb.util.GenericHibernateDao;
import com.xyt.po.XytUserInGroup;

@Transactional
@Component("XytUserInGroupDao")
public class XytUserInGroupDao extends GenericHibernateDao{
	/**
	 * 根据群组信息查找所有的群组成员
	 */
	@SuppressWarnings("unchecked")
	public List<XytUserInGroup> getUserInGroupByGroupId(Integer groupId)
	{
		return this.findBySql(" from XytUserInGroup where group_rid = "+groupId+" and remove = 1 order by rid desc", false);
	}
	
	/**
	 * 根据用户信息查找所有的群组
	 */
	@SuppressWarnings("unchecked")
	public List<XytUserInGroup> getUserInGroupByUserId(Integer userId)
	{
		return this.findBySql(" from XytUserInGroup where xytuserinfo_rid = "+userId+" and remove = 1 order by rid desc", false);
	}
	
	/**
	 * 根据群组rid查找所有的群组成员
	 */
	@SuppressWarnings("unchecked")
	public List<XytUserInGroup> getUserInGroupByrid(Integer rid)
	{
		return this.findBySql(" from XytUserInGroup where rid = "+rid+" order by rid desc", false);
	}
	
	/**
	 * 判断用户是否已经在某个群组中
	 */
	@SuppressWarnings("unchecked")
	public List<XytUserInGroup> getUserInGroupByUserIdAndGroupId(Integer userId, Integer groupId)
	{
		return this.findBySql(" from XytUserInGroup where xytuserinfo_rid = "+userId+" and group_rid = "+ groupId +" order by rid desc", false);
	}
	
	/**
	 * 根据rid查找对应的加入群组记录
	 */
	@SuppressWarnings("unchecked")
	public List<XytUserInGroup> getXytUserInGroupByRid(Integer rid)
	{
		return this.findBySql(" from XytUserInGroup where rid = '"+rid+"' order by rid desc", false);
	}
	
	/**
	 * 根据grouporder及userinfo查找对应的群组记录
	 */
	@SuppressWarnings("unchecked")
	public List<XytUserInGroup> getXytUserInGroupByGroupOrderRid(Integer groupOrderRid, Integer userInfoRid)
	{
		return this.findBySql(" from XytUserInGroup where group_rid = '"+groupOrderRid+"' and xytuserinfo_rid = '"+userInfoRid+"' order by rid desc", false);
	}
}
