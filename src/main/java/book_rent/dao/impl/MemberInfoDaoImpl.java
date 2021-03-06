package book_rent.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import book_rent.dao.MemberInfoDao;
import book_rent.dto.MemGrade;
import book_rent.dto.MemGradeRentCount;
import book_rent.dto.MemberInfo;
import book_rent.uitl.JdbcConn;

public class MemberInfoDaoImpl implements MemberInfoDao {

	private static final MemberInfoDaoImpl instance = new MemberInfoDaoImpl();

	public static MemberInfoDaoImpl getInstance() {
		return instance;
	}

	public MemberInfoDaoImpl() {
	}

	@Override
	public List<MemberInfo> selectMemberInfoByAll() {
		String sql = "select memNo, memName, memBirth, memTel, memCp, memAddr, memGradeNo, memGradeName, memGradeRentCount, memRentCount from vw_meminfo_grade order by memNo";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<MemberInfo> list = new ArrayList<MemberInfo>();
				do {
					list.add(getMemberInfo(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MemberInfo> selectMemberInfoByNo(MemberInfo memberinfo) {
		String sql = "select memNo, memName, memBirth, memTel, memCp, memAddr, memGradeNo, memGradeName, memGradeRentCount, memRentCount from vw_meminfo_grade where memNo = ? order by memNo";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, memberinfo.getMemNo());

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<MemberInfo> list = new ArrayList<MemberInfo>();
					do {
						list.add(getMemberInfo(rs));
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
	public List<MemberInfo> selectMemberInfoByName(MemberInfo memberinfo) {
		String sql = "select memNo, memName, memBirth, memTel, memCp, memAddr, memGradeNo, memGradeName, memGradeRentCount, memRentCount from vw_meminfo_grade where memName= ? order by memNo";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, memberinfo.getMemName());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<MemberInfo> list = new ArrayList<MemberInfo>();
					do {
						list.add(getMemberInfo(rs));
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
	public List<MemberInfo> selectMemberInfoByBirth(MemberInfo memberinfo) {
		String sql = "select memNo, memName, memBirth, memTel, memCp, memAddr, memGradeNo, memGradeName, memGradeRentCount, memRentCount from vw_meminfo_grade where memBirth = ? order by memNo";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, memberinfo.getMemBirth());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<MemberInfo> list = new ArrayList<MemberInfo>();
					do {
						list.add(getMemberInfo(rs));
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
	public List<MemberInfo> selectMemberInfoByCp(MemberInfo memberinfo) {
		String sql = "select memNo, memName, memBirth, memTel, memCp, memAddr, memGradeNo, memGradeName, memGradeRentCount, memRentCount from vw_meminfo_grade where memCp = ? order by memNo";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, memberinfo.getMemCp());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<MemberInfo> list = new ArrayList<MemberInfo>();
					do {
						list.add(getMemberInfo(rs));
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
	public List<MemberInfo> selectMemberInfoByGrade(MemberInfo memberinfo) {
		String sql = "select memNo, memName, memBirth, memTel, memCp, memAddr, memGradeNo, memGradeName, memGradeRentCount, memRentCount from vw_meminfo_grade where memGradeNo = ? order by memNo";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, memberinfo.getMemGradeNo().getMemGradeNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<MemberInfo> list = new ArrayList<MemberInfo>();
					do {
						list.add(getMemberInfo(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private MemberInfo getMemberInfo(ResultSet rs) throws SQLException {
		int memNo = rs.getInt("memNo");
		String memName = rs.getString("memName");
		String memBirth = rs.getString("memBirth");
		String memTel = rs.getString("memTel");
		String memCp = rs.getString("memCp");
		String memAddr = rs.getString("memAddr");
		MemGrade memGradeNo = new MemGrade(rs.getInt("memGradeNo"), rs.getString("memGradeName"));
		MemGradeRentCount memGradeRentCount = new MemGradeRentCount(rs.getInt("memGradeRentCount"));
		int memRentCount = rs.getInt("memRentCount");
		return new MemberInfo(memNo, memName, memBirth, memTel, memCp, memAddr, memGradeNo, memGradeRentCount,
				memRentCount);
	}
	
	private MemberInfo getMemberRank(ResultSet rs) throws SQLException {
		int memNo = rs.getInt("memNo");
		String memName = rs.getString("memName");		
		MemGrade memGradeNo = new MemGrade(rs.getInt("memGradeNo"), rs.getString("memGradeName"));
		int memRecord = rs.getInt("memRecord");
		return new MemberInfo(memNo, memName, memGradeNo, memRecord);
	}
	
	

	@Override

	public int insertMember(MemberInfo memberinfo) {
		String sql = "insert into memberinfo(memNo, memName, memBirth, memTel, memCp, memAddr, memGradeNo) "
				+ "values (?, ?, ?, ?, ?, ?, ?)";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, memberinfo.getMemNo());
			pstmt.setString(2, memberinfo.getMemName());
			pstmt.setString(3, memberinfo.getMemBirth());
			pstmt.setString(4, memberinfo.getMemTel());
			pstmt.setString(5, memberinfo.getMemCp());
			pstmt.setString(6, memberinfo.getMemAddr());
			pstmt.setInt(7, memberinfo.getMemGradeNo().getMemGradeNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateMember(MemberInfo memberinfo) {
		String sql = "update memberinfo set memName = ?, memBirth = ?, memTel = ?, memCp = ?, memAddr = ?, memGradeNo = ? "
				+ "where memNo = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, memberinfo.getMemName());
			pstmt.setString(2, memberinfo.getMemBirth());
			pstmt.setString(3, memberinfo.getMemTel());
			pstmt.setString(4, memberinfo.getMemCp());
			pstmt.setString(5, memberinfo.getMemAddr());
			pstmt.setInt(6, memberinfo.getMemGradeNo().getMemGradeNo());
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
//			JOptionPane.showMessageDialog(null, "???????????? ???????????? ???????????????.");
		}
		return 0;
	}

	@Override
	public MemberInfo selectMeberInfoByMemNo(int memNo) {
		String sql = "select memNo, memName, memBirth, memTel, memCp, memAddr, memGradeNo, memGradeName, memGradeRentCount, memRentCount from vw_meminfo_grade where memNo = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, memNo);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {

					return getMemberInfo(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MemberInfo> selectMemberRank() {
		String sql = "select memNo, memName, memGradeNo, memGradeName, memRecord from vw_meminfo_grade order by memRecord desc, memNo limit 10";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<MemberInfo> list = new ArrayList<MemberInfo>();
				do {
					list.add(getMemberRank(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
