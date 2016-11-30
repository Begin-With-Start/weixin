package com.dxj.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.marker.config.ConfigTest;
import org.marker.config.DxjConfig;
import org.marker.utils.MySecurity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dxj.dao.DxjTravelRouteDao;
import com.dxj.dao.DxjTravelRouteOrderDao;
import com.dxj.po.DxjTravelRoute;
import com.dxj.po.DxjTravelRouteOrder;
import com.dxj.util.DxjPayUtil;
import com.xxcb.util.BaseAction;
import com.xxcb.weixin.SignUtilTest;
import com.xyt.action.XytOrderAction;
import com.xyt.dao.XytUserInfoDao;
import com.xyt.po.BrandWCPayParameterVo;
import com.xyt.po.XytUserInfo;
import com.xyt.util.GsonUtil;
import com.xyt.util.IpAddressUtils;

@Scope("prototype")
@Component("DxjTravelRouteOrderAction")
public class DxjTravelRouteOrderAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1490798719646903660L;

	@Resource
	public DxjTravelRouteOrderDao dxjTravelRouteOrderDao;
	
	@Resource
	public XytUserInfoDao xytUserInfoDao;
	
	@Resource
	public DxjTravelRouteDao dxjTravelRouteDao;
	/**
	 * 根据userRid、courseRid,判断是否已报名该课程
	 */
	public String getOrderByUserAndCourse()
	{
		Integer routeId = Integer.valueOf(this.getRequest().getParameter("routeId"));
		Integer userid = Integer.valueOf(this.getRequest().getParameter("userid"));
		List<DxjTravelRouteOrder > listOrder = dxjTravelRouteOrderDao.getOrderByUserAndRoute(userid, routeId);
		this.showjsondata(GsonUtil.toJson(listOrder));
		return null;
	}
	
	/**
	 * 订购支付
	 */
	public String travelRouteOrderPay()
	{
		//打印日志
		PropertyConfigurator.configure(ConfigTest.logPath);
		final Logger logger  =  Logger.getLogger(XytOrderAction.class );
		
		Integer userid = Integer.valueOf(this.getRequest().getParameter("userid"));
		Integer routeid = Integer.valueOf(this.getRequest().getParameter("routeId"));
		String addrip = IpAddressUtils.getIpAddr(this.getRequest());
		logger.error("new  addrip  ="+addrip);
		XytUserInfo xytUserInfo = xytUserInfoDao.getXytUserInfoByRid(userid).get(0);
		DxjTravelRoute route = dxjTravelRouteDao.getTravelRouteById(routeid).get(0);
		
		BrandWCPayParameterVo brandWCPayParameterVo = new BrandWCPayParameterVo();
		
		//获取安全秘钥
		String key = DxjConfig.key;
		
		String openId = xytUserInfo.getOpenid();
		
		String appId = DxjConfig.APPID;
		
		String timeStamp = String.valueOf(System.currentTimeMillis()/1000);
		
		String nonceStr=String.valueOf(Math.random());
		
		String signType = "MD5";
		
		String prepay_id = new String();
		
		try {
			prepay_id = DxjPayUtil.getPrepayId(openId, addrip, route);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String packAge = "prepay_id=" + prepay_id;
		
		String []array = {"appId=".concat(appId), "timeStamp=".concat(timeStamp), "nonceStr=".concat(nonceStr),
				"package=".concat(packAge), "signType=".concat(signType)};
		
		SignUtilTest.sort(array);
		
		String stringA = new String() ;
		
		String stringSignTemp = new String();
		
		String paySign = new String();
		
		for(int i = 0 ; i < array.length ; i++)
		{
			stringA = stringA.concat(array[i].concat("&"));
		}
		
		stringSignTemp = stringA.concat("key=").concat(key);
		
		MySecurity mySecurity = new MySecurity();
		
		paySign = mySecurity.encode(stringSignTemp, "MD5");
		
		paySign = paySign.toUpperCase();
		
		brandWCPayParameterVo.setAppId(appId);
		brandWCPayParameterVo.setNonceStr(nonceStr);
		brandWCPayParameterVo.setPackAge(packAge);
		brandWCPayParameterVo.setPaySign(paySign);
		brandWCPayParameterVo.setSignType(signType);
		brandWCPayParameterVo.setTimeStamp(timeStamp);
		
		this.showjsondata(GsonUtil.toJson(brandWCPayParameterVo));
		
		return null;
	}
}
