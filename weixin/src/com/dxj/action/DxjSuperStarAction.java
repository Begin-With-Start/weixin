package com.dxj.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.dxj.dao.DxjSuperStarDao;
import com.dxj.po.DxjSuperStar;
import com.xxcb.util.BaseAction;
import com.xyt.dao.XytLikePointForCourseDao;
import com.xyt.dao.XytMessageDao;
import com.xyt.dao.XytUserInfoDao;
import com.xyt.po.XytLikePointForCourse;
import com.xyt.po.XytMessage;
import com.xyt.po.XytUserInfo;
import com.xyt.util.GsonUtil;

/**
 * 校园网红控制类
 * @author Administrator
 *
 */
@Scope("prototype")
@Component("DxjSuperStarAction")
public class DxjSuperStarAction  extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4922453488363368901L;

	@Resource
	public DxjSuperStarDao dxjSuperStarDao;
	
	@Resource
	public XytMessageDao xytMessageDao;
	
	@Resource
	public XytLikePointForCourseDao xytLikePointForCourseDao;
	
	@Resource
	public XytUserInfoDao xytUserInfoDao;
	
	/**
	 * 获取所有的网红信息
	 * @return
	 */
	public String getAllSuperStar()
	{
		Integer userId = Integer.parseInt((this.getRequest().getParameter("user")));
		List<DxjSuperStar> listDxjSuperStar = dxjSuperStarDao.getAllSuperStar();
		System.out.println("userId:"+userId);
		this.getRequest().setAttribute("listDxjSuperStar", listDxjSuperStar);
		this.getRequest().setAttribute("userId", userId);
		return "totalsuperstar";
	}
	
	/**
	 * 获取单挑网红信息
	 * @return
	 */
	public String getSuperStar()
	{
		Integer userId = Integer.parseInt((this.getRequest().getParameter("userId")));
		Integer starId = Integer.parseInt((this.getRequest().getParameter("starId")));
		List<XytMessage> listXytMessage = xytMessageDao.getXytMessageByStar(starId);
		DxjSuperStar dxjSuperStar = (DxjSuperStar) dxjSuperStarDao.findByID(DxjSuperStar.class, starId);
		
		if(null != dxjSuperStar)
		{
			System.out.println("dxjSuperStar.getName():"+dxjSuperStar.getName());
		}
		this.getRequest().setAttribute("listXytMessage", listXytMessage);
		this.getRequest().setAttribute("star", dxjSuperStar);
		this.getRequest().setAttribute("starId", starId);
		this.getRequest().setAttribute("userId", userId);
		return "superstar";
	}
	
	/**
	 * 为网红送花
	 */
	/**
	 * 点赞
	 * @return
	 */
	public String addLikePoint()
	{
		Integer userId = Integer.valueOf(this.getRequest().getParameter("userId"));
		Integer starId = Integer.valueOf(this.getRequest().getParameter("starId"));
		DxjSuperStar star = (DxjSuperStar) dxjSuperStarDao.findByID(DxjSuperStar.class, starId);
		XytUserInfo xytUserInfo = (XytUserInfo) xytUserInfoDao.findByID(XytUserInfo.class, userId);
		List<XytLikePointForCourse> listLikePointForCourse = xytLikePointForCourseDao.getLikePointByStarIdAndUserRid(starId,userId);
		JSONObject likePointNumber = new JSONObject(); 
		if(0 != listLikePointForCourse.size())
		{
			likePointNumber.put("likePointNumber", -1);
			this.showjsondata(GsonUtil.toJson(likePointNumber));
		}else{
			star.setLikePointNumber(star.getLikePointNumber()+1);
			dxjSuperStarDao.update(star);
			
			likePointNumber.put("likePointNumber", star.getLikePointNumber());
			XytLikePointForCourse xytLikePointForCourse = new XytLikePointForCourse();
			
			xytLikePointForCourse.setDxjSuperStar(star);
			xytLikePointForCourse.setXytUserInfo(xytUserInfo);
			xytLikePointForCourse.setCreateDate(new Date());
			xytLikePointForCourseDao.save(xytLikePointForCourse);
			this.showjsondata(GsonUtil.toJson(likePointNumber));
		}
		return null;
	}
}
