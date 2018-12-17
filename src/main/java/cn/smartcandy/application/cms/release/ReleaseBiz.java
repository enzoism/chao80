package cn.smartcandy.application.cms.release;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;

import cn.smartcandy.application.a.account.Admin;
import cn.smartcandy.application.a.account.User;
import cn.smartcandy.application.a.collection.CollectionDao;
import cn.smartcandy.common.common.BaseBiz;
import cn.smartcandy.common.utils.TMemberUtils;
import cn.smartcandy.framework.biz.workdate.WorkDate;
import cn.smartcandy.framework.core.data.DataTable;
import cn.smartcandy.framework.core.db.mybatis.MyBatisDBAccess;
import cn.smartcandy.framework.core.exception.BusinessException;
import cn.smartcandy.framework.utils.CStringUtils;

public class ReleaseBiz extends BaseBiz{
	private ReleaseDao releaseDao = new ReleaseDao();
	private CollectionDao collectionDao = new CollectionDao();

	// ----------------------------------------------------------轮播图发布
	public int addBannerMsg(Map<String, String> param, Admin admin) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String relSequence = dbAccess.getSeqID("a_release");
			param.put("relSequence", relSequence);
			param.put("relStatus", Release.STATUS_NORMAL);
			param.put("userSequence", admin.getAdminSequence());
			param.put("userName", admin.getAdminName());
			param.put("relDate", WorkDate.getSystemTime());
			param.put("relType", Release.RELEASE_TYPE_BANNER);
			
			String imageURL = param.get("imageURL");
			if (CStringUtils.isEmpty(imageURL)) {
				throw new BusinessException("当前轮播图片缺失");
			}
			
			param.put("relThumbImage", imageURL);
			int insertBannerMsg = releaseDao.insertBannerMsg(param, dbAccess);
			
