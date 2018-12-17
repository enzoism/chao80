package cn.smartcandy.common.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.opensymphony.xwork2.ActionContext;

import cn.smartcandy.framework.core.data.Column;
import cn.smartcandy.framework.core.data.DataTable;
import cn.smartcandy.framework.core.data.Meta;
import cn.smartcandy.framework.core.data.PagedDataTable;
import cn.smartcandy.framework.core.data.PagingInfo;
import cn.smartcandy.framework.core.exception.BusinessException;
import cn.smartcandy.framework.utils.CStringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TStringUtils {

	public TStringUtils() {
		super();
	}

	
	/**
	 * 方法描述：List字符数组去重
	 * @param list
	 * @return List<String>
	 * @throws BusinessException
	 */
	public static List<String> getNoRepeatStringList(List<String> list){
		List<String> result = new ArrayList<String>();
		for (int i = 0;null!=list && i < list.size(); i++) {
			if(!result.contains(list.get(i))){
				result.add(list.get(i));
            }
		}
		return result;
	}
	/**
	 * 方法描述：List字符转字符数组
	 * @param string
	 * @return
	 * @return String[]
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	public static String[] turnStringToStringArr(String string) {
		String[] array = null;
	    ArrayList<String> list = null;
	    if (!CStringUtils.isEmpty(string)) {
	        JSONArray json = JSONArray.fromObject(string);//把String转换为json 
	        list = (ArrayList<String>) JSONArray.toCollection(json,    
	       		 String.class);  
	        array  = list.toArray(new String[list.size()]);
	    }
	    return array;
	}
	
	

	/**
	 * 方法描述：List字符转字符集合
	 * @param string
	 * @return
	 * @return String[]
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	public static List<String>turnStringToStringList(String string) {
	    List<String> list = null;
	    if (!CStringUtils.isEmpty(string)) {
	        JSONArray json = JSONArray.fromObject(string);//把String转换为json 
	        list = (ArrayList<String>) JSONArray.toCollection(json,    
	       		 String.class);  
	    }
	    return list;
	} 
	/**
	 * 方法描述：jsonString转Map
	 * @param string
	 * @return Map<String,Object>
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> turnStringToObjectMap(String string) {
		Map<String, Object> map = null;
		try {
			map = new ObjectMapper().readValue(string, Map.class);
		} catch (JsonParseException e) {
			throw new BusinessException("Json类型转换异常！");
		} catch (JsonMappingException e) {
			throw new BusinessException("Json类型转换异常！");
		} catch (IOException e) {
			throw new BusinessException("Json类型转换异常！");
		}
		return map;
	}
	
	/**
	 * 方法描述：jsonString转Map
	 * @param string
	 * @return Map<String,String>
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> turnStringToStringMap(String string) {
		Map<String, String> map = null;
		try {
			map = new ObjectMapper().readValue(string, Map.class);
		} catch (JsonParseException e) {
			throw new BusinessException("Json类型转换异常！");
		} catch (JsonMappingException e) {
			throw new BusinessException("Json类型转换异常！");
		} catch (IOException e) {
			throw new BusinessException("Json类型转换异常！");
		}
		return map;
	}
	
	/**
	 * 方法描述：List字符转Map对象数组
	 * @param string
	 * @return
	 * @return ArrayList<Map<String,Object>>
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String,Object>>turnStringToObjectMapList(String string) {
		  ArrayList<Map<String,Object>> list = null;
		    if (!CStringUtils.isEmpty(string)) {
		        JSONArray json = JSONArray.fromObject(string);//把String转换为json 
		        list = (ArrayList<Map<String,Object>>) JSONArray.toCollection(json,    
		       		 Map.class); 
		    }
		    return list;
	}
	/**
	 * 方法描述：List字符转Map字符数组
	 * @param string
	 * @return
	 * @return ArrayList<Map<String,Object>>
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String,String>>turnStringToStringMapList(String string) {
		  ArrayList<Map<String,String>> list = null;
		    if (!CStringUtils.isEmpty(string)) {
		        JSONArray json = JSONArray.fromObject(string);//把String转换为json 
		        list = (ArrayList<Map<String,String>>) JSONArray.toCollection(json,    
		       		 Map.class); 
		    }
		    return list;
	}
	
	/**
	 * 方法描述：List字符转Class字符数组(因为要支持泛型，要new 工具类对象)
	 * @param string
	 * @return
	 * @return ArrayList<T>
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> turnStringToClassList(String string, Class<T> clazz) {
		ArrayList<T> list = null;
		if (!CStringUtils.isEmpty(string)) {
		    list = (ArrayList<T>) JSONArray.toCollection(JSONArray.fromObject(string),clazz); 
		}
		return list;
	}
	

    
    /**
     * 方法描述：DataTable转化成Map<String,Object>结构
     * @param dataTable
     * @return
     * @return Map<String,Object>
     * @throws BusinessException
     */
    public static Map<String, Object> turnDataTableToObjectMap(DataTable dataTable){
    	Map<String, Object> map = new HashMap<>();
    	try {
    		for (int i = 0;null!=dataTable && null!=dataTable.getMeta() &&  i < dataTable.getColumnCount(); i++) {
    			Meta meta = dataTable.getMeta();
    			Column column2 = meta.get(i);
    			String key = column2.getNameOrigin();
        		String value = dataTable.getString(0, i);
    			map.put(key, value);
        	}
    	} catch (Exception e) {
			throw new BusinessException("参数不合法");
		}
	    return map;
    }
    


	
    /**
     * 方法描述：DataTable转化成Map<String,Object>结构
     * @param dataTable
     * @return
     * @return Map<String,Object>
     * @throws BusinessException
     */
    public static List<Map<String, Object>> turnDataTableToObjectMapList(DataTable dataTable){
    	List<Map<String, Object>> mapList = new ArrayList<>();
    	Map<String, Object> map = new HashMap<>();
    	try {
    		for (int i = 0; null!=dataTable && i < dataTable.getRowCount(); i++) {
    			map = new HashMap<>();
        		for (int j = 0;null!=dataTable && null!=dataTable.getMeta() &&  j < dataTable.getColumnCount(); j++) {
        			Meta meta = dataTable.getMeta();
        			Column column2 = meta.get(j);
        			String key = column2.getNameOrigin();
            		String value = dataTable.getString(i, j);
        			map.put(key, value);
            	}
        		mapList.add(map);
			}
    	} catch (Exception e) {
			throw new BusinessException("参数不合法");
		}
	    return mapList;
    }
    /**
     * 方法描述：DataTable转化成Map<String,Object>结构
     * @param dataTable
     * @return
     * @return Map<String,Object>
     * @throws BusinessException
     */
    public static List<Map<String, String>> turnDataTableToStringMapList(DataTable dataTable){
       	List<Map<String, String>> mapList = new ArrayList<>();
    	Map<String, String> map = new HashMap<>();
    	try {
    		for (int i = 0;null!=dataTable && i < dataTable.getRowCount(); i++) {
    			map = new HashMap<>();
        		for (int j = 0;null!=dataTable && null!=dataTable.getMeta() &&  j < dataTable.getColumnCount(); j++) {
        			Meta meta = dataTable.getMeta();
        			Column column2 = meta.get(j);
        			String key = column2.getNameOrigin();
            		String value = dataTable.getString(i, j);
        			map.put(key, value);
            	}
        		mapList.add(map);
			}
    	} catch (Exception e) {
			throw new BusinessException("参数不合法");
		}
	    return mapList;
    }
    
    public static Map<String, String> turnDataTableToPageInfoMap(DataTable dataTable){
    	Map<String, String> map = new HashMap<String, String>();
    	String pageCount = String.valueOf(0);
    	String hasMorePage = String.valueOf(Boolean.FALSE);
    	String totalCount = String.valueOf(0);
    	String pageSize = String.valueOf(0);
    	String currentPageNo = String.valueOf(0);
    	if (null!=dataTable) {
    		PagedDataTable pagedDataTable = (PagedDataTable) dataTable;
    		PagingInfo pagingInfo = pagedDataTable.getPagingInfo();
		    pageCount = String.valueOf(pagingInfo.getPageCount());
		    hasMorePage = String.valueOf(pagingInfo.getHasMorePage());
		    totalCount = String.valueOf(pagingInfo.getTotalCount());
		    pageSize = String.valueOf(pagingInfo.getPageSize());
		    currentPageNo = String.valueOf(pagingInfo.getCurrentPageNo());
		}
    	map.put("pageCount", pageCount);
    	map.put("hasMorePage", hasMorePage);
    	map.put("totalCount", totalCount);
    	map.put("pageSize", pageSize);
    	map.put("currentPageNo", currentPageNo);
    	
    	return map;
    }

	
    /**
     * 方法描述：将List转化成分页Map
     * @param list
     * @return Map<String,String>
     * @throws BusinessException
     */
    public static <T> Map<String, String> turnListToPageInfoMap(List<T> list){
    	Map<String, String> map = new HashMap<String, String>();
    	String pageCount = String.valueOf(0);
    	String hasMorePage = String.valueOf(Boolean.FALSE);
    	String totalCount = String.valueOf(0);
    	String pageSize = String.valueOf(0);
    	String currentPageNo = String.valueOf(0);
    	if (!CollectionUtils.isEmpty(list)) {
		    pageCount = String.valueOf(1);
		    hasMorePage = String.valueOf(Boolean.FALSE);
		    totalCount = String.valueOf(list.size());
		    pageSize = String.valueOf(list.size());
		    currentPageNo = String.valueOf(1);
		}
    	map.put("pageCount", pageCount);
    	map.put("hasMorePage", hasMorePage);
    	map.put("totalCount", totalCount);
    	map.put("pageSize", pageSize);
    	map.put("currentPageNo", currentPageNo);
    	
    	return map;
    }
    /**
	 * 方法描述：
	 * @param map
	 * @return String
	 * @throws BusinessException
	 */
	public static String turnMapToJsonString(Map<?, ?> map) {
		JSONObject json = JSONObject.fromObject(map);
		String jsonString = json.toString();
		return jsonString;
	}
	// 将Map中的key放在集合中
	public static  List<String> turnStringMapListToList(List<Map<String, String>> mapList,String key) {
		List<String> list = new ArrayList<>();
		for (int i = 0;null!=mapList && i<mapList.size(); i++) {
			Map<String, String> map = mapList.get(i);
			String value = map.get(key);
			list.add(value);
		}
		return list;
	}
	// 将Map中的key放在集合中
	public static  List<String> turnObjectMapListToList(List<Map<String, Object>> mapList,String key) {
		List<String> list = new ArrayList<>();
		for (int i = 0;null!=mapList && i<mapList.size(); i++) {
			Map<String, Object> map = mapList.get(i);
			String value = (String) map.get(key);
			list.add(value);
		}
		return list;
	}
    /**
     * 方法描述：从request中直接取出对象,前台不要传递数组,将多有的参数转成String
     * @return
     * @return Map<String,String>
     * @throws BusinessException
     */
    public static Map<String, String> getStringMapFromRequest(){
        Map<String, Object> map =  ActionContext.getContext().getParameters();
        Map<String, String> stringMap = new HashMap<String, String>();
        String value = null;
        Set<String> set = map.keySet();
        for (String key : set) {
            Object[] values = (Object[]) map.get(key);
            if (values[0] instanceof String) {
				value = (String) values[0];
				value = CStringUtils.isEmpty(value)?null:value.trim();
                stringMap.put(key, value);
			}
        }
    	return stringMap;
     } 
    
    /**
     * 方法描述：从request中直接取出对象,前台不要传递数组,将多有的参数转成String
     * @return Map<String,Object>
     * @throws BusinessException
     */
    public static Map<String, Object> getObjectMapFromRequest(){
        Map<String, Object> map =  ActionContext.getContext().getParameters();
        String value = null;
        Set<String> set = map.keySet();
        for (String key : set) {
            Object[] values = (Object[]) map.get(key);
            if (values[0] instanceof String) {
				value = (String) values[0];
				value = CStringUtils.isEmpty(value)?null:value.trim();
				map.put(key, value);
			}else{
				map.put(key, values[0]);
			}
        }
    	return map;
     }


	/**
	 * 方法描述：获取property的参数(在开发环境乱码)
	 * @param key
	 * @param filePath
	 * @return
	 * @return String
	 * @throws BusinessException
	 */
	public static String loadProperty(String key,String filePath) {
		Properties pro = new Properties(); 
		InputStreamReader instream;
		try {
			instream = new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(filePath), "UTF-8");
			pro.load(instream);
		} catch (UnsupportedEncodingException e) {
			throw new BusinessException("配置文件读取失败");
		} catch (IOException e) {
			throw new BusinessException("配置文件读取失败");
		}catch (Exception e) {
			throw new BusinessException("配置文件读取失败");
		}
		String value = pro.getProperty(key);
		return value;
	}
    /**
     * 方法描述：放置默认值
     * @param pramMap
     * @param key
     * @param defaultValue
     * @return
     * @return String
     * @throws BusinessException
     */
    public static String getDefaultValueMap(Map<String, Object> pramMap,String key,String defaultValue){
		String value = (String) pramMap.get(key);
    	try {
			value = CStringUtils.isEmpty(value) ? defaultValue : value;
    	} catch (BusinessException e) {
			throw new BusinessException("参数不合法");
		}
		return  value;
    }
	//打印List
	public static <T> void printList(List<T> list) {
		for (int i = 0;null!=list && i < list.size(); i++) {
			T t = list.get(i);
			System.out.println(t);
		}
		System.out.println();
	}
	
	//打印Map
	public static void printStringMap(Map<String, String> map) {
		/*for (String key : map.keySet()) {
		   System.out.println("Key= "+ key + " Value= " + map.get(key));
		}
		System.out.println();*/
	}
	
	
	//打印Map
	public static void printObjectMap(Map<String, Object> map) {
		/*for (String key : map.keySet()) {
		   System.out.println("Key= "+ key + " Value= " + map.get(key));
		}
		System.out.println();*/
		
	}
    
}
