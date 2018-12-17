package cn.smartcandy.application.a.wxmsg.bean;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

import cn.smartcandy.framework.utils.CStringUtils;

public class CommonUtil {

	/**
	 * 方法描述：将请求参数转换成xml格式的String
	 * @param param
	 * @return
	 */
	public static String getRequestXml(Map<String, String> param){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<xml>");
		Iterator<String> ite = param.keySet().iterator();
		while(ite.hasNext()){
			String key = (String) ite.next();
			String value = (String) param.get(key);
			if("attach".equalsIgnoreCase(key) || "body".equalsIgnoreCase(key) || "sign".equalsIgnoreCase(key)){
				stringBuffer.append("<" + key + ">" + "<![CDATA[" + value +"]]></" + key + ">");
			}else{
				stringBuffer.append("<" + key + ">" + value + "</" + key + ">");
			}
		}
		stringBuffer.append("</xml>");
		return stringBuffer.toString();
	}
	
	/**
	 * 方法描述：获取随机字符串
	 * @return
	 */
	public static String getNonce_str() {
		return  CStringUtils.produceRandomCharArray(16, 1);
	}
	
	/**
	 * 方法描述：返回给微信的参数
	 * @param return_code
	 * @param return_msg
	 * @return
	 */
	public static String setXML(String return_code, String return_msg) {  
		return "<xml><return_code><![CDATA[" + return_code  
				+ "]]></return_code><return_msg><![CDATA[" + return_msg  
				+ "]]></return_msg></xml>";  
	}
	
	/**
	 * 方法描述:将金额的元转换为分(微信接收的单位为：分)
	 * 
	 * @param total_fee
	 * @return
	 */
	public static String changeYtoF(String total_fee){ 
		return new BigDecimal(100).multiply(new BigDecimal(total_fee)).setScale(0).toString();
	}
	
	/**
	 * 方法描述：将金额的分转换为元
	 * 
	 * @param total_fee
	 * @return
	 */
	public static String changeFtoY(String total_fee){
		return new BigDecimal(total_fee).divide(new BigDecimal(100)).setScale(2).toString();
	}
}
