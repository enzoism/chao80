<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="org.apache.commons.lang3.StringUtils"%>

<%
	String callbackType = request.getParameter("callbackType");
	if (StringUtils.isEmpty(callbackType)) {
		callbackType = (String) request.getAttribute("callbackType");
	}
	String forwardUrl = request.getParameter("forwardUrl");
	if (StringUtils.isEmpty(forwardUrl)) {
		forwardUrl = (String) request.getAttribute("forwardUrl");
	}
	String statusCode = request.getParameter("statusCode");
	if (StringUtils.isEmpty(statusCode)) {
		statusCode = (String) request.getAttribute("statusCode");
	}
	String message = request.getParameter("message");
	if (StringUtils.isEmpty(message)) {
		message = (String) request.getAttribute("message");
	}
	String userID = request.getParameter("userID");
	if (StringUtils.isEmpty(userID)) {
		userID = (String) request.getAttribute("userID");
	}
	String loginID = request.getParameter("loginID");
	if (StringUtils.isEmpty(userID)) {
		loginID = (String) request.getAttribute("loginID");
	}
	message = StringUtils.trimToEmpty(message);
	statusCode = StringUtils.trimToEmpty(statusCode);
	forwardUrl = StringUtils.trimToEmpty(forwardUrl);
	callbackType = StringUtils.trimToEmpty(callbackType);
	String resultjson = String.format("{\"statusCode\":\"%s\", \"message\":\"%s\",\"navTabId\":\"\", \"callbackType\":\"%s\",\"forwardUrl\":\"%s\",\"userID\":\"%s\",\"loginID\":\"%s\"}",
			statusCode, message, callbackType, forwardUrl,userID,loginID);
	out.println(resultjson);
%>
