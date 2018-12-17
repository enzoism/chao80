package cn.smartcandy.common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.smartcandy.framework.core.exception.BusinessException;
import cn.smartcandy.framework.core.log.Log;
import cn.smartcandy.framework.utils.CStringUtils;
import cn.smartcandy.framework.utils.JSONUtils;
import cn.smartcandy.framework.web.filter.HttpCharacterResponseWrapper;

public class JsonFilter implements Filter {
	private List<String> excludedAttrs = null;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		response.reset();
		HttpCharacterResponseWrapper cResponse = new HttpCharacterResponseWrapper(response);

		chain.doFilter(request, cResponse);

		Map mapReturnValue = new HashMap();

		Enumeration attrNames = request.getAttributeNames();

		while (attrNames.hasMoreElements()) {
			String attrName = (String) attrNames.nextElement();
			if ((!(attrName.startsWith("struts."))) && (!(this.excludedAttrs.contains(attrName)))) {
				Object attrValue = request.getAttribute(attrName);

				if (attrValue != null) {
					mapReturnValue.put(attrName, attrValue);
				}

			}

		}

		Object objException = request.getAttribute("exception");
		if (objException != null) {
			Exception exception = (Exception) objException;
			mapReturnValue.put("statusCode", "500");
			mapReturnValue.put("message", exception.getMessage());

			String errorCode = "";
			if (exception instanceof BusinessException) {
				errorCode = ((BusinessException) exception).getEiCode();
			}
			mapReturnValue.put("errorCode", errorCode);

			Object objRedirectUrl = request.getAttribute("redirectUrl");
			String redirectUrl = "";
			if (objRedirectUrl != null) {
				redirectUrl = CStringUtils.trimToEmpty((String) objRedirectUrl);
			}
			mapReturnValue.put("redirectUrl", redirectUrl);
		} else {
			mapReturnValue.put("statusCode", "200");
			mapReturnValue.put("message", "");
			mapReturnValue.put("errorCode", "");
			mapReturnValue.put("redirectUrl", "");
		}

		String json = JSONUtils.toJSONString(mapReturnValue);

		if ("true".equalsIgnoreCase(request.getParameter("isCallback"))) {
			String callback = request.getParameter("callback");
			json = String.format("%s(%s)", new Object[] { callback, json });
		}
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(json);

		String printJson = request.getParameter("printJson");
		if (CStringUtils.isNotEmpty(printJson))
			Log.logInfo(json);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void init(FilterConfig arg0) throws ServletException {
		this.excludedAttrs = new ArrayList();

		this.excludedAttrs.add("__cleanup_recursion_counter");
		this.excludedAttrs.add("org.sitemesh.webapp.contentfilter.BasicSelector.APPLIED_ONCE");
	}

	public void destroy() {
	}

}
