package book_rent.dao;

import java.util.List;

import book_rent.dto.BookInfo;

public interface BookInfoDao {
	List<BookInfo> selectBookInfoByAll();

	List<BookInfo> selectBookInfoByNo(BookInfo bookinfo);

	List<BookInfo> selectBookInfoByName(BookInfo bookinfo);

	List<BookInfo> selectBookInfoByCate(BookInfo bookinfo);

	List<BookInfo> selectBookInfoByRent(BookInfo bookinfo);

	int insertBook(BookInfo bookinfo);

	int updateBook(BookInfo bookinfo);

	int deleteBook(BookInfo bookinfo);

}
