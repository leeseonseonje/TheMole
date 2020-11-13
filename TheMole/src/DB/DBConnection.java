package DB;

import java.sql.*;

public class DBConnection {
	
	public DBConnection() {
		try {
		Connection con = makeConnection();
		Statement stmt = con.createStatement();
		
		//stmt.executeUpdate("delete from users where id = \"\";"); // ���ִ� ��

		ResultSet rs = stmt.executeQuery("SELECT * FROM gamer");
		while(rs.next()) {
			String id = rs.getString("id");
			String password = rs.getString("passwords");
			System.out.println(id + " " + password);
		}
		rs.close();

		stmt.close();
		con.close();
		} catch(Exception e) { System.out.println("������ ���̽� ���� : " + e.getMessage()); }
		
	}
	
	public static Connection makeConnection() {

		String url = "jdbc:mysql://localhost:3306/user?serverTimezone=Asia/Seoul";
		// sqldb�� database �̸�, serverTimezone�� �ð��� ����
		String id = "root";
		String password = "root";
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("����̹� ���� ����");
			con = DriverManager.getConnection(url,id,password);
			System.out.println("�����ͺ��̽� ���� ����");
		} catch(Exception e) {
			System.out.println("�����ͺ��̽� ���� ���� : " + e.getMessage());
		}
		return con;
	}
	public static void main(String arg[]) {
		new DBConnection();
	}
}
