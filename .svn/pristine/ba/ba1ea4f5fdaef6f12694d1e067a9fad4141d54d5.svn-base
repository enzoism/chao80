package cn.smartcandy.application.a.wxmsg.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.ParseException;

import cn.smartcandy.application.a.wxmsg.bean.AppKeyInfo;
import cn.smartcandy.application.a.wxmsg.bean.JsApiTicket;
import cn.smartcandy.application.a.wxmsg.bean.OAuth2Token;
import cn.smartcandy.application.a.wxmsg.bean.WeixinPayConfig;
import cn.smartcandy.application.a.wxmsg.bean.WxUserInfo;
import cn.smartcandy.framework.core.cache.CacheManager;
import cn.smartcandy.framework.core.exception.BusinessException;
import cn.smartcandy.framework.core.log.Log;
import cn.smartcandy.framework.utils.http.CHttpUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 项目名称：zmjema
 * 类名称：WxAction  
 * 类描述：接收微信请求action
 * 创建人：zhoushuyi
 * 创建时间：2016-12-15 下午18:40
 * 修改人：  
 * 修改时间：
 * 修改备注：
 * Company:zmjema & HLS (C) 2016
 * @version 1.0
 */
public class WebPageApi {
	
	/**获取Code url */
	private static final String URL_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?"
			+ "appid=APPID&"
			+ "redirect_uri=REDIRECT_URI&"
			+ "response_type=code&"
			+ "scope=SCOPE&"
			+ "state=STATE#wechat_redirect";
	
	/**网页授权Token url */
	private static final String URL_WEBTOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?"
			+ "appid=APPID"
			+ "&secret=SECRET"
			+ "&code=CODE"
			+ "&grant_type=authorization_code";
	
	/**拉取用户信息 url */
	private static final String URL_GETUSER = "https://api.weixin.qq.com/sns/userinfo?"
			+ "access_token=ACCESS_TOKEN"
			+ "&openid=OPENID"
			+ "&lang=zh_CN";
	
	/**获取微信JS-SDK权限验证签名票据 */
	private static final String URL_GETTICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?"
			+ "type=TYPE"
			+ "&access_token=ACCESS_TOKEN";
	
	/**缓存前缀*/
	public static final String WEIXIN_JSAPI_TICKET_PREFIX = "WEIXIN_JSAPI_TICKET_PREFIX_";
	
	/**
	 * 方法描述：获取accessToken，失败则返回null
	 * 
	 * @return
	 */
	public static JsApiTicket getJsApiTicket(String type, String branchSequence, String access_token){
		JsApiTicket jsApiTicket = (JsApiTicket)CacheManager.getInstance().get(WEIXIN_JSAPI_TICKET_PREFIX + branchSequence);
		if(null == jsApiTicket || jsApiTicket.isExprexpired()){
			WebPageApi.updateJsApiTicket(type, branchSequence, access_token);
			jsApiTicket = WebPageApi.getJsApiTicket(type, branchSequence, access_token);
		}
		
		return jsApiTicket;
	}
	
	/**
     * 方法描述：获取微信签名票据，用于生成微信JS-SDK权限验证签名
     * 
     * @param jsapi 支付类型
     * @param branchSequence
     * @param access_token    网页授权接口调用凭证
	 * @throws UnsupportedEncodingException 
     */
    public static JsApiTicket updateJsApiTicket(String type, String branchSequence, String access_token) {
        //拼接请求地址
    	String requestUrl = URL_GETTICKET.replace("TYPE", type).replace("ACCESS_TOKEN", access_token);
        JSONObject jsonObject = CHttpUtils.doGetJson(requestUrl);
        JsApiTicket jsapi_ticket = null;
        try {
			jsonObject = CHttpUtils.doGetJson(requestUrl);
			if(jsonObject != null && jsonObject.containsKey("ticket")){
				jsapi_ticket = new JsApiTicket(jsonObject);
				// 存入缓存
				CacheManager.getInstance().put(jsapi_ticket, WEIXIN_JSAPI_TICKET_PREFIX + branchSequence);
				Log.logDebug("获取ticket成功："+jsonObject.getString("ticket"));
			}
		} catch (ParseException e) {
			Log.logError("获取ticket失败", e);
		}
		
      return jsapi_ticket;
    }
    
