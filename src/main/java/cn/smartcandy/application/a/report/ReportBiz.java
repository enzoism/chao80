package cn.smartcandy.application.a.report;

import java.util.HashMap;
import java.util.Map;

import cn.smartcandy.application.a.account.User;
import cn.smartcandy.application.cms.release.ReleasePhoto;
import cn.smartcandy.common.common.BaseBiz;
import cn.smartcandy.framework.core.data.DataTable;
import cn.smartcandy.framework.core.db.mybatis.MyBatisDBAccess;
import cn.smartcandy.framework.core.exception.BusinessException;
import cn.smartcandy.framework.utils.CStringUtils;

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
public class ReportBiz extends BaseBiz{
	private ReportDao reportDao = new ReportDao();
	// 举报图片
	public int reportImage(Map<String, String> param,User user) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String defendSequence = param.get("defendSequence");
			if (CStringUtils.isEmpty(defendSequence)) {
				throw new BusinessException("参数不合法");
			}
			String reportSequence = dbAccess.getSeqID("a_report");
			param.put("reportSequence", reportSequence);
			param.put("reportType", Report.TYPE_IMAGE);
			param.put("reportStatus", Report.STATUS_NORMAL);
			param.put("userSequence", user.getUserSequence());
			param.put("userName", user.getUserName());
			
			int reportImage = reportDao.insertReportMsg(param, dbAccess);
			dbAccess.commitTransction();
			return reportImage;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("举报图片失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 举报藏品
	public int reportCollection(Map<String, String> param,User user) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String defendSequence = param.get("defendSequence");
			if (CStringUtils.isEmpty(defendSequence)) {
				throw new BusinessException("参数不合法");
			}
			String reportSequence = dbAccess.getSeqID("a_report");
			param.put("reportSequence", reportSequence);
			param.put("reportType", Report.TYPE_COLLECTION);
			param.put("reportStatus", Report.STATUS_NORMAL);
			param.put("userSequence", user.getUserSequence());
			param.put("userName", user.getUserName());
			int reportCollection = reportDao.insertReportMsg(param, dbAccess);
			dbAccess.commitTransction();
			return reportCollection;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("举报藏品失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 举报藏家
	public int reportCollector(Map<String, String> param,User user) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String defendSequence = param.get("defendSequence");
			if (CStringUtils.isEmpty(defendSequence)) {
				throw new BusinessException("参数不合法");
			}
			String reportSequence = dbAccess.getSeqID("a_report");
			param.put("reportSequence", reportSequence);
			param.put("reportType", Report.TYPE_COLLECTOR);
			param.put("reportStatus", Report.STATUS_NORMAL);
			param.put("userSequence", user.getUserSequence());
			param.put("userName", user.getUserName());
			int reportCollector = reportDao.insertReportMsg(param, dbAccess);
			dbAccess.commitTransction();
			return reportCollector;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("举报藏家失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}

	// 举报生活照
	public int reportPhoto(Map<String, String> param,User user) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String defendSequence = param.get("defendSequence");
			if (CStringUtils.isEmpty(defendSequence)) {
				throw new BusinessException("参数不合法");
			}
			// 查询是否已经举报
			Map<String, String> countParam = new HashMap<String, String>();
			countParam.put("userSequence", user.getUserSequence());
			countParam.put("defendSequence", defendSequence);
			countParam.put("reportType", Report.TYPE_PHOTO);
			// 生活照状态
			String photoStatus = reportDao.selectReportPhotoStatus(countParam, dbAccess);
			if (CStringUtils.equals(photoStatus, ReleasePhoto.STATUS_READ)) {
				throw new BusinessException("该生活照已通过审核，举报失败！");
			}else if (CStringUtils.equals(photoStatus, ReleasePhoto.STATUS_BLACK)||CStringUtils.equals(photoStatus, ReleasePhoto.STATUS_DELETE)) {
				throw new BusinessException("该生活照已经删除，举报失败！");
			}
			// 举报数量
			int reportCount = reportDao.selectReportCount(countParam, dbAccess);
			if (reportCount >0) {
				throw new BusinessException("该生活照已经举报，不能重复举报！");
			}

			// 插入举报数据
			String reportSequence = dbAccess.getSeqID("a_report");
			param.put("reportSequence", reportSequence);
			param.put("reportType", Report.TYPE_PHOTO);
			param.put("reportStatus", Report.STATUS_NORMAL);
			param.put("userSequence", user.getUserSequence());
			param.put("userName", user.getUserName());

			int reportPhoto = reportDao.insertReportMsg(param, dbAccess);
			dbAccess.commitTransction();
			return reportPhoto;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("举报生活照失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	
	// 举报生活照列表
	public DataTable queryReportPhotoDataList(Map<String, String> param,int pageNo,int pageSize) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			DataTable dataTable = reportDao.selectReportPhotoDataList(param, pageNo, pageSize, dbAccess);
			return dataTable;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("举报数据查询失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	
}
