package book_rent.dao;

import java.sql.Connection;

import book_rent.uitl.JdbcUtil;

public class DaoFactory {
	private static final Connection connection = JdbcUtil.getConnection();

	private DaoFactory(){}

	public static Dao getMember_info_dao() {
		return new Member_info_dao(connection);
	}

//	public static DAO getCarDAO() {
//		return new CarDAO(connection);
//	}
//
//	public static DAO getRantingDAO() {
//		return new RantingDAO(connection);
//	}
//
//	public void finalize() throws Throwable {
//
//	}

}