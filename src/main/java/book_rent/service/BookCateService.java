package book_rent.service;

import java.util.List;

import book_rent.dao.BookCateDao;
import book_rent.dao.impl.BookCateDaoImpl;
import book_rent.dto.BookCate;

public class BookCateService {
	private BookCateDao cateDao = BookCateDaoImpl.getInstance();

	public List<BookCate> showBookCateByAll() {
		return cateDao.selectBookCateByAll();

	}
}
