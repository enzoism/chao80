package cn.smartcandy.application.a.wxmsg.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cn.smartcandy.application.a.wxmsg.bean.TemplateData;
import cn.smartcandy.application.a.wxmsg.bean.WxTemplate;

/**
 * 项目名称：zmjema
 * 类名称：SendTemplateMsg
 * 类描述：模板数据 
 * 创建人：xujunjun
 * 创建时间：2016-12-15 下午14:30
 * 修改人：  
 * 修改时间：
 * 修改备注：
 * Company:zmjema & HLS (C) 2016
 * @version 1.0
 */
public class TemplateMsg {

	/**
	 * 方法描述：设置模板参数数据
	 * @param openId
	 * @param templateId
	 * @return
	 */
	public static WxTemplate templateMsg(String openId,String templateId,Map<String,String> temInfoMap){
		WxTemplate temp  = new WxTemplate();
		temp.setTouser(openId);
		temp.setTemplate_id(templateId);
		
        Map<String,TemplateData> param = new HashMap<String,TemplateData>();
        
        Set<Entry<String,String>> em = temInfoMap.entrySet();
        
        for(Entry<String,String> en: em){
        	TemplateData te = new TemplateData();
        	
        	te.setColor("#000000");
        	te.setValue(en.getValue());
            
            param.put(en.getKey(), te);
        }
        
        temp.setData(param);
		return temp;
	}
}
