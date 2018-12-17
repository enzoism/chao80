package cn.smartcandy.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.smartcandy.application.a.account.Admin;
import cn.smartcandy.application.a.account.User;
import cn.smartcandy.application.a.wxmsg.bean.WxUserInfo;
import cn.smartcandy.application.cms.release.ReleaseBiz;
import cn.smartcandy.application.cms.release.ReleaseCategary;
import cn.smartcandy.common.common.SessionKeys;
import cn.smartcandy.framework.core.exception.BusinessException;
import cn.smartcandy.framework.core.log.Log;
import cn.smartcandy.framework.utils.CStringUtils;
import net.sf.json.JSONArray;

public class TMemberUtils {
	//private static CacheManager cacheManager = CacheManager.getInstance();
	private static String cacheCateList = null;
	private static int cacheNewsShowType = -1;

	/**
	 * 方法描述：是不是用户
	 * @param object
	 * @return boolean
	 * @throws BusinessException
	 */
	public static boolean isUser(Object object){
		return (null != object && object instanceof User);
	}
	
	/**
	 * 方法描述：是不是管理员
	 * @param object
	 * @return boolean
	 * @throws BusinessException
	 */
	public static boolean isAdmin(Object object){
		Admin admin = (Admin) object;
		return null != object && object instanceof Admin && Admin.STATUS_NORMAL.equals(admin.getAdminStatus());
	}
	
	/**
	 * 方法描述：是不是管理员
	 * @param object
	 * @return boolean
	 * @throws BusinessException
	 */
	public static boolean isSuperAdmin(Object object){
		Admin admin = (Admin) object;
		return TMemberUtils.isAdmin(object) && Admin.LEVEL_SUPER.equals(admin.getAdminLevel());
	}
	
