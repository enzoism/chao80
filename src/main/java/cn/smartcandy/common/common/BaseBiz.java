package cn.smartcandy.common.common;

import cn.smartcandy.framework.core.db.mybatis.MyBatisDBAccess;

/**
  * 项目名称：annCRM-source  
  * 类名称：BaseBiz  
  * 类描述：业务服务基类
  * 创建人：lgbzc-surface  
  * 创建时间：2016-2-26 上午03:39:39  
  * 修改人：lgbzc-surface  
  * 修改时间：2016-2-26 上午03:39:39  
  * 修改备注：
  * Company:北京闻壹信息有限公司 (C) 2017
  * @version 1.0
  */
public class BaseBiz {

	protected MyBatisDBAccess dbAccess = MyBatisDBAccess.getInstance();
}
