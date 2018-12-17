package cn.smartcandy.application.a.wxmsg.api;

import cn.smartcandy.application.a.wxmsg.bean.AppKeyInfo;
import cn.smartcandy.application.a.wxmsg.bean.OAuthToken;
import cn.smartcandy.application.a.wxmsg.bean.WeixinPayConfig;
import cn.smartcandy.framework.core.cache.CacheManager;
import cn.smartcandy.framework.core.log.Log;
import cn.smartcandy.framework.utils.http.CHttpUtils;
import net.sf.json.JSONObject;

/**
 * 项目名称：zmjema
 * 类名称：TokenApi  
 * 类描述：调用Token api
 * 创建人：zhoushuyi
 * 创建时间：2016-12-12 上午12:56
 * 修改人：  
 * 修改时间：
 * 修改备注：
 * Company:zmjema & HLS (C) 2016
 * @version 1.0
 */
public class TokenApi {
	
	//获取access_token,access_token是公众号的全局唯一接口调用凭据
	private static final String URL_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	/** 缓存前缀*/
	public static final String WEIXIN_ACCESS_TOKEN_PREFIX = "WEIXIN_ACCESS_TOKEN_PREFIX_";


	/**
	 * 方法描述：获取accessToken，失败则返回null
	 * 
	 * @return
	 */
	public static OAuthToken getAccessToken(String branchSequence){
		OAuthToken accessToken = (OAuthToken)CacheManager.getInstance().get(WEIXIN_ACCESS_TOKEN_PREFIX + branchSequence);
		if(null == accessToken || accessToken.isExprexpired()){
			TokenApi.updateAccessToken(branchSequence);
			accessToken = TokenApi.getAccessToken(branchSequence);
		}
		
		return accessToken;
	}
	
	/**
	 * 方法描述：获取AccessToken
	
	 * @param branchSequence
	 * @return
	 */
    public static boolean updateAccessToken(String branchSequence) {
		AppKeyInfo appKeyInfo = WeixinPayConfig.getInstance().getAppKeyInfo(branchSequence);
		String appID = appKeyInfo.getAppID();
		String appSecret = appKeyInfo.getAppSecret();
		String url = URL_TOKEN.replace("APPID", appID).replace("APPSECRET", appSecret);
		JSONObject jsonObject = CHttpUtils.doGetJson(url);
		Log.logInfo("jsonObject:"+jsonObject);
		if (jsonObject != null && jsonObject.containsKey("access_token")) {
			OAuthToken accessToken = new OAuthToken(jsonObject);
			// 存入缓存
			CacheManager.getInstance().put(accessToken, WEIXIN_ACCESS_TOKEN_PREFIX + branchSequence);

			Log.logInfo("获取AccessToken成功：" + accessToken);
		} else {
			Log.logError("获取AccessToken失败");
			return Boolean.FALSE;

		}

		return Boolean.TRUE;
    }
	 
}
