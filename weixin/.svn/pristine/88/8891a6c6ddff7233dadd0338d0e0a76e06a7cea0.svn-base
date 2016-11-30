package com.xyt.action;

import java.io.BufferedReader;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.marker.config.ConfigTest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.xxcb.util.BaseAction;
import com.xyt.dao.XytCourseDao;
import com.xyt.dao.XytOrderDao;
import com.xyt.dao.XytPayRecordDao;
import com.xyt.dao.XytUserInfoDao;
import com.xyt.po.XytCourse;
import com.xyt.po.XytOrder;
import com.xyt.po.XytPayRecord;
import com.xyt.po.XytUserInfo;

/**
 * 微信支付消息通知接收
 * @author Administrator
 *
 */
@Scope("prototype")
@Component("XytNotifyFromWxAction")
public class XytNotifyFromWxAction extends BaseAction{

	private static final long serialVersionUID = -8657074715214072689L;
	
	@Resource
	public XytUserInfoDao xytUserInfoDao;
	
	@Resource
	public XytCourseDao xytCourseDao;
	
	@Resource
	public XytOrderDao xytOrderDao;
	
	@Resource
	public XytPayRecordDao xytPayRecordDao;
	
	/**
	 * 团队订单支付成功接收通知消息
	 */
	public String GroupOrderNotifyFromWx()
	{
		PropertyConfigurator.configure(ConfigTest.logPath);
		final Logger logger  =  Logger.getLogger(XytNotifyFromWxAction.class );
		
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
            	String attach = this.getParameter(notifyResult, "attach");
            	if(("" != attach) && (null != attach))
            	{
            		Integer orderId = Integer.valueOf(attach);
            		XytOrder order = xytOrderDao.getXytOrderByRid(orderId).get(0);
            		XytCourse xytCourse = order.getXytCourse();
            		
            		if(order.isPaied())
            		{
            			logger.error("订单状态已修改");
            			this.getResponse().getWriter().write(weixinNotifyResult);
            			return null;
            		}else
            		{
            			xytOrderDao.changeOrderPaindStatus(orderId);
            			logger.error("修改订单状态成功");
            			
            			//保存支付记录
            			String openid = this.getParameter(notifyResult, "openid");
            			
            			Integer totalFee = Integer.valueOf(notifyResult.substring(notifyResult.indexOf("<total_fee>")+"<total_fee>".length(), notifyResult.indexOf("</total_fee>")));
            			
            			String transactionId = this.getParameter(notifyResult, "transaction_id");
            			String endTime = this.getParameter(notifyResult, "time_end");
            			String isSubscribe = this.getParameter(notifyResult, "is_subscribe");
            			
            			//对应课程的已报名人数增加
            			//课程付费报名人数+1
            			xytCourse.setTotalAppliedNumber(xytCourse.getTotalAppliedNumber() + 1);
            			//总人数+1
            			xytCourse.setAppliedStudentsNumber(xytCourse.getAppliedStudentsNumber() + 1);
            			xytCourseDao.update(xytCourse);
            			
            			XytUserInfo xytUser = xytUserInfoDao.getXytUserInfoByOpenId(openid);
            			XytPayRecord xytPayRecord = new XytPayRecord();
            			xytPayRecord.setEndTime(endTime);
            			xytPayRecord.setOpenid(openid);
            			xytPayRecord.setSubscribe(isSubscribe);
            			xytPayRecord.setTotalFee(totalFee);
            			xytPayRecord.setTransactionId(transactionId);
            			xytPayRecord.setXytOrder(order);
            			xytPayRecord.setXytUserInfo(xytUser);
            			xytPayRecordDao.save(xytPayRecord);
            			logger.error("保存支付记录成功     xytPayRecord.rid  ="+xytPayRecord.getRid());
            			
            			this.getResponse().getWriter().write(weixinNotifyResult);
            			return null;
            		}
            	}
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 个人订单支付成功接收通知消息
	 */
	public String PersonalOrderNotifyFromWx()
	{
		PropertyConfigurator.configure(ConfigTest.logPath);
		final Logger logger  =  Logger.getLogger(XytNotifyFromWxAction.class );
		
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
                Integer courseRid = -1; 
                XytUserInfo userInfo = new XytUserInfo();
                XytCourse course = new XytCourse();
            	if(("" != attach) && (null != attach))
            	{
            		courseRid = Integer.valueOf(attach);
            		course = xytCourseDao.getXytCourseByRid(courseRid).get(0);
            		logger.error("courseRid  :"+courseRid);
            	}
            	
            	if(("" != openid) && (null != openid))
            	{
            		userInfo = xytUserInfoDao.getXytUserInfoByOpenId(openid);
            		logger.error("openid  :"+openid);
            	}
            	
            	//判断订单是否已经生成，如果已生成则返回成功给微信服务器
            	if(( null != course) && (null != userInfo))
            	{
            		Integer totalFee = Integer.valueOf(notifyResult.substring(notifyResult.indexOf("<total_fee>")+"<total_fee>".length(), notifyResult.indexOf("</total_fee>")));
        			String transactionId = this.getParameter(notifyResult, "transaction_id");
        			String endTime = this.getParameter(notifyResult, "time_end");
        			String isSubscribe = this.getParameter(notifyResult, "is_subscribe");
            		List<XytOrder> listXytOrder = xytOrderDao.getOrderByUserAndCourse(userInfo.getRid(), courseRid);
            		if(0 == listXytOrder.size())
            		{
            			//不存在课程订单，新建课程订单并保存
            			XytOrder xytOrder = new XytOrder();
            			xytOrder.setXytCourse(course);
            			xytOrder.setXytuserinfo(userInfo);
            			xytOrder.setCreateDate(new Date());
            			xytOrder.setFee(course.getFee());
            			xytOrder.setPaied(true);
            			xytOrder.setXytTercherInfo(course.getTeacher());
            			xytOrder.setAudition(false);
            			xytOrderDao.save(xytOrder);
            			//返回响应给微信服务器
            			logger.error("保存订单成功");
            			
            			//对应课程的已报名人数增加
            			//课程付费报名人数+1
            			course.setTotalAppliedNumber(course.getTotalAppliedNumber() + 1);
            			//总人数+1
            			course.setAppliedStudentsNumber(course.getAppliedStudentsNumber() + 1);
            			xytCourseDao.update(course);
            			
            			//保存支付记录
            			XytPayRecord xytPayRecord = new XytPayRecord();
            			xytPayRecord.setEndTime(endTime);
            			xytPayRecord.setOpenid(openid);
            			xytPayRecord.setSubscribe(isSubscribe);
            			xytPayRecord.setTotalFee(totalFee);
            			xytPayRecord.setTransactionId(transactionId);
            			xytPayRecord.setXytOrder(xytOrder);
            			xytPayRecord.setXytUserInfo(userInfo);
            			xytPayRecordDao.save(xytPayRecord);
            			logger.error("保存支付记录成功     xytPayRecord.rid  ="+xytPayRecord.getRid());
            			
            			this.getResponse().getWriter().write(weixinNotifyResult);
            			return null;
            		}else{
            			XytOrder xytOrder = listXytOrder.get(0);
            			if(!xytOrder.isPaied())
            			{
            				if(xytOrder.isAudition())
                			{
                				xytOrder.setAudition(false);
                			}
            				
            				xytOrder.setPaied(true);
            				xytOrderDao.update(xytOrder);
            				//返回响应给微信服务器
            				logger.error("修改订单成功");
            				
            				//课程付费报名人数+1
                			course.setTotalAppliedNumber(course.getTotalAppliedNumber() + 1);
                			//总人数+1
                			course.setAppliedStudentsNumber(course.getAppliedStudentsNumber() + 1);
                			xytCourseDao.update(course);
                			
            				//保存支付记录
                			XytPayRecord xytPayRecord = new XytPayRecord();
                			xytPayRecord.setEndTime(endTime);
                			xytPayRecord.setOpenid(openid);
                			xytPayRecord.setSubscribe(isSubscribe);
                			xytPayRecord.setTotalFee(totalFee);
                			xytPayRecord.setTransactionId(transactionId);
                			xytPayRecord.setXytOrder(xytOrder);
                			xytPayRecord.setXytUserInfo(userInfo);
                			xytPayRecordDao.save(xytPayRecord);
                			logger.error("保存支付记录成功     xytPayRecord.rid  ="+xytPayRecord.getRid());
            				
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
