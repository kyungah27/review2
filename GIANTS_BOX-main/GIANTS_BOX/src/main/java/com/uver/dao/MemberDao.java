package com.uver.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.uver.vo.MemberVO;

public interface MemberDao {

	void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	int doUpdata(MemberVO member);

	MemberVO doSelectOne(int seq);

	MemberVO doSelectOneById(String id);

	/**
	 * 
	 * @param div
	 * @param searchWord
	 * @return
	 */
	List<MemberVO> doSelectList(String div, String searchWord);

	/**
	 * 삭제
	 * @param member
	 * @return
	 */
	int doDelete(int seq);

	/**
	 * 사용자등록
	 * @param member
	 * @return
	 */
	int doInsert(MemberVO member);

}