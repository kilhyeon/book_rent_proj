package book_rent.service;

import java.util.List;

import book_rent.dao.RentDao;
import book_rent.dao.impl.RentDaoImpl;
import book_rent.dto.BookInfo;
import book_rent.dto.Rent;
import book_rent.ui.content.MemberInfo;

public class RentService {
	private RentDao rentDao = RentDaoImpl.getInstance();

	public List<Rent> showRentList() {
		return rentDao.selectRentByAll();
	}
	
	public List<Rent> showRentListByMemNo(MemberInfo memInfo){
		return rentDao.selectRentByMemNo(memInfo);
	}
	
	public Rent showRentByRentNo(int rentNo) {
		return rentDao.selectRentByRentNo(rentNo);
	}
	

	public int addRent(MemberInfo itemMemNo, BookInfo itemBookNo) {
		return rentDao.insertRent(itemMemNo, itemBookNo);
	}

	public int delRent(BookInfo bookNo, Rent rentNo) {
		return rentDao.deleteRent(bookNo, rentNo);
	}
}
