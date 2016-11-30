package com.xyt.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xxcb.util.DateUtil;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.xxcb.util.BaseAction;
import com.xyt.dao.XytCourseDao;
import com.xyt.dao.XytGroupOrderDao;
import com.xyt.dao.XytOrderDao;
import com.xyt.dao.XytTercherInfoDao;
import com.xyt.dao.XytUserInGroupDao;
import com.xyt.dao.XytUserInfoDao;
import com.xyt.po.XytCourse;
import com.xyt.po.XytGroupOrder;
import com.xyt.po.XytGroupOrder.expectTimeEnum;
import com.xyt.po.XytGroupOrder.groupIntentionEnum;
import com.xyt.po.XytOrder;
import com.xyt.po.XytTercherInfo;
import com.xyt.po.XytUserInGroup;
import com.xyt.po.XytUserInfo;
import com.xyt.util.GsonUtil;

@Scope("prototype")
@Component("XytGroupOrderAction")
public class XytGroupOrderAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7382455192899914574L;
	
	@Resource
	public XytGroupOrderDao xytGroupOrderDao;
	
	@Resource
	public XytUserInGroupDao xytUserInGroupDao;
	
	@Resource
	public XytUserInfoDao xytUserInfoDao;
	
	@Resource
	public XytOrderDao xytOrderDao;
	
	@Resource
	public XytCourseDao xytCourseDao;
	
	@Resource
	public XytTercherInfoDao xytTercherInfoDao;
	
	public String getXytGroupOrderInProceed()
	{
		Integer userId = Integer.valueOf(this.getRequest().getParameter("user"));
		List<XytGroupOrder> listXytGroupOrderInProceed = xytGroupOrderDao.getXytGroupOrderInProceed();
		boolean hasGroup = false;
		if( 0 < listXytGroupOrderInProceed.size())
		{
			hasGroup = true;
		}
		this.getRequest().setAttribute("listXytGroupOrderInProceed", listXytGroupOrderInProceed);
		this.getRequest().setAttribute("hasGroup", hasGroup);
		this.getRequest().setAttribute("userId", userId);
		return "groupInProceed";
	}
	
	public String getGroupOrderByGroupId()
	{
		Integer groupId = Integer.valueOf(this.getRequest().getParameter("groupId"));
		Integer userId = Integer.valueOf(this.getRequest().getParameter("userId"));
		XytGroupOrder groupOrder = xytGroupOrderDao.getGroupOrderByGroupId(groupId).get(0);
		List<XytUserInGroup> listXytUserInGroup= xytUserInGroupDao.getUserInGroupByGroupId(groupId);
		System.out.println("listXytUserInGroup.size()  :"+listXytUserInGroup.size());
		this.getRequest().setAttribute("groupOrder", groupOrder);
		this.getRequest().setAttribute("listXytUserInGroup", listXytUserInGroup);
		this.getRequest().setAttribute("userId", userId);
		return "group";
	}
	
	/**
	 * 加入团队
	 */
	public String joinGroup()
	{
		Integer groupId = Integer.valueOf(this.getRequest().getParameter("groupId"));
		Integer userId = Integer.valueOf(this.getRequest().getParameter("userId"));
		
		XytGroupOrder groupOrder = xytGroupOrderDao.getGroupOrderByGroupId(groupId).get(0);
		
		//加入操作返回结果
		String result = new String();
		JSONObject jsonResult = new JSONObject(); 
		//判断是否已经在该群组中
		List<XytUserInGroup> listXytUserInGroup = xytUserInGroupDao.getUserInGroupByUserIdAndGroupId(userId, groupId);
		
		//判断是否已经订购了该课程
		List<XytOrder > listOrder = xytOrderDao.getAppliedOrderByUserAndCourse(userId, groupOrder.getXytCourse().getRid());
		if(0 < listOrder.size()){
			result = "您已订购了该课程！";
			jsonResult.put("result", result);
			this.showjsondata(GsonUtil.toJson(jsonResult));
			return null;
		}
		
		if(0 < listXytUserInGroup.size())
		{
			XytUserInGroup xytUserInGroup = listXytUserInGroup.get(0);
			if(xytUserInGroup.isRemove())
			{
				result = "已加入该群组，不可重复加入";
				jsonResult.put("result", result);
				this.showjsondata(GsonUtil.toJson(jsonResult));
				return null;
			}else{
				//判断群组是否已满员
				List<XytGroupOrder> listXytGroupOrder = xytGroupOrderDao.getGroupOrderByGroupId(groupId);
				if(0 < listXytGroupOrder.size())
				{
					XytGroupOrder xytGroupOrder = listXytGroupOrder.get(0);
					if(xytGroupOrder.getMemberNumber() >= xytGroupOrder.getTotalMemberNumber())
					{
						result = "对不起，该群组已满员！";
						jsonResult.put("result", result);
						this.showjsondata(GsonUtil.toJson(jsonResult));
						return null;
					}else{
						xytUserInGroup.setRemove(true);
						xytGroupOrderDao.groupOrderAddMemberNumber(groupId);
						result = "加入群组成功！";
						jsonResult.put("result", result);
						this.showjsondata(GsonUtil.toJson(jsonResult));
						return null;
					}
				}
			}
		}else{
			//判断群组是否已满员
			List<XytGroupOrder> listXytGroupOrder = xytGroupOrderDao.getGroupOrderByGroupId(groupId);
			if(0 < listXytGroupOrder.size())
			{
				XytGroupOrder xytGroupOrder = listXytGroupOrder.get(0);
				if(xytGroupOrder.getMemberNumber() >= xytGroupOrder.getTotalMemberNumber())
				{
					result = "对不起，该群组已满员！";
					jsonResult.put("result", result);
					this.showjsondata(GsonUtil.toJson(jsonResult));
					return null;
				}else{
					XytUserInfo xytUserInfo = xytUserInfoDao.getXytUserInfoByRid(userId).get(0);
					
					//保存一条订单信息
					XytOrder xytOrder = new XytOrder();
					xytOrder.setCreateDate(new Date());
					xytOrder.setAudition(false);
					xytOrder.setFee(xytGroupOrder.getGroupFee());
					xytOrder.setPaied(false);
					xytOrder.setXytCourse(xytGroupOrder.getXytCourse());
					xytOrder.setXytGroupOrder(xytGroupOrder);
					xytOrder.setXytTercherInfo(xytGroupOrder.getXytTercherInfo());
					xytOrder.setXytuserinfo(xytUserInfo);
					
					xytOrderDao.save(xytOrder);
					
					//添加一条用户加入群组记录
					XytUserInGroup xytUserInGroup = new XytUserInGroup();
					xytUserInGroup.setGroup(xytGroupOrder);
					xytUserInGroup.setXytUserInfo(xytUserInfo);
					xytUserInGroup.setJoinDate(new Date());
					xytUserInGroup.setXytOrder(xytOrder);
					xytUserInGroup.setRemove(true);
					xytUserInGroupDao.save(xytUserInGroup);
					
					//群组人数加1
					xytGroupOrderDao.groupOrderAddMemberNumber(groupId);
					
					result = "加入群组成功！";
					jsonResult.put("result", result);
					this.showjsondata(GsonUtil.toJson(jsonResult));
					return null;
				}
			}
		}
		return null;
	}
	
	
	
	/**
	 * 根据群组信息查找所有的群组成员
	 */
	public String getUserInGroupByGroupId()
	{
		Integer groupId = Integer.valueOf(this.getRequest().getParameter("groupId"));
		List<XytUserInGroup> listXytUserInGroup = xytUserInGroupDao.getUserInGroupByGroupId(groupId);
		this.showjsondata(GsonUtil.toJson(listXytUserInGroup));
		return null;
	}
	
	/**
	 * 根据用户信息查找所有的群组
	 */
	public String getUserInGroupByUserId()
	{
		Integer userId = Integer.valueOf(this.getRequest().getParameter("userid"));
		List<XytUserInGroup> listXytUserInGroup = xytUserInGroupDao.getUserInGroupByUserId(userId);
		boolean hasGroup = false;
		if(0 < listXytUserInGroup.size())
		{
			hasGroup = true;
		}
		this.getRequest().setAttribute("listXytUserInGroup", listXytUserInGroup);
		this.getRequest().setAttribute("hasGroup", hasGroup);
		return "mygroup";
	}
	
	/**
	 * 跳转到组建团队页面
	 */
	public String createGroup()
	{
		//获取所有课程信息
		List<XytCourse> listXytCourse = xytCourseDao.getAllCourse();
		List<XytTercherInfo> listXytTercherInfo = xytTercherInfoDao.getAllXytTercherInfo();
		this.getRequest().setAttribute("listXytCourse", listXytCourse);
		this.getRequest().setAttribute("listXytTercherInfo", listXytTercherInfo);
		return "createGroup";
	}
	
	/**
	 * 根据rid查找对应的群组
	 */
	public String getUserInGroupByRid()
	{
		Integer rid = Integer.valueOf(this.getRequest().getParameter("groupid"));
		List<XytUserInGroup> listXytUserInGroup = xytUserInGroupDao.getUserInGroupByrid(rid);
		this.showjsondata(GsonUtil.toJson(listXytUserInGroup));
		return null;
	}
	
	/**
	 * 创建团队
	 */
	public String createGroupOrder()
	{
		String groupName = this.getRequest().getParameter("groupName");
		Integer courseId = Integer.valueOf(this.getRequest().getParameter("courseId"));
		String expectAddress = this.getRequest().getParameter("expectAddress");
		Integer teacherId = Integer.valueOf(this.getRequest().getParameter("teacherId"));
		Integer expectTime = Integer.valueOf(this.getRequest().getParameter("expectTime"));
		Integer groupIntention = Integer.valueOf(this.getRequest().getParameter("groupIntention"));
		Integer totalMemberNumber = Integer.valueOf(this.getRequest().getParameter("totalMemberNumber"));
		
		if(totalMemberNumber.equals(-1))
		{
			totalMemberNumber = 12;
		}
		
		Integer userId = Integer.valueOf(this.getRequest().getParameter("userId"));
		
		JSONObject json = new JSONObject();
		
		//判断是否已经订购了该课程
		List<XytOrder> listOrder = xytOrderDao.getAppliedOrderByUserAndCourse(userId, courseId);
		if(0 < listOrder.size()){
			json.put("groupId", -1);
			this.showjsondata(GsonUtil.toJson(json));
			return null;
		}
		
		
		XytCourse xytCourse = xytCourseDao.getXytCourseByRid(courseId).get(0);
		XytUserInfo colonel = xytUserInfoDao.getXytUserInfoByRid(userId).get(0);
		
		XytGroupOrder xytGroupOrder = new XytGroupOrder();
		if(!groupName.equals("emptyName"))
		{
			xytGroupOrder.setGroupName(groupName);
		}
		
		if(!expectAddress.equals("emptyName"))
		{
			xytGroupOrder.setExpectAddress(expectAddress);
		}
		
		//按时间产生团队编码
		Date date = new Date();
		String code = DateUtil.dateToStr(date,DateUtil.DATE_TIME_FORMAT_YYYYMMDDHHMISS);
		xytGroupOrder.setGroupCode(code);
		xytGroupOrder.setTotalMemberNumber(totalMemberNumber);
		xytGroupOrder.setMemberNumber(1);
		xytGroupOrder.setOriginalFee(xytCourse.getFee());
		//团购价折扣
		xytGroupOrder.setGroupDiscount(new BigDecimal(0.6));
		xytGroupOrder.setGroupFee(xytCourse.getFee().multiply(xytGroupOrder.getGroupDiscount()));
		xytGroupOrder.setXytCourse(xytCourse);
		xytGroupOrder.setColonel(colonel);
		xytGroupOrder.setCreateDate(date);
		xytGroupOrder.setRemove(true);
		XytTercherInfo xytTercherInfo = null;
		if(-1 != teacherId)
		{
			xytTercherInfo = xytTercherInfoDao.getXytTercherInfoByRid(teacherId).get(0);
			xytGroupOrder.setXytTercherInfo(xytTercherInfo);
		}else{
			xytGroupOrder.setXytTercherInfo(null);
		}
		if(-1 != expectTime)
		{
			if(0 == expectTime)
			{
				xytGroupOrder.setExpectTime(expectTimeEnum.周一至周五中午班);
			}else if(1 == expectTime){
				xytGroupOrder.setExpectTime(expectTimeEnum.周一至周五晚上班);
			}else if(2 == expectTime){
				xytGroupOrder.setExpectTime(expectTimeEnum.周末班);
			}
		}else{
			xytGroupOrder.setExpectTime(null);
		}
		
		if(-1 != groupIntention)
		{
			if(0 == groupIntention)
			{
				xytGroupOrder.setGroupIntention(groupIntentionEnum.纯个人);
			}else if(1 == expectTime){
				xytGroupOrder.setGroupIntention(groupIntentionEnum.携同事及朋友组团);
			}else if(2 == expectTime){
				xytGroupOrder.setGroupIntention(groupIntentionEnum.单独组团);
			}
		}else{
			xytGroupOrder.setGroupIntention(null);
		}
			
		xytGroupOrder.setProceed(true);
		
		xytGroupOrderDao.save(xytGroupOrder);
		
		//添加一条订单记录
		XytOrder xytOrder = new XytOrder();
		xytOrder.setAudition(false);
		xytOrder.setCreateDate(date);
		//订单费用等于团队订单的费用
		xytOrder.setFee(xytGroupOrder.getGroupFee());
		xytOrder.setPaied(false);
		xytOrder.setXytCourse(xytCourse);
		xytOrder.setXytGroupOrder(xytGroupOrder);
		xytOrder.setXytTercherInfo(xytTercherInfo);
		xytOrder.setXytuserinfo(colonel);
				
		xytOrderDao.save(xytOrder);
		
		//添加一条加入群组记录
		XytUserInGroup xytUserInGroup = new XytUserInGroup();
		xytUserInGroup.setGroup(xytGroupOrder);
		xytUserInGroup.setJoinDate(date);
		xytUserInGroup.setXytUserInfo(colonel);
		xytUserInGroup.setXytOrder(xytOrder);
		xytUserInGroup.setRemove(true);
		xytUserInGroupDao.save(xytUserInGroup);
		
		json.put("groupId", xytGroupOrder.getRid());
		this.showjsondata(GsonUtil.toJson(json));
		
		return null;
	}
	
	/**
	 * 退出群组
	 */
	public String quitGroup()
	{
		Integer userid = Integer.valueOf(this.getRequest().getParameter("userid"));
		//群组记录id
		Integer uerInGroupid = Integer.valueOf(this.getRequest().getParameter("groupid"));
		
		List<XytGroupOrder> listXytGroupOrder = xytGroupOrderDao.getGroupOrderByGroupId(uerInGroupid);
		
		List<XytUserInGroup> listXytUserInGroup = xytUserInGroupDao.getUserInGroupByUserIdAndGroupId(userid, uerInGroupid);
		
		List<XytUserInGroup>  ListTotalxytUserInGroup = xytUserInGroupDao.getUserInGroupByGroupId(uerInGroupid);
		
		XytGroupOrder xytGroupOrder = new XytGroupOrder();
		XytUserInGroup xytUserInGroup = new XytUserInGroup();
		if(0 < listXytGroupOrder.size()) 
		{
			xytGroupOrder = listXytGroupOrder.get(0);
		}
		
		if(0 < listXytUserInGroup.size()) 
		{
			xytUserInGroup = listXytUserInGroup.get(0);
		}
		
		XytOrder xytOrder = xytUserInGroup.getXytOrder();
		
		//删除用户加入群组记录  XytUserInGroup
		xytUserInGroup.setRemove(false);
		
		//删除订单信息（订单信息保留）
		//xytOrderDao.delete(xytOrder);
		
		System.out.println("xytGroupOrder.getMemberNumber()  :"+xytGroupOrder.getMemberNumber());
		
		if(1 == xytGroupOrder.getMemberNumber())
		{
			//如果团队中只有一个人  那么删除这个团队订单
			xytGroupOrder.setRemove(false);
		}else{
			//如果退团的是团长，则推选下一个人作为团长
			System.out.println("xytGroupOrder.getColonel().getRid())  :"+xytGroupOrder.getColonel().getRid());
			System.out.println("ListTotalxytUserInGroup.size()  :"+ListTotalxytUserInGroup.size());
			if(userid.equals(xytGroupOrder.getColonel().getRid()))
			{
				for(int index = 0 ; index < ListTotalxytUserInGroup.size(); index++)
				{
					XytUserInGroup xytUserInG = ListTotalxytUserInGroup.get(index);
					System.out.println("xytUserInG.getXytUserInfo().getRid())  :"+xytUserInG.getXytUserInfo().getRid());
					if(!xytUserInG.getXytUserInfo().getRid().equals(xytGroupOrder.getColonel().getRid()))
					{
						xytGroupOrder.setColonel(xytUserInG.getXytUserInfo());
						System.out.println("设置团长成功");
						break;
					}
				}
			}
			//团队人数减1
			
			System.out.println("团队人数减1");
			xytGroupOrder.setMemberNumber(xytGroupOrder.getMemberNumber() - 1);
		}
		xytUserInGroupDao.update(xytUserInGroup);
		xytGroupOrderDao.update(xytGroupOrder);
		String result = "退出群组成功！";
		JSONObject jsonResult = new JSONObject(); 
		jsonResult.put("result", result);
		this.showjsondata(GsonUtil.toJson(jsonResult));
		return null;
	}
}
