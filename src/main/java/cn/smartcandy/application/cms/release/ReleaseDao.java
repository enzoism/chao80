package cn.smartcandy.application.cms.release;

import java.util.List;
import java.util.Map;

import cn.smartcandy.framework.core.data.DataTable;
import cn.smartcandy.framework.core.db.mybatis.MyBatisDBAccess;

public class ReleaseDao {
	// -----------------------------------轮播图----------------------------------- //
	// 轮播图--发布
	public int insertBannerMsg(Map<String, String> param, MyBatisDBAccess dbAccess) {
		return dbAccess.insert("release.insertBannerMsg", param);
	}
	// 查询单个轮播信息
	public ReleaseBanner selectBannerMsg(Map<String, String> param, MyBatisDBAccess dbAccess) {
		return dbAccess.selectOne("release.selectBannerMsg", param);
	}
	// 查询Banner列表 DONE
	public DataTable selectBannerDataList(Map<String, String> param, int pageNo, int pageSize, MyBatisDBAccess dbAccess) {
		return dbAccess.selectDataTable("release.selectBannerDataList", param, pageNo, pageSize);
	}
	// 更新Banner信息
	public int updateBannerMsg(Map<String, String> param, MyBatisDBAccess dbAccess) {
		return dbAccess.update("release.updateBannerMsg", param);
	}
	// -----------------------------------新闻----------------------------------- //
	// 新闻--发布
	public int insertNewsMsg(Map<String, String> param, MyBatisDBAccess dbAccess) {
		return dbAccess.insert("release.insertNewsMsg", param);
	}
	// 查询News信息
	public ReleaseNews selectNewsMsg(Map<String, String> param, MyBatisDBAccess dbAccess) {
		return dbAccess.selectOne("release.selectNewsMsg", param);
	}
	// 查询News列表 DONE
	public DataTable selectNewsDataList(Map<String, String> param, int pageNo, int pageSize, MyBatisDBAccess dbAccess) {
		return dbAccess.selectDataTable("release.selectNewsDataList", param, pageNo, pageSize);
	}
	// 更新News信息
	public int updateNewsMsg(Map<String, String> param, MyBatisDBAccess dbAccess) {
		return dbAccess.update("release.updateNewsMsg", param);
	}
	// 查询News展示类型（News/Collection）
	public int selectNewsShowType(Map<String, String> param, MyBatisDBAccess dbAccess) {
		return dbAccess.selectOne("release.selectNewsShowType", param);
	}
	// 改变News展示类型（News/Collection）
	public int updateNewsShowType(Map<String, String> param, MyBatisDBAccess dbAccess) {
		return dbAccess.update("release.updateNewsShowType", param);
	}
	// -----------------------------------视频----------------------------------- //
	// 视频--发布
	public int insertVideoMsg(Map<String, String> param, MyBatisDBAccess dbAccess) {
		return dbAccess.insert("release.insertVideoMsg", param);
	}
	public ReleaseVideo selectVideoMsg(Map<String, String> param, MyBatisDBAccess dbAccess)  {
		return dbAccess.selectOne("release.selectVideoMsg", param);
	}
	// 查询Vide列表 DONE
	public DataTable selectVideoDataList(Map<String, String> param, int pageNo, int pageSize, MyBatisDBAccess dbAccess) {
		return dbAccess.selectDataTable("release.selectVideoDataList", param, pageNo, pageSize);
	}
	// 更新Video信息
	public int updateVideoMsg(Map<String, String> param, MyBatisDBAccess dbAccess) {
		return dbAccess.update("release.updateVideoMsg", param);
	}
	// -----------------------------------生活照----------------------------------- //
	// 生活照--发布
	public int insertPhotoMsg(Map<String, String> param, MyBatisDBAccess dbAccess) {
		return dbAccess.insert("release.insertPhotoMsg", param);
	}
	// 查询生活照
	public ReleasePhoto selectPhotoMsg(Map<String, String> param, MyBatisDBAccess dbAccess)  {
		return dbAccess.selectOne("release.selectPhotoMsg", param);
	}
	// 查询藏品生活照 DONE
	public DataTable selectPhotoDataList(Map<String, String> param, int pageNo, int pageSize, MyBatisDBAccess dbAccess) {
		return dbAccess.selectDataTable("release.selectPhotoDataList", param, pageNo, pageSize);
	}
	// 查询藏品生活照 DONE
	public DataTable selectColPhotoDataList(Map<String, String> param, int pageNo, int pageSize, MyBatisDBAccess dbAccess) {
		return dbAccess.selectDataTable("release.selectColPhotoDataList", param, pageNo, pageSize);
	}
	// 拉黑生活照
	public int updatePhotoMsg(Map<String, Object> param, MyBatisDBAccess dbAccess) {
		return dbAccess.update("release.updatePhotoMsg", param);
	}
	
	// -----------------------------------类别----------------------------------- //
	// 查询Category
	public ReleaseCategary selectCategaryMsg(Map<String, String> param, MyBatisDBAccess dbAccess) {
		return dbAccess.selectOne("release.selectCategaryMsg", param);
	}
	// 添加Category
	public int insertCategaryMsg(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.insert("release.insertCategaryMsg", param);
	}	
	// 查询Category列表
	public List<ReleaseCategary> selectCategaryList(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectList("release.selectCategaryList", param);
	}
	// 查询Category列表
	public DataTable selectCategaryDataList(Map<String, String> param, MyBatisDBAccess dbAccess) {
		return dbAccess.selectDataTable("release.selectCategaryDataList", param);
	}
	// 更新Category信息
	public int updateCategaryMsg(Map<String, String> param, MyBatisDBAccess dbAccess) {
		return dbAccess.update("release.updateCategaryMsg", param);
	}
	
	// 更新种类发布藏品数量
	public int updateCategaryReleaseNum(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.update("release.updateCategaryReleaseNum", param);
	}
	
}
