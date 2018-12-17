package cn.smartcandy.application.a.image;

import java.util.List;
import java.util.Map;

import cn.smartcandy.common.common.BaseAction;
import cn.smartcandy.common.utils.TStringUtils;
import cn.smartcandy.framework.core.data.DataTable;

public class ImageAction extends BaseAction{

	private static final long serialVersionUID = -3592946853238845339L;
	private ImageBiz imageBiz = new ImageBiz();

	// 上传藏品图片
	public String uploadCollectionImages() {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
        TStringUtils.printStringMap(param);
        imageBiz.uploadCollectionImages(param);
        request.setAttribute("uploadSuccess", "OK");
		return null;
	}
	
	// 查询藏品的所有生活照
	public void queryCollectionPhotoDataList() {
        Map<String, String> param = TStringUtils.getStringMapFromRequest();
        TStringUtils.printStringMap(param);
        DataTable dataTable = imageBiz.queryCollectionPhotoDataList(param, super.getPageNo(), super.getPageSize());
        List<Map<String, String>> photoList = TStringUtils.turnDataTableToStringMapList(dataTable);
        Map<String, String> pageInfo = TStringUtils.turnDataTableToPageInfoMap(dataTable);
		request.setAttribute("photoList", photoList);
		request.setAttribute("pageInfo", pageInfo);
	}
	
}
