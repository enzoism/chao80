package cn.smartcandy.application.a.report;

import java.util.Map;

import cn.smartcandy.framework.core.data.DataTable;
import cn.smartcandy.framework.core.db.mybatis.MyBatisDBAccess;

/**
 * @项目名称：zmjema
 * @类名称：ReportAction
 * @类描述：举报藏品
 * @创建人：tangzhifeng
 * @创建时间：2017年10月31日 上午11:33:35
 * @修改人：someOne
 * @修改时间：2017年10月31日 上午11:33:35 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class ReportDao {
	// 举报
	public int insertReportMsg(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.insert("report.insertReportMsg", param);
	}
	// 举报数量
	public int selectReportCount(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectOne("report.selectReportCount", param);
	}
	// 举报生活照状态
	public String selectReportPhotoStatus(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectOne("report.selectReportPhotoStatus", param);
	}
	
	// 举报生活照列表
	public DataTable selectReportPhotoDataList(Map<String, String> param, int pageNo,int pageSize,MyBatisDBAccess dbAccess){
		return dbAccess.selectDataTable("report.selectReportPhotoDataList", param, pageNo, pageSize);
	}
	
}
