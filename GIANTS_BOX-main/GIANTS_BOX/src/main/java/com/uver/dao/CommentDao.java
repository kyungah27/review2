package com.uver.dao;

import java.util.List;

import com.uver.vo.CommentVO;

public interface CommentDao {

	// -----메서드
	int doInsert(CommentVO comment);

	int doDelete(CommentVO vo);

	int doUpdate(CommentVO vo);

	CommentVO doSelectOne(int comment_seq);

	List<CommentVO> doSelectList(CommentVO vo);

}