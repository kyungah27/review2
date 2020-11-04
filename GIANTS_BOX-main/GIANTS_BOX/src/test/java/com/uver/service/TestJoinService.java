package com.uver.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.uver.vo.JoinVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class TestJoinService {
	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	WebApplicationContext context;
	@Autowired
	JoinService joinService;
	
	List<JoinVO> joinArr;
	JoinVO vo01;
	JoinVO vo02;
	JoinVO vo03;
	JoinVO vo04;
	JoinVO vo05;
	

	@Before
	public void setUp() throws Exception {
		joinArr = Arrays.asList(
		vo01 = new JoinVO(1001,28,1),
		vo02 = new JoinVO(1001,27,0),
		vo03 = new JoinVO(1001,29,0)
		);
	}

	@Test
	public void Delete() {
		joinService.doDelete(vo01);
		JoinVO vo = joinService.doSelectOne(vo01);	
		assertThat(vo.getPriority(), is(1));
	}
	
	@Test
	@Ignore
	public void kickAndBen() {	
		
		joinService.banMember(vo01, vo02);
		JoinVO vo = joinService.doSelectOne(vo02);
		assertThat(vo.getPriority(), is(2));
		
		joinService.kickMember(vo01, vo03);
		vo = joinService.doSelectOne(vo03);
		assertThat(vo, is(nullValue()));

	}
	
	@Test
	@Ignore
	public void update() {
		joinService.doUpdate(vo01);
	}
		
	@Test
	public void bean() {
		LOG.debug("context  :"  + 	context);
		LOG.debug("joinService  :"  + joinService);	
		assertThat(context, is(notNullValue()));
		assertThat(joinService, is(notNullValue()));
	}

}
