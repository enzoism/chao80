package cn.smartcandy.application.cms.release;

import java.io.Serializable;

/**
 * @项目名称：zmjema
 * @类名称：ReleasePhoto
 * @类描述：藏品生活照
 * @创建人：tangzhifeng
 * @创建时间：2017年10月29日 上午10:36:27
 * @修改人：someOne
 * @修改时间：2017年10月29日 上午10:36:27 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class ReleasePhoto implements Serializable{

	private static final long serialVersionUID = 3333156121667114276L;
	// 状态
	public static final String STATUS_NORMAL = "N";					// 正常
	public static final String STATUS_BLACK = "B";					// 黑名单
	public static final String STATUS_DELETE = "D";					// 注销
	public static final String STATUS_READ = "R";					// 已读
	private String photoSequence;			//序号名称
	private String photoTitle;				//主标题
	private String photoBrief;				//详情简介
	private String photoURL;				//图片地址
	private String photoRelType;			//标题类型：0:Banner  1:News  2:Video 3:媒体
	private String photoStatus;				//图片状态
	private String colSequence;				//藏品序号
	private String userSequence;			//用户序号
	private String userName;				//用户名称
	
	
	public String getColSequence() {
		return colSequence;
	}
	public void setColSequence(String colSequence) {
		this.colSequence = colSequence;
	}
	public String getUserSequence() {
		return userSequence;
	}
	public void setUserSequence(String userSequence) {
		this.userSequence = userSequence;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhotoSequence() {
		return photoSequence;
	}
	public void setPhotoSequence(String photoSequence) {
		this.photoSequence = photoSequence;
	}
	public String getPhotoTitle() {
		return photoTitle;
	}
	public void setPhotoTitle(String photoTitle) {
		this.photoTitle = photoTitle;
	}
	public String getPhotoBrief() {
		return photoBrief;
	}
	public void setPhotoBrief(String photoBrief) {
		this.photoBrief = photoBrief;
	}
	public String getPhotoURL() {
		return photoURL;
	}
	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}
	public String getPhotoRelType() {
		return photoRelType;
	}
	public void setPhotoRelType(String photoRelType) {
		this.photoRelType = photoRelType;
	}
	public String getPhotoStatus() {
		return photoStatus;
	}
	public void setPhotoStatus(String photoStatus) {
		this.photoStatus = photoStatus;
	}
	@Override
	public String toString() {
		return "ReleasePhoto [colSequence=" + colSequence + ", userSequence=" + userSequence + ", userName=" + userName
				+ ", photoSequence=" + photoSequence + ", photoTitle=" + photoTitle + ", photoBrief=" + photoBrief
				+ ", photoURL=" + photoURL + ", photoRelType=" + photoRelType + ", photoStatus=" + photoStatus + "]";
	}
	

}
