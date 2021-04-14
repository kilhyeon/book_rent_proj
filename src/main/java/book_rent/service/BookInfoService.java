package book_rent.service;

import java.util.List;

import book_rent.dao.BookInfoDao;
import book_rent.dao.impl.BookInfoDaoImpl;
import book_rent.dto.BookInfo;

public class BookInfoService {
	private BookInfoDao bookDao = BookInfoDaoImpl.getInstance();

	public List<BookInfo> showBookList() {
		return bookDao.selectBookInfoByAll();
	}
	
	public BookInfo showBookByNo(BookInfo bookinfo) {
		return bookDao.selectBookByNo(bookinfo);
	}
	
	public void addBook (BookInfo bookinfo) {
		bookDao.insertBook(bookinfo);
	}
	
	public void modifyBook (BookInfo bookinfo) {
		bookDao.updateBook(bookinfo);
	}
	
	public void removeBook (BookInfo bookinfo) {
		bookDao.deleteBook(bookinfo);
	}
}
