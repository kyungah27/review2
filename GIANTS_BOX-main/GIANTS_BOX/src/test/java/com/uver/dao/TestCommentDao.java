package com.uver.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.uver.vo.CommentVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class TestCommentDao {
	Logger LOG = Logger.getLogger(TestCommentDao.class);

	@Autowired
	CommentDao dao;

	@Autowired
	ApplicationContext context;

	CommentVO comment01;
	CommentVO comment02;

	@Before
	public void setUp() throws Exception {
		LOG.debug("** setup() **");
		LOG.debug("***************************************");
		LOG.debug("** context **" + context);
		LOG.debug("** CommentDaoImpl **" + dao);
		comment01 = new CommentVO(12, 2, "10", "재밌었어요ㅋㅋㅋㅋㅋㅋㅋ", "", "ehgml", "");
		comment02 = new CommentVO(10, 2, "10", "재밌었어요ㅋㅋㅋㅋㅋㅋㅋ", "", "ehgml", "");

		LOG.debug("[comment01] " + comment01);
		LOG.debug("[comment02] " + comment02);
		LOG.debug("***************************************");

	}

	@Test
	public void test() {
		int flag = 0;
		// 삭제
		// dao.doDelete(comment01);
		// dao.doDelete(comment02);

		// 삽입
		// flag = dao.doInsert(comment01);
		// assertThat(flag, is(1));
		// flag = dao.doInsert(comment02);
		// assertThat(flag, is(1));

		// 수정
		// CommentVO updateVO = new CommentVO(12, 2, "10", "재밌었어요ㅋㅋㅋㅋㅋㅋㅋ", "", "ehgml",
		// "");
		// flag = dao.doUpdate(updateVO);
		// assertThat(flag, is(1));

		// 단건조회 안되는거
		// CommentVO vsVO = dao.doSelectOne(updateVO);
		// checkComment(updateVO, vsVO);
		// CommentVO test = new CommentVO();

		// 단건조회 성공
		// dao.doSelectOne(comment01.getCommentSeq());

		// 리스트 조회
		CommentVO comment = new CommentVO();
		comment.setSeq(2);
		comment.setDiv("10");

		List<CommentVO> list = dao.doSelectList(comment);
		assertThat(list.size(), is(3));

		// 입력데이터와 비교>>>질문,굳이 없어도 되는듯
		checkComment(comment01, list.get(0));
		checkComment(comment02, list.get(1));
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("** tearDown() **");
		LOG.debug("***************************************");
	}

	private void checkComment(CommentVO inVO, CommentVO vsVO) {
		assertThat(inVO.getCommentSeq(), is(vsVO.getCommentSeq()));
		assertThat(inVO.getSeq(), is(vsVO.getSeq()));
		assertThat(inVO.getDiv(), is(vsVO.getDiv()));
		assertThat(inVO.getContent(), is(vsVO.getContent()));
		// assertThat(inVO.getRegDt(), is(vsVO.getRegDt()));
		assertThat(inVO.getRegId(), is(vsVO.getRegId()));
		// assertThat(inVO.getModDt(), is(vsVO.getModDt()));
	}
}
