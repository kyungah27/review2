package com.uver.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.uver.vo.MemberVO;

//메소드 수행 순서:
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})

public class TestMemberDao {
	Logger LOG = Logger.getLogger(TestMemberDao.class);
	
	@Autowired
	@Qualifier("MemberDaoImpl")
	MemberDao dao;
	
	@Autowired
	ApplicationContext context;
	
	MemberVO member01;
	MemberVO member02;
	MemberVO member03;
	
	@Test
	public void test() {
		//데이터 입력
//		dao.doInsert(member01);
//		dao.doInsert(member02);
//		dao.doInsert(member03);
		
		//데이터삭제
//		int flag = dao.doDelete(31);
//		assertThat(flag, is(1));
//		dao.doDelete(member02.getSeq());
//		dao.doDelete(member03.getSeq());
		
//		//단건조회
//		memberVO resultVO = dao.doSelectOneById("H170_04");
//		memberVO member02 = dao.doSelectOneById("H170_02");
//		memberVO member03 = dao.doSelectOneById("H170_03");
//		memberVO seqResultVO = dao.doSelectOne(35);
//		LOG.debug(seqResultVO.toString());
		
		//다건조회(아이디(10),이름(20))
		List<MemberVO> list = dao.doSelectList("20", "최");
		for(int i = 0 ; i<list.size(); i++) {
			LOG.debug("list "+ i + "번째   :" + list.get(i).toString());
		}
		
		List<MemberVO> list2 = dao.doSelectList("10", "H170_02");
		for(int i = 0 ; i<list2.size(); i++) {
			LOG.debug("list "+ i + "번째   :" + list2.get(i).toString());
		}
		
	}
	
	@Test
	public void update() {
		
		MemberVO updateVO = new MemberVO("H170_04","최현우_04_UU","1234","dotori130@naver.com","01012344321","19900130",1,"스릴러");
		updateVO.setSeq(35);
		
		dao.doUpdata(updateVO);
		
		MemberVO afterUpdateVO =  dao.doSelectOne(35);
		
		LOG.debug(afterUpdateVO);
		
		
		
	}

	

	
	
	@Before
	public void setUp() {
		member01=new MemberVO("H170_04","최현우_04","1234","dotori130@naver.com","01012344321","19900130",1,"스릴러");
		member02=new MemberVO("H170_05","최현우_05","1234","dotori130@naver.com","01012344221","19900130",1,"코믹");
		member03=new MemberVO("H170_06","최현우_06","1234","dotori130@naver.com","01012342321","19900130",1,"멜로");
		LOG.debug("** setup() **");
		LOG.debug("***********************************");
		LOG.debug("context"+context);
	 
	}
	
	
}
