package com.xxcb.weixin;

import java.awt.Color;
import java.io.File;
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

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.marker.utils.HttpUtil;
import org.marker.utils.MySecurity;
import org.marker.weixin.DefaultSession;
import org.marker.weixin.HandleMessageAdapter;
import org.marker.weixin.msg.Msg4Event;
import org.marker.weixin.msg.Msg4Image;
import org.marker.weixin.msg.Msg4Link;
import org.marker.weixin.msg.Msg4Location;
import org.marker.weixin.msg.Msg4Text;
import org.marker.weixin.msg.Msg4Video;
import org.marker.weixin.msg.Msg4Voice;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.xxcb.dao.CjxmDao;
import com.xxcb.po.AccessToken;
import com.xxcb.po.Button1;
import com.xxcb.po.Button2;
import com.xxcb.po.Button3;
import com.xxcb.po.Button4;
import com.xxcb.po.InvitationRecord;
import com.xxcb.po.ScoreCode;
import com.xxcb.po.UserInfo;
import com.xxcb.util.AmusingService;
import com.xxcb.util.ContentUtil;
import com.xxcb.util.ImageUtil;
import com.xxcb.util.InvitationCodeUtil;

	
	public class ValidateServlet extends HttpServlet {
		
		private CjxmDao dao = null;		
		private static final long serialVersionUID = 1L;
		
		//每7200秒刷新一次accesstoken
		private final long time= 7200000;
		// TOKEN 是你在微信平台开发模式中设置的哦
		public static final String TOKEN = "hnzhenhaowan2015";
		
		static Logger log4j = Logger.getLogger(ValidateServlet.class.getClass());

		/**
		 * 处理微信服务器验证
		 * 
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			String signature = request.getParameter("signature");// 微信加密签名
			String timestamp = request.getParameter("timestamp");// 时间戳
			String nonce = request.getParameter("nonce");// 随机数
			String echostr = request.getParameter("echostr");// 随机字符串

			// 重写totring方法，得到三个参数的拼接字符串
			List<String> list = new ArrayList<String>(3) {
				private static final long serialVersionUID = 2621444383666420433L;
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
			final Logger logger  =  Logger.getLogger(ValidateServlet.class );
			
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			InputStream is = request.getInputStream();
			OutputStream os = response.getOutputStream();
			String msgSignature = request.getParameter("signature");  
	        // 时间戳  
			final String timestamp = request.getParameter("timestamp"); 
			final String nonce = request.getParameter("nonce");// 随机数
			 
			
			@SuppressWarnings("unused")
			final DefaultSession session = DefaultSession.newInstance(); 
			session.addOnHandleMessageListener(new HandleMessageAdapter() {
				@Override
				public void onTextMsg(Msg4Text msg) { 
					//回复一条消息
					Msg4Text reMsg = new Msg4Text();
					if(ContentUtil.isScoreCode(msg.getContent()))
					{
						reMsg.setFromUserName(msg.getToUserName());
						reMsg.setToUserName(msg.getFromUserName());
						reMsg.setCreateTime(msg.getCreateTime());
						
						
						//直接回复邀请码加分
						String userId = msg.getFromUserName();
						String code = msg.getContent();
						String nickname = null;
						List listUserinfo = dao.getUserByOpenId(userId);
						if(0 != listUserinfo.size())
						{
							UserInfo userInfo = (UserInfo) listUserinfo.get(0);
							nickname = userInfo.getNickname();
						}
						
						List<ScoreCode> listRecord = dao.getInviteRecordByUserId(userId);
						if(0 != listRecord.size())
						{
							logger.warn(userId+"：重复加分");
							reMsg.setContent("亲，你已经帮好友加过分啦，每人只能加一次哦~");
							reMsg.setFuncFlag("0"); 
							session.callback(reMsg);//回传消息
						}else{
							List listCode = dao.getCodeByCode(code);
							if(0 == listCode.size())
							{
								logger.warn(userId+"：输入的邀请码有误");
								reMsg.setContent("您输入的邀请码有误，请核对后再输入，谢谢！");
								reMsg.setFuncFlag("0"); 
								session.callback(reMsg);//回传消息
							}else
							{
								ScoreCode scoreCode = (ScoreCode) listCode.get(0);
								if(scoreCode.getUserId().equals(userId))
								{
									logger.warn(userId+"：输入自己的邀请码");
									reMsg.setContent("您输入的是自己的邀请码，请输入好友的邀请码~");
									reMsg.setFuncFlag("0"); 
									session.callback(reMsg);//回传消息
								}else
								{
									dao.updateScoreByCode(scoreCode.getRid());
									InvitationRecord invitationRecord = new InvitationRecord();
									invitationRecord.setInvitationCode(code);
									invitationRecord.setUserid(userId);
									invitationRecord.setNickname(nickname);
									invitationRecord.setInviterNickname(scoreCode.getNickName());
									invitationRecord.setInviterUserid(scoreCode.getUserId());
									dao.save(invitationRecord);
									logger.warn(userId+"加分成功");
									reMsg.setContent("您帮好友加分成功！");
									reMsg.setFuncFlag("0"); 
									session.callback(reMsg);//回传消息
								}
							}
						}
					}else if(ContentUtil.isPhoneNumber(msg.getContent()))
					{
						reMsg.setFromUserName(msg.getToUserName());
						reMsg.setToUserName(msg.getFromUserName());
						reMsg.setCreateTime(msg.getCreateTime());
						reMsg.setContent("<a href='http://222.240.171.100/weixin/pages/CYtimeLine/index.html?tel="+msg.getContent()+"'>点击查看“我与《潇湘晨报》的故事”</a>");
						reMsg.setFuncFlag("0"); 
						session.callback(reMsg);//回传消息
					}else if(msg.getContent().equals("A"))
					{
						reMsg.setFromUserName(msg.getToUserName());
						reMsg.setToUserName(msg.getFromUserName());
						reMsg.setCreateTime(msg.getCreateTime());
						reMsg.setContent("<a href='http://222.240.171.100/weixin/pages/weizazhi/index.html'>拜年咯！</a>");
						reMsg.setFuncFlag("0"); 
						session.callback(reMsg);//回传消息
					}
					else
					{
						reMsg.setFromUserName(msg.getToUserName());
						reMsg.setToUserName(msg.getFromUserName());
						reMsg.setCreateTime(msg.getCreateTime());
						reMsg.setContent("感谢您对《湖南真好玩》的关注！");
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
					if(event.equals("subscribe")){
						//当用户关注订阅号时通过openid获取其基本信息
						UserInfo userinfo = null;
						String openId = msg.getFromUserName();
						
						//从数据库获取Oauth2AccessToken
						AccessToken oauth2AccessToken = new AccessToken();
						List<AccessToken> listAcctoken = dao.getAccessToken();
						logger.warn("listAcctoken.size()："+listAcctoken.size());
						
						if(0 != listAcctoken.size())
						{
							oauth2AccessToken = listAcctoken.get(0);
							Date now = new Date();
							logger.warn("now："+now);
							logger.warn("time:  "+listAcctoken.get(0).getTime());
							if(time < (now.getTime() - oauth2AccessToken.getTime().getTime()))
							{
								logger.warn("时间差     "+ (now.getTime() - oauth2AccessToken.getTime().getTime()));
								//重新获取AccessToken并刷新数据库中的记录
								oauth2AccessToken = HttpUtil.getAccessToken();
								SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss:ms");
								String currentTime=df.format(now);
								logger.warn("oauth2AccessToken.getAccessToken()："+oauth2AccessToken.getAccessToken());
								System.out.println("currentTime  :"+currentTime);
								dao.updateAccessToken(oauth2AccessToken.getAccessToken(), currentTime);
							}
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
								userinfo = new UserInfo();
								userinfo.setSubscribe(jsonObject.getInt("subscribe"));
								userinfo.setOpenid(jsonObject.getString("openid"));
								userinfo.setNickname(jsonObject.getString("nickname"));
								userinfo.setSex(jsonObject.getInt("sex"));
								userinfo.setLanguage(jsonObject.getString("language"));
								userinfo.setCity(jsonObject.getString("city"));
								userinfo.setProvince(jsonObject.getString("province"));
								userinfo.setCountry(jsonObject.getString("country"));
								userinfo.setHeadimgurl(jsonObject.getString("headimgurl"));
								userinfo.setSubscribe_time(jsonObject.getString("subscribe_time"));
								userinfo.setRemark(jsonObject.getString("remark"));
								userinfo.setGroupid(jsonObject.getString("groupid"));
								logger.warn(userinfo.getNickname());
								//保存用户数据
								logger.warn("保存用户数据");
						        dao.save(userinfo);
						        logger.warn("用户数据保存成功");
							}catch(Exception e)
							{
								if( 0 == userinfo.getSubscribe())
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
						reMsg.setContent("亲，直接回复好友的邀请码即可为好友加分！【为自己积分】积分商城—>生成邀请图片，再将图片保存分享给好友吧！【阿克苏苹果、双肩背包、车展门票免费领！】<a href='http://w.url.cn/s/AguUQ6C'>点击了解活动详情</a>");
						session.callback(reMsg);//回传消息
					}
					else if (eType.equals("0yuangou")){
						//保存点击量
						Date date = new Date();
						String clickdate = date.toString().substring(0, 11).concat(date.toString().substring(24, 28));
						logger.warn("clickdate："+clickdate);
						
						List<Button1> button1record = dao.getButton1ClickRecord(clickdate);
						logger.warn("button1record.size()："+button1record.size());
						if(0 == button1record.size())
						{
							Button1 botton1record = new Button1();
							botton1record.setClickdate(clickdate);
							botton1record.setTotalclick(1);
							dao.save(botton1record);
						}else
						{
							dao.updateButton1(clickdate);
						}
						
						
						logger.warn("关注人："+msg.getFromUserName());
						//判断该用户是否已保存在数据库
						List<UserInfo> userList = dao.getUserByOpenId(msg.getFromUserName());
						if(userList.size() == 0)
						{
							logger.warn("该用户信息暂未保存");
							//保存其基本信息
							UserInfo userinfo = null;
							String openId = msg.getFromUserName();
							//从数据库获取Oauth2AccessToken
							AccessToken oauth2AccessToken = new AccessToken();
							List<AccessToken> listAcctoken = dao.getAccessToken();
							logger.warn("listAcctoken.size()："+listAcctoken.size());
							
							if(0 != listAcctoken.size())
							{
								oauth2AccessToken = listAcctoken.get(0);
								Date now = new Date();
								logger.warn("now："+now);
								logger.warn("time:  "+listAcctoken.get(0).getTime());
								if(time < (now.getTime() - oauth2AccessToken.getTime().getTime()))
								{
									logger.warn("时间差     "+ (now.getTime() - oauth2AccessToken.getTime().getTime()));
									//重新获取AccessToken并刷新数据库中的记录
									oauth2AccessToken = HttpUtil.getAccessToken();
									SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss:ms");
									String currentTime=df.format(now);
									dao.updateAccessToken(oauth2AccessToken.getAccessToken(), currentTime);
								}
							}
							String accessToken = oauth2AccessToken.getAccessToken();
							String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
							requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
							JSONObject jsonObject = HttpUtil.httpsRequest(requestUrl, "GET", null);
							if( null != jsonObject)
							{
								logger.warn("jsonObject is not null   "+jsonObject.toString());
								try{
									userinfo = new UserInfo();
									userinfo.setSubscribe(jsonObject.getInt("subscribe"));
									userinfo.setOpenid(jsonObject.getString("openid"));
									userinfo.setNickname(jsonObject.getString("nickname"));
									userinfo.setSex(jsonObject.getInt("sex"));
									userinfo.setLanguage(jsonObject.getString("language"));
									userinfo.setCity(jsonObject.getString("city"));
									userinfo.setProvince(jsonObject.getString("province"));
									userinfo.setCountry(jsonObject.getString("country"));
									userinfo.setHeadimgurl(jsonObject.getString("headimgurl"));
									userinfo.setSubscribe_time(jsonObject.getString("subscribe_time"));
									userinfo.setRemark(jsonObject.getString("remark"));
									userinfo.setGroupid(jsonObject.getString("groupid"));
									logger.warn(userinfo.getNickname());
									//保存用户数据
									logger.warn("保存用户数据");
							        dao.save(userinfo);
							        logger.warn("用户数据保存成功");
								}catch(Exception e)
								{
									if( 0 == userinfo.getSubscribe())
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
						reMsg.setContent("<a href='http://222.240.171.100/weixin/pages/0yuangou.jsp?user="+msg.getFromUserName()+"'>点击进入免费抽奖</a>");
						session.callback(reMsg);//回传消息
					}else if (eType.equals("lipin")){
						logger.warn("点击我的礼品");
						//保存点击量
						Date date = new Date();
						String clickdate = date.toString().substring(0, 11).concat(date.toString().substring(24, 28));
						logger.warn("clickdate："+clickdate);
						
						List<Button2> button2record = dao.getButton2ClickRecord(clickdate);
						logger.warn("button2record.size()："+button2record.size());
						if(0 == button2record.size())
						{
							Button2 botton2record = new Button2();
							botton2record.setClickdate(clickdate);
							botton2record.setTotalclick(1);
							dao.save(botton2record);
						}else
						{
							dao.updateButton2(clickdate);
						}
						
						
						System.out.println("关注人："+msg.getFromUserName());
						reMsg.setFromUserName(msg.getToUserName());
						reMsg.setToUserName(msg.getFromUserName());
						reMsg.setCreateTime(msg.getCreateTime());
						//logger.warn("用户"+msg.getFromUserName()+"的ip地址为   :"+request.getRemoteAddr());
						reMsg.setContent("<a href='http://222.240.171.100/weixin/pages/1yuangou.jsp?user="+msg.getFromUserName()+"'>点击进入特价商品</a>");
						reMsg.setFuncFlag("0"); 
						session.callback(reMsg);//回传消息
					}else if(eType.equals("creatimage"))
					{
						logger.warn("生成邀请图片");
						
						String accessToken = HttpUtil.getAccessToken().getAccessToken();
						logger.warn("accessToken:"+accessToken);
						
						Msg4Image reMsgImage = new Msg4Image();
						reMsgImage.setFromUserName(msg.getToUserName());
						reMsgImage.setToUserName(msg.getFromUserName());
						reMsgImage.setCreateTime(msg.getCreateTime());
						
						//获取昵称和邀请码
						String nickName = new String();
						String code = new String();
						String headUrl = new String();
						String userId = msg.getFromUserName();
						List<UserInfo> listUserInfo = dao.getUserByOpenId(userId);
						if(0 < listUserInfo.size())
						{
							UserInfo userInfo = listUserInfo.get(0);
							nickName = userInfo.getNickname();
							headUrl = userInfo.getHeadimgurl();
						}
						
						List<ScoreCode> listCode = dao.getCodeByUserId(userId);
						if(0 == listCode.size())
						{
							//如果没有邀请码则直接生成邀请码并返回图片
							ScoreCode scoreCode = new ScoreCode();
							scoreCode.setUserId(userId);
							scoreCode.setScore(0);
							scoreCode.setNickName(nickName);
							//不重复的随机邀请码
							List<ScoreCode> listAllCode = dao.getAllScoreCode();
							String invitationCode = new String();
							while(true)
							{
								invitationCode = InvitationCodeUtil.getRandomCharAndNumr();
								int j = 0;
								for(int i = 0 ; i < listAllCode.size() ; i++)
								{
									if(invitationCode.equals(listAllCode.get(i).getInvitationCode()))
									{
										break;
									}else
									{
										j++;
									}
								}
								if(j == listAllCode.size())
								{
									System.out.println("j == listAllCode.size()");
									break;
								}
								
							}
							scoreCode.setInvitationCode(invitationCode);
							dao.save(scoreCode);
							
							//上传图片的路径已写死
							String path = "C:" + File.separator +"Program Files" + File.separator +"Apache Software Foundation" + File.separator+"Tomcat 6.0" + File.separator+"webapps" + File.separator+"weixin" + File.separator+"pages" + File.separator+"msgImage" + File.separator;
							File fileA = new File(path+  "A.jpg"); // 获取本地文件
							
							File fileB = new File(path+  "B.jpg"); // 获取本地文件
							ImageUtil.createStringMark(fileA.toString(), nickName, invitationCode, Color.white, 100, fileB.toString());
							
							File fileHead = new File(path + File.separator); 
							
							//根据用户的头像地址获取头像图片保存在本地
							String headPicName = "head.jpg";
							String headFile = fileHead.toString();
							try {
								ImageUtil.download(headUrl, headPicName, headFile);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							logger.warn("头像图片保存在本地成功");
							
							//将用户头像缩减为小图片
							String fileBigPic = headFile.concat(File.separator).concat("head.jpg");
							String fileSmallPic = headFile.concat(File.separator).concat("small.jpg");
							
							logger.warn("fileBigPic:"+fileBigPic);
							logger.warn("fileSmallPic:"+fileSmallPic);
							
							ImageUtil.bigToSmall(fileBigPic, fileSmallPic, 60, 60);
							logger.warn("头像缩减为小图片成功");
							//将头像贴附到大图中
							
							ImageUtil.overlapImage(fileB.toString(),fileSmallPic);
							logger.warn("头像贴附成功");
							
							File fileResult = new File(path+  "result.jpg"); // 获取本地文件
							
							String url = "https://api.weixin.qq.com/cgi-bin/material/add_material";
							String mediaId = HttpUtil.uploadImage(url, accessToken, "image", fileResult);
							logger.warn("mediaId:"+mediaId);
							
							reMsgImage.setMediaId(mediaId);
							reMsgImage.setFuncFlag("0");
							
							session.callback(reMsgImage);//回传消息
						}
						else
						{
							ScoreCode scoreCode = listCode.get(0);
							code = scoreCode.getInvitationCode();
							
							//上传图片的路径已写死
							String path = "C:" + File.separator +"Program Files" + File.separator +"Apache Software Foundation" + File.separator+"Tomcat 6.0" + File.separator+"webapps" + File.separator+"weixin" + File.separator+"pages" + File.separator+"msgImage" + File.separator;
							File fileA = new File(path+  "A.jpg"); // 获取本地文件
							
							//fileB为最终发送给用户的图片
							File fileB = new File(path+  "B.jpg"); // 获取本地文件
							ImageUtil.createStringMark(fileA.toString(), nickName, code, Color.white, 100, fileB.toString());
							
							File fileHead = new File(path + File.separator); 
							
							//根据用户的头像地址获取头像图片保存在本地
							String headPicName = "head.jpg";
							String headFile = fileHead.toString();
							try {
								ImageUtil.download(headUrl, headPicName, headFile);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							logger.warn("头像图片保存在本地成功");
							
							//将用户头像缩减为小图片
							String fileBigPic = headFile.concat(File.separator).concat("head.jpg");
							String fileSmallPic = headFile.concat(File.separator).concat("small.jpg");
							
							logger.warn("fileBigPic:"+fileBigPic);
							logger.warn("fileSmallPic:"+fileSmallPic);
							
							ImageUtil.bigToSmall(fileBigPic, fileSmallPic, 60, 60);
							logger.warn("头像缩减为小图片成功");
							//将头像贴附到大图中
							
							ImageUtil.overlapImage(fileB.toString(),fileSmallPic);
							logger.warn("头像贴附成功");
							
							File fileResult = new File(path+  "result.jpg"); // 获取本地文件
							
							String url = "https://api.weixin.qq.com/cgi-bin/material/add_material";
							String mediaId = HttpUtil.uploadImage(url, accessToken, "image", fileResult);
							logger.warn("mediaId:"+mediaId);
							
							reMsgImage.setMediaId(mediaId);
							reMsgImage.setFuncFlag("0");
							
							session.callback(reMsgImage);//回传消息
						}
					}else if(eType.equals("myscore"))
					{
						logger.warn("我的积分");
						reMsg.setFromUserName(msg.getToUserName());
						reMsg.setToUserName(msg.getFromUserName());
						reMsg.setCreateTime(msg.getCreateTime());
						reMsg.setContent("<a href='http://222.240.171.100/weixin/pages/score/myinvitecode.jsp?user="+msg.getFromUserName()+"'>点击查看我的邀请码和积分</a>");
						reMsg.setFuncFlag("0"); 
						session.callback(reMsg);//回传消息
					}else if(eType.equals("jiafen"))
					{
						logger.warn("帮TA加分");
						reMsg.setFromUserName(msg.getToUserName());
						reMsg.setToUserName(msg.getFromUserName());
						reMsg.setCreateTime(msg.getCreateTime());
						reMsg.setContent("直接将好友的邀请码回复给公众号即可完成加分！"+"<a href='http://222.240.171.100/weixin/pages/score/invitationrecord.jsp?user="+msg.getFromUserName()+"'>您也可进入页面帮TA加分</a>");
						reMsg.setFuncFlag("0"); 
						session.callback(reMsg);//回传消息
					}else if(eType.equals("duihuan"))
					{
						logger.warn("兑换礼品");
						reMsg.setFromUserName(msg.getToUserName());
						reMsg.setToUserName(msg.getFromUserName());
						reMsg.setCreateTime(msg.getCreateTime());
						reMsg.setContent("<a href='http://222.240.171.100/weixin/pages/score/jifenduihuan.jsp?user="+msg.getFromUserName()+"'>点击进入积分商城</a>");
						reMsg.setFuncFlag("0"); 
						session.callback(reMsg);//回传消息
					}
					
					System.out.println("--222--------"+eType+"-------333--------");
				}

				@Override
				public void onLinkMsg(Msg4Link msg) {
					System.out.println("收到URL："+msg.getUrl());
				}

				@Override
				public void onLocationMsg(Msg4Location msg) {
					System.out.println("收到地理位置消息：");
					System.out.println("X:"+msg.getLocation_X());
					System.out.println("Y:"+msg.getLocation_Y());
					System.out.println("Scale:"+msg.getScale());
				}

				@Override
				public void onErrorMsg(int errorCode) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onVoiceMsg(Msg4Voice msg) {
					// TODO Auto-generated method stub
					
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
		}

	}
