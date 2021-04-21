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

	public List<BookInfo> showBookListByNo(BookInfo bookinfo) {
		return bookDao.selectBookInfoByNo(bookinfo);
	}

	public List<BookInfo> showBookListByName(BookInfo bookinfo) {
		return bookDao.selectBookInfoByName(bookinfo);
	}

	public List<BookInfo> showBookListByCate(BookInfo bookinfo) {
		return bookDao.selectBookInfoByCate(bookinfo);
	}

	public List<BookInfo> showBookListByRent(BookInfo bookinfo) {
		return bookDao.selectBookInfoByRent(bookinfo);
	}

	public BookInfo showBookByBookNo(int bookNo) {
		return bookDao.selectBookInfoBybookNo(bookNo);
	}
	
	
	public void addBook(BookInfo bookinfo) {
		bookDao.insertBook(bookinfo);
	}

	public void modifyBook(BookInfo bookinfo) {
		bookDao.updateBook(bookinfo);
	}

	public void removeBook(BookInfo bookinfo) {
		bookDao.deleteBook(bookinfo);
	}
}
