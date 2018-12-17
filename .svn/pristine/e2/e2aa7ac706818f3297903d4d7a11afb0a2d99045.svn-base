package cn.smartcandy.common.error;

import cn.smartcandy.framework.core.data.DataTable;
import cn.smartcandy.framework.core.exception.Error;
import cn.smartcandy.framework.web.struts.BaseAction;

public class ErrorAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private Error error;
	private String q_cei_code;
	private String cei_sequence;
	private String q_cei_name;
	private String q_cbs_sequence;
	private ErrorBiz errorBiz = new ErrorBiz();

	/**
	 * 新增错误代码
	 * @return
	 */
	public String add() {
		// super.objectFieldNullToBlank(error);
		errorBiz.addError(error, super.user);
		return "add-success";
	}

	/**
	 * 删除错误代码
	 * @return
	 */
	public String delete() {
		errorBiz.deleteError(cei_sequence, super.user);
		return "delete-success";
	}

	/**
	 * 跳转到修改页面
	 * @return
	 */
	public String toModifyPage() {
		error = errorBiz.queryError(cei_sequence);
		super.objectFieldNullToBlank(error);
		super.request.setAttribute("errorBean", error);
		return "modifyPage-success";
	}

	/**
	 * 修改错误代码
	 * @return
	 */
	public String modify() {
		// super.objectFieldNullToBlank(error);
		errorBiz.modifyError(error, super.user);
		return "modify-success";
	}

	/**
	 * 查询错误代码
	 * @return
	 */
	public String query() {
		error = new Error();
		error.setCei_code(super.getParameter("q_cei_code"));
		error.setCei_name(super.getParameter("q_cei_name"));
		error.setCbs_sequence(super.getParameter("q_cbs_sequence"));
		
		DataTable dtError = errorBiz.queryErrorList(error, super.getPageNo(), this.getPageSize());
		request.setAttribute("dtError", dtError);
		return "query-success";
	}

	/**
	 * 错误代码查询页面调用
	 * @return
	 */
	public String errorQuery() {
		error = new Error();
		error.setCei_code(super.getParameter("q_cei_code"));
		error.setCei_name(super.getParameter("q_cei_name"));
		error.setCbs_sequence(super.getParameter("q_cbs_sequence"));
		super.request.setAttribute("dtError", errorBiz.queryErrorList(error, super.getPageNo(), this.getPageSize()));
		return "errorquery-success";
	}

	/**
	 * 查询明细
	 * @return
	 */
	public String queryDetail() {
		error = errorBiz.queryError(cei_sequence);
		super.objectFieldNullToBlank(error);
		super.request.setAttribute("errorBean", error);
		return "queryDetail-success";
	}

	/**
	 * @return the error
	 */
	public Error getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(Error error) {
		this.error = error;
	}

	/**
	 * @return the f_cei_code
	 */
	public String getq_cei_code() {
		return q_cei_code;
	}

	/**
	 * @param f_cei_code the f_cei_code to set
	 */
	public void setq_cei_code(String f_cei_code) {
		q_cei_code = f_cei_code;
	}

	/**
	 * @return the cei_sequence
	 */
	public String getCei_sequence() {
		return cei_sequence;
	}

	/**
	 * @param cei_sequence the cei_sequence to set
	 */
	public void setCei_sequence(String cei_sequence) {
		this.cei_sequence = cei_sequence;
	}

	/**
	 * @return the f_cei_name
	 */
	public String getq_cei_name() {
		return q_cei_name;
	}

	/**
	 * @param f_cei_name the f_cei_name to set
	 */
	public void setq_cei_name(String f_cei_name) {
		q_cei_name = f_cei_name;
	}

	/**
	 * @return the f_cbs_sequence
	 */
	public String getq_cbs_sequence() {
		return q_cbs_sequence;
	}

	/**
	 * @param f_cbs_sequence the f_cbs_sequence to set
	 */
	public void setq_cbs_sequence(String f_cbs_sequence) {
		q_cbs_sequence = f_cbs_sequence;
	}

}
