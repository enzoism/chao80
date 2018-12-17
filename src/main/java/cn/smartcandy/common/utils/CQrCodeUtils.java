package cn.smartcandy.common.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import cn.smartcandy.framework.core.exception.BusinessException;
import cn.smartcandy.framework.core.log.Log;
import weibo4j.http.BASE64Encoder;

/**
 * @项目名称：collection
 * @类名称：CQrCodeUtils
 * @类描述：生成二维码的工具类，底层使用com.google.zxing
 * @创建人：yaoyinghong
 * @创建时间：2017年7月6日 下午4:04:01
 * @修改人：yaoyinghong
 * @修改时间：2017年7月6日 下午4:04:01 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class CQrCodeUtils {
	
	public static int DEFAULTWIDTHANDHEIGHT=400;//指定二维码图片大小(宽和高)
	
	/**
	 * @方法描述：生成二维码图片字符串
	 *
	 * @param content 二维码内容
	 * @return String 生成二维码 "data:image/png;base64,"+codeImageStr字符串(png)
	 * @throws BusinessException
	 */
	public static String getQrcodeImgStr(String content, Integer widthandheight, String errorCorrtL){
		String format = "png";
        BitMatrix bitMatrix = null;  
        try {  
            bitMatrix = getBitMatrix(content, widthandheight, errorCorrtL);  
        } catch (Exception e) {  
            Log.logError("生成二维码图片失败", e);  
        } 
        return getBaseImgStr(bitMatrix, format);
	}
	
	/**
	 * @方法描述: 获取QrCode的 BitMatrix
	 *  
	 * @param content
	 * @param widthandheight
	 * @param errorCorrtL
	 * @return
	 * @throws WriterException  
	 * @return BitMatrix
	 */
	public static BitMatrix getBitMatrix(String content, Integer widthandheight, String errorCorrtL) throws WriterException{
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
        if(null == widthandheight){
        	widthandheight = DEFAULTWIDTHANDHEIGHT;
        }
        if(null == errorCorrtL){
        	errorCorrtL = "L";
        }
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");//指定编码类型
        hints.put(EncodeHintType.ERROR_CORRECTION, selectErrorCorrtL(errorCorrtL));//指定纠错级别
        //hints.put(EncodeHintType.MARGIN, 1);									//指定二维码边距
        return new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, widthandheight, widthandheight, hints);  
	}
	
	/**
	 * @方法描述:获取img Base64数据
	 *  
	 * @param bitMatrix
	 * @param format 图片格式 "png" "jpg"
	 * @return  
	 * @return String
	 */
	private static String getBaseImgStr(BitMatrix bitMatrix, String format){
		BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);  
        ByteArrayOutputStream os = new ByteArrayOutputStream();  
        try {
			ImageIO.write(image, format, os);
		} catch (IOException e) {
			Log.logError("图片流输出失败", e);
		}
        //利用ImageIO类提供的write方法，将bi以png图片的数据模式写入流  
        byte b[] = os.toByteArray();
        String codeImageStr = BASE64Encoder.encode(b); 
        return "data:image/png;base64," + codeImageStr;
	}
	
	/**
	 * @方法描述:选择纠错级别
	 *  
	 * @param errorCorrtL 纠错级别
	 * @return ErrorCorrectionLevel
	 */
	public static ErrorCorrectionLevel selectErrorCorrtL(String errorCorrtL){
        switch(errorCorrtL){
        case "L":/** L = ~7% correction */
        	return ErrorCorrectionLevel.L;
        case "M":/** M = ~15% correction */
        	return ErrorCorrectionLevel.M;
        case "Q":/** Q = ~25% correction */
        	return ErrorCorrectionLevel.Q;
        case "H":/** H = ~30% correction */
        	return ErrorCorrectionLevel.H;
        default:
        	return ErrorCorrectionLevel.L;
        }
	}
	
	/**
	 * 方法描述：生成二维码图片,并写入到response中
	 * @param urlCode
	 * @throws Exception
	 */
	public static void generateQRcode(String content, HttpServletResponse response) throws Exception{
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);		
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");						
		hints.put(EncodeHintType.MARGIN, 1);									//指定二维码边距
		BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, DEFAULTWIDTHANDHEIGHT, DEFAULTWIDTHANDHEIGHT, hints);
		MatrixToImageWriter.writeToStream(bitMatrix, "png", response.getOutputStream());
		//MatrixToImageWriter.toBufferedImage(bitMatrix);
	}
	
	
	
}
