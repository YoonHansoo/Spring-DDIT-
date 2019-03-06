package kr.or.ddit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ProfileInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(ProfileInterceptor.class);
	/**
	 * Method : preHandle
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 * Method 설명 : controller method 실행 전 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.debug("ProfileInterceptor preHandle");
		//다른 인터셉터 혹은 controller로 요청을 계속 위임처리
		long startTime = System.currentTimeMillis();
		
		request.setAttribute("startTime", startTime);
		
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.debug("ProfileInterceptor postHandle");
		
		long startTime = (long) request.getAttribute("startTime");
		long EndTime = System.currentTimeMillis();
		
		long profilingTime = EndTime-startTime;
		 
		
		logger.debug(" {} -- profilingTime : {}",request.getRequestURI(), profilingTime); 
		 
		
		
		super.postHandle(request, response, handler, modelAndView);
	}
	
	
}
