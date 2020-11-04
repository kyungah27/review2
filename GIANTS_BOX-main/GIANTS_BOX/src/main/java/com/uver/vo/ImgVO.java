package com.uver.vo;

import com.uver.cmn.DTO;

public class ImgVO extends DTO {

	private int 	imgSeq;			/** 이미지 순번 */
	private String 	originName;		/** 첨부이름 */
	private String 	serverName;		/** 서버저장이름 */
	private String 	imgType;			/** 확장자 */
	private int 	imgSize;			/** 크기 */
	private String	isThumbnail;	/** 썸네일여부 */
	private String 	regDt;			/** 등록일 */
	private String 	regId;			/** 등록자 */
	
	
	public ImgVO() {
		super();
	}

	public ImgVO(int imgSeq, String originName, String serverName, String imgType, int imgSize, String isThumbnail,
			String regDt, String regId) {
		super();
		this.imgSeq = imgSeq;
		this.originName = originName;
		this.serverName = serverName;
		this.imgType = imgType;
		this.imgSize = imgSize;
		this.isThumbnail = isThumbnail;
		this.regDt = regDt;
		this.regId = regId;
	}



	public int getImgSeq() {
		return imgSeq;
	}



	public String getOriginName() {
		return originName;
	}


	public String getServerName() {
		return serverName;
	}


	public String getImgType() {
		return imgType;
	}



	public int getImgSize() {
		return imgSize;
	}



	public String getIsThumbnail() {
		return isThumbnail;
	}



	public String getRegDt() {
		return regDt;
	}



	public String getRegId() {
		return regId;
	}



	@Override
	public String toString() {
		return "ImgVO [imgSeq=" + imgSeq + ", originName=" + originName + ", serverName=" + serverName + ", imgType="
				+ imgType + ", imgSize=" + imgSize + ", isThumbnail=" + isThumbnail + ", regDt=" + regDt + ", regId="
				+ regId + ", getDiv()=" + getDiv() + ", getNum()=" + getNum() + ", getTotalCnt()=" + getTotalCnt()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + imgSeq;
		result = prime * result + imgSize;
		result = prime * result + ((imgType == null) ? 0 : imgType.hashCode());
		result = prime * result + ((isThumbnail == null) ? 0 : isThumbnail.hashCode());
		result = prime * result + ((originName == null) ? 0 : originName.hashCode());
		result = prime * result + ((regId == null) ? 0 : regId.hashCode());
		result = prime * result + ((serverName == null) ? 0 : serverName.hashCode());
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
		ImgVO other = (ImgVO) obj;
		if (imgSeq != other.imgSeq)
			return false;
		if (imgSize != other.imgSize)
			return false;
		if (imgType == null) {
			if (other.imgType != null)
				return false;
		} else if (!imgType.equals(other.imgType))
			return false;
		if (isThumbnail == null) {
			if (other.isThumbnail != null)
				return false;
		} else if (!isThumbnail.equals(other.isThumbnail))
			return false;
		if (originName == null) {
			if (other.originName != null)
				return false;
		} else if (!originName.equals(other.originName))
			return false;
		if (regId == null) {
			if (other.regId != null)
				return false;
		} else if (!regId.equals(other.regId))
			return false;
		if (serverName == null) {
			if (other.serverName != null)
				return false;
		} else if (!serverName.equals(other.serverName))
			return false;
		return true;
	}
	
	
	
	
}
