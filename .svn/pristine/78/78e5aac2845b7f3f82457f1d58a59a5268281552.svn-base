package cn.smartcandy.common.parameter;

import java.util.Map;

import cn.smartcandy.framework.web.struts.BaseAction;
import cn.smartcandy.framework.core.data.DataTable;

/**
  * 项目名称：a-source  
  * 类名称：ParameterAction  
  * 类描述：基础信息参数表操作Action层  
  * 创建人：ShiLei  
  * 创建时间：2016-5-7 下午4:24:24  
  * 修改人：ShiLei 
  * 修改时间：2016-5-7 下午4:24:24  
  * 修改备注：
  * Company:Annjema & HLS (C) 2016
  * @version 1.0
 */
public class ParameterAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	ParameterBiz parameterBiz = new ParameterBiz();
	ParameterMappingBean pm = null;
	ParameterDictionaryBean[] pds = null;

	/**
	 * 方法描述：查询参数结构信息
	 *
	 * @return String 查询成功标识
	 */
	public String queryPM() {
		String pmSequence = super.getParameter("pmSequence");
		pm = parameterBiz.queryPM(pmSequence);
		super.objectFieldNullToBlank(pm);
		this.request.setAttribute("pm", pm);

		return "queryPM-success";
	}

	/**
	 * 方法描述：根据条件查询参数结构信息
	 *
	 * @return String 查询成功标识
	 */
	public String queryPMList() {
		String q_pmCode = super.getParameter("q_pmCode");
		String q_pmName = super.getParameter("q_pmName");
		DataTable dataTable = parameterBiz.queryPMList(q_pmCode, q_pmName, super.getPageNo(), super.getPageSize());
		this.request.setAttribute("dataTable", dataTable);

		return "queryPMList-success";
	}

	/**
	 * 方法描述：添加参数结构信息
	 *
	 * @return String 添加成功标识
	 */
	public String addPM() {
		parameterBiz.addPM(pm, super.user);

		return "addPM-success";
	}

	/**
	 * 方法描述：修改参数结构信息
	 *
	 * @return String 修改成功标识
	 */
	public String modifyPM() {
		parameterBiz.modifyPM(pm, super.user);

		return "modifyPM-success";
	}

	/**
	 * 方法描述：删除参数结构信息
	 *
	 * @return String 删除成功标识
	 */
	public String deletePM() {
		String pmSequence = request.getParameter("pmSequence");
		parameterBiz.deletePM(pmSequence, super.user);

		return "deletePM-success";
	}

	/**
	 * 方法描述：根据参数结构序号查询参数字典信息
	 *
	 * @return String 查询成功标识
	 */
	public String queryPDList() {
		String pmSequence = super.getParameter("pmSequence");
		Map<String, Object> param = parameterBiz.queryPDList(pmSequence);
		pm = (ParameterMappingBean) param.get("pm");
		pds = (ParameterDictionaryBean[]) param.get("pds");
		super.objectFieldNullToBlank(pm);
		super.objectFieldNullToBlank(pm);
		this.request.setAttribute("pm", pm);
		this.request.setAttribute("pds", pds);

		return "queryPDList-success";
	}

	/**
	 * 方法描述：修改参数字典信息
	 *
	 * @return String 修改成功标识
	 */
	public String modifyPD() {
		parameterBiz.modifyPD(pds, super.user);
		request.setAttribute("topage", "pdList");

		return "modifyPD-success";
	}

	/**
	 * 方法描述：删除参数字典信息
	 *
	 * @return String 删除成功标识
	 */
	public String deletePD() {
		String pdSequence = request.getParameter("pdSequence");
		parameterBiz.deletePD(pdSequence, super.user);

		return "deletePD-success";
	}

	/**
	 * @return the pm
	 */
	public ParameterMappingBean getPm() {
		return pm;
	}

	/**
	 * @param pm the pm to set
	 */
	public void setPm(ParameterMappingBean pm) {
		this.pm = pm;
	}

	/**
	 * @return the pds
	 */
	public ParameterDictionaryBean[] getPds() {
		return pds;
	}

	/**
	 * @param pds the pds to set
	 */
	public void setPds(ParameterDictionaryBean[] pds) {
		this.pds = pds;
	}
}
