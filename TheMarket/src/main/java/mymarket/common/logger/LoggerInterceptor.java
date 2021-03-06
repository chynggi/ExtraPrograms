package mymarket.common.logger;

import javax.servlet.http.*;

import org.apache.commons.logging.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.handler.*;

public class LoggerInterceptor extends HandlerInterceptorAdapter {
	protected Log log = LogFactory.getLog(LoggerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("====================================== START ======================================");
			log.debug(" Request URI \t: " + request.getRequestURI());
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("====================================== END ======================================\n");
		}
	}
}
