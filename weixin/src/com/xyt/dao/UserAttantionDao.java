package com.xyt.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxcb.util.GenericHibernateDao;
import com.xyt.po.UserAttantion;
import com.xyt.po.XytUserInfo;

@Transactional
@Component("UserAttantionDao")
public class UserAttantionDao   extends GenericHibernateDao {
	
	
	public int findFriendsCount(XytUserInfo user) {
		String hql = "SELECT count(*) FROM UserAttantion ua where ua.user = :user";
		Query query = getSession().createQuery(hql);
		query.setParameter("user", user);
		return ((Long) query.uniqueResult()).intValue();
	}
	
	public int findFansCount(XytUserInfo user) {
		String hql = "SELECT count(*) FROM UserAttantion ua where ua.attantionUser = :attantionUser";
		Query query = getSession().createQuery(hql);
		query.setParameter("attantionUser", user);
		return ((Long) query.uniqueResult()).intValue();
	}
	
	public UserAttantion findByUserAndAttantionUser(XytUserInfo user, XytUserInfo attantionUser) {
		String hql = "from UserAttantion ua where ua.user = :user and ua.attantionUser = :attantionUser";
		Query query = getSession().createQuery(hql);
		query.setParameter("user", user);
		query.setParameter("attantionUser", attantionUser);
		Object obj = query.uniqueResult();
		if(obj != null) {
			return (UserAttantion)obj;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<UserAttantion> findMyFriends(XytUserInfo user) {
		String hql = "from UserAttantion ua where ua.user = :user order by ua.createTime desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("user", user);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserAttantion> findMyFans(XytUserInfo user) {
		String hql = "from UserAttantion ua where ua.attantionUser = :attantionUser order by ua.createTime desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("attantionUser", user);
		return query.list();
	}
	
	
	
}
