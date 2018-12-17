package cn.smartcandy.application.a.account;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.smartcandy.common.common.BaseAction;
import cn.smartcandy.common.utils.TMemberUtils;
import cn.smartcandy.common.utils.TStringUtils;
import cn.smartcandy.framework.conf.SysConfig;
import cn.smartcandy.framework.core.data.DataTable;
import cn.smartcandy.framework.utils.CStringUtils;

/**
 * @项目名称：zmjema
 * @类名称：AccountAction
 * @类描述：账户注册绑定
 * @创建人：tangzhifeng
 * @创建时间：2017年10月13日 下午6:21:18
 * @修改人：someOne
 * @修改时间：2017年10月13日 下午6:21:18 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class AccountAction extends BaseAction{

	private static final long serialVersionUID = 6846023117016423631L;
	private  AccountBiz accountBiz = new AccountBiz();

	public static int USER_MAXINACT_IVEINTERVAL = 180 * 60;					//最大活跃时间
	public static boolean USER_ALLOW_LOGIN_REPEATEDLY = Boolean.TRUE;		//允许重复登录
	
	public AccountAction(){
		USER_MAXINACT_IVEINTERVAL = SysConfig.getInstance().get("USER_MAXINACT_IVEINTERVAL", 180) * 60;
	}
	
	// 注册用户 DONE
	public void userRegister() {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
        TStringUtils.printStringMap(param);
        User user = accountBiz.registerAccount(param,request);
		request.setAttribute("user", user);
		TMemberUtils.saveUser(request, user);
	}
	// 用户登录 DONE
	public String userLogin() {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
        TStringUtils.printStringMap(param);
        // 用户登录
        User user = accountBiz.loginAccount(param,request);
		request.setAttribute("loginMsgUser", user);
		TMemberUtils.saveUser(request, user);
		return null;
	}
	// 当前登录用户(判断用户是否还在线) DONE
	public String currentUserMsg() {
		User user = (User) TMemberUtils.getUser(request);
		if (TMemberUtils.isUser(user)) {
			user = new AccountBiz().currentUserMsg(user.getUserSequence());
		}
		request.setAttribute("userMsg", user);
		return null;
	}
	
	// 用户注销 DONE
	public String userLoginOut() {
		TMemberUtils.cleanUser(request);
		return null;
	}

	// 查询用户信息 DONE
	public String queryUserDetailMsg() {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
        TStringUtils.printStringMap(param);
        User userMsg =  accountBiz.queryUserDetailMsg(param,super.getUser());
		request.setAttribute("userMsg", userMsg);
		return null;
	}
	
	// 查询用户账户
	public String queryUserAccount() {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
        TStringUtils.printStringMap(param);

        User userMsg =  accountBiz.queryUserAccount(param, request, super.getUser());
		request.setAttribute("userMsg", userMsg);
		return null;
	}
	
	// 查询分页用户信息 
	public String queryUserDataList() {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
        TStringUtils.printStringMap(param);
        DataTable dataTable = accountBiz.queryUserDataList(param,super.getPageNo(),super.getPageSize(),super.getAdmin());

        List<Map<String, String>> userList = TStringUtils.turnDataTableToStringMapList(dataTable);
        Map<String, String> pageInfo = TStringUtils.turnDataTableToPageInfoMap(dataTable);
		request.setAttribute("userList", userList);
		request.setAttribute("pageInfo", pageInfo);
		return null;
	}
	// 查询藏家分页数据
	public String queryCollectorDataList() {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
        TStringUtils.printStringMap(param);
        DataTable dataTable = accountBiz.queryCollectorDataList(param,super.getPageNo(),super.getPageSize(),super.getAdmin());

        List<Map<String, String>> userList = TStringUtils.turnDataTableToStringMapList(dataTable);
        Map<String, String> pageInfo = TStringUtils.turnDataTableToPageInfoMap(dataTable);
		request.setAttribute("colerList", userList);
		request.setAttribute("pageInfo", pageInfo);
		return null;
	}
	
	// 查询Top DONE
	public String queryTopUserList() {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
        TStringUtils.printStringMap(param);
        List<User> userList = accountBiz.queryTopUserList(param,request);
		request.setAttribute("userList", userList);
		return null;
	}
	//----------------------------------账户信息更改----------------------------------//	
	// 更改用户信息（更改用户信息）
	public String modifyUserMsg() {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
        TStringUtils.printStringMap(param);
        User userMsg = accountBiz.modifyUserMsg(param,super.getUser());
		request.setAttribute("success", userMsg);
		TMemberUtils.saveUser(request, userMsg);
		return null;
	}
	// 更改账户信息（更改用户的点赞数量）
	public String modifyUserCount() {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
        TStringUtils.printStringMap(param);
        accountBiz.modifyUserCount(param,super.getUser());
		return null;
	}
	// 更改账户信息（重置密码）
	public String resetUserPwd() {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
        TStringUtils.printStringMap(param);
        accountBiz.resetUserPwd(param,super.getUser());
		return null;
	}
	// 升级用户身份 DONE
	public String upgradeUserLevel() {
		List<String> userList = Arrays.asList(request.getParameterValues("userList[]"));
        accountBiz.upgradeUserLevel(userList, super.getAdmin());
		return null;
	}
	
	// 拉黑用户身份 DONE
	public String blackUserStatus() {
		List<String> userList = Arrays.asList(request.getParameterValues("userList[]"));
		String userStatusVal = request.getParameter("userStatus");
		String userStatus = CStringUtils.isEmpty(userStatusVal)?User.STATUS_NORMAL:userStatusVal;
		Map<String, Object> param = new HashMap<String, Object>();
 		param.put("userStatus", userStatus);
 		param.put("userList", userList);
        accountBiz.blackUserStatus(param, super.getAdmin());
		return null;
	}
	
	// 拉黑用藏家身份 DONE
	public String blackCollectorStatus() {
		List<String> userList = Arrays.asList(request.getParameterValues("userList[]"));
		String adminStatusVal = request.getParameter("adminStatus");
		String adminStatus = CStringUtils.isEmpty(adminStatusVal)?User.STATUS_NORMAL:adminStatusVal;
		Map<String, Object> param = new HashMap<String, Object>();
 		param.put("adminStatus", adminStatus);
 		param.put("userList", userList);
        accountBiz.blackCollectorStatus(param, super.getAdmin());
		return null;
	}
	
	// 查询分类用户信息 DONE
	public String queryCateUserDataList() {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
		TStringUtils.printStringMap(param);
		// 查询分类用户
		DataTable dataTable = accountBiz.queryCateUserDataList(param,super.getPageNo(),super.getPageSize());
		List<Map<String, Object>> userMapList = TStringUtils.turnDataTableToObjectMapList(dataTable);
		// 查询用户最新藏品图片
		List<Map<String, Object>> queryCateUserList = accountBiz.queryCateUserList(userMapList,param.get("cateSequence"),user);
		// 分页信息
        Map<String, String> pageInfo = TStringUtils.turnDataTableToPageInfoMap(dataTable);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("userList", queryCateUserList);
		return null;
	}
	
	//----------------------------------未完待续----------------------------------//
	// 管理员账户登录
	public String adminLogin() {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
		TStringUtils.printStringMap(param);
		Admin adminMsg = accountBiz.selectAdminAccount(param);
		request.setAttribute("admin", adminMsg);
		TMemberUtils.saveAdmin(request, adminMsg);
		
		return null;
	} 
	// 当前登录用户(判断用户是否还在线) DONE
	public String currentAdminMsg() {
		Admin adminMsg = (Admin) TMemberUtils.getAdmin(request);
		request.setAttribute("admin", adminMsg);
		return null;
	}
	// 创建管理员账户信息
	public String adminCreate() {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
		TStringUtils.printStringMap(param);
		accountBiz.insertAdminAccount(param, super.getAdmin());
		return null;
	}
	// 查询管理员账户信息
	public String queryAdminMsg() {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
		TStringUtils.printStringMap(param);
		Admin admin = accountBiz.selectAdminMsg(param, super.getAdmin());
		request.setAttribute("admin", admin);
		return null;
	}
	// 管理员注销 
	public String adminLoginOut() {
		TMemberUtils.cleanAdmin(request);
		return "adminLoginOut";
	}
	
	// 管理员列表
	public String queryAdminDataList() {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
		TStringUtils.printStringMap(param);
		
		DataTable dataTable = accountBiz.queryAdminDataList(param, super.getPageNo(), super.getPageSize(), super.getAdmin());
		List<Map<String, Object>> adminList = TStringUtils.turnDataTableToObjectMapList(dataTable);
        Map<String, String> pageInfo = TStringUtils.turnDataTableToPageInfoMap(dataTable);
        request.setAttribute("adminList", adminList);
		request.setAttribute("pageInfo", pageInfo);
		
		return null;
	} 
	// 删除管理员账户信息
	public String deleteAdminCount() {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
		TStringUtils.printStringMap(param);
		System.out.println("super.getAdmin():"+super.getAdmin());
		accountBiz.deleteAdminCount(param, super.getAdmin());
		return null;
	}
	
	// 删除管理员账户信息
	public String modifyAdminCount() {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
		TStringUtils.printStringMap(param);
		System.out.println("super.getAdmin():"+super.getAdmin());
		accountBiz.modifyAdminCount(param, super.getAdmin());
		return null;
	}
}
