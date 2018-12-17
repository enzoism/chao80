package cn.smartcandy.application.a.tag;

import java.io.Serializable;

/**
 * @项目名称：zmjema
 * @类名称：CollectionTag
 * @类描述：藏品标签
 * @创建人：tangzhifeng
 * @创建时间：2017年11月10日 上午11:12:34
 * @修改人：someOne
 * @修改时间：2017年11月10日 上午11:12:34 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class CollectionTag implements Serializable{
	
	private static final long serialVersionUID = 1859651580662177320L;
	// 状态
	public static final String STATUS_NORMAL = "N";					//正常
	public static final String STATUS_BLACK = "B";					//黑名单
	public static final String STATUS_DELETE = "D";					//注销

	private String tagSequence;					// 序号
	private String tagName;						// 昵称
	private String tagStatus;					// 状态
	private String tagDate;						// 创建日期
	public String getTagSequence() {
		return tagSequence;
	}
	public void setTagSequence(String tagSequence) {
		this.tagSequence = tagSequence;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getTagStatus() {
		return tagStatus;
	}
	public void setTagStatus(String tagStatus) {
		this.tagStatus = tagStatus;
	}
	public String getTagDate() {
		return tagDate;
	}
	public void setTagDate(String tagDate) {
		this.tagDate = tagDate;
	}
	@Override
	public String toString() {
		return "CollectionTag [tagSequence=" + tagSequence + ", tagName=" + tagName + ", tagStatus=" + tagStatus
				+ ", tagDate=" + tagDate + "]";
	}
	
}
