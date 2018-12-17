package cn.smartcandy.application.cms.release;

import java.io.Serializable;

/**
 * @项目名称：zmjema
 * @类名称：ReleaseCategary
 * @类描述：管理员级别种类(a_release_cate)
 * @创建人：tangzhifeng
 * @创建时间：2017年10月20日 下午12:04:35
 * @修改人：someOne
 * @修改时间：2017年10月20日 下午12:04:35 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class ReleaseCategary implements Serializable{
	private static final long serialVersionUID = 4395428104259525363L;
	private String cateSequence;			// 类型序号
	private String cateName;				// 类型名称
	private String cateBrief;				// 类型简介
	private String cateContent;				// 备注信息
	private String cateDate;				// 创建时间
	private String cateStatus;				// 类型状态
	private String cateRelType;				// 创建类型：0:Banner  1:News  2:Video
	private String userSequence;			// 创建人
	private String userName;				// 创建人姓名

	private String colCount;				// 发布个数
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getColCount() {
		return colCount;
	}
	public void setColCount(String colCount) {
		this.colCount = colCount;
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
	public String getCateBrief() {
		return cateBrief;
	}
	public void setCateBrief(String cateBrief) {
		this.cateBrief = cateBrief;
	}
	public String getCateContent() {
		return cateContent;
	}
	public void setCateContent(String cateContent) {
		this.cateContent = cateContent;
	}
	public String getCateDate() {
		return cateDate;
	}
	public void setCateDate(String cateDate) {
		this.cateDate = cateDate;
	}
	public String getCateStatus() {
		return cateStatus;
	}
	public void setCateStatus(String cateStatus) {
		this.cateStatus = cateStatus;
	}
	public String getCateRelType() {
		return cateRelType;
	}
	public void setCateRelType(String cateRelType) {
		this.cateRelType = cateRelType;
	}
	public String getUserSequence() {
		return userSequence;
	}
	public void setUserSequence(String userSequence) {
		this.userSequence = userSequence;
	}
	@Override
	public String toString() {
		return "ReleaseCategary [cateSequence=" + cateSequence + ", cateName=" + cateName + ", cateBrief=" + cateBrief
				+ ", cateContent=" + cateContent + ", cateDate=" + cateDate + ", cateStatus=" + cateStatus
				+ ", cateRelType=" + cateRelType + ", userSequence=" + userSequence + "]";
	}
	
}
