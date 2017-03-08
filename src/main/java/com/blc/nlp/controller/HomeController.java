package com.blc.nlp.controller;

import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import com.blc.nlp.dao.HomeDAO;

@Controller
public class HomeController {
	@Autowired
	private HomeDAO homeDAO;
	
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object,Object> saveFile(MultipartHttpServletRequest request) throws Exception {
		Iterator<String> itr = request.getFileNames();
		MultipartFile multipartFile = request.getFile(itr.next());		
		Map<Object,Object> map = homeDAO.searchFile(multipartFile);
		return map;
	}


}
