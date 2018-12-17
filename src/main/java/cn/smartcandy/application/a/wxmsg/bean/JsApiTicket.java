package cn.smartcandy.application.a.wxmsg.bean;
import java.util.Date;

import net.sf.json.JSONObject;

/**
  * 项目名称：zmjema
  * 类名称：JsApiTicket
  * 类描述：JsApiTicket对象
  * 创建人：lgbzc-surface
  * 创建时间：2017年7月17日 下午8:04:59
  * 修改人：
  * 修改时间：
  * 修改备注：
  * Company:北京闻壹信息有限公司 (C) 2017
  * @version 1.0
  */
public class JsApiTicket {
	private String ticket;
	private int expires_in;
	private long exprexpiredTime;
	private long create_time;

	public JsApiTicket(String ticket, int expiresIn) {
		this.ticket = ticket;
		setExpires_in(expiresIn);
	}

	public JsApiTicket(String ticket, int expiresIn, long createTime) {
		this.ticket = ticket;
		setExpires_in(expiresIn, createTime);
	}
	
	/**
	 * {"errcode":0,"errmsg":"ok","ticket":"bxLdikRXVbTPdHSM05e5u5sUoXNKdvsdshFKA","expires_in":7200}
	 * 
	 * @param jsonObj
	 */
	public JsApiTicket(JSONObject jsonObj) {
		this.ticket = jsonObj.getString("ticket");
		// 根据当前时间的毫秒数+获取的秒数计算过期时间
		int expiresIn = jsonObj.getInt("expires_in");
		if (jsonObj.containsKey("create_time")) {
			// 获取创建时间
			long createTime = jsonObj.getLong("create_time");
			if (createTime > 0) {
				// 设定指定日期
				setExpires_in(expiresIn, createTime);
				return;
			}
		}
		setExpires_in(expiresIn);
	}

	/**
	 * 判断js api ticket是否过期
	 *
	 * @return 过期返回 true,否则返回false
	 */
	public boolean isExprexpired() {
		Date now = new Date();
		long nowLong = now.getTime();
		return nowLong >= exprexpiredTime;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getExpires_in() {
		return expires_in;
	}

	/**
	 * 设置 凭证有效时间，单位：秒
	 *
	 * <p>
	 * 为了与微信服务器保存同步，误差设置为提前1分钟，即：将创建时间提早1分钟
	 * </p>
	 *
	 * @param expires_in
	 *            凭证有效时间，单位：秒
	 */
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
		// 获取当前时间
		Date now = new Date();
		// 获取当前时间毫秒数 - 1 分钟，即提前1分钟过期
		create_time = now.getTime() - 60000;
		// 设置下次过期时间 = 当前时间 + (凭证有效时间(秒) * 1000)
		this.exprexpiredTime = create_time + (expires_in * 1000);
	}

	/**
	 * 设置 凭证有效时间，单位：秒
	 *
	 * <p>
	 * 为了与微信服务器保存同步，误差设置为提前1分钟，即：将创建时间提早1分钟
	 * </p>
	 *
	 * @param expires_in
	 *            凭证有效时间，单位：秒
	 * @param createTime
	 *            凭证创建时间
	 */
	public void setExpires_in(int expires_in, long createTime) {
		this.expires_in = expires_in;
		// 获取当前时间毫秒数
		create_time = createTime - 60000;
		// 设置下次过期时间 = 当前时间 + (凭证有效时间(秒) * 1000)
		this.exprexpiredTime = create_time + (expires_in * 1000);
	}

	/**
	 * 获取 此次凭证创建时间 单位：毫秒数
	 *
	 * @return 创建时间 毫秒数
	 */
	public long getCreate_time() {
		return this.create_time + 60000;
	}
}
