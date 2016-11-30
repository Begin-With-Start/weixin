package com.xyt.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.xxcb.util.BaseAction;
import com.xyt.dao.XytCollegeDao;
import com.xyt.dao.XytMessageDao;
import com.xyt.dao.XytUserInfoDao;
import com.xyt.po.XytCollege;
import com.xyt.po.XytMessage;
import com.xyt.po.XytUserInfo;
import com.xyt.util.GsonUtil;

@Scope("prototype")
@Component("XytCollegeAction")
public class XytCollegeAction extends BaseAction{

	private static final long serialVersionUID = 7054257612836817102L;

	@Resource
	public XytUserInfoDao xytUserInfoDao;
	
	@Resource
	public XytCollegeDao xytCollegeDao;
	
	@Resource
	public XytMessageDao xytMessageDao;
	
	/**
	 * 根据collegeId查找所有属于该学校的用户信息
	 */
	public String getXytUserInfoByCollegeId(){
		Integer userId = Integer.valueOf(this.getRequest().getParameter("user"));
		Integer collegeId = Integer.valueOf(this.getRequest().getParameter("collegeId"));
		//判断当前用户是否属于该学校，如果不属于该校园则隐藏我要退学按钮
		boolean isBelongToThisCollege = false;
		XytUserInfo xytUserInfo = xytUserInfoDao.getXytUserInfoByRid(userId).get(0);
		if((null != xytUserInfo.getCollege()) && (xytUserInfo.getCollege().getRid().equals(collegeId)))
		{
			isBelongToThisCollege = true;
		}
		XytCollege xytCollege = xytCollegeDao.getXytCollegeByRid(collegeId).get(0);
		List<XytUserInfo> listXytUserInfo = xytUserInfoDao.getXytUserInfoByCollegeRid(xytCollege.getRid());
		//获取所有该学校的留言信息
		List<XytMessage> listXytMessage = xytMessageDao.getXytMessageByCollege(xytCollege.getRid());
		this.getRequest().setAttribute("listXytMessage", listXytMessage);
		this.getRequest().setAttribute("xytCollege", xytCollege);
		this.getRequest().setAttribute("listXytUserInfo", listXytUserInfo);
		this.getRequest().setAttribute("userId", userId);
		this.getRequest().setAttribute("isBelongToThisCollege", isBelongToThisCollege);
		this.getRequest().setAttribute("collegeId", collegeId);
		return "mycampus";
	}
	
	/**
	 * 进入选择校园页面
	 */
	public String chooseCampus()
	{
		List<XytCollege> listXytCollege = xytCollegeDao.getAllXytCollege();
		this.getRequest().setAttribute("listXytCollege", listXytCollege);
		return "choosecampus";
	}
	
	/**
	 * 加入校园
	 */
	public String joinCampus()
	{
		Integer collegeId = Integer.valueOf(this.getRequest().getParameter("collegeId"));
		Integer userId = Integer.valueOf(this.getRequest().getParameter("userId"));
		JSONObject result = new JSONObject();
		//判断是否已经加入校园
		XytUserInfo xytUserInfo = xytUserInfoDao.getXytUserInfoByRid(userId).get(0);
		if(null != xytUserInfo)
		{
			if(null == xytUserInfo.getCollege())
			{
				//将用户的学校设置为collegeId
				xytUserInfoDao.joinCampus(userId, collegeId);
				//添加学校的用户数
				xytCollegeDao.addUserForCollege(collegeId);
				result.put("result", "success");
			}else
			{
				result.put("result", "fail");
			}
			this.showjsondata(GsonUtil.toJson(result));
		}
		
		return null;
	}
	
	/**
	 * 退出学校
	 */
	public String quitCampus()
	{
		Integer userId = Integer.valueOf(this.getRequest().getParameter("userId"));
		Integer collegeId = xytUserInfoDao.getXytUserInfoByRid(userId).get(0).getCollege().getRid();
		xytUserInfoDao.quitCampus(userId);
		xytCollegeDao.reduceUserForCollege(collegeId);
		JSONObject result = new JSONObject();
		result.put("result", "success");
		this.showjsondata(GsonUtil.toJson(result));
		return null;
	}
}
