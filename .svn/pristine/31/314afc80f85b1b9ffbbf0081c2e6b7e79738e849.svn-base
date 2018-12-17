package cn.smartcandy.common.error;

import cn.smartcandy.framework.core.data.DataTable;
import cn.smartcandy.framework.core.db.mybatis.MyBatisDBAccess;
import cn.smartcandy.framework.core.exception.BusinessException;
import cn.smartcandy.framework.core.exception.Error;
import cn.smartcandy.framework.core.user.User;

public class ErrorBiz {
	private ErrorDao errorDao = new ErrorDao();

	/**
	 * 新增错误代码
	 * @param error 错误代码
	 * @param user 操作员
	 */
	public void addError(Error error, User user) {
		MyBatisDBAccess dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			String ceiSequence = dbAccess.getSeqID("C_EI");
			String ceiCode = error.getCei_code();
			error.setCei_sequence(ceiSequence);
			error.setCei_code(ceiCode + ceiSequence);
			// if ("SYS".equals(ceiCode)) {
			// error.setCei_code(ceiCode + ceiSequence.substring(1));
			// }
			error.setCei_flag("0");// 默认为0，可用
			errorDao.insertError(error, dbAccess);
			dbAccess.commitTransction();
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("save fail!", e);
		} finally {
			dbAccess.endTransction();
		}
	}

	/**
	 * 删错错误代码
	 * @param cei_sequence 错误代码序号
	 * @param user 操作员
	 */
	public void deleteError(String cei_sequence, User user) {
		MyBatisDBAccess dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			errorDao.deleteError(cei_sequence, dbAccess);
			dbAccess.commitTransction();
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("delete fail!", e);
		} finally {
			dbAccess.endTransction();
		}
	}

	/**
	 * 修改错误代码
	 * @param error
	 * @param user 操作员
	 */
	public void modifyError(Error error, User user) {
		MyBatisDBAccess dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			errorDao.updateError(error, dbAccess);
			dbAccess.commitTransction();
		} catch (Exception e) {
			dbAccess.rollBack();
			throw new BusinessException("save fail!", e);
		} finally {
			dbAccess.endTransction();
		}
	}

	/**
	 * 根据code name type 条件查询错误代码
	 * @param error 查询条件
	 * @param pageNo 当前页号
	 * @param pageSize 分页长度
	 * @return DataTable 查询结果集
	 */
	public DataTable queryErrorList(Error error, int pageNo, int pageSize) {
		MyBatisDBAccess dbAccess = MyBatisDBAccess.getInstance();
		DataTable dtError = null;
		try {
			dbAccess.startTransction();
			dtError = errorDao.selectErrorList(error, pageNo, pageSize, dbAccess);
		} catch (Exception e) {
			throw new BusinessException("query fail!", e);
		} finally {
			dbAccess.endTransction();
		}
		return dtError;
	}

	/**
	 * 查询错误代码明细
	 * @param cei_sequence 错误代码序号
	 * @return Error 错误代码
	 */
	public Error queryError(String cei_sequence) {
		MyBatisDBAccess dbAccess = MyBatisDBAccess.getInstance();
		try {
			dbAccess.startTransction();
			return errorDao.selectError(cei_sequence, dbAccess);
		} catch (Exception e) {
			throw new BusinessException("query fail!", e);
		} finally {
			dbAccess.endTransction();
		}
	}
}
