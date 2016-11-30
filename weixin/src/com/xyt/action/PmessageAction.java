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
import com.xyt.dao.XytUserInfoDao;
import com.xyt.po.Pmessage;
import com.xyt.po.XytUserInfo;
import com.xyt.util.StringUtil;

@Scope("prototype")
@Component("PmessageAction")
public class PmessageAction extends BaseAction {

	private static final long serialVersionUID = -6934322419970391860L;
	
	private Integer seuserId;
	private Integer reuserId;
	private Integer rid;
	private String content;
	
	@Resource
	private XytUserInfoDao xytUserInfoDao;
	
	@Resource
	private PmessageDao pmessageDao;
	
	public String showSendPmessage() {
		
		if(seuserId == null) {
			getRequest().setAttribute("errorMsg", "发送用户不存在！");
			return "error";
		}
		
		XytUserInfo seuser = (XytUserInfo)xytUserInfoDao.findByID(XytUserInfo.class, seuserId);
		if(seuser == null) {
			getRequest().setAttribute("errorMsg", "发送用户不存在！");
			return "error";
		}
		
		if(reuserId == null) {
			getRequest().setAttribute("errorMsg", "接收用户不存在！");
			return "error";
		}
		
		XytUserInfo reuser = (XytUserInfo)xytUserInfoDao.findByID(XytUserInfo.class, reuserId);
		if(reuser == null) {
			getRequest().setAttribute("errorMsg", "接收用户不存在！");
			return "error";
		}
		
		getRequest().setAttribute("seuser", seuser);
		getRequest().setAttribute("reuser", reuser);
		
		List<Pmessage> pmessageList = pmessageDao.findOurPmessage(seuserId, reuserId);
		if(CollectionUtils.isNotEmpty(pmessageList)) {
			for(Pmessage pmessage : pmessageList) {
				if(pmessage.getReuser().getRid().intValue() == seuserId.intValue()) {
					if(pmessage.getIsread().intValue() == Pmessage.UNREAD) {
						pmessage.setIsread(Pmessage.READ);
						pmessageDao.update(pmessage);
					}
				}else {
					pmessage.setMy(true);
				}
			}
		}
		getRequest().setAttribute("pmessageList", pmessageList);
		
		return "showSendPmessage";
	}
	
	
	public String sendPmessage() {
		
		JSONObject json = new JSONObject();
		
		if(StringUtils.isEmpty(content)) {
			json.put("content", "请填写私信内容！");
			json.put("result", "error");
			showjsondata(json.toString());
			return null;
		}
		
		if(seuserId == null) {
			json.put("content", "发送用户不存在！");
			json.put("result", "error");
			showjsondata(json.toString());
			return null;
		}
		
		XytUserInfo seuser = (XytUserInfo)xytUserInfoDao.findByID(XytUserInfo.class, seuserId);
		if(seuser == null) {
			json.put("content", "发送用户不存在！");
			json.put("result", "error");
			showjsondata(json.toString());
			return null;
		}
		
		if(reuserId == null) {
			json.put("content", "接收用户不存在！");
			json.put("result", "error");
			showjsondata(json.toString());
			return null;
		}
		
		XytUserInfo reuser = (XytUserInfo)xytUserInfoDao.findByID(XytUserInfo.class, reuserId);
		if(reuser == null) {
			json.put("content", "接收用户不存在！");
			json.put("result", "error");
			showjsondata(json.toString());
			return null;
		}
		
		Pmessage pmessage = new Pmessage();
		pmessage.setContent(content);
		pmessage.setCreateTime(new Date());
		pmessage.setIpaddress(getRequest().getRemoteAddr());
		pmessage.setIsread(Pmessage.UNREAD);
		pmessage.setRestatus(1);
		pmessage.setReuser(reuser);
		pmessage.setSestatus(1);
		pmessage.setSeuser(seuser);
		pmessageDao.save(pmessage);
		
		json.put("content", "发送成功！");
		json.put("result", "success");
		showjsondata(json.toString());
		return null;
	}
	
	
	public String showMyPmessage() {
		
		if(rid == null) {
			getRequest().setAttribute("errorMsg", "用户不存在！");
			return "error";
		}
		
		XytUserInfo user = (XytUserInfo)xytUserInfoDao.findByID(XytUserInfo.class, rid);
		if(user == null) {
			getRequest().setAttribute("errorMsg", "用户不存在！");
			return "error";
		}
		
		getRequest().setAttribute("user", user);
		
		List<Pmessage> pmessageList = pmessageDao.findMyPmessage(rid);
		
		if(CollectionUtils.isNotEmpty(pmessageList)) {
			for(Pmessage pmessage : pmessageList) {
				if(pmessage.getSeuser().getRid().intValue() == rid.intValue()) {
					pmessage.setMy(true);
				}else {
					pmessage.setUnReadCount(pmessageDao.findUnReadCount(pmessage.getSeuser().getRid(), rid));
				}
			}
		}
		getRequest().setAttribute("pmessageList", pmessageList);
		
		return "showMyPmessage";
	}


	public Integer getSeuserId() {
		return seuserId;
	}


	public void setSeuserId(Integer seuserId) {
		this.seuserId = seuserId;
	}


	public Integer getReuserId() {
		return reuserId;
	}


	public void setReuserId(Integer reuserId) {
		this.reuserId = reuserId;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Integer getRid() {
		return rid;
	}


	public void setRid(Integer rid) {
		this.rid = rid;
	}
	


}
