package book_rent.dto;

public class BookInfo {
	private int bookNo;
	private String bookName;
	private int bookCateNo;
	private String rentState;

	public BookInfo() {
		// TODO Auto-generated constructor stub
	}

	public BookInfo(int bookNo) {
		this.bookNo = bookNo;
	}

	public BookInfo(String bookName) {
		this.bookName = bookName;
	}

	public BookInfo(int bookNo, String bookName, int bookCateNo, String rentState) {

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

	public int getBookCateNo() {
		return bookCateNo;
	}

	public void setBookCateNo(int bookCateNo) {
		this.bookCateNo = bookCateNo;
	}

	public String getRentState() {
		return rentState;
	}

	public void setRentState(String rentState) {
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
		return String.format("BookInfo [bookNo=%s, bookName=%s, bookCateNo=%s, rentState=%s]", bookNo, bookName,
				bookCateNo, rentState);
	}

//	@Override
//	public String toString() {
//		return String.format("%s", bookNo);
//	}

}
