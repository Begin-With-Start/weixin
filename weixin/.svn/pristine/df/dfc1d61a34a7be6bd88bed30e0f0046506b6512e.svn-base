package com.xyt.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxcb.util.GenericHibernateDao;
import com.xyt.po.Pmessage;

@Transactional
@Component("PmessageDao")
public class PmessageDao   extends GenericHibernateDao {
	

	@SuppressWarnings("unchecked")
	public List<Pmessage> findOurPmessage(Integer seuserId, Integer reuserId) {
		String hql = "from Pmessage pm where (pm.seuser.rid = ? and pm.reuser.rid = ? and pm.sestatus = 1) or (pm.seuser.rid = ? and pm.reuser.rid = ? and pm.restatus = 1) order by pm.createTime desc";
		Query query = getSession().createQuery(hql);
		query.setParameter(0,seuserId);
		query.setParameter(1,reuserId);
		query.setParameter(2,reuserId);
		query.setParameter(3,seuserId);
		query.setMaxResults(20);
		
		List<Pmessage> pmessageList = query.list();
		if(CollectionUtils.isNotEmpty(pmessageList)) {
			Collections.reverse(pmessageList);
		}
		
		return pmessageList;
	}

	@SuppressWarnings("unchecked")
	public List<Pmessage> findMyPmessage(Integer rid) {
		String sql = "SELECT MAX (TMP1.PMID) pmid FROM ( SELECT pm.PMID, pm.REUSER_RID userid FROM PMESSAGE pm WHERE pm.SEUSER_RID = ? AND pm.SESTATUS = 1 UNION ALL SELECT pm.PMID, pm.SEUSER_RID userid FROM PMESSAGE pm WHERE pm.REUSER_RID = ? AND pm.RESTATUS = 1 ) tmp1 GROUP BY tmp1.userid";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter(0,rid);
		query.setParameter(1,rid);
		List<BigDecimal> list = query.list();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<Integer> pmIdList = new ArrayList<Integer>();
		for(BigDecimal pmId : list) {
			pmIdList.add(pmId.intValue());
		}
		String hql = "from Pmessage pm where pm.pmid in (:pmIdList) order by pm.createTime desc";
		Query query2 = getSession().createQuery(hql);
		query2.setParameterList("pmIdList",pmIdList);
		return query2.list();
	}
	
	
	public int findUnReadCount(Integer myUserId) {
		String sql = "SELECT count(*) FROM Pmessage pm where pm.REUSER_RID = ? and pm.isread = 0 and pm.restatus = 1";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter(0,myUserId);
		return ((BigDecimal) query.uniqueResult()).intValue();
	}
	
	
	public int findUnReadCount(Integer hisUserId, Integer myUserId) {
		String sql = "SELECT count(*) FROM Pmessage pm where pm.SEUSER_RID = ? and pm.REUSER_RID = ? and pm.isread = 0 and pm.restatus = 1";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter(0,hisUserId);
		query.setParameter(1,myUserId);
		return ((BigDecimal) query.uniqueResult()).intValue();
	}
	
	
	
}
