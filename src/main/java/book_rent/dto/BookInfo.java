package book_rent.dto;

public class BookInfo {
	private int bookNo;
	private String bookName;
	private int bookCountTotal;
	private int bookCount;
	private BookCate bookCateNo;
	private int rentState;
	private int bookRecord;

	public BookInfo() {
		// TODO Auto-generated constructor stub
	}

	public BookInfo(int bookNo) {
		this.bookNo = bookNo;
	}

	public BookInfo(String bookName) {
		this.bookName = bookName;
	}

	public BookInfo(int bookNo, String bookName) {
		this.bookNo = bookNo;
		this.bookName = bookName;
	}

	public BookInfo(int bookNo, String bookName, int bookCountTotal, BookCate bookCateNo) {
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.bookCountTotal = bookCountTotal;
		this.bookCateNo = bookCateNo;
	}

	public BookInfo(int bookNo, String bookName, BookCate bookCateNo, int bookRecord) {
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.bookCateNo = bookCateNo;
		this.bookRecord = bookRecord;
	}

	public BookInfo(int bookNo, String bookName, int bookCountTotal, int bookCount, BookCate bookCateNo,
			int rentState) {
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.bookCountTotal = bookCountTotal;
		this.bookCount = bookCount;
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
			return "대여가능";

		} else {
			return "대여불가";
		}
	}

	public void setRentState(int rentState) {
		this.rentState = rentState;
	}

	public int getBookCount() {
		return bookCount;
	}

	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}

	public int getRentStateToInt() {
		return rentState;
	}

	public int getBookCountTotal() {
		return bookCountTotal;
	}

	public void setBookCountTotal(int bookCountTotal) {
		this.bookCountTotal = bookCountTotal;
	}

	public int getBookRecord() {
		return bookRecord;
	}

	public void setBookRecord(int bookRecord) {
		this.bookRecord = bookRecord;
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
		return String.format("BookInfo [bookNo=%s, bookName=%s, bookCount=%s, bookCateNo=%s, rentState=%s]", bookNo,
				bookName, bookCount, bookCateNo, rentState);
	}

//	@Override
//	public String toString() {
//		return String.format("%s", bookNo);

//	}

}
