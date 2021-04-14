package book_rent.dao;

import java.util.List;

import book_rent.dto.BookInfo;

public interface BookInfoDao {
	List<BookInfo> selectBookInfoByAll();
	
	BookInfo selectBookByNo(BookInfo bookinfo);

	int insertBook(BookInfo bookinfo);

	int updateBook(BookInfo bookinfo);

	int deleteBook(BookInfo bookinfo);

}
