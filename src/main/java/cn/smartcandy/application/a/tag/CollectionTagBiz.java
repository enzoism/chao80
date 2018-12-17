package cn.smartcandy.application.a.tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import cn.smartcandy.application.a.account.Admin;
import cn.smartcandy.common.common.BaseBiz;
import cn.smartcandy.common.utils.TStringUtils;
import cn.smartcandy.framework.biz.workdate.WorkDate;
import cn.smartcandy.framework.core.data.DataTable;
import cn.smartcandy.framework.core.db.mybatis.MyBatisDBAccess;
import cn.smartcandy.framework.core.exception.BusinessException;
import cn.smartcandy.framework.utils.CStringUtils;

public class CollectionTagBiz extends BaseBiz{
	private CollectionTagDao tagDao = new CollectionTagDao();
	// 查询类别的所有具体Tag
	public int addTagTypeMsg(Map<String, String> param,Admin admin){
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String cateSequence = param.get("cateSequence");
			if (CStringUtils.isEmpty(cateSequence)) {
				throw new BusinessException("参数不合法");
			}
			String typeSequence = dbAccess.getSeqID("a_tag_type");
			param.put("typeSequence", typeSequence);
			param.put("typeStatus", CollectionTag.STATUS_NORMAL);
			// 查询tagType是否存在
			int TagTypeCount = tagDao.selectTagTypeMsg(param, dbAccess);
			if (TagTypeCount>0) {
				throw new BusinessException("当前标签类型已经存在，添加标签失败！");
			}
			// 添加tagType
			int tagTypeMsg = tagDao.insertTagTypeMsg(param, dbAccess);
			
			// 添加tag
			String tagNames = param.get("tagNames");
			String[] tagName1 = tagNames.split(";");
			Set<String> tagList = new HashSet<String>();
			for (int i = 0; i < tagName1.length; i++) {
				String[] tagName2 = tagName1[i].split("；");
				tagList.addAll(Arrays.asList(tagName2));
			}
			for (String tagName : tagList) {  
				Map<String, String> map = new HashMap<String, String>();
				map.put("typeSequence", typeSequence); 
				String tagSequence = dbAccess.getSeqID("a_tag");
				map.put("tagSequence", tagSequence); 
				map.put("tagName", tagName); 
				map.put("tagStatus", CollectionTag.STATUS_NORMAL); 
				map.put("tagDate", WorkDate.getSystemTime()); 
				tagDao.insertTagMsg(map, dbAccess);
			}  
			dbAccess.commitTransction();
			return tagTypeMsg;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("添加标签失败：", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	
	//=================================可能要重做======================================//
	// 查询类别的所有具体Tag
	public List<Map<String, String>> queryTypeDataList(Map<String, String> param,int pageNo, int pageSize){
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			// 查询列表
			DataTable typeDataTable  = tagDao.selectTypeDataList(param, pageNo, pageSize, dbAccess);
			List<Map<String, String>> typeMapList = TStringUtils.turnDataTableToStringMapList(typeDataTable);
			List<String> typeList = TStringUtils.turnStringMapListToList(typeMapList, "typeSequence");
			// 查询对应的数据
			List<Map<String, String>> tagList = new ArrayList<Map<String, String>>();
			if (!CollectionUtils.isEmpty(typeList)) {
				Map<String, Object> typeParam = new HashMap<>();
				typeParam.put("typeList", typeList);
				tagList = tagDao.selectTagList(typeParam, dbAccess);
			}
			return tagList;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询标签失败：", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 删除TagType
	public int deleteTagTypeMsg(Map<String, String> param,Admin admin){
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String typeSequence = param.get("typeSequence");
			if (CStringUtils.isEmpty(typeSequence)) {
				throw new BusinessException("参数不合法");
			}
			String typeStatus = param.get("typeStatus");
			typeStatus = CStringUtils.isEmpty(typeStatus)?CollectionTag.STATUS_DELETE:typeStatus;
			Map<String, String> modifyParam = new HashMap<String, String>();
			modifyParam.put("typeStatus", typeStatus);
			modifyParam.put("typeSequence", typeSequence);
			int typeMsg = tagDao.updateTagTypeMsg(modifyParam, dbAccess);
			dbAccess.commitTransction();
			return typeMsg;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("删除标签失败：", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	// 删除TagType
	public int modifyTagTypeMsg(Map<String, String> param,Admin admin){
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String typeSequence = param.get("typeSequence");
			if (CStringUtils.isEmpty(typeSequence)) {
				throw new BusinessException("参数不合法");
			}
			// 移除以前的tag
			Map<String, String> deleteParam = new HashMap<String, String>();
			deleteParam.put("tagStatus", CollectionTag.STATUS_DELETE);
			deleteParam.put("typeSequence", typeSequence);
			tagDao.updateTagTypeTags(deleteParam, dbAccess);
			
			// 添加tag
			String tagNames = param.get("tagNames");
			String[] tagName1 = tagNames.split(";");
			Set<String> tagList = new HashSet<String>();
			for (int i = 0; i < tagName1.length; i++) {
				String[] tagName2 = tagName1[i].split("；");
				tagList.addAll(Arrays.asList(tagName2));
			}
			for (String tagName : tagList) {  
				Map<String, String> map = new HashMap<String, String>();
				map.put("typeSequence", typeSequence); 
				String tagSequence = dbAccess.getSeqID("a_tag");
				map.put("tagSequence", tagSequence); 
				map.put("tagName", tagName); 
				map.put("tagStatus", CollectionTag.STATUS_NORMAL); 
				map.put("tagDate", WorkDate.getSystemTime()); 
				tagDao.insertTagMsg(map, dbAccess);
			}  
			dbAccess.commitTransction();
			return 1;
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("删除标签失败：", e);
		} finally {
			dbAccess.endTransction();
		}
	}
	
	
	// 查询类别的所有具体Tag
	public Map<String, String> queryTagTypeTagList(Map<String, String> param,int pageNo, int pageSize){
		Map<String, String> resultMap = new HashMap<String, String>();
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			// 查询列表
			String typeSequence = param.get("typeSequence");
			if (CStringUtils.isEmpty(typeSequence)) {
				throw new BusinessException("参数不合法");
			}
			List<String> typeList = new ArrayList<>();
			typeList.add(typeSequence);
			// 查询对应的数据
			List<Map<String, String>> tagList = new ArrayList<Map<String, String>>();
			if (!CollectionUtils.isEmpty(typeList)) {
				Map<String, Object> typeParam = new HashMap<>();
				typeParam.put("typeList", typeList);
				tagList = tagDao.selectTagList(typeParam, dbAccess);
				// 转化数据结构
				String typeName = null;
				List<String> tagNamesList = new ArrayList<>();
				for (int i = 0; i < tagList.size(); i++) {
					Map<String, String> map = tagList.get(i);
					typeName = map.get("typeName");
					tagNamesList.add(map.get("tagName"));
				}
				
				resultMap.put("typeName", typeName);
				resultMap.put("typeSequence", typeSequence);
				resultMap.put("tagNames", CStringUtils.join(tagNamesList," ;"));
				
			}else{
				System.out.println("当前tag类型不存在");
			}
			return resultMap;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询标签失败：", e);
		} finally {
			dbAccess.endTransction();
		}
	}
}
