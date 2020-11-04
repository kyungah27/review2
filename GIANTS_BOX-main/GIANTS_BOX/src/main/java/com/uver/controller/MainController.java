package com.uver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("MainController")
public class MainController {
	private static final Logger LOG = LoggerFactory.getLogger(EventImgController.class);

	
	//--- 메인페이지로 이동
		@RequestMapping(value="main.do")
		public String fileView() {
			LOG.debug("-------------------");
			LOG.debug("main page()");
			LOG.debug("-------------------");
			
			//--- 추후 views 폴더에 main.jsp 페이지 설정
			return "test_page";
		}

}
