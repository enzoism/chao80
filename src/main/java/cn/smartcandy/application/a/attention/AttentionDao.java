package cn.smartcandy.application.a.attention;

import java.util.List;
import java.util.Map;

import cn.smartcandy.framework.core.data.DataTable;
import cn.smartcandy.framework.core.db.mybatis.MyBatisDBAccess;

public class AttentionDao {
	// 关注用户 DONE
	public int insertUserAttention(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.insert("attention.insertUserAttention", param);
	}
	// 取消关注用户 DONE
	public int updateUserAttention(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.update("attention.updateUserAttention", param);
	}
	// 查询所有关注用户
	public List<String> selectAttentionUserList(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectList("attention.selectAttentionUserList", param);
	}
	// 查询所有关注用户 DONE
	public DataTable selectAttentionUserDataList(Map<String, String> param,int pageNo,int pageSize, MyBatisDBAccess dbAccess){
		return dbAccess.selectDataTable("attention.selectAttentionUserDataList", param, pageNo, pageSize);
	}
	// 查询所有粉丝用户 DONE
	public DataTable selectFansUserDataList(Map<String, String> param,int pageNo,int pageSize, MyBatisDBAccess dbAccess){
		return dbAccess.selectDataTable("attention.selectFansUserDataList", param, pageNo, pageSize);
	}
	// 查询有没有关注过某个用户 DONE
	public int selectUserAttentionCount(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectOne("attention.selectUserAttentionCount", param);
	}
	//================================================================================================//
	// 查询是否关注商品 
	public int selectCollectionAttentionCount(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectOne("attention.selectCollectionAttentionCount", param);
	}
	// 关注商品 DONE
	public int insertCollectionAttention(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.insert("attention.insertCollectionAttention", param);
	}
	// 取消关注商品 DONE
	public int updateCollectionAttention(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.update("attention.updateCollectionAttention", param);
	}
	// 查询所有关注藏品--没有种类的查询
	public DataTable selectAttentionColDataListWithoutCate(Map<String, String> param, int PageNo,int pageSize, MyBatisDBAccess dbAccess){
		return dbAccess.selectDataTable("attention.selectAttentionColDataListWithoutCate", param, PageNo, pageSize);
	}
	// 查询所有关注藏品--有种类的查询
	public DataTable selectAttentionColDataListWithCate(Map<String, String> param, int PageNo,int pageSize, MyBatisDBAccess dbAccess){
		return dbAccess.selectDataTable("attention.selectAttentionColDataListWithCate", param, PageNo, pageSize);
	}
	// 查询所有被赞藏品
	public DataTable selectFansCollectionDataList(Map<String, String> param, int PageNo,int pageSize, MyBatisDBAccess dbAccess){
		return dbAccess.selectDataTable("attention.selectFansCollectionDataList", param, PageNo, pageSize);
	}
	//---------------------------------------消息通知---------------------------------------//
	// 插入消息
	public int insertAttentionMsg(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.insert("attention.insertAttentionMsg", param);
	}
	// 更新消息状态
	public int updateAttentionMsg(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.update("attention.updateAttentionMsg", param);
	}
	
	// 查询消息个数
	public int selectAttentionMsgCount(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectOne("attention.selectAttentionMsgCount", param);
	}
	// 查询消息列表
	public DataTable selectAttentionMsgDataList(Map<String, String> param, int PageNo,int pageSize, MyBatisDBAccess dbAccess){
		return dbAccess.selectDataTable("attention.selectAttentionMsgDataList", param, PageNo, pageSize);
	}

}
