package book_rent.dto;

public class MemGrade {
	private int memGradeNo;
	private String memGradeName;

	public MemGrade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemGrade(int memGradeNo) {
		super();
		this.memGradeNo = memGradeNo;
	}

	public MemGrade(String memGradeName) {
		super();
		this.memGradeName = memGradeName;
	}

	public MemGrade(int memGradeNo, String memGradeName) {
		super();
		this.memGradeNo = memGradeNo;
		this.memGradeName = memGradeName;
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

	@Override
	public String toString() {
		return String.format("%s(%s)", memGradeName, memGradeNo);
	}

	
}
