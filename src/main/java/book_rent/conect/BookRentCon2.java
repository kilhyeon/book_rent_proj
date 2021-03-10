package book_rent.conect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import book_rent.dto.Member_info_dto;
import book_rent.uitl.JdbcUtil;

public class BookRentCon2 {
	public static void main(String[] args) {

		ArrayList<Member_info_dto> list = null;

		String sql = "select memNo, memName, memBirth, memTel, memCp, memAddr from member_info";
		
		try (Connection con = JdbcUtil.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			
			// 1. JDBC 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			list = new ArrayList<>();
			System.out.println(list);
			while (rs.next()) {
				list.add(getMember_info(rs));
			}
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver Not Found");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Member_info Query 결과는");
		for (Member_info_dto m : list) {
			System.out.println(m);
		}

	}

	private static Member_info_dto getMember_info(ResultSet rs) throws SQLException {
		String memNo = rs.getString("memNo");
		String memName = rs.getString("memName");
		String memBirth = rs.getString("memBirth");
		String memTel = rs.getString("memTel");
		String memCp = rs.getString("memCp");
		String memAddr = rs.getString("memAddr");
		return new Member_info_dto(memNo, memName, memBirth, memTel, memCp, memAddr);
	}

}
