package com.uver.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.uver.vo.EventImgVO;
import com.uver.vo.ImgVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                               "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class TestEventImgService {
	private static final Logger LOG = LoggerFactory.getLogger(TestEventImgService.class);

	@Autowired
    WebApplicationContext  context;
    
    @Autowired
    EventImgService service;
    
    private List<EventImgVO> eventImgList;
    
    private int seq = 123;
    
    @Before
    public void setUp() {
    	LOG.debug("---setup()---------------------------");
		
		ImgVO img01 = new ImgVO(seq, "originName01", "serverName01", "png", 10, "y", "2020-08-08", "regId01");
		ImgVO img02 = new ImgVO(seq+1, "originName02", "serverName02", "png", 10, "y", "2020-08-08", "regId02");
		
		eventImgList = Arrays.asList(
							new EventImgVO(seq, 2, img01),
							new EventImgVO(seq+1, 2, img02),
							new EventImgVO(seq+2, 3, img01)
						);
		
		for (EventImgVO vo : eventImgList) {
			LOG.debug("[List] " + vo);
		}
    	LOG.debug("----------------------------------");
    }
    
    
    
    @Test
	@Ignore
	public void test() {
		LOG.debug("---test---");
	}
    
    @Test
    @Ignore
    public void bean() {
    	LOG.debug("----------------------------------");
    	LOG.debug("bean()");
    	LOG.debug("[context] " + context);
		LOG.debug("[EventImgService] " + service);
    	LOG.debug("----------------------------------");
		
		assertThat(context, is(notNullValue()));
		assertThat(service, is(notNullValue()));
    }
    
    
    @Test
//    @Ignore
    public void add() {
    	assertThat(eventImgList.get(0), is(service.doSelectOne(seq)));
    	service.doSelectAll(2);
    	deleteAll();
    	
    	int flag = 0;
    	flag = service.doInsert(eventImgList.get(0));
    	flag += service.doInsert(eventImgList.get(1));
    	flag += service.doInsert(eventImgList.get(2));
    	assertThat(flag, is(eventImgList.size()));
    	
    	
    	
    }
    
    private void deleteAll() {
    	for (EventImgVO vo : eventImgList) {
    		this.service.doDelete(vo.getImgSeq());
    	}
    }
    
    
    @After
	public void tearDown() throws Exception {
		LOG.debug("---tearDown()---");		
	}
	
}
