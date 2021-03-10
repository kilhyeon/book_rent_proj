package book_rent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import book_rent.dto.Member_info_dto;

public class Member_info_dao extends Dao<Member_info_dto> {

	public Member_info_dao(Connection connection) {
		super(connection);
	}

	@Override
	public boolean create(Member_info_dto obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Member_info_dto obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Member_info_dto obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Member_info_dto find(String ref) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member_info_dto> all() {
		Vector<Member_info_dto> members = new Vector<>();
		try {
			String query = "SELECT * FROM member_info";
			PreparedStatement state = this.connection.prepareStatement(query,
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet result = state.executeQuery();

			while(result.next()){
				members.add(new Member_info_dto(
						result.getString("memNo"),
						result.getString("MemName"),
						result.getString("MemBirth"),
						result.getString("MemTel"),
						result.getString("MemCp"),
						result.getString("MemAddr")
						)
				);
			}
			return members;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}

	@Override
	public List<Member_info_dto> available() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

}
