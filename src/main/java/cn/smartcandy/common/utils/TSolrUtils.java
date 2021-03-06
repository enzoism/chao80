package cn.smartcandy.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;

import cn.smartcandy.application.a.collection.Collection;
import cn.smartcandy.framework.core.exception.BusinessException;

/**
 * @项目名称：zmjema
 * @类名称：TSolrUtils
 * @类描述：solr查询工具类
 * @创建人：tangzhifeng
 * @创建时间：2017年11月12日 下午9:45:25
 * @修改人：someOne
 * @修改时间：2017年11月12日 下午9:45:25 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class TSolrUtils {
	
	//private final static String SOLR_URL = "http://localhost:9080/solr/chao80/";
	private final static String SOLR_URL = "http://www.chao80.com/solr/chao80/";
	// 创建SolrServer服务
	public static HttpSolrClient createSolrServer(){
		HttpSolrClient solr = null;
		solr = new HttpSolrClient(SOLR_URL);
		return solr;
	}
	
	/**
	 * 方法描述：搜索藏品(直接返回结果)
	 * @param searchKey
	 * @param pageNo
	 * @param pageSize
	 * @throws Exception
	 * @return Map<String,Object>
	 * @throws BusinessException
	 */
//	public static Map<String, Object> searchCollectionList(String searchKey, int pageNo,int pageSize) throws Exception{
//		SolrQuery query = new SolrQuery();
//		HttpSolrClient solrServer = TSolrUtils.createSolrServer("chao80/");
//		// 搜索字段
//		// 搜索字段
//		String search = "colTitle:"+searchKey
//						+"OR colSubTitle:"+searchKey
//						+"OR colContent:"+searchKey
//						+"OR colStatus:"+searchKey
//						+"OR colPrice:"+searchKey
//						+"OR colThumb:"+searchKey
//						+"OR userName:"+searchKey
//						+"OR searchTag:"+searchKey;
//		query.setQuery(search);
//		// 过滤条件
//        query.addFilterQuery("colStatus:N");  
//		// 搜索排序
////		query.setSort("colSequence", SolrQuery.ORDER.desc);
//        // 高亮显示
////        query.setHighlight(true);  
////        query.addHighlightField("colTitle");  
////        query.addHighlightField("colSubTitle");  
////        query.addHighlightField("colContent");  
////        query.addHighlightField("colStatus");  
////        query.addHighlightField("colPrice");  
////        query.addHighlightField("colThumb");  
////        query.addHighlightField("searchTag");  
////
////        query.setHighlightSimplePre("<span>");  
////        query.setHighlightSimplePost("</span>"); 
//		// 设置分页
//		int currentPage = (pageNo == 0) ? pageNo : pageNo * pageSize;
//		query.setStart(currentPage);
//		query.setRows(pageSize);
//		// 遍历结果
//		QueryResponse response = solrServer.query(query);
//		SolrDocumentList solrDocumentList = response.getResults();
//		long resultSize = solrDocumentList.getNumFound();
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		// 实体对象
//        List<Collection> tmpLists = response.getBeans(Collection.class);
//        if(tmpLists!=null && tmpLists.size()>0){
//            for(Collection collection:tmpLists){
//                System.out.println(collection);
//            }
//        }
//        // 分页数据
//        Map<String, String> pageInfo = TSolrUtils.getPageInfo(pageNo, pageSize, resultSize);
//        
//        resultMap.put("list", tmpLists);
//        resultMap.put("pageInfo", pageInfo);
//        return resultMap;
//	}
	
	
	public static QueryResponse searchCollectionResponse(String searchKey, int pageNo,int pageSize) throws Exception{
		SolrQuery query = new SolrQuery();
		HttpSolrClient solrServer = TSolrUtils.createSolrServer();
		// 搜索字段
		ModifiableSolrParams params = new ModifiableSolrParams(); 
		String search = "colTitle:"+searchKey
				+" OR colSubTitle:"+searchKey
				+" OR colContent:"+searchKey
				+" OR colStatus:"+searchKey
				+" OR colPrice:"+searchKey
				+" OR colThumb:"+searchKey
				+" OR userName:"+searchKey
				+" OR searchTag:"+searchKey
				;
		System.out.println(search);
		query.setQuery(search);
		// 过滤条件
        query.addFilterQuery("colStatus:N");  
		// 设置分页
		int current = pageNo-1;
		int currentPage = (current==0) ? current : current * pageSize;
		query.setStart(currentPage);
		query.setRows(pageSize);
		params.add(query);
		QueryResponse response = solrServer.query(params);
		// 遍历结果
        return response;
	}
	
	/**
	 * 方法描述：转化成对象列表
	 * @param response
	 * @throws Exception
	 * @return List<Collection>
	 * @throws BusinessException
	 */
	public static List<Collection> turnResponseToColList(QueryResponse response) throws Exception{
      List<Collection> tmpLists = response.getBeans(Collection.class);
      if(tmpLists!=null && tmpLists.size()>0){
          System.out.println("通过文档集合获取查询的结果"); 
          for(Collection collection:tmpLists){
              System.out.println(collection);
          }
      }
        return tmpLists;
	}
	/**
	 * 方法描述：处理分页
	 * @param pageNo
	 * @param pageSizz
	 * @param resultSize
	 * @return Map<String,String>
	 * @throws BusinessException
	 */
	public static Map<String, String> getPageInfo(int pageNo,int pageSizz,long resultSize){
		Map<String, String> pageInfo = new HashMap<String, String>();
    	String pageCount = String.valueOf(0);
    	String hasMorePage = String.valueOf(Boolean.FALSE);
    	String totalCount = String.valueOf(0);
    	String pageSize = String.valueOf(0);
    	String currentPageNo = String.valueOf(0);
    	if (resultSize>0) {
    		long count = resultSize/pageSizz;
		    pageCount = String.valueOf((resultSize%pageSizz==0)?count:count+1);
		    int pageNoTemp = (pageNo==0)?1:pageNo;
		    hasMorePage = (pageNoTemp*pageSizz < resultSize)?String.valueOf(Boolean.TRUE):String.valueOf(Boolean.FALSE);
		    totalCount = String.valueOf(resultSize);
		    pageSize = String.valueOf(pageSizz);
		    currentPageNo = String.valueOf(pageNo);
		}
    	pageInfo.put("pageCount", pageCount);
    	pageInfo.put("hasMorePage", hasMorePage);
    	pageInfo.put("totalCount", totalCount);
    	pageInfo.put("pageSize", pageSize);
    	pageInfo.put("currentPageNo", currentPageNo);
		return pageInfo;
	}
	/**
	 * 方法描述：上传数据到Solr
	 * @param collection
	 * @throws Exception
	 * @return void
	 * @throws BusinessException
	 */
	public static void addCollection(Collection collection) throws Exception{
		SolrInputDocument document = new SolrInputDocument();
		document.addField("colSequence", collection.getColSequence());
		document.addField("colTitle", collection.getColTitle());
		document.addField("colSubTitle", collection.getColSubTitle());
		document.addField("colContent", collection.getColContent());
		document.addField("colStatus", collection.getColStatus());
		document.addField("colPrice", collection.getColPrice());
		document.addField("colThumb", collection.getColThumb());
		document.addField("userName", collection.getUserName());
		document.addField("searchTag", collection.getSearchTag());
		document.addField("imgWidth", collection.getImgWidth());
		document.addField("imgHeigh", collection.getImgHeigh());

		HttpSolrClient solr = new HttpSolrClient(SOLR_URL + "");
		solr.add(document);
		solr.commit();
		solr.close();
	}
	
}
