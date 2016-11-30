package org.marker.weixin.test;
 
import org.marker.utils.MenuUtilTest;
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
public class ButtonTestTest {

	public static void main(String[] args) {
		
		// 创建按钮
		Data4Button btn = new Data4Button();
		
		// 创建一级菜单
		Data4Menu menu1 = new Data4Menu("click","好好学习","goodstudy");
		Data4Menu menu2 = new Data4Menu("click","课表","class");	
		
		Data4Menu menu11 = new Data4Menu("click","我的课表","myclass");	
		Data4Menu menu12 = new Data4Menu("click","组团报名","group");	
		
		Data4Menu menu14 = new Data4Menu("view","测试菜单3","http://vip.vip.natapp.cn/cswxcrm/personalCenter/entrance.do");	
		Data4Menu menu15 = new Data4Menu("view","测试菜单4","http://vip.vip.natapp.cn/cswxcrm/personalCenter/info.do");	
		
		
		Data4Menu menu3 = new Data4Menu("click","我的校园","mycampus");
		Data4Menu menu4 = new Data4Menu("click","英语","button1");
		Data4Menu menu5 = new Data4Menu("click","计算机","button2");
		Data4Menu menu6 = new Data4Menu("click","数学","button3");
		Data4Menu menu8 = new Data4Menu("click","更多课程","button5");
		Data4Menu menu9 = new Data4Menu("click","逛校园","aroundcompus");
		Data4Menu menu10 = new Data4Menu("click","我的同学","classmates");
		
		Data4Menu menu13 = new Data4Menu("click","我的主页","myhome");
		try {
			menu1.addSubMenu(menu4);
			menu1.addSubMenu(menu5);
			menu1.addSubMenu(menu6);
			menu1.addSubMenu(menu8);
			menu2.addSubMenu(menu14);
			menu2.addSubMenu(menu15);
			menu2.addSubMenu(menu11);
			menu2.addSubMenu(menu12);
			menu3.addSubMenu(menu9);
			menu3.addSubMenu(menu10);
			menu3.addSubMenu(menu13);
		} catch (WeixinSubMenuOutOfBoundException e1) {
			e1.printStackTrace();
		}
			
		try {
			// 菜单之间的关系
			btn.addMenu(menu1);	
			btn.addMenu(menu2);			
			btn.addMenu(menu3); 
	 
		/*// 创建按钮
		Data4Button btn = new Data4Button();
		
		// 创建一级菜单
		Data4Menu menu1 = new Data4Menu("click","好好学习","http://vip.vip.natapp.cn/cswxcrm/personalCenter/entrance.do");
		Data4Menu menu2 = new Data4Menu("click","课表","class");	
		
		//Data4Menu menu11 = new Data4Menu("click","我的课表","myclass");	
		//Data4Menu menu12 = new Data4Menu("click","组团报名","group");	
		
		Data4Menu menu14 = new Data4Menu("click","测试菜单1","http://vip.vip.natapp.cn/cswxcrm/personalCenter/entrance.do");	
		Data4Menu menu15 = new Data4Menu("click","测试菜单2","http://vip.vip.natapp.cn/cswxcrm/personalCenter/info.do");	
		
		
		Data4Menu menu3 = new Data4Menu("click","我的校园","http://vip.vip.natapp.cn/cswxcrm/personalCenter/info.do");
		//Data4Menu menu4 = new Data4Menu("click","英语","button1");
		//Data4Menu menu5 = new Data4Menu("click","计算机","button2");
		//Data4Menu menu6 = new Data4Menu("click","数学","button3");
		//Data4Menu menu8 = new Data4Menu("click","更多课程","button5");
		//Data4Menu menu9 = new Data4Menu("view","逛校园","www.baidu.com");
		//Data4Menu menu10 = new Data4Menu("click","我的同学","classmates");
		
		//Data4Menu menu13 = new Data4Menu("click","我的主页","myhome");
		try {
			menu2.addSubMenu(menu14);
			menu2.addSubMenu(menu15);
		} catch (WeixinSubMenuOutOfBoundException e1) {
			e1.printStackTrace();
		}
			
		try {
			// 菜单之间的关系
			btn.addMenu(menu1);	
			btn.addMenu(menu2);			
			btn.addMenu(menu3); */
		
		// Object -> json
		String menustr = JSON.toJSONString(btn);
		
		// weixinapi更具微信规范，添加了异常机制。
		/*
		 * 比如：菜单操作的异常，一级菜单最多三个，超出就抛出异常 
		 * 
		 * */ 
		    MenuUtilTest menuUtilTest = new MenuUtilTest();
		    menuUtilTest.create(menustr);
		} catch (WeixinException e) { 
			e.printStackTrace();
		}
		
	}
}
