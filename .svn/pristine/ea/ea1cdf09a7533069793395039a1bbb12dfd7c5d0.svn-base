package cn.smartcandy.application.a.attention;

import java.io.Serializable;

public class UserAttention implements Serializable{

	private static final long serialVersionUID = 7531027618222764907L;
	// 状态
	public static final String STATUS_NORMAL = "N";					// 正常
	public static final String STATUS_BLACK = "B";					// 黑名单
	public static final String STATUS_DELETE = "D";					// 注销	
	
	private String attentSequence;						// 序号
	private String attentDate;							// 日期
	private String attentStatus;							// 日期
	private String userSequence;						// 序号
	private String relUserSequence;						// 序号
	
	public String getAttentStatus() {
		return attentStatus;
	}
	public void setAttentStatus(String attentStatus) {
		this.attentStatus = attentStatus;
	}
	public String getAttentSequence() {
		return attentSequence;
	}
	public void setAttentSequence(String attentSequence) {
		this.attentSequence = attentSequence;
	}
	public String getAttentDate() {
		return attentDate;
	}
	public void setAttentDate(String attentDate) {
		this.attentDate = attentDate;
	}
	public String getUserSequence() {
		return userSequence;
	}
	public void setUserSequence(String userSequence) {
		this.userSequence = userSequence;
	}
	public String getRelUserSequence() {
		return relUserSequence;
	}
	public void setRelUserSequence(String relUserSequence) {
		this.relUserSequence = relUserSequence;
	}
	@Override
	public String toString() {
		return "UserAttention [attentSequence=" + attentSequence + ", attentDate=" + attentDate + ", userSequence="
				+ userSequence + ", relUserSequence=" + relUserSequence + "]";
	}
}
