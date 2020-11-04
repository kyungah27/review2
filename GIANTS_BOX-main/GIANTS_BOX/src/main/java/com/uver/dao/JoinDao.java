package com.uver.dao;

import java.util.List;

import com.uver.vo.JoinVO;

public interface JoinDao {
	/**
	 * 이벤트 참가
	 * @param vo
	 * @return flag ( 1 성공 , 0 실패 )
	 */
	int doInsert(JoinVO vo);
	/**
	 * 이벤트 빠져나오기
	 * @param vo
	 * @return	 flag ( 1 성공 , 0 실패 )
	 */
	int doDelete(JoinVO vo);
	/**
	 * 이벤트 업데이트(권한)
	 * @param vo
	 * @return	 flag ( 1 성공 , 0 실패 )
	 */
	int doUpdate(JoinVO vo);
	/**
	 * 이벤트참여 셀렉원
	 * @param vo
	 * @return	JoinVO( 선택된 단일 개체)
	 */
	JoinVO doSelectOne(JoinVO vo);
	/**
	 * 이벤트 참여 리스트(개인별, 이벤트별)
	 * @param vo
	 * @return list (선택된 복수 개체) 
	 */
	List doSelectList(JoinVO vo);
	/**
	 * priority 권한 부여를 위한 가장 먼저 가입한 유저
	 * @param event_seq
	 * @return 조건에 부합하는 member_seq
	 */
	public int doSelectMinReg(int event_seq);

}