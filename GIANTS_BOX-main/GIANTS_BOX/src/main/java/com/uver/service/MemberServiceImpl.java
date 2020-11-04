package com.uver.service;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.uver.dao.MemberDao;
import com.uver.dao.MemberDaoImpl;
import com.uver.vo.MemberVO;

@Service("MemberServiceImpl")
public class MemberServiceImpl {
	final Logger LOG = LoggerFactory.getLogger(MemberServiceImpl.class);
@Autowired
MemberDaoImpl memberDaoImpl;

public MemberServiceImpl() {}


public void setMemberDaoImpl(MemberDaoImpl memberDaoImpl) {
	this.memberDaoImpl = memberDaoImpl;
}


/**
 * 회원가입
 * @throws SQLException 
 * @throws ClassNotFoundException 
 */
public int regPw(MemberVO inputuser) throws ClassNotFoundException, SQLException {
	int flag = 0;

	String pwPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
	
	Matcher matcher = Pattern.compile(pwPattern).matcher(inputuser.getPassword());
	if(matcher.matches() == true) {
		LOG.debug("회원등록이 완료되었습니다.");

		flag = 1;
	}else {
		LOG.debug("영문자,숫자,특수문자를 조합해주세요");
	}
	
	return flag;
	

}

/**
 * 로그인
 * 
 * @param user
 */
// View User loginUser 를줌  -> Controller ( Service.login(loginUser) )  -> Service(userDaoImpl.doSelectList(); ) 

MemberVO inputUser = new MemberVO();

public MemberVO login(MemberVO inputUser) {
	MemberVO resultVO = null;
	MemberVO searchMember = new MemberVO();
	searchMember.setUserId("H");

	List<MemberVO> list = memberDaoImpl.doSelectList("10", "H"); // H170_01

	for (MemberVO vo : list) {
		if (vo.getUserId().equals(inputUser.getUserId()) && vo.getPassword().equals(inputUser.getPassword())) {
			LOG.debug("로그인에 성공하셨습니다.");
			resultVO = vo;
		}
	}
	if (resultVO == null) {
		LOG.debug("아이디 혹은 비밀번호를 확앤해주세요");
	}

	return resultVO;
}


}
