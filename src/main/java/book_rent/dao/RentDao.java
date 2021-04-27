package book_rent.dao;

import java.util.List;

import book_rent.dto.BookInfo;
import book_rent.dto.Rent;
import book_rent.ui.content.MemberInfo;

public interface RentDao {
	List<Rent> selectRentByAll();

	List<Rent> selectRentByMemNo(MemberInfo memInfo);
	
	Rent selectRentByRentNo(int rentNo);
	
	int insertRent(MemberInfo memInfo, BookInfo bookInfo);

	int deleteRent(BookInfo bookNo, Rent rentNo);
}
