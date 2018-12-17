package cn.smartcandy.application.a.image;

import java.util.List;
import java.util.Map;

import cn.smartcandy.application.a.collection.Collection;
import cn.smartcandy.framework.core.data.DataTable;
import cn.smartcandy.framework.core.db.mybatis.MyBatisDBAccess;

public class ImageDao {
	
	// 上传藏品图片
	public int insertCollectionImages(Map<String, String> param, MyBatisDBAccess dbAccess) {
		return dbAccess.insert("image.insertCollectionImages", param);
	}
	
	// 更改藏品图片
	public int updateCollectionImages(Map<String, String> param, MyBatisDBAccess dbAccess) {
		return dbAccess.insert("image.updateCollectionImages", param);
	}
	
	// 查询指定用户的Top6图片
	public List<ImageCollection> selectCateUserList(String userSequence, MyBatisDBAccess dbAccess){
		return dbAccess.selectList("image.selectCateUserList", userSequence);
	}
	
	// 查询图片的批次信息
	public Collection selectCollectionMsg(String imageSequence, MyBatisDBAccess dbAccess){
		return dbAccess.selectOne("image.selectCollectionMsg", imageSequence);
	}
	
	// 查询藏品的所有图片
	public List<ImageCollection> selectCollectionImageList(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectList("image.selectCollectionImageList", param);
	}
	
	// 查询藏品的所有生活照
	public DataTable selectCollectionPhotoDataList(Map<String, String> param,int pageNo, int pageSize, MyBatisDBAccess dbAccess){
		return dbAccess.selectDataTable("image.selectCollectionPhotoDataList", param, pageNo, pageSize);
	}

}
