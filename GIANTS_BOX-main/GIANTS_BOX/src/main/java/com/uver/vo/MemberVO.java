package com.uver.vo;

public class MemberVO {

	/**순번*/
	private int seq;
	
	/**회원ID*/
	private String userId;
	
	/**이름*/
	private String name;
	
	/**비밀번호*/
	private String password;
	
	/**이메일*/
	private String email;
	
	/**휴대폰*/
	private String cellPhone;
	
	/**생년월일*/
	private String birthday;
	
	/**회원ID*/
	private int auth;
	
	/**등록일*/
	private String regDt;
	
	/**영화장르*/
	private String genre;

	public MemberVO() {
		super();
	}

	public MemberVO(String userId, String name, String password, String email, String cellPhone, String birthday,
			int auth,String genre) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.email = email;
		this.cellPhone = cellPhone;
		this.birthday = birthday;
		this.auth = auth;
		this.genre = genre;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "memberVO [seq=" + seq + ", userId=" + userId + ", name=" + name + ", password=" + password + ", email="
				+ email + ", cellPhone=" + cellPhone + ", birthday=" + birthday + ", auth=" + auth + ", regDt=" + regDt
				+ ", genre=" + genre + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
