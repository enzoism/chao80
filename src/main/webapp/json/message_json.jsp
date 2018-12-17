<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.StringUtils"%>
<%@ page import="cn.smartcandy.framework.core.log.Log"%>

<%
	// ok:200, error:300, timeout:301
	String statusCode = (String) request.getAttribute("statusCode");
	if (StringUtils.isEmpty(statusCode)) {
		statusCode = "500";
	}
	String message = (String) request.getAttribute("message");
	String callbackType = request.getParameter("callbackType");
	if (StringUtils.isEmpty(callbackType)) {
		callbackType = (String) request.getAttribute("callbackType");
	}
	String navTabId = request.getParameter("navTabId");
	if (StringUtils.isEmpty(navTabId)) {
		navTabId = (String) request.getAttribute("navTabId");
	}
	String forwardUrl = request.getParameter("forwardUrl");
	if (StringUtils.isEmpty(forwardUrl)) {
		forwardUrl = (String) request.getAttribute("forwardUrl");
	}
	Log.logDebug("{\"statusCode\":\"" + statusCode + "\", \"message\":\"" + StringUtils.trimToEmpty(message) + "\", \"callbackType\":\""
			+ StringUtils.trimToEmpty(callbackType) + "\", \"navTabId\":\"" + StringUtils.trimToEmpty(navTabId) + "\", \"forwardUrl\":\""
			+ StringUtils.trimToEmpty(forwardUrl) + "\"}");
%>
{"statusCode":"<%=statusCode%>", "message":"<%=StringUtils.trimToEmpty(message)%>", "callbackType":"<%=StringUtils.trimToEmpty(callbackType)%>", "navTabId":"<%=StringUtils.trimToEmpty(navTabId)%>",
"forwardUrl":"<%=StringUtils.trimToEmpty(forwardUrl)%>"}
