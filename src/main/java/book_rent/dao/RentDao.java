package book_rent.dao;

import java.util.List;

import book_rent.dto.BookInfo;
import book_rent.dto.MemberInfo;
import book_rent.dto.Rent;

public interface RentDao {
	List<Rent> selectRentByAll();
	
	int insertRent(MemberInfo itemMemNo, BookInfo itemBookNo);
}
