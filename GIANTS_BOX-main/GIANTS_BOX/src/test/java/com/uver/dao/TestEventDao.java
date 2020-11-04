package com.uver.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.uver.cmn.Search;
import com.uver.vo.EventVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) //스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class TestEventDao {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired //테스트 컨텍스트 프레임워크는 일치하는 컨텍스트를 찾아 DI해 준다.
    WebApplicationContext  context ;
	
	@Autowired
	@Qualifier("eventDaoImpl")
	EventDaoImpl eventDao;
	
	EventVO event01;
	EventVO event02;
	EventVO event03;
	
	@Before
	public void setUp() {
		
		LOG.debug("** setup() **");
    	LOG.debug("***************************************");
    	LOG.debug("** context **"+context);
    	LOG.debug("** userDao **"+eventDao);
		
		event01 = new EventVO(100, "test_01", "새모임01", "새로운모임01", 15, "영화01", "", "", "서울", "", "20/11/13");
		event02 = new EventVO(200, "test_02", "새모임02", "새로운모임02", 15, "영화02", "", "", "경기", "", "20/11/13");
		event03 = new EventVO(300, "test_03", "새모임03", "새로운모임03", 15, "영화03", "", "", "인천", "", "20/11/13");
	}
	
	@Test
	@Ignore
	public void addAndGet() {
		
		//eventDao.doDelete(event01);
		//eventDao.doDelete(event02);
		//eventDao.doDelete(event03);
		
		//eventDao.doInsert(event01);
		//eventDao.doInsert(event02);
		//eventDao.doInsert(event03);
		
		
	}
	
	
	@Test
	public void doSeletList() {
		Search search = new Search("10", "새모임01", 10, 1);
		List<EventVO> list = eventDao.doSelectList(search);
		LOG.debug("list.size():"+list.size());
	}
	
	@Test
	@Ignore
	public void doUpdate() {
		eventDao.doDelete(event01);
		eventDao.doDelete(event02);
		eventDao.doDelete(event03);
		
		int flag = eventDao.doInsert(event01);
		assertThat(1, is(1));
		
		event01.setUserId(event01.getUserId()+"_up");
		event01.setEventNm(event01.getEventNm()+"_up");
		event01.setContent(event01.getContent()+"_up");
		event01.setCapacity(20);
		event01.setMovieInfo(event01.getMovieInfo()+"_up");
		event01.setStartDt("20/11/01");
		event01.setEndDt("20/11/03");
		event01.setLocation(event01.getLocation()+"_up");
		event01.setTargetDt("20/11/13");
		
		flag = eventDao.doUpdate(event01);
		assertThat(1, is(1));

	}
	
	@Test
	@Ignore
	public void doSelectOne() {
		eventDao.doSelectOne(event01.getEventSeq());
	}
	
	@Test
	@Ignore
	public void bean() {
		fail("Not yet implemented");
	}

}