	// 将登陆用户保存
	public static void saveUser(HttpServletRequest request,User user){
		HttpSession session = request.getSession();
		session.setAttribute(SessionKeys.LOGIN_USER, user);
	}
	// 获取session数据，不加判断
	public static Object getUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		Object attribute = session.getAttribute(SessionKeys.LOGIN_USER);
		return attribute;
	}
	// 将登陆管理员保存
	public static void saveAdmin(HttpServletRequest request,Admin admin){
		HttpSession session = request.getSession();
		session.setAttribute(SessionKeys.LOGIN_ADMIN, admin);
	}
	// 获取session数据，不加判断
	public static Object getAdmin(HttpServletRequest request){
		HttpSession session = request.getSession();
		Object attribute = session.getAttribute(SessionKeys.LOGIN_ADMIN);
		return attribute;
	}
	// 缓存种类
	public static void saveCategaryList(HttpServletRequest request,List<ReleaseCategary> categaryList){
		JSONArray fromObject = JSONArray.fromObject(categaryList);
		String cateList = fromObject.toString();
//		cacheManager.put(SessionKeys.ADMIN_CATE, cateList);
//		System.out.println("cateList1:"+cateList);
//		System.out.println("cateList2:"+cacheManager.get(SessionKeys.ADMIN_CATE));
		TMemberUtils.cacheCateList = cateList;
	}

	// 获取用户种类
	public static List<ReleaseCategary> getCategaryList(HttpServletRequest request){
//		List<ReleaseCategary> categaryList = null;
//		String cateStr = (String) cacheManager.get(SessionKeys.ADMIN_CATE);
//		System.out.println("---------------------cacheManager："+cateStr);
//		if (CStringUtils.isEmpty(cateStr)) {
//			Map<String, String> param = new HashMap<>();
//			categaryList = new ReleaseBiz().queryCategaryList(param,request);
//			TMemberUtils.saveCategaryList(request, categaryList);
//		}else{
//			categaryList = TStringUtils.turnStringToClassList(cateStr, ReleaseCategary.class);
//		}
		List<ReleaseCategary> categaryList = null;
		String cateStr = TMemberUtils.cacheCateList ;
		if (CStringUtils.isEmpty(cateStr)) {
			Map<String, String> param = new HashMap<>();
			categaryList = new ReleaseBiz().queryCategaryList(param,request);
			TMemberUtils.saveCategaryList(request, categaryList);
			System.out.println("------------------------数据库查询");
		}else{
			categaryList = TStringUtils.turnStringToClassList(cateStr, ReleaseCategary.class);
			System.out.println("------------------------缓存中取");
		}
		return categaryList;
	}
	// 查询用户种类cateSequenceList
	public static List<String> getCategarySequenceList(HttpServletRequest request){
		List<ReleaseCategary> categaryList = TMemberUtils.getCategaryList(request);
		List<String> cateList = new ArrayList<String>();
		for (int i = 0;null!=categaryList && i < categaryList.size(); i++) {
			cateList.add(categaryList.get(i).getCateSequence());
		}
		return cateList;
	}
	
	// 缓存新闻展示类型
	public static void saveNewsShowType(HttpServletRequest request,int showType){
		TMemberUtils.cacheNewsShowType = showType;
	}

	// 获取用户种类
	public static int getNewsShowType(HttpServletRequest request){
		int showType = TMemberUtils.cacheNewsShowType ;
		if (showType == -1) {
			Map<String, String> param = new HashMap<>();
			showType = new ReleaseBiz().queryNewsShowType(param);
			TMemberUtils.saveNewsShowType(request, showType);
			System.out.println("------------------------数据库查询");
		}
		return showType;
	}

	
	// 获取CateName
	public static String getCategaryName(HttpServletRequest request,String cateSequence){
		// 获取缓存信息
		List<ReleaseCategary> categaryList = TMemberUtils.getCategaryList(request);
		String cateName = null;
		for (int i = 0; i < categaryList.size(); i++) {
			ReleaseCategary categary = categaryList.get(i);
			if (CStringUtils.equals(categary.getCateSequence(), categary.getCateSequence())) {
				cateName = categary.getCateName();
				break;
			}
		}
		return cateName;
	}

	/**
	 * 方法描述：注销登录，清除信息
	 * @param request
	 * @param user
	 * @return void
	 * @throws BusinessException
	 */
	public static void cleanUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute(SessionKeys.LOGIN_USER);
		session.removeAttribute(SessionKeys.ATTENTION_USERS);
		session.invalidate();
	}
	
	public static void cleanAdmin(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute(SessionKeys.LOGIN_ADMIN);
		session.removeAttribute(SessionKeys.ATTENTION_USERS);
		session.invalidate();
	}
	/**
	 * 方法描述  比对用户信息，判断是否关注过
	 * @param userList
	 * @return boolean
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	public static List<User>  filterAttentionUserList(HttpServletRequest request, List<User> usrList){
		HttpSession session = request.getSession();
		List<String> usrSeqList = (List<String>) session.getAttribute(SessionKeys.ATTENTION_USERS);
		User user = null;
		for (int i = 0; null!=usrList && i<usrList.size(); i++) {
			user = usrList.get(i);
			if (usrSeqList.contains(user.getUserSequence())) {
				usrList.get(i).setHasAttention(Boolean.TRUE);
			}else{
				usrList.get(i).setHasAttention(Boolean.FALSE);
			}
		}
		return usrList;
	}
	// 缓存微信授权信息
	public static void saveWeiXinUserInfo(HttpServletRequest request,WxUserInfo userInfo){
		Log.logInfo("缓存微信授权信息:"+userInfo);
		HttpSession session = request.getSession();
		session.setAttribute(SessionKeys.THIRTH_LOGIN_WEIXIN, userInfo);
	}
	// 获取微信授权信息
	public static WxUserInfo getWeiXinUserInfo(HttpServletRequest request){
		HttpSession session = request.getSession();
		WxUserInfo userInfo = (WxUserInfo) session.getAttribute(SessionKeys.THIRTH_LOGIN_WEIXIN);
		Log.logInfo("------------------>微信注册userInfo："+userInfo);
		if (null == userInfo) {
			throw new BusinessException("微信授权信息获取失败！");
		}
		if (CStringUtils.isEmpty(userInfo.getOpenId()) || CStringUtils.isEmpty(userInfo.getUnionid())) {
			throw new BusinessException("微信授权信息获取失败！");
		}
		return userInfo;
	}
	
	// 缓存QQ授权信息
	public static void saveQQUserInfo(HttpServletRequest request,WxUserInfo userInfo){
		Log.logInfo("缓存QQ授权信息:"+userInfo);
		HttpSession session = request.getSession();
		session.setAttribute(SessionKeys.THIRTH_LOGIN_QQ, userInfo);
	}
	// 获取QQ授权信息
	public static WxUserInfo getQQUserInfo(HttpServletRequest request){
		HttpSession session = request.getSession();
		WxUserInfo userInfo = (WxUserInfo) session.getAttribute(SessionKeys.THIRTH_LOGIN_QQ);
		Log.logInfo("------------------>QQ注册userInfo："+userInfo);
		if (null == userInfo) {
			throw new BusinessException("QQ授权信息获取失败！");
		}
		if (CStringUtils.isEmpty(userInfo.getOpenId())) {
			throw new BusinessException("QQ授权信息获取失败！");
		}
		return userInfo;
	}
	
}