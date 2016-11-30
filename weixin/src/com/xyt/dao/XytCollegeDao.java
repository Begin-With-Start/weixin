package com.xyt.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxcb.util.GenericHibernateDao;
import com.xyt.po.XytCollege;

@Transactional
@Component("XytCollegeDao")
public class XytCollegeDao extends GenericHibernateDao{
	
	/**
	 * 根据rid查找学校信息
	 */
	@SuppressWarnings("unchecked")
	public List<XytCollege> getXytCollegeByRid(Integer rid)
	{
		return this.findBySql(" from XytCollege where rid = "+rid+" order by rid desc", false);
	}
	
	/**
	 * 查找所有的学校信息
	 */
	@SuppressWarnings("unchecked")
	public List<XytCollege> getAllXytCollege()
	{
		return this.findBySql(" from XytCollege order by rid desc", false);
	}
	
	/**
	 * 添加学校人数
	 */
	public boolean addUserForCollege(Integer collegeId)
	{
		String sql ="update XytCollege set groupNumber = groupNumber + 1 where rid = " + collegeId;
		return this.executeSQL(sql, false);
	}
	
	/**
	 * 减少学校人数
	 */
	public boolean reduceUserForCollege(Integer collegeId)
	{
		String sql ="update XytCollege set groupNumber = groupNumber - 1 where rid = " + collegeId;
		return this.executeSQL(sql, false);
	}
}	
