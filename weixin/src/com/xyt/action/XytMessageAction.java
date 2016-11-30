package com.xyt.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.dxj.dao.DxjSuperStarDao;
import com.dxj.dao.DxjTravelRouteDao;
import com.dxj.po.DxjSuperStar;
import com.dxj.po.DxjTravelRoute;
import com.xxcb.util.BaseAction;
import com.xyt.dao.XytCollegeDao;
import com.xyt.dao.XytCourseDao;
import com.xyt.dao.XytMessageDao;
import com.xyt.dao.XytUserInfoDao;
import com.xyt.po.XytCollege;
import com.xyt.po.XytCourse;
import com.xyt.po.XytMessage;
import com.xyt.po.XytUserInfo;
import com.xyt.util.GsonUtil;

@Scope("prototype")
@Component("XytMessageAction")
public class XytMessageAction  extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7493412297901149055L;

	@Resource
	public XytMessageDao xytMessageDao;
	
	@Resource
	public XytUserInfoDao xytUserInfoDao;
	
	@Resource
	public XytCourseDao xytCourseDao;

	@Resource
	public XytCollegeDao xytCollegeDao;
	
	@Resource
	public DxjSuperStarDao dxjSuperStarDao;
	
	@Resource
	public DxjTravelRouteDao dxjTravelRouteDao;
	
	/**
	 * 根据courseId查找对应的评论
	 * @return
	 */
	/*public String course()
	{
		Integer courseId = Integer.parseInt((this.getRequest().getParameter("courseId")));
		List<XytMessage> listXytMessage = xytMessageDao.getXytMessageByCourse(courseId);
		this.getRequest().setAttribute("listXytMessage", listXytMessage);
		return "course";
	}*/
	
	/**
	 * 根据teacherId查找对应的评论
	 * @return
	 */
	public String getXytMessageByTeacherId()
	{
		Integer teacherId = Integer.parseInt(this.getRequest().getParameter("teacherId"));
		List<XytMessage> listXytMessage = xytMessageDao.getXytMessageByTeacher(teacherId);
		this.showjsondata(GsonUtil.toJson(listXytMessage));
		return null;
	}
	
	/**
	 * 保存校园留言信息
	 */
	public String saveCollegeMessage()
	{
		Integer userid = Integer.valueOf(this.getRequest().getParameter("userid"));
		Integer collegeId = Integer.valueOf(this.getRequest().getParameter("collegeId"));
		XytCollege xytCollege = xytCollegeDao.getXytCollegeByRid(collegeId).get(0);
		String content = this.getRequest().getParameter("content");
		if(("" != content) && (null != content)&& (0 != content.length()))
		{
			XytUserInfo xytUserInfo = xytUserInfoDao.getXytUserInfoByRid(userid).get(0);
			XytMessage xytMessage = new XytMessage();
			xytMessage.setAnonymity(false);
			xytMessage.setContent(content);
			xytMessage.setCreateDate(new Date());
			xytMessage.setShow(true);
			xytMessage.setXytTercherInfo(null);
			xytMessage.setXytUserInfo(xytUserInfo);
			xytMessage.setXytCollege(xytCollege);
			xytMessageDao.save(xytMessage);
		}
		JSONObject json = new JSONObject();
		json.put("result", "success");
		this.showjsondata(GsonUtil.toJson(json));
		return null;
	}
	
	/**
	 * 保存校园匿名留言信息
	 */
	public String saveCollegeMessageAnonymity()
	{
		Integer userid = Integer.valueOf(this.getRequest().getParameter("userid"));
		Integer collegeId = Integer.valueOf(this.getRequest().getParameter("collegeId"));
		XytCollege xytCollege = xytCollegeDao.getXytCollegeByRid(collegeId).get(0);
		String content = this.getRequest().getParameter("content");
		if(("" != content) && (null != content)&& (0 != content.length()))
		{
			XytUserInfo xytUserInfo = xytUserInfoDao.getXytUserInfoByRid(userid).get(0);
			XytMessage xytMessage = new XytMessage();
			xytMessage.setAnonymity(true);
			xytMessage.setContent(content);
			xytMessage.setCreateDate(new Date());
			xytMessage.setShow(true);
			xytMessage.setXytTercherInfo(null);
			xytMessage.setXytUserInfo(xytUserInfo);
			xytMessage.setXytCollege(xytCollege);
			xytMessageDao.save(xytMessage);
		}
		JSONObject json = new JSONObject();
		json.put("result", "success");
		this.showjsondata(GsonUtil.toJson(json));
		return null;
	}
	
	/**
	 * 保存网红留言信息
	 */
	public String saveDxjMessage()
	{
		XytMessage xytMessage = new XytMessage();
		Integer starId = Integer.parseInt(this.getRequest().getParameter("starId"));
		Integer userId = Integer.valueOf(this.getRequest().getParameter("userId"));
		//留言内容
		String content = this.getRequest().getParameter("content");
		
		if(("" != content) && (null != content)&& (0 != content.length()))
		{
			XytUserInfo xytUserInfo = (XytUserInfo) xytUserInfoDao.findByID(XytUserInfo.class, userId);
			DxjSuperStar star = (DxjSuperStar) dxjSuperStarDao.findByID(DxjSuperStar.class, starId);
			xytMessage.setAnonymity(false);
			xytMessage.setContent(content);
			xytMessage.setCreateDate(new Date());
			xytMessage.setShow(true);
			xytMessage.setXytUserInfo(xytUserInfo);
			xytMessage.setStar(star);
			xytMessageDao.save(xytMessage);
		}
		JSONObject json = new JSONObject();
		json.put("result", "success");
		this.showjsondata(GsonUtil.toJson(json));
		return null;
	}
	
	/**
	 * 保存网红匿名留言信息
	 */
	public String saveDxjMessageAnonymity()
	{
		XytMessage xytMessage = new XytMessage();
		Integer starId = Integer.parseInt(this.getRequest().getParameter("starId"));
		Integer userId = Integer.valueOf(this.getRequest().getParameter("userId"));
		//留言内容
		String content = this.getRequest().getParameter("content");
		
		if(("" != content) && (null != content)&& (0 != content.length()))
		{
			XytUserInfo xytUserInfo = (XytUserInfo) xytUserInfoDao.findByID(XytUserInfo.class, userId);
			DxjSuperStar star = (DxjSuperStar) dxjSuperStarDao.findByID(DxjSuperStar.class, starId);
			xytMessage.setAnonymity(true);
			xytMessage.setContent(content);
			xytMessage.setCreateDate(new Date());
			xytMessage.setShow(true);
			xytMessage.setXytUserInfo(xytUserInfo);
			xytMessage.setStar(star);
			xytMessageDao.save(xytMessage);
		}
		JSONObject json = new JSONObject();
		json.put("result", "success");
		this.showjsondata(GsonUtil.toJson(json));
		return null;
	}
	
	/**
	 * 保存旅游线路留言信息
	 */
	public String saveDxjRouteMessage()
	{
		XytMessage xytMessage = new XytMessage();
		Integer routeId = Integer.parseInt(this.getRequest().getParameter("routeId"));
		Integer userId = Integer.valueOf(this.getRequest().getParameter("userId"));
		//留言内容
		String content = this.getRequest().getParameter("content");
		
		if(("" != content) && (null != content)&& (0 != content.length()))
		{
			XytUserInfo xytUserInfo = (XytUserInfo) xytUserInfoDao.findByID(XytUserInfo.class, userId);
			DxjTravelRoute route = (DxjTravelRoute) dxjTravelRouteDao.findByID(DxjTravelRoute.class, routeId);
			xytMessage.setAnonymity(false);
			xytMessage.setContent(content);
			xytMessage.setCreateDate(new Date());
			xytMessage.setShow(true);
			xytMessage.setXytUserInfo(xytUserInfo);
			xytMessage.setRoute(route);
			xytMessageDao.save(xytMessage);
		}
		JSONObject json = new JSONObject();
		json.put("result", "success");
		this.showjsondata(GsonUtil.toJson(json));
		return null;
	}
	
	/**
	 * 保存旅游线路匿名留言信息
	 */
	public String saveDxjRouteMessageAnonymity()
	{
		XytMessage xytMessage = new XytMessage();
		Integer routeId = Integer.parseInt(this.getRequest().getParameter("routeId"));
		Integer userId = Integer.valueOf(this.getRequest().getParameter("userId"));
		//留言内容
		String content = this.getRequest().getParameter("content");
		
		if(("" != content) && (null != content)&& (0 != content.length()))
		{
			XytUserInfo xytUserInfo = (XytUserInfo) xytUserInfoDao.findByID(XytUserInfo.class, userId);
			DxjTravelRoute route = (DxjTravelRoute) dxjTravelRouteDao.findByID(DxjTravelRoute.class, routeId);
			xytMessage.setAnonymity(true);
			xytMessage.setContent(content);
			xytMessage.setCreateDate(new Date());
			xytMessage.setShow(true);
			xytMessage.setXytUserInfo(xytUserInfo);
			xytMessage.setRoute(route);
			xytMessageDao.save(xytMessage);
		}
		JSONObject json = new JSONObject();
		json.put("result", "success");
		this.showjsondata(GsonUtil.toJson(json));
		return null;
	}
	
	/**
	 *保存留言信息
	 */
	public String saveXytMessage()
	{
		XytMessage xytMessage = new XytMessage();
		Integer courseId = Integer.parseInt(this.getRequest().getParameter("courseId"));
		Integer userid = Integer.valueOf(this.getRequest().getParameter("userid"));
		//留言内容
		String content = this.getRequest().getParameter("content");
		
		if(("" != content) && (null != content)&& (0 != content.length()))
		{
			XytUserInfo xytUserInfo = xytUserInfoDao.getXytUserInfoByRid(userid).get(0);
			XytCourse xytCourse = xytCourseDao.getXytCourseByRid(courseId).get(0);
			xytMessage.setAnonymity(false);
			xytMessage.setContent(content);
			xytMessage.setCreateDate(new Date());
			xytMessage.setShow(true);
			xytMessage.setXytCourse(xytCourse);
			xytMessage.setXytTercherInfo(null);
			xytMessage.setXytUserInfo(xytUserInfo);
			xytMessageDao.save(xytMessage);
		}
		JSONObject json = new JSONObject();
		json.put("result", "success");
		this.showjsondata(GsonUtil.toJson(json));
		return null;
	}
	
	/**
	 * 保存匿名留言信息
	 */
	public String saveXytMessageAnonymity()
	{
		XytMessage xytMessage = new XytMessage();
		Integer courseId = Integer.parseInt(this.getRequest().getParameter("courseId"));
		Integer userid = Integer.valueOf(this.getRequest().getParameter("userid"));
		//留言内容
		String content = this.getRequest().getParameter("content");
		if(("" != content) && (null != content)&& (0 != content.length()))
		{
			XytUserInfo xytUserInfo = xytUserInfoDao.getXytUserInfoByRid(userid).get(0);
			XytCourse xytCourse = xytCourseDao.getXytCourseByRid(courseId).get(0);
			xytMessage.setAnonymity(true);
			xytMessage.setContent(content);
			xytMessage.setCreateDate(new Date());
			xytMessage.setShow(true);
			xytMessage.setXytCourse(xytCourse);
			xytMessage.setXytTercherInfo(null);
			xytMessage.setXytUserInfo(xytUserInfo);
			xytMessageDao.save(xytMessage);
		}
		JSONObject json = new JSONObject();
		json.put("result", "success");
		this.showjsondata(GsonUtil.toJson(json));
		return null;
	}
}
