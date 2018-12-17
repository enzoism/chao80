package cn.smartcandy.common.alimsg;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import cn.smartcandy.framework.core.exception.BusinessException;
import cn.smartcandy.framework.core.log.Log;
import cn.smartcandy.framework.utils.CStringUtils;
import cn.smartcandy.framework.utils.JSONUtils;

/**
 * @项目名称：zmjema
 * @类名称：AliMsgSendUtil
 * @类描述：AliMsgSendUtil
 * @创建人：tangzhifeng
 * @创建时间：2017年7月11日 下午2:42:10
 * @修改人：someOne
 * @修改时间：2017年7月11日 下午2:42:10 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class AliMsgSendUtil {
	// 1.不变常量
	private final static String aliyun_sms_region_id = "cn-hangzhou"; 								// 阿里云regionId
	private final static String aliyun_sms_access_key_id = "LTAI7k7nnpYZoXvx"; 						// 阿里云 accessKeyId
	private final static String aliyun_sms_access_key_secret = "2ystZLR3cRU7R10gOKzTrB1KJ6iyx5"; 	// 阿里云 accessKeySecret
	private final static String aliyun_sms_end_point_name = "cn-hangzhou"; 							// 阿里云end_point_name
	private final static String aliyun_sms_product = "Dysmsapi"; 									// 阿里云产品
	private final static String aliyun_sms_domain = "dysmsapi.aliyuncs.com"; 						// 阿里云调用短信服务平台接口域名
	
	// 2.错误码
	public final static String CODE_OK = "OK"; 											// 请求成功
	public final static String CODE_DENY = "isp.RAM_PERMISSION_DENY"; 					// RAM权限DENY
	public final static String CODE_OUT_SERVICE = "isv.OUT_OF_SERVICE"; 				// 业务停机
	public final static String CODE_UN_SUBSCRIPT = "isv.PRODUCT_UN_SUBSCRIPT"; 			// 未开通云通信产品的阿里云客户
	public final static String CODE_UNSUBSCRIBE = "isv.PRODUCT_UNSUBSCRIBE"; 			// 产品未开通
	public final static String CODE_NOT_EXISTS = "isv.ACCOUNT_NOT_EXISTS"; 				// 账户不存在
	public final static String CODE_ABNORMAL = "isv.ACCOUNT_ABNORMAL"; 					// 账户异常
	public final static String CODE_TEMPLATE_ILLEGAL = "isv.SMS_TEMPLATE_ILLEGAL"; 		// 短信模板不合法
	public final static String CODE_SIGNATURE_ILLEGAL = "isv.SMS_SIGNATURE_ILLEGAL"; 	// 短信签名不合法
	public final static String CODE_INVALID_PARAMETERS = "isv.INVALID_PARAMETERS"; 		// 参数异常
	public final static String CODE_SYSTEM_ERROR = "isp.SYSTEM_ERROR"; 					// 系统错误
	public final static String CODE_NUMBER_ILLEGAL = "isv.MOBILE_NUMBER_ILLEGAL"; 		// 非法手机号
	public final static String CODE_OVER_LIMIT = "isv.MOBILE_COUNT_OVER_LIMIT"; 		// 手机号码数量超过限制
	public final static String CODE_MISSING_PARAMETERS = "isv.TEMPLATE_MISSING_PARAMETERS"; // 模板缺少变量
	public final static String CODE_LIMIT_CONTROL = "isv.BUSINESS_LIMIT_CONTROL"; 		// 业务限流
	public final static String CODE_JSON_PARAM = "isv.INVALID_JSON_PARAM"; 				// JSON参数不合法，只接受字符串值
	public final static String CODE_CONTROL_LIMIT = "isv.BLACK_KEY_CONTROL_LIMIT"; 		// 黑名单管控
	public final static String CODE_LENGTH_LIMIT = "isv.PARAM_LENGTH_LIMIT"; 			// 参数超出长度限制
	public final static String CODE_NOT_SUPPORT_URL = "isv.PARAM_NOT_SUPPORT_URL"; 		// 不支持URL
	public final static String CODE_NOT_ENOUGH = "isv.AMOUNT_NOT_ENOUGH"; 				// 账户余额不足
	
	// 3.可变参数
	private  String templateCode = ""; 			// 短信模板Code
	private  String templateParam = ""; 		// 短信模板参数字符串
	private  String phoneNum = ""; 				// 发送短信手机号
	private  String singName="智慧糖果云平台"; 		// 签名名称(默认)
	private  String product="智慧糖果"; 			// 显示名称(默认)

	// 1.验证参数有效性
	private static AliMsgSendUtil verifyParamEffective(Map<String, String> paramMap,String[] phoneNums){
		AliMsgSendUtil aliMsg = new AliMsgSendUtil();
		try {
			//1.验证手机号
			if (ArrayUtils.isEmpty(phoneNums)) {
				throw new BusinessException("手机号码不能为空");
			}
			
			//2.获取短信签名
			String templateId = paramMap.get("templateId");
			if (CStringUtils.isEmpty(templateId)) {
				throw new BusinessException("短信请求参数不合法");
			}
			aliMsg.templateCode = AliMsgCodeEnum.getValueCode(Integer.valueOf(templateId));		//短信模板
			aliMsg.phoneNum = jsonWithPhoneNums(phoneNums);										//手机号码
			aliMsg.singName = "智慧糖果云平台";														//短信签名
			aliMsg.product = "智慧糖果";															//公司名称	
			
			//3.格式化参数
			paramMap.put("product", aliMsg.product);
			aliMsg.templateParam = JSONUtils.toJSONString(paramMap);

		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return aliMsg;
	}
	// 2.格式手机号
	private static String jsonWithPhoneNums(String[] phoneNums){
		StringBuffer recNumSB = new StringBuffer();
		for (int i = 0; i < phoneNums.length; i++) {
			if (i == phoneNums.length - 1) {
				recNumSB.append(phoneNums[i]);
			} else {
				recNumSB.append(phoneNums[i] + ",");
			}
		}
		return recNumSB.toString();
	}
	
	//3.真实发送短信方法
	public static boolean sendAliMsg(Map<String, String> paramMap,String[] phoneNums) {
		boolean isSendSuccess = false;
		try {
			//1.准备参数
			AliMsgSendUtil aliMsg = verifyParamEffective(paramMap,phoneNums);
			//2.配置参数
			IClientProfile profile = DefaultProfile.getProfile(aliyun_sms_region_id, aliyun_sms_access_key_id,
					aliyun_sms_access_key_secret);
			DefaultProfile.addEndpoint(aliyun_sms_end_point_name, aliyun_sms_region_id, aliyun_sms_product,
					aliyun_sms_domain);
			IAcsClient client = new DefaultAcsClient(profile);
			SendSmsRequest request = new SendSmsRequest();
			request.setSignName(aliMsg.singName);
			request.setTemplateCode(aliMsg.templateCode);
			request.setTemplateParam(aliMsg.templateParam);
			request.setPhoneNumbers(aliMsg.phoneNum);		
			
			//3.阿里短信查询
			SendSmsResponse response = client.getAcsResponse(request);// 短信发送
			Log.logInfo("阿里短信反馈码："+response.getCode());

			//4.短信状态
			if (response.getCode() != null) {
				if (response.getCode().equals(AliMsgSendUtil.CODE_OK)) {
					isSendSuccess = true;
				}else if (response.getCode().equals(AliMsgSendUtil.CODE_OUT_SERVICE)) {
					throw new BusinessException("业务停机，短信发送异常，请稍候再试！");
				}else if (response.getCode().equals(AliMsgSendUtil.CODE_NOT_EXISTS)) {
					throw new BusinessException("账户不存在，短信发送异常，请稍候再试！");
				}else if (response.getCode().equals(AliMsgSendUtil.CODE_ABNORMAL)) {
					throw new BusinessException("账户异常，短信发送异常，请稍候再试！");
				}else if (response.getCode().equals(AliMsgSendUtil.CODE_INVALID_PARAMETERS)) {
					throw new BusinessException("参数异常，短信发送异常，请稍候再试！");
				}else if (response.getCode().equals(AliMsgSendUtil.CODE_NUMBER_ILLEGAL)) {
					throw new BusinessException("非法手机号，短信发送异常，请稍候再试！");
				}else if (response.getCode().equals(AliMsgSendUtil.CODE_OVER_LIMIT)) {
					throw new BusinessException("手机号码数量超过限制，短信发送异常，请稍候再试！");
				}else if (response.getCode().equals(AliMsgSendUtil.CODE_LIMIT_CONTROL)) {
					throw new BusinessException("业务限流，短信发送异常，请稍候再试！");
				}else if (response.getCode().equals(AliMsgSendUtil.CODE_CONTROL_LIMIT)) {
					throw new BusinessException("黑名单管控，短信发送异常，请稍候再试！");
				}else {
					Log.logError("其他原因导致短信发送异常，请稍候再试！错误码：" + response.getCode());
					throw new BusinessException("其他原因导致短信发送异常，请稍候再试！");
				}
			}else{
				throw new BusinessException("其他原因导致短信发送异常，请稍候再试！");
			}
		}catch (BusinessException e) {
			throw e;
		}  catch (Exception e) {
			throw new BusinessException(e);
		} finally {
		
		}
		return isSendSuccess;
	}

	public static void main(String[] args) {
		//1.参数准备
		Map<String, String>  map  = new HashMap<>();
		String smsCheckCode = CStringUtils.produceRandomNumberArray(4);
		map.put("code", smsCheckCode);
		
		//2.String【】里面装载电话号码(暂不支持多用户操作,只能上传一个手机号)
		String[] phoneNums = new String[]{"15300248680"};//15538551102//15901482654//18258128870//
		
		//3. templateId:1.信息变更		2.修改密码		3.活动确认		4.用户注册		5.登录异常		6.登录确认		7.短信测试		8.身份验证
		map.put("templateId", "4");
		
		//4.其他数据
		map.put("customer", "DowneyJr");
		
		//5.成功与否
		Boolean flag = AliMsgSendUtil.sendAliMsg(map,phoneNums);
		System.out.println("有没有发送成功:"+flag);
		
	}

}
