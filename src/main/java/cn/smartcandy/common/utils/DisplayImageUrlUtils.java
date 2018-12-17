package cn.smartcandy.common.utils;

import java.io.File;

import org.apache.commons.lang.StringUtils;

import cn.smartcandy.framework.conf.SysConfig;
import cn.smartcandy.framework.core.data.DataTable;

/**
 * @项目名称：collection
 * @类名称：DisplayImageUrlUtils
 * @类描述：DisplayImageUrlUtils 获取回显照片路径的工具类
 * @创建人：yaoyinghong
 * @创建时间：2017年7月11日 下午12:02:40
 * @修改人：yaoyinghong
 * @修改时间：2017年7月11日 下午12:02:40 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class DisplayImageUrlUtils {

	private static final String UPLOAD_BASE = SysConfig.getInstance().get("UPLOAD_BASE");
	
	/**
	 * @方法描述:
	 *  
	 * @param dataTable DataTable dataTable 包含 imageURL的 DataTable
	 * @param isLogo Boolean isLogo 是否为Logo图片
	 * @param imgCloumnName DataTable中包含 imageURL的 字段名
	 * @return String[] uploadPath + piImageURL
	 */
	public static String[] getDisplayImageUrlList(DataTable dataTable, Boolean isLogo, String imgCloumnName){
		
		//获取图片路径
		String[] pspUrl = new String[dataTable.getRowCount()];
		for(int i=0; i<dataTable.getRowCount();i++){
			String logoUrl = dataTable.getNotNullString(i, imgCloumnName); //相对路径
			pspUrl[i] = getImgRelativePath(logoUrl, isLogo);
		}
		return pspUrl;
	}
	
	/**
	 * @方法描述:验证图片是否存在，获取图片相对路径
	 *  
	 * @param imgUrl 图片相对路径
	 * @param isLogo Boolean isLogo 是否为Logo图片
	 * @return String 返回图片相对路径
	 */
	public static String getImgRelativePath(String imgUrl, Boolean isLogo){
		String uploadName = StringUtils.EMPTY;
		String[] uploadLogoFileNames = null;
		String[] uploadLogoURL = imgUrl.split("/");
		if(uploadLogoURL.length>1){
			String uploadUrl = StringUtils.EMPTY;
			//图片相对地址
			for(int j=0; j<uploadLogoURL.length-1; j++){
				uploadUrl += uploadLogoURL[j] + "/";
			}
			
			String uploadFileName = uploadLogoURL[uploadLogoURL.length-1];	//获取文件名
			
			if(isLogo){
				uploadLogoFileNames = uploadFileName.split("\\.");
				uploadName = uploadLogoFileNames[0]+"_thumb.jpg";//缩略图文件名(logo)
				return getPspUrl(UPLOAD_BASE, uploadUrl, uploadName, "/img/");
			}else{
				uploadName = uploadFileName;
				return getPspUrl(UPLOAD_BASE, uploadUrl, uploadName, null);
			}
			
		}else{
			return  StringUtils.EMPTY;
		}
	}
	
	/**
	 * @方法描述:验证图片是否存在，存在则返回图片路径
	 *  
	 * @param uploadBasePath 图片存储的根目录
	 * @param uploadUrl 图片相对地址
	 * @param uploadName 图片名称
	 * @param returnBaseUrl 返回图片path的基础路径
	 * @return String 回显图片相对路径Path
	 */
	private static String getPspUrl(String uploadBasePath, String uploadUrl, String uploadName, String returnBaseUrl ){
		//缩略图绝对路径
		StringBuilder pspUrl = new StringBuilder();
		
		File imageFile = new File(uploadBasePath + uploadUrl,uploadName);  
		if(imageFile.exists()){
			//pspUrl[i] = "/img/" + uploadUrl + uploadName; 前端页面传入了  "/img/"
			return pspUrl.append(org.apache.commons.lang3.StringUtils.trimToEmpty(returnBaseUrl)).append(uploadUrl).append(uploadName).toString();
	    }else{
	    	return "";
	    }
	}
	
	
	
}
