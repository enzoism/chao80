package cn.smartcandy.application.a.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;

import cn.smartcandy.application.a.attention.AttentionDao;
import cn.smartcandy.application.a.collection.Collection;
import cn.smartcandy.application.a.collection.CollectionDao;
import cn.smartcandy.application.a.thirtyLogin.LoginUeab;
import cn.smartcandy.application.a.thirtyLogin.LoginUeabDao;
import cn.smartcandy.application.a.wxmsg.bean.WxUserInfo;
import cn.smartcandy.application.cms.release.ReleaseCategary;
import cn.smartcandy.common.common.BaseBiz;
import cn.smartcandy.common.common.CommonVars;
import cn.smartcandy.common.utils.TMemberUtils;
import cn.smartcandy.common.utils.TStringUtils;
import cn.smartcandy.framework.biz.workdate.WorkDate;
import cn.smartcandy.framework.core.data.DataTable;
import cn.smartcandy.framework.core.db.mybatis.MyBatisDBAccess;
import cn.smartcandy.framework.core.exception.BusinessException;
import cn.smartcandy.framework.core.exception.UserNotLoginException;
import cn.smartcandy.framework.utils.CStringUtils;
import cn.smartcandy.framework.utils.MD5Utils;

/**
 * @项目名称：zmjema
 * @类名称：AccountBiz
 * @类描述：账户注册绑定
 * @创建人：tangzhifeng
 * @创建时间：2017年10月13日 下午6:21:33
 * @修改人：someOne
 * @修改时间：2017年10月13日 下午6:21:33 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class AccountBiz extends BaseBiz{
	
	private  AccountDao accountDao = new AccountDao();
	private  CollectionDao collectionDao = new CollectionDao();
	private  AttentionDao attentionDao = new AttentionDao();
	private LoginUeabDao ueabDao = new LoginUeabDao();

	// 注册用户 DONE
	public User registerAccount(Map<String, String> param,HttpServletRequest request) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
		
			// 用户注册信息
			String ueabType = param.get("ueabtype");
			String dataTime = WorkDate.getSystemTime();
	        String userEmail = param.get("userEmail");
	        String userPhone = param.get("userPhone");
	        // 验证数据
			if (CStringUtils.isEmpty(userEmail) && CStringUtils.isEmpty(userPhone)) {
				throw new BusinessException("请输入有效邮箱或者电话");
			}
			
			// 查询是否绑定
			Map<String, String> map = new HashMap<>();
			map.put("userPhone", userPhone);
			map.put("userEmail", userEmail);
			List<User> userRegistList = accountDao.selectUserList(map, dbAccess);
			if (!CollectionUtils.isEmpty(userRegistList) && CStringUtils.isNotEmpty(userEmail)) {
				throw new BusinessException("该邮箱已经注册");
			}else if (!CollectionUtils.isEmpty(userRegistList) && CStringUtils.isNotEmpty(userPhone)) {
				throw new BusinessException("该手机已经注册");
			}
			
			// 开始注册绑定
			Map<String, String> usrMap = this.prepareUserRegisterMsg(dataTime, param, dbAccess);
			accountDao.insertUserAccount(usrMap, dbAccess);
			
			// 查询用户
			User registerUser = accountDao.selectUserAccount(usrMap, dbAccess);
			// TODO  删除
			//ueabType = CommonVars.PM_WEIXIN;
			// 第三方绑定
			if (CStringUtils.equals(ueabType, CommonVars.PM_WEIXIN)|| CStringUtils.equals(ueabType, CommonVars.PM_QQ)) {
				// 查询微信绑定
				Map<String, String> bindCount = this.prepareUeabBindCount(ueabType, dbAccess, request);
				int count = ueabDao.selectBindCount(bindCount, dbAccess);
				if (count>0) {
					throw new BusinessException("当前微信已经绑定账户，请勿重复绑定！");
				}
				String userSeq = registerUser.getUserSequence();
				Map<String, String> ueabMap = this.prepareUeabBindMsg(dataTime,userSeq,ueabType,dbAccess, request);
				ueabDao.inserBindMsg(ueabMap, dbAccess);
			}else if (CStringUtils.isNotEmpty(ueabType)) {
				throw new BusinessException("绑定类型异常!");
			}

			TMemberUtils.saveUser(request, registerUser);
			dbAccess.commitTransction();
			return registerUser;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("注册账户失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 准备用户注册信息
	private Map<String,String> prepareUserRegisterMsg(String dataTime,Map<String,String> param, MyBatisDBAccess dbAccess){
		String userSequence = dbAccess.getSeqID("a_user");
		String userPwd = param.get("userPwd");
		param.put("userSequence", userSequence);
		param.put("userImageURL", "/default/icon.png");
		param.put("userRemark", User.REMARK_REGISTER);
		param.put("userSex", User.SEX_M);
		param.put("userCreatDate", dataTime);
		param.put("userPwd", MD5Utils.md5Encode(userPwd));
		param.put("verfyID", User.VERFY_USER);
		param.put("fansCount", String.valueOf(0));
		param.put("likeCount", String.valueOf(0));
		param.put("attentCount", String.valueOf(0));
		param.put("userStatus", User.STATUS_NORMAL);
		param.put("adminStatus", User.STATUS_NULL);
		
		return param;
	}
	
	// 准备第三方绑定信息
	private Map<String,String> prepareUeabBindMsg(String dataTime,String userSequence,String ueabType, MyBatisDBAccess dbAccess,HttpServletRequest request){
		WxUserInfo userInfo = null;
		Map<String,	String> ueabMap = new HashMap<String,String>();
		String ueabSequence = dbAccess.getSeqID("a_ueab");
		if (CStringUtils.equals(ueabType, CommonVars.PM_WEIXIN)) {
			userInfo = TMemberUtils.getWeiXinUserInfo(request);
			ueabMap.put("ueabType", CommonVars.PM_WEIXIN);
		}else if (CStringUtils.equals(ueabType, CommonVars.PM_QQ)) {
			userInfo = TMemberUtils.getQQUserInfo(request);
			ueabMap.put("ueabType", CommonVars.PM_QQ);
		}else{
			throw new BusinessException("绑定类型异常!");
		}
		ueabMap.put("openID", userInfo.getOpenId());
		ueabMap.put("unionID", userInfo.getUnionid());
		ueabMap.put("ueabBindDate",dataTime);
		ueabMap.put("ueabSequence",ueabSequence);
		ueabMap.put("userSequence", userSequence);
		ueabMap.put("ueabStatus", LoginUeab.STATUS_NORMAL);
		return ueabMap;
	}
	// 查询第三方绑定信息
	private Map<String,String> prepareUeabBindCount(String ueabType, MyBatisDBAccess dbAccess,HttpServletRequest request){
		WxUserInfo userInfo = null;
		Map<String,	String> ueabMap = new HashMap<String,String>();
		if (CStringUtils.equals(ueabType, CommonVars.PM_WEIXIN)) {
			userInfo = TMemberUtils.getWeiXinUserInfo(request);
			ueabMap.put("ueabType", CommonVars.PM_WEIXIN);
		}else if (CStringUtils.equals(ueabType, CommonVars.PM_QQ)) {
			userInfo = TMemberUtils.getQQUserInfo(request);
			ueabMap.put("ueabType", CommonVars.PM_QQ);
		}else{
			throw new BusinessException("绑定类型异常!");
		}
		ueabMap.put("openID", userInfo.getOpenId());
		ueabMap.put("unionID", userInfo.getUnionid());
		ueabMap.put("ueabStatus", LoginUeab.STATUS_NORMAL);
		return ueabMap;
	}
	
	// 登录用户(☆) DONE
	public User loginAccount(Map<String, String> param, HttpServletRequest request) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		User loginUser = null;
		try {
			dbAccess.startTransction();
	        String userEmail = param.get("userEmail");
	        String userPhone = param.get("userPhone");
	        String userPwd = param.get("userPwd");
	        String userName = param.get("userName");
	        // 验证数据
			if (CStringUtils.isEmpty(userEmail) && CStringUtils.isEmpty(userPhone) && CStringUtils.isEmpty(userName)) {
				throw new BusinessException("请输入有效邮箱或者电话");
			}
			// 查询注册用户
			param.put("userPwd", MD5Utils.md5Encode(userPwd));
			loginUser = accountDao.selectUserAccount(param, dbAccess);
			if (!TMemberUtils.isUser(loginUser)) {
				throw new BusinessException("用户密码错误，或者用户不存在！");
			}else{
				// 用户被拉黑
				if (loginUser.getUserStatus().equals(User.STATUS_BLACK)) {
					throw new BusinessException("用户已经被拉黑，暂不能登录！");
				}
			}
			// 第三方账户绑定
			String ueabType = param.get("ueabtype");
			// TODO  删除
			//ueabType = CommonVars.PM_WEIXIN;
			System.out.println("------------>第三方绑定ueabType："+ueabType);
			if (CStringUtils.equals(ueabType, CommonVars.PM_WEIXIN)|| CStringUtils.equals(ueabType, CommonVars.PM_QQ)) {
				// 查询微信绑定
				Map<String, String> bindCount = this.prepareUeabBindCount(ueabType, dbAccess, request);
				int count = ueabDao.selectBindCount(bindCount, dbAccess);
				if (count>0) {
					throw new BusinessException("当前微信已经绑定账户，请勿重复绑定！");
				}
				String dataTime = WorkDate.getSystemTime();
				System.out.println("------------>开始第三方绑定");
				Map<String, String> ueabMap= this.prepareUeabBindMsg(dataTime, loginUser.getUserSequence(), ueabType, dbAccess, request);
				ueabDao.inserBindMsg(ueabMap, dbAccess);
			}else if (CStringUtils.isNotEmpty(ueabType)) {
				throw new BusinessException("绑定类型异常!");
			}
			dbAccess.commitTransction();
			return loginUser;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("注册账户失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	
	// 查询登录用户信息(☆) DONE
	public User currentUserMsg(String userSequence) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		User loginUser = null;
		try {
			dbAccess.startTransction();
			Map<String, String> map = new HashMap<String, String>();
			map.put("userSequence", userSequence);
			loginUser = accountDao.selectUserAccount(map, dbAccess);
			return loginUser;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询账户失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 查询用户信息(只查询自己) DONE
	public User queryUserDetailMsg(Map<String, String> param, User user) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			// 查询用户信息
			User userMsg = accountDao.selectUserAccount(param, dbAccess);
			// 查询是否关注
			if (TMemberUtils.isUser(user)) {
				int count = queryUserAttentionOrNot(param,user, dbAccess);
				if (count>0) {
					userMsg.setHasAttention(Boolean.TRUE);
				}	
			}
			return user;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询用户失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}

	// 查询用户账户(☆)
	public User queryUserAccount(Map<String, String> param,HttpServletRequest request, User user) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String userSequence = param.get("userSequence");
			User userMsg = null;
			if (CStringUtils.isNotEmpty(param.get("userSequence")) && TMemberUtils.isUser(user)) {
				param.put("userSequence", userSequence);
				userMsg = this.queryUserAttentionAccount(param, user);
			}else if (CStringUtils.isNotEmpty(param.get("userSequence"))) {
				param.put("userSequence", userSequence);
				userMsg = accountDao.selectUserAccount(param, dbAccess);
			}else if (TMemberUtils.isUser(user)) {
				param.put("userSequence", user.getUserSequence());
				userMsg = accountDao.selectUserAccount(param, dbAccess);
			}else{
				throw new UserNotLoginException("会话超时，请重新登录！");
			}
			return userMsg;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询用户失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	
	// 查询用户账户,并查询是否关注(☆)
	public User queryUserAttentionAccount(Map<String, String> param, User user) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			// 查询用户信息
			User userMsg = accountDao.selectUserAccount(param, dbAccess);
			// 查询是否关注
			if (TMemberUtils.isUser(user)) {
				int count = queryUserAttentionOrNot(param,user, dbAccess);
				if (count>0) {
					userMsg.setHasAttention(Boolean.TRUE);
				}	
			}
			return userMsg;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询用户失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	
	// 查询用户是否被关注
	public int queryUserAttentionOrNot(Map<String, String> param, User user, MyBatisDBAccess dbAccess2) throws BusinessException {

		String relUserSequence = param.get("userSequence");
		if (CStringUtils.isEmpty(relUserSequence)) {
			throw new BusinessException("参数不合法");
		}
		Map<String, String> attParam = new HashMap<String, String>();
		attParam.put("userSequence", user.getUserSequence());
		attParam.put("relUserSequence", relUserSequence);
		int count = new AttentionDao().selectUserAttentionCount(attParam, dbAccess2);

		return count;
	}
	// 查询用户是否被关注
	public int queryUserAttentionOrNot(Map<String, String> param, MyBatisDBAccess dbAccess2) throws BusinessException {

		String relUserSequence = param.get("relUserSequence");
		String userSequence = param.get("userSequence");

		if (CStringUtils.isEmpty(relUserSequence) || CStringUtils.isEmpty(userSequence)) {
			throw new BusinessException("参数不合法");
		}
		Map<String, String> attParam = new HashMap<String, String>();
		attParam.put("userSequence", userSequence);
		attParam.put("relUserSequence", relUserSequence);
		int count = new AttentionDao().selectUserAttentionCount(attParam, dbAccess2);

		return count;
	}
	
	// 查询用户
	public DataTable queryUserDataList(Map<String, String> param,int pageNo, int pageSize,Admin admin) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			
			String userStatus = param.get("userStatus");
			userStatus= CStringUtils.equals(User.STATUS_BLACK, userStatus)?User.STATUS_BLACK:User.STATUS_NORMAL;
			param.put("userStatus", userStatus);
			param.put("verfyID", User.VERFY_USER);
			
			DataTable dataTable = accountDao.selectUserDataList(param, pageNo, pageSize, dbAccess);
			return dataTable;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询用户失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	
	// 查询藏家
	public DataTable queryCollectorDataList(Map<String, String> param,int pageNo, int pageSize,Admin admin) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String adminStatus = param.get("adminStatus");
			adminStatus= CStringUtils.equals(User.STATUS_BLACK, adminStatus)?User.STATUS_BLACK:User.STATUS_NORMAL;
			if (CStringUtils.equals(User.STATUS_BLACK, adminStatus)) {
				param.put("verfyID", User.VERFY_USER);
			}else{
				param.put("verfyID", User.VERFY_COLLECTION);
				param.put("userStatus", User.STATUS_NORMAL);
			}
			param.put("adminStatus", adminStatus);
			DataTable dataTable = accountDao.selectUserDataList(param, pageNo, pageSize, dbAccess);
			return dataTable;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询用户失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	
	// 查询Top用户 DONE
	public List<User> queryTopUserList(Map<String, String> param, HttpServletRequest request) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String topSize = param.get("topSize");
			String colCount = param.get("colCount");
			topSize = CStringUtils.isEmpty(topSize)?String.valueOf(10):topSize;
			colCount = CStringUtils.isEmpty(colCount)?String.valueOf(5):colCount;
			param.put("topSize", topSize);
			param.put("colCount", colCount);
			// 查询top用户
			List<User> userList = accountDao.selectTopUserList(param, dbAccess);
			// 查询top商品
			for (int i = 0; null!=userList && i<userList.size(); i++) {
				User usr = userList.get(i);
				Map<String, String> colParam = new HashMap<String, String>();
				colParam.put("userSequence", usr.getUserSequence());
				colParam.put("colCount", colCount);
				// 查询藏品
				List<Collection> collectionList = collectionDao.selectTopCollectionList(colParam, dbAccess);
				userList.get(i).setColList(collectionList);
				
				// 查询种类
		    	DataTable dataTable = collectionDao.selectReleaseCategaryMap(usr.getUserSequence(), dbAccess);
		    	List<Map<String, String>> categaryList = TStringUtils.turnDataTableToStringMapList(dataTable);
		    	List<Map<String, String>> cateMapList = this.getCateListMap(categaryList, request);
			    userList.get(i).setCateMapList(cateMapList);
			}
		    
			dbAccess.commitTransction();
			return userList;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("查询top用户失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	
	private List<Map<String, String>> getCateListMap(List<Map<String, String>> categaryList,HttpServletRequest request){
		// 将种类拆成
		List<ReleaseCategary> categaryList2 = TMemberUtils.getCategaryList(request);
	    for (int i = 0; i < categaryList2.size(); i++) {
	    	// 添加默认值
			String cateSequence = categaryList2.get(i).getCateSequence();
			categaryList2.get(i).setColCount(String.valueOf(0));
			// 设置真实值
			for (int j = 0; j < categaryList.size(); j++) {
				Map<String, String> map = categaryList.get(j);
				String value = map.get("cateSequence");
				if (CStringUtils.equals(cateSequence, value)) {
					categaryList2.get(i).setColCount(map.get("colCount"));
					break;
				}
			}
		}
	    
	    // 转化成ListMap
	    List<Map<String, String>> cateMapList = new ArrayList<Map<String, String>>();
	    for (int j = 0; j < categaryList2.size(); j++) {
	    	ReleaseCategary categary = categaryList2.get(j);
	    	Map<String, String> map = new HashMap<String, String>();
	    	map.put("cateSequence", categary.getCateSequence());
	    	map.put("cateName", categary.getCateName());
	    	map.put("colCount", categary.getColCount());
	    	cateMapList.add(map);
		}
	    return cateMapList;
	}
	
	//----------------------------------账户信息更改----------------------------------//	
	// 更改用户信息
	public User modifyUserMsg(Map<String, String> param,User user) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();

			String userName = param.get("userName");				// 用户名
			String userPhone = param.get("userPhone");				// 手机号
			String userImageURL = param.get("userImageURL");		// 头像地址
			if (CStringUtils.isEmpty(userName) && CStringUtils.isEmpty(userPhone) && CStringUtils.isEmpty(userImageURL)) {
				throw new BusinessException("参数不合法");
			}
			
			//修改用户信息
			param.put("userSequence", user.getUserSequence());
			accountDao.updateUserMsg(param, dbAccess);
			param = new HashMap<String, String>();
			param.put("userSequence", user.getUserSequence());
			//查询用户信息
			User userMsg = accountDao.selectUserAccount(param, dbAccess);
			dbAccess.commitTransction();
			return userMsg;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("更改账户信息失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}

	// 更改用户账户
	public int modifyUserCount(Map<String, String> param,User user) throws BusinessException {
		
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			param.put("userSequence", user.getUserSequence());
			int updateUserMsg = accountDao.updateUserCount(param, dbAccess);
			dbAccess.commitTransction();
			return updateUserMsg;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("更改账户信息失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 重置用户密码
	public int resetUserPwd(Map<String, String> param,User user) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String userPwd = param.get("userPwd");			// 用户密码
			String newPwd = param.get("newPwd");			// 新密码
			if (CStringUtils.isEmpty(userPwd) ||  CStringUtils.isEmpty(newPwd)) {
				throw new BusinessException("当前输入密码为空,修改密码失败");
			}else if (CStringUtils.equals(userPwd, newPwd)) {
				throw new BusinessException("新旧密码相等,修改密码失败");
			}
			param = new HashMap<String, String>();
			param.put("userSequence", user.getUserSequence());
			param.put("userPwd", MD5Utils.md5Encode(userPwd));
			User suserMsg = accountDao.selectUserAccount(param, dbAccess);
			if (!TMemberUtils.isUser(suserMsg)) {
				throw new BusinessException("用户密码错误,修改密码失败！");
			}
			// 修复用户数据
			param = new HashMap<String, String>();
			param.put("userSequence", user.getUserSequence());
			param.put("userPwd", MD5Utils.md5Encode(newPwd));
			int resetCount = accountDao.updateUserPwd(param, dbAccess);
			dbAccess.commitTransction();
			return resetCount;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("更改账户信息失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
		
		
	// 更改用户账户
	public int modifyUserCount(Map<String, String> param) throws BusinessException {
		
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String userSequence = param.get("userSequence");
			if (CStringUtils.isEmpty(userSequence)) {
				throw new BusinessException("参数不合法");
			}
			int updateUserMsg = accountDao.updateUserCount(param, dbAccess);
			dbAccess.commitTransction();
			return updateUserMsg;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("更改账户信息失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 查询分类用户信息  DONE
	public DataTable queryCateUserDataList(Map<String, String> param, int pageNo, int pageSize) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			DataTable dataTable = null;
			String cateSequence = param.get("cateSequence");
			if (CStringUtils.isEmpty(cateSequence)) {
				dataTable = accountDao.selectUserSequenceDataWithoutCate(param, pageNo, pageSize, dbAccess);
			}else{
				dataTable = accountDao.selectUserSequenceDataWithCate(param, pageNo, pageSize, dbAccess);
			}
			return dataTable;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询类型失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 查询分类用户信息
	public List<User> queryUserTopCollectionList(List<String> usrList,User user,HttpServletRequest request) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			AttentionDao attentionDao = new AttentionDao();

			if (CollectionUtils.isEmpty(usrList)) {return new ArrayList<User>();}
			// 查询用户信息
			List<User> userList = accountDao.selectCateUserList(usrList, dbAccess);
			// 查询用户信息最新商品图片
			for (int i = 0;null!=userList && i<userList.size(); i++) {
				Map<String, String> colParam = new HashMap<String, String>();
				colParam.put("userSequence", userList.get(i).getUserSequence());
				List<Collection> collectionList = collectionDao.selectTopCategaryCollectionWithoutCate(colParam, dbAccess);
				// 添加藏品
				userList.get(i).setColList(collectionList);
				// 判断是否关注
				if (TMemberUtils.isUser(user)) {
					Map<String, String> attParam = new HashMap<String, String>();
					attParam.put("userSequence", user.getUserSequence());
					attParam.put("relUserSequence", userList.get(i).getUserSequence());
					int count = attentionDao.selectUserAttentionCount(attParam, dbAccess);
					if (count > 0) {
						userList.get(i).setHasAttention(Boolean.TRUE);
					}
				}
				// 查询种类
		    	DataTable dataTable = collectionDao.selectReleaseCategaryMap(userList.get(i).getUserSequence(), dbAccess);
		    	List<Map<String, String>> categaryList = TStringUtils.turnDataTableToStringMapList(dataTable);
		    	List<Map<String, String>> cateMapList = this.getCateListMap(categaryList, request);
			    userList.get(i).setCateMapList(cateMapList);
			}
			
			return userList;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询类型失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	
	// 查询分类用户信息
	public List<Map<String, Object>> queryCateUserList(List<Map<String, Object>> userMapList,String cateSequence, User user) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			if (CollectionUtils.isEmpty(userMapList)) {return userMapList;}
			List<String> usrSeqList = TStringUtils.turnObjectMapListToList(userMapList, "userSequence");
			TStringUtils.printList(usrSeqList);
			// 查询用户信息最新商品图片
			for (int i = 0;null!=usrSeqList && i<usrSeqList.size(); i++) {
				Map<String, String> colParam = new HashMap<>();
				colParam.put("cateSequence", cateSequence);
				colParam.put("userSequence", usrSeqList.get(i));
				
				List<Collection> collectionList = null;
				if (CStringUtils.isEmpty(cateSequence)) {
					collectionList = collectionDao.selectTopCategaryCollectionWithoutCate(colParam, dbAccess);
				}else{
					collectionList = collectionDao.selectTopCategaryCollectionWithCate(colParam, dbAccess);
				}
				userMapList.get(i).put("colList", collectionList);
				
				// 判断是否关注
				if (TMemberUtils.isUser(user)) {
					Map<String, String> attParam = new HashMap<String, String>();
					attParam.put("userSequence", user.getUserSequence());
					attParam.put("relUserSequence", usrSeqList.get(i));
					int count = attentionDao.selectUserAttentionCount(attParam, dbAccess);
					if (count > 0) {
						userMapList.get(i).put("hasAttention", Boolean.TRUE);
					}
				}
			}
			return userMapList;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询类型失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	
	
	// 查询分类用户信息
//	public List<User> queryCateUserList(List<String> usrList,User user) throws BusinessException {
//		dbAccess = MyBatisDBAccess.getInstance();
//		try {
//			dbAccess.startTransction();
//			// 如果用户为空
//			if (CollectionUtils.isEmpty(usrList)) {return new ArrayList<User>();}
//			// 查询用户信息
//			List<User> userList = accountDao.selectCateUserList(usrList, dbAccess);
//			// 查询用户信息最新商品图片
//			for (int i = 0;null!=userList && i<userList.size(); i++) {
//				List<ImageCollection> selectCateUserList = imageDao.selectCateUserList(userList.get(i).getUserSequence(), dbAccess);
//				// 添加藏品
//				userList.get(i).setColImageList(selectCateUserList);
//				// 判断是否关注
//				if (TMemberUtils.isUser(user)) {
//					Map<String, String> attParam = new HashMap<String, String>();
//					attParam.put("userSequence", user.getUserSequence());
//					attParam.put("relUserSequence", userList.get(i).getUserSequence());
//					int count = attentionDao.selectUserAttentionOrNot(attParam, dbAccess);
//					if (count > 0) {
//						userList.get(i).setHasAttention(Boolean.TRUE);
//					}
//				}
//			}
//			return userList;
//		} catch (BusinessException e) {
//			throw e;
//		} catch (Exception e) {
//			throw new BusinessException("查询类型失败:", e);
//		} finally {
//			dbAccess.endTransction();
//		}
//	}
	// 查询分类用户信息
	public List<Map<String, Object>> queryCateUserListNoAttention(List<Map<String, Object>> userMapList,String cateSequence, User user) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			if (CollectionUtils.isEmpty(userMapList)) {return userMapList;}
			List<String> usrSeqList = TStringUtils.turnObjectMapListToList(userMapList, "userSequence");
			// 查询用户信息
			// 查询用户信息最新商品图片
			for (int i = 0;null!=usrSeqList && i<usrSeqList.size(); i++) {
				Map<String, String> colParam = new HashMap<>();
				colParam.put("cateSequence", cateSequence);
				colParam.put("userSequence", usrSeqList.get(i));
				List<Collection> collectionList = null;
				if (CStringUtils.isEmpty(cateSequence)) {
					collectionList = collectionDao.selectTopCategaryCollectionWithoutCate(colParam, dbAccess);
				}else{
					collectionList = collectionDao.selectTopCategaryCollectionWithCate(colParam, dbAccess);
				}
				userMapList.get(i).put("colList", collectionList);
				userMapList.get(i).put("hasAttention", Boolean.TRUE);
			}
			return userMapList;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询类型失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}

	// 查询用户有没有被关注 DONE
	public List<Map<String, String>> selectUserAttentionOrNot(DataTable dataTable, User user){
		try {
			dbAccess.startTransction();
			List<Map<String, String>> userSequenceList = TStringUtils.turnDataTableToStringMapList(dataTable);
			List<String> usrSeqList = TStringUtils.turnStringMapListToList(userSequenceList, "userSequence");
			for (int i = 0; null!=usrSeqList && i < usrSeqList.size(); i++) {
				Map<String, String> usrParam = new HashMap<String, String>();
				usrParam.put("userSequence", user.getUserSequence());
				usrParam.put("relUserSequence", usrSeqList.get(i));
				if (new AttentionDao().selectUserAttentionCount(usrParam, dbAccess) > 0) {
					userSequenceList.get(i).put("hasAttention",String.valueOf(Boolean.TRUE));
				}else{
					userSequenceList.get(i).put("hasAttention",String.valueOf(Boolean.FALSE));
				}
			}
			return userSequenceList;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("添加收藏品:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	
	//----------------------------------未完待续----------------------------------//	
	// 升级用户身份 DONE
	public int upgradeUserLevel(List<String> list,Admin admin) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			if (CollectionUtils.isEmpty(list)) {
				throw new BusinessException("没有选定升级的用户");
			}
			Map<String, Object> param= new HashMap<String, Object>();
			param.put("operUserSequence", admin.getAdminSequence());
			param.put("adminStatus", User.STATUS_NORMAL);
			param.put("userStatus", User.STATUS_NORMAL);
			param.put("verfyID", User.VERFY_COLLECTION);
			param.put("userCreatDate", WorkDate.getSystemTime());
			param.put("userList", list);
			int updateUserMsg = accountDao.updateUserLevelStatus(param, dbAccess);
			dbAccess.commitTransction();
			return updateUserMsg;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("更改账户信息失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	//==================================管理员账户====================================//
	// 拉黑用户身份 DONE
	@SuppressWarnings("unchecked")
	public int blackUserStatus(Map<String, Object> param,Admin admin) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			List<String> userList = (List<String>) param.get("userList");
			if (CollectionUtils.isEmpty(userList)) {
				throw new BusinessException("没有选定升级的用户");
			}
			param.put("userList", userList);
			param.put("operUserSequence", admin.getAdminSequence());
			param.put("userCreatDate", WorkDate.getSystemTime());
			String userStatus = (String) param.get("userStatus");
			if (CStringUtils.equals(userStatus, User.STATUS_BLACK)) {
				param.put("adminStatus",  User.STATUS_BLACK);
				param.put("userStatus", User.STATUS_BLACK);
				param.put("verfyID", User.VERFY_USER);
			}else if (CStringUtils.equals(userStatus, User.STATUS_NORMAL)) {
				param.put("adminStatus",  User.STATUS_BLACK);
				param.put("userStatus", User.STATUS_NORMAL);
				param.put("verfyID", User.VERFY_USER);
			}else{
				throw new BusinessException("操作不合法");
			}
			int updateUserMsg = accountDao.updateUserLevelStatus(param, dbAccess);
			dbAccess.commitTransction();
			return updateUserMsg;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("更改账户信息失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 降级藏家身份 DONE
	@SuppressWarnings("unchecked")
	public int blackCollectorStatus(Map<String, Object> param,Admin admin) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			List<String> userList = (List<String>) param.get("userList");
			if (CollectionUtils.isEmpty(userList)) {
				throw new BusinessException("没有选定升级的藏家");
			}
			param.put("userList", userList);
			param.put("operUserSequence", admin.getAdminSequence());
			param.put("userCreatDate", WorkDate.getSystemTime());
			String adminStatus = (String) param.get("adminStatus");
			if (CStringUtils.equals(adminStatus, User.STATUS_BLACK)) {
				param.put("adminStatus",  User.STATUS_BLACK);
				param.put("userStatus", User.STATUS_NORMAL);
				param.put("verfyID", User.VERFY_USER);
			}else if (CStringUtils.equals(adminStatus, User.STATUS_NORMAL)) {
				param.put("adminStatus",  User.STATUS_NORMAL);
				param.put("userStatus", User.STATUS_NORMAL);
				param.put("verfyID", User.VERFY_COLLECTION);
			}else{
				throw new BusinessException("操作不合法");
			}
			int updateUserMsg = accountDao.updateUserLevelStatus(param, dbAccess);
			dbAccess.commitTransction();
			return updateUserMsg;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("更改账户信息失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	//==================================管理员账户====================================//
	// 查询管理员账户信息
	public Admin selectAdminAccount(Map<String, String> param){
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String adminPwd = param.get("adminPwd");
			if (CStringUtils.isEmpty(adminPwd)) {
				throw new BusinessException("当前密码为空");
			}
			param.put("adminPwd", MD5Utils.md5Encode(adminPwd));
			Admin adminMsg = accountDao.selectAdminAccount(param, dbAccess);
			if (!TMemberUtils.isAdmin(adminMsg)) {
				throw new BusinessException("用户密码错误，或者管理员账户不存在！");
			}
			if (TMemberUtils.isAdmin(adminMsg) && (Admin.STATUS_BLACK.equals(adminMsg.getAdminStatus()))) {
				throw new BusinessException("当前账户已经被拉黑，暂不能登录！");
			}
			return adminMsg;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("管理员登录失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	
	// 查询管理员账户信息
	public Admin selectAdminMsg(Map<String, String> param,Admin admin){
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String adminSequence = param.get("adminSequence");
			if (CStringUtils.isEmpty(adminSequence)) {
				throw new BusinessException("参数不合法");
			}
			Admin adminMsg = accountDao.selectAdminAccount(param, dbAccess);
			return adminMsg;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("管理员登录失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 创建管理员账户
	public int insertAdminAccount(Map<String, String> param, Admin admin){
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			if (!TMemberUtils.isSuperAdmin(admin)) {
				throw new BusinessException("当前管理员无操作权限");
			}
	        String adminEmail = param.get("adminEmail");
	        String adminPhone = param.get("adminPhone");
	        String adminPwd = param.get("adminPwd");
	        String adminName = param.get("adminName");
	        // 验证数据
			if (CStringUtils.isEmpty(adminEmail) && CStringUtils.isEmpty(adminPhone)) {
				throw new BusinessException("请输入有效邮箱或者电话");
			}
			Map<String, String> map = new HashMap<>();
			map.put("adminPhone", adminPhone);
			map.put("adminEmail", adminEmail);
			map.put("adminStatus", Admin.STATUS_NORMAL);
			// 校验重复
			int adminCount = accountDao.selectAdminCount(map, dbAccess);
			if (adminCount>0 && CStringUtils.isNotEmpty(adminEmail)) {
				throw new BusinessException("该邮箱已经注册");
			}else if (adminCount>0 && CStringUtils.isNotEmpty(adminPhone)) {
				throw new BusinessException("该手机已经注册");
			}
			// 创建新管理员
			String adminSequence = dbAccess.getSeqID("a_admin");
			param.put("adminSequence", adminSequence);
			param.put("adminImageURL", "/default/icon2.png");
			param.put("adminPwd", MD5Utils.md5Encode(adminPwd));
			param.put("adminEmail", adminEmail);
			param.put("adminPhone", adminPhone);
			param.put("adminStatus", Admin.STATUS_NORMAL);
			param.put("adminCreatDate", WorkDate.getSystemTime());
			param.put("adminRemark",adminName+"在"+WorkDate.getSystemTime()+"被 "+admin.getAdminName()+" 创建！");
			param.put("adminSex", Admin.SEX_M);
			param.put("adminLevel", Admin.LEVEL_ADMIN);
			
			accountDao.insertAdminAccount(param, dbAccess);

			dbAccess.commitTransction();
			return adminCount;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("创建管理员账户失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	
	// 管理员账户列表
	public DataTable queryAdminDataList(Map<String, String> param, int pageNo, int pageSize, Admin admin){
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String adminStatus = param.get("param");
			adminStatus = CStringUtils.isEmpty(adminStatus)?Admin.STATUS_NORMAL:Admin.STATUS_BLACK;
			param.put("adminStatus", adminStatus);
			param.put("adminLevel", Admin.LEVEL_ADMIN);
			DataTable dataTable = accountDao.selectAdminDataList(param, pageNo, pageSize, dbAccess);
			return dataTable;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("创建管理员账户失败", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	
	// 删除管理员账户
	public int deleteAdminCount(Map<String, String> param, Admin admin){
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			if (!TMemberUtils.isSuperAdmin(admin)) {
				throw new BusinessException("当前管理员无操作权限");
			}
			String adminSequence = param.get("adminSequence");
			if (CStringUtils.isEmpty(adminSequence)) {
				throw new BusinessException("参数不合法");
			}
			String adminStatus = param.get("param");
			adminStatus = CStringUtils.isEmpty(adminStatus)?Admin.STATUS_BLACK:Admin.STATUS_NORMAL;
			param.put("adminStatus", adminStatus);
			param.put("adminLevel", Admin.LEVEL_ADMIN);
			int adminCount = accountDao.updateAdminCount(param, dbAccess);
			dbAccess.commitTransction();
			return adminCount;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("删除管理员账户失败", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	
	// 更改管理员账户
	public int  modifyAdminCount(Map<String, String> param, Admin admin){
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			if (!TMemberUtils.isSuperAdmin(admin)) {
				throw new BusinessException("当前管理员无操作权限");
			}
			String adminSequence = param.get("adminSequence");
	        String adminEmail = param.get("adminEmail");
	        String adminPhone = param.get("adminPhone");
	        String adminPwd = param.get("adminPwd");
			if (CStringUtils.isEmpty(adminSequence) || CStringUtils.isEmpty(adminPwd)) {
				throw new BusinessException("参数不合法");
			}
	        // 验证数据
			if (CStringUtils.isEmpty(adminEmail) && CStringUtils.isEmpty(adminPhone)) {
				throw new BusinessException("请输入有效登录电话号");
			}
			Map<String, String> map = new HashMap<>();
			map.put("adminPhone", adminPhone);
			map.put("adminEmail", adminEmail);
			map.put("adminStatus", Admin.STATUS_NORMAL);
			map.put("ingoreAdminSeq", adminSequence);
			// 校验重复
			int adminCount = accountDao.selectAdminCount(map, dbAccess);
			if (adminCount>0 && CStringUtils.isNotEmpty(adminEmail)) {
				throw new BusinessException("该邮箱已经注册");
			}else if (adminCount>0 && CStringUtils.isNotEmpty(adminPhone)) {
				throw new BusinessException("该手机已经注册");
			}
			// 更新数据
			String adminStatus = Admin.STATUS_NORMAL;
			param.put("adminPwd", MD5Utils.md5Encode(adminPwd));
			param.put("adminStatus", adminStatus);
			param.put("adminLevel", Admin.LEVEL_ADMIN);
			int admint = accountDao.updateAdminCount(param, dbAccess);
			dbAccess.commitTransction();
			return admint;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("修改管理员账户失败", e);
		} finally {
			dbAccess.endTransction();
		}
	}
}
