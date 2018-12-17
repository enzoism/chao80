<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.StringUtils"%>
<%@ page import="cn.smartcandy.framework.core.log.Log"%>

<%
	String result = (String) request.getAttribute("result");
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
	Log.logDebug("{\"statusCode\":\"200\", \"message\":\"操作成功\", \"callbackType\":\"" + StringUtils.trimToEmpty(callbackType) + "\", \"navTabId\":\""
			+ StringUtils.trimToEmpty(navTabId) + "\", \"forwardUrl\":\"" + StringUtils.trimToEmpty(forwardUrl) + "\"}");
%>
{"statusCode":"200", "message":"操作成功", "result":"<%=StringUtils.trimToEmpty(result)%>", "callbackType":"<%=StringUtils.trimToEmpty(callbackType)%>",
"navTabId":"<%=StringUtils.trimToEmpty(navTabId)%>","forwardUrl":"<%=StringUtils.trimToEmpty(forwardUrl)%>"}
