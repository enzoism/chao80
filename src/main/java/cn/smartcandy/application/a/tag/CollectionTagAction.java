package cn.smartcandy.application.a.tag;

import java.util.List;
import java.util.Map;

import cn.smartcandy.common.common.BaseAction;
import cn.smartcandy.common.utils.TStringUtils;

public class CollectionTagAction extends BaseAction{

	private static final long serialVersionUID = -6353672721980423824L;
	private  CollectionTagBiz tagBiz = new CollectionTagBiz();
	// 添加Tag
	public String addTagTypeMsg()  throws Exception {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    tagBiz.addTagTypeMsg(param, super.getAdmin());
		return null;
	}
	// 查询类别的所有具体Tag
	public String queryTagTypeDataList()  throws Exception {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
		List<Map<String, String>> tagList = tagBiz.queryTypeDataList(param,super.getPageNo(),super.getPageSize());
		request.setAttribute("tagList", tagList);
		Map<String, String> pageInfo = TStringUtils.turnListToPageInfoMap(tagList);
		request.setAttribute("pageInfo", pageInfo);
		return null;
	}
	// 添加Tag
	public String deleteTagTypeMsg()  throws Exception {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    tagBiz.deleteTagTypeMsg(param, super.getAdmin());
		return null;
	}
	// 修改Tag
	public String modifyTagTypeMsg()  throws Exception {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    tagBiz.modifyTagTypeMsg(param, super.getAdmin());
		return null;
	}
	// 查询指定的tag
	public String selectTagTypeTagList() {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
		Map<String, String> tagMap = tagBiz.queryTagTypeTagList(param,super.getPageNo(),super.getPageSize());
		request.setAttribute("tagMap", tagMap);
		return null;
	}

}
