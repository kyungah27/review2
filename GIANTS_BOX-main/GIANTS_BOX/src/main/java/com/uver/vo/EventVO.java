package com.uver.vo;

import com.uver.cmn.DTO;

public class EventVO extends DTO {

	/**이벤트 순번*/
	private int eventSeq;
	/**회원ID*/
	private String userId;
	/**이벤트명*/
	private String eventNm;
	/**내용*/
	private String content;
	/**모임정원*/
	private int capacity;
	/**영화정보*/
	private String movieInfo;
	/**시작일*/
	private String startDt;
	/**종료일*/
	private String endDt;
	/**장소*/
	private String location;
	/**등록일*/
	private String regDt;
	/**수정일*/
	private String targetDt;
	
	public EventVO() {}

	public EventVO(int eventSeq, String userId, String eventNm, String content, int capacity, String movieInfo,
			String startDt, String endDt, String location, String regDt, String targetDt) {
		super();
		this.eventSeq = eventSeq;
		this.userId = userId;
		this.eventNm = eventNm;
		this.content = content;
		this.capacity = capacity;
		this.movieInfo = movieInfo;
		this.startDt = startDt;
		this.endDt = endDt;
		this.location = location;
		this.regDt = regDt;
		this.targetDt = targetDt;
	}

	public int getEventSeq() {
		return eventSeq;
	}

	public void setEventSeq(int eventSeq) {
		this.eventSeq = eventSeq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEventNm() {
		return eventNm;
	}

	public void setEventNm(String eventNm) {
		this.eventNm = eventNm;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getMovieInfo() {
		return movieInfo;
	}

	public void setMovieInfo(String movieInfo) {
		this.movieInfo = movieInfo;
	}

	public String getStartDt() {
		return startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getTargetDt() {
		return targetDt;
	}

	public void setTargetDt(String targetDt) {
		this.targetDt = targetDt;
	}

	@Override
	public String toString() {
		return "EventVO [eventSeq=" + eventSeq + ", userId=" + userId + ", eventNm=" + eventNm + ", content=" + content
				+ ", capacity=" + capacity + ", movieInfo=" + movieInfo + ", startDt=" + startDt + ", endDt=" + endDt
				+ ", location=" + location + ", regDt=" + regDt + ", targetDt=" + targetDt + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
