package com.uver.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.uver.vo.MemberVO;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })


public class TestMemberService {
	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	WebApplicationContext context;
	
	@Autowired
	MemberServiceImpl memberServiceImpl;
	
	MemberVO member01;
	MemberVO member02;
	MemberVO member03;
	
	
	@Before
	public void setUp() throws Exception {
		member01=new MemberVO("H170_04","최현우_04","1234","dotori130@naver.com","01012344321","19900130",1,"스릴러");
		member02=new MemberVO("H170_05","최현우_05","1234","dotori130@naver.com","01012344221","19900130",1,"코믹");
		member03=new MemberVO("H170_06","최현우_06","1234","dotori130@naver.com","01012342321","19900130",1,"멜로");
		
		 LOG.debug("** setup() **");
		 LOG.debug("***********************************");
		 LOG.debug("context"+ context);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void regTest() throws ClassNotFoundException, SQLException {
		
		//비밀번호 조건 테스트
		MemberVO regPwUser = new MemberVO();
		regPwUser.setUserId("H170_07");
		regPwUser.setPassword("@d923f25");
		int resultUser01 = memberServiceImpl.regPw(regPwUser);
		assertThat(1, is(resultUser01));
		
	}
	
	
	@Test
	public void test() {
		//로그인 테스트
				MemberVO loginUser = new MemberVO(); // 사용자가 입력해줄 값 
				loginUser.setUserId("H170_04");
				loginUser.setPassword("1234");		
				MemberVO resultUser = memberServiceImpl.login(loginUser);
				LOG.debug("resultUser" + resultUser);
				loginUser.setUserId("H170_04");
				loginUser.setPassword("12345");		
				memberServiceImpl.login(loginUser);
	}

}
