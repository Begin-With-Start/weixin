package com.xyt.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.xxcb.util.BaseAction;
import com.xyt.dao.PmessageDao;
import com.xyt.dao.UserAttantionDao;
import com.xyt.dao.XytUserInfoDao;
import com.xyt.po.Pmessage;
import com.xyt.po.UserAttantion;
import com.xyt.po.XytUserInfo;
import com.xyt.util.StringUtil;

@Scope("prototype")
@Component("UserAttantionAction")
public class UserAttantionAction extends BaseAction {

	private static final long serialVersionUID = -6934322419970391860L;
	
	private Integer userId;
	private Integer attantionUserId;
	
	@Resource
	private XytUserInfoDao xytUserInfoDao;
	@Resource
	private UserAttantionDao userAttantionDao;
	
	
	
	/**
	 * 关注或取消关注
	 * @return
	 */
	public String attantion() {
		
		JSONObject json = new JSONObject();
		
		if(userId == null) {
			json.put("content", "用户不存在！");
			json.put("result", "error");
			showjsondata(json.toString());
			return null;
		}
		
		XytUserInfo user = (XytUserInfo)xytUserInfoDao.findByID(XytUserInfo.class, userId);
		if(user == null) {
			json.put("content", "用户不存在！");
			json.put("result", "error");
			showjsondata(json.toString());
			return null;
		}
		
		if(attantionUserId == null) {
			json.put("content", "关注用户不存在！");
			json.put("result", "error");
			showjsondata(json.toString());
			return null;
		}
		
		XytUserInfo attantionUser = (XytUserInfo)xytUserInfoDao.findByID(XytUserInfo.class, attantionUserId);
		if(attantionUser == null) {
			json.put("content", "关注用户不存在！");
			json.put("result", "error");
			showjsondata(json.toString());
			return null;
		}
		
		UserAttantion userAttantion = userAttantionDao.findByUserAndAttantionUser(user, attantionUser);
		if(userAttantion == null) {
			userAttantion = new UserAttantion();
			userAttantion.setCreateTime(new Date());
			userAttantion.setUser(user);
			userAttantion.setAttantionUser(attantionUser);
			userAttantionDao.save(userAttantion);
			
			json.put("content", "attantion");
			json.put("result", "success");
			showjsondata(json.toString());
		}else {
			userAttantionDao.delete(userAttantion);
			
			json.put("content", "cancelAttantion");
			json.put("result", "success");
			showjsondata(json.toString());
		}
		
		return null;
	}
	
	/**
	 * 显示我的好友
	 * @return
	 */
	public String showMyFriends() {
		
		if(userId == null) {
			getRequest().setAttribute("errorMsg", "用户不存在！");
			return "error";
		}
		
		XytUserInfo user = (XytUserInfo)xytUserInfoDao.findByID(XytUserInfo.class, userId);
		if(user == null) {
			getRequest().setAttribute("errorMsg", "用户不存在！");
			return "error";
		}
		
		getRequest().setAttribute("user", user);
		
		List<UserAttantion> userAttantionList = userAttantionDao.findMyFriends(user);
		boolean hasFriends = false;
		if(0 < userAttantionList.size())
		{
			hasFriends = true;
		}
		getRequest().setAttribute("hasFriends", hasFriends);
		getRequest().setAttribute("userAttantionList", userAttantionList);
		
		return "showMyFriends";
	}
	
	
	/**
	 * 显示我的粉丝
	 * @return
	 */
	public String showMyFans() {
		
		if(userId == null) {
			getRequest().setAttribute("errorMsg", "用户不存在！");
			return "error";
		}
		
		XytUserInfo user = (XytUserInfo)xytUserInfoDao.findByID(XytUserInfo.class, userId);
		if(user == null) {
			getRequest().setAttribute("errorMsg", "用户不存在！");
			return "error";
		}
		
		getRequest().setAttribute("user", user);
		
		List<UserAttantion> userAttantionList = userAttantionDao.findMyFans(user);
		
		boolean hasAttantion = false;
		if(0 < userAttantionList.size())
		{
			hasAttantion = true;
		}
		getRequest().setAttribute("hasAttantion", hasAttantion);
		
		
		getRequest().setAttribute("userAttantionList", userAttantionList);
		
		return "showMyFans";
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Integer getAttantionUserId() {
		return attantionUserId;
	}


	public void setAttantionUserId(Integer attantionUserId) {
		this.attantionUserId = attantionUserId;
	}
	


}
