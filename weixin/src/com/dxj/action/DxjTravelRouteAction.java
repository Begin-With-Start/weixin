package com.dxj.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dxj.dao.DxjTravelRouteDao;
import com.dxj.dao.DxjTravelRouteOrderDao;
import com.dxj.po.DxjTravelRoute;
import com.dxj.po.DxjTravelRouteOrder;
import com.xxcb.util.BaseAction;
import com.xyt.dao.XytMessageDao;
import com.xyt.po.CourseNumberRank;
import com.xyt.po.XytMessage;

/**
 * 旅游线路控制类
 * @author Administrator
 *
 */
@Scope("prototype")
@Component("DxjTravelRouteAction")
public class DxjTravelRouteAction  extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4203952672271086537L;
	
	@Resource
	public DxjTravelRouteDao dxjTravelRouteDao;
	
	@Resource
	public DxjTravelRouteOrderDao dxjTravelRouteOrderDao;
	
	@Resource
	public	XytMessageDao xytMessageDao;
	/**
	 * 保存旅游线路
	 */
	public String saveTravelRoute()
	{
		DxjTravelRoute travelRoute = new DxjTravelRoute();
		travelRoute.setAppliedNumber(0);
		travelRoute.setDiscount(false);
		travelRoute.setDiscountNumber(null);
		travelRoute.setFee(new BigDecimal(0.01));
		travelRoute.setIntroduction("一天行程，吃喝玩乐行游购全地图攻略团，教你未来四年如何做一名地地道道的“长沙老口子”");
		travelRoute.setLikePointNumber(0);
		travelRoute.setPayByScore(false);
		travelRoute.setPictureUrl("pages\\xytpages\\image\\dxj\\travelroute\\saomangxianlu1.png");
		travelRoute.setScoreApplied(null);
		travelRoute.setStartDate(new Date());
		travelRoute.setTravelRouteName("新生“扫盲A计划”之1号路线“经典篇”");
		travelRoute.setWeixinUrl("http://mp.weixin.qq.com/s?__biz=MzIxNjU3MTE0MA==&tempkey=X0IFnpdMRbVVrDGsOfaeycCaYhN4noSj4TI5n0JdKaINKB4X%2FyHZhC0hQqlUjDPYek2TvCPyquKl4c1rXncv6BG1ui6O5Bb4nZlv54x5KRqu8sz0MVvTLvrNPVoIVjyC4JK6R%2BWoB7nPSueilGR2AQ%3D%3D&#rd");
		dxjTravelRouteDao.save(travelRoute);
		System.out.println("保存成功");
		return null;
	}
	
	
	/**
	 * 获取所有的旅游线路
	 * @return
	 */
	public String getAlltravelRoute()
	{
		Integer userId = Integer.parseInt((this.getRequest().getParameter("user")));
		List<DxjTravelRoute> listDxjTravelRoute = dxjTravelRouteDao.getAllTravelRoute();
		
		int totalNumber = 0;
		List<DxjTravelRoute> listTravelRouteOrder = new ArrayList<DxjTravelRoute>();
		if(5 < listDxjTravelRoute.size())
		{
			for(int index = 0 ; index < 5 ; index ++)
			{
				listTravelRouteOrder.add(listTravelRouteOrder.get(index));
				totalNumber = totalNumber + listTravelRouteOrder.get(index).getAppliedNumber();
			}
		}else
		{
			for(int index = 0 ; index < listDxjTravelRoute.size() ; index ++)
			{
				listTravelRouteOrder.add(listDxjTravelRoute.get(index));
				totalNumber = totalNumber + listDxjTravelRoute.get(index).getAppliedNumber();
			}
		}
		if(0 < totalNumber)
		{
			List<CourseNumberRank> listCourseNumberRank = new ArrayList<CourseNumberRank>();
			for(int index = 0 ; index < listTravelRouteOrder.size(); index ++)
			{
				DxjTravelRoute dxjTravelRoute = listTravelRouteOrder.get(index);
				if(0 < dxjTravelRoute.getAppliedNumber())
				{
					CourseNumberRank courseNumberRank = new CourseNumberRank();
					
					courseNumberRank.setCourseName(dxjTravelRoute.getTravelRouteName());
					float appliedNumber = (float)dxjTravelRoute.getAppliedNumber();
					String percentage = String.valueOf((appliedNumber/((float)totalNumber))*100);
					if(5<percentage.length())
					{
						percentage = percentage.substring(0, 5);
					}
					courseNumberRank.setPercentage(Float.valueOf(percentage));
					if(0 == index)
					{
						courseNumberRank.setMax(true);
					}
					listCourseNumberRank.add(courseNumberRank);
				}
			}
			this.getRequest().setAttribute("isShow", true);
			this.getRequest().setAttribute("listCourseNumberRank", listCourseNumberRank);
		}else{
			this.getRequest().setAttribute("isShow", false);
		}
		System.out.println(listDxjTravelRoute.size());
		this.getRequest().setAttribute("listDxjTravelRoute", listDxjTravelRoute);
		this.getRequest().setAttribute("userId", userId);
		return "totaltravelroute";
	}

	/**
	 * 获取所有的旅游线路
	 * @return
	 */
	public String shooseTravelRoute()
	{
		Integer userId = Integer.parseInt((this.getRequest().getParameter("userId")));
		List<DxjTravelRoute> listDxjTravelRoute = dxjTravelRouteDao.getAllTravelRoute();
		this.getRequest().setAttribute("listDxjTravelRoute", listDxjTravelRoute);
		this.getRequest().setAttribute("userId", userId);
		return "choosetravelroute";
	}
	
	/**
	 * 获取所有的旅游线路
	 * @return
	 */
	public String shooseGroup()
	{
		Integer userId = Integer.parseInt((this.getRequest().getParameter("userId")));
		List<DxjTravelRoute> listDxjTravelRoute = dxjTravelRouteDao.getAllGroup();
		this.getRequest().setAttribute("listDxjTravelRoute", listDxjTravelRoute);
		this.getRequest().setAttribute("userId", userId);
		return "choosetravelroute";
	}
	
	/**
	 * 跳转到单挑的旅游线路
	 * @return
	 */
	public String getTravelRoute()
	{
		Integer userId = Integer.parseInt((this.getRequest().getParameter("userId")));
		Integer routeId = Integer.parseInt((this.getRequest().getParameter("routeId")));
		DxjTravelRoute dxjTravelRoute = (DxjTravelRoute) dxjTravelRouteDao.findByID(DxjTravelRoute.class, Integer.valueOf(routeId));
		List<DxjTravelRouteOrder> listDxjTravelRouteOrder = dxjTravelRouteOrderDao.getOrderByRoute(routeId);
		List<XytMessage> listXytMessage = xytMessageDao.getXytMessageByrouteId(routeId);
		
		this.getRequest().setAttribute("listXytMessage", listXytMessage);
		this.getRequest().setAttribute("dxjTravelRoute", dxjTravelRoute);
		this.getRequest().setAttribute("userId", userId);
		this.getRequest().setAttribute("routeId", routeId);
		this.getRequest().setAttribute("listDxjTravelRouteOrder", listDxjTravelRouteOrder);
		return "travelroute";
	}
}
