package book_rent.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class JdbcUtil {

	public static Connection getConnection() {
		String propertiesPeth = "db.properties";
		Connection con = null;
		try(InputStream in = ClassLoader.getSystemResourceAsStream(propertiesPeth)){
			Properties prop = new Properties();
			prop.load(in);
			con = DriverManager.getConnection(prop.getProperty("url"), prop);
			/*
			System.out.println("prop > " + prop);
			System.out.println();
			for(Entry<Object, Object> e : prop.entrySet()) {
				System.out.printf("%s -> %s%n", e.getKey(), e.getValue()); // // key, value 값 출력
			}
			for(Object key : prop.keySet()) {
				System.out.print(key + " -> ");
				System.out.println(prop.get(key)); // key에 대응하는 value 값만 출력 (value가 저장된 주소 값)
			}
			*/
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return con;
	}
}
