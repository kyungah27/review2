package com.sist.ehr;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;


public interface ReviewDao {

	void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	int doUpdate(ReviewVO review);

	ReviewVO doSelectOne(int seq);

	ReviewVO doSelectOneById(String id);

	/**
	 * 
	 * @param div
	 * @param searchWord
	 * @return
	 */
	List<ReviewVO> doSelectList(String div, String searchWord);

	/**
	 * �궘�젣
	 * @param member
	 * @return
	 */
	int doDelete(int seq);

	/**
	 * �궗�슜�옄�벑濡�
	 * @param member
	 * @return
	 */
	int doInsert(ReviewVO review);

}