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
		String sql = "select bookNo, bookName, cateNo, rentState from bookinfo";
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

	private BookInfo GetBookInfo(ResultSet rs) throws SQLException {
		String bookNo = rs.getString("bookNo");
		String bookName = rs.getString("bookName");
		String cateNo = rs.getString("cateNo");
		String rentState = rs.getString("rentState");
		return new BookInfo(bookNo, bookName, cateNo, rentState);
	}

	@Override
	public int insertBook(BookInfo bookinfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBook(BookInfo bookinfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBook(BookInfo bookinfo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
