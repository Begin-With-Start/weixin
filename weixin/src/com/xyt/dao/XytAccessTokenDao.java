package com.xyt.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxcb.util.GenericHibernateDao;
import com.xxcb.weixin.ValidateServlet;
import com.xyt.po.XytAccessToken;

@Transactional
@Component("XytAccessTokenDao")
public class XytAccessTokenDao  extends GenericHibernateDao{
	static Logger log4j = Logger.getLogger(ValidateServlet.class.getClass());
	
	/**
	 * 获取accesstoken
	 * @return List
	 * @author lidu
	 * @time 2015.8.5
	 */
	@SuppressWarnings("unchecked")
	public List<XytAccessToken> getAccessToken(String name)
	{
		return this.findBySql(" from XytAccessToken where name = '" + name +"' order by rid desc", false);
	}
	
	/**
	 * 更新数据库中的accesstoken
	 * @param accessToken
	 * @param now
	 * @return boolean
	 * @author lidu
	 * @date 2015.8.6
	 */
	public boolean updateAccessToken(String name, String accessToken,String now)
	{
		String sql =" update XytAccessToken set ACCESSTOKEN = " + "'"+accessToken +"'"+ ", TIME = to_date('"+now.substring(0, 19)+"'"+",'yyyy-MM-dd:hh24:mi:ss') where name = '" + name+"'";
		return this.executeSQL(sql, false);
	}
}
