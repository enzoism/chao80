package cn.smartcandy.application.a.wxmsg.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.smartcandy.application.a.wxmsg.api.WebPageApi;
import cn.smartcandy.application.a.wxmsg.bean.AppKeyInfo;
import cn.smartcandy.application.a.wxmsg.bean.OAuth2Token;
import cn.smartcandy.application.a.wxmsg.bean.WeixinPayConfig;
import cn.smartcandy.application.a.wxmsg.bean.WxUserInfo;
import cn.smartcandy.framework.conf.SysConfig;
import cn.smartcandy.framework.core.exception.BusinessException;
import cn.smartcandy.framework.core.log.Log;
import cn.smartcandy.framework.utils.CStringUtils;
import cn.smartcandy.framework.web.utils.WebUtils;

/**
 * 项目名称：zmjema
 * 类名称：MessageUtil
 * 类描述：微信工具类
 * 创建人：zhoushuyi
 * 创建时间：2016-12-12 上午12:56
 * 修改人：
 * 修改时间：
 * 修改备注：
 * Company:zmjema & HLS (C) 2016
 * @version 1.0
 */
public class MessageUtil {
	//public static String branchSeq = StringUtils.trimToEmpty(ServletActionContext.getRequest().getParameter("branchSequence"));
	//闻壹信息 平台
	public static final String APPID = SysConfig.getInstance().get("WEIXIN_APPID");
	public static final String APPSECRET = SysConfig.getInstance().get("WEIXIN_APPSECRET");
//	//微信开放平台AppID
//	public static final String OPEN_APPID = SysConfig.getInstance().get("WEIXIN_OPEN_APPID");
//	//微信开放平台AppSecret
//	public static final String OPEN_APPSECRET = SysConfig.getInstance().get("WEIXIN_OPEN_APPSECRET");
//	//回调地址
//	public static final String REDIRECT_URI = SysConfig.getInstance().get("WEIXIN_REDIRECT_URI");
//	//回调地址 注册用
//	public static final String REDIRECT_URI_SIGNUP = SysConfig.getInstance().get("WEIXIN_REDIRECT_URI_SIGNUP");
	//微信开放平台AppID
	public static final String OPEN_APPID = "wxfd827b5f6bda9866";
	//微信开放平台AppSecret
	public static final String OPEN_APPSECRET = "8171ca3deed1a1a314215dac596e52ed";
	//回调地址
	public static final String REDIRECT_URI = "http%3A%2F%2Fwww.chao80.com%2FthirtyLogin_realWeixinLogin.action";
	//回调地址 注册用
	public static final String REDIRECT_URI_SIGNUP = "http%3A%2F%2Fwww.chao80.com%2FthirtyLogin_realWeixinLogin.action";
	//微信token
	public static final String TOKEN = SysConfig.getInstance().get("WEIXIN_TOKEN");
	//微信服务器URL 用于公众号回调页面
	public static final String WXURL = SysConfig.getInstance().get("WEIXIN_WXURL");
	/**消息类型，文本*/
	public static final String TEXT        = "text";
	/**消息类型，图片 */
	public static final String IMAGE       = "image";
	/**消息类型，链接 */
	public static final String LINK        = "link";
	/**消息类型，地理位置 */
	public static final String LOCATION    = "location";
	/**消息类型，语音*/
	public static final String VOICE       = "voice";
	/**消息类型，视频 */
	public static final String VIDEO       = "video";
	/**消息类型，小视频*/
	public static final String SHORT_VIDEO = "shortvideo";
	/**消息类型， 事件*/
	public static final String EVENT       = "event";
	
	/**事件类型,上传地理位置  */
	public static final String EVENT_LOCATION = "LOCATION";
	/**事件类型,订阅  */
	public static final String EVENT_SUBSCRIBE = "subscribe";
	/**事件类型,取消订阅  */
	public static final String EVENT_UNSUBSCRIBE = "unsubscribe";
	/**事件类型，扫码  */
	public static final String EVENT_SCAN  = "SCAN";
	/**事件类型，点击菜单事件，(推送消息)  */
	public static final String EVENT_CLICK = "CLICK";
	/**事件类型，点击菜单事件，(进入网页)  */
	public static final String EVENT_VIEW  = "VIEW";
	

	/**
	 * 方法描述：获取网页微信用户信息，用于网页登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public static WxUserInfo getWebWxUserInfo(HttpServletRequest request ,HttpServletResponse response){
		OAuth2Token oAuth2Token = null;
		WxUserInfo snsUserInfo = null;
		response.setContentType("utf-8");
		//换取网页授权access_token的票据
		String code = request.getParameter("code");
		System.out.println("----------------CODE:"+code);
		//获取网页授权token以及openID
		if(null != code){
			oAuth2Token = WebPageApi.getOauth2AccessToken(MessageUtil.OPEN_APPID, MessageUtil.OPEN_APPSECRET, code);
			System.out.println("----------------oAuth2Token:"+oAuth2Token);

		}
		if(null != oAuth2Token) {
			Log.logInfo("微信网页登录，用户授权成功");
			snsUserInfo = WebPageApi.getSNSUserInfo(oAuth2Token.getAccessToken(), oAuth2Token.getOpenId());
		}else{
			Log.logInfo("微信网页登录，用户授权失败");
			throw new BusinessException("微信网页登录，用户授权失败");
		}
		return snsUserInfo;
	}

	/**
	 * 方法描述：获取微信用户信息，用于公众号
	 * @param request
	 * @param response
	 * @return
	 */
	public static WxUserInfo getWxUserInfo(HttpServletRequest request, HttpServletResponse response){

//		OAuth2Token oAuth2Token = TMemberUtils.getOAuth2Token(request.getSession());
		WxUserInfo snsUserInfo = null;
//		
//		//获取微信用户信息
//		if(null != oAuth2Token) {
//			Log.logInfo("微信公众号"+oAuth2Token.getOpenId()+"，用户授权成功");
//			snsUserInfo = WebPageApi.getSNSUserInfo(oAuth2Token.getAccessToken(), oAuth2Token.getOpenId());
//		}else{
//			Log.logInfo("获取微信公众信息失败");
//			throw new BusinessException("获取微信公众信息失败");
//		}
		
		return snsUserInfo;
	}
	
