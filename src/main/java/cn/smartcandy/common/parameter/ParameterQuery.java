package cn.smartcandy.common.parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.smartcandy.framework.core.cache.CacheManager;
import cn.smartcandy.framework.core.data.DataTable;
import cn.smartcandy.framework.core.db.mybatis.MyBatisDBAccess;
import cn.smartcandy.framework.core.exception.BusinessException;
import cn.smartcandy.framework.core.log.Log;

/**
  * 项目名称：a-source  
  * 类名称：ParameterQuery  
  * 类描述：参数查询
  * 创建人：ShiLei  
  * 创建时间：2014-9-5 下午5:53:22  
  * 修改人：ShiLei  
  * 修改时间：2014-9-5 下午5:53:22  
  * 修改备注：
  * Company:Annjema & HLS (C) 2016
  * @version 1.0
 */
public class ParameterQuery {
	private static ParameterQuery parameterQuery = null;

	private ParameterQuery() {
		init();
	}

	public static ParameterQuery getInstance() {
		if (null == parameterQuery) {
			parameterQuery = new ParameterQuery();
		}
		return parameterQuery;
	}

	private void init() throws BusinessException {
		Map<String, ParameterMappingBean> mapPM = new HashMap<String, ParameterMappingBean>();
		Map<String, List<ParameterDictionaryBean>> listPD = new HashMap<String, List<ParameterDictionaryBean>>();
		Map<String, Map<String, ParameterDictionaryBean>> mapPD = new HashMap<String, Map<String, ParameterDictionaryBean>>();
		DataTable dt = this.queryParameter();
		if (null == dt || dt.getRowCount() <= 0) {
			throw new BusinessException("无参数化数据！");
		}
		String pmCode = StringUtils.EMPTY;
		for (int i = 0;  i < dt.getRowCount(); i++) {
			ParameterDictionaryBean pd = new ParameterDictionaryBean();
			pd.setPmSequence(dt.getString(i, "pmSequence"));
			pd.setPdCode(dt.getString(i, "pdCode"));
			pd.setPdName(dt.getString(i, "pdName"));
			pd.setPdSequence(dt.getString(i, "pdSequence"));
			pd.setPdIsDeletable(dt.getString(i, "pdIsDeletable"));
			pd.setPdIsRevisable(dt.getString(i, "pdIsRevisable"));
			pd.setPdOrder(dt.getString(i, "pdOrder"));
			pd.setPdFlag(dt.getString(i, "pdFlag"));
			if (!pmCode.equals(dt.getString(i, "pmCode"))) {
				pmCode = dt.getString(i, "pmCode");
				ParameterMappingBean pm = new ParameterMappingBean();
				pm.setPmCode(pmCode);
				pm.setPmName(dt.getString(i, "pmName"));
				pm.setPmSequence(dt.getString(i, "pmSequence"));
				pm.setPmDescribe(dt.getString(i, "pmDescribe"));
				pm.setPmIsDeletable(dt.getString(i, "pmIsDeletable"));
				pm.setPmIsRevisable(dt.getString(i, "pmIsRevisable"));
				pm.setPmOrder(dt.getString(i, "pmOrder"));
				pm.setPmFlag(dt.getString(i, "pmFlag"));

				mapPM.put(pmCode, pm);

				List<ParameterDictionaryBean> list = new ArrayList<ParameterDictionaryBean >();
				list.add(pd);
				listPD.put(pmCode, list);

				Map<String, ParameterDictionaryBean> param = new HashMap<String, ParameterDictionaryBean>();
				param.put(pd.getPdCode(), pd);
				mapPD.put(pmCode, param);
			} else {
				List<ParameterDictionaryBean> list = listPD.get(pmCode);
				list.add(pd);

				Map<String, ParameterDictionaryBean> param = mapPD.get(pmCode);
				param.put(pd.getPdCode(), pd);
			}
		}
		// 加入缓存机制
		CacheManager cacheManager = CacheManager.getInstance();
		cacheManager.remove("mapPM");
		cacheManager.remove("listPD");
		cacheManager.remove("mapPD");
		cacheManager.put(mapPM, "mapPM");
		cacheManager.put(listPD, "listPD");
		cacheManager.put(mapPD, "mapPD");

		Log.logDebug("加载参数缓存记录...");
	}

