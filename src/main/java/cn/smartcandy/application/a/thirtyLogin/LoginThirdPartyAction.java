package cn.smartcandy.application.a.thirtyLogin;

import java.io.IOException;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;

import cn.smartcandy.application.a.account.User;
import cn.smartcandy.application.a.wxmsg.bean.WxUserInfo;
import cn.smartcandy.application.a.wxmsg.util.MessageUtil;
import cn.smartcandy.common.common.BaseAction;
import cn.smartcandy.common.common.CommonVars;
import cn.smartcandy.common.utils.TMemberUtils;
import cn.smartcandy.framework.core.exception.BusinessException;
import cn.smartcandy.framework.core.log.Log;
import cn.smartcandy.framework.utils.CStringUtils;
import cn.smartcandy.framework.utils.aes.AES;
import cn.smartcandy.framework.utils.aes.AESToolBean;
import cn.smartcandy.framework.web.utils.WebUtils;
import weibo4j.model.WeiboException;

/**
 * @项目名称：zmjema
 * @类名称：LoginThirdPartyAction
 * @类描述：第三方登录接口
 * @创建人：tangzhifeng
 * @创建时间：2017年11月17日 上午9:55:51
 * @修改人：someOne
 * @修改时间：2017年11月17日 上午9:55:51 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class LoginThirdPartyAction  extends BaseAction {

	private static final long serialVersionUID = -1191754121294069714L;

	// QQ登录
	public String loginWithQQ() {
		try {
			String attribute = (String) session.getAttribute("qq_connect_state");
			System.out.println("qq_connect_state:"+attribute);
			
			String authorizeURL = new com.qq.connect.oauth.Oauth().getAuthorizeURL(request);
			response.sendRedirect(authorizeURL);
		} catch (QQConnectException | IOException e) {
			e.printStackTrace();
			throw new BusinessException("QQ授权失败,请重新拉取授权");
		}
		return null;
	}
	// 微信登录
	public String loginWithWeiXin() {
		try {
			if(WebUtils.isWeixin(request)){
				response.sendRedirect("weiXinLogin_login.action");
			}else{
			  	byte[] keyByte = AESToolBean.getKeyByteByKeyFile();
			  	String state = AES.encrypt(session.getId(), keyByte);
			 	String weixinURL = "https://open.weixin.qq.com/connect/qrconnect?appid=wxfd827b5f6bda9866&redirect_uri=http%3A%2F%2Fwww.chao80.com%2FthirtyLogin_realWeixinLogin.action&response_type=code&scope=snsapi_login&state={state}#wechat_redirect";
			 	response.sendRedirect(CStringUtils.format(weixinURL, "[{\"redirect_uri\":\""+ MessageUtil.REDIRECT_URI_SIGNUP +"\" , \"state\":\""+ state +"\"}]"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("微信授权失败,请重新拉取授权");
		}

		return null;
	}
	
	// 微博登录
	public String loginWithWeiBo() throws QQConnectException {
		try {
			String authorizeURL = new weibo4j.Oauth().authorize("code");
			response.sendRedirect(authorizeURL);
		} catch (IOException | WeiboException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// -------------------------------------真实登录接口----------------------------------------//
	
	// 真实微信回调
	public String realWeixinLogin() {
		// 微信授权信息
		System.out.println("---------------------WX真实登录");
		WxUserInfo wxUser = MessageUtil.getWebWxUserInfo(request, response);
		System.out.println("-----------------微信拉取用户的信息："+wxUser);
		if (null == wxUser) {
			throw new BusinessException("微信授权失败,请重新拉取授权");
		}
		// 绑定用户信息
		TMemberUtils.saveWeiXinUserInfo(request, wxUser);
		User bindUser = LoginUtil.getUeabBindUser(wxUser.getUnionid(), wxUser.getOpenId(), CommonVars.PM_WEIXIN, request);
		System.out.println("-------------------------->bindUser:"+bindUser);
		if (TMemberUtils.isUser(bindUser)) {
			System.out.println("---------------->用户已经绑定，数据库查询用户："+bindUser);
			if (bindUser.getUserStatus().equals(User.STATUS_BLACK)) {
				System.out.println("---------------->用户已经被拉黑，暂不能登录!");
				throw new BusinessException("用户已经被拉黑，暂不能登录！");
			}else{
				TMemberUtils.saveUser(request, bindUser);
			}
			System.out.println("---------------->用户已经绑定，直接登录!");
			return "loginSuccess";
		}else{
			return "wxRegister";
		}
	}
	// 真实QQ登录
	public String realQQLogin() {
		try {
			AccessToken accessToken = new com.qq.connect.oauth.Oauth().getAccessTokenByRequest(request);
			System.out.println("QQ拉取授权accessToken："+accessToken);
			if (CStringUtils.isBlank(accessToken.getAccessToken())) {
    			throw new BusinessException("QQ授权失败,请重新拉取授权");
			}
            OpenID openIDObj = new OpenID(accessToken.getAccessToken());
            String openID = openIDObj.getUserOpenID();
            if (CStringUtils.isEmpty(openID)) {
    			throw new BusinessException("QQ授权失败,请重新拉取授权");
			}
            // 模拟微信，用对象存储
            WxUserInfo wxUser = new WxUserInfo();
            wxUser.setOpenId(openID);
	 		System.out.println("QQ拉取授权wxUser："+wxUser);
            // 绑定用户信息
    		TMemberUtils.saveQQUserInfo(request, wxUser);
    		User bindUser = LoginUtil.getUeabBindUser(null,openID, CommonVars.PM_QQ, request);
    		if (TMemberUtils.isUser(bindUser)) {
    			System.out.println("---------------->用户已经绑定，数据库查询用户："+bindUser);
    			if (bindUser.getUserStatus().equals(User.STATUS_BLACK)) {
    				System.out.println("---------------->用户已经被拉黑，暂不能登录!");
    				throw new BusinessException("用户已经被拉黑，暂不能登录！");
    			}else{
    				TMemberUtils.saveUser(request, bindUser);
    			}
    			System.out.println("---------------->用户已经绑定，直接登录!");
    			return "loginSuccess";
    		}else{
    			return "qqRegister";
    		}
		}catch (Exception e) {
			System.out.println("QQ链接失败e:"+e);
			Log.logError("QQ登录失败", e);
			throw new BusinessException("QQ授权失败,请重新拉取授权");
		}
	}
	
}
