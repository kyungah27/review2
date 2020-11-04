package com.uver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uver.service.JoinService;

@Controller("JoinController")
public class JoinController {
	private static final Logger LOG = LoggerFactory.getLogger(EventImgController.class);
	
	@Autowired
	JoinService service;
	
	public JoinController() {
		
		
	}
	
	@RequestMapping(value = "join/join_view.do")
	public String fileView() {
		LOG.debug("===================");
		LOG.debug("==fileView() ==");
		LOG.debug("===================");
		
		return "join/join_list";
	}

}
