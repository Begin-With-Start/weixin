package com.xyt.action;

import java.util.Date;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.xxcb.util.BaseAction;
import com.xyt.dao.XytScoreInfoDao;
import com.xyt.dao.XytUserInfoDao;
import com.xyt.po.XytScoreConfig;
import com.xyt.po.XytScoreInfo;
import com.xyt.po.XytUserInfo;
import com.xyt.util.StringUtil;

@Scope("prototype")
@Component("RegisterAction")
public class RegisterAction extends BaseAction {

	private static final long serialVersionUID = -6934322419970391860L;
	
	private XytUserInfo xytUserInfo;
	private String openId;
	private String redirectUrl;
	
	
	@Resource
	private XytUserInfoDao xytUserInfoDao;
	@Resource
	private XytScoreInfoDao xytScoreInfoDao;
	
	public String showRegister() {
		
		if(StringUtils.isEmpty(openId)) {
			getRequest().setAttribute("errorMsg", "微信身份ID不存在！");
			return "error";
		}
		
		xytUserInfo = xytUserInfoDao.getXytUserInfoByOpenId(openId);
		if(xytUserInfo == null) {
			getRequest().setAttribute("errorMsg", "用户不存在！");
			return "error";
		}
		
		return "showRegister";
	}
	
	
	public String register() {
		
		JSONObject json = new JSONObject();
		String name = xytUserInfo.getName();
		String telNumber = xytUserInfo.getTelNumber();
		String verifyCode = getRequest().getParameter("verifyCode");
		
		if(StringUtils.isEmpty(name)) {
			json.put("content", "请填写您的姓名！");
			json.put("result", "error");
			showjsondata(json.toString());
			return null;
		}
		
		if(StringUtils.isEmpty(telNumber)) {
			json.put("content", "请填写您的手机号码！");
			json.put("result", "error");
			showjsondata(json.toString());
			return null;
		}
		
		if(!StringUtil.isMobileNO(telNumber)) {
			json.put("content", "请填写有效的手机号码！");
			json.put("result", "error");
			showjsondata(json.toString());
			return null;
		}
		
		if(StringUtils.isEmpty(verifyCode)) {
			json.put("content", "请填写验证码！");
			json.put("result", "error");
			showjsondata(json.toString());
			return null;
		}
		
		if(!verifyCode.equalsIgnoreCase((String)getSession().getAttribute("verifyCode"))) {
			json.put("content", "验证码错误！");
			json.put("result", "error");
			showjsondata(json.toString());
			return null;
		}else {
			getSession().setAttribute("verifyCode", "");
		}
		
		if(StringUtils.isEmpty(xytUserInfo.getOpenid())) {
			json.put("content", "微信身份ID不存在！");
			json.put("result", "error");
			showjsondata(json.toString());
			return null;
		}
		
		XytUserInfo pXytUserInfo = xytUserInfoDao.getXytUserInfoByOpenId(xytUserInfo.getOpenid());
		if(pXytUserInfo == null) {
			json.put("content", "用户不存在！");
			json.put("result", "error");
			showjsondata(json.toString());
			return null;
		}
		
		//积分操作，帮好友加积分
		String inputScoreCode = xytUserInfo.getInputScoreCode();
		boolean hasInputScoreCode = pXytUserInfo.isHasInputScoreCode();
		if(!StringUtils.isEmpty(inputScoreCode))
		{
			if(!hasInputScoreCode)
			{
				XytUserInfo sXytUserInfo = xytUserInfoDao.getXytUserInfoByScoreCode(inputScoreCode);
				if(null == sXytUserInfo)
				{
					json.put("content", "您输入的邀请码有误，请核对后输入！");
					json.put("result", "error");
					showjsondata(json.toString());
					return null;
				}else if(sXytUserInfo.getRid().equals(pXytUserInfo.getRid()))
				{
					json.put("content", "不可输入自己的邀请码，请核对后输入！");
					json.put("result", "error");
					showjsondata(json.toString());
					return null;
				}else
				{
					//输入的验证码正确，添加一条XytScoreInfo信息，当前用户状态改变为已输入邀请码
					System.out.println("输入的验证码正确！");
					XytScoreInfo xytScoreInfo = new XytScoreInfo();
					xytScoreInfo.setCreateTime(new Date());
					xytScoreInfo.setDescription(XytScoreConfig.addScoreDiscription);
					xytScoreInfo.setOtherUserInfo(pXytUserInfo);
					xytScoreInfo.setPay(false);
					xytScoreInfo.setScoreCode(inputScoreCode);
					xytScoreInfo.setScoreOption(XytScoreConfig.addScoreByFriends);
					xytScoreInfo.setSelfUserInfo(sXytUserInfo);
					xytScoreInfo.setStatus(true);
					xytScoreInfoDao.save(xytScoreInfo);
					System.out.println("添加一条XytScoreInfo信息正确！");
					pXytUserInfo.setHasInputScoreCode(true);
					pXytUserInfo.setInputScoreCode(inputScoreCode);
				}
			}
		}
		
		pXytUserInfo.setName(name);
		pXytUserInfo.setTelNumber(telNumber);
		xytUserInfoDao.update(pXytUserInfo);
		
		json.put("content", "提交成功！");
		json.put("result", "success");
		showjsondata(json.toString());
		return null;
	}
	

	public XytUserInfo getXytUserInfo() {
		return xytUserInfo;
	}

	public void setXytUserInfo(XytUserInfo xytUserInfo) {
		this.xytUserInfo = xytUserInfo;
	}


	public String getOpenId() {
		return openId;
	}


	public void setOpenId(String openId) {
		this.openId = openId;
	}


	public String getRedirectUrl() {
		return redirectUrl;
	}


	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	

}
