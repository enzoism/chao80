package cn.smartcandy.application.a.thirtyLogin;

import java.util.Map;

import cn.smartcandy.application.a.account.User;
import cn.smartcandy.framework.core.db.mybatis.MyBatisDBAccess;

/**
 * @项目名称：zmjema
 * @类名称：LoginUeab
 * @类描述：绑定实体类
 * @创建人：tangzhifeng
 * @创建时间：2017年11月17日 上午11:50:03
 * @修改人：someOne
 * @修改时间：2017年11月17日 上午11:50:03 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class LoginUeabDao {
	
	// 判断账户是否绑定
	public int selectBindCount(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectOne("thirtyLogin.selectBindCount", param);
	}	
	// 查询绑定Ueab信息
	public LoginUeab selectBindUeab(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectOne("thirtyLogin.selectBindUeab", param);
	}	
	// 查询绑定用户信息
	public User selectBindUser(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectOne("thirtyLogin.selectBindUser", param);
	}
	// 插入用户绑定数据
	public int inserBindMsg(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.insert("thirtyLogin.inserBindMsg", param);
	}
	// 删除用户绑定数据
	public int updateBindMsg(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.update("thirtyLogin.updateBindMsg", param);
	}
	
}
