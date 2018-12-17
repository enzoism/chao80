package cn.smartcandy.application.cms.release;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.smartcandy.application.a.account.AccountBiz;
import cn.smartcandy.application.a.account.User;
import cn.smartcandy.application.a.collection.CollectionBiz;
import cn.smartcandy.common.common.BaseAction;
import cn.smartcandy.common.utils.TMemberUtils;
import cn.smartcandy.common.utils.TStringUtils;
import cn.smartcandy.framework.core.data.DataTable;

public class ReleaseAction extends BaseAction{
	private static final long serialVersionUID = -6834280112223031738L;
	private ReleaseBiz releaseBiz = new ReleaseBiz();

	// ----------------------------------------------------------轮播图发布
	// 添加轮播图 DONE
	public String addBannerMsg(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
		releaseBiz.addBannerMsg(param,super.getAdmin());
		return null;
	}
	// 查询轮播图信息 DONE
	public String queryBannerMsg(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
		ReleaseBanner queryBannerMsg = releaseBiz.queryBannerMsg(param);
		request.setAttribute("bannerMsg", queryBannerMsg);
		return null;
	}
	// 查询Banner列表 DONE
	public String queryBannerList(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    DataTable dataTable = releaseBiz.queryBannerDataList(param,super.getPageNo(),super.getPageSize());
        List<Map<String, String>> bannerList = TStringUtils.turnDataTableToStringMapList(dataTable);
        request.setAttribute("bannerList", bannerList);
        
        Map<String, String> pageInfo = TStringUtils.turnDataTableToPageInfoMap(dataTable);
		request.setAttribute("pageInfo", pageInfo);
		return null;
	}
	// 更新Banner列表 DONE
	public String modifyBannerMsg(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    releaseBiz.modifyBannerMsg(param, super.getAdmin());
		return null;
	}
	// 删除Banner信息 DONE
	public String deleteBannerMsg(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    param.put("relStatus", Release.STATUS_BLACK);
	    releaseBiz.modifyBannerMsg(param, super.getAdmin());
		return null;
	}
	
	// ----------------------------------------------------------新闻发布
	// 发布新闻
	public String addNewsMsg(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	   	releaseBiz.addNewsMsg(param, super.getAdmin());
		return null;
	}
	// 查询新闻信息 DONE
	public String queryNewsMsg(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    ReleaseNews queryNewsMsg = releaseBiz.queryNewsMsg(param);
		request.setAttribute("newsMsg", queryNewsMsg);
		return null;
	}
	// 查询新闻列表 DONE
	public String queryNewsList(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    int showType = TMemberUtils.getNewsShowType(request);
	    if (showType == 0) {
		    DataTable dataTable = releaseBiz.queryNewsDataList(param,super.getPageNo(),7);
	        List<Map<String, String>> newsList = TStringUtils.turnDataTableToStringMapList(dataTable);
	        request.setAttribute("newsList", newsList);
			
	        Map<String, String> pageInfo = TStringUtils.turnDataTableToPageInfoMap(dataTable);
			request.setAttribute("pageInfo", pageInfo);
		}else{
			DataTable dataTable =new CollectionBiz().queryCollectionDataList(param, super.getPageNo(),7);
			List<Map<String, Object>> collectionList = TStringUtils.turnDataTableToObjectMapList(dataTable);
			TStringUtils.printList(collectionList);

			Map<String, String> pageInfo = TStringUtils.turnDataTableToPageInfoMap(dataTable);
			TStringUtils.printStringMap(pageInfo);
			request.setAttribute("list", collectionList);
			request.setAttribute("pageInfo", pageInfo);
	        request.setAttribute("newsList", "展示藏品");
		}
        request.setAttribute("showType", String.valueOf(showType));
		return null;
	}
	// 更新News DONE
	public String modifyNewsMsg(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    releaseBiz.modifyNewsMsg(param, super.getAdmin());
		return null;
	}
	// 删除News信息 DONE
	public String deleteNewsMsg(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    param.put("relStatus", Release.STATUS_BLACK);
	    releaseBiz.modifyNewsMsg(param, super.getAdmin());
		return null;
	}
	// 查询展是News或者藏品
	public String queryNewsShowType(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    int showType = releaseBiz.queryNewsShowType(param);
	    request.setAttribute("showType", showType);
	    // 数据缓存起来
	    TMemberUtils.saveNewsShowType(request, showType);
		return null;
	}
	// 改变News展示内容
	public String changeNewsShowType(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    int showType = releaseBiz.changeNewsShowType(param,request);
	    request.setAttribute("showType", showType);
	    // 数据缓存起来
	    TMemberUtils.saveNewsShowType(request, showType);
		return null;
	}
	// ----------------------------------------------------------视频发布
	// 发布视频
	public String addVideoMsg(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    releaseBiz.addVideoMsg(param,super.getAdmin());
		return null;
	}
	// 查询Video信息 DONE
	public String queryVideoMsg(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    ReleaseVideo videoMsg = releaseBiz.queryVideoMsg(param);
		request.setAttribute("videoMsg", videoMsg);
		return null;
	}
	// 查询video列表 DONE
	public String queryVideoList(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    DataTable dataTable = releaseBiz.queryVideoList(param,super.getPageNo(),super.getPageSize());
        List<Map<String, String>> videoList = TStringUtils.turnDataTableToStringMapList(dataTable);
        request.setAttribute("videoList", videoList);
		
        Map<String, String> pageInfo = TStringUtils.turnDataTableToPageInfoMap(dataTable);
		request.setAttribute("pageInfo", pageInfo);
		return null;
	}
	// 更新Video DONE
	public String modifyVideoMsg(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    releaseBiz.modifyVideoMsg(param, super.getAdmin());
		return null;
	}
	// 删除Video信息 DONE
	public String deleteVideoMsg(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    param.put("relStatus", Release.STATUS_BLACK);
	    releaseBiz.modifyVideoMsg(param, super.getAdmin());
		return null;
	}
	// ----------------------------------------------------------生活照发布
	// 发布生活照
	public String addPhotoMsg(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    ReleasePhoto addPhotoMsg = releaseBiz.addPhotoMsg(param, super.getUser());
	    request.setAttribute("photoMsg", addPhotoMsg);
	    return null;
	}
	// 查询藏品生活照 DONE
	public String queryPhotoList(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    
	    DataTable dataTable = releaseBiz.queryPhotoDataList(param,super.getPageNo(),super.getPageSize());
        List<Map<String, String>> photoList = TStringUtils.turnDataTableToStringMapList(dataTable);
        request.setAttribute("photoList", photoList);
		
        Map<String, String> pageInfo = TStringUtils.turnDataTableToPageInfoMap(dataTable);
		request.setAttribute("pageInfo", pageInfo);
		return null;
	}
	// 查询藏品生活照 (后台管理)
	public String queryColPhotoList(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    
	    DataTable dataTable = releaseBiz.queryColPhotoList(param,super.getPageNo(),super.getPageSize());
        List<Map<String, String>> photoList = TStringUtils.turnDataTableToStringMapList(dataTable);
        request.setAttribute("photoList", photoList);
		
        Map<String, String> pageInfo = TStringUtils.turnDataTableToPageInfoMap(dataTable);
		request.setAttribute("pageInfo", pageInfo);
		return null;
	}
	// 拉黑生活照
	public String blackPhotoMsg(){
        Map<String, Object> param = TStringUtils.getObjectMapFromRequest();
	    TStringUtils.printObjectMap(param);
	    String[] photoList = request.getParameterValues("photoList[]");
	    param.put("photoList", photoList);
	    releaseBiz.blackPhotoMsg(param, super.getAdmin());
		return null;
	}
	
