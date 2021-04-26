package book_rent.service;

import java.util.List;

import book_rent.dao.RentDao;
import book_rent.dao.impl.RentDaoImpl;
import book_rent.dto.BookInfo;
import book_rent.dto.MemberInfo;
import book_rent.dto.Rent;

public class RentService {
	private RentDao rentDao = RentDaoImpl.getInstance();

	public List<Rent> showRentList() {
		return rentDao.selectRentByAll();
	}
	
	public List<Rent> showRentListByMemNo(MemberInfo memInfo){
		return rentDao.selectRentByMemNo(memInfo);
	}
	

	public int addRent(MemberInfo itemMemNo, BookInfo itemBookNo) {
		return rentDao.insertRent(itemMemNo, itemBookNo);
	}

}
