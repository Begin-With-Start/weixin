package com.xxcb.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxcb.util.GenericHibernateDao;
import com.xxcb.weixin.ValidateServlet;
@Transactional
@Component("HxdlDao")
public class CjxmDao extends GenericHibernateDao{
	
	static Logger log4j = Logger.getLogger(ValidateServlet.class.getClass());
	
	@SuppressWarnings("unchecked")
	public List getAllCjxm(){
		return this.findBySql("from Cjxm order by sort desc", false);
	}
	
	public List getAllZjjl()
	{
		return this.findBySql(" from Zjjl order by rid desc", false);
	}
	
	public List getAllQgxm()
	{
		return this.findBySql(" from Qgxm order by xmid desc", false);
	}
	
	public List getAllJfdhxm()
	{
		return this.findBySql(" from Jfdh order by sort desc", false);
	}
	
	/**
	 * 根据xmid查找抽奖项目信息
	 * @param openId
	 * @return
	 * @author lidu
	 * @date 2015.8.6
	 */
	public List getCjxmByxmid(String xmid)
	{
		return this.findBySql(" from Jfdh where xmid='"+xmid+"' order by rid desc", false);
	}
	
	
	/**
	 * 每次抢购之后奖品份数减一
	 * @param xmid
	 * @return boolean
	 * @author lidu
	 */
	public boolean updateQgxmFenshuByXmid(int xmid)
	{
		String sql ="update wx_qgxm set FENSHU = FENSHU - 1 where XMID = " + xmid;
		return this.executeSQL(sql, false);
	}
	
	/**
	 * 每次兑换之后积分减少
	 * @param xmid
	 * @return boolean
	 * @author lidu
	 */
	public boolean updateScoreByUserid(String userId, int jiage)
	{
		String sql ="update wx_scorecode set score = score - "+ jiage +" where userId = '" + userId + "'";
		return this.executeSQL(sql, false);
	}
	
	
	/**
	 * 每次兑换之后奖品份数减一
	 * @param xmid
	 * @return boolean
	 * @author lidu
	 */
	public boolean updateDhxmFenshuByXmid(int xmid)
	{
		String sql ="update wx_jfdh set FENSHU = FENSHU - 1 where XMID = " + xmid;
		return this.executeSQL(sql, false);
	}
	
	
	/**
	 * 根据openid获取洗脚记录数
	 */
	public int getFBAmount(String openId)
	{
		return this.getCountSql(" WX_FootbathRecord where openId='"+openId+"' order by rid desc", false);
	}
	
	/**
	 * 获取所有邀请码信息
	 * @return 邀请码信息列表
	 * @author lidu
	 * @date 2015.11.3
	 */
	public List getAllScoreCode(){
		return this.findBySql("from ScoreCode order by rid desc", false);
	}
	
	
	/**
	 * 根据ScoreCode更新积分
	 * @param openId
	 * @return
	 * @author lidu
	 * @date 2015.11.3
	 */
	public boolean updateScoreByCode(int rid)
	{
		String sql ="update wx_scorecode set score = score + 1 where rid = " + rid;
		return this.executeSQL(sql, false);
	}
	
	/**
	 * 根据openid查找邀请码信息
	 * @param openId
	 * @return
	 * @author lidu
	 * @date 2015.8.6
	 */
	public List getInviteRecordByUserId(String userId)
	{
		return this.findBySql(" from InvitationRecord where userId='"+userId+"' order by rid desc", false);
	}
	
	/**
	 * 根据xmid查找积分兑换项目信息
	 * @param openId
	 * @return
	 * @author lidu
	 * @date 2015.8.6
	 */
	public List getDhxmByxmid(String xmid)
	{
		return this.findBySql(" from Jfdh where xmid='"+xmid+"' order by rid desc", false);
	}
	
	
	
	
	
	/**
	 * 根据openid查找邀请码信息信息
	 * @param openId
	 * @return
	 * @author lidu
	 * @date 2015.8.6
	 */
	public List getCodeByUserId(String userId)
	{
		return this.findBySql(" from ScoreCode where userId='"+userId+"' order by rid desc", false);
	}
	
