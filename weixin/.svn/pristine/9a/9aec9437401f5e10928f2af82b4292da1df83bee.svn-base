package com.xxcb.util;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.hql.ast.QueryTranslatorImpl;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 数据库操作公共类
 * @author zhangpeng
 *
 */
@SuppressWarnings("unchecked")
@Transactional
@Component
public class GenericHibernateDao {
	@Resource
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sf) {
	this.sessionFactory = sf;
    }

    /**
         * create session
         * 
         * @return session
         */
    public Session getSession() {
	return sessionFactory.getCurrentSession();
    }

    /**
         * save object
         * 
         * @param obj
         */
    public void save(Object obj) {
    	getSession().save(obj);
    }

    /**
         * update object
         * 
         * @param obj
         */
    public void update(Object obj) {
	getSession().update(obj);
    }

    /**
         * delete object
         * 
         * @param obj
         */
    public void delete(Object obj) {
	getSession().delete(obj);
    }
    /**
     * saveOrUpdate object
     * @param obj
     */
    public void saveOrUpdate(Object obj){
	getSession().saveOrUpdate(obj);
    }

    public void loadObject(Object obj,Serializable id){
    	getSession().load(obj, id);
    }
    /**
         * find all object and no page
         * 
         * @param obj
         * @return List
         */
    public List findAll(Class classzz) {
	Criteria criteria = getSession().createCriteria(classzz);
	return criteria.list();
    }

    /**
         * find by ID
         * 
         * @param classzz
         * @param ID
         * @return Object
         */
    public Object findByID(Class classzz, Serializable ID) {
	return getSession().get(classzz, ID);
    }

    /**  
         * 根据Class来分页
         * @param page
         * @param classzz
         * @return page
         */
    public PagerUtil findByPage(PagerUtil page, Class classzz) {
	try {
	    Criteria criteria = getSession().createCriteria(classzz);
//	    criteria.setProjection(Projections.rowCount());
//	    page.setTotalCount((Integer) criteria.list().get(0));
//	    criteria.setProjection(null);
	    page.setTotalCount(getCountCriteria(criteria));
	    criteria.setFirstResult(page.getPageSize() * (page.getPageNo() - 1));
	    criteria.setMaxResults(page.getPageSize());
	    criteria.addOrder(Order.desc("id"));
	    page.setList(criteria.list());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return page;

    }
    /**
     * 根据Criteria分页
     * @param page
     * @param criteria
     * @return
     */
    public PagerUtil findByCriteriaPage(PagerUtil page, Criteria criteria) {
	try {
		int totalCount = getCountCriteria(criteria);
		if (totalCount>0){
			page.setTotalCount(totalCount);
		    criteria.setFirstResult(page.getPageSize() * (page.getPageNo() - 1));
		    criteria.setMaxResults(page.getPageSize());
		    criteria.addOrder(Order.desc("id"));
		    page.setList(criteria.list());
		}
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return page;
    }
    /**
     * 根据SQL来分页
     * @param page
     * @param hql
     * @param flag
     * @return page
     */
    public PagerUtil findByHqlPage(PagerUtil page,String hql,boolean flag){
	try {
		//long t1 = System.currentTimeMillis();
	    Query query = null ;
	    if (flag){
		query = getSession().createQuery(hql);
	    }else{
		query = getSession().createSQLQuery(hql);
	    }	  
//	   page.setTotalCount(query.list().size());//获取总记录数
	   page.setTotalCount(getCountSql(hql, flag));
	   query.setFirstResult(page.getPageSize() * (page.getPageNo() - 1));
	   query.setMaxResults(page.getPageSize());
	    page.setList(query.list());
		//long t3 = System.currentTimeMillis();
		//Calendar c=Calendar.getInstance(); 
		//c.setTimeInMillis(t3-t1); 
		//System.out.println("耗时: " + c.get(Calendar.MINUTE) + "分 " + c.get(Calendar.SECOND) + "秒 " + c.get(Calendar.MILLISECOND) + " 微秒");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return page;
    }
    
    /**
     * 取list.size总数分页
     * @param page
     * @param hql
     * @param flag
     * @return
     */
    public PagerUtil findBySqlCountPage(PagerUtil page,String hql,boolean flag,Integer count){
    	try {
    		//long t1 = System.currentTimeMillis();
    	    Query query = null ;
    	    if (flag){
    		query = getSession().createQuery(hql);
    	    }else{
    		query = getSession().createSQLQuery(hql);
    	    }	  
    	    page.setTotalCount(count); 
    	   query.setFirstResult(page.getPageSize() * (page.getPageNo() - 1));
    	   query.setMaxResults(page.getPageSize());
    	    page.setList(query.list());
    		//long t3 = System.currentTimeMillis();
    		//Calendar c=Calendar.getInstance(); 
    		//c.setTimeInMillis(t3-t1); 
    		//System.out.println("耗时: " + c.get(Calendar.MINUTE) + "分 " + c.get(Calendar.SECOND) + "秒 " + c.get(Calendar.MILLISECOND) + " 微秒");
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}
    	return page;
    }
     /**

	 * 根据sql语句查询，不带翻页
	 * @param sql
	 * 			String 要查询的sql
	 * @param issql
	 * 			boolean 是sql还是hql 若为sql，则为true，若为hql，则为false
	 * @return
	 * 			List 返回查询的list结果集
	 */
	public List findBySql(String sql, boolean issql) {
		try {
			Query query = null;
			if (issql) {
				query = getSession().createSQLQuery(sql);
			} else {
				query = getSession().createQuery(sql);
			}
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	/**
	 * 执行SQL语句，用于使用Sql语句删除或者修改数据
	 * 
	 * @param sql 完整Sql 语句
	 * 
	 */
	public boolean executeSQL(String sql,boolean issql){
		try {
			if (issql){
				Query query = getSession().createQuery(sql);query.executeUpdate();
			}else{
				SQLQuery query = getSession().createSQLQuery(sql);query.executeUpdate();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	/**
	 * 转换sql语句，获取记录数
	 * @param sql
	 * @param issql
	 * @return 返回sql语句的记录数
	 * 
	 */
	public int getCountSql(String sql, boolean ishql){
		int count = 0;
		try{
			String countSql = "";
			if(!ishql){
				//countSql = sql.trim().toLowerCase();
				countSql = sql.trim();
			}else{
				QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(sql,sql, Collections.EMPTY_MAP, (org.hibernate.engine.SessionFactoryImplementor)sessionFactory);
				queryTranslator.compile(Collections.EMPTY_MAP, false); 
				countSql = queryTranslator.getSQLString();
			}
			//countSql = "select count(1) from (" + countSql.replaceFirst("order by.+", "") + ") temp_c";
			countSql = "select count(1) from " + countSql.replaceFirst("order by.+", "");
			Query query = getSession().createSQLQuery(countSql);
			count = ((Number)query.uniqueResult()).intValue();
			
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return count;
	}
	
	/**
	 * 利用Criteria获取记录数
	 * @param criteria
	 * @return 返回记录数
	 * 
	 */
	public int getCountCriteria(Criteria criteria){
		int count = 0;
		try{
			count = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			criteria.setProjection(null);
		}catch (Exception e) {
			return 0;
		}
		return count;
	}
	
	/**
	 * sql转换dto并分页
	 * @param page
	 * @param hql
	 * @param flag
	 * @param c
	 * @return
	 */
	   public PagerUtil findTransformersBeanPage(PagerUtil page,String hql,boolean flag,Class c){
			try {
				SQLQuery query =getSession().createSQLQuery(hql); 
				   page.setTotalCount(getCountSql(hql, flag));
				   query.setFirstResult(page.getPageSize() * (page.getPageNo() - 1));
				   query.setMaxResults(page.getPageSize());
				   transformersBean(query,c);
			    page.setList(query.list());
			} catch (Exception e) {
			    e.printStackTrace();
			}
			return page;
		    }
	   
	   private List transformersBean(SQLQuery query ,Class c){
		   Field[] f = c.getDeclaredFields();
		   for (int i = 0 ; i<f.length ;i++){
			  String type = f[i].getType().getName();
			  if (type.equals("java.util.Date")){
				  query.addScalar(f[i].getName(),Hibernate.DATE);
			  }
			  if (type.equals("java.sql.Timestamp")){
				  query.addScalar(f[i].getName(),Hibernate.TIMESTAMP);
			  }
			  else if (type.equals("java.lang.String")){
				  query.addScalar(f[i].getName(),Hibernate.STRING);
			  }
	    		 
	    	}
		   query.setResultTransformer( new AliasToBeanResultTransformer(c));
		   return query.list();
	   }
	   
	   /**
		 * sql转换dto
		 * @param page
		 * @param hql
		 * @param flag
		 * @param c
		 * @return
		 */
	   public List findTransformersBeanList(String sql,Class c){
		   SQLQuery query =getSession().createSQLQuery(sql);
		   return transformersBean(query,c);
	   }
}
