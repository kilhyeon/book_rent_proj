package book_rent.dto;

public class BookCate {
	private int bookCateNo;
	private String bookCateName;

	public BookCate() {
		// TODO Auto-generated constructor stub
	}

	public BookCate(int bookCateNo) {
		this.bookCateNo = bookCateNo;
	}

	public BookCate(int bookCateNo, String bookCateName) {
		this.bookCateNo = bookCateNo;
		this.bookCateName = bookCateName;
	}

	public int getBookCateNo() {
		return bookCateNo;
	}

	public void setBookCateNo(int bookCateNo) {
		this.bookCateNo = bookCateNo;
	}

	public String getBookCateName() {
		return bookCateName;
	}

	public void setBookCateName(String bookCateName) {
		this.bookCateName = bookCateName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookCateNo;
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
		BookCate other = (BookCate) obj;
		if (bookCateNo != other.bookCateNo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s(%s)", bookCateName, bookCateNo);
	}

}
