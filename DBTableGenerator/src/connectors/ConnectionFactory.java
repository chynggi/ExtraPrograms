package connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	//Usage Example: ConnectionFactory.getConnection
	public static Connection getConnection(String DBType,String host,String DBName,int port,String User, String Password)  {	
		Connection conn = null;
		switch(DBType)
		{
		case "Oracle":
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@"+host+":"+port+":"+DBName,
						User,
						Password);
				return conn;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Driver Not Found: 드라이버를 찾을 수 없습니다.");
				return null;
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("URL/USER/PASSWORD ERROR: 주소/유저명/비밀번호가 잘못되었습니다.");
				e.printStackTrace();
			}
			break;
		case "MySQL":
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(
						"jdbc:mysql://"+host+":"+port+":"+DBName,
						User,
						Password);
				return conn;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Driver Not Found: 드라이버를 찾을 수 없습니다.");
				return null;
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("URL/USER/PASSWORD ERROR: 주소/유저명/비밀번호가 잘못되었습니다.");
				e.printStackTrace();
			}
			break;
		case "MariaDB":		
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				conn = DriverManager.getConnection(
						"jdbc:mariadb://"+host+":"+port+":"+DBName,
						User,
						Password);
				return conn;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Driver Not Found: 드라이버를 찾을 수 없습니다.");
				return null;
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("URL/USER/PASSWORD ERROR: 주소/유저명/비밀번호가 잘못되었습니다.");
				e.printStackTrace();
			}
			break;
		
		}
		
		
		
		
		return null;
		
	}
}
