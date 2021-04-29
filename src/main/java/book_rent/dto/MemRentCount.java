package book_rent.dto;

public class MemRentCount extends MemberInfo {
	private int memRentCount;

	public MemRentCount(int memRentCount) {
		this.memRentCount = memRentCount;
		super.setMemRentCount(memRentCount);
	}

}
