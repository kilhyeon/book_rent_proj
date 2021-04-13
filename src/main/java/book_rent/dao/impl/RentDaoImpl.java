package book_rent.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import book_rent.dao.RentDao;
import book_rent.database.JdbcConn;
import book_rent.dto.BookInfo;
import book_rent.dto.MemberInfo;
import book_rent.dto.Rent;

public class RentDaoImpl implements RentDao {

	private static final RentDaoImpl instance = new RentDaoImpl();

	public static RentDaoImpl getInstance() {
		return instance;
	}

	public RentDaoImpl() {
	}

	@Override
	public List<Rent> selectRentByAll() {
		String sql = "select rentNo, memNo, bookNo, rentDate, returnDate, lateDate from rent";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Rent> list = new ArrayList();
				do {
					list.add(GetRent(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Rent GetRent(ResultSet rs) throws SQLException {
		String rentNo = rs.getString("rentNo");
		MemberInfo memNo = new MemberInfo(rs.getString("memNo"));
		BookInfo bookNo = new BookInfo(rs.getString("bookNo"));
		Date rentDate = rs.getTimestamp("rentDate");
		Date returnDate = rs.getTimestamp("returnDate");
		int lateDate = rs.getInt("lateDate");
		
//		try {
//			memNo.setMemName(rs.getString("memName"));
//		}catch(SQLException e){}
//		
//		try {
//			bookNo.setBookName(rs.getString("bookName"));
//		}catch(SQLException e){}
//		
		return new Rent(rentNo, memNo, bookNo, rentDate, returnDate, lateDate);
	}

}
