package hr_db;

import java.sql.*;

public class DB_Connection {
	public static void main(String[] args) {
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.getConnection("jdbc:mysql://localhost:3307/hr_recruitment_tracker", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
            return;
	}

}
}