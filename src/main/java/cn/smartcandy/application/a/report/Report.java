package cn.smartcandy.application.a.report;


/**
 * @项目名称：zmjema
 * @类名称：Report
 * @类描述：举报Bean
 * @创建人：tangzhifeng
 * @创建时间：2017年10月31日 上午11:34:37
 * @修改人：someOne
 * @修改时间：2017年10月31日 上午11:34:37 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class Report {

	// 状态
	public static final String STATUS_IGNORE = "I";					//忽略
	public static final String STATUS_NORMAL = "N";					//正常
	public static final String STATUS_BLACK = "B";					//拉黑
	public static final String STATUS_DELETE = "D";					//删除
	public static final String STATUS_READ = "R";					//已读

	// 类型
	public static final String TYPE_IMAGE = "1";					//图片
	public static final String TYPE_COLLECTION = "2";				//藏品
	public static final String TYPE_COLLECTOR = "3";				//藏家
	public static final String TYPE_PHOTO = "4";					//图片
	
	// 原因
	public static final String CAUSE_DEFAULT = "0";					//默认
	public static final String CAUSE_SEX = "1";						//色情
	public static final String CAUSE_FORCE = "2";					//暴力
	public static final String CAUSE_COPY = "3";					//抄袭

	
	private String reportSequence;					// 序号
	private String reportType;						// 举报类型
	private String reportCause;						// 举报原因
	private String reportStatus;					// 举报状态
	private String reportContent;					// 举报内容
	private String userSequence;					// 用户序号
	private String userName;						// 昵称
	private String defendSequence;					// 被告序号
	
	
	public String getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}
	public String getDefendSequence() {
		return defendSequence;
	}
	public void setDefendSequence(String defendSequence) {
		this.defendSequence = defendSequence;
	}
	public String getReportCause() {
		return reportCause;
	}
	public void setReportCause(String reportCause) {
		this.reportCause = reportCause;
	}
	public String getReportSequence() {
		return reportSequence;
	}
	public void setReportSequence(String reportSequence) {
		this.reportSequence = reportSequence;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSequence() {
		return userSequence;
	}
	public void setUserSequence(String userSequence) {
		this.userSequence = userSequence;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	@Override
	public String toString() {
		return "Report [reportSequence=" + reportSequence + ", userSequence=" + userSequence + ", userName=" + userName
				+ ", defendSequence=" + defendSequence + ", reportType=" + reportType + ", reportCause=" + reportCause
				+ ", reportContent=" + reportContent + "]";
	}
	
}
