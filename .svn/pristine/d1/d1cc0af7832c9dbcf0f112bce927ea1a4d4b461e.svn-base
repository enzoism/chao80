package cn.smartcandy.application.a.wxmsg.bean;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.smartcandy.common.parameter.ParameterDictionaryBean;
import cn.smartcandy.common.parameter.ParameterQuery;
import cn.smartcandy.framework.conf.SysConfig;
import cn.smartcandy.framework.core.cache.CacheManager;
import cn.smartcandy.framework.core.log.Log;
import cn.smartcandy.framework.utils.CStringUtils;

/**
 * 类描述：参数配置
 * @author Administrator
 */
public class WeixinPayConfig {
	public static String WEIXIN_PAY_CREATE_IP = SysConfig.getInstance().get("WEIXIN_PAY_CREATE_IP");//终端ip	//115.171.233.150
	
	//异步通知url
	public static String WEIXIN_PAY_NOTIFY_URL = SysConfig.getInstance().get("WEIXIN_PAY_NOTIFY_URL");
	//统一下单API
	public static String WEIXIN_PAY_UNIFIED_URL = SysConfig.getInstance().get("WEIXIN_PAY_UNIFIED_URL");
	//申请退款API
	public static String WEIXIN_PAY_REFUND_URL = SysConfig.getInstance().get("WEIXIN_PAY_REFUND_URL");
	//查询订单URL
	public static String WEIXIN_PAY_ORDER_QUERY_URL = SysConfig.getInstance().get("WEIXIN_PAY_ORDER_QUERY_URL");
	
	/** 缓存前缀*/
	public static final String WEIXIN_KEY_PREFIX = "WEIXIN_KEY_PREFIX_";
	
	private static WeixinPayConfig weixinPayConfig = null;
	
	/**
	 * 方法描述：获取实例
	
	 * @return
	 */
	public static WeixinPayConfig getInstance() {
		if (null == weixinPayConfig) {
			weixinPayConfig = new WeixinPayConfig();
			weixinPayConfig.initAppKeyInfo();
		}
		return weixinPayConfig;
	}
	
	/**
	 * 方法描述：获取微信公众平台、商户平台、开放平台ID、密钥信息
	
	 * @param branchSequence
	 * @return
	 * @throws Exception 
	 */
	public AppKeyInfo getAppKeyInfo(String branchSequence){
		
		if (CStringUtils.isEmpty(branchSequence)) {
			Log.logInfo("商户序号获取失败，请稍候再试!");
		}
		
		CacheManager cm = CacheManager.getInstance();
		AppKeyInfo appKeyInfo = (AppKeyInfo)cm.get(WEIXIN_KEY_PREFIX + branchSequence);
		if(null == appKeyInfo){
			// 重新初始化appKeyInfo
			if (null == weixinPayConfig) {
				weixinPayConfig = WeixinPayConfig.getInstance();
			}else{
				weixinPayConfig.initAppKeyInfo();
			}
			appKeyInfo = (AppKeyInfo)cm.get(WEIXIN_KEY_PREFIX + branchSequence);
			Log.logInfo("session中没有AppKeyInfo,重新请求数据库获取信息");
			// appKeyInfo依然没有获取到
			if (null == appKeyInfo) {
				Log.logInfo("未查询到商户微信公众平台、商户平台、开放平台ID、密钥信息。商户序号：" + branchSequence);
			}
		}
		return appKeyInfo;
	}
	
	/**
	 * 方法描述：初始化微信公众平台、商户平台、开放平台ID、密钥信息
	 */
	public void initAppKeyInfo(){
		Map<String, List<ParameterDictionaryBean>> mapAppKey = ParameterQuery.getInstance().getPDListByPrefix(WEIXIN_KEY_PREFIX);
		
		Iterator<String> it = mapAppKey.keySet().iterator();
		while(it.hasNext()){
			AppKeyInfo appKeyInfo = new AppKeyInfo();
			String key = it.next();
			
			List<ParameterDictionaryBean> lstPD = mapAppKey.get(key);
			for(int i = 0;null != lstPD && i < lstPD.size(); i++){
				ParameterDictionaryBean pd = lstPD.get(i);
				
				switch(pd.getPdCode()){
					case "APP_ID":
						appKeyInfo.setAppID(pd.getPdName());
						break;
					case "APP_SECRET":
						appKeyInfo.setAppSecret(pd.getPdName());
						break;	
					case "MCH_ID":
						appKeyInfo.setMchID(pd.getPdName());
						break;	
					case "API_KEY":
						appKeyInfo.setApiKey(pd.getPdName());
						break;	
					case "TEMPLATE_ID_BOOK_CUSTOMER":
						appKeyInfo.setCustomerBookTempLateID(pd.getPdName());
						break;	
					case "TEMPLATE_ID_BOOK_EMPLOYEE":
						appKeyInfo.setEmployeeBookTempLateID(pd.getPdName());
						break;	
					case "TEMPLATE_ID_PAYMENT":
						appKeyInfo.setPaymentTempLateID(pd.getPdName());
						break;	
					case "TEMPLATE_ID_CONSUME":
						appKeyInfo.setConsumeTempLateID(pd.getPdName());
						break;	
				}
			}
			
			CacheManager cm = CacheManager.getInstance();
			cm.put(appKeyInfo, key);
			Log.logInfo("初始化appKeyInfo配置信息:"+appKeyInfo);
		}
		
	}
	
}
