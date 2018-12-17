package cn.smartcandy.application.a.attention;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import cn.smartcandy.application.a.account.AccountBiz;
import cn.smartcandy.common.common.BaseAction;
import cn.smartcandy.common.utils.TStringUtils;
import cn.smartcandy.framework.core.data.DataTable;
import cn.smartcandy.framework.core.exception.BusinessException;

public class AttentionAction extends BaseAction{

	private static final long serialVersionUID = -3791225530177941735L;
	private AttentionBiz attentionBiz = new AttentionBiz();
	private AccountBiz accountBiz = new AccountBiz();
	// 关注用户 DONE
	public String addUserAttention() throws BusinessException {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    attentionBiz.addUserAttention(param,super.getUser());
		return null;
	}
	// 取消关注用户 DONE
	public String removeUserAttention() throws BusinessException {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    attentionBiz.removeUserAttention(param,super.getUser());
		return null;
	}
	// 查询所有关注用户 DONE
	public String queryAttentionUserDataList() throws BusinessException {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    DataTable dataTable = attentionBiz.queryAttentionUserDataList(param,super.getUser(),super.getPageNo(),super.getPageSize());
		List<Map<String, Object>> userMapList = TStringUtils.turnDataTableToObjectMapList(dataTable);
	    
		// 查询用户最新藏品图片
		if (!CollectionUtils.isEmpty(userMapList)) {
			accountBiz.queryCateUserListNoAttention(userMapList,param.get("cateSequence"),user);//不判断藏品是否点赞
		}
	    Map<String, String> pageInfoMap = TStringUtils.turnDataTableToPageInfoMap(dataTable);
	    TStringUtils.printStringMap(pageInfoMap);
	    request.setAttribute("userList", userMapList);
	    request.setAttribute("pageInfo", pageInfoMap);

		return null;
	}
	// 查询所有粉丝用户 DONE
	public String queryFansUserDataList() throws BusinessException {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    DataTable dataTable = attentionBiz.queryFansUserDataList(param,super.getUser(),super.getPageNo(),super.getPageSize());
	    List<Map<String, String>> userList = new AccountBiz().selectUserAttentionOrNot(dataTable, super.getUser());

	    Map<String, String> pageInfoMap = TStringUtils.turnDataTableToPageInfoMap(dataTable);
	    TStringUtils.printStringMap(pageInfoMap);
	    
	    request.setAttribute("userList", userList);
	    request.setAttribute("pageInfo", pageInfoMap);
		return null;
	}
	
	// 关注商品 DONE
	public String addCollectionAttention() throws BusinessException {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    attentionBiz.addCollectionAttention(param,super.getUser());
		return null;
	}
	// 取消关注商品 DONE
	public String removeCollectionAttention() throws BusinessException {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    attentionBiz.removeCollectionAttention(param,super.getUser());
		return null;
	}
	// 查询所有关注藏品
	public String queryAttentionCollectionDataList() throws BusinessException {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);

	    DataTable dataTable = attentionBiz.queryAttentionCollectionDataList(param,super.getUser(),super.getPageNo(),super.getPageSize());
	    List<Map<String, String>> collectionList = TStringUtils.turnDataTableToStringMapList(dataTable);
	    Map<String, String> pageInfoMap = TStringUtils.turnDataTableToPageInfoMap(dataTable);
	    for (int i = 0;null!=collectionList &&  i < collectionList.size(); i++) {
	    	collectionList.get(i).put("hasAttention", String.valueOf(Boolean.TRUE));
		}
	    request.setAttribute("collectionList", collectionList);
	    request.setAttribute("pageInfo", pageInfoMap);
		return null;
	}
	// 查询所有关注藏品
	public String queryFansCollectionDataList() throws BusinessException {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    DataTable dataTable = attentionBiz.queryFansCollectionDataList(param,super.getUser(),super.getPageNo(),super.getPageSize());
	    List<Map<String, String>> collectionList = TStringUtils.turnDataTableToStringMapList(dataTable);
	    Map<String, String> pageInfoMap = TStringUtils.turnDataTableToPageInfoMap(dataTable);
	    
	    request.setAttribute("collectionList", collectionList);
	    request.setAttribute("pageInfo", pageInfoMap);
		return null;
	}
	
	// 查询消息列表
	public String queryAttentionMsgDataList() throws BusinessException {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    DataTable dataTable = attentionBiz.queryAttentionMsgDataList(param,super.getUser(),super.getPageNo(),super.getPageSize());
	    List<Map<String, String>> msgList = TStringUtils.turnDataTableToStringMapList(dataTable);
	    request.setAttribute("msgList", msgList);
	    Map<String, String> pageInfoMap = TStringUtils.turnDataTableToPageInfoMap(dataTable);
	    request.setAttribute("pageInfo", pageInfoMap);
		return null;
	}
}
