package com.uver.service;

import java.util.List;

import com.uver.vo.JoinVO;

public interface JoinService {
	
	
	//--------------bypass---------------------- 
	public int doInsert(JoinVO vo);
	
	public JoinVO doSelectOne(JoinVO vo);
	public List doSelectList(JoinVO vo);
	public int doUpdate(JoinVO vo);	
	//--------------bypass---------------------- 
	
	/**
	 * 만약 prioriy가 1이면 등록일이 가장 빠른사람에게 priority를 1 부여
	 * @param vo
	 * @return flag 1 성공 0 실패
	 */
	public int doDelete(JoinVO vo);
	/**
	 *  이벤트에서 강퇴 후, 참여 거절을 위한 메서드
	 * @param masterVO 권한이 1 인 VO
	 * @param targetVO 이벤트에 참여 못하게 하고싶은 VO
	 * @return flag 1 성공 0 실패
	 */
	public int banMember(JoinVO masterVO , JoinVO targetVO );
	
	/**
	 *  이벤트에서 강퇴
	 * @param masterVO 권한이 1 인 VO
	 * @param targetVO 이벤트에 참여 못하게 하고싶은 VO
	 * @return flag 1 성공 0 실패
	 */
	public int kickMember(JoinVO masterVO , JoinVO targetVO );
}