	/**
	 * 方法描述：获取微信用户信息，用于公众号
	 * @param request
	 * @param response
	 * @param session
	 * @return OAuth2Token
	 * @throws BusinessException
	 */
	public static OAuth2Token getWxOpenID(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		response.setContentType("utf-8");
		String branchSequence = (String) session.getAttribute("branchSequence");
		AppKeyInfo appKeyInfo = WeixinPayConfig.getInstance().getAppKeyInfo(branchSequence);
    	String appID = appKeyInfo.getAppID();
    	String appSecret = appKeyInfo.getAppSecret();
		String code = request.getParameter("code");
		String state = "1";
		String redirectUrl = null;
		OAuth2Token oAuth2Token = null;

		try {
			if (CStringUtils.isNotEmpty(code)) {
				// 获取微信的辨识标志
				oAuth2Token = WebPageApi.getOauth2AccessToken(appID, appSecret, code);
				//WxUserInfo webWxUserInfo = WebPageApi.getSNSUserInfo(oAuth2Token.getAccessToken(), oAuth2Token.getOpenId());
				//TMemberUtils.handleWithAuthToken(branchSequence,oAuth2Token, webWxUserInfo, session);		
				// 初始化用户的绑定信息
				//TMemberUtils.prepareWXBindMsg(branchSequence, request, response, session);
			} else {
				if (WebUtils.isAjaxRequest(request)) {
					redirectUrl = WebUtils.getHeaderReferer(request);
					response.sendRedirect(redirectUrl);
					Log.logInfo("微信重定向的请求是Ajax:" + redirectUrl);	
				}else{
					String URL = request.getRequestURL().toString();
					URL = CStringUtils.isEmpty(branchSequence) ? URL : URL + "?branchSequence=" + branchSequence;
					redirectUrl = WebPageApi.getOauthPageUrl(getRedirectUrlAppendParam(request,URL), state, branchSequence);
					response.sendRedirect(redirectUrl);
					Log.logInfo("微信重定向URL:" + redirectUrl);				
				}
			}
		}catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return oAuth2Token;
	}
	public static OAuth2Token getWxOpenID(String branchSequence, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		response.setContentType("utf-8");
		AppKeyInfo appKeyInfo = WeixinPayConfig.getInstance().getAppKeyInfo(branchSequence);
    	String appID = appKeyInfo.getAppID();
    	String appSecret = appKeyInfo.getAppSecret();
		String code = request.getParameter("code");
		String state = "1";
		String redirectUrl = null;
		OAuth2Token oAuth2Token = null;

		try {
			if (CStringUtils.isNotEmpty(code)) {
				// 获取微信的辨识标志
				Log.logInfo("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$获取到Code之后开始将数据缓存到Session");
				oAuth2Token = WebPageApi.getOauth2AccessToken(appID, appSecret, code);
//				WxUserInfo webWxUserInfo = WebPageApi.getSNSUserInfo(oAuth2Token.getAccessToken(), oAuth2Token.getOpenId());
//				Log.logInfo("微信请求是webWxUserInfo:" + webWxUserInfo);	
//				TMemberUtils.handleWithAuthToken(branchSequence,oAuth2Token, webWxUserInfo, session);
//				// 初始化用户的绑定信息
//				TMemberUtils.prepareWXBindMsg(branchSequence, request, response, session);
			} else {
				String URL = request.getRequestURL().toString();
				URL = CStringUtils.isEmpty(branchSequence) ? URL : URL + "?branchSequence=" + branchSequence;
				redirectUrl = WebPageApi.getOauthPageUrl(getRedirectUrlAppendParam(request,URL), state, branchSequence);
				response.sendRedirect(redirectUrl);
				Log.logInfo("######################################################微信重定向URL获取Code:" + redirectUrl);
			}
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return oAuth2Token;
	}

	/**
	 * 方法描述：将请求的参数拼接在参数后面再次返回
	 * @param request
	 * @return
	 * @return String
	 * @throws BusinessException
	 */
	private static String getRedirectUrlAppendParam(HttpServletRequest request,final String URL){
		@SuppressWarnings("rawtypes")
		Enumeration enu=request.getParameterNames();  
		String requestURL = URL;
		while(enu.hasMoreElements()){ 
			String paraName=(String)enu.nextElement();  
			if (requestURL.indexOf("?")>0) {
				requestURL += "&"+paraName+"="+request.getParameter(paraName);
				Log.logInfo("已经有参数了，将参数直接拼接后面");
			}else{
				requestURL += "?"+paraName+"="+request.getParameter(paraName);
				Log.logInfo("还没有参数，将参数直接拼接后面");
			}
		}
		//System.out.println("-------------------》拼接后的requestURL: "+requestURL);  
		return requestURL;
				
	}

	/**
	 * 方法描述：是不是JsonAction请求
	 * @param request
	 * @return boolean
	 * @throws BusinessException
	 */
	public static boolean isJsonActionRequest(HttpServletRequest request) {
		String URL = request.getRequestURL().toString();
		return URL.indexOf("/jsonAction")>0;
	}

	
}



