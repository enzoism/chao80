package cn.smartcandy.application.a.image;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cn.smartcandy.common.common.BaseBiz;
import cn.smartcandy.common.utils.TStringUtils;
import cn.smartcandy.framework.core.data.DataTable;
import cn.smartcandy.framework.core.db.mybatis.MyBatisDBAccess;
import cn.smartcandy.framework.core.exception.BusinessException;
import cn.smartcandy.framework.utils.CStringUtils;

public class ImageBiz extends BaseBiz{

	private ImageDao imageDao = new ImageDao();
	
	// 上传藏品图片
	public void uploadCollectionImages(Map<String, String> param) throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			
			Map<String, String> imgParam = new HashMap<String, String>();
			String imgBatchNo = dbAccess.getSeqID("a_collection_imgBatch");
			String colSequence = param.get("colSequence");
			Random random = new Random();
			
			String imageURL = param.get("imageURL");
			if (CStringUtils.isEmpty(imageURL) || CStringUtils.equals(imageURL, "undefined")) {
				return ;
			}
			List<String> imageList = Arrays.asList(imageURL.split(","));
			//List<String> imageList = ImageBiz.initWithTestData();
			for (int i = 0; null!=imageList && i < imageList.size(); i++) {
				imgParam = new HashMap<String, String>();
				imgParam.put("colSequence", colSequence);
				imgParam.put("imgURL", imageList.get(i));
				imgParam.put("imgBatchNo", imgBatchNo);
				imgParam.put("imgURL", imageList.get(i));
				imgParam.put("imgStatus", ImageCollection.STATUS_NORMAL);
				imgParam.put("imgSequence", dbAccess.getSeqID("a_collection_img"));
				int width = random.nextInt(2)%2+1;
				int heigh = (width == 1)?(random.nextInt(2)%2+2):2;
				imgParam.put("imgWidth", String.valueOf(width));
				imgParam.put("imgHeigh", String.valueOf(heigh));
				imageDao.insertCollectionImages(imgParam, dbAccess);
			}
			dbAccess.commitTransction();
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("上传藏品图片失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	
	// 上传藏品图片
	public void uploadCollectionImages(Map<String, String> param, MyBatisDBAccess dbAccess2) throws BusinessException {
		try {
			Map<String, String> imgParam = new HashMap<String, String>();
			String imgBatchNo = dbAccess2.getSeqID("a_collection_imgBatch");
			String colSequence = param.get("colSequence");
			String imageURL = param.get("imageURL");
			String imgWidth = param.get("imgWidth");
			String imgHeigh = param.get("imgHeigh");

			List<String> imageList = Arrays.asList(imageURL.split(","));
			TStringUtils.printList(imageList);
			for (int i = 0; null!=imageList && i < imageList.size(); i++) {
				imgParam = new HashMap<String, String>();
				imgParam.put("colSequence", colSequence);
				imgParam.put("imgURL", imageList.get(i));
				imgParam.put("imgBatchNo", imgBatchNo);
				imgParam.put("imgURL", imageList.get(i));
				imgParam.put("imgStatus", ImageCollection.STATUS_NORMAL);
				imgParam.put("imgSequence", dbAccess2.getSeqID("a_collection_img"));
				imgParam.put("imgWidth", imgWidth);
				imgParam.put("imgHeigh", imgHeigh);
				imageDao.insertCollectionImages(imgParam, dbAccess2);
			}
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("上传藏品图片失败:", e);
		} 
	}
	
	// 删除藏品图片
	public void deleteCollectionImages(Map<String, String> param, MyBatisDBAccess dbAccess2) throws BusinessException {
		try {
			Map<String, String> imgParam = new HashMap<String, String>();
			String colSequence = param.get("colSequence");
			if (CStringUtils.isEmpty(colSequence)) {
				throw new BusinessException("参数不合法！");
			}
			imgParam.put("colSequence", colSequence);
			imgParam.put("imgStatus", ImageCollection.STATUS_BLACK);
			imageDao.updateCollectionImages(imgParam, dbAccess2);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("删除藏品图片失败:", e);
		} 
	}
	
	
	// 查询藏品的所有生活照
	public DataTable queryCollectionPhotoDataList(Map<String, String> param,int pageNo,int pageSize)throws BusinessException {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String colSequence = param.get("colSequence");
			if (CStringUtils.isEmpty(colSequence)) {
				throw new BusinessException("参数不合法");
			}
			DataTable dataTable = imageDao.selectCollectionPhotoDataList(param, pageNo, pageSize, dbAccess);
			return dataTable;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询藏品生活照失败:", e);
		} finally {
			dbAccess.endTransction();
		}
	}
}