package com.uver.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

import com.uver.vo.EventImgVO;
import com.uver.vo.ImgVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) //스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class TestEventImgDao {
	private static final Logger LOG = LoggerFactory.getLogger(TestEventImgDao.class);
	
	@Autowired
    WebApplicationContext  context;
    
    @Autowired
    EventImgDaoImpl dao;
    
    EventImgVO eventImg01;
    EventImgVO eventImg02;
    EventImgVO eventImg03;

	@Before
	public void setUp() throws Exception {
		LOG.debug("---setup()---------------------------");
		LOG.debug("[context] " + context);
		LOG.debug("[EventImgDao] " + dao);
    	
		
		
		ImgVO img01 = new ImgVO(89, "originName01", "serverName01", "png", 10, "y", "2020-08-08", "regId01");
		ImgVO img02 = new ImgVO(90, "originName02", "serverName02", "png", 10, "y", "2020-08-08", "regId02");
		
		//---이미지 시퀀스, 이벤트 시퀀스
		eventImg01 = new EventImgVO(89, 2, img01);
		eventImg02 = new EventImgVO(90, 2, img02);
		eventImg03 = new EventImgVO(91, 3, img01);
    	
    	LOG.debug("[eventImg01] " + eventImg01);
    	LOG.debug("[eventImg02] " + eventImg02);
    	LOG.debug("[eventImg03] " + eventImg03);
    	LOG.debug("----------------------------------");
	}


	@Test
	@Ignore
	public void test() {
		LOG.debug("---test()---");
	}
	
	
	
	@Test
	@Ignore
	public void addAndGet() {
		//---추가
		int flag = dao.doInsert(eventImg01);
		flag += dao.doInsert(eventImg02);
		flag += dao.doInsert(eventImg03);
		assertThat(flag, is(3));
		
		//---다건 조회
		List<EventImgVO> list = dao.doSelectList(eventImg01.getEventSeq());
		assertThat(eventImg01, is(list.get(0)));
		assertThat(eventImg02, is(list.get(1)));
		
		assertThat(dao.count(eventImg01.getEventSeq()), is(list.size()));
	}
	
	@Test
//	@Ignore
	public void doSelectOne() {
		//---단건 조회
		dao.doSelectOne(eventImg01.getImgSeq());
		dao.doSelectOne(eventImg02.getImgSeq());
		dao.doSelectOne(eventImg03.getImgSeq());
	}
	
	
	@After
	public void tearDown() throws Exception {
		LOG.debug("---tearDown()---");		
	}

}
