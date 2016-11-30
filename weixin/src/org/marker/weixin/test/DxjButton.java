package org.marker.weixin.test;

import org.marker.utils.DxjMenu;
import org.marker.utils.MenuUtil;
import org.marker.weixin.exception.WeixinException;
import org.marker.weixin.exception.WeixinSubMenuOutOfBoundException;
import org.marker.weixin.msg.Data4Button;
import org.marker.weixin.msg.Data4Menu;

import com.alibaba.fastjson.JSON;

public class DxjButton {
public static void main(String[] args) {
		
		// 创建按钮
		Data4Button btn = new Data4Button();
		
		// 创建一级菜单
		Data4Menu menu1 = new Data4Menu("click","HIGH假期","travel");
		Data4Menu menu2 = new Data4Menu("click","大神馆","show");		
		Data4Menu menu3 = new Data4Menu("click","我的校园","beauty");
		
		Data4Menu menu31 = new Data4Menu("click","校园社团","holiday");
		Data4Menu menu32 = new Data4Menu("click","我的同学","myfriends");
		Data4Menu menu33 = new Data4Menu("click","我的主页","home");
		
		try {
			menu3.addSubMenu(menu31);
			menu3.addSubMenu(menu32);
			menu3.addSubMenu(menu33);
		} catch (WeixinSubMenuOutOfBoundException e1) {
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
			DxjMenu.create(menustr);
			System.out.println("菜单创建成功");
		} catch (WeixinException e) { 
			e.printStackTrace();
		}
		
	}
}
