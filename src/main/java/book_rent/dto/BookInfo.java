package book_rent.dto;

public class BookInfo {
	private String bookNo;
	private String bookName;
	private String cateNo;
	private String rentState;

	public BookInfo() {
		// TODO Auto-generated constructor stub
	}

	public BookInfo(String bookNo) {
		this.bookNo = bookNo;
	}

	public BookInfo(String bookNo, String bookName, String cateNo, String rentState) {
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.cateNo = cateNo;
		this.rentState = rentState;
	}

	public String getBookNo() {
		return bookNo;
	}

	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getCateNo() {
		return cateNo;
	}

	public void setCateNo(String cateNo) {
		this.cateNo = cateNo;
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
		result = prime * result + ((bookNo == null) ? 0 : bookNo.hashCode());
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
		if (bookNo == null) {
			if (other.bookNo != null)
				return false;
		} else if (!bookNo.equals(other.bookNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("BookInfo [bookNo=%s, bookName=%s, cateNo=%s, rentState=%s]", bookNo, bookName, cateNo,
				rentState);
	}

}
