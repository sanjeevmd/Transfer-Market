package transfermarketproject.controller;

import java.io.Console;
import java.time.Instant;

import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import transfermarketproject.Model.Manager;
import transfermarketproject.Model.Player;

public class RequestInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String type = "";

		try {
			Logger logger = Logger.getLogger(RequestInterceptor.class);
			logger.info("Request URL::" + request.getRequestURL().toString() + ":: Start Time=" + Instant.now());
		}catch(Exception e) {
			
		}
		
		if(request.getSession().getAttribute("user") != null) {
			type = request.getSession().getAttribute("user").getClass().toString();
		}
		if(type.contains("Manager")) {
			Manager manager = (Manager) request.getSession().getAttribute("user");
			if(manager != null) {
				return true;
			}
		}else if(type.contains("Player")){
			Player player = (Player) request.getSession().getAttribute("user");	
			if(player != null) {
				return true;
			}
		}
		response.sendRedirect("/TransferMarketProject/Index/User");
		return false;
	}
}
