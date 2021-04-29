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
	
	public List<Rent> showRentAllList() {
		return rentDao.selectRentByList();
	}
	
	public List<Rent> showRentListByMemNo(MemberInfo memInfo){
		return rentDao.selectRentByMemNo(memInfo);
	}
	
	public Rent showRentByRentNo(int rentNo) {
		return rentDao.selectRentByRentNo(rentNo);
	}
	

	public int bookRent(MemberInfo memInfo, BookInfo bookInfo) {
		return rentDao.insertRent(memInfo, bookInfo);
	}

	public int bookReturn(Rent rentInfo, MemberInfo memInfo) {
		return rentDao.updateRent(rentInfo, memInfo);
	}
}
