package book_rent.dto;

public class MemRentCount extends MemberInfo {
	private int memRentCount;

	public MemRentCount(int memRentCount) {
		this.memRentCount = memRentCount;

	}

	public int getMemRentCount() {
		return memRentCount;
	}

	public void setMemRentCount(int memRentCount) {
		this.memRentCount = memRentCount;
	}

}
