package cn.smartcandy.application.a.attention;

import java.io.Serializable;

/**
 * @项目名称：zmjema
 * @类名称：AttentionMsg
 * @类描述：关注消息通知
 * @创建人：tangzhifeng
 * @创建时间：2017年11月13日 下午11:14:12
 * @修改人：someOne
 * @修改时间：2017年11月13日 下午11:14:12 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class AttentionMsg implements Serializable,Comparable<AttentionMsg>{

	private static final long serialVersionUID = -7545095432029161158L;
	// 状态
	public static final String STATUS_NORMAL = "N";					// 正常
	public static final String STATUS_BLACK = "B";					// 黑名单
	public static final String STATUS_DELETE = "D";					// 删除
	
	public static final String TYPE_ATT_USER = "U";					// 关注用户
	public static final String TYPE_ATT_COLL = "C";					// 关注藏品
	
	private String msgSequence;							// 序号
	private String msgStatus;							// 状态
	private String userSequence;						// 关注的人
	private String colSequence;							// 关注的藏品
	private String msgType;								// 关注的类型（人/藏品）
	private String msgDate;								// 日期
	private String operSequence;						// 操作人
	
	// 辅助
	private String colTitle;						// 操作人
	private String colThumb;						// 操作人
	private String userName;						// 操作人
	private String userImageURL;						// 操作人

	
	public String getColTitle() {
		return colTitle;
	}
	public void setColTitle(String colTitle) {
		this.colTitle = colTitle;
	}
	public String getColThumb() {
		return colThumb;
	}
	public void setColThumb(String colThumb) {
		this.colThumb = colThumb;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserImageURL() {
		return userImageURL;
	}
	public void setUserImageURL(String userImageURL) {
		this.userImageURL = userImageURL;
	}
	public String getMsgSequence() {
		return msgSequence;
	}
	public void setMsgSequence(String msgSequence) {
		this.msgSequence = msgSequence;
	}
	public String getMsgStatus() {
		return msgStatus;
	}
	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}
	public String getUserSequence() {
		return userSequence;
	}
	public void setUserSequence(String userSequence) {
		this.userSequence = userSequence;
	}
	public String getColSequence() {
		return colSequence;
	}
	public void setColSequence(String colSequence) {
		this.colSequence = colSequence;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgDate() {
		return msgDate;
	}
	public void setMsgDate(String msgDate) {
		this.msgDate = msgDate;
	}
	public String getOperSequence() {
		return operSequence;
	}
	public void setOperSequence(String operSequence) {
		this.operSequence = operSequence;
	}
	@Override
	public String toString() {
		return "AttentionMsg [msgSequence=" + msgSequence + ", msgStatus=" + msgStatus + ", userSequence="
				+ userSequence + ", colSequence=" + colSequence + ", msgType=" + msgType + ", msgDate=" + msgDate
				+ ", operSequence=" + operSequence + "]";
	}
	@Override
	public int compareTo(AttentionMsg msg) {
		int compare = this.msgDate.compareTo(msg.msgDate);
		return -compare;
	}
	
}