	/**
     * 方法描述：生成回调url，这个结果要求用户在微信中打开，即可获得token，并指向redirectUrl,可用于微信登录与微信网页
     * 
     * @param redirectUrl 用户自己设置的回调地址
     * @param state       用户自带参数
     * @return 回调url，用户在微信中打开即可开始授权
	 * @throws UnsupportedEncodingException 
     */
    public static String getOauthPageUrl(String redirectUrl, String state, String branchSequence) throws UnsupportedEncodingException {
    	AppKeyInfo appKeyInfo = WeixinPayConfig.getInstance().getAppKeyInfo(branchSequence);
    	String appID = null;
    	
    	String userState = StringUtils.isBlank(state) ? "STATE" : state;
        redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
        String requestUrl = null;
        if(null != appKeyInfo){
        	appID = appKeyInfo.getAppID();
		}else{
			//appID = MessageUtil.APPID;
		}
        requestUrl = URL_CODE.replace("APPID", appID).replace("REDIRECT_URI", redirectUrl).replace("SCOPE", "snsapi_base").replace("STATE", userState); 
        //requestUrl = URL_CODE.replace("APPID", appID).replace("REDIRECT_URI", redirectUrl).replace("SCOPE", "snsapi_userinfo").replace("STATE", userState); 
        return requestUrl;
    }
    
    /**
     * 方法描述：生成回调url，这个结果要求用户在微信中打开，即可获得token，并指向redirectUrl,可用于微信登录与微信网页,没有关注公众号
     * @param redirectUrl
     * @param state
     * @param branchSequence
     * @throws UnsupportedEncodingException
     * @return String
     * @throws BusinessException
     */
    public static String getOauthPageUrlWithoutAttention(String redirectUrl, String state, String branchSequence) throws UnsupportedEncodingException {
    	AppKeyInfo appKeyInfo = WeixinPayConfig.getInstance().getAppKeyInfo(branchSequence);
    	String appID = null;
    	
    	String userState = StringUtils.isBlank(state) ? "STATE" : state;
        redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
        String requestUrl = null;
        if(null != appKeyInfo){
        	appID = appKeyInfo.getAppID();
		}else{
			//appID = MessageUtil.APPID;
		}
        //requestUrl = URL_CODE.replace("APPID", appID).replace("REDIRECT_URI", redirectUrl).replace("SCOPE", "snsapi_base").replace("STATE", userState); 
        requestUrl = URL_CODE.replace("APPID", appID).replace("REDIRECT_URI", redirectUrl).replace("SCOPE", "snsapi_userinfo").replace("STATE", userState); 
        return requestUrl;
    }
    
	/**
     * 方法描述：获取网页授权凭证
     * @param appId 公众账号的唯一标识
     * @param appSecret 公众账号的密钥
     * @param code
     * @return WeixinAouth2Token
     */
    public static OAuth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
        OAuth2Token oauthToken = null;
        // 拼接请求地址
        String requestUrl = URL_WEBTOKEN.replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code);
        // 获取网页授权凭证
        JSONObject jsonObject = CHttpUtils.doGetJson(requestUrl);
        if (null != jsonObject) {
            try {
            	oauthToken = new OAuth2Token();
            	oauthToken.setAccessToken(jsonObject.getString("access_token"));
            	oauthToken.setExpiresIn(jsonObject.getInt("expires_in"));
            	oauthToken.setRefreshToken(jsonObject.getString("refresh_token"));
            	oauthToken.setOpenId(jsonObject.getString("openid"));
            	oauthToken.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
            	oauthToken = null;
                Object errorCode = jsonObject.get("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                Log.logError("获取网页授权凭证失败,errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}");
            }
        }
        return oauthToken;
    }
	
    /**
     * 方法描述：通过网页授权获取用户信息
     * @param accessToken 网页授权接口调用凭证
     * @param openId 用户标识
     * @return SNSUserInfo
     */
    @SuppressWarnings( { "deprecation", "unchecked" })
    public static WxUserInfo getSNSUserInfo(String accessToken, String openId) {
    	WxUserInfo snsUserInfo = null;
    	String requestUrl = WebPageApi.URL_GETUSER.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 通过网页授权获取用户信息
        JSONObject jsonObject = CHttpUtils.doGetJson(requestUrl);
        
        if (jsonObject != null) {
            try {
                snsUserInfo = new WxUserInfo();
                // 用户的标识
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                // 昵称
                snsUserInfo.setNickname(jsonObject.getString("nickname"));
                // 性别（1是男性，2是女性，0是未知）
                snsUserInfo.setSex(jsonObject.getInt("sex"));
                // 用户所在国家
                snsUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                snsUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                snsUserInfo.setCity(jsonObject.getString("city"));
                // 用户头像
                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
                // 用户特权信息
                snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));
                //判断存在unionid，则取unionid set微信用户信息
               if(jsonObject.containsKey("unionid")){
            	   snsUserInfo.setUnionid(jsonObject.getString("unionid"));
               }
              
            } catch (Exception e) {
                snsUserInfo = null;
                Object errorCode = jsonObject.get("errcode");
                Object errorMsg = jsonObject.get("errmsg");
                Log.logError("获取用户信息失败 errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}",e);
            }
        }
        return snsUserInfo;
    }
	
}
