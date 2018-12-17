package cn.smartcandy.common.common;

/**
 * @项目名称：zmjema
 * @类名称：SessionKeys
 * @类描述：SessionKey值
 * @创建人：tangzhifeng
 * @创建时间：2017年10月15日 下午12:52:49
 * @修改人：someOne
 * @修改时间：2017年10月15日 下午12:52:49 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class SessionKeys {

	// 用户/藏家
	public static final String LOGIN_USER = "LOGIN_USER";					//当前登录的用户
	
	// 关注用户
	public static final String ATTENTION_USERS = "ATTENTION_USERS";			//已经关注的用户

	// 管理员
	public static final String LOGIN_ADMIN = "LOGIN_ADMIN";					//当前登录的用户
	
	// 管理种类
	public static final String ADMIN_CATE = "ADMIN_CATE";					//管理员创建种类

	// 微信userInfo
	public static final String THIRTH_LOGIN_WEIXIN = "THIRTH_LOGIN_WEIXIN";	//微信授权信息
	public static final String THIRTH_LOGIN_QQ = "THIRTH_LOGIN_QQ";			//QQ授权信息
}
