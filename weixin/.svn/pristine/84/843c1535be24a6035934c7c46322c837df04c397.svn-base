package com.xyt.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.xxcb.util.BaseAction;
import com.xyt.dao.XytScoreInfoDao;
import com.xyt.dao.XytUserInfoDao;
import com.xyt.po.XytScoreInfo;

@Scope("prototype")
@Component("XytScoreInfoAction")
public class XytScoreInfoAction extends BaseAction{

	private static final long serialVersionUID = 4585336583640210250L;

	@Resource
	public XytUserInfoDao xytUserInfoDao;
	
	@Resource
	public XytScoreInfoDao xytScoreInfoDao;
	
	public String getScoreInfoRecord()
	{
		Integer userid = Integer.valueOf(this.getRequest().getParameter("userid"));
		List<XytScoreInfo> listXytScoreInfo = xytScoreInfoDao.getXytScoreInfoByUserId(userid);
		Integer totalScore = 0;
		for(int index = 0 ; index < listXytScoreInfo.size(); index++ )
		{
			XytScoreInfo xytScoreInfo = listXytScoreInfo.get(index);
			totalScore = totalScore + xytScoreInfo.getScoreOption();
		}
		this.getRequest().setAttribute("totalScore", totalScore);
		this.getRequest().setAttribute("listXytScoreInfo", listXytScoreInfo);
		this.getRequest().setAttribute("userid", userid);
		return "myscorerecord";
	}
}
