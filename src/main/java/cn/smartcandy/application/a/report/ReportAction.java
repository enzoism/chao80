package cn.smartcandy.application.a.report;

import java.util.List;
import java.util.Map;

import cn.smartcandy.common.common.BaseAction;
import cn.smartcandy.common.utils.TStringUtils;
import cn.smartcandy.framework.core.data.DataTable;
import cn.smartcandy.framework.core.exception.BusinessException;

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
public class ReportAction extends BaseAction{

	private static final long serialVersionUID = 2761005301464315831L;
	private ReportBiz reportBiz = new ReportBiz();
	// 举报图片
	public String reportImage() throws BusinessException {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    reportBiz.reportImage(param, super.getUser());
		return null;
	}
	// 举报藏品
	public String reportCollection() throws BusinessException {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    reportBiz.reportCollection(param, super.getUser());	
		return null;
	}
	// 举报藏家
	public String reportCollector() throws BusinessException {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    reportBiz.reportCollector(param, super.getUser());
		return null;
	}

	// 举报生活照
	public String reportPhoto() throws BusinessException {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    reportBiz.reportPhoto(param, super.getUser());
		return null;
	}
	
	// 举报生活照列表
	public String queryReportPhotoDataList() throws BusinessException {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    DataTable dataTable = reportBiz.queryReportPhotoDataList(param, super.getPageNo(),super.getPageSize());
	    List<Map<String, String>> reportList = TStringUtils.turnDataTableToStringMapList(dataTable);
	    Map<String, String> pageInfo = TStringUtils.turnDataTableToPageInfoMap(dataTable);
	    request.setAttribute("reportList", reportList);
	    request.setAttribute("pageInfo", pageInfo);
		return null;
	}
	
}
