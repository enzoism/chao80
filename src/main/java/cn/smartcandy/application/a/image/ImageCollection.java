package cn.smartcandy.application.a.image;

import java.io.Serializable;

public class ImageCollection implements Serializable{

	private static final long serialVersionUID = 2326116018312884233L;
	// 状态
	public static final String STATUS_NORMAL = "N";					//正常
	public static final String STATUS_BLACK = "B";					//黑名单
	public static final String STATUS_DELETE = "D";					//注销
	
	// 身份
	public static final String TYPE_IMAGE = "I";					//藏品图片
	public static final String TYPE_PHOTO = "P";					//藏品生活照
	
	private String imgSequence;			// 序号
	private String imgBatchNo;			// 批次序号
	private String imgURL;				// 图片地址
	private String colSequence;			// 藏品序号
	private String imgStatus;			// 图片状态
	private String imgWidth;			// 图片宽度
	private String imgHeigh;			// 图片高度
	private String imgType;				// 图片种类
	
	
	public String getImgType() {
		return imgType;
	}
	public void setImgType(String imgType) {
		this.imgType = imgType;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public String getImgSequence() {
		return imgSequence;
	}
	public void setImgSequence(String imgSequence) {
		this.imgSequence = imgSequence;
	}
	public String getImgBatchNo() {
		return imgBatchNo;
	}
	public void setImgBatchNo(String imgBatchNo) {
		this.imgBatchNo = imgBatchNo;
	}
	public String getColSequence() {
		return colSequence;
	}
	public void setColSequence(String colSequence) {
		this.colSequence = colSequence;
	}
	public String getImgStatus() {
		return imgStatus;
	}
	public void setImgStatus(String imgStatus) {
		this.imgStatus = imgStatus;
	}
	public String getImgWidth() {
		return imgWidth;
	}
	public void setImgWidth(String imgWidth) {
		this.imgWidth = imgWidth;
	}
	public String getImgHeigh() {
		return imgHeigh;
	}
	public void setImgHeigh(String imgHeigh) {
		this.imgHeigh = imgHeigh;
	}
	@Override
	public String toString() {
		return "ImageCollection [imgSequence=" + imgSequence + ", imgBatchNo=" + imgBatchNo + ", colSequence="
				+ colSequence + ", imgStatus=" + imgStatus + ", imgWidth=" + imgWidth + ", imgHeigh=" + imgHeigh + "]";
	}
}
