package cn.smartcandy.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.smartcandy.common.common.SessionKeys;
import cn.smartcandy.common.utils.TMemberUtils;
import cn.smartcandy.framework.utils.CStringUtils;

/**
 * @项目名称：zmjema
 * @类名称：ManagerAuthorFilter
 * @类描述：权限管理过滤器
 * @创建人：tangzhifeng
 * @创建时间：2017年10月24日 下午12:05:13
 * @修改人：someOne
 * @修改时间：2017年10月24日 下午12:05:13 
 * @Company:SmartCandy (C) 2017
 * @version 1.0
 */
public class AuthorFilter implements Filter {

	private static final String ADMINSTR = "/admin/";							// 管理平台
	private static final String ADMIN_REDIRECT_PATH = "/admin/login.html";		// 管理后台页面
	private static final String USER_REDIRECT_PATH= "/index.html";				// 管理后台页面

	public FilterConfig config;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();

		String filterIgnore = CStringUtils.replaceBlank(config.getInitParameter("filterIgnore")); // 登录登陆页面
		String filterSuffix = config.getInitParameter("filterSuffix"); // 过滤资源后缀参数

		String[] filterIgnoreList = filterIgnore.split(";");
		String[] filterSuffixList = filterSuffix.split(";");

		// 不过滤的后缀
		if (!AuthorFilter.isContains(request.getRequestURI(), filterSuffixList)) {// 只对指定过滤参数后缀进行过滤
			chain.doFilter(request, response);
			return;
		}
		System.out.println("----------------URL:"+request.getRequestURI());
		// 不过滤的页面
		if (AuthorFilter.isContains(request.getRequestURI(), filterIgnoreList)
				&& request.getRequestURI().indexOf("/admin/index.html") < 0) {// 对登录页面不进行过滤
			chain.doFilter(request, response);
			return;
		}
		// 取出当前登录用户
		Object userObject = session.getAttribute(SessionKeys.LOGIN_USER);
		Object adminObject = session.getAttribute(SessionKeys.LOGIN_ADMIN);
		
		if (TMemberUtils.isAdmin(adminObject)) {
			System.out.println("------------------>登录级别：超级管理员");
			chain.doFilter(request, response);
			return;
		} else if (TMemberUtils.isUser(userObject)) {
			System.out.println("------------------>登录级别：普通用户");
			chain.doFilter(request, response);
			return;
		} else if(request.getRequestURI().indexOf(ADMINSTR)>=0 && !TMemberUtils.isAdmin(adminObject) && request.getRequestURI().indexOf(ADMIN_REDIRECT_PATH)<=0){
			System.out.println("------------------>访问级别：超级管理员");
			response.sendRedirect(AuthorFilter.ADMIN_REDIRECT_PATH);
			return;
		} else{
			System.out.println("------------------>访问级别：登录用户");
			response.sendRedirect(AuthorFilter.USER_REDIRECT_PATH);
			return;
		}
	}

	@Override
	public void destroy() {
		this.config = null;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
	}

	public static boolean isContains(String container, String[] regx) {
		boolean result = false;
		for (int i = 0; i < regx.length; i++) {
			if (container.indexOf(regx[i]) != -1) {
				return true;
			}
		}
		return result;
	}

}
