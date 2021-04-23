package book_rent.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import book_rent.dao.MemGradeDao;
import book_rent.database.JdbcConn;
import book_rent.dto.BookCate;
import book_rent.dto.MemGrade;

public class MemGradeDaoImpl implements MemGradeDao {
	private static final MemGradeDaoImpl instance = new MemGradeDaoImpl();

	public static MemGradeDaoImpl getInstance() {
		return instance;
	}

	public MemGradeDaoImpl() {
	}

	@Override
	public List<MemGrade> selectMemGradeByAll() {
		String sql = "select memGradeNo, memGradeName from grade";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<MemGrade> list = new ArrayList<MemGrade>();
				do {
					list.add(getMemGrade(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private MemGrade getMemGrade(ResultSet rs) throws SQLException {
		int memGradeNo = rs.getInt("MemGradeNo");
		String memGradeName = rs.getString("MemGradeName");
		return new MemGrade(memGradeNo, memGradeName);
	}

	@Override
	public List<MemGrade> selectMemGradeByNo(MemGrade grade) {
		String sql = "select memGradeNo, memGradeName from grade where memGradeNo = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, grade.getMemGradeNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<MemGrade> list = new ArrayList<MemGrade>();
					do {
						list.add(getMemGrade(rs));
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
	public List<MemGrade> selectMemGradeByName(MemGrade grade) {
		String sql = "select memGradeNo, memGradeName from grade where memGradeName = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, grade.getMemGradeName());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<MemGrade> list = new ArrayList<MemGrade>();
					do {
						list.add(getMemGrade(rs));
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
	public int insertMemGrade(MemGrade grade) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMemGrade(MemGrade grade) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMemGrade(MemGrade grade) {
		// TODO Auto-generated method stub
		return 0;
	}

}
