package cn.smartcandy.application.a.thirtyLogin;

import java.io.Serializable;

/**
 * @项目名称：zmjema
 * @类名称：LoginUeab
 * @类描述：绑定实体类
 * @创建人：tangzhifeng
 * @创建时间：2017年11月17日 上午11:50:03
 * @修改人：someOne
 * @修改时间：2017年11月17日 上午11:50:03 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class LoginUeab implements Serializable{
	private static final long serialVersionUID = 8162656110833416193L;
	// 状态
	public static final String STATUS_NULL = "U";					//空状态
	public static final String STATUS_NORMAL = "N";					//正常
	public static final String STATUS_BLACK = "B";					//黑名单
	public static final String STATUS_DELETE = "D";					//注销

	private String ueabSequence;				// 绑定号
	private String userSequence;				// 用户昵称
	private String openID;						// openID
	private String unionID;						// unionID
	private String ueabType;					// 绑定类型
	private String ueabBindDate;				// 绑定时间
	private String ueabUnBindDate;				// 解绑时间
	public String getUeabSequence() {
		return ueabSequence;
	}
	public void setUeabSequence(String ueabSequence) {
		this.ueabSequence = ueabSequence;
	}
	public String getUserSequence() {
		return userSequence;
	}
	public void setUserSequence(String userSequence) {
		this.userSequence = userSequence;
	}
	public String getOpenID() {
		return openID;
	}
	public void setOpenID(String openID) {
		this.openID = openID;
	}
	public String getUnionID() {
		return unionID;
	}
	public void setUnionID(String unionID) {
		this.unionID = unionID;
	}
	public String getUeabType() {
		return ueabType;
	}
	public void setUeabType(String ueabType) {
		this.ueabType = ueabType;
	}
	public String getUeabBindDate() {
		return ueabBindDate;
	}
	public void setUeabBindDate(String ueabBindDate) {
		this.ueabBindDate = ueabBindDate;
	}
	public String getUeabUnBindDate() {
		return ueabUnBindDate;
	}
	public void setUeabUnBindDate(String ueabUnBindDate) {
		this.ueabUnBindDate = ueabUnBindDate;
	}
	@Override
	public String toString() {
		return "LoginUeab [ueabSequence=" + ueabSequence + ", userSequence=" + userSequence + ", openID=" + openID
				+ ", unionID=" + unionID + ", ueabType=" + ueabType + ", ueabBindDate=" + ueabBindDate
				+ ", ueabUnBindDate=" + ueabUnBindDate + "]";
	}
	
}
