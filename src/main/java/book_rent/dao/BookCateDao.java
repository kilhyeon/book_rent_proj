package book_rent.dao;

import java.util.List;

import book_rent.dto.BookCate;

public interface BookCateDao {
	List<BookCate> selectBookCateByAll();
	List<BookCate> selectBookCateByNo(BookCate cate);
	List<BookCate> selectBookCateByName(BookCate cate);
	
	int insertBookCate(BookCate cate);
	int updateBookCate(BookCate cate);
	int deleteBookCate(BookCate cate);
}
