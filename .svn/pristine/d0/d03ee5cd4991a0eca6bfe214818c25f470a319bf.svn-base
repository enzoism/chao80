package cn.smartcandy.common;

import org.apache.commons.lang3.StringUtils;

import cn.smartcandy.framework.biz.exception.UnitNotSelectException;
import cn.smartcandy.framework.core.exception.BusinessException;

/**
  * 项目名称：a-source  
  * 类名称：ExceptionHandler  
  * 类描述：异常信息工具类
  * 创建人：chengli  
  * 创建时间：2016-9-2 下午04:56:51  
  * 修改人：chengli  
  * 修改时间：2016-9-2 下午04:56:51  
  * 修改备注：
  * Company:Annjema & HLS (C) 2016
  * @version 1.0
 */
public class ExceptionHandler {

	/**
	 * 获取BusinessException信息内容 ，当该异常不包含BusinessException异常，则返回系统异常，请稍候再试
	 * @param e
	 * @return
	 */
	public String getBusinessExceptionMessage(Throwable e) {
		if (null == e) {
			return "<b>系统或网络有点小问题 </b><br/> 请您两分钟后再试一下哟~";
		}
		if (e.getMessage() == null) {
			return getBusinessExceptionMessage(e.getCause());
		} else {
			String message = "<b>系统或网络有点小问题 </b><br/> 请您两分钟后再试一下哟~";
			if (e instanceof BusinessException) {
				message = e.getMessage();
			}
			return StringUtils.trimToEmpty(message);
		}
	}

	/**
	 * 判断传入的异常，是否包含UnitNotSelectException
	 * @param throwable
	 * @return 包含返回true，否则返回false
	 */
	public boolean isUnitNotSelectException(Throwable throwable) {
		if (throwable instanceof UnitNotSelectException) {
			return true;
		} else {
			if (null == throwable) {
				return false;
			}
			return isUnitNotSelectException(throwable.getCause());
		}
	}

	/**
	 * 获取UnitNotSelectException异常，从传入的对象提取
	 * @param throwable
	 * @return 包含返回UnitNotSelectException，否则返回null
	 */
	public UnitNotSelectException getUnitNotSelectException(Throwable throwable) {
		if (throwable instanceof UnitNotSelectException) {
			return (UnitNotSelectException) throwable;
		} else {
			if (null == throwable) {
				return null;
			}
			return getUnitNotSelectException(throwable.getCause());
		}
	}

}
