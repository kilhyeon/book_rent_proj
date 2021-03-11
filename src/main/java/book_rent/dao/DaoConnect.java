package book_rent.dao;

import java.sql.Connection;

import book_rent.uitl.JdbcUtil;

public class DaoConnect {
	private static final Connection connection = JdbcUtil.getConnection();

	private DaoConnect(){}

	public static Dao getMember_info_dao() {
		return new Member_info_dao(connection);
	}

//	public static Dao getBook_info_dao() {
//		return new book_info_dao(connection);
//	}
//
//	public static DAO getRent_dao() {
//		return new rent_dao(connection);
//	}
//

}