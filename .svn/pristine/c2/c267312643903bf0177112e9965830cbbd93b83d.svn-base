package cn.smartcandy.application.a.thirtyLogin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.smartcandy.application.a.account.User;
import cn.smartcandy.common.utils.TStringUtils;
import cn.smartcandy.framework.core.db.mybatis.MyBatisDBAccess;
import cn.smartcandy.framework.core.exception.BusinessException;

/**
 * @项目名称：zmjema
 * @类名称：LoginUtil
 * @类描述：登录工具类，判断用户存在与否
 * @创建人：tangzhifeng
 * @创建时间：2017年11月17日 上午11:03:58
 * @修改人：someOne
 * @修改时间：2017年11月17日 上午11:03:58 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class LoginUtil {

	// 获取UEAB绑定用户信息
	public static User getUeabBindUser(String unionID, String openID,String ueabType, HttpServletRequest request) throws BusinessException {
		MyBatisDBAccess dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			Map<String, String> ueabMap = new HashMap<String, String>();
			ueabMap.put("unionID", unionID);
			ueabMap.put("openID", openID);
			ueabMap.put("ueabType", ueabType);
			ueabMap.put("ueabStatus", LoginUeab.STATUS_NORMAL);
			TStringUtils.printStringMap(ueabMap);
			User user = new LoginUeabBiz().selectBindUser(ueabMap);
			return user;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("第三方登录异常", e);
		} finally {
			dbAccess.endTransction();
		}
	}
}
