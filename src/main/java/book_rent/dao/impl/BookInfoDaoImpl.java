package book_rent.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import book_rent.dao.BookInfoDao;
import book_rent.database.JdbcConn;
import book_rent.dto.BookCate;
import book_rent.dto.BookInfo;

public class BookInfoDaoImpl implements BookInfoDao {

	private static final BookInfoDaoImpl instance = new BookInfoDaoImpl();

	public static BookInfoDaoImpl getInstance() {
		return instance;
	}

	public BookInfoDaoImpl() {
	}

	@Override
	public List<BookInfo> selectBookInfoByAll() {
		String sql = "select bookNo, bookName, bookCount, bookCateNo, bookCateName, rentState from vw_bookinfo_cate order by bookNo";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<BookInfo> list = new ArrayList<BookInfo>();
				do {
					list.add(getBookInfo(rs));
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

		String sql = "select bookNo, bookName, bookCount, bookCateNo, bookCateName, rentState from vw_bookinfo_cate where bookNo = ? order by bookNo";

		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, bookinfo.getBookNo());

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<BookInfo> list = new ArrayList<BookInfo>();
					do {
						list.add(getBookInfo(rs));
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
		String sql = "select bookNo, bookName, bookCount, bookCateNo, bookCateName, rentState from vw_bookinfo_cate where bookName = ? order by bookNo";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, bookinfo.getBookName());

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<BookInfo> list = new ArrayList<BookInfo>();
					do {
						list.add(getBookInfo(rs));
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
		String sql = "select bookNo, bookName, bookCount, bookCateNo, bookCateName, rentState from vw_bookinfo_cate where bookCateNo = ? order by bookNo";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, bookinfo.getBookCateNo().getBookCateNo());

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<BookInfo> list = new ArrayList<BookInfo>();
					do {
						list.add(getBookInfo(rs));
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
		String sql = "select bookNo, bookName, bookCount, bookCateNo, bookCateName, rentState from vw_bookinfo_cate where rentState = ? order by bookNo";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, bookinfo.getRentStateToInt());

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<BookInfo> list = new ArrayList<BookInfo>();
					do {
						list.add(getBookInfo(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private BookInfo getBookInfo(ResultSet rs) throws SQLException {
		int bookNo = rs.getInt("bookNo");
		String bookName = rs.getString("bookName");
		int bookCount = rs.getInt("bookCount");
		BookCate bookCateNo = new BookCate(rs.getInt("bookCateNo"), rs.getString("bookCateName"));
		int rentState = rs.getInt("rentState");
		return new BookInfo(bookNo, bookName, bookCount, bookCateNo, rentState);

	}

	@Override
	public int insertBook(BookInfo bookinfo) {
		String sql = "insert into bookinfo(bookNo, bookName, bookCount, bookCateNo) values (?, ?, ?, ?)";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, bookinfo.getBookNo());
			pstmt.setString(2, bookinfo.getBookName());
			pstmt.setInt(3, bookinfo.getBookCount());
			pstmt.setInt(4, bookinfo.getBookCateNo().getBookCateNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateBook(BookInfo bookinfo) {
		String sql = "update bookinfo set bookName = ?, bookCount = ?, bookCateNo = ? where bookNo = ? ";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, bookinfo.getBookName());
			pstmt.setInt(2, bookinfo.getBookCount());
			pstmt.setInt(3, bookinfo.getBookCateNo().getBookCateNo());
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

	@Override
	public BookInfo selectBookInfoBybookNo(int bookNo) {
		String sql = "select bookNo, bookName, bookCount, bookCateNo, bookCateName, rentState from vw_bookinfo_cate where bookNo = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, bookNo);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {

					return getBookInfo(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