	/**
	 * 根据code查找邀请码信息信息
	 * @param openId
	 * @return 
	 * @author lidu
	 * @date 2015.8.6
	 */
	public List getCodeByCode(String code)
	{
		return this.findBySql(" from ScoreCode where invitationCode='"+code+"' order by rid desc", false);
	}
	
	/**
	 * 根据openid查找用户信息
	 * @param openId
	 * @return
	 * @author lidu
	 * @date 2015.8.6
	 */
	public List getUserByOpenId(String openId)
	{
		return this.findBySql(" from UserInfo where openId='"+openId+"' order by rid desc", false);
	}
	
	/**
	 * 获取accesstoken
	 * @return List
	 * @author lidu
	 * @time 2015.8.5
	 */
	public List getAccessToken()
	{
		return this.findBySql(" from AccessToken order by rid desc", false);
	}
	
	
	/**
	 * 更新数据库中的accesstoken
	 * @param accessToken
	 * @param now
	 * @return boolean
	 * @author lidu
	 * @date 2015.8.6
	 */
	public boolean updateAccessToken(String accessToken,String now)
	{
		String sql =" update AccessToken set ACCESSTOKEN = " + "'"+accessToken +"'"+ ", ATTIME = to_date('"+now.substring(0, 19)+"'"+",'yyyy-MM-dd:hh24:mi:ss')";
		return this.executeSQL(sql, false);
	}
	
	/**
	 * 更新数据库中wx_userinfo的用户电话号码
	 * @param userid
	 * @param phoneNumber
	 * @return boolean
	 * @author lidu
	 * @date 2015.8.7
	 */
	public boolean updateUserInfoPhoneNumber(String userid,String phoneNumber)
	{
		String sqlUserinfo = "update wx_UserInfo set PHONENUMBER = '"+phoneNumber+"' where OPENID = '"+userid+"'";
		return (this.executeSQL(sqlUserinfo, false));
	}
	
	
	/**
	 * 更新数据库中zjjl的用户电话号码
	 * @param userid
	 * @param phoneNumber
	 * @return boolean
	 * @author lidu
	 * @date 2015.8.7
	 */
	public boolean updateZjjlPhoneNumber(String userid,String phoneNumber)
	{
		String sqlZjjl = "update Zjjl set TEL = '"+phoneNumber+"' where USERID = '"+userid+"'";
		return (this.executeSQL(sqlZjjl, false));
	}
	
	/**
	 * 获取所有优惠券信息
	 * @return 优惠券信息列表
	 * @author lidu
	 * @date 2015.7.17
	 */
	public List getAllYhqxx(){
		return this.findBySql("from Yhqxx order by rid desc", false);
	}
	
	/**
	 * 获取所有未被抽奖的优惠券信息
	 * @return 未被抽奖的优惠券信息列表
	 * @author lidu
	 * @date 2015.7.17
	 */
	public List getAllYhqxxNotUsed()
	{
		return this.findBySql("from Yhqxx where sfcj = '0' order by rid desc", false);
	}
	
	public boolean updateyhqxxNotUsed(String yhqbm)
	{
		String sql ="update Yhqxx set sfcj = '1' where yhqbm = " + "'"+yhqbm+"'";
		return this.executeSQL(sql, false);
	}
	
	
	
	public List getAllZjjlByUser(String userid){
		return this.findBySql(" from Zjjl where userid='"+userid+"' order by rid desc", false);
	}
	
	public List getAllQgjlByUser(String userid){
		return this.findBySql(" from Qgjl where userid='"+userid+"' order by rid desc", false);
	}
	
	public List showZjjlByUser(String userid){
		String sql ="select c.dbt,z.zjlx,z.zjsj,z.zjbm,z.yhqbm from Cjxm c,Zjjl z where c.xmid= z.xmid and userid= '"+userid +"' order by rid desc";
		return this.findBySql(sql,false);
	}
	
	public List showQgjlByUser(String userid){
		String sql ="select c.dbt,z.qgsj from Qgxm c,Qgjl z where c.xmid= z.xmid and userid= '"+userid +"' order by rid desc";
		return this.findBySql(sql,false);
	}
	
	public List showDhjlByUser(String userid){
		String sql ="select c.dbt,z.dhsj from Jfdh c,Jfdhjl z where c.xmid= z.xmid and userid= '"+userid +"'";
		return this.findBySql(sql,false);
	}
	
