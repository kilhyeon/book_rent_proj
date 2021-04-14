package book_rent.dto;

import java.util.Date;

public class Rent {
	private int rentNo;
	private MemberInfo memNo;
	private BookInfo bookNo;
	private Date rentDate;
	private Date returnDate;
	private int lateDate;

	public Rent() {
		// TODO Auto-generated constructor stub
	}

	public Rent(int rentNo) {
		this.rentNo = rentNo;
	}

	public Rent(int rentNo, MemberInfo memNo, BookInfo bookNo, Date rentDate, Date returnDate, int lateDate) {
		this.rentNo = rentNo;
		this.memNo = memNo;
		this.bookNo = bookNo;
		this.rentDate = rentDate;
		this.returnDate = returnDate;
		this.lateDate = lateDate;
	}

	public int getRentNo() {
		return rentNo;
	}

	public void setRentNo(int rentNo) {
		this.rentNo = rentNo;
	}

	public MemberInfo getMemNo() {
		return memNo;
	}

	public void setMemNo(MemberInfo memNo) {
		this.memNo = memNo;
	}

	public BookInfo getBookNo() {
		return bookNo;
	}

	public void setBookNo(BookInfo bookNo) {
		this.bookNo = bookNo;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getLateDate() {
		return lateDate;
	}

	public void setLateDate(int lateDate) {
		this.lateDate = lateDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rentNo;
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
		Rent other = (Rent) obj;
		if (rentNo != other.rentNo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Rent [rentNo=%s, memNo=%s, bookNo=%s, rentDate=%s, returnDate=%s, lateDate=%s]", rentNo,
				memNo, bookNo, rentDate, returnDate, lateDate);
	}

}
