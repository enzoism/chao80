package cn.smartcandy.common.utils;

import cn.smartcandy.application.a.account.User;

/**
 * @项目名称：zmjema
 * @类名称：TLoginUtils
 * @类描述：第三方登录登录工具
 * @创建人：tangzhifeng
 * @创建时间：2017年11月16日 下午3:51:32
 * @修改人：someOne
 * @修改时间：2017年11月16日 下午3:51:32 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class TLoginUtils {

	// 微信登录
	public static boolean WXLogin(Object object){
		return (null != object && object instanceof User);
	}
	
	// QQ登录
	public static boolean QQLogin(Object object){
		return (null != object && object instanceof User);
	}
	
	
	
}
