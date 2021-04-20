package book_rent.dto;

public class MemberInfo {
	private int memNo;
	private String memName;
	private String memBirth;
	private String memTel;
	private String memCp;
	private String memAddr;
	private int memGradeNo;

	public MemberInfo() {
		// TODO Auto-generated constructor stub
	}

	public MemberInfo(int memNo) {
		this.memNo = memNo;
	}

	public MemberInfo(String memName) {
		this.memName = memName;
	}

	public MemberInfo(int memNo, String memName, String memBirth, String memTel, String memCp, String memAddr,
			int memGradeNo) {
		this.memNo = memNo;
		this.memName = memName;
		this.memBirth = memBirth;
		this.memTel = memTel;
		this.memCp = memCp;
		this.memAddr = memAddr;
		this.memGradeNo = memGradeNo;
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

	public int getMemGradeNo() {
		return memGradeNo;
	}

	public void setMemGradeNo(int memGradeNo) {
		this.memGradeNo = memGradeNo;
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
		return String.format(
				"MemberInfo [memNo=%s, memName=%s, memBirth=%s, memTel=%s, memCp=%s, memAddr=%s, memGrade=%s]", memNo,
				memName, memBirth, memTel, memCp, memAddr, memGradeNo);
	}

//	@Override
//	public String toString() {
//		return String.format("%s", memNo);
//	}

}
