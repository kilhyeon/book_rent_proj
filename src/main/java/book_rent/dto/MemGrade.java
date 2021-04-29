package book_rent.dto;

public class MemGrade {
	private int memGradeNo;
	private String memGradeName;
	private int memGradeRentCount;

	public MemGrade() {
		// TODO Auto-generated constructor stub
	}

	public MemGrade(int memGradeNo) {
		this.memGradeNo = memGradeNo;
	}

	public MemGrade(int memGradeNo, String memGradeName) {
		this.memGradeNo = memGradeNo;
		this.memGradeName = memGradeName;
	}

	public MemGrade(int memGradeNo, String memGradeName, int memGradeRentCount) {
		this.memGradeNo = memGradeNo;
		this.memGradeName = memGradeName;
		this.memGradeRentCount = memGradeRentCount;
	}

	public int getMemGradeNo() {
		return memGradeNo;
	}

	public void setMemGradeNo(int memGradeNo) {
		this.memGradeNo = memGradeNo;
	}

	public String getMemGradeName() {
		return memGradeName;
	}

	public void setMemGradeName(String memGradeName) {
		this.memGradeName = memGradeName;
	}

	public int getMemGradeRentCount() {
		return memGradeRentCount;
	}

	public void setMemGradeRentCount(int memGradeRentCount) {
		this.memGradeRentCount = memGradeRentCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + memGradeNo;
		result = prime * result + memGradeRentCount;
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
		MemGrade other = (MemGrade) obj;
		if (memGradeNo != other.memGradeNo)
			return false;
		if (memGradeRentCount != other.memGradeRentCount)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s(%s)", memGradeName, memGradeNo);
	}

}
