package book_rent.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import book_rent.dao.BookInfoDao;
import book_rent.database.JdbcConn;
import book_rent.dto.BookInfo;
import book_rent.dto.MemberInfo;

public class BookInfoDaoImpl implements BookInfoDao {

	private static final BookInfoDaoImpl instance = new BookInfoDaoImpl();

	public static BookInfoDaoImpl getInstance() {
		return instance;
	}

	public BookInfoDaoImpl() {
	}

	@Override
	public List<BookInfo> selectBookInfoByAll() {
		String sql = "select bookNo, bookName, bookCateNo, rentState from bookinfo";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<BookInfo> list = new ArrayList();
				do {
					list.add(GetBookInfo(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BookInfo> selectBookInfoByNo(BookInfo bookinfo) {
		String sql = "select bookNo, bookName, bookCateNo, rentState from bookinfo where bookNo = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, bookinfo.getBookNo());

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<BookInfo> list = new ArrayList<BookInfo>();
					do {
						list.add(GetBookInfo(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BookInfo> selectBookInfoByName(BookInfo bookinfo) {
		String sql = "select bookNo, bookName, bookCateNo, rentState from bookinfo where bookName = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, bookinfo.getBookName());

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<BookInfo> list = new ArrayList<BookInfo>();
					do {
						list.add(GetBookInfo(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BookInfo> selectBookInfoByCate(BookInfo bookinfo) {
		String sql = "select bookNo, bookName, bookCateNo, rentState from bookinfo where bookCateNo = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, bookinfo.getBookCateNo());

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<BookInfo> list = new ArrayList<BookInfo>();
					do {
						list.add(GetBookInfo(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BookInfo> selectBookInfoByRent(BookInfo bookinfo) {
		String sql = "select bookNo, bookName, bookCateNo, rentState from bookinfo where rentState = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, bookinfo.getRentState());

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<BookInfo> list = new ArrayList<BookInfo>();
					do {
						list.add(GetBookInfo(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private BookInfo GetBookInfo(ResultSet rs) throws SQLException {
		int bookNo = rs.getInt("bookNo");
		String bookName = rs.getString("bookName");
		int bookCateNo = rs.getInt("bookCateNo");
		String rentState = rs.getString("rentState");
		return new BookInfo(bookNo, bookName, bookCateNo, rentState);
	}

	@Override
	public int insertBook(BookInfo bookinfo) {
		String sql = "insert into bookinfo(bookNo, bookName, bookCateNo, rentState) values (?, ?, ?, ?)";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, bookinfo.getBookNo());
			pstmt.setString(2, bookinfo.getBookName());
			pstmt.setInt(3, bookinfo.getBookCateNo());
			pstmt.setString(4, bookinfo.getRentState());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateBook(BookInfo bookinfo) {
		String sql = "update bookinfo set bookName = ?, bookCateNo = ?, rentState =? where bookNo = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, bookinfo.getBookName());
			pstmt.setInt(3, bookinfo.getBookCateNo());
			pstmt.setString(3, bookinfo.getRentState());
			pstmt.setInt(4, bookinfo.getBookNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteBook(BookInfo bookinfo) {
		String sql = "delete from bookinfo where bookNo = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, bookinfo.getBookNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
