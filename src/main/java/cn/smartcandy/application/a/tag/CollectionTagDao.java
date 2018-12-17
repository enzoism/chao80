package cn.smartcandy.application.a.tag;

import java.util.List;
import java.util.Map;

import cn.smartcandy.framework.core.data.DataTable;
import cn.smartcandy.framework.core.db.mybatis.MyBatisDBAccess;

public class CollectionTagDao {
	
	// 添加TagType
	public int insertTagTypeMsg(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.insert("tag.insertTagTypeMsg", param);
	}
	// 添加Tag
	public int insertTagMsg(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.insert("tag.insertTagMsg", param);
	}
	// 查询TagType是否存在
	public int selectTagTypeMsg(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectOne("tag.selectTagTypeMsg", param);
	}
	// 查询typeList
	public DataTable selectTypeDataList(Map<String, String> param,int pageNo, int pageSize, MyBatisDBAccess dbAccess) {
		return dbAccess.selectDataTable("tag.selectTypeDataList", param, pageNo, pageSize);
	}
	// 查询tagList
	public List<Map<String, String>> selectTagList(Map<String, Object> param, MyBatisDBAccess dbAccess){
		return dbAccess.selectList("tag.selectTagList", param);
	}
	// 修改TagType
	public int updateTagTypeMsg(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.update("tag.updateTagTypeMsg", param);
	}
	// 移除以前TagType的tag
	public int updateTagTypeTags(Map<String, String> param, MyBatisDBAccess dbAccess){
		return dbAccess.update("tag.updateTagTypeTags", param);
	}
	
}