	/**
	 * 每次抽奖完之后奖品份数减一
	 * @param xmid
	 * @return boolean
	 * @author lidu
	 */
	public boolean updateFenshuByXmid(int xmid)
	{
		String sql ="update CJXM set FENSHU = FENSHU - 1 where XMID = " + xmid;
		System.out.println("updateFenshuByXmid");
		return this.executeSQL(sql, false);
	}
	
	/**
	 * 每次抽奖完之后奖品份数减一
	 * @param xmid
	 * @return boolean
	 * @author lidu
	 */
	public boolean updateNumberByXmid(int xmid)
	{
		String sql ="update Qgxm set FENSHU = FENSHU - 1 where XMID = " + xmid;
		return this.executeSQL(sql, false);
	}
	
	/**
	 * 根据xmid查找抢购项目
	 * @param xmid
	 * @return
	 * @author lidu
	 * @date 2015.10.19
	 */
	public List getQgxmByXmid(int xmid)
	{
		return this.findBySql(" from Qgxm where xmid='"+xmid+"'", false);
	}
	
	
	/**
	 * 获取按键1的最新记录
	 * @param xmid
	 * @return
	 */
	public List getButton1ClickRecord(String clickdate)
	{
		return this.findBySql(" from Button1 where clickdate='"+clickdate+"' order by rid desc", false);
	}
	
	
	/**
	 * 刷新按键1的click记录
	 * @param xmid
	 * @return
	 */
	public boolean updateButton1(String clickdate)
	{
		String sql =" update wx_button1 set totalclick = totalclick + 1 where clickdate = '"+clickdate+"'";
		return this.executeSQL(sql, false);
	}
	
	
	/**
	 * 获取按键2的最新记录
	 * @param xmid
	 * @return
	 */
	public List getButton2ClickRecord(String clickdate)
	{
		return this.findBySql(" from Button2 where clickdate='"+clickdate+"' order by rid desc", false);
	}
	
	
	/**
	 * 刷新按键2的click记录
	 * @param xmid
	 * @return
	 */
	public boolean updateButton2(String clickdate)
	{
		String sql =" update wx_button2 set totalclick = totalclick + 1 where clickdate = '"+clickdate+"'";
		return this.executeSQL(sql, false);
	}
	
	/**
	 * 获取按键3的最新记录
	 * @param xmid
	 * @return
	 */
	public List getButton3ClickRecord(String clickdate)
	{
		return this.findBySql(" from Button3 where clickdate='"+clickdate+"' order by rid desc", false);
	}
	
	
	/**
	 * 刷新按键3的click记录
	 * @param xmid
	 * @return
	 */
	public boolean updateButton3(String clickdate)
	{
		String sql =" update wx_button3 set totalclick = totalclick + 1 where clickdate = '"+clickdate+"'";
		return this.executeSQL(sql, false);
	}
	
	/**
	 * 获取按键4的最新记录
	 * @param xmid
	 * @return
	 */
	public List getButton4ClickRecord(String clickdate)
	{
		return this.findBySql(" from Button4 where clickdate='"+clickdate+"' order by rid desc", false);
	}
	
	
	/**
	 * 刷新按键4的click记录
	 * @param xmid
	 * @return
	 */
	public boolean updateButton4(String clickdate)
	{
		String sql =" update wx_button4 set totalclick = totalclick + 1 where clickdate = '"+clickdate+"'";
		return this.executeSQL(sql, false);
	}
	
	/**
	 * 获取关键字对应的记录
	 * @param keyword
	 * @return
	 */
	public List getMediaIdByKeyword(String keyword)
	{
		return this.findBySql(" from KeyMaterialMap where keyword='"+keyword+"' order by rid desc", false);
	}
	
	public List getFenshuByXmid(int xmid)
	{
		return this.findBySql(" from Cjxm where xmid='"+xmid+"'", false);
	}
	
	/**
	 * 根据名字和电话号码获取业务记录
	 * @param name
	 * @param tel
	 * @return
	 */
	public List getYwhzByNameAndTel(String tel)
	{
		return this.findBySql(" from Ywhz where kh_tel1 = '" + tel + "' order by kh_createddate" , false);
	}
}
