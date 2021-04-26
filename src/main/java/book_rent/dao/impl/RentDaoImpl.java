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
import book_rent.dto.BookCate;
import book_rent.dto.BookInfo;
import book_rent.dto.MemGrade;
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
		String sql = "select rentNo, memNo, memName, memGradeNo, memGradeName, bookNo, bookName, bookCateNo, bookCateName, "
				+ "rentDate, returnDate, lateDate from vw_rent_mb order by rentNo";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Rent> list = new ArrayList<Rent>();
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

	@Override
	public List<Rent> selectRentByMemNo(MemberInfo memInfo) {
		String sql = "select rentNo, memNo, memName, memGradeNo, memGradeName, bookNo, bookName, bookCateNo, bookCateName, "
				+ "rentDate, returnDate, lateDate from vw_rent_mb where memNo = ? order by rentNo";
		
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, memInfo.getMemNo());

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<Rent> list = new ArrayList<Rent>();
					do {
						list.add(GetRent(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	private Rent GetRent(ResultSet rs) throws SQLException {
		int rentNo = rs.getInt("rentNo");
		MemberInfo memNo = new MemberInfo(rs.getInt("memNo"), rs.getString("memName"));
//		MemberInfo memName = new MemberInfo(rs.getString("memName"));
//		MemGrade memGradeNo = new MemGrade(rs.getInt("memGradeNo"));
//		MemGrade memGradeName = new MemGrade(rs.getString("memGradeName"));
		BookInfo bookNo = new BookInfo(rs.getInt("bookNo"), rs.getString("bookName"));
//		BookInfo bookName = new BookInfo(rs.getString("bookName"));
//		BookCate bookCateNo = new BookCate(rs.getInt("bookCateNo"));
//		BookCate bookCateName = new BookCate(rs.getString("bookCateName"));
		Date rentDate = rs.getDate("rentDate");
		Date returnDate = rs.getDate("returnDate");
		int lateDate = rs.getInt("lateDate");

		return new Rent(rentNo, memNo, bookNo, rentDate, returnDate, lateDate);
	}

	private Rent GetRent2(ResultSet rs) throws SQLException {
		int rentNo = rs.getInt("rentNo");
		MemberInfo memNo = new MemberInfo(rs.getInt("memNo"));
		BookInfo bookNo = new BookInfo(rs.getInt("bookNo"));
		Date rentDate = rs.getTimestamp("rentDate");
		Date returnDate = rs.getTimestamp("returnDate");
		int lateDate = rs.getInt("lateDate");

		return new Rent(rentNo, memNo, bookNo, rentDate, returnDate, lateDate);
	}

	@Override
	public int insertRent(MemberInfo memNo, BookInfo bookNo) {
		String rentSql = "insert into rent (memNo, bookNo, rentDate, returnDate, lateDate) values (?, ?, now(), DATE_ADD(NOW(), INTERVAL 3 DAY), 0)";
		String bookSql = "update bookinfo set rentState = 1 where bookNo = ?";
		Connection con = JdbcConn.getConnection();

		try (PreparedStatement pstmt = con.prepareStatement(rentSql);
				PreparedStatement pstmt2 = con.prepareStatement(bookSql);) {

			pstmt.setInt(1, memNo.getMemNo());
			pstmt.setInt(2, bookNo.getBookNo());
			pstmt.executeUpdate();
			pstmt2.setInt(1, bookNo.getBookNo());
			pstmt2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	

}
