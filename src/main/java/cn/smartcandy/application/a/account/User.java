package cn.smartcandy.application.a.account;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.smartcandy.application.a.collection.Collection;
import cn.smartcandy.application.a.image.ImageCollection;

/**
 * @项目名称：zmjema
 * @类名称：User
 * @类描述：注册会员
 * @创建人：tangzhifeng
 * @创建时间：2017年10月13日 下午12:16:53
 * @修改人：someOne
 * @修改时间：2017年10月13日 下午12:16:53 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class User implements Serializable{
	private static final long serialVersionUID = -2529259778263736879L;
	// 状态
	public static final String STATUS_NULL = "U";					//空状态
	public static final String STATUS_NORMAL = "N";					//正常
	public static final String STATUS_BLACK = "B";					//黑名单
	public static final String STATUS_DELETE = "D";					//注销
	
	// 性别
	public static final String SEX_F = "F";							//女性
	public static final String SEX_M = "M";							//男性
	
	// 身份
	public static final String VERFY_USER = "0";					//普通会员
	public static final String VERFY_COLLECTION = "1";				//管理员
	public static final String VERFY_ADMIN = "9";					//管理员

	// 备注
	public static final String REMARK_REGISTER = "普通注册";					//普通注册会员

	private String userSequence;			// 序号
	private String userName;				// 昵称
	private String userPwd;					// 密码
	private String userEmail;				// 邮箱
	private String userPhone;				// 电话
	private String userStatus;				// 用户状态N：正常  D：注销  B:黑名单  
	private String userCreatDate;			// 用户创建时间
	private String userRemark;				// 备注
	private String userSex;					// 性别F:女性  M：男性
	private String userBirth;				// 生日
	private String userAddr;				// 地址
	private String userImageURL;			// 头像
	private String verfyID;					// 权限辨识：0：普通会员  1：藏家
	private String adminStatus;				// 管理员状态N：正常  D：注销  B:黑名单  
	
	private String attentCount;				// 关注人数
	private String fansCount;				// 粉丝人数
	private String likeCount;				// 点赞个数
	private String likedCount;				// 被赞个数
	private String colCount;				// 藏品个数
	
	//辅助
	
	private List<Collection> colList;							// 藏品个数
	private boolean hasAttention = Boolean.FALSE;				// 是否关注
	private List<ImageCollection> colImageList;					// 藏品图片
	private List<Map<String,String>> cateMapList;				// 种类MapList
	
	

	public List<Map<String, String>> getCateMapList() {
		return cateMapList;
	}
	public void setCateMapList(List<Map<String, String>> cateMapList) {
		this.cateMapList = cateMapList;
	}
	public String getLikedCount() {
		return likedCount;
	}
	public void setLikedCount(String likedCount) {
		this.likedCount = likedCount;
	}
	public List<ImageCollection> getColImageList() {
		return colImageList;
	}
	public void setColImageList(List<ImageCollection> colImageList) {
		this.colImageList = colImageList;
	}
	public boolean isHasAttention() {
		return hasAttention;
	}
	public void setHasAttention(boolean hasAttention) {
		this.hasAttention = hasAttention;
	}
	public List<Collection> getColList() {
		return colList;
	}
	public void setColList(List<Collection> colList) {
		this.colList = colList;
	}
	public String getColCount() {
		return colCount;
	}
	public void setColCount(String colCount) {
		this.colCount = colCount;
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
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getUserCreatDate() {
		return userCreatDate;
	}
	public void setUserCreatDate(String userCreatDate) {
		this.userCreatDate = userCreatDate;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	public String getUserImageURL() {
		return userImageURL;
	}
	public void setUserImageURL(String userImageURL) {
		this.userImageURL = userImageURL;
	}

	public String getAdminStatus() {
		return adminStatus;
	}
	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}
	public String getVerfyID() {
		return verfyID;
	}
	public void setVerfyID(String verfyID) {
		this.verfyID = verfyID;
	}
	public String getAttentCount() {
		return attentCount;
	}
	public void setAttentCount(String attentCount) {
		this.attentCount = attentCount;
	}
	public String getFansCount() {
		return fansCount;
	}
	public void setFansCount(String fansCount) {
		this.fansCount = fansCount;
	}
	public String getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(String likeCount) {
		this.likeCount = likeCount;
	}
	@Override
	public String toString() {
		return "User [userSequence=" + userSequence + ", userName=" + userName + ", userPwd=" + userPwd + ", userEmail="
				+ userEmail + ", userPhone=" + userPhone + ", userStatus=" + userStatus + ", userCreatDate="
				+ userCreatDate + ", userRemark=" + userRemark + ", userSex=" + userSex + ", userBirth=" + userBirth
				+ ", userAddr=" + userAddr + ", userImageURL=" + userImageURL + ", verfyID=" + verfyID
				+ ", adminStatus=" + adminStatus + ", attentCount=" + attentCount + ", fansCount=" + fansCount
				+ ", likeCount=" + likeCount + ", colCount=" + colCount + ", colList=" + colList + ", hasAttention="
				+ hasAttention + ", colImageList=" + colImageList + "]";
	}
	
}
