package jdbcExc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectMysql {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/coinfo", "root", "666666");
			Statement stmt = conn.createStatement();
			ResultSet res = stmt
					.executeQuery("select *from company");
			while (res.next()) {
				System.out.println(res.getInt(1)+"\t"+res.getString(2)+"\t"+res.getString(3));
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