			dbAccess.commitTransction();
			return insertBannerMsg;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("轮播图发布失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 查询轮播图信息
	public ReleaseBanner queryBannerMsg(Map<String, String> param) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String relSequence = param.get("relSequence");
			if (CStringUtils.isEmpty(relSequence)) {
				throw new BusinessException("参数不合法");
			}
			ReleaseBanner releaseBanner = releaseDao.selectBannerMsg(param, dbAccess);

			return releaseBanner;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询轮播图信息失败:", e);
		} finally {
		}
	}
	// 查询Banner列表 DONE
	public DataTable queryBannerDataList(Map<String, String> param,int pageNo,int pageSize) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String relStatus = param.get("relStatus");
			relStatus=CStringUtils.isEmpty(relStatus)?Release.STATUS_NORMAL:relStatus;
			param.put("relStatus", relStatus);
			DataTable dataTable = releaseDao.selectBannerDataList(param, pageNo, pageSize, dbAccess);
			return dataTable;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("轮播图更改失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 更新Banner信息
	public int modifyBannerMsg(Map<String, String> param,Admin admin){
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String relSequence = param.get("relSequence");
			if (CStringUtils.isEmpty(relSequence)) {
				throw new BusinessException("参数不合法");
			}
			String imageURL = param.get("imageURL");
			param.put("relThumbImage", imageURL);
			param.put("userSequence", admin.getAdminSequence());
			param.put("userName", admin.getAdminName());
			param.put("relDate", WorkDate.getSystemTime());
			int updateBannerMsg = releaseDao.updateBannerMsg(param, dbAccess);
			
			dbAccess.commitTransction();
			return updateBannerMsg;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("新闻发布失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	
	// ----------------------------------------------------------新闻发布
	public int addNewsMsg(Map<String, String> param, Admin admin) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String cateSequence = param.get("cateSequence");
			if (CStringUtils.isEmpty(cateSequence)) {
				throw new BusinessException("参数不合法");
			}
			String relThumbImage = param.get("imageURL");
			if (CStringUtils.isEmpty(relThumbImage)) {
				throw new BusinessException("新闻图片缺失");
			}
			
			Map<String, String> cateParam = new HashMap<String, String>();
			cateParam.put("cateSequence", cateSequence);
			cateParam.put("cateRelType", Release.RELEASE_TYPE_NEWS);
			ReleaseCategary categary = releaseDao.selectCategaryMsg(cateParam, dbAccess);
			if (null == categary) {
				throw new BusinessException("当前种类不存在");
			}
			
			String relSequence = dbAccess.getSeqID("a_release");
			param.put("relSequence", relSequence);
			param.put("relStatus", Release.STATUS_NORMAL);
			param.put("userSequence", admin.getAdminSequence());
			param.put("userName", admin.getAdminName());
			param.put("relDate", WorkDate.getSystemTime());
			param.put("relType", Release.RELEASE_TYPE_NEWS);
			param.put("cateSequence", categary.getCateSequence());
			param.put("cateName", categary.getCateName());
			param.put("relType", Release.RELEASE_TYPE_NEWS);
			param.put("relType", Release.RELEASE_TYPE_NEWS);
			param.put("relThumbImage", relThumbImage);
			
			
			int insertNewsMsg = releaseDao.insertNewsMsg(param, dbAccess);
			dbAccess.commitTransction();
			return insertNewsMsg;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("新闻发布失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 查询轮播图信息
	public ReleaseNews queryNewsMsg(Map<String, String> param) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String relSequence = param.get("relSequence");
			if (CStringUtils.isEmpty(relSequence)) {
				throw new BusinessException("参数不合法");
			}
			ReleaseNews releaseNews = releaseDao.selectNewsMsg(param, dbAccess);

			return releaseNews;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("新闻查询失败:", e);
		} finally {
		}
	}
	// 查询新闻列表 DONE
	public DataTable queryNewsDataList(Map<String, String> param,int pageNo,int pageSize) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String relStatus = param.get("relStatus");
			relStatus=CStringUtils.isEmpty(relStatus)?Release.STATUS_NORMAL:relStatus;
			param.put("relStatus", relStatus);
			DataTable dataTable = releaseDao.selectNewsDataList(param, pageNo, pageSize, dbAccess);
			return dataTable;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("新闻查询失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 更新News信息
	public int modifyNewsMsg(Map<String, String> param,Admin admin){
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String relSequence = param.get("relSequence");
			if (CStringUtils.isEmpty(relSequence)) {
				throw new BusinessException("参数不合法");
			}
			String relThumbImage = param.get("imageURL");
			param.put("relThumbImage", relThumbImage);
			param.put("userSequence", admin.getAdminSequence());
			param.put("userName", admin.getAdminName());
			param.put("relDate", WorkDate.getSystemTime());
			int updateNewsMsg = releaseDao.updateNewsMsg(param, dbAccess);
			
			dbAccess.commitTransction();
			return updateNewsMsg;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("新闻更新失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 查询News展示类型
	public int queryNewsShowType(Map<String, String> param){
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			int showType = releaseDao.selectNewsShowType(param, dbAccess);
			return showType;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询News展示类型失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	
	// 改变News展示类型
	public int changeNewsShowType(Map<String, String> param,HttpServletRequest request){
		int showType = TMemberUtils.getNewsShowType(request);
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			showType = (showType == 0) ? 1 : 0;
			param.put("pmFlag", String.valueOf(showType));
			releaseDao.updateNewsShowType(param, dbAccess);
			dbAccess.commitTransction();
			return showType;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("改变News展示类型失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// ----------------------------------------------------------视频发布
	// 添加视频
	public int addVideoMsg(Map<String, String> param,Admin admin) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String relURL = param.get("relURL");
			String imageURL = param.get("imageURL");
			if (CStringUtils.isEmpty(relURL) || CStringUtils.isEmpty(imageURL)) {
				throw new BusinessException("video视频或者图片缺失");
			}
			String relTitle = param.get("relTitle");
			if (CStringUtils.isEmpty(relTitle)) {
				throw new BusinessException("video标题缺失");
			}

			String relSequence = dbAccess.getSeqID("a_release");
			param.put("relThumbImage", imageURL);
			param.put("relURL", relURL);
			param.put("relSequence", relSequence);
			param.put("relStatus", Release.STATUS_NORMAL);
			param.put("userSequence", admin.getAdminSequence());
			param.put("userName", admin.getAdminName());		
			param.put("relDate", WorkDate.getSystemTime());
			param.put("relType", Release.RELEASE_TYPE_VIDEO);
			int insertVideoMsg = releaseDao.insertVideoMsg(param, dbAccess);
			
			dbAccess.commitTransction();
			return insertVideoMsg;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("视频发布失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 查询Video信息
	public ReleaseVideo queryVideoMsg(Map<String, String> param) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String relSequence = param.get("relSequence");
			if (CStringUtils.isEmpty(relSequence)) {
				throw new BusinessException("参数不合法");
			}
			ReleaseVideo selectVideoMsg = releaseDao.selectVideoMsg(param, dbAccess);
			return selectVideoMsg;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("视频查询失败:", e);
		} finally {
		}
	}
	// 查询Vide列表 DONE
	public DataTable queryVideoList(Map<String, String> param, int pageNo,int pageSize) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String relStatus = param.get("relStatus");
			relStatus=CStringUtils.isEmpty(relStatus)?Release.STATUS_NORMAL:relStatus;
			param.put("relStatus", relStatus);
			DataTable dataTable = releaseDao.selectVideoDataList(param, pageNo, pageSize, dbAccess);
			return dataTable;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("视频查询失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 更新Video信息
	public int modifyVideoMsg(Map<String, String> param,Admin admin){
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String relSequence = param.get("relSequence");
			if (CStringUtils.isEmpty(relSequence)) {
				throw new BusinessException("参数不合法");
			}
			String imageURL = param.get("imageURL");
			param.put("relThumbImage", imageURL);
			param.put("userSequence", admin.getAdminSequence());
			param.put("userName", admin.getAdminName());
			param.put("relDate", WorkDate.getSystemTime());
			int updateVideoMsg = releaseDao.updateVideoMsg(param, dbAccess);
			
			dbAccess.commitTransction();
			return updateVideoMsg;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("视频更新失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// ------------------------------藏品生活照管理------------------------------ //
	//TODO 删除固定图片
	public ReleasePhoto addPhotoMsg(Map<String, String> param,User user) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String colSequence = param.get("colSequence");
			String photoURL = param.get("photoURL");

			if (CStringUtils.isEmpty(colSequence) || CStringUtils.isEmpty(photoURL)) {
				throw new BusinessException("参数不合法");
			}			
			String photoSequence = dbAccess.getSeqID("a_collection_photo");
			param.put("photoSequence", photoSequence);
			param.put("userSequence", user.getUserSequence());
			param.put("userName", user.getUserName());
			param.put("photoURL", photoURL);
			param.put("photoStatus", ReleasePhoto.STATUS_NORMAL);
			releaseDao.insertPhotoMsg(param, dbAccess);
			
			ReleasePhoto releasePhoto = releaseDao.selectPhotoMsg(param, dbAccess);
			dbAccess.commitTransction();
			return releasePhoto;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("添加种类失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}

	// 查询藏品生活照 DONE
	public DataTable queryPhotoDataList(Map<String, String> param, int pageNo,int pageSize) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String relStatus = param.get("relStatus");
			relStatus=CStringUtils.isEmpty(relStatus)?Release.STATUS_NORMAL:relStatus;
			param.put("relStatus", relStatus);
			DataTable dataTable = releaseDao.selectPhotoDataList(param, pageNo, pageSize, dbAccess);
			return dataTable;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询生活照失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 查询藏品生活照 DONE
	public DataTable queryColPhotoList(Map<String, String> param, int pageNo,int pageSize) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String colSequence = param.get("colSequence");
			colSequence = "undefined".equals(colSequence)?null:colSequence;
			param.put("colSequence", colSequence);
			String relStatus = param.get("relStatus");
			relStatus=CStringUtils.isEmpty(relStatus)?Release.STATUS_NORMAL:relStatus;
			param.put("relStatus", relStatus);
			DataTable dataTable = releaseDao.selectColPhotoDataList(param, pageNo, pageSize, dbAccess);
			return dataTable;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询生活照失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 拉黑生活照
	public int blackPhotoMsg(Map<String, Object> param,Admin admin) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String[] photoList = (String[]) param.get("photoList");
			if (ArrayUtils.isEmpty(photoList)) {
				throw new BusinessException("参数不合法");
			}
			String photoStatus = (String) param.get("photoStatus");
			photoStatus=CStringUtils.isEmpty(photoStatus)?ReleasePhoto.STATUS_BLACK:photoStatus;
			param.put("photoStatus", photoStatus);
			param.put("userSequence", admin.getAdminSequence());
			param.put("userName", admin.getAdminName());
			int photoMsg = releaseDao.updatePhotoMsg(param, dbAccess);
			dbAccess.commitTransction();
			return photoMsg;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("查询生活照失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// ------------------------------种类管理------------------------------ //
	// 添加种类
	public int addCategaryMsg(Map<String, String> param, HttpServletRequest request, Admin admin) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			
			String cateSequence = dbAccess.getSeqID("a_releaseCate");
			param.put("cateSequence", cateSequence);
			param.put("cateDate", WorkDate.getSystemTime());
			param.put("cateStatus", Release.STATUS_NORMAL);
			param.put("cateRelType", Release.RELEASE_TYPE_NEWS);
			param.put("userSequence", admin.getAdminSequence());
			param.put("userName", admin.getAdminName());
			int insertCategaryMsg = releaseDao.insertCategaryMsg(param, dbAccess);
			dbAccess.commitTransction();
			return insertCategaryMsg;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("创建类型失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	
	// 查询Categary信息
	public ReleaseCategary queryCategaryMsg(Map<String, String> param) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String cateSequence = param.get("cateSequence");
			if (CStringUtils.isEmpty(cateSequence)) {
				throw new BusinessException("参数不合法");
			}
			ReleaseCategary selectCategaryMsg = releaseDao.selectCategaryMsg(param, dbAccess);
			return selectCategaryMsg;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("视频查询失败:", e);
		} finally {
		}
	}
	
	// 添加类型列表 DONE
	public List<ReleaseCategary> queryCategaryList(Map<String, String> param,HttpServletRequest request) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			// 请求数据，并将数据缓存
			param.put("cateStatus", Release.STATUS_NORMAL);
			List<ReleaseCategary> categaryList = releaseDao.selectCategaryList(param, dbAccess);
			TMemberUtils.saveCategaryList(request, categaryList);
			return categaryList;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询类型失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 添加类型列表 DONE
	public DataTable queryCategaryDataList(Map<String, String> param) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String cateStatus = param.get("cateStatus");
			cateStatus=CStringUtils.isEmpty(cateStatus)?Release.STATUS_NORMAL:cateStatus;
			param.put("cateStatus", cateStatus);
			DataTable dataTable = releaseDao.selectCategaryDataList(param, dbAccess);
			return dataTable;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("新闻发布失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 更新Video信息
	public int modifyCategaryMsg(Map<String, String> param,HttpServletRequest request, Admin admin){
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String cateSequence = param.get("cateSequence");
			if (CStringUtils.isEmpty(cateSequence)) {
				throw new BusinessException("参数不合法");
			}
			param.put("userSequence", admin.getAdminSequence());
			param.put("cateDate", WorkDate.getSystemTime());
			int updateVideoMsg = releaseDao.updateCategaryMsg(param, dbAccess);
			
			// 请求数据，并将数据缓存
			param.put("cateStatus", Release.STATUS_NORMAL);
			List<ReleaseCategary> categaryList = releaseDao.selectCategaryList(param, dbAccess);
			TMemberUtils.saveCategaryList(request, categaryList);
			
			dbAccess.commitTransction();
			return updateVideoMsg;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("视频更新失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// -----------------------------------用户中心----------------------------------- //
	
	// 用户信息+用户是否被点赞
	public User queryReleasedUserData(Map<String, String> param, User user) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			 // TODO 查询用户信息
			String userSequence = param.get("userSequence");
			if (CStringUtils.isEmpty(userSequence)) {
				throw new BusinessException();
			}
			 // TODO 判断用户
			
			
			return null;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询个人中心失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 查询用户发布的藏品 DONE
	public DataTable queryReleasedDataList(Map<String, String> param, int pageNo, int pageSize) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String cateSequence = param.get("cateSequence");
			String userSequence = param.get("userSequence");
			if (CStringUtils.isEmpty(cateSequence) || CStringUtils.isEmpty(userSequence)) {
				throw new BusinessException("参数不合法");
			}
			DataTable dataTable = collectionDao.selectCateCollectionDataList(param, pageNo, pageSize, dbAccess); 
			return dataTable;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询个人中心失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
}
