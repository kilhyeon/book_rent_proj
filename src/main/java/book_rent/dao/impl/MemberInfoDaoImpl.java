package book_rent.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import book_rent.dao.MemberInfoDao;
import book_rent.database.JdbcConn;
import book_rent.dto.MemberInfo;

public class MemberInfoDaoImpl implements MemberInfoDao {

	private static final MemberInfoDaoImpl instance = new MemberInfoDaoImpl();

	public static MemberInfoDaoImpl getInstance() {
		return instance;
	}

	public MemberInfoDaoImpl() {
	}

	@Override
	public List<MemberInfo> selectMemberInfoByAll() {
		String sql = "select memNo, memName, memBirth, memTel, memCp, memAddr, memGrade from memberinfo";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<MemberInfo> list = new ArrayList();
				do {
					list.add(GetMemberInfo(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private MemberInfo GetMemberInfo(ResultSet rs) throws SQLException {
		int memNo = rs.getInt("memNo");
		String memName = rs.getString("memName");
		String memBirth = rs.getString("memBirth");
		String memTel = rs.getString("memTel");
		String memCp = rs.getString("memCp");
		String memAddr = rs.getString("memAddr");
		String memGrade = rs.getString("memGrade");
		return new MemberInfo(memNo, memName, memBirth, memTel, memCp, memAddr, memGrade);
	}

	@Override
	public MemberInfo selectMemberByNo(MemberInfo memberinfo) {
		String sql = "select memNo, memName, memBirth, memTel, memCp, memAddr, memGrade from memberinfo where = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, memberinfo.getMemNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return GetMemberInfo(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertMember(MemberInfo memberinfo) {
		String sql = "insert into memberinfo(memNo, memName, memBirth, memTel, memCp, memAddr, memGrade) "
				+ "values (?, ?, ?, ?, ?, ?, ?)";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, memberinfo.getMemNo());
			pstmt.setString(2, memberinfo.getMemName());
			pstmt.setString(3, memberinfo.getMemBirth());
			pstmt.setString(4, memberinfo.getMemTel());
			pstmt.setString(5, memberinfo.getMemCp());
			pstmt.setString(6, memberinfo.getMemAddr());
			pstmt.setString(7, memberinfo.getMemGrade());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateMember(MemberInfo memberinfo) {
		String sql = "update memberinfo set memName = ?, memBirth = ?, memTel = ?, memCp = ?, memAddr = ?, memGrade = ? "
				+ "where memNo = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, memberinfo.getMemName());
			pstmt.setString(2, memberinfo.getMemBirth());
			pstmt.setString(3, memberinfo.getMemTel());
			pstmt.setString(4, memberinfo.getMemCp());
			pstmt.setString(5, memberinfo.getMemAddr());
			pstmt.setString(6, memberinfo.getMemGrade());
			pstmt.setInt(7, memberinfo.getMemNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteMember(MemberInfo memberinfo) {
		String sql = "delete from memberinfo where memNo = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, memberinfo.getMemNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
