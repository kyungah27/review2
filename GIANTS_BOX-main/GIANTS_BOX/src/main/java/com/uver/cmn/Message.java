package com.uver.cmn;

/**
 * @author Joun
 *
 */
public class Message extends DTO {

	
	/**
	 * 메시지 ID
	 * ex) 10
	 */
	private String msgId;
	
	/**
	 * 메시지 내용
	 * ex) 등록되었습니다.
	 */
	private String msgContents;

	public Message() {
		super();
	}

	public Message(String msgId, String msgContents) {
		super();
		this.msgId = msgId;
		this.msgContents = msgContents;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgContents() {
		return msgContents;
	}

	public void setMsgContents(String msgContents) {
		this.msgContents = msgContents;
	}

	@Override
	public String toString() {
		return "Message [msgId=" + msgId + ", msgContents=" + msgContents + ", getDiv()=" + getDiv() + ", getNum()="
				+ getNum() + ", getTotalCnt()=" + getTotalCnt() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

	

	
	
	
}
