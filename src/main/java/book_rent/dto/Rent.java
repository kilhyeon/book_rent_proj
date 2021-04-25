package book_rent.dto;

import java.util.Date;

public class Rent {
//	select rentNo, memNo, memName, memGradeNo, memGradeName, bookNo, bookName, bookCateNo, bookCateName, 
//	rentDate, returnDate, lateDate from vw_rent_mb

	private int rentNo;
	private MemberInfo memNo;
	private MemberInfo memName;
	private MemGrade memGradeNo;
	private MemGrade memGradeName;
	private BookInfo bookNo;
	private BookInfo bookName;
	private BookCate bookCateNo;
	private BookCate bookCateName;
	private Date rentDate;
	private Date returnDate;
	private int lateDate;

	public Rent() {
		// TODO Auto-generated constructor stub
	}

	public Rent(int rentNo) {
		this.rentNo = rentNo;
	}

	public Rent(MemberInfo memNo) {
		this.memNo = memNo;
	}

	public Rent(BookInfo bookNo) {
		this.bookNo = bookNo;
	}

	public Rent(int rentNo, MemberInfo memNo, BookInfo bookNo) {
		this.rentNo = rentNo;
		this.memNo = memNo;
		this.bookNo = bookNo;
	}

	public Rent(MemberInfo memNo, BookInfo bookNo) {
		this.memNo = memNo;
		this.bookNo = bookNo;
	}

	public Rent(int rentNo, MemberInfo memNo, BookInfo bookNo, Date rentDate, Date returnDate, int lateDate) {
		this.rentNo = rentNo;
		this.memNo = memNo;
		this.bookNo = bookNo;
		this.rentDate = rentDate;
		this.returnDate = returnDate;
		this.lateDate = lateDate;
	}

	public Rent(int rentNo, MemberInfo memNo, MemberInfo memName, MemGrade memGradeNo, MemGrade memGradeName,
			BookInfo bookNo, BookInfo bookName, BookCate bookCateNo, BookCate bookCateName, Date rentDate,
			Date returnDate, int lateDate) {
		this.rentNo = rentNo;
		this.memNo = memNo;
		this.memName = memName;
		this.memGradeNo = memGradeNo;
		this.memGradeName = memGradeName;
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.bookCateNo = bookCateNo;
		this.bookCateName = bookCateName;
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

	public MemberInfo getMemName() {
		return memName;
	}

	public void setMemName(MemberInfo memName) {
		this.memName = memName;
	}

	public MemGrade getMemGradeNo() {
		return memGradeNo;
	}

	public void setMemGradeNo(MemGrade memGradeNo) {
		this.memGradeNo = memGradeNo;
	}

	public MemGrade getMemGradeName() {
		return memGradeName;
	}

	public void setMemGradeName(MemGrade memGradeName) {
		this.memGradeName = memGradeName;
	}

	public BookInfo getBookName() {
		return bookName;
	}

	public void setBookName(BookInfo bookName) {
		this.bookName = bookName;
	}

	public BookCate getBookCateNo() {
		return bookCateNo;
	}

	public void setBookCateNo(BookCate bookCateNo) {
		this.bookCateNo = bookCateNo;
	}

	public BookCate getBookCateName() {
		return bookCateName;
	}

	public void setBookCateName(BookCate bookCateName) {
		this.bookCateName = bookCateName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookNo == null) ? 0 : bookNo.hashCode());
		result = prime * result + ((memNo == null) ? 0 : memNo.hashCode());
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
		if (bookNo == null) {
			if (other.bookNo != null)
				return false;
		} else if (!bookNo.equals(other.bookNo))
			return false;
		if (memNo == null) {
			if (other.memNo != null)
				return false;
		} else if (!memNo.equals(other.memNo))
			return false;
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
