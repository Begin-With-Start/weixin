package com.xxcb.weixin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.marker.config.ConfigTest;
import org.marker.config.DxjConfig;
import org.marker.utils.DxjMenu;
import org.marker.utils.HttpUtil;
import org.marker.utils.MenuUtilTest;
import org.marker.utils.MySecurity;
import org.marker.weixin.DefaultSession;
import org.marker.weixin.HandleMessageAdapter;
import org.marker.weixin.exception.WeixinException;
import org.marker.weixin.msg.Data4Item;
import org.marker.weixin.msg.Msg4Event;
import org.marker.weixin.msg.Msg4Image;
import org.marker.weixin.msg.Msg4ImageText;
import org.marker.weixin.msg.Msg4Link;
import org.marker.weixin.msg.Msg4Location;
import org.marker.weixin.msg.Msg4Text;
import org.marker.weixin.msg.Msg4Video;
import org.marker.weixin.msg.Msg4Voice;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.xxcb.dao.CjxmDao;
import com.xxcb.po.KeyMaterialMap;
import com.xyt.action.XytCourseAction;
import com.xyt.dao.XytAccessTokenDao;
import com.xyt.dao.XytCourseDao;
import com.xyt.dao.XytUserInfoDao;
import com.xyt.po.XytAccessToken;
import com.xyt.po.XytCourse;
import com.xyt.po.XytUserInfo;
import com.xyt.util.XytScoreCodeCreateUtil;

	public class ValidateServletTest extends HttpServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1549494578583907716L;
		private XytAccessTokenDao xytAccessTokenDao = null;
		private CjxmDao dao = null;		
		private XytUserInfoDao xytUserInfoDao = null;		
		private XytCourseDao xytCourseDao = null;		
		
		private XytCourseAction xytCourseAction = null;
		
		
		
		//每7200秒刷新一次accesstoken
		private final long time= 7200000;
		// TOKEN 是你在微信平台开发模式中设置的哦
		public static final String TOKEN = "hnzhenhaowantest";
		/**
		 * 处理微信服务器验证
		 * 
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			System.out.println("test");
			String signature = request.getParameter("signature");// 微信加密签名
			String timestamp = request.getParameter("timestamp");// 时间戳
			String nonce = request.getParameter("nonce");// 随机数
			String echostr = request.getParameter("echostr");// 随机字符串

			// 重写totring方法，得到三个参数的拼接字符串
			List<String> list = new ArrayList<String>(3) {
				private static final long serialVersionUID = 4440739483644821986L;
				public String toString() {
					return this.get(0) + this.get(1) + this.get(2);
				}
			};
			list.add(TOKEN);
			list.add(timestamp);
			list.add(nonce);
			Collections.sort(list);// 排序
			String tmpStr = new MySecurity().encode(list.toString(),
					MySecurity.SHA_1);// SHA-1加密
			Writer out = response.getWriter();
			if (signature.equals(tmpStr)) {
				out.write(echostr);// 请求验证成功，返回随机码
			} else {
				out.write("");
			}
			out.flush();
			out.close();
		}

		
		/**
		 * 处理微信服务器发过来的各种消息，包括：文本、图片、地理位置、音乐等等
		 * 
		 * 
		 */
		protected void doPost(final HttpServletRequest request,
				final HttpServletResponse response) throws ServletException, IOException {
			PropertyConfigurator.configure( "C:/Program Files/Apache Software Foundation/Tomcat 6.0/webapps/weixin/WEB-INF/log4j.properties" );
			final Logger logger  =  Logger.getLogger(ValidateServletTest.class );
			final String basepath = xytCourseAction.getBasePath();
			//设置basepath
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			InputStream is = request.getInputStream();
			OutputStream os = response.getOutputStream();
			String msgSignature = request.getParameter("signature");  
	        // 时间戳  
			final String timestamp = request.getParameter("timestamp"); 
			final String nonce = request.getParameter("nonce");// 随机数
			 
			
			final DefaultSession session = DefaultSession.newInstance(); 
		session.addOnHandleMessageListener(new HandleMessageAdapter() {
				@Override
				public void onTextMsg(Msg4Text msg) { 
					//回复一条消息
					logger.warn("收到消息  "+msg.getContent());
					Msg4Text reMsg = new Msg4Text();
					reMsg.setFromUserName(msg.getToUserName());
					reMsg.setToUserName(msg.getFromUserName());
					reMsg.setCreateTime(msg.getCreateTime());
					
					//图文消息
					Msg4ImageText reMsg4ImageText = new Msg4ImageText();
					reMsg4ImageText.setCreateTime(msg.getCreateTime());
					reMsg4ImageText.setFromUserName(msg.getToUserName());
					reMsg4ImageText.setToUserName(msg.getFromUserName());
					reMsg4ImageText.setFuncFlag("0");
					//图文消息对象
					Data4Item data4Item = new Data4Item();
					List<KeyMaterialMap> list = dao.getMediaIdByKeyword(msg.getContent());
					if(0 != list.size())
					{
						//如果找到了关键字对应的mediaid，则返回图文消息给用户
						KeyMaterialMap keyMaterialMap = list.get(0);
						String mediaId = keyMaterialMap.getMediaId();
						MenuUtilTest menuUtilTest = new MenuUtilTest();
						String access_token = null;
						try {
							access_token = menuUtilTest.getAccessToken();
						} catch (WeixinException e) {
							// TODO Auto-generated catch block
							logger.warn("获取token失败  "+e.getMessage());
							e.printStackTrace();
						}
						
						logger.warn("access_token：  "+access_token);
						String requestUrlMedia = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
						requestUrlMedia = requestUrlMedia.replace("ACCESS_TOKEN", access_token);
						JSONObject outputStrMedia = new JSONObject();
						outputStrMedia.put("media_id", mediaId);
						JSONObject jsonObjectMedia = HttpUtil.httpsRequest(requestUrlMedia, "POST", outputStrMedia.toString());
						if( null != jsonObjectMedia)
						{
							logger.warn("jsonObjectMedia is not null");
							//解析获取的图文消息
							//arrayItem保存
							JSONArray arrayItem=jsonObjectMedia.getJSONArray("news_item");
							logger.warn("arrayItem.size():  "+arrayItem.size() );
							JSONObject rowsItem=null;
							for(int j = 0 ; j < arrayItem.size() ; j++)
							{
								rowsItem = arrayItem.getJSONObject(j);
								
								//图文消息的标题
								String title = rowsItem.getString("title");
								
								//图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
								String digest = rowsItem.getString("digest");
								
								//图文页的URL
								String url = rowsItem.getString("url");
								
								data4Item.setTitle(title);
								data4Item.setDescription(digest);
								data4Item.setPicUrl(keyMaterialMap.getPicurl());
								data4Item.setUrl(url);
								
								reMsg4ImageText.addItem(data4Item);
							}
							session.callback(reMsg4ImageText);//回传消息
						}
						
					}else if(msg.getContent().equals("news"))
					{
						//获取所有的图文素材
						String requestUrlMedia = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
						String access_token = null;
						try {
							access_token = DxjMenu.getAccessToken();
						} catch (WeixinException e) {
							// TODO Auto-generated catch block
							logger.warn("获取token失败  "+e.getMessage());
							e.printStackTrace();
						}
						requestUrlMedia = requestUrlMedia.replace("ACCESS_TOKEN", access_token);
						
						JSONObject mediaJson = new JSONObject();
						mediaJson.put("type", "news");
						mediaJson.put("offset", 0);
						mediaJson.put("count", 20);
						
						JSONObject jsonObjectMedia = HttpUtil.httpsRequest(requestUrlMedia, "POST", mediaJson.toString());
						if(null != jsonObjectMedia)
						{
							logger.warn("jsonObjectMedia is not null");
							String total_count = jsonObjectMedia.getString("total_count");
							String item_count = jsonObjectMedia.getString("item_count");
							System.out.println("total_count:"+total_count);
							System.out.println("item_count:"+item_count);
							
							JSONArray arrayItem=jsonObjectMedia.getJSONArray("item");
							for(int index = 0 ; index < arrayItem.size(); index ++)
							{
								JSONObject rowsItem=arrayItem.getJSONObject(index);
								String media_id = rowsItem.getString("media_id");
								System.out.println("media_id:"+media_id);
								JSONObject content = rowsItem.getJSONObject("content");
								System.out.println();
								System.out.println();
								JSONArray news_item = content.getJSONArray("news_item");
								for(int i = 0; i < news_item.size(); i++)
								{
									JSONObject news = news_item.getJSONObject(i);
									
									String title = news.getString("title");
									System.out.println("title:"+title);
									
									System.out.println();
								}
								
							}
						}
						reMsg.setContent("调用成功");
						reMsg.setFuncFlag("0"); 
						session.callback(reMsg);//回传消息
					}
					else if(msg.getContent().equals("image"))
					{
						//获取所有的图文素材
						String requestUrlMedia = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
						String access_token = null;
						try {
							access_token = DxjMenu.getAccessToken();
						} catch (WeixinException e) {
							// TODO Auto-generated catch block
							logger.warn("获取token失败  "+e.getMessage());
							e.printStackTrace();
						}
						requestUrlMedia = requestUrlMedia.replace("ACCESS_TOKEN", access_token);
						
						JSONObject mediaJson = new JSONObject();
						mediaJson.put("type", "image");
						mediaJson.put("offset", 0);
						mediaJson.put("count", 20);
						
						JSONObject jsonObjectMedia = HttpUtil.httpsRequest(requestUrlMedia, "POST", mediaJson.toString());
						if(null != jsonObjectMedia)
						{
							logger.warn("jsonObjectMedia is not null");
							String total_count = jsonObjectMedia.getString("total_count");
							String item_count = jsonObjectMedia.getString("item_count");
							
							System.out.println();
							System.out.println("total_count:"+total_count);
							System.out.println("item_count:"+item_count);
							
							JSONArray arrayItem=jsonObjectMedia.getJSONArray("item");
							for(int index = 0 ; index < arrayItem.size(); index ++)
							{
								JSONObject rowsItem=arrayItem.getJSONObject(index);
								String media_id = rowsItem.getString("media_id");
								System.out.println("media_id:"+media_id);
								String name = rowsItem.getString("name");
								System.out.println("name:"+name);
								System.out.println();
							}
						}
						reMsg.setContent("调用成功");
						reMsg.setFuncFlag("0"); 
						session.callback(reMsg);//回传消息
					}else if(msg.getContent().equals("1")){
						String mediaId = "5pnMAQ6EhNJyTm1_r4sSSruUZpgYYvp1sdNiJAs149A";
						String access_token = null;
						try {
							access_token = DxjMenu.getAccessToken();
						} catch (WeixinException e) {
							// TODO Auto-generated catch block
							logger.warn("获取token失败  "+e.getMessage());
							e.printStackTrace();
						}
						String requestUrlMedia = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
						requestUrlMedia = requestUrlMedia.replace("ACCESS_TOKEN", access_token);
						JSONObject outputStrMedia = new JSONObject();
						outputStrMedia.put("media_id", mediaId);
						JSONObject jsonObjectMedia = HttpUtil.httpsRequest(requestUrlMedia, "POST", outputStrMedia.toString());
						if( null != jsonObjectMedia)
						{
							logger.warn("jsonObjectMedia is not null");
							//解析获取的图文消息
							//arrayItem保存
							JSONArray arrayItem=jsonObjectMedia.getJSONArray("news_item");
							logger.warn("arrayItem.size():  "+arrayItem.size() );
							JSONObject rowsItem=null;
								rowsItem = arrayItem.getJSONObject(1);
								
								//图文消息的标题
								String title = rowsItem.getString("title");
								
								//图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
								String digest = rowsItem.getString("digest");
								
								//图文页的URL
								String url = rowsItem.getString("url");
								
								//图文封面图片
								data4Item.setTitle(title);
								data4Item.setDescription(digest);
								data4Item.setPicUrl("https://mmbiz.qlogo.cn/mmbiz_jpg/uRjE8wkgQQxNLG7zsXRRB5IByMtmmY90icgT2ZV1hsic8IbNFgfibXBvKDHNPBgBZP6527KFRMz4rkI0NiaVb3GIow/0?wx_fmt=jpeg");
								data4Item.setUrl(url);
								
								reMsg4ImageText.addItem(data4Item);
							session.callback(reMsg4ImageText);//回传消息
						}
					}else if(msg.getContent().equals("2")){
						String mediaId = "5pnMAQ6EhNJyTm1_r4sSSruUZpgYYvp1sdNiJAs149A";
						String access_token = null;
						try {
							access_token = DxjMenu.getAccessToken();
						} catch (WeixinException e) {
							// TODO Auto-generated catch block
							logger.warn("获取token失败  "+e.getMessage());
							e.printStackTrace();
						}
						String requestUrlMedia = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
						requestUrlMedia = requestUrlMedia.replace("ACCESS_TOKEN", access_token);
						JSONObject outputStrMedia = new JSONObject();
						outputStrMedia.put("media_id", mediaId);
						JSONObject jsonObjectMedia = HttpUtil.httpsRequest(requestUrlMedia, "POST", outputStrMedia.toString());
						if( null != jsonObjectMedia)
						{
							logger.warn("jsonObjectMedia is not null");
							//解析获取的图文消息
							//arrayItem保存
							JSONArray arrayItem=jsonObjectMedia.getJSONArray("news_item");
							logger.warn("arrayItem.size():  "+arrayItem.size() );
							JSONObject rowsItem=null;
								rowsItem = arrayItem.getJSONObject(2);
								
								//图文消息的标题
								String title = rowsItem.getString("title");
								
								//图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
								String digest = rowsItem.getString("digest");
								
								//图文页的URL
								String url = rowsItem.getString("url");
								
								//图文封面图片
								data4Item.setTitle(title);
								data4Item.setDescription(digest);
								data4Item.setPicUrl("https://mmbiz.qlogo.cn/mmbiz_jpg/uRjE8wkgQQxNLG7zsXRRB5IByMtmmY90pw7gOjp0aKqxctBTD695To6TXdlYaxRa1dF8capOdacIW5uatugsmA/0?wx_fmt=jpeg");
								data4Item.setUrl(url);
								
								reMsg4ImageText.addItem(data4Item);
							session.callback(reMsg4ImageText);//回传消息
						}
					}else if(msg.getContent().equals("3")){
						String mediaId = "5pnMAQ6EhNJyTm1_r4sSSruUZpgYYvp1sdNiJAs149A";
						String access_token = null;
						try {
							access_token = DxjMenu.getAccessToken();
						} catch (WeixinException e) {
							// TODO Auto-generated catch block
							logger.warn("获取token失败  "+e.getMessage());
							e.printStackTrace();
						}
						String requestUrlMedia = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
						requestUrlMedia = requestUrlMedia.replace("ACCESS_TOKEN", access_token);
						JSONObject outputStrMedia = new JSONObject();
						outputStrMedia.put("media_id", mediaId);
						JSONObject jsonObjectMedia = HttpUtil.httpsRequest(requestUrlMedia, "POST", outputStrMedia.toString());
						if( null != jsonObjectMedia)
						{
							logger.warn("jsonObjectMedia is not null");
							//解析获取的图文消息
							//arrayItem保存
							JSONArray arrayItem=jsonObjectMedia.getJSONArray("news_item");
							logger.warn("arrayItem.size():  "+arrayItem.size() );
							JSONObject rowsItem=null;
								rowsItem = arrayItem.getJSONObject(3);
								
								//图文消息的标题
								String title = rowsItem.getString("title");
								
								//图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
								String digest = rowsItem.getString("digest");
								
								//图文页的URL
								String url = rowsItem.getString("url");
								
								//图文封面图片
								data4Item.setTitle(title);
								data4Item.setDescription(digest);
								data4Item.setPicUrl("https://mmbiz.qlogo.cn/mmbiz_jpg/uRjE8wkgQQxNLG7zsXRRB5IByMtmmY90Q6rFjCHNMUU8Oh0ib4lPA2OZb8icBicpgj7rgbPq2yj6z2ibrWQxyB3WRA/0?wx_fmt=jpeg");
								data4Item.setUrl(url);
								
								reMsg4ImageText.addItem(data4Item);
							}
							session.callback(reMsg4ImageText);//回传消息
					}else if(msg.getContent().equals("4")){
						String mediaId = "5pnMAQ6EhNJyTm1_r4sSSruUZpgYYvp1sdNiJAs149A";
						String access_token = null;
						try {
							access_token = DxjMenu.getAccessToken();
						} catch (WeixinException e) {
							// TODO Auto-generated catch block
							logger.warn("获取token失败  "+e.getMessage());
							e.printStackTrace();
						}
						String requestUrlMedia = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
						requestUrlMedia = requestUrlMedia.replace("ACCESS_TOKEN", access_token);
						JSONObject outputStrMedia = new JSONObject();
						outputStrMedia.put("media_id", mediaId);
						JSONObject jsonObjectMedia = HttpUtil.httpsRequest(requestUrlMedia, "POST", outputStrMedia.toString());
						if( null != jsonObjectMedia)
						{
							logger.warn("jsonObjectMedia is not null");
							//解析获取的图文消息
							//arrayItem保存
							JSONArray arrayItem=jsonObjectMedia.getJSONArray("news_item");
							logger.warn("arrayItem.size():  "+arrayItem.size() );
							JSONObject rowsItem=null;
								rowsItem = arrayItem.getJSONObject(4);
								
								//图文消息的标题
								String title = rowsItem.getString("title");
								
								//图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
								String digest = rowsItem.getString("digest");
								
								//图文页的URL
								String url = rowsItem.getString("url");
								
								//图文封面图片
								data4Item.setTitle(title);
								data4Item.setDescription(digest);
								data4Item.setPicUrl("https://mmbiz.qlogo.cn/mmbiz_jpg/uRjE8wkgQQxNLG7zsXRRB5IByMtmmY90o4HefypeYdrPhib1t2CkPyvh45VInVlAIZAeWOEZ3CeY0QibqicicuDIIA/0?wx_fmt=jpeg");
								data4Item.setUrl(url);
								
								reMsg4ImageText.addItem(data4Item);
							session.callback(reMsg4ImageText);//回传消息
						}
					}
					
					else
					{
						reMsg.setContent("感谢您的回复！如需帮助，请回复“help”");
						reMsg.setFuncFlag("0"); 
						session.callback(reMsg);//回传消息
					}
				}

				@Override
				public void onImageMsg(Msg4Image msg) {

				}

				@SuppressWarnings("deprecation")
				@Override
				public void onEventMsg(final Msg4Event msg) {
					
					String eType = msg.getEventKey();
					String event = msg.getEvent();
					Msg4Text reMsg = new Msg4Text();
					reMsg.setFromUserName(msg.getToUserName());
					reMsg.setToUserName(msg.getFromUserName());
					reMsg.setCreateTime(msg.getCreateTime());
					
					XytUserInfo  userInfo = xytUserInfoDao.getXytUserInfoByOpenId(msg.getFromUserName());
					
					if(event.equals("subscribe")){
						
						logger.warn("关注的公众号为："+msg.getToUserName());
						
						XytUserInfo xytUserInfo = new XytUserInfo();
						String openId = msg.getFromUserName();
						logger.warn(msg.getToUserName()+"粉丝openId   :"+openId);
						//判断数据库是否已保存粉丝信息
						
						XytUserInfo pXytUserInfo = xytUserInfoDao.getXytUserInfoByOpenId(openId);
						//关注的是湖南几好玩
						if(msg.getToUserName().equals(ConfigTest.appName))
						{
							logger.warn("关注《湖南几好玩》");
							if(pXytUserInfo == null)
							{
								//从数据库获取Oauth2AccessToken
								XytAccessToken oauth2AccessToken = new XytAccessToken();
								List<XytAccessToken> listXytAccessToken = xytAccessTokenDao.getAccessToken("hnjihaowan");
								logger.warn("listXytAccessToken.size()："+listXytAccessToken.size());
								
								if(0 != listXytAccessToken.size())
								{
									oauth2AccessToken = listXytAccessToken.get(0);
									Date now = new Date();
									logger.warn("now："+now.getTime());
									logger.warn("time:  "+listXytAccessToken.get(0).getTime());
									if(time < (now.getTime() - oauth2AccessToken.getTime().getTime()))
									{
										logger.warn("时间差     "+ (now.getTime() - oauth2AccessToken.getTime().getTime()));
										//重新获取AccessToken并刷新数据库中的记录
										oauth2AccessToken = HttpUtil.getXytAccessToken();
										SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss:ms");
										String currentTime=df.format(now);
										logger.warn("accessToken.getAccessToken()："+oauth2AccessToken.getAccessToken());
										System.out.println("currentTime  :"+currentTime);
										xytAccessTokenDao.updateAccessToken("hnjihaowan",oauth2AccessToken.getAccessToken(), currentTime);
									}
								}else
								{
									oauth2AccessToken = HttpUtil.getXytAccessToken();
									
									xytAccessTokenDao.save(oauth2AccessToken);
								}
								String accessToken = oauth2AccessToken.getAccessToken();
								logger.warn("accessToken："+accessToken);
								String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
								requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
								JSONObject jsonObject = HttpUtil.httpsRequest(requestUrl, "GET", null);
								
								if( null != jsonObject)
								{
									logger.warn("jsonObject is not null   "+jsonObject.toString());
									try{
										xytUserInfo = new XytUserInfo();
										xytUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
										xytUserInfo.setOpenid(jsonObject.getString("openid"));
										xytUserInfo.setNickname(jsonObject.getString("nickname"));
										xytUserInfo.setSex(jsonObject.getInt("sex"));
										xytUserInfo.setLanguage(jsonObject.getString("language"));
										xytUserInfo.setCity(jsonObject.getString("city"));
										xytUserInfo.setProvince(jsonObject.getString("province"));
										xytUserInfo.setCountry(jsonObject.getString("country"));
										xytUserInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
										xytUserInfo.setSubscribe_time(jsonObject.getString("subscribe_time"));
										xytUserInfo.setRemark(jsonObject.getString("remark"));
										xytUserInfo.setGroupid(jsonObject.getString("groupid"));
										xytUserInfo.setWeixin(msg.getToUserName());
										//为每一个粉丝生成一个唯一的随机编码
										String scoreCode = XytScoreCodeCreateUtil.createScoreCode();
										while(true){
											XytUserInfo user = xytUserInfoDao.getXytUserInfoByScoreCode(scoreCode);
											if(null == user)
											{
												break;
											}else{
												scoreCode = XytScoreCodeCreateUtil.createScoreCode();
											}
										}
										xytUserInfo.setScoreCode(scoreCode);
										
										logger.warn("湖南几好玩新关注粉丝昵称  ："+xytUserInfo.getNickname());
										//保存用户数据
										logger.warn("保存用户数据");
										xytUserInfoDao.save(xytUserInfo);
									    logger.warn("用户数据保存成功");
									}catch(Exception e)
									{
										if( 0 == xytUserInfo.getSubscribe())
										{
											logger.warn("用户已取消关注");
										}
										else
										{
											int errorCode = jsonObject.getInt("errcode");
											String errorMsg = jsonObject.getString("errmsg");
											logger.warn("获取用户信息失败");
										}
									}
								}
							}
							reMsg.setContent("欢迎关注《湖南几好玩》");
						}else if(msg.getToUserName().equals(DxjConfig.appName))//关注的为大学伽
						{
							logger.warn("关注《DA学伽》");
							if(pXytUserInfo == null)
							{
								//从数据库获取Oauth2AccessToken
								XytAccessToken oauth2AccessToken = new XytAccessToken();
								List<XytAccessToken> listXytAccessToken = xytAccessTokenDao.getAccessToken("dxj");
								logger.warn("listXytAccessToken.size()："+listXytAccessToken.size());
								
								if(0 != listXytAccessToken.size())
								{
									oauth2AccessToken = listXytAccessToken.get(0);
									Date now = new Date();
									logger.warn("now："+now.getTime());
									logger.warn("time:  "+listXytAccessToken.get(0).getTime());
									if(time < (now.getTime() - oauth2AccessToken.getTime().getTime()))
									{
										logger.warn("时间差     "+ (now.getTime() - oauth2AccessToken.getTime().getTime()));
										//重新获取AccessToken并刷新数据库中的记录
										oauth2AccessToken = HttpUtil.getDxjAccessToken();
										SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss:ms");
										String currentTime=df.format(now);
										logger.warn("accessToken.getAccessToken()："+oauth2AccessToken.getAccessToken());
										System.out.println("currentTime  :"+currentTime);
										xytAccessTokenDao.updateAccessToken("dxj",oauth2AccessToken.getAccessToken(), currentTime);
									}
								}else
								{
									oauth2AccessToken = HttpUtil.getDxjAccessToken();
									xytAccessTokenDao.save(oauth2AccessToken);
								}
								String accessToken = oauth2AccessToken.getAccessToken();
								logger.warn("accessToken："+accessToken);
								String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
								requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
								JSONObject jsonObject = HttpUtil.httpsRequest(requestUrl, "GET", null);
								
								if( null != jsonObject)
								{
									logger.warn("jsonObject is not null   "+jsonObject.toString());
									try{
										xytUserInfo = new XytUserInfo();
										xytUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
										xytUserInfo.setOpenid(jsonObject.getString("openid"));
										xytUserInfo.setNickname(jsonObject.getString("nickname"));
										xytUserInfo.setSex(jsonObject.getInt("sex"));
										xytUserInfo.setLanguage(jsonObject.getString("language"));
										xytUserInfo.setCity(jsonObject.getString("city"));
										xytUserInfo.setProvince(jsonObject.getString("province"));
										xytUserInfo.setCountry(jsonObject.getString("country"));
										xytUserInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
										xytUserInfo.setSubscribe_time(jsonObject.getString("subscribe_time"));
										xytUserInfo.setRemark(jsonObject.getString("remark"));
										xytUserInfo.setGroupid(jsonObject.getString("groupid"));
										xytUserInfo.setWeixin(msg.getToUserName());
										//为每一个粉丝生成一个唯一的随机编码
										String scoreCode = XytScoreCodeCreateUtil.createScoreCode();
										while(true){
											XytUserInfo user = xytUserInfoDao.getXytUserInfoByScoreCode(scoreCode);
											if(null == user)
											{
												break;
											}else{
												scoreCode = XytScoreCodeCreateUtil.createScoreCode();
											}
										}
										xytUserInfo.setScoreCode(scoreCode);
										
										logger.warn("大学伽新关注粉丝昵称  ："+xytUserInfo.getNickname());
										//保存用户数据
										logger.warn("保存用户数据");
										xytUserInfoDao.save(xytUserInfo);
									    logger.warn("用户数据保存成功");
									}catch(Exception e)
									{
										if( 0 == xytUserInfo.getSubscribe())
										{
											logger.warn("用户已取消关注");
										}
										else
										{
											int errorCode = jsonObject.getInt("errcode");
											String errorMsg = jsonObject.getString("errmsg");
											logger.warn("获取用户信息失败");
										}
									}
								}
							}
							reMsg.setContent("欢迎关注《DA学伽》");
						}
						
						
					
					}else if(event.equals("LOCATION")){
						//粉丝关注之后推送地理位置事件
						System.out.println("收到地理位置消息：");
						logger.warn("收到地理位置消息");
						logger.warn("经度  :"+msg.getLongitude());
						logger.warn("纬度  :"+msg.getLatitude());
						logger.warn("精度  :"+msg.getPrecision());
						
					}else if (eType.equals("button1")){
						XytCourse xytCourse = xytCourseDao.getXytCourseByButton("button1").get(0);
						String url = "<a href='"+ basepath + "xyt/XytCourseAction!getXytCourse.action?user="+userInfo.getRid()+"&courseId="+xytCourse.getRid()+"'>点击查看课程详情</a>";
						reMsg.setContent(url);
							
					}else if (eType.equals("button2")){
						XytCourse xytCourse = xytCourseDao.getXytCourseByButton("button2").get(0);
						String url = "<a href='"+ basepath + "xyt/XytCourseAction!getXytCourse.action?user="+userInfo.getRid()+"&courseId="+xytCourse.getRid()+"'>点击查看课程详情</a>";
						reMsg.setContent(url);
					}else if (eType.equals("button3")){
						XytCourse xytCourse = xytCourseDao.getXytCourseByButton("button3").get(0);
						String url = "<a href='"+ basepath + "xyt/XytCourseAction!getXytCourse.action?user="+userInfo.getRid()+"&courseId="+xytCourse.getRid()+"'>点击查看课程详情</a>";
						reMsg.setContent(url);
					}else if (eType.equals("button5")){
						reMsg.setContent("<a href='"+ basepath +"xyt/XytCourseAction!getAllXytCourse.action?user="+userInfo.getRid()+"'>点击查看更多课程</a>");
					}else if (eType.equals("myclass")){
						reMsg.setContent("<a href='"+ basepath +"xyt/XytOrderAction!getCourses.action?user="+userInfo.getRid()+"'>点击查看我的课表</a>");
					
					}else if (eType.equals("group")){
						reMsg.setContent("<a href='"+ basepath +"xyt/XytGroupOrderAction!getXytGroupOrderInProceed.action?user="+userInfo.getRid()+"'>点击查看组团信息</a>");
						
					}else if (eType.equals("aroundcompus")){
						if(null != userInfo.getCollege())
						{
							reMsg.setContent("<a href='"+ basepath +"xyt/XytCollegeAction!getXytUserInfoByCollegeId.action?user="+userInfo.getRid()+"&collegeId="+userInfo.getCollege().getRid() +"'>点击进入我的学校</a>");
						}else
						{
							reMsg.setContent("您尚未入学，<a href='"+ basepath +"xyt/XytCollegeAction!chooseCampus.action?user="+userInfo.getRid()+"'>点击选择我的学校</a>");
						}
					}else if (eType.equals("classmates")){
						reMsg.setContent("<a href='"+ basepath +"xyt/userAttantion!showMyFriends.action?userId="+userInfo.getRid()+"'>点击查看我的好友</a>");
					}else if (eType.equals("myhome")){
						reMsg.setContent("<a href='"+ basepath +"xyt/userInfo!showMyHome.action?userId="+userInfo.getRid()+"'>点击进入我的主页</a>");
					}
					
					else if (eType.equals("show")){
						reMsg.setContent("<a href='"+ basepath +"dxj/DxjSuperStarAction!getAllSuperStar.action?user="+userInfo.getRid()+"'>点击进入大神馆</a>");
					}else if (eType.equals("holiday")){
						reMsg.setContent("<a href='"+ basepath +"dxj/DxjTravelRouteAction!shooseGroup.action?userId="+userInfo.getRid()+"'>点击进入校园社团</a>");
					}else if (eType.equals("myfriends")){
						reMsg.setContent("<a href='"+ basepath +"xyt/userAttantion!showMyFriends.action?userId="+userInfo.getRid()+"'>点击查看我的好友</a>");
					}else if (eType.equals("home")){
						reMsg.setContent("<a href='"+ basepath +"xyt/userInfo!showMyHome.action?userId="+userInfo.getRid()+"'>点击进入我的主页</a>");
					}else if (eType.equals("travel")){
						
						reMsg.setContent("<a href='"+ basepath +"dxj/DxjTravelRouteAction!getAlltravelRoute.action?user="+userInfo.getRid()+"'>点击进入HIGH假期</a>");
					}
					
					
					session.callback(reMsg);//回传消息
				}

				@Override
				public void onLinkMsg(Msg4Link msg) {
					System.out.println("收到URL："+msg.getUrl());
				}

				@Override
				public void onLocationMsg(Msg4Location msg) {
					System.out.println("收到地理位置消息：");
					logger.warn("收到地理位置消息");
					System.out.println("X:"+msg.getLocation_X());
					System.out.println("Y:"+msg.getLocation_Y());
					System.out.println("Scale:"+msg.getScale());
					logger.warn("X:"+msg.getLocation_X());
					logger.warn("Y:"+msg.getLocation_Y());
				}

				@Override
				public void onErrorMsg(int errorCode) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onVoiceMsg(Msg4Voice msg) {
					// TODO Auto-generated method stub
					logger.warn("收到语音消息");
					String content = msg.getRecognition();
					logger.warn("content :"+content);
					Msg4Text reMsg = new Msg4Text();
					reMsg.setFromUserName(msg.getToUserName());
					reMsg.setToUserName(msg.getFromUserName());
					reMsg.setCreateTime(msg.getCreateTime());
					
					reMsg.setContent("您发送的语音识别结果为  ："+content);
					reMsg.setFuncFlag("0"); 
					session.callback(reMsg);//回传消息
				}

				@Override
				public void onVideoMsg(Msg4Video msg) {
					// TODO Auto-generated method stub
					
				}
				
		});
			session.process(is, os);
			session.close();
		}

		/**
		 * 初始化方法
		 */
		public void init() throws ServletException {
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();  
			dao = (CjxmDao)wac.getBean("HxdlDao"); 
			xytAccessTokenDao = (XytAccessTokenDao)wac.getBean("XytAccessTokenDao"); 
			xytUserInfoDao = (XytUserInfoDao)wac.getBean("XytUserInfoDao"); 
			xytCourseDao = (XytCourseDao)wac.getBean("XytCourseDao"); 
			xytCourseAction = (XytCourseAction)wac.getBean("XytCourseAction"); 
		}

	}
