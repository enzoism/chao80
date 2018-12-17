package cn.smartcandy.application.a.wxmsg.bean;

/**
  * 项目名称：zmjema
  * 类名称：AppKeyInfo
  * 类描述：微信公众平台、商户平台、开放平台ID、密钥信息
  * 创建人：lgbzc-surface
  * 创建时间：2017年7月17日 下午4:36:14
  * 修改人：
  * 修改时间：
  * 修改备注：
  * Company:北京闻壹信息有限公司 (C) 2017
  * @version 1.0
  */
public class AppKeyInfo {
	
	/**公众平台*/
	private String appID;//公众号id
	private String appSecret;//APP_ID对应密钥
	
	/**商户平台*/
	private String mchID;//商户号
	private String apiKey;////密钥(生成签名需要)
	
	private String customerBookTempLateID;//预约模板消息ID
	private String employeeBookTempLateID;//预约模板消息ID
	private String paymentTempLateID;//支付模板消息ID
	private String consumeTempLateID;//消费模板消息ID
	
	/**
	 * @return the appID
	 */
	public String getAppID() {
		return appID;
	}
	/**
	 * @param appID the appID to set
	 */
	public void setAppID(String appID) {
		this.appID = appID;
	}
	/**
	 * @return the appSecret
	 */
	public String getAppSecret() {
		return appSecret;
	}
	/**
	 * @param appSecret the appSecret to set
	 */
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	/**
	 * @return the mchID
	 */
	public String getMchID() {
		return mchID;
	}
	/**
	 * @param mchID the mchID to set
	 */
	public void setMchID(String mchID) {
		this.mchID = mchID;
	}
	/**
	 * @return the apiKey
	 */
	public String getApiKey() {
		return apiKey;
	}
	/**
	 * @param apiKey the apiKey to set
	 */
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getPaymentTempLateID() {
		return paymentTempLateID;
	}
	public void setPaymentTempLateID(String paymentTempLateID) {
		this.paymentTempLateID = paymentTempLateID;
	}
	public String getConsumeTempLateID() {
		return consumeTempLateID;
	}
	public void setConsumeTempLateID(String consumeTempLateID) {
		this.consumeTempLateID = consumeTempLateID;
	}
	public String getCustomerBookTempLateID() {
		return customerBookTempLateID;
	}
	public void setCustomerBookTempLateID(String customerBookTempLateID) {
		this.customerBookTempLateID = customerBookTempLateID;
	}
	public String getEmployeeBookTempLateID() {
		return employeeBookTempLateID;
	}
	public void setEmployeeBookTempLateID(String employeeBookTempLateID) {
		this.employeeBookTempLateID = employeeBookTempLateID;
	}
	@Override
	public String toString() {
		return "AppKeyInfo [appID=" + appID + ", appSecret=" + appSecret + ", mchID=" + mchID + ", apiKey=" + apiKey
				+ ", customerBookTempLateID=" + customerBookTempLateID + ", employeeBookTempLateID="
				+ employeeBookTempLateID + ", paymentTempLateID=" + paymentTempLateID + ", consumeTempLateID="
				+ consumeTempLateID + "]";
	}
	
	
}
