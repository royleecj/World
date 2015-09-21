package com.webapp.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Controller
@RequestMapping("/locale")
public class LocaleController {

	static Log log = LogFactory.getLog(LocaleController.class);
	/*
		DI  방법
		1. 생성자 주입 방법
		2. property 주입 방법
		3. field 주입 방법
	*/
	@Autowired        //field 주입 방법 
	LocaleResolver localeResolver;
	
	@RequestMapping("/{language:[a-z]{2}}")
	void setLocale(@PathVariable String language,HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("language = " + language);
		
		Locale locale = new Locale(language);
		localeResolver.setLocale(request, response, locale);
		
		
		PrintWriter out = response.getWriter();
		out.println("language = " + language+"<br>");
		out.println("language = " + locale.getLanguage());
				
	}
}
