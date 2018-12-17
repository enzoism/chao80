<%@page import="cn.smartcandy.framework.utils.CStringUtils"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ page import="cn.smartcandy.framework.biz.exception.*"%>
<%@ page import="cn.smartcandy.framework.core.exception.*"%>
<%@ page import="cn.smartcandy.framework.core.log.Log"%>
<%@ page import="cn.smartcandy.framework.utils.*"%>
<%@ page import="cn.smartcandy.framework.web.*"%>
<%@ page import="cn.smartcandy.common.ExceptionHandler"%>
<%
	String statusCode = "200";
	String message = "";
	String callbackType = "";
	String redirectUrl = "";
	// 对话框宽度、高度
	int pdWidth = 0;
	int pdHeight = 0;
	String http_referer = request.getHeader("referer");
	ExceptionHandler exceptionHandler = new ExceptionHandler();
	
	if (exception instanceof UserNotLoginException) {// 用户没有登录
		statusCode = "301";
		message = exception.getMessage();
		
		//redirectUrl = "/noLogin.html";
		//非Ajax请求直接定向到登录页面
		/* if(!PageUtils.isAjaxRequest(request)){
			String msgSessionInvalidate = "您长时间未操作，会话超时，请重新登录！";
			//request.setAttribute("loginMessage", msgSessionInvalidate);
			response.sendRedirect(redirectUrl);
		} */
		System.out.print("此时用户没有登录");
	} else if (exception instanceof BusinessException) {// 业务类异常
		statusCode = "500";
		message = exceptionHandler.getBusinessExceptionMessage(exception);
		Log.logError("BusinessException:" + message);

		System.out.print(message);
	} else {// 其他异常
		statusCode = "500";
		message = exceptionHandler.getBusinessExceptionMessage(exception);
		Log.logError(message + ":500", new Exception(exception));
	}
	exception.printStackTrace();
	// message=message.replaceAll("\n","");
	// message=message.replaceAll("\"","'");
	
	request.setAttribute("exception", exception);
	request.setAttribute("statusCode", statusCode);
	request.setAttribute("message", message);
	request.setAttribute("redirectUrl", redirectUrl);
		
	//Ajax请求直接输出错误信息
	if(PageUtils.isAjaxRequest(request)){
		String result = String.format("{\"statusCode\":\"%s\", \"message\":\"%s\",\"navTabId\":\"\", \"callbackType\":\"%s\",\"redirectUrl\":\"%s\","
				+ "\"pdWidth\":\"%s\",\"pdHeight\":\"%s\"}", statusCode, message, callbackType, redirectUrl, pdWidth, pdHeight);
		out.println(result);
		System.out.print("是AJAX请求");
	}else if(CStringUtils.isEmpty(redirectUrl)){//非Ajax请求直接定向到错误页面
		request.getRequestDispatcher("/error.jsp").forward(request, response);
		System.out.print("不是AJAX请求");
	}
%>