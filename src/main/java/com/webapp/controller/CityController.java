package com.webapp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webapp.command.CityCommand;
import com.webapp.command.Code;
import com.webapp.command.District;
import com.webapp.mapper.CityMapper;
import com.webapp.mapper.CountryMapper;
import com.webapp.model.City;
import com.webapp.validator.CityCommandValidator;

@Controller
@RequestMapping("/city")
public class CityController {
	static Log log = LogFactory.getLog(CityController.class);
	
	
	@Autowired
	CityMapper cityMapper;
	@Autowired   // scan 하여 등록시 이와같은 타입이 있으면 자동등록함 
	CountryMapper countryMapper;
	@Autowired
	CityCommandValidator validator;
	
	

	@RequestMapping("/district/{countrycode:[A-Z]{3}}")
	// <-이같은 표현식을 정규표현식이라 한다, path variable
	String getDistricts(@PathVariable String countrycode, Model model) {
		log.info("getDistricts()...countryCode = " + countrycode);
		
		List<String> districts = cityMapper.selectDistricts(countrycode);
		
		model.addAttribute("districts", districts);
		
		return "city/districts";
		
	}
	
	
	
	@RequestMapping
	String listByParameter(String countrycode, Model model) {
		model.addAttribute("countrycode", countrycode);
		
		List<Code> list = countryMapper.selectCodes();
		
		for (Code c : list) {
			log.info("code = " + c.getCode()+ " " + " name = " + c.getName());
		}
		
		return "city/list";
	}
	
	

	@RequestMapping("/list")
	void listByParameter(HttpServletRequest request, Model model) {
		
		String countrycode = request.getParameter("countrycode");
		
		log.info("requestURI = " + request.getRequestURI());
		
		model.addAttribute("countrycode", countrycode);
		// return "city/list";
	}

	@ModelAttribute("countryCode")
	List<Code> getCountryCode() {
//		Map<String, String> countryCode = new HashMap<String, String>();
//		countryCode.put("KOR", "대한민국");
//		countryCode.put("USA", "미국");
//		countryCode.put("UK", "영국");
//		countryCode.put("CHN", "중궈");

		
//		List<District> model = new ArrayList<District>();
		
		List<Code> model = countryMapper.selectCodes();
		log.info("get countryCode()......size = " + model.size());
		
//		for (Code c : list) {
//			model.add(new District(c.getCode(), c.getName()));
//		}
		
		return model;
	}

	@ModelAttribute("districts")
	List<String> getDistrict(String countryCode) {
		
		List<String> model = cityMapper.selectDistricts(countryCode);
		log.info("get Districts()......size = " + model.size());
//		List<District> list = new ArrayList<District>();
//		list.add(new District("Seoul", "서울"));
//		list.add(new District("Cheju", "제주"));
//		list.add(new District("Chollabuk", "전라북도"));
//		list.add(new District("Chollanam", "전라남도"));
//		list.add(new District("Chungchongnam", "충청남도"));
//		list.add(new District("Inchon", "인천"));
//		list.add(new District("Kang-won", "강원도"));
//		list.add(new District("Kwangju", "광주광역시"));
//		list.add(new District("Kyonggi", "경기도"));
//		list.add(new District("Kyongsangbuk", "경상북도"));
//		list.add(new District("Kyongsangnam", "경상남도"));
//		list.add(new District("Pusan", "부산광역시"));
//		list.add(new District("Taegu", "대구광역시"));
//		list.add(new District("Taejon", "대전광역시"));
		
		return model;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	String registerForm(@ModelAttribute("city") CityCommand command) {
		log.info("registerForm().....");
		return "city/registerForm";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	String register(@ModelAttribute("city") CityCommand command, Errors errors, Model model) {
		log.info("register() POST............");
		
		/*
		 * City Command Validation
		 */
		
		
		validator.validate(command, errors);
		
		
		
		
		if(errors.hasFieldErrors("name")){
			FieldError e = errors.getFieldError("name");
			String[] codes = e.getCodes();
			for (String s : codes) {
				log.info("[" + s + "]");
			}
		}
		
		
		if (errors.hasErrors()) {
			errors.reject("city.register", new Object[] { command.getName() },
					"city Global Error"); // global error
			return "city/registerForm";
		}
		/*
		 *  DB Register ==> Service를 사용해서 처리 한다.
		 */
		
		City city = command.getCity();
		cityMapper.insert(city);
		model.addAttribute("city", city);
		
		
		return "city/registerSuccess";
	}
	
	@RequestMapping("/modify")
	String modify(@ModelAttribute("city") CityCommand command, Errors errors) {
		if (errors.hasFieldErrors()) {
			List<FieldError> fieldErrors = errors.getFieldErrors();
			for (FieldError fe : fieldErrors) {
				log.info("Field = " + fe.getField());
				log.info("ObjectName = " + fe.getObjectName());
				log.info("getCodes = " + Arrays.toString(fe.getCodes()));
				log.info("getRejectedValue = " + fe.getRejectedValue());
			}
		}

		return "city/modify";
	}

}
