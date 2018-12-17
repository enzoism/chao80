package cn.smartcandy.application.a.thirtyLogin;

import java.util.Map;

import cn.smartcandy.application.a.account.User;
import cn.smartcandy.common.common.BaseBiz;
import cn.smartcandy.framework.core.db.mybatis.MyBatisDBAccess;
import cn.smartcandy.framework.core.exception.BusinessException;
import cn.smartcandy.framework.utils.CStringUtils;

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
public class LoginUeabBiz  extends BaseBiz{
	private LoginUeabDao ueabDao = new LoginUeabDao();
	
	// 创建绑定信息
	public int createBindMsg(Map<String, String> param){
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String openID  = param.get("openID");
			if (CStringUtils.isEmpty(openID)) {
				throw new BusinessException("参数不合法！");
			}
			int count = ueabDao.inserBindMsg(param, dbAccess);
			return count;
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
	// 判断账户是否绑定
	public int selectBindCount(Map<String, String> param){
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String openID  = param.get("openID");
			if (CStringUtils.isEmpty(openID)) {
				throw new BusinessException("参数不合法！");
			}
			int count = ueabDao.selectBindCount(param, dbAccess);
			return count;
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
	// 查询绑定Ueab信息
	public LoginUeab selectBindUeab(Map<String, String> param){
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			LoginUeab ueab = ueabDao.selectBindUeab(param, dbAccess);
			return ueab;
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

	// 查询绑定用户信息
	public User selectBindUser(Map<String, String> param){
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			User bindUser = ueabDao.selectBindUser(param, dbAccess);
			return bindUser;
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

}
