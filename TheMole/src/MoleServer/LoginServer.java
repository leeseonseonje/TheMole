package MoleServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import DB.DBConnection;
import io.netty.channel.ChannelHandlerContext;

public class LoginServer {
//	private String id, pw;
//	private ChannelHandlerContext ctx;
	public LoginServer(String id, String pw, ChannelHandlerContext ctx) {
		System.out.println("완료됨");
	/*	this.id = id;
		this.pw = pw;
		this.ctx = ctx;*/
	//	boolean result = false;
		String password = null;
		try {
			Connection con = DBConnection.makeConnection(); // DB연결
			PreparedStatement pstmt = con.prepareStatement("select *from gamer where id like ?");
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();;
			while (rs.next()) { // 데이터베이스 테이블 내용 돌림 확인
				password = rs.getString("passwords");
			}
			
			if(pw.equals(password))
				ctx.writeAndFlush("LOGIN");
			else
				ctx.writeAndFlush("LOGINFAIL");
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
