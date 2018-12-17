package cn.smartcandy.application.a.account;

import java.io.Serializable;

public class UserAttention implements Serializable{

	private static final long serialVersionUID = -2968270516470593722L;
	
	private String attentSequence;			// 用户序号
	private String userSequence;			// 用户序号
	private String relUserSequence;			// 关联序号
	
	
	public String getUserSequence() {
		return userSequence;
	}
	public String getAttentSequence() {
		return attentSequence;
	}
	public void setAttentSequence(String attentSequence) {
		this.attentSequence = attentSequence;
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
		return "UserAttention [userSequence=" + userSequence + ", relUserSequence=" + relUserSequence + "]";
	}

}
