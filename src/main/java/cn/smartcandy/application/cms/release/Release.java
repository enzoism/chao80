package cn.smartcandy.application.cms.release;

import java.io.Serializable;

public class Release implements Serializable{
	private static final long serialVersionUID = 4395428104259525363L;
	// 状态
	public static final String STATUS_NORMAL = "N";					// 正常
	public static final String STATUS_BLACK = "B";					// 黑名单
	public static final String STATUS_DELETE = "D";					// 注销
	
	// 所属类型
	public static final String RELEASE_TYPE_BANNER = "0";					// 轮播图
	public static final String RELEASE_TYPE_NEWS = "1";						// 新闻
	public static final String RELEASE_TYPE_VIDEO = "2";					// 视频
	public static final String RELEASE_TYPE_MEDIA = "3";					// 媒体

	
	protected String relSequence;			//序号
	protected String relTitle;				//主标题
	protected String relSubTitle;			//副标题
	protected String relBrief;				//详情简介
	protected String relContent;			//全部详情
	protected String relStatus;				//新闻状态
	protected String relThumbImage;			//缩略图
	protected String userSequence;			//作者序号
	protected String userName;				//作者
	protected String cateSequence;			//类型序号
	protected String cateName;				//类型名称
	protected String relDate;				//发布时间
	protected String relImageBatchNo;		//图片关联批次
	protected String relType;				//发布类型
	protected String relURL;				//发布类型
	
	
	public String getRelURL() {
		return relURL;
	}
	public void setRelURL(String relURL) {
		this.relURL = relURL;
	}
	public String getRelType() {
		return relType;
	}
	public void setRelType(String relType) {
		this.relType = relType;
	}
	public String getRelSequence() {
		return relSequence;
	}
	public void setRelSequence(String relSequence) {
		this.relSequence = relSequence;
	}
	public String getRelTitle() {
		return relTitle;
	}
	public void setRelTitle(String relTitle) {
		this.relTitle = relTitle;
	}
	public String getRelSubTitle() {
		return relSubTitle;
	}
	public void setRelSubTitle(String relSubTitle) {
		this.relSubTitle = relSubTitle;
	}
	public String getRelBrief() {
		return relBrief;
	}
	public void setRelBrief(String relBrief) {
		this.relBrief = relBrief;
	}
	public String getRelContent() {
		return relContent;
	}
	public void setRelContent(String relContent) {
		this.relContent = relContent;
	}
	public String getRelStatus() {
		return relStatus;
	}
	public void setRelStatus(String relStatus) {
		this.relStatus = relStatus;
	}
	public String getRelThumbImage() {
		return relThumbImage;
	}
	public void setRelThumbImage(String relThumbImage) {
		this.relThumbImage = relThumbImage;
	}
	public String getRelDate() {
		return relDate;
	}
	public void setRelDate(String relDate) {
		this.relDate = relDate;
	}
	public String getRelImageBatchNo() {
		return relImageBatchNo;
	}
	public void setRelImageBatchNo(String relImageBatchNo) {
		this.relImageBatchNo = relImageBatchNo;
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
	public String getCateSequence() {
		return cateSequence;
	}
	public void setCateSequence(String cateSequence) {
		this.cateSequence = cateSequence;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	@Override
	public String toString() {
		return "Release [relSequence=" + relSequence + ", relTitle=" + relTitle + ", relSubTitle=" + relSubTitle
				+ ", relBrief=" + relBrief + ", relContent=" + relContent + ", relStatus=" + relStatus
				+ ", relThumbImage=" + relThumbImage + ", userSequence=" + userSequence + ", userName=" + userName
				+ ", cateSequence=" + cateSequence + ", cateName=" + cateName + ", relDate=" + relDate
				+ ", relImageBatchNo=" + relImageBatchNo + ", relType=" + relType + "]";
	}
}
