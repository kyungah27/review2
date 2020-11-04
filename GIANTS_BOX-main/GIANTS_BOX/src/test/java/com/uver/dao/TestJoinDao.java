package com.uver.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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
public class TestJoinDao {
	final static Logger   LOG = LoggerFactory.getLogger(TestJoinDao.class);
	
	@Autowired // 테스트 컨텍스트 프레임워크는 일치하는 컨텍스트를 찾아 DI해 준다.
	WebApplicationContext context;

	@Autowired
	JoinDao joinDao;
	
	JoinVO vo01;
	JoinVO vo02;
	JoinVO vo03;
	JoinVO vo04;
	JoinVO vo05;

	List<JoinVO> joinArr;
	
	@Before
	public void setUp() throws Exception {
		joinArr = Arrays.asList(
		vo01 = new JoinVO(1001,28,1),
		vo02 = new JoinVO(1001,27,0),
		vo03 = new JoinVO(1001,29,0),
		vo04 = new JoinVO(1002,27,0),
		vo05 = new JoinVO(1002,29,1)
		);
	}

	

	// insert delete update selectOne
	@Test
	public void addAndGet() {		
		int flag = 0;	
		// insert delete test

		for(JoinVO vo : joinArr) {
			flag = joinDao.doDelete(vo);
		}		
		for(JoinVO vo : joinArr) {
			flag = joinDao.doInsert(vo);
			assertThat(flag, is(1));
		}
		//업데이트 테스트
		JoinVO updateVO = new JoinVO(1001, 27 ,0);
		flag = joinDao.doUpdate(updateVO);
		assertThat(flag, is(1));
		//셀렉원 업데이트 검증
		JoinVO vsVO = joinDao.doSelectOne(updateVO);
		checkVO(updateVO,vsVO);	
		JoinVO test = new JoinVO();
	}
	
	@Test
	public void SelectList() {
		//event로 검색
		JoinVO voSeartToEvent = new JoinVO();
		voSeartToEvent.setEventSeq(1001);
		List<JoinVO> list01 = joinDao.doSelectList(voSeartToEvent);
		for(JoinVO vo : list01) {
			LOG.debug(vo.toString());
			assertThat(vo.getEventSeq(), is(voSeartToEvent.getEventSeq()));
		}
		//member로 검색
		JoinVO voSeartToMember = new JoinVO();
		voSeartToMember.setMemberSeq(1001);
		List<JoinVO> list02 = joinDao.doSelectList(voSeartToMember);
		for(JoinVO vo : list02) {
			LOG.debug(vo.toString());
			assertThat(vo.getMemberSeq(), is(voSeartToMember.getMemberSeq()));
		}
		
	}
	
	@Test
	public void MinRegTest() {
		int minRegId = joinDao.doSelectMinReg(1002);
		LOG.debug(minRegId+"");
	}
	
	
    private void checkVO(JoinVO orgUser, JoinVO vsUser) {
    	assertThat(orgUser.getPriority(), is(vsUser.getPriority()));
    	assertThat(orgUser.getEventSeq(), is(vsUser.getEventSeq()));
    	assertThat(orgUser.getMemberSeq(), is(vsUser.getMemberSeq()));
    }
	
	
	
	@Test
	public void bean() {
		
		LOG.debug("context"  + context);
		LOG.debug("joinDao"  + joinDao);
		assertThat(context, is(notNullValue()));
		assertThat(joinDao, is(notNullValue()));
	}

}
