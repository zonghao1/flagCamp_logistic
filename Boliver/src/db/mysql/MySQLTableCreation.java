package db.mysql;

import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;

public class MySQLTableCreation {
	// Run this as Java application to reset db schema.
	public static void main(String[] args) {
		try {
			// Step 1 Connect to MySQL.
			System.out.println("Connecting to " + MySQLDBUtil.URL);
			Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
			Connection conn = DriverManager.getConnection(MySQLDBUtil.URL);
			
			if (conn == null) {
				return;
			}
			// Step 2 Drop tables in case they exist.
			Statement statement = conn.createStatement();
			
			String sql = "DROP TABLE IF EXISTS OrderHistory";
			statement.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS CurrentOrder";
			statement.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS Robot";
			statement.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS RobotType";
			statement.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS Base";
			statement.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS Users";
			statement.executeUpdate(sql);
			
			// Step 3 Create new tables
			sql = "CREATE TABLE Users"
					+ "user_id VARCHAR(255) NOT NULL,"
					+ "username VARCHAR(255) NOT NULL UNIQUE,"
					+ "password VARCHAR(255) NOT NULL,"
					+ "email VARCHAR(255) NOT NULL,"
					+ "fname VARCHAR(255) NOT NULL,"
					+ "lname VARCHAR(255) NOT NULL,"
					+ "PRIMARY KEY(user_id)"
					+ ")";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE RobotType ("
					+ "type_id VARCHAR(255) NOT NULL,"
					+ "type VARCHAR(255) NOT NULL UNIQUE,"
					+ "speed VARCHAR(255) NOT NULL,"
					+ "PRIMARY KEY (type_id)"
					+ ")";
			statement.executeUpdate(sql);
			
			sql = "CREATE TABLE Base ("
					+ "base_id VARCHAR(255) NOT NULL,"
					+ "address VARCHAR(255) NOT NULL,"
					+ "lat VARCHAR(255) NOT NULL,"
					+ "lon VARCHAR(255) NOT NULL,"
					+ "PRIMARY KEY (base_id)"
					+ ")";
			statement.executeUpdate(sql);
			
			sql = "CREATE TABLE Robot ("
					+ "robot_id VARCHAR(255) NOT NULL,"
					+ "type_id VARCHAR(255) NOT NULL,"
					+ "base_id VARCHAR(255),"
					+ "robotStatus VARCHAR(255) NOT NULL,"
					+ "curLocation VARCHAR(255) NOT NULL,"
					+ "power VARCHAR(255) NOT NULL,"
					+ "destination VARCHAR(255),"
					+ "PRIMARY KEY (robot_id),"
					+ "FOREIGN KEY (base_id) REFERENCES base(base_id),"
					+ "FOREIGN KEY (type_id) REFERENCES RobotType(type_id)"
					+ ")";
			statement.executeUpdate(sql);
			
			sql = "CREATE TABLE CurrentOrder ("
					+ "order_id VARCHAR(255) NOT NULL,"
					+ "robot_id VARCHAR(255) NOT NULL,"
					+ "user_id VARCHAR(255) NOT NULL,"
					+ "origin VARCHAR(255) NOT NULL,"
					+ "destination VARCHAR(255) NOT NULL,"
					+ "PRIMARY KEY (base_id),"
					+ "FOREIGN KEY (robot_id) REFERENCES Robot(robot_id),"
					+ "FOREIGN KEY (user_id) REFERENCES Users(user_id)"
					+ ")";
			statement.executeUpdate(sql);
			
			sql = "CREATE TABLE CurrentOrder ("
					+ "order_id VARCHAR(255) NOT NULL,"
					+ "robot_id VARCHAR(255) NOT NULL,"
					+ "user_id VARCHAR(255) NOT NULL,"
					+ "orderStatus VARCHAR(255) NOT NULL,"
					+ "origin VARCHAR(255) NOT NULL,"
					+ "PRIMARY KEY (base_id),"
					+ "FOREIGN KEY (robot_id) REFERENCES Robot(robot_id),"
					+ "FOREIGN KEY (user_id) REFERENCES Users(user_id)"
					+ ")";
			statement.executeUpdate(sql);
			
			sql = "CREATE TABLE history ("
					+ "user_id VARCHAR(255) NOT NULL,"
					+ "item_id VARCHAR(255) NOT NULL,"
					+ "last_favor_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
					+ "PRIMARY KEY (user_id, item_id),"
					+ "FOREIGN KEY (user_id) REFERENCES users(user_id),"
					+ "FOREIGN KEY (item_id) REFERENCES items(item_id)"
					+ ")";
			statement.executeUpdate(sql);
			
			// Step 4: insert fake user 1111/3229c1097c00d497a0fd282d586be050
			sql = "INSERT INTO users VALUES('1111', '3229c1097c00d497a0fd282d586be050', 'John', 'Smith')";
			statement.executeUpdate(sql);
			
			conn.close();
			System.out.println("Import done successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
