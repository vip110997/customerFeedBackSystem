package serverSide;

import java.sql.*;

public class DatabaseConnection {

	public Connection connectToDatabase() {
		String jdbcURL = "jdbc:mysql://localhost:3306/feedback"; // The database URL should come here;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Connection dbconnect = DriverManager.getConnection(database connection url,
			// username, password);
			Connection dbconnect = DriverManager.getConnection(jdbcURL, "root", "");
			System.out.println("Connection Made");
			return dbconnect;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

}
