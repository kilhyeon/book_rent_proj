package book_rent.uitl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConn {

	public static Connection getConnection() {
		String propertiesPeth = "db.properties";
		Connection con = null;
		try (InputStream in = ClassLoader.getSystemResourceAsStream(propertiesPeth)) {
			Properties prop = new Properties();
			prop.load(in);
			con = DriverManager.getConnection(prop.getProperty("url"), prop);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return con;
	}
}