	/**
	 * 方法描述：查询所有的参数数据
	 *
	 * @return 参数结果集
	 * @throws BusinessException
	 */
	private DataTable queryParameter() throws BusinessException {
		MyBatisDBAccess dbAccess = null;
		try {
			dbAccess = MyBatisDBAccess.getInstance();
			dbAccess.startTransction();
			return dbAccess.selectDataTable("parameter.selectPDPMList");
		} catch (Exception e) {
			Log.logError("查询参数信息失败！", e);
			throw new BusinessException("查询参数信息失败！");
		} finally {
			dbAccess.endTransction();
		}
	}

	/**
	 * 方法描述：根据结构代码查询参数字典列表
	 * @param pmCode 结构代码
	 * @return 参数字典列表
	 * @throws BusinessException
	 */
	public List<ParameterDictionaryBean> getPDList(String pmCode) throws BusinessException {
		CacheManager cacheManager = CacheManager.getInstance();
		Object object = cacheManager.get("listPD");
		if (null == object) {
			this.init();
			object = cacheManager.get("listPD");
			if (null == object) {
				return null;
			}
		}
		@SuppressWarnings("unchecked")
		Map<String, List<ParameterDictionaryBean>> listPD = (Map<String, List<ParameterDictionaryBean>> ) object;

		return listPD.get(pmCode);
	}
	
	/**
	 * 方法描述：根据结构代码前缀查询参数字典列表
	 * 
	 * @param pmCodePrefix 结构代码前缀
	 * @return 参数字典列表
	 * @throws BusinessException
	 */
	public Map<String, List<ParameterDictionaryBean>> getPDListByPrefix(String pmCodePrefix) throws BusinessException {
		CacheManager cacheManager = CacheManager.getInstance();
		Object object = cacheManager.get("listPD");
		if (null == object) {
			this.init();
			object = cacheManager.get("listPD");
			if (null == object) {
				return null;
			}
		}
		@SuppressWarnings("unchecked")
		Map<String, List<ParameterDictionaryBean>> listPD = (Map<String, List<ParameterDictionaryBean>> ) object;
		Map<String, List<ParameterDictionaryBean>> mapPDResult = new HashMap<String, List<ParameterDictionaryBean>>() ;
		
		Iterator<String> it = listPD.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			
			if(key.startsWith(pmCodePrefix)){
				mapPDResult.put(key, listPD.get(key));
			}
		}
		
		return mapPDResult;
	}

	/**
	 * 方法描述：根据结构代码、字典代码查询参数字典
	 * @param pmCode 结构代码
	 * @param pdCode 字典代码
	 * @return 参数字典
	 * @throws BusinessException
	 */
	public ParameterDictionaryBean getPD(String pmCode, String pdCode) throws BusinessException {
		CacheManager cacheManager = CacheManager.getInstance();
		Object object = cacheManager.get("mapPD");
		if (null == object) {
			this.init();
			object = cacheManager.get("mapPD");
			if (null == object) {
				return null;
			}
		}
		@SuppressWarnings("unchecked")
		Map<String, Map<String, ParameterDictionaryBean>> mapPD = (Map<String, Map<String, ParameterDictionaryBean>> ) object;
		Map<String, ParameterDictionaryBean> param = mapPD.get(pmCode);
		if (null == param) {
			return null;
		}
		return param.get(pdCode);
	}

	/**
	 * 方法描述：清理参数记录
	 *
	 */
	public void clear() {
		CacheManager cacheManager = CacheManager.getInstance();
		cacheManager.remove("mapPM");
		cacheManager.remove("listPD");
		cacheManager.remove("mapPD");

		Log.logDebug("清除参数缓存记录...");
	}
}
