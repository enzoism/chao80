package cn.smartcandy.application.a.collection;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.beans.Field;

import cn.smartcandy.application.a.account.User;
import cn.smartcandy.application.a.image.ImageCollection;

/**
 * @项目名称：zmjema
 * @类名称：Collection
 * @类描述：藏品JavenBean
 * @创建人：tangzhifeng
 * @创建时间：2017年10月15日 下午2:58:44
 * @修改人：someOne
 * @修改时间：2017年10月15日 下午2:58:44 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class Collection implements Serializable{

	private static final long serialVersionUID = 6248954107822186381L;
	// 状态
	public static final String STATUS_NORMAL = "N";					// 正常
	public static final String STATUS_BLACK = "B";					// 黑名单
	public static final String STATUS_DELETE = "D";					// 注销
	// 出售状态
	public static final String SALE_PRICE = "1";					// 销售品
	public static final String SALE_FREE = "0";						// 非卖品
	
	
	private String colSequence;						// 序号
	private String colDate;							// 出厂时间
	private String colTitle;						// 藏品标题
	private String colSubTitle;						// 藏品子标题
	private String colContent;						// 藏品内容
	private String colLikeNum;						// 喜欢次数
	private String colDisLikeNum;					// 不喜欢次数
	private String userSequence;					// 作者序号
	private String userName;						// 作者名称
	private String colThumb;						// 藏品缩略图
	private String colImageBatch;					// 藏品图片批次
	private String colPrice;						// 藏品价钱
	private String cateSequence;					// 藏品分类
	private String cateName;						// 藏品分类名称
	private String colStatus;						// 藏品状态
	private String createDate;						// 发布时间
	private String imgWidth;						// 图片宽度
	private String imgHeigh;						// 图片高度
	private String isSale;							// 是否出售
	private String searchTag;						// 搜索标签
	
	
	// 辅助
	private boolean hasAttention = Boolean.FALSE;				// 是否关注
	private List<ImageCollection> imageList ;					// 藏品详情页
	private List<ImageCollection> photoList ;					// 藏品生活照
	private User user;											// 发布作者
	private List<Map<String, String>> cateList;						// 发布类别
	private List<String> cateSeqList;						// 发布类别


	@Field
	public void setColSequence(String colSequence) {
		this.colSequence = colSequence;
	}
	@Field
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Field
	public void setColTitle(String colTitle) {
		this.colTitle = colTitle;
	}
	@Field
	public void setColSubTitle(String colSubTitle) {
		this.colSubTitle = colSubTitle;
	}
	@Field
	public void setColContent(String colContent) {
		this.colContent = colContent;
	}
	@Field
	public void setColStatus(String colStatus) {
		this.colStatus = colStatus;
	}
	@Field
	public void setColThumb(String colThumb) {
		this.colThumb = colThumb;
	}
	@Field
	public void setColPrice(String colPrice) {
		this.colPrice = colPrice;
	}
	@Field
	public void setSearchTag(String searchTag) {
		this.searchTag = searchTag;
	}	
	@Field
	public void setImgWidth(String imgWidth) {
		this.imgWidth = imgWidth;
	}
	@Field
	public void setImgHeigh(String imgHeigh) {
		this.imgHeigh = imgHeigh;
	}
	public String getSearchTag() {
		return searchTag;
	}
	public void setColLikeNum(String colLikeNum) {
		this.colLikeNum = colLikeNum;
	}
	public void setColDate(String colDate) {
		this.colDate = colDate;
	}
	public void setColDisLikeNum(String colDisLikeNum) {
		this.colDisLikeNum = colDisLikeNum;
	}
	public void setUserSequence(String userSequence) {
		this.userSequence = userSequence;
	}
	public void setColImageBatch(String colImageBatch) {
		this.colImageBatch = colImageBatch;
	}
	public void setCateSequence(String cateSequence) {
		this.cateSequence = cateSequence;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public void setIsSale(String isSale) {
		this.isSale = isSale;
	}
	public void setHasAttention(boolean hasAttention) {
		this.hasAttention = hasAttention;
	}
	public void setImageList(List<ImageCollection> imageList) {
		this.imageList = imageList;
	}
	public void setPhotoList(List<ImageCollection> photoList) {
		this.photoList = photoList;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setCateList(List<Map<String, String>> cateList) {
		this.cateList = cateList;
	}
	public void setCateSeqList(List<String> cateSeqList) {
		this.cateSeqList = cateSeqList;
	}
	public String getColSequence() {
		return colSequence;
	}
	public String getColDate() {
		return colDate;
	}
	public String getColTitle() {
		return colTitle;
	}
	public String getColSubTitle() {
		return colSubTitle;
	}
	public String getColContent() {
		return colContent;
	}
	public String getColLikeNum() {
		return colLikeNum;
	}
	public String getColDisLikeNum() {
		return colDisLikeNum;
	}
	public String getUserSequence() {
		return userSequence;
	}
	public String getUserName() {
		return userName;
	}
	public String getColThumb() {
		return colThumb;
	}
	public String getColImageBatch() {
		return colImageBatch;
	}
	public String getColPrice() {
		return colPrice;
	}
	public String getCateSequence() {
		return cateSequence;
	}
	public String getCateName() {
		return cateName;
	}
	public String getColStatus() {
		return colStatus;
	}
	public String getCreateDate() {
		return createDate;
	}
	public String getImgWidth() {
		return imgWidth;
	}
	public String getImgHeigh() {
		return imgHeigh;
	}
	public String getIsSale() {
		return isSale;
	}
	public boolean isHasAttention() {
		return hasAttention;
	}
	public List<ImageCollection> getImageList() {
		return imageList;
	}
	public List<ImageCollection> getPhotoList() {
		return photoList;
	}
	public User getUser() {
		return user;
	}
	public List<Map<String, String>> getCateList() {
		return cateList;
	}
	public List<String> getCateSeqList() {
		return cateSeqList;
	}
	@Override
	public String toString() {
		return "Collection [colSequence=" + colSequence + ", colDate=" + colDate + ", colTitle=" + colTitle
				+ ", colSubTitle=" + colSubTitle + ", colContent=" + colContent + ", colLikeNum="
				+ colLikeNum + ", colDisikerNum=" + colDisLikeNum + ", userSequnece=" + userSequence + ", colThumb="
				+ colThumb + ", colImageBatch=" + colImageBatch + ", colPrice=" + colPrice + ", cateSequence="
				+ cateSequence + ", createDate=" + createDate + "]";
	}


	

}
