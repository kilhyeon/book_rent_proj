package book_rent.dto;

public class BookInfo {
	private int bookNo;
	private String bookName;
	private BookCate bookCateNo;
	private int rentState;

	public BookInfo() {
		// TODO Auto-generated constructor stub
	}

	public BookInfo(int bookNo) {
		this.bookNo = bookNo;
	}

	public BookInfo(String bookName) {
		this.bookName = bookName;
	}

	public BookInfo(BookCate bookCateNo) {
		this.bookCateNo = bookCateNo;
	}

	public BookInfo(int bookNo, String bookName) {
		this.bookNo = bookNo;
		this.bookName = bookName;
	}

	public BookInfo(int bookNo, String bookName, BookCate bookCateNo) {
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.bookCateNo = bookCateNo;
	}

	public BookInfo(int bookNo, String bookName, BookCate bookCateNo, int rentState) {

		this.bookNo = bookNo;
		this.bookName = bookName;
		this.bookCateNo = bookCateNo;
		this.rentState = rentState;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public BookCate getBookCateNo() {
		return bookCateNo;
	}

	public void setBookCateNo(BookCate bookCateNo) {
		this.bookCateNo = bookCateNo;
	}

	public String getRentState() {
		if (rentState == 0) {
			return "보유중";

		} else {
			return "대여중";
		}
	}

	public void setRentState(int rentState) {
		this.rentState = rentState;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookNo;
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
		BookInfo other = (BookInfo) obj;
		if (bookNo != other.bookNo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s(%s)", bookName, bookNo);
	}

//	@Override
//	public String toString() {
//		return String.format("%s", bookNo);
//	}

}
