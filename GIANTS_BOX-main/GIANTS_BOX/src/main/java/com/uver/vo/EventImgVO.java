package com.uver.vo;

import com.uver.cmn.DTO;

public class EventImgVO extends DTO {

	private int			imgSeq;		/** 이미지 순번 */
	private int 		eventSeq;	/** 이벤트 순번 */
	private ImgVO 		imgVO;		/** ImgVO */
	
	public EventImgVO(int imgSeq, int eventSeq) {
		super();
		this.imgSeq = imgSeq;
		this.eventSeq = eventSeq;
	}

	public EventImgVO(int imgSeq, int eventSeq, ImgVO imgVO) {
		super();
		this.imgSeq = imgSeq;
		this.eventSeq = eventSeq;
		this.imgVO = imgVO;
	}

	public int getImgSeq() {
		return imgSeq;
	}

	public int getEventSeq() {
		return eventSeq;
	}

	public ImgVO getImgVO() {
		return imgVO;
	}

	@Override
	public String toString() {
		return "EventImgVO [imgSeq=" + imgSeq + ", eventSeq=" + eventSeq + ", imgVO=" + imgVO + ", getDiv()=" + getDiv()
				+ ", getNum()=" + getNum() + ", getTotalCnt()=" + getTotalCnt() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eventSeq;
		result = prime * result + imgSeq;
		result = prime * result + ((imgVO == null) ? 0 : imgVO.hashCode());
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
		EventImgVO other = (EventImgVO) obj;
		if (eventSeq != other.eventSeq)
			return false;
		if (imgSeq != other.imgSeq)
			return false;
		if (imgVO == null) {
			if (other.imgVO != null)
				return false;
		} else if (!imgVO.equals(other.imgVO))
			return false;
		return true;
	}
	
	
	
	
	
}
