package cn.smartcandy.common.alimsg;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.smartcandy.framework.core.exception.BusinessException;
import cn.smartcandy.framework.utils.CStringUtils;
import cn.smartcandy.framework.web.struts.BaseAction;

public class AliMsgAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 方法描述：身份验证：短信发送
	 * 接口地址： aliyun_sendAliMsgIdVerify.action
	 * @throws IOException
	 * @return void
	 * @throws BusinessException
	 */
	public void sendAliMsg() throws IOException {
		//1.用户名称
		Map<String, String>  map  = new HashMap<>();

		//2.验证码
		String smsCheckCode = CStringUtils.produceRandomNumberArray(4);
		session.setAttribute("smsCheckCode", smsCheckCode);
		map.put("code", smsCheckCode);
		
		//4.操作序号
		String templateId = super.getParameter("templateId");
		map.put("templateId", templateId);
		
		//5.String【】里面装载电话号码(暂不支持多用户操作,只能上传一个手机号)
		String[] phoneNum = super.getParameterValues("phoneNums");
		
		//6. templateId:	//1.信息变更	2.修改密码		3.活动确认		4.用户注册		
							//5.登录异常	6.登录确认		7.短信测试		8.身份验证
							//9.预约成功	10.预约提醒
		Boolean flag = AliMsgSendUtil.sendAliMsg(map, phoneNum);
		request.setAttribute("successInfo", flag);
	}

}
