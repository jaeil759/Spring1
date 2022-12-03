package kr.or.ddit.vo;

public class Member {
	private String mem_id;
	private String mem_pw;
	

	public String getMem_id() {
		return mem_id;
	}


	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}


	public String getMem_pw() {
		return mem_pw;
	}


	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}

	public boolean isMatchPassword(String pw) {
		boolean isMatchPassword = false;
		if (this.mem_pw.equals(pw)) {
			isMatchPassword = true;
		}
		return isMatchPassword;
	}
	
}
