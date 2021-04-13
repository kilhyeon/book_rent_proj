package book_rent.dao;

import java.util.List;

import book_rent.dto.Rent;

public interface RentDao {
	List<Rent> selectRentByAll();

}
