package com.xyt.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.xxcb.util.BaseAction;
import com.xyt.dao.XytTercherInfoDao;
import com.xyt.po.XytCourse;
import com.xyt.po.XytTercherInfo;
import com.xyt.util.GsonUtil;

@Scope("prototype")
@Component("XytTercherInfoAction")
public class XytTercherInfoAction  extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8658168421134494580L;
	
	/**
	 * 根据教师名称查找对应的教师信息
	 */
	@Resource
	public XytTercherInfoDao xytTercherInfoDao;
	
	public String getXytTercherInfoByName()
	{
		String name = this.getRequest().getParameter("tercherName");
		List<XytTercherInfo> listXytTercherInfo = xytTercherInfoDao.getXytTercherInfoByName(name);
		this.showjsondata(GsonUtil.toJson(listXytTercherInfo));
		return null;
	}
	
}