	// ----------------------------------------------------------种类发布
	// 发布种类
	public String addCategaryMsg(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    releaseBiz.addCategaryMsg(param, request ,super.getAdmin());
	    
		// 系统发布种类（缓存）
		Map<String, String> hashMap = new HashMap<String,String>();
		hashMap.put("cateStatus", Release.STATUS_NORMAL);
		new ReleaseBiz().queryCategaryList(hashMap,request);
		return null;
	}
	// 查询种类信息 DONE
	public String queryCategaryMsg(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    ReleaseCategary cateMsg = releaseBiz.queryCategaryMsg(param);
		request.setAttribute("cateMsg", cateMsg);
		return null;
	}
	// 查询种类列表  DONE 
	public String queryCategaryList(){
		List<ReleaseCategary> categaryList = TMemberUtils.getCategaryList(request);
	    request.setAttribute("categaryList", categaryList);
	    
	    Map<String, String> pageInfo = TStringUtils.turnListToPageInfoMap(categaryList);
		request.setAttribute("pageInfo", pageInfo);
		return null;
	}
	// 查询种类列表  DONE 
	public String queryCategaryDataList(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    DataTable dataTable = releaseBiz.queryCategaryDataList(param);
	    
	    List<Map<String, Object>> categaryListMap = TStringUtils.turnDataTableToObjectMapList(dataTable);
	    request.setAttribute("categaryList", categaryListMap);
	    
	    Map<String, String> pageInfo = TStringUtils.turnDataTableToPageInfoMap(dataTable);
		request.setAttribute("pageInfo", pageInfo);
		return null;
	}
	// 更新Video DONE
	public String modifyCategaryMsg(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    releaseBiz.modifyCategaryMsg(param, request, super.getAdmin());
		return null;
	}
	// 删除Video信息 DONE
	public String deleteCategaryMsg(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    param.put("cateStatus", Release.STATUS_BLACK);
	    releaseBiz.modifyCategaryMsg(param, request, super.getAdmin());
		return null;
	}

	
	// -----------------------------------用户中心----------------------------------- //
	// 查询种类列表，附带个人信息  DONE 
	public String queryReleasedCategaryList(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    List<ReleaseCategary> categaryList  = new CollectionBiz().queryCategaryReleaseCount(param, request, user);
	    request.setAttribute("categaryList", categaryList);
        User userMsg = new AccountBiz().queryUserAccount(param,request,user);
		request.setAttribute("userMsg", userMsg);

		return null;
	}
	
	// 查询用户点赞的藏品列表
	public String querylikeCategaryList(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    List<ReleaseCategary> categaryList  = new CollectionBiz().querylikeCategaryList(param, request, super.getUser());
	    request.setAttribute("categaryList", categaryList);
	    //DONE 请求用户信息放进去
        User userMsg = new AccountBiz().queryUserAccount(new HashMap<String, String>(),request,super.getUser());
		request.setAttribute("userMsg", userMsg);
		return null;
	}
	// 查询用户发布的藏品 DONE
	public String queryReleasedDataList(){
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
	    TStringUtils.printStringMap(param);
	    DataTable dataTable = releaseBiz.queryReleasedDataList(param, super.getPageNo(), super.getPageSize());
	    
	    // 查询藏品是否关注
		//List<Map<String, String>> collectionList = new CollectionBiz().selectColAttentionOrNot(dataTable, user);
	    // 不查询藏品是否关注
		List<Map<String, String>> collectionList = TStringUtils.turnDataTableToStringMapList(dataTable);
		// TODO 加上用户的
	    request.setAttribute("categaryList", collectionList);
	    Map<String, String> pageInfo = TStringUtils.turnDataTableToPageInfoMap(dataTable);
		request.setAttribute("pageInfo", pageInfo);
		return null;
	}
	
	
	

}
