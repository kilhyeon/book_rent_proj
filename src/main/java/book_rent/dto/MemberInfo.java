package book_rent.dto;

public class MemberInfo {
	private int memNo;
	private String memName;
	private String memBirth;
	private String memTel;
	private String memCp;
	private String memAddr;
	private String memGrade;

	public MemberInfo() {
		// TODO Auto-generated constructor stub
	}

	public MemberInfo(int memNo) {
		this.memNo = memNo;
	}

	public MemberInfo(int memNo, String memName, String memBirth, String memTel, String memCp, String memAddr,
			String memGrade) {
		this.memNo = memNo;
		this.memName = memName;
		this.memBirth = memBirth;
		this.memTel = memTel;
		this.memCp = memCp;
		this.memAddr = memAddr;
		this.memGrade = memGrade;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
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

	public String getMemGrade() {
		return memGrade;
	}

	public void setMemGrade(String memGrade) {
		this.memGrade = memGrade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + memNo;
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
		MemberInfo other = (MemberInfo) obj;
		if (memNo != other.memNo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s", memNo);
	}

}
