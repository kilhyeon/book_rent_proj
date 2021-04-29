package book_rent.dto;

public class BookCount extends BookInfo {
	private int bookCount;

	public BookCount(int bookCount) {
		this.bookCount = bookCount;
	}

	public int getBookCount() {
		return bookCount;
	}

	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}

}
