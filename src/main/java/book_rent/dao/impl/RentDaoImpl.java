package book_rent.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import book_rent.dao.RentDao;
import book_rent.dto.BookCate;
import book_rent.dto.BookInfo;
import book_rent.dto.MemGrade;
import book_rent.dto.MemberInfo;
import book_rent.dto.Rent;
import book_rent.uitl.JdbcConn;

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
					list.add(getRent(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Rent> selectRentByList() {
		String sql = "select rentNo, memNo, memName, memGradeNo, memGradeName, bookNo, bookName, bookCateNo, bookCateName, "
				+ "rentDate, returnDate, lateDate from vw_rent_mb order by rentNo";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Rent> list = new ArrayList<Rent>();
				do {
					list.add(getRentAll(rs));
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
				+ "rentDate, returnDate, lateDate from vw_rent_mb where memNo = ? and returnDate is null order by rentNo";

		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, memInfo.getMemNo());

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<Rent> list = new ArrayList<Rent>();
					do {
						list.add(getRent(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Rent getRentAll(ResultSet rs) throws SQLException {
		int rentNo = rs.getInt("rentNo");
		MemberInfo memNo = new MemberInfo(rs.getInt("memNo"), rs.getString("memName"));
		MemberInfo memName = new MemberInfo(rs.getString("memName"));
		MemGrade memGradeNo = new MemGrade(rs.getInt("memGradeNo"), rs.getString("memGradeName"));
		BookInfo bookNo = new BookInfo(rs.getInt("bookNo"), rs.getString("bookName"));
		BookInfo bookName = new BookInfo(rs.getString("bookName"));
		BookCate bookCateNo = new BookCate(rs.getInt("bookCateNo"), rs.getString("bookCateName"));
		Date rentDate = rs.getDate("rentDate");
		Date returnDate = rs.getDate("returnDate");
		int lateDate = rs.getInt("lateDate");
		return new Rent(rentNo, memNo, memName, memGradeNo, bookNo, bookName, bookCateNo, rentDate, returnDate, lateDate);
	}
	
	

	private Rent getRent(ResultSet rs) throws SQLException {
		int rentNo = rs.getInt("rentNo");
		MemberInfo memNo = new MemberInfo(rs.getInt("memNo"), rs.getString("memName"));
		BookInfo bookNo = new BookInfo(rs.getInt("bookNo"), rs.getString("bookName"));
		Date rentDate = rs.getDate("rentDate");
		Date returnDate = rs.getDate("returnDate");
		int lateDate = rs.getInt("lateDate");

		return new Rent(rentNo, memNo, bookNo, rentDate, returnDate, lateDate);
	}

	private Rent getRent2(ResultSet rs) throws SQLException {
		int rentNo = rs.getInt("rentNo");
		MemberInfo memNo = new MemberInfo(rs.getInt("memNo"));
		BookInfo bookNo = new BookInfo(rs.getInt("bookNo"));
		Date rentDate = rs.getDate("rentDate");
		Date returnDate = rs.getDate("returnDate");
		int lateDate = rs.getInt("lateDate");

		return new Rent(rentNo, memNo, bookNo, rentDate, returnDate, lateDate);
	}

	@Override
	public int insertRent(MemberInfo memNo, BookInfo bookNo) {
		String rentSql = "insert into rent (memNo, bookNo, rentDate, returnDate, lateDate) values (?, ?, now(), null, 0)";
		String bookSql1 = "update bookinfo set bookCount = bookCount+1 where bookNo = ?";
		String bookSql2 = "update bookinfo set rentState = if (bookCount < bookCountTotal, 0, 1) where bookNo = ?";
		String memSql = "update memberinfo set memRentCount = memRentCount+1 where memNo = ?";

		String recordSql1 = "update memberinfo set memRecord = memRecord+1 where memNo = ?";
		String recordSql2 = "update bookinfo set bookRecord = bookRecord+1 where bookNo = ?";

		Connection con = JdbcConn.getConnection();

		try (PreparedStatement pstmt = con.prepareStatement(rentSql);
				PreparedStatement pstmt1 = con.prepareStatement(bookSql1);
				PreparedStatement pstmt2 = con.prepareStatement(bookSql2);
				PreparedStatement pstmt3 = con.prepareStatement(memSql);
				PreparedStatement pstmt4 = con.prepareStatement(recordSql1);
				PreparedStatement pstmt5 = con.prepareStatement(recordSql2);) {

			pstmt.setInt(1, memNo.getMemNo());
			pstmt.setInt(2, bookNo.getBookNo());
			pstmt.executeUpdate();
			pstmt1.setInt(1, bookNo.getBookNo());
			pstmt1.executeUpdate();
			pstmt2.setInt(1, bookNo.getBookNo());
			pstmt2.executeUpdate();
			pstmt3.setInt(1, memNo.getMemNo());
			pstmt3.executeUpdate();

			pstmt4.setInt(1, memNo.getMemNo());
			pstmt4.executeUpdate();
			pstmt5.setInt(1, bookNo.getBookNo());
			pstmt5.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int updateRent(Rent rentNo, MemberInfo memNo) {
		String returnSql1 = "update rent set returnDate = now() where rentNo = ? and returnDate is null";
		String returnSql2 = "update rent set lateDate = if(ReturnDate - rentDate > 3, (ReturnDate - rentDate) -3, 0) where rentNo = ?";
		String bookSql1 = "update bookinfo set bookCount = bookCount-1 where bookNo = ?";
		String bookSql2 = "update bookinfo set rentState = if (bookCount < bookCountTotal, 0, 1) where bookNo = ?";
		String memSql = "update memberinfo set memRentCount = memRentCount-1 where memNo = ?";
		Connection con = JdbcConn.getConnection();
		try (PreparedStatement pstmt1 = con.prepareStatement(returnSql1);
				PreparedStatement pstmt2 = con.prepareStatement(returnSql2);
				PreparedStatement pstmt3 = con.prepareStatement(bookSql1);
				PreparedStatement pstmt4 = con.prepareStatement(bookSql2);
				PreparedStatement pstmt5 = con.prepareStatement(memSql);) {

			pstmt1.setInt(1, rentNo.getRentNo());
			pstmt1.executeUpdate();
			pstmt2.setInt(1, rentNo.getRentNo());
			pstmt2.executeUpdate();
			pstmt3.setInt(1, rentNo.getBookNo().getBookNo());
			pstmt3.executeUpdate();
			pstmt4.setInt(1, rentNo.getBookNo().getBookNo());
			pstmt4.executeUpdate();
			pstmt5.setInt(1, memNo.getMemNo());
			pstmt5.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public Rent selectRentByRentNo(int rentNo) {
		String sql = "select rentNo, memNo, memName, memGradeNo, memGradeName, bookNo, bookName, bookCateNo, bookCateName, "
				+ "rentDate, returnDate, lateDate from vw_rent_mb where rentNo = ? order by rentNo";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, rentNo);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {

					return getRent2(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
