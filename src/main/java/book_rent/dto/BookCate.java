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

	public BookCate(String bookCateName) {
		this.bookCateName = bookCateName;
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
	public String toString() {
		return String.format("%s(%s)", bookCateName, bookCateNo);
	}

}
