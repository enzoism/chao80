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
public class ReleaseImage implements Serializable{

	private static final long serialVersionUID = -9181766078485419824L;
	// 状态
	public static final String STATUS_NORMAL = "N";					// 正常
	public static final String STATUS_BLACK = "B";					// 黑名单
	public static final String STATUS_DELETE = "D";					// 注销
	
	// 所属类型
	public static final String RELEASE_TYPE_BANNER = "0";					// 轮播图
	public static final String RELEASE_TYPE_NEWS = "1";						// 新闻
	public static final String RELEASE_TYPE_VIDEO = "2";					// 视频
	public static final String RELEASE_TYPE_MEDIA = "3";					// 媒体
	
	
	
	private String imageSequence;			//序号
	private String imageBatchNo;			//批次序号
	private String imageURL;				//图片地址
	private String imageRelType;			//标题类型：0:Banner  1:News  2:Video 3:媒体
	public String getImageSequence() {
		return imageSequence;
	}
	public void setImageSequence(String imageSequence) {
		this.imageSequence = imageSequence;
	}
	public String getImageBatchNo() {
		return imageBatchNo;
	}
	public void setImageBatchNo(String imageBatchNo) {
		this.imageBatchNo = imageBatchNo;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getImageRelType() {
		return imageRelType;
	}
	public void setImageRelType(String imageRelType) {
		this.imageRelType = imageRelType;
	}
	@Override
	public String toString() {
		return "ReleaseImage [imageSequence=" + imageSequence + ", imageBatchNo=" + imageBatchNo + ", imageURL="
				+ imageURL + ", imageRelType=" + imageRelType + "]";
	}

}
