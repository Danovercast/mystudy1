package com.dabai.springmvc.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception e) {
		ModelAndView mov=new ModelAndView();
		if(e instanceof MessageException){
			MessageException ee=(MessageException) e;
			
		}else{
			mov.addObject("error", "unknow Exception");
		}
		mov.setViewName("error");
		return mov;
	}

}
