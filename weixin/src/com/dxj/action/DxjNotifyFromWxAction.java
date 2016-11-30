package com.dxj.action;

import java.io.BufferedReader;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.marker.config.ConfigTest;
import org.marker.config.DxjConfig;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dxj.dao.DxjPayRecordDao;
import com.dxj.dao.DxjTravelRouteDao;
import com.dxj.dao.DxjTravelRouteOrderDao;
import com.dxj.po.DxjPayRecord;
import com.dxj.po.DxjTravelRoute;
import com.dxj.po.DxjTravelRouteOrder;
import com.xxcb.util.BaseAction;
import com.xyt.action.XytNotifyFromWxAction;
import com.xyt.dao.XytUserInfoDao;
import com.xyt.po.XytCourse;
import com.xyt.po.XytOrder;
import com.xyt.po.XytPayRecord;
import com.xyt.po.XytUserInfo;

@Scope("prototype")
@Component("DxjNotifyFromWxAction")
public class DxjNotifyFromWxAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3938522454018391877L;

	@Resource
	public DxjTravelRouteDao dxjTravelRouteDao;
	
	@Resource
	public XytUserInfoDao xytUserInfoDao;
	
	@Resource
	public DxjTravelRouteOrderDao dxjTravelRouteOrderDao;
	
	@Resource
	public DxjPayRecordDao dxjPayRecordDao;
	/**
	 * 个人订单支付成功接收通知消息
	 */
	public String OrderNotifyFromWx()
	{
		PropertyConfigurator.configure(DxjConfig.logPath);
		final Logger logger  =  Logger.getLogger(DxjNotifyFromWxAction.class );
		
        StringBuffer xmlStr = new StringBuffer();
        
        String weixinNotifyResult = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        
        try {
        	BufferedReader reader = this.getRequest().getReader();
        	String line = null;
            while ((line = reader.readLine()) != null) {
                xmlStr.append(line);
            }   
            
            //解析字符串
            String notifyResult = xmlStr.toString();
            if((notifyResult.contains("<result_code><![CDATA[SUCCESS]]></result_code>"))
            		&& (notifyResult.contains("<return_code><![CDATA[SUCCESS]]></return_code>")))
            {
            	//获取attach  为courseRid
            	String attach = this.getParameter(notifyResult, "attach");
                String openid = this.getParameter(notifyResult, "openid");
                Integer routeid = -1; 
                XytUserInfo userInfo = new XytUserInfo();
                DxjTravelRoute route = new DxjTravelRoute();
            	if(("" != attach) && (null != attach))
            	{
            		routeid = Integer.valueOf(attach);
            		route = dxjTravelRouteDao.getTravelRouteById(routeid).get(0);
            		logger.error("routeid  :"+routeid);
            	}
            	
            	if(("" != openid) && (null != openid))
            	{
            		userInfo = xytUserInfoDao.getXytUserInfoByOpenId(openid);
            		logger.error("openid  :"+openid);
            	}
            	
            	//判断订单是否已经生成，如果已生成则返回成功给微信服务器
            	if(( null != route) && (null != userInfo))
            	{
            		Integer totalFee = Integer.valueOf(notifyResult.substring(notifyResult.indexOf("<total_fee>")+"<total_fee>".length(), notifyResult.indexOf("</total_fee>")));
        			String transactionId = this.getParameter(notifyResult, "transaction_id");
        			String endTime = this.getParameter(notifyResult, "time_end");
        			String isSubscribe = this.getParameter(notifyResult, "is_subscribe");
            		List<DxjTravelRouteOrder> listDxjTravelRouteOrder = dxjTravelRouteOrderDao.getOrderByUserAndRoute(userInfo.getRid(), route.getId());
            		if(0 == listDxjTravelRouteOrder.size())
            		{
            			//不存在课程订单，新建课程订单并保存
            			DxjTravelRouteOrder routeOrder = new DxjTravelRouteOrder();
            			routeOrder.setFee(route.getFee());
            			routeOrder.setPaied(true);
            			routeOrder.setPayByScore(false);
            			routeOrder.setRoute(route);
            			routeOrder.setXytuserinfo(userInfo);
            			
            			dxjTravelRouteOrderDao.save(routeOrder);
            			//返回响应给微信服务器
            			logger.error("保存订单成功");
            			
            			//总人数+1
            			route.setAppliedNumber(route.getAppliedNumber() + 1);
            			dxjTravelRouteDao.update(route);
            			
            			//保存支付记录
            			DxjPayRecord dxjPayRecord = new DxjPayRecord();
            			dxjPayRecord.setEndTime(endTime);
            			dxjPayRecord.setIsSubscribe(isSubscribe);
            			dxjPayRecord.setOpenid(openid);
            			dxjPayRecord.setRouteOrder(routeOrder);
            			dxjPayRecord.setTotalFee(totalFee);
            			dxjPayRecord.setTransactionId(transactionId);
            			dxjPayRecord.setXytUserInfo(userInfo);
            			dxjPayRecordDao.save(dxjPayRecord);
            			
            			logger.error("DXJ保存支付记录成功     dxjPayRecord.id  = "+dxjPayRecord.getId());
            			
            			this.getResponse().getWriter().write(weixinNotifyResult);
            			return null;
            		}else{
            			DxjTravelRouteOrder routeOrder = listDxjTravelRouteOrder.get(0);
            			if(!routeOrder.isPaied())
            			{
            				routeOrder.setPaied(true);
            				dxjTravelRouteOrderDao.update(routeOrder);
            				//返回响应给微信服务器
            				logger.error("修改订单成功");
            				
                			//总人数+1
            				route.setAppliedNumber(route.getAppliedNumber() + 1);
            				dxjTravelRouteDao.update(route);
                			
            				//保存支付记录
                			DxjPayRecord dxjPayRecord = new DxjPayRecord();
                			dxjPayRecord.setEndTime(endTime);
                			dxjPayRecord.setIsSubscribe(isSubscribe);
                			dxjPayRecord.setOpenid(openid);
                			dxjPayRecord.setRouteOrder(routeOrder);
                			dxjPayRecord.setTotalFee(totalFee);
                			dxjPayRecord.setTransactionId(transactionId);
                			dxjPayRecord.setXytUserInfo(userInfo);
                			dxjPayRecordDao.save(dxjPayRecord);
                			
                			logger.error("DXJ保存支付记录成功     dxjPayRecord.id  = "+dxjPayRecord.getId());
                			
                			this.getResponse().getWriter().write(weixinNotifyResult);
                			return null;
            			}else
            			{
            				//返回响应给微信服务器
            				logger.error("返回消息");
            				this.getResponse().getWriter().write(weixinNotifyResult);
            				return null;
            			}
            		}
            	}
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public String getParameter(String notifyResult, String parameter)
	{
		String start = "<".concat(parameter).concat("><![CDATA[");
		String end = "]]></".concat(parameter).concat(">");
		return notifyResult.substring(notifyResult.indexOf(start)+start.length(), notifyResult.indexOf(end));
	}
}
