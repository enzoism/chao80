package cn.smartcandy.common.error;

import cn.smartcandy.framework.core.data.DataTable;
import cn.smartcandy.framework.core.db.mybatis.MyBatisDBAccess;
import cn.smartcandy.framework.core.exception.Error;

public class ErrorDao {

	public int insertError(Error error, MyBatisDBAccess dbAccess) {
		return dbAccess.insert("error.insert", error);
	}

	public int deleteError(String cei_sequence, MyBatisDBAccess dbAccess) {
		return dbAccess.delete("error.delete", cei_sequence);
	}

	public int updateError(Error error, MyBatisDBAccess dbAccess) {
		return dbAccess.update("error.update", error);
	}

	public Error selectError(String cei_sequence, MyBatisDBAccess dbAccess) {
		return dbAccess.selectOne("error.select", cei_sequence);
	}

	public DataTable selectErrorList(Error error, int pageNo, int pageSize, MyBatisDBAccess dbAccess) {
		return dbAccess.selectDataTable("error.selectList", error, pageNo, pageSize);
	}
}
