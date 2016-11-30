package com.xyt.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxcb.util.GenericHibernateDao;
import com.xyt.po.XytScoreInfo;

@Transactional
@Component("XytScoreInfoDao")
public class XytScoreInfoDao  extends GenericHibernateDao{
	/**
	 * 获取用户所有的积分信息
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XytScoreInfo> getXytScoreInfoByUserId(Integer userId)
	{
		return this.findBySql(" from XytScoreInfo where selfuserinfo_rid = '"+userId+"' order by rid desc", false);
	}
}
