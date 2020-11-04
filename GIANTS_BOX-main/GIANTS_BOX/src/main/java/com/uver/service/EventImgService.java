package com.uver.service;

import java.util.List;

import com.uver.cmn.Search;
import com.uver.vo.EventImgVO;
import com.uver.vo.ImgVO;
	
public interface EventImgService {
	
	/**
	 * 이미지, 이벤트_이미지 테이블 등록
	 * 
	 * @param EventImgVO
	 * @return int (1: 성공 / 0: 실패)
	 */
	public int doInsert(EventImgVO eventImg);
	
	/**
	 * 이미지, 이벤트_이미지 테이블 삭제
	 * 
	 * @param ing imgSeq
	 * @return int(1: 성공 / 0: 실패)
	 */
	public int doDelete(int imgSeq);
	
	/**
	 * 이미지 수정
	 * 썸네일 여부만 변경
	 * 
	 * @param EventImgVO
	 * @return int(1:성공 / 0: 실패)
	 */
	public int doUpdate(EventImgVO eventImg);
	
	/**
	 * 이미지seq 기반 단건 조회
	 * 
	 * @param int imgSeq
	 * @return EventImgVO
	 */
	public EventImgVO doSelectOne(int imgSeq);
	
	
	/**
	 * 검색 기반 다건 조회
	 * 
	 * @param Search search
	 * @return
	 */
	public List<EventImgVO> doSelectList(Search search);

	/**
	 * 이벤트seq 기반 다건 조회
	 * 
	 * @param int eventSeq
	 * @return List<EventImgVO>
	 */
	public List<EventImgVO> doSelectAll(int eventSeq);

}
