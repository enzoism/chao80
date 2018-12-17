package cn.smartcandy.common.common;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.smartcandy.application.a.account.Admin;
import cn.smartcandy.application.a.account.User;
import cn.smartcandy.framework.core.db.QueryParam;
import cn.smartcandy.framework.core.exception.UserNotLoginException;
import cn.smartcandy.framework.web.utils.CookieUtils;
import cn.smartcandy.framework.web.utils.WebUtils;

public class BaseAction extends ActionSupport{
	private static final long serialVersionUID = -7610347275628221103L;
	public static final String REQUEST_METHOD_GET = "GET";
	public static final String REQUEST_METHOD_POST = "POST";
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();
	protected HttpSession session = this.request.getSession();

	protected User user = null;
	protected Admin admin = null;

	public static final int DEFAULT_PAGE_SIZE = 20;

	public void validate() {
		this.user = ((User) this.session.getAttribute(SessionKeys.LOGIN_USER));
		this.admin = ((Admin) this.session.getAttribute(SessionKeys.LOGIN_ADMIN));
		super.validate();
	}


	protected String getHeaderReferer() {
		return this.request.getHeader("referer");
	}

	protected String getParameter(String paramName) {
		String paramValue = this.request.getParameter(paramName);
		return StringUtils.trimToNull(paramValue);
	}

	protected String getParameter(String paramName, String defaultValue) {
		String paramValue = getParameter(paramName);

		return ((paramValue == null) ? defaultValue : paramValue);
	}


	protected String[] getParameterValues(String paramName) {
		String[] paramValues = this.request.getParameterValues(paramName);

		for (int i = 0; (paramValues != null) && (i < paramValues.length); ++i) {
			paramValues[i] = StringUtils.trim(paramValues[i]);
		}

		return paramValues;
	}



	protected int getIntParameter(String paramName, int defaultValue) {
		String paramValue = getParameter(paramName);
		if (StringUtils.isNotEmpty(paramValue)) {
			return NumberUtils.toInt(paramValue);
		}
		return defaultValue;
	}


	protected Cookie getCookie(String cookieName) {
		return CookieUtils.getCookie(cookieName, this.request);
	}

	protected String getCookieValue(String cookieName) {
		return CookieUtils.getCookieValue(cookieName, this.request);
	}

	protected void addCookie(String cookieName, String value) {
		CookieUtils.addCookie(cookieName, value, this.request, this.response);
	}

	protected void addCookie(String cookieName, String value, int expiry) {
		CookieUtils.addCookie(cookieName, value, expiry, this.request, this.response);
	}

	@SuppressWarnings("rawtypes")
	protected QueryParam getQueryParam() {
		QueryParam qp = new QueryParam();

		Map paramMap = this.request.getParameterMap();
		Iterator it = paramMap.keySet().iterator();

		while (it.hasNext()) {
			String paramName = (String) it.next();
			if ((paramName.startsWith("Q_")) || (paramName.startsWith("q_"))) {
				Object paramValue = paramMap.get(paramName);

				if (paramValue != null) {
					qp.put(paramName, paramValue);
				}
			}
		}

		return qp;
	}

	public boolean isAjaxRequest() {
		return WebUtils.isAjaxRequest(this.request);
	}

	public boolean isGet() {
		return "GET".equalsIgnoreCase(this.request.getMethod());
	}

	public boolean isPost() {
		return "POST".equalsIgnoreCase(this.request.getMethod());
	}

	public int getPageSize() {
		int pageSize = getIntParameter("pageSize", getIntParameter("numPerPage", 20));

		if (pageSize <= 0) {
			pageSize = 20;
		}
		return pageSize;
	}

	public int getPageNo() {
		int pageNo = getIntParameter("currentPageNo", getIntParameter("pageNo", getIntParameter("pageNum", 1)));

		if (pageNo <= 0) {
			pageNo = 1;
		}
		return pageNo;
	}

	public String getWebDir() {
		String filePath = ServletActionContext.getServletContext().getRealPath("/");
		if (filePath == null) {
			String webPath = super.getClass().getResource("/").getPath();
			int index = webPath.indexOf("WEB-INF");
			if (index != -1) {
				filePath = webPath.substring(0, index);
			}
		}
		return filePath;
	}

	public User getUser() {
		if (this.user == null) {
			throw new UserNotLoginException("会话超时，请重新登录！");
		}
		return this.user;
	}


	public Admin getAdmin() {
		if (this.admin == null) {
			throw new UserNotLoginException("会话超时，请重新登录！");
		}
		return admin;
	}

	
}
