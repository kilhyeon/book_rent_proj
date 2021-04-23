package book_rent.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import book_rent.dao.BookCateDao;
import book_rent.database.JdbcConn;
import book_rent.dto.BookCate;

public class BookCateDaoImpl implements BookCateDao {
	private static final BookCateDaoImpl instance = new BookCateDaoImpl();

	public static BookCateDaoImpl getInstance() {
		return instance;
	}

	public BookCateDaoImpl() {
	}

	@Override
	public List<BookCate> selectBookCateByAll() {
		String sql = "select bookCateNo, bookCateName from category";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<BookCate> list = new ArrayList<BookCate>();
				do {
					list.add(getBookCate(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private BookCate getBookCate(ResultSet rs) throws SQLException {
		int bookCateNo = rs.getInt("bookCateNo");
		String bookCateName = rs.getString("bookCateName");
		return new BookCate(bookCateNo, bookCateName);
	}

	@Override
	public List<BookCate> selectBookCateByNo(BookCate cate) {
		String sql = "select bookCateNo, bookCateName from category where bookCateNo = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, cate.getBookCateNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<BookCate> list = new ArrayList<BookCate>();
					do {
						list.add(getBookCate(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BookCate> selectBookCateByName(BookCate cate) {
		String sql = "select bookCateNo, bookCateName from category where bookCateName = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, cate.getBookCateName());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<BookCate> list = new ArrayList<BookCate>();
					do {
						list.add(getBookCate(rs));
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
	public int insertBookCate(BookCate cate) {
		String sql = "insert into category values(?, ?)";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, cate.getBookCateNo());
			pstmt.setString(2, cate.getBookCateName());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateBookCate(BookCate cate) {
		String sql = "update category set bookCateName = ? where bookCateNo = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, cate.getBookCateName());
			pstmt.setInt(2, cate.getBookCateNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteBookCate(BookCate cate) {
		String sql = "delete from category where bookCateNo = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, cate.getBookCateNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
