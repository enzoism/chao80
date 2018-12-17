package cn.smartcandy.common.parameter;

import java.util.HashMap;
import java.util.Map;

import cn.smartcandy.framework.core.db.mybatis.MyBatisDBAccess;
import cn.smartcandy.framework.core.data.DataTable;

/**
  * 项目名称：a-source  
  * 类名称：ParameterDao
  * 类描述：基础信息参数表操作数据库操作层  
  * 创建人：ShiLei  
  * 创建时间：2016-5-7 下午4:25:31  
  * 修改人：ShiLei  
  * 修改时间：2016-5-7 下午4:25:31  
  * 修改备注：
  * Company:Annjema & HLS (C) 2016
  * @version 1.0
 */
public class ParameterDao {

	/**
	 * 方法描述：查询参数结构信息
	 *
	 * @param pmSequence 参数结构序号
	 * @param dbAccess 数据库连接实例
	 * @return 参数结构信息
	 */
	public ParameterMappingBean selectPM(String pmSequence, MyBatisDBAccess dbAccess) {
		return dbAccess.selectOne("parameter.selectPM", pmSequence);
	}

	/**
	 * 方法描述：查询参数结构信息
	 *
	 * @param pmCode 参数结构代码
	 * @param pmName 参数结构名称
	 * @param pageNo 当前页号
	 * @param pageSize 分页长度
	 * @param dbAccess 数据库连接实例
	 * @return 查询结果集
	 */
	public DataTable selectPMList(String pmCode, String pmName, int pageNo, int pageSize, MyBatisDBAccess dbAccess) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("pmCode", pmCode);
		param.put("pmName", pmName);

		return dbAccess.selectDataTable("parameter.selectPMList", param, pageNo, pageSize);
	}

	/**
	 * 方法描述：查询参数结构信息
	 *
	 * @param pmCode 参数结构代码
	 * @param pmName 参数结构名称
	 * @param dbAccess 数据库连接实例
	 * @return 查询结果集
	 */
	public DataTable selectPMList(String pmCode, String pmName, MyBatisDBAccess dbAccess) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("pmCode", pmCode);
		param.put("pmName", pmName);

		return dbAccess.selectDataTable("parameter.selectPMList", param);
	}

	/**
	 * 方法描述：查询下一个结构代码
	 *
	 * @param dbAccess 数据库连接实例
	 * @return 结构代码
	 */
	public String selectNextCode(MyBatisDBAccess dbAccess) {
		int nextVal = dbAccess.selectOne("parameter.selectNextCode");
		String nextCode="00000" + nextVal;

		return nextCode.substring(nextCode.length() - 6);
	}

	/**
	 * 方法描述：新增参数结构信息
	 *
	 * @param pm 参数结构bean
	 * @param dbAccess 数据库连接实例
	 */
	public void insertPM(ParameterMappingBean pm, MyBatisDBAccess dbAccess) {
		dbAccess.insert("parameter.insertPM", pm);
	}

	/**
	 * 方法描述：修改参数结构信息
	 *
	 * @param pm 参数结构bean
	 * @param dbAccess 数据库连接实例
	 */
	public void updatePM(ParameterMappingBean pm, MyBatisDBAccess dbAccess) {
		dbAccess.update("parameter.updatePM", pm);
	}

	/**
	 * 方法描述：删除参数结构信息
	 *
	 * @param pmSequence 参数结构序号
	 * @param dbAccess 数据库连接实例
	 */
	public void deletePM(String pmSequence, MyBatisDBAccess dbAccess) {
		dbAccess.delete("parameter.deletePM", pmSequence);
	}

	/**
	 * 方法描述：查询参数字典信息
	 *
	 * @param pmSequence 参数结构序号
	 * @param dbAccess 数据库连接实例
	 * @return 查询结果集
	 */
	public ParameterDictionaryBean[] selectPDList(String pmSequence, MyBatisDBAccess dbAccess) {
		return this.selectPDList(pmSequence, null, dbAccess);
	}

	/**
	 * 方法描述：查询参数字典信息
	 *
	 * @param pmSequence 参数结构序号
	 * @param pdCode 参数字典代码
	 * @param dbAccess 数据库连接实例
	 * @return 查询结果集
	 */
	public ParameterDictionaryBean[] selectPDList(String pmSequence, String pdCode, MyBatisDBAccess dbAccess) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("pmSequence", pmSequence);
		param.put("pdCode", pdCode);

		return dbAccess.selectArray("parameter.selectPDList", param, ParameterDictionaryBean.class);
	}

	/**
	 * 方法描述：新增参数字典信息
	 *
	 * @param pd 参数字典bean
	 * @param dbAccess 数据库连接实例
	 */
	public void insertPD(ParameterDictionaryBean pd, MyBatisDBAccess dbAccess) {
		dbAccess.insert("parameter.insertPD", pd);
	}

	/**
	 * 方法描述：更新参数字典信息
	 *
	 * @param pd 参数字典bean
	 * @param dbAccess 数据库连接实例
	 */
	public void updatePD(ParameterDictionaryBean pd, MyBatisDBAccess dbAccess) {
		dbAccess.update("parameter.updatePD", pd);
	}

	/**
	 * 方法描述：删除参数字典信息
	 *
	 * @param pdSequence 参数字典序号
	 * @param dbAccess 数据库连接实例
	 */
	public void deletePD(String pdSequence, MyBatisDBAccess dbAccess) {
		dbAccess.delete("parameter.deletePD", pdSequence);
	}

}
