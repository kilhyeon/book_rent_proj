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
		String memNo = rs.getString("memno");
		String memName = rs.getString("memname");
		String memBirth = rs.getString("membirth");
		String memTel = rs.getString("memtel");
		String memCp = rs.getString("memcp");
		String memAddr = rs.getString("memaddr");
		String memGrade = rs.getString("memgrade");
		return new MemberInfo(memNo, memName, memBirth, memTel, memCp, memAddr, memGrade);
	}

	@Override
	public int insertEmployee(MemberInfo member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateEmployee(MemberInfo member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEmployee(MemberInfo member) {
		// TODO Auto-generated method stub
		return 0;
	}

}
