package com.uver.vo;

import com.uver.cmn.DTO;

public class CommentVO extends DTO {

	
	/** 댓글 순번 */
	private int commentSeq;
	/** 이벤트,후기 순번 */
	private int seq;
	/** 10: 이벤트, 20:후기 */
	private String div;
	/** 댓글내용 */
	private String content;
	/** 등록일 */
	private String regDt;
	/** 등록아이디 */
	private String regId;
	/** 수정일 */
	private String modDt;

	public CommentVO() {
	}

	public CommentVO(int commentSeq, int seq, String div, String content, String regDt, String regId, String modDt) {
		super();
		this.commentSeq = commentSeq;
		this.seq = seq;
		this.div = div;
		this.content = content;
		this.regDt = regDt;
		this.regId = regId;
		this.modDt = modDt;
	}

	public int getCommentSeq() {
		return commentSeq;
	}

	public void setCommentSeq(int commentSeq) {
		this.commentSeq = commentSeq;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getDiv() {
		return div;
	}

	public void setDiv(String div) {
		this.div = div;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	@Override
	public String toString() {
		return "CommentVO [commentSeq=" + commentSeq + ", seq=" + seq + ", div=" + div + ", content=" + content
				+ ", regDt=" + regDt + ", regId=" + regId + ", modDt=" + modDt + ", toString()=" + super.toString()
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commentSeq;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((div == null) ? 0 : div.hashCode());
		result = prime * result + ((regId == null) ? 0 : regId.hashCode());
		result = prime * result + seq;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentVO other = (CommentVO) obj;
		if (commentSeq != other.commentSeq)
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (div == null) {
			if (other.div != null)
				return false;
		} else if (!div.equals(other.div))
			return false;
		if (regId == null) {
			if (other.regId != null)
				return false;
		} else if (!regId.equals(other.regId))
			return false;
		if (seq != other.seq)
			return false;
		return true;
	}
}
