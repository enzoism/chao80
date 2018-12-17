package cn.smartcandy.common.upload;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.smartcandy.framework.conf.SysConfig;
import cn.smartcandy.framework.core.exception.BusinessException;
import cn.smartcandy.framework.core.log.Log;
import cn.smartcandy.framework.utils.DateTimeUtils;
import cn.smartcandy.framework.web.struts.BaseAction;
import net.coobird.thumbnailator.Thumbnails;

/**
 * @项目名称：collection
 * @类名称：UploadImageAction
 * @类描述：UploadImageAction
 * @创建人：yaoyinghong
 * @创建时间：2017年7月12日 上午10:31:48
 * @修改人：yaoyinghong
 * @修改时间：2017年7月12日 上午10:31:48 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class UploadImageAction extends BaseAction {

	private static final long serialVersionUID = 8921889656896711773L;
	private HttpServletRequest request = ServletActionContext.getRequest();

	private String title;
	private File[] file;
	private String[] fileFileName;
	private String[] fileContentType;
	
	
	private File[] saveFiles;
	private String[] saveFileName;
	private String[] saveContentType;
	
	// 本地域名
	private static String LOCOLHOST = "/";//文件地址
	
	// 上传路径（本地）
//	private static String UPLOAD_BASE = SysConfig.getInstance().get("UPLOAD_BASE_LOCAL");//默认地址
//	private static String UPLOAD_IMAGE = SysConfig.getInstance().get("UPLOAD_IMAGE_LOCAL");//图片地址
//	private static String UPLOAD_MIMAGE = SysConfig.getInstance().get("UPLOAD_MIMAGE_LOCAL");//图片地址
//	private static String UPLOAD_VIDEO = SysConfig.getInstance().get("UPLOAD_VIDEO_LOCAL");//视频地址
//	private static String UPLOAD_FILE = SysConfig.getInstance().get("UPLOAD_FILE_LOCAL");//文件地址
	
	// 上传路径（远程）
	private static String UPLOAD_BASE = SysConfig.getInstance().get("UPLOAD_BASE");//默认地址
	private static String UPLOAD_IMAGE = SysConfig.getInstance().get("UPLOAD_IMAGE");//图片地址
	private static String UPLOAD_MIMAGE = SysConfig.getInstance().get("UPLOAD_MIMAGE");//图片地址
	private static String UPLOAD_VIDEO = SysConfig.getInstance().get("UPLOAD_VIDEO");//视频地址
	private static String UPLOAD_FILE = SysConfig.getInstance().get("UPLOAD_FILE");//文件地址
	
	// 网址路径
	private static String UPLOAD_BASE_URL = SysConfig.getInstance().get("UPLOAD_BASE_URL");//默认地址
	private static String UPLOAD_IMAGE_URL = SysConfig.getInstance().get("UPLOAD_IMAGE_URL");//图片地址
	private static String UPLOAD_MIMAGE_URL = SysConfig.getInstance().get("UPLOAD_MIMAGE_URL");//图片地址
	private static String UPLOAD_VIDEO_URL = SysConfig.getInstance().get("UPLOAD_VIDEO_URL");//视频地址
	private static String UPLOAD_FILE_URL = SysConfig.getInstance().get("UPLOAD_FILE_URL");//文件地址

	// 默认上传（不明确传输类型）
	public void uploadBase() throws Exception {
		// 异常判断
		if ((this.file == null) || (this.file.length <= 0)) {
			throw new BusinessException("未找到上传文件！");
		}
		if (!ServletFileUpload.isMultipartContent(request)){
			throw new BusinessException("上传文件类型错误！");
		}
		// 上传文件
		for (int i = 0; i < this.file.length; ++i) {
			Log.logInfo("\t上传文件: " + this.file[i] + "\n\t\t上传文件类型: "+ this.fileContentType[i]);

			String prefix = DateTimeUtils.getSystemTime("yyyyMMddHHmmssSSS");				// 图片名称
			String fileName = prefix + "." + getFileExtionName(this.fileFileName[i]);		// 图片全称（图片+后缀）
			String fileDir = DateTimeUtils.getSystemDate("yyyy/MM/dd/");					// 图片文件夹
			String uploadDir = UPLOAD_BASE + fileDir;										// 图片文件夹绝对地址
			
			// 文件上传
			File toFile;
			try {
				toFile = new File(uploadDir, fileName);
				if (!(toFile.getParentFile().exists())) {
					toFile.getParentFile().mkdirs();
				}
				FileUtils.copyFile(this.file[i], toFile);										
			} catch (Exception e) {
				throw new BusinessException("文件上传失败");
			}
			
			// 上传的网络地址
			String serverURL = this.getServerURL(UPLOAD_BASE_URL,fileDir,fileName);											// 文件的网络地址
			request.setAttribute("src", serverURL);
			Log.logInfo("  ---->file In server路径："+toFile   +"\n 域名访问："+serverURL);
		}
	}
	// 上传图片
	public void uploadImages() throws Exception {
		System.out.println("------------------------------this.file:"+this.file);
		System.out.println("------------------------------this.file.length:"+this.file.length);
		System.out.println("------------------------------this.fileFileName:"+this.fileFileName);
		//System.out.println("------------------------------this.uploadContentType:"+this.fileContentType);
		// 异常判断
		if ((this.file == null) || (this.file.length <= 0)) {
			throw new BusinessException("未找到上传文件！");
		}
		if (!ServletFileUpload.isMultipartContent(request)){
			throw new BusinessException("上传文件类型错误！");
		}
		// 上传文件
		for (int i = 0; i < this.file.length; ++i) {

			String prefix = DateTimeUtils.getSystemTime("yyyyMMddHHmmssSSS");				// 图片名称
			String fileName = prefix + "." + getFileExtionName(this.fileFileName[i]);		// 图片全称（图片+后缀）
			String fileDir = DateTimeUtils.getSystemDate("yyyy/MM/dd/");					// 图片文件夹
			String uploadDir = UPLOAD_IMAGE + fileDir;										// 图片文件夹绝对地址
			
			// 文件上传
			File toFile;
			try {
				toFile = new File(uploadDir, fileName);
				if (!(toFile.getParentFile().exists())) {
					toFile.getParentFile().mkdirs();
				}
				FileUtils.copyFile(this.file[i], toFile);										
			} catch (Exception e) {
				throw new BusinessException("文件上传失败");
			}
			
			String serverURL = this.getServerURL(UPLOAD_IMAGE_URL,fileDir,fileName);											// 文件的网络地址

			request.setAttribute("src", serverURL);
			Log.logInfo("  ---->file In server路径："+toFile   +"\n 域名访问："+serverURL);
		}
	}
	
	// 上传视频
	public void uploadVideo() throws Exception {
		System.out.println("------------------------------this.file:"+this.file);
		System.out.println("------------------------------this.file.length:"+this.file.length);
		System.out.println("------------------------------this.fileFileName:"+this.fileFileName);
		System.out.println("------------------------------this.uploadContentType:"+this.fileContentType);
		// 异常判断
		if ((this.file == null) || (this.file.length <= 0)) {
			throw new BusinessException("未找到上传文件！");
		}
		if (!ServletFileUpload.isMultipartContent(request)){
			throw new BusinessException("上传文件类型错误！");
		}
		// 上传文件
		for (int i = 0; i < this.file.length; ++i) {
			Log.logInfo("\t上传文件: " + this.file[i] + "\n\t\t上传文件类型: "+ this.fileContentType[i]);

			String prefix = DateTimeUtils.getSystemTime("yyyyMMddHHmmssSSS");				// 图片名称
			String fileName = prefix + "." + getFileExtionName(this.fileFileName[i]);		// 图片全称（图片+后缀）
			String fileDir = DateTimeUtils.getSystemDate("yyyy/MM/dd/");					// 图片文件夹
			String uploadDir = UPLOAD_VIDEO + fileDir;										// 图片文件夹绝对地址
			
			// 文件上传
			File toFile;
			try {
				toFile = new File(uploadDir, fileName);
				if (!(toFile.getParentFile().exists())) {
					toFile.getParentFile().mkdirs();
				}
				FileUtils.copyFile(this.file[i], toFile);										
			} catch (Exception e) {
				throw new BusinessException("文件上传失败");
			}
			
			// 上传的网络地址
			String serverURL = getHostURL()+"upload/"+UPLOAD_VIDEO_URL+fileDir+fileName;											// 文件的网络地址
			request.setAttribute("src", serverURL);
			Log.logInfo("  ---->file In server路径："+toFile   +"\n 域名访问："+serverURL);

		}
	}
	
	// 上传文件
	public void uploadFile() throws Exception {
		System.out.println("------------------------------this.file:"+this.file);
		System.out.println("------------------------------this.file.length:"+this.file.length);
		System.out.println("------------------------------this.fileFileName:"+this.fileFileName);
		System.out.println("------------------------------this.uploadContentType:"+this.fileContentType);
		// 异常判断
		if ((this.file == null) || (this.file.length <= 0)) {
			throw new BusinessException("未找到上传文件！");
		}
		if (!ServletFileUpload.isMultipartContent(request)){
			throw new BusinessException("上传文件类型错误！");
		}
		// 上传文件
		for (int i = 0; i < this.file.length; ++i) {
			Log.logInfo("\t上传文件: " + this.file[i] + "\n\t\t上传文件类型: "+ this.fileContentType[i]);

			String prefix = DateTimeUtils.getSystemTime("yyyyMMddHHmmssSSS");				// 图片名称
			String fileName = prefix + "." + getFileExtionName(this.fileFileName[i]);		// 图片全称（图片+后缀）
			String fileDir = DateTimeUtils.getSystemDate("yyyy/MM/dd/");					// 图片文件夹
			String uploadDir = UPLOAD_FILE + fileDir;										// 图片文件夹绝对地址
			// 文件上传
			File toFile;
			try {
				toFile = new File(uploadDir, fileName);
				if (!(toFile.getParentFile().exists())) {
					toFile.getParentFile().mkdirs();
				}
				FileUtils.copyFile(this.file[i], toFile);										
			} catch (Exception e) {
				throw new BusinessException("文件上传失败");
			}
			
			// 上传的网络地址
			String serverURL = this.getServerURL(UPLOAD_FILE_URL,fileDir,fileName);
			request.setAttribute("src", serverURL);
			Log.logInfo("  ---->file In server路径："+toFile   +"\n 域名访问："+serverURL);

		}
	}
	
	// 上传缩略图
	public void uploadThumb() throws Exception {
		System.out.println("------------------------------this.file:"+this.file);
		System.out.println("------------------------------this.file.length:"+this.file.length);
		System.out.println("------------------------------this.fileFileName:"+this.fileFileName);
		System.out.println("------------------------------this.uploadContentType:"+this.fileContentType);
		// 异常判断
		if ((this.file == null) || (this.file.length <= 0)) {
			throw new BusinessException("未找到上传文件！");
		}
		if (!ServletFileUpload.isMultipartContent(request)){
			throw new BusinessException("上传文件类型错误！");
		}
		// 上传文件
		for (int i = 0; i < this.file.length; ++i) {
			Log.logInfo("\t上传文件: " + this.file[i] + "\n\t\t上传文件类型: "+ this.fileContentType[i]);

			String prefix = DateTimeUtils.getSystemTime("yyyyMMddHHmmssSSS");				// 图片名称
			String fileName = prefix + "." + getFileExtionName(this.fileFileName[i]);		// 图片全称（图片+后缀）
			String fileDir = DateTimeUtils.getSystemDate("yyyy/MM/dd/");					// 图片文件夹
			String uploadDir = UPLOAD_IMAGE + fileDir;										// 图片文件夹绝对地址
			String muploadDir = UPLOAD_MIMAGE + fileDir;										// 图片文件夹绝对地址
			// 压缩文件
			File toFile = new File(uploadDir, fileName);
			File mtoFile = new File(muploadDir, fileName);
			if (!(toFile.getParentFile().exists())) {
				toFile.getParentFile().mkdirs();
			}
			if (!(mtoFile.getParentFile().exists())) {
				mtoFile.getParentFile().mkdirs();
			}
			FileUtils.copyFile(this.file[i], toFile);	
			
			// 上传的网络地址
			String serverURL = this.getServerURL(UPLOAD_IMAGE_URL, fileDir, fileName);											// 文件的网络地址
			request.setAttribute("src", serverURL);
			Log.logInfo("  ---->file In server路径："+toFile   +"\n 域名访问："+serverURL);
			Log.logInfo("  ---------------------------------------------------------->生成缩略图片并上传");
			
			// 裁剪缩略图：图片的高保持400,高根据图片的宽度进行缩放
			BufferedImage image = ImageIO.read(toFile);
			float  fixedHeight = 400;	//固定的高度
			float imageHeight = image.getHeight() < fixedHeight ? fixedHeight : image.getHeight(); 
			float scale = fixedHeight/imageHeight;
			System.out.println("当前图片imageHeight:"+imageHeight+"      scale:"+scale);

			// 上传缩略图
			Thumbnails.of(toFile) 
		    .scale(scale)  			// 图片缩放比例
		    .outputQuality(1f)  	// 图片质量
		    .outputFormat(getFileExtionName(this.fileFileName[i]))  	// 转化类型
		    .toFile(muploadDir+prefix);
		}
	}
	
	
	// 上传生活照
	public void uploadPhoto() throws Exception {
		System.out.println("------------------------------this.file:"+this.file);
		System.out.println("------------------------------this.file.length:"+this.file.length);
		System.out.println("------------------------------this.fileFileName:"+this.fileFileName);
		System.out.println("------------------------------this.uploadContentType:"+this.fileContentType);
		// 异常判断
		if ((this.file == null) || (this.file.length <= 0)) {
			throw new BusinessException("未找到上传文件！");
		}
		if (!ServletFileUpload.isMultipartContent(request)){
			throw new BusinessException("上传文件类型错误！");
		}
		// 上传文件
		for (int i = 0; i < this.file.length; ++i) {
			Log.logInfo("\t上传文件: " + this.file[i] + "\n\t\t上传文件类型: "+ this.fileContentType[i]);

			String prefix = DateTimeUtils.getSystemTime("yyyyMMddHHmmssSSS");					// 图片名称
			String fileName = prefix + "." + getFileExtionName(this.fileFileName[i]);			// 图片全称（图片+后缀）
			String fileDir = DateTimeUtils.getSystemDate("yyyy/MM/dd/");						// 图片文件夹
			String muploadDir = UPLOAD_MIMAGE + fileDir;										// 图片文件夹绝对地址
			// 压缩文件
			File toFile = new File(muploadDir, fileName);
			if (!(toFile.getParentFile().exists())) {
				toFile.getParentFile().mkdirs();
			}
			
			// 裁剪缩略图：图片的宽保持400,高根据图片的宽度进行缩放
			BufferedImage image = ImageIO.read(this.file[i]);
			float  fixedWidth = 400;	//固定的高度
			float imageWidth = image.getHeight() < fixedWidth ? fixedWidth : image.getWidth(); 
			float scale = fixedWidth/imageWidth;
			System.out.println("当前图片imageWidth:"+imageWidth+"      scale:"+scale);
			
			Thumbnails.of(this.file[i]) 
		    .scale(scale)  			// 图片缩放比例
		    .outputQuality(1f)  	// 图片质量
		    .outputFormat(getFileExtionName(this.fileFileName[i]))  	// 转化类型
		    .toFile(muploadDir+prefix);
			
			String mserverURL = this.getServerURL(UPLOAD_MIMAGE_URL, fileDir, fileName);											// 文件的网络地址
			request.setAttribute("src", mserverURL);
			Log.logInfo("  ---->缩略图网络地址："+mserverURL);
		}
	}
	//*******************************************固定配置******************************************//
	// 获取主机名称
	private String getHostURL(){
		StringBuffer url = request.getRequestURL(); 
		String contextPath = request.getContextPath();
		String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(contextPath).append("/").toString();  
		return tempContextUrl;
	}
	// 获取文件后缀名
	private String getFileExtionName(String fileName) {
		int dotIndex = fileName.lastIndexOf(".");
		if (dotIndex > 0) {
			return fileName.substring(dotIndex + 1);
		}
		return "";
	}
	//  获取服务器地址
	private String getServerURL(String fileType, String fileDir, String fileName){
		String hostName =LOCOLHOST+fileType+fileDir+fileName;
		return hostName;
	}
	//*******************************************SET\GET******************************************//


	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}

	public File[] getSaveFiles() {
		return saveFiles;
	}

	public void setSaveFiles(File[] saveFiles) {
		this.saveFiles = saveFiles;
	}

	public String[] getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String[] getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String[] fileContentType) {
		this.fileContentType = fileContentType;
	}

	public File[] getSave() {
		return this.saveFiles;
	}

	public void setSave(File[] save) {
		this.saveFiles = save;
	}

	public String[] getSaveFileName() {
		return this.saveFileName;
	}

	public void setSaveFileName(String[] saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String[] getSaveContentType() {
		return this.saveContentType;
	}

	public void setSaveContentType(String[] saveContentType) {
		this.saveContentType = saveContentType;
	}
	
}
