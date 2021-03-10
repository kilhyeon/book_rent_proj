package book_rent.dto;

public class Member_info_dto {
	private String memNo;
	private String memName;
	private String memBirth;
	private String memTel;
	private String memCp;
	private String memAddr;

	public Member_info_dto() {
		// TODO Auto-generated constructor stub
	}

	public Member_info_dto(String memNo, String memName, String memBirth, String memTel, String memCp, String memAddr) {
		this.memNo = memNo;
		this.memName = memName;
		this.memBirth = memBirth;
		this.memTel = memTel;
		this.memCp = memCp;
		this.memAddr = memAddr;
	}

	public String getMemNo() {
		return memNo;
	}

	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemBirth() {
		return memBirth;
	}

	public void setMemBirth(String memBirth) {
		this.memBirth = memBirth;
	}

	public String getMemTel() {
		return memTel;
	}

	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}

	public String getMemCp() {
		return memCp;
	}

	public void setMemCp(String memCp) {
		this.memCp = memCp;
	}

	public String getMemAddr() {
		return memAddr;
	}

	public void setMemAddr(String memAddr) {
		this.memAddr = memAddr;
	}

	@Override
	public String toString() {
		return String.format("Member_info [memNo=%s, memName=%s, memBirth=%s, memTel=%s, memCp=%s, memAddr=%s]", memNo,
				memName, memBirth, memTel, memCp, memAddr);
	}

}
