package cn.smartcandy.common.alimsg;

/**
 * @项目名称：zmjema
 * @类名称：AliMsgCodeEnum
 * @类描述：阿里短信平台-短信签名枚举
 * @创建人：tangzhifeng
 * @创建时间：2017年8月1日 下午9:46:17
 * @修改人：someOne
 * @修改时间：2017年8月1日 下午9:46:17 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public enum AliMsgCodeEnum {
	
	USER_MSG_CHANGE(1,"SMS_72520001"),		// 信息变更模板CODE
	USER_PWD_CHANGE(2,"SMS_72520002"),		// 修改密码模板CODE
	USER_ACT_MAKESURE(3,"SMS_72520001"),	// 活动确认模板CODE
	USER_MSG_REGISTER(4,"SMS_72520004"),	// 用户注册模板CODE
	USER_LOGIN_ERROR(5,"SMS_72520005"),		// 信息变更模板CODE
	USER_MSG_LOGIN(6,"SMS_72520006");		// 信息变更模板CODE

    // 成员变量  
    private int templateId;  
    private String templateCode;  

    // 构造方法  
    private AliMsgCodeEnum(int templateId, String templateCode) {  
        this.templateId = templateId;  
        this.templateCode = templateCode;  
    } 

	// 取值
	public static String getValueCode(int templateId){
		String code  = null;
        for (AliMsgCodeEnum e: AliMsgCodeEnum.values()){
            if(e.getTemplateId() == templateId){
            	code = e.getTemplateCode();
                break;
            }
        }
        return code;
    }
	
	public int getTemplateId() {
		return templateId;
	}
	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
}
