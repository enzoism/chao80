package cn.smartcandy.application.a.account;

import java.util.List;
import java.util.Map;

import cn.smartcandy.framework.core.data.DataTable;
import cn.smartcandy.framework.core.db.mybatis.MyBatisDBAccess;

/**
 * @项目名称：zmjema
 * @类名称：AccountDao
 * @类描述：账户注册绑定
 * @创建人：tangzhifeng
 * @创建时间：2017年10月13日 下午6:21:44
 * @修改人：someOne
 * @修改时间：2017年10月13日 下午6:21:44 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class AccountDao {
	
	// 用户注册
	public int insertUserAccount(Map<String, String> param, MyBatisDBAccess dbAccess) {
		return dbAccess.insert("account.insertUserAccount", param);
	}
	// 查询用户信息（全部信息）
	public User selectUserMsg(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectOne("account.selectUserMsg", param);
	}
	// 查询用户账户（账户信息）DONE
	public User selectUserAccount(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectOne("account.selectUserAccount", param);
	}
	// 查询所有用户信息（不分页）DONE
	public List<User> selectUserList(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectList("account.selectUserList", param);
	}
	// 查询所有用户信息
	public DataTable selectUserDataList(Map<String, String> param,int pageNo, int pageSize, MyBatisDBAccess dbAccess){
		return dbAccess.selectDataTable("account.selectUserDataList", param, pageNo, pageSize);
	}
	
	// 查询用户信息（Top10）DONE
	public List<User> selectTopUserList(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectList("account.selectTopUserList", param);
	}
	
	//----------------------------------账户信息更改----------------------------------//	
	// 更新用户信息
	public int updateUserMsg(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.update("account.updateUserMsg", param);
	}
	// 更新用户账户 DONE
	public int updateUserCount(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.update("account.updateUserCount", param);
	}
	// 重置用户密码
	public int updateUserPwd(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.update("account.updateUserPwd", param);
	}
	//----------------------------------账户信息更改----------------------------------//	
	// 查询分类用户序号
	public DataTable selectUserSequenceDataWithoutCate(Map<String, String> param,int pageNo, int pageSize, MyBatisDBAccess dbAccess){
		return dbAccess.selectDataTable("account.selectUserSequenceDataWithoutCate", param, pageNo, pageSize);
	}
	// 查询分类用户序号
	public DataTable selectUserSequenceDataWithCate(Map<String, String> param,int pageNo, int pageSize, MyBatisDBAccess dbAccess){
		return dbAccess.selectDataTable("account.selectUserSequenceDataWithCate", param, pageNo, pageSize);
	}
	// 查询分类用户信息
	public List<User> selectCateUserList(List<String> userList, MyBatisDBAccess dbAccess){
		return dbAccess.selectList("account.selectCateUserList", userList);
	}
	// 升级和拉黑用户 DONE
	public int updateUserLevelStatus(Map<String, Object> param, MyBatisDBAccess dbAccess){
		return dbAccess.update("account.updateUserLevelStatus", param);
	}
	
	//----------------------------------管理员账户----------------------------------//	
	// 查询管理员账户信息
	public Admin selectAdminAccount(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectOne("account.selectAdminAccount", param);
	}
	// 创建管理员账户
	public int insertAdminAccount(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.insert("account.insertAdminAccount", param);
	}
	// 查询所有用户信息（不分页）DONE
	public int selectAdminCount(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectOne("account.selectAdminCount", param);
	}
	// 查询所有用户信息（不分页）DONE
	public DataTable selectAdminDataList(Map<String, String> param,int pageNo, int pageSize, MyBatisDBAccess dbAccess){
		return dbAccess.selectDataTable("account.selectAdminDataList", param, pageNo, pageSize);
	}
	
	// 更新管理员账户
	public int updateAdminCount(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.update("account.updateAdminCount", param);
	}
	
}
