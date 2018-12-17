package cn.smartcandy.common.parameter;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.smartcandy.framework.core.data.DataTable;
import cn.smartcandy.framework.core.db.mybatis.MyBatisDBAccess;
import cn.smartcandy.framework.core.exception.BusinessException;
import cn.smartcandy.framework.core.user.User;

/**
  * 项目名称：a-source  
  * 类名称：ParameterImp  
  * 类描述：基础信息参数表操作业务层  
  * 创建人：ShiLei  
  * 创建时间：2016-5-7 下午4:25:23  
  * 修改人：ShiLei  
  * 修改时间：2016-5-7 下午4:25:23  
  * 修改备注：
  * Company:Annjema & HLS (C) 2016
  * @version 1.0
 */
public class ParameterBiz {
	/* 数据库类实体化 */
	MyBatisDBAccess dbAccess = null;
	ParameterDao paramterDao = new ParameterDao();

	/**
	 * 方法描述：查询参数结构信息
	 *
	 * @param pmSequence 参数结构序号
	 * @return 参数结构信息
	 */
	public ParameterMappingBean queryPM(String pmSequence) {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			return paramterDao.selectPM(pmSequence, dbAccess);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询参数结构信息失败", e);
		} finally {
			dbAccess.endTransction();
		}
	}

	/**
	 * 方法描述：查询参数结构信息
	 *
	 * @param pmCode 参数结构代码
	 * @param pmName 参数结构名称
	 * @param pageNo 当前页号
	 * @param pageSize 分页长度
	 * @return 查询结果集
	 */
	public DataTable queryPMList(String pmCode, String pmName, int pageNo, int pageSize) {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			return paramterDao.selectPMList(pmCode, pmName, pageNo, pageSize, dbAccess);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询参数结构信息失败", e);
		} finally {
			dbAccess.endTransction();
		}
	}

	/**
	 * 方法描述：新增参数结构信息
	 *
	 * @param pm 参数结构bean
	 * @param user 当前操作员
	 */
	public void addPM(ParameterMappingBean pm, User user) {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			if (StringUtils.isEmpty(pm.getPmCode())) {
				String code = paramterDao.selectNextCode(dbAccess);
				pm.setPmCode(code);
			} else {
				DataTable dataTable = paramterDao.selectPMList(pm.getPmCode(), null, dbAccess);
				if (null != dataTable && dataTable.getRowCount() > 0) {
					throw new BusinessException("该结构代码已存在，请检查");
				}
			}
			String sequence = dbAccess.getSeqID("a_pm");
			pm.setPmSequence(sequence);
			paramterDao.insertPM(pm, dbAccess);

			dbAccess.commitTransction();

			// 清理参数缓存数据
			ParameterQuery.getInstance().clear();
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("新增参数结构信息失败", e);
		} finally {
			dbAccess.endTransction();
		}
	}

	/**
	 * 方法描述：修改参数结构信息
	 *
	 * @param pm 参数结构bean
	 * @param user 当前操作员
	 */
	public void modifyPM(ParameterMappingBean pm, User user) {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			ParameterMappingBean pmOld = paramterDao.selectPM(pm.getPmSequence(), dbAccess);
			if (null == pmOld) {
				throw new BusinessException("该参数结构不存在或已删除，不能进行修改操作");
			}
			if (!"1".equals(pmOld.getPmIsRevisable())) {
				throw new BusinessException("该参数结构为不可修改的类型，不能进行修改操作");
			}
			DataTable dataTable = paramterDao.selectPMList(pm.getPmCode(), null, dbAccess);
			if (null != dataTable && dataTable.getRowCount() > 0) {
				if (dataTable.getRowCount() != 1
						|| (pm.getPmCode().equals(dataTable.getString(0, "pmCode")) && !pm.getPmSequence().equals(dataTable.getString(0, "pmSequence")))) {
					throw new BusinessException("该结构代码已存在，请检查");
				}
			}
			paramterDao.updatePM(pm, dbAccess);

			dbAccess.commitTransction();

			// 清理参数缓存数据
			ParameterQuery.getInstance().clear();
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("修改参数结构信息失败", e);
		} finally {
			dbAccess.endTransction();
		}
	}

	/**
	 * 方法描述：删除参数结构信息
	 *
	 * @param pmSequence 参数结构序号
	 * @param user 当前操作员
	 */
	public void deletePM(String pmSequence, User user) {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			ParameterMappingBean pm = paramterDao.selectPM(pmSequence, dbAccess);
			if (null == pm) {
				throw new BusinessException("该参数结构不存在或已删除，不能进行删除操作");
			}
			if (!"1".equals(pm.getPmIsDeletable())) {
				throw new BusinessException("该参数结构为不可删除的类型，不能进行删除操作");
			}
			ParameterDictionaryBean[] pds = paramterDao.selectPDList(pmSequence, dbAccess);
			if (null != pds && pds.length > 0) {
				throw new BusinessException("该参数结构下存在参数字典，不能进行删除操作");
			}
			paramterDao.deletePM(pmSequence, dbAccess);

			dbAccess.commitTransction();

			// 清理参数缓存数据
			ParameterQuery.getInstance().clear();
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("删除参数结构信息失败", e);
		} finally {
			dbAccess.endTransction();
		}
	}

	/**
	 * 方法描述：查询参数字典信息
	 *
	 * @param pmSequence 参数结构序号
	 * @return 查询结果集
	 */
	public Map<String, Object> queryPDList(String pmSequence) {
		Map<String, Object> param = new HashMap<String, Object>();
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			ParameterMappingBean pm = paramterDao.selectPM(pmSequence, dbAccess);
			ParameterDictionaryBean[] pds = paramterDao.selectPDList(pmSequence, dbAccess);
			param.put("pm", pm);
			param.put("pds", pds);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("查询参数字典信息失败", e);
		} finally {
			dbAccess.endTransction();
		}
		return param;
	}

	/**
	 * 方法描述：修改参数字典信息
	 *
	 * @param pds 参数字典bean<数组> 
	 * @param user 当前操作员
	 */
	public void modifyPD(ParameterDictionaryBean[] pds, User user) {
		if (null == pds || pds.length <= 0) {
			throw new BusinessException("没有需要修改的参数字典记录");
		}
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			for (int i = 0;  i < pds.length; i++) {
				ParameterDictionaryBean[] pdsTemp = paramterDao.selectPDList(pds[i].getPmSequence(), pds[i].getPdCode(), dbAccess);
				if (null != pdsTemp && pdsTemp.length > 0) {
					if (pdsTemp.length > 1 || !pdsTemp[0].getPdSequence().equals(pds[i].getPdSequence())) {
						throw new BusinessException("同参数结构下不允许使用相同的字典代码，请检查");
					}
				}
				pds[i].setPdOrder(String.valueOf(i + 1));
				if (StringUtils.isEmpty(pds[i].getPdSequence())) {
					String sequence = dbAccess.getSeqID("a_pd");
					pds[i].setPdSequence(sequence);
					paramterDao.insertPD(pds[i], dbAccess);
				} else {
					paramterDao.updatePD(pds[i], dbAccess);
				}
			}

			dbAccess.commitTransction();

			// 清理参数缓存数据
			ParameterQuery.getInstance().clear();
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("修改参数字典信息失败", e);
		} finally {
			dbAccess.endTransction();
		}
	}

	/**
	 * 方法描述：删除参数字典信息
	 *
	 * @param pdSequence 参数字典序号
	 * @param user 当前操作员
	 */
	public void deletePD(String pdSequence, User user) {
		dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			paramterDao.deletePD(pdSequence, dbAccess);

			dbAccess.commitTransction();

			// 清理参数缓存数据
			ParameterQuery.getInstance().clear();
		} catch (BusinessException e) {
			dbAccess.rollBack();
			throw e;
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("删除参数字典信息失败", e);
		} finally {
			dbAccess.endTransction();
		}
	}
}
