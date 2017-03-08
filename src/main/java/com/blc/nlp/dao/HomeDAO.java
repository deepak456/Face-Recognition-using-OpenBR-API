package com.blc.nlp.dao;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;


public interface HomeDAO {

	public Map<Object,Object> searchFile(MultipartFile multipartFile) throws Exception;

}
