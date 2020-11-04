package com.uver.vo;

import com.uver.cmn.DTO;

//회원순번(PK)(FK)	member_seq(PK)(FK)	N/A	NUMBER(20)	NOT NULL
//이벤트순번(PK)(FK)	event_seq(PK)(FK)	N/A	NUMBER(20)	NOT NULL
//권한	priority	N/A	NUMBER(1)	NULL

public class JoinVO extends DTO {
	
	private int eventSeq;
	private int memberSeq;
	private int priority;
	private String regDt;
	
	public JoinVO() {
		
	}

	public JoinVO(int eventSeq, int memberSeq, int priority) {
		super();
		this.eventSeq = eventSeq;
		this.memberSeq = memberSeq;
		this.priority = priority;
	}

	public int getEventSeq() {
		return eventSeq;
	}

	public void setEventSeq(int eventSeq) {
		this.eventSeq = eventSeq;
	}

	public int getMemberSeq() {
		return memberSeq;
	}

	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	

	@Override
	public String toString() {
		return "JoinVO [eventSeq=" + eventSeq + ", memberSeq=" + memberSeq + ", priority=" + priority + ", regDt="
				+ regDt + ", toString()=" + super.toString() + "]";
	}


	
	

	
	
	

	
	
	

}
