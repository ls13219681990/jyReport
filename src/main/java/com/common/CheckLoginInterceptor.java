package com.common;

import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

public class CheckLoginInterceptor {
    private ServletContext servletContext;
    private WebApplicationContext ctx;
	
	/*@Inject
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public void init() {
		ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		super.init();
	}
	*//**
     *
     *//*
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		
		Map session = actionInvocation.getInvocationContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest (); 
		return actionInvocation.invoke();
//		ServletContext sc = ServletActionContext.getServletContext();
////		Map count = (Map) sc.getAttribute("AccessCount");// 判断如果登录成功，统计数字自增
////		if(count==null){
////			count = new HashMap();
////		}
////		count.put(request.getSession().getId(), request.getSession().getId());
////		sc.setAttribute("AccessCount", count);
////		
////	    System.out.println(count);
//		
//		// 检查Session中是否存在user
//		
//		Long userId = (Long)session.get("userId");
//		if (userId != null ) {
//			// 存在的情况下进行后续操作。
//			return actionInvocation.invoke();
//		} else {
//			// 否则终止后续操作，返回LOGIN
//			String temp = request.getParameter("userId");
//			if(temp != null && !temp.isEmpty() ){
//				session.put("userId", Long.parseLong(temp.trim()));
//				return actionInvocation.invoke();
//			}else{
//				return "sessionOut";
//			}
//		}
	}
//	private String getOriginPage(){
//		String page="";
//		HttpServletRequest request = ServletActionContext.getRequest (); 
//		String queryString=request.getQueryString();
//		if(queryString.indexOf("=")!=-1){
//			page=queryString.substring(queryString.indexOf("=")+1);
//		}
//		return page;
//	}*/
}
