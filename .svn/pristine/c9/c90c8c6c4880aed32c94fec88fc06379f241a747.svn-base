package cn.smartcandy.application.a.wxmsg.bean;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import cn.smartcandy.application.a.wxmsg.api.TokenApi;
import cn.smartcandy.application.a.wxmsg.api.WebPageApi;
import cn.smartcandy.framework.core.log.Log;
import cn.smartcandy.framework.utils.MD5Utils;  


/**
 * 项目名称：zmjema
 * 类名称：SignUtils  
 * 类描述：创建微信签名
 * 创建人：guoyanmei
 * 创建时间：2017-7-15 上午12:56
 * 修改人：  
 * 修改时间：
 * 修改备注：
 * Company:zmjema & HLS (C) 2016
 * @version 1.0
 */
public class SignUtils{
	
	/**
	 * 方法描述：判断签名
	 * @param characterEncoding
	 * @param packageParams
	 * @param API_KEY
	 * @return
	 */
	public static boolean isTrueSign(String characterEncoding, Map<String, String> packageParams, String API_KEY){
		StringBuffer stringBuffer = new StringBuffer();
		Iterator<String> ite = packageParams.keySet().iterator();
		while(ite.hasNext()){
			String key = (String) ite.next();
			String value = (String) packageParams.get(key);
			if(!"sign".equals(key) && null != value && "".equals(value)){
				stringBuffer.append(key + "=" + value +"&");
			}
		}
		stringBuffer.append("key=" + API_KEY);//拼接API_KEY
		
		//算摘要
		String mySign = MD5Utils.md5Encode(stringBuffer.toString(), characterEncoding).toLowerCase();
		String tenpaySign = ((String) packageParams.get("sign")).toLowerCase();
		return mySign.equals(tenpaySign);
	}

	/**
	 * 方法描述：sign签名
	 * @param characterEncoding
	 * @param packageParams
	 * @param API_KEY
	 * @return
	 */
	public static String createSign(String characterEncoding, Map<String, String> packageParams, String API_KEY) {  
	    StringBuffer stringBuffer = new StringBuffer();  
	    Iterator<String> ite = packageParams.keySet().iterator();
		while(ite.hasNext()){
			String key = (String) ite.next();
			String value = (String) packageParams.get(key); 
	        if (null != value && !"".equals(value) && !"sign".equals(key) && !"key".equals(key)) {  
	        	stringBuffer.append(key + "=" + value + "&");  
	        }  
	    }  
	    
	    stringBuffer.append("key=" + API_KEY);  
	    String sign = MD5Utils.md5Encode(stringBuffer.toString(), characterEncoding).toUpperCase(); 
	    return sign;  
	}
	
	/**
	 * 方法描述：创建微信支付的jsapi签名
	 * @param characterEncoding
	 * @param packageParams
	 * @param API_KEY
	 * @return
	 */
    public static Map<String, Object> produceSign(HttpServletRequest request) {
    	Map<String, Object> ret = new HashMap<String, Object>();
    	/*确认url是页面完整的url(请在当前页面alert(location.href.split('#')[0])确认)，
        	包括'http(s)://'部分，以及'？'后面的GET参数部分,但不包括'#'hash后面的部分。
        	签名url必须与支付页url保持一致，否则签名无效
    	 */
    	String url = request.getHeader("Referer"); 
    	String branchSequence = (String) request.getSession().getAttribute("branchSequence");
    	
    	ret = signature(url, branchSequence, ret);

        return ret;
    }

    /**
     * @方法描述:创建微信jsapi签名
     *  
     * @param url
     * @param branchSequence
     * @param ret
     * @return Map<String,Object>
     */
    public static Map<String, Object> signature(String url, String branchSequence, Map<String, Object> ret){
        String nonce_str = create_nonce_str();				//随机字符串
        String timestamp = create_timestamp();				//时间戳
        String weChatJsApiURL;
        String signature = StringUtils.EMPTY;
    	String jsapi_ticket = getJsApiTicket(branchSequence);//微信JS接口的临时票据
	     Log.logInfo("传递jsapi_ticket参数："+jsapi_ticket);
       //注意这里参数名(key)必须全部小写，且必须有序
	     weChatJsApiURL = "jsapi_ticket=" + jsapi_ticket +
                 "&noncestr=" + nonce_str +
                 "&timestamp=" + timestamp +
                 "&url=" + url;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(weChatJsApiURL.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("s_noncestr", nonce_str);
        ret.put("s_timestamp", timestamp);
        ret.put("signature", signature);
        
        return ret;
    }
    
    private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    public static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    
    /**
     * 方法描述：获取JsApiTicket
    
     * @param branchSequence
     * @return
     */
    public static String getJsApiTicket(String branchSequence){
        // jsapi_ticket 应该全局存储与更新，以下代码以写入到文件中做示例
       //TODO 缓存jsapi_ticket
    	OAuthToken accessToken = null;
		accessToken = TokenApi.getAccessToken(branchSequence);
		JsApiTicket jsapi_ticket = WebPageApi.getJsApiTicket("jsapi", branchSequence, accessToken.getAccess_token());
    	
        return jsapi_ticket.getTicket();
      }
    
    
	/**
	 * 方法描述：paySign支付签名
	 * @param characterEncoding
	 * @param prepay_id
	 * @param API_KEY
	 * @return
	 */
	public static  Map<String, String> createPaySign(String characterEncoding, Map<String, String>  params, String API_KEY) {  
		Map<String, String> sortMap = new TreeMap<String, String>();
		sortMap.putAll(params);
		// 以k1=v1&k2=v2...方式拼接参数
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, String> s : sortMap.entrySet()) {
			String k = s.getKey();
			String v = s.getValue();
			if (StringUtils.isBlank(v)) {// 过滤空值
				continue;
			}
			builder.append(k).append("=").append(v).append("&");
		}
		if (!sortMap.isEmpty()) {
			builder.deleteCharAt(builder.length() - 1);
		}
		builder.append("&key=" + API_KEY);// 拼接API_KEY
		// md5加密，全部转成大写
		String paySign = MD5Utils.md5Encode(builder.toString(), characterEncoding).toUpperCase();
		sortMap.put("paySign", paySign);
		return sortMap;
   }

}
