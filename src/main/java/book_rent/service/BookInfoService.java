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
}
