package cn.smartcandy.application.a.collection;

import java.util.List;
import java.util.Map;

import cn.smartcandy.framework.core.data.DataTable;
import cn.smartcandy.framework.core.db.mybatis.MyBatisDBAccess;

public class CollectionDao {
	// 添加藏品（OK）
	public int insertCollection(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.insert("collection.insertCollection", param);
	}
	// 更新藏品
	public int updateCollection(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.insert("collection.updateCollection", param);
	}
	// 查找藏品详情 DONE
	public Collection selectCollectionMsg(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectOne("collection.selectCollectionMsg", param);
	}
	// 查询藏品种类 
	public List<String> selectCollectionCateList(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectList("collection.selectCollectionCateList", param);
	}
	// 查询藏品种类 
	public List<Map<String, String>> selectCollectionCate(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectList("collection.selectCollectionCate", param);
	}
	// 查询分类藏品信息 DONE
	public DataTable selectCollectionDataListWithoutCate(Map<String, String> param, int pageNo, int pageSize, MyBatisDBAccess dbAccess){
		return dbAccess.selectDataTable("collection.selectCollectionDataListWithoutCate", param, pageNo, pageSize);
	}
	// 查询分类藏品信息 DONE
	public DataTable selectCollectionDataListWithCate(Map<String, String> param, int pageNo, int pageSize, MyBatisDBAccess dbAccess){
		return dbAccess.selectDataTable("collection.selectCollectionDataListWithCate", param, pageNo, pageSize);
	}
	// 查询热门分类藏品信息 DONE
	public DataTable selectHotCollectionDataListWithoutCate(Map<String, String> param, int pageNo, int pageSize, MyBatisDBAccess dbAccess){
		return dbAccess.selectDataTable("collection.selectHotCollectionDataListWithoutCate", param, pageNo, pageSize);
	}
	// 查询热门分类藏品信息 DONE
	public DataTable selectHotCollectionDataListWithCate(Map<String, String> param, int pageNo, int pageSize, MyBatisDBAccess dbAccess){
		return dbAccess.selectDataTable("collection.selectHotCollectionDataListWithCate", param, pageNo, pageSize);
	}
	// 查询用户藏品（OK）
	public DataTable selectUserCollectionDataList(Map<String, String> param, int pageNo, int pageSize, MyBatisDBAccess dbAccess){
		return dbAccess.selectDataTable("collection.selectUserCollectionDataList", param, pageNo, pageSize);
	}
	// 查询种类藏品列表
	public List<Map<String, Object>> selecCollectionCateList(Map<String, Object> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectList("collection.selecCollectionCateList", param);
	}
	// 查询藏品Top10（OK）DONE
	public List<Collection> selectTopCollectionList(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectList("collection.selectTopCollectionList", param);
	}
	// 查询用户发布的藏品 DONE
	public DataTable selectCateCollectionDataList(Map<String, String> param, int pageNo, int pageSize, MyBatisDBAccess dbAccess){
		return dbAccess.selectDataTable("collection.selectCateCollectionDataList", param, pageNo, pageSize);
	}
	// 更新藏品的点赞数量
	public int updateCollectionLikeCount(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.update("collection.updateCollectionLikeCount", param);
	}
	// 查询发布的种类和数量
	public int selectCategaryReleaseCount(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectOne("collection.selectCategaryReleaseCount", param);
	}
	// 查询用户的六个最热产品列表
	public List<Collection> selectTopCategaryCollectionWithoutCate(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectList("collection.selectTopCategaryCollectionWithoutCate", param);
	}
	// 查询用户的六个最热产品列表
	public List<Collection> selectTopCategaryCollectionWithCate(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectList("collection.selectTopCategaryCollectionWithCate", param);
	}
	// 查询用户点赞的种类的数量
	public int selectlikeCateCount(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectOne("collection.selectlikeCateCount", param);
	}
	// 查询用户发布种类的藏品个数
	public DataTable selectReleaseCategaryMap(String userSequence, MyBatisDBAccess dbAccess){
		return dbAccess.selectDataTable("collection.selectReleaseCategaryMap", userSequence);
	}
	//---------------------------------------修复数据------------------------------------------//
	// 查询所有的藏品数据
	public List<Collection> selectCollectionSeqList(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectList("collection.selectCollectionSeqList", param);
	}
	// 查询每一个藏品的关联数据
	public int selectCollectionRelCate(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectOne("collection.selectCollectionRelCate", param);
	}
	// 在关联表中插入数据
	public int insertCollectionRelCate(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.insert("collection.insertCollectionRelCate", param);
	}
	//---------------------------------------种类数据------------------------------------------//
	public int updateCollectionCate(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.update("collection.updateCollectionCate", param);
	}
	//---------------------------------------搜索藏品------------------------------------------//
	public DataTable selectCollectionData(Map<String, String> param,int pageNo, int pageSize, MyBatisDBAccess dbAccess){
		return dbAccess.selectDataTable("collection.selectCollectionData", param, pageNo, pageSize);
	}
	//---------------------------------------藏品乱序------------------------------------------//
	// 查询所有的藏品数据
	public List<String> selectCollectionSortList(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectList("collection.selectCollectionSortList", param);
	}
	// 进行乱序操作
	public int updateCollectionSort(Map<String, Object> param, MyBatisDBAccess dbAccess){
		return dbAccess.update("collection.updateCollectionSort", param);
	}
	// 不在首页展示改藏品
	public int updateCollectionShowInHome(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.update("collection.updateCollectionShowInHome", param);
	}
	
}
