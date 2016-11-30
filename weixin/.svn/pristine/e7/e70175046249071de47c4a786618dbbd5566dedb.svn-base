package org.marker.weixin.test;
 
import org.marker.utils.MenuUtil;
import org.marker.weixin.exception.WeixinException;
import org.marker.weixin.exception.WeixinSubMenuOutOfBoundException;
import org.marker.weixin.msg.Data4Button;
import org.marker.weixin.msg.Data4Menu;

import com.alibaba.fastjson.JSON;


/**
 * 微信菜单测试代码
 * @author marker
 * @date 2014年8月30日
 * @version 1.0
 */
public class ButtonTest {

	public static void main(String[] args) {
		
		// 创建按钮
		Data4Button btn = new Data4Button();
		
		// 创建一级菜单
		Data4Menu menu1 = new Data4Menu("click","免费抽奖","0yuangou");
		Data4Menu menu2 = new Data4Menu("click","特价商品","lipin");		
		Data4Menu menu3 = new Data4Menu("click","积分商城","");
		//Data4Menu menu3 = new Data4Menu("view","小报童报名","http://www.xxcb.cn/xbt/xbtwx.html");
		Data4Menu menu4 = new Data4Menu("click","兑换礼品","duihuan");
		Data4Menu menu5 = new Data4Menu("click","生成邀请图片","creatimage");
		Data4Menu menu6 = new Data4Menu("click","我的积分","myscore");
		Data4Menu menu7 = new Data4Menu("click","帮TA加分","jiafen");
		try {
			menu3.addSubMenu(menu4);
			menu3.addSubMenu(menu5);
			menu3.addSubMenu(menu6);
			menu3.addSubMenu(menu7);
		} catch (WeixinSubMenuOutOfBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		try {
			// 菜单之间的关系
			btn.addMenu(menu1);	
			btn.addMenu(menu2);			
			btn.addMenu(menu3); 
	 
		// Object -> json
		String menustr = JSON.toJSONString(btn);
		
		// weixinapi更具微信规范，添加了异常机制。
		/*
		 * 比如：菜单操作的异常，一级菜单最多三个，超出就抛出异常 
		 * 
		 * */ 
			MenuUtil.create(menustr);
		} catch (WeixinException e) { 
			e.printStackTrace();
		}
		
	}
}
