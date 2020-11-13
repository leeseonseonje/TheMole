package Mole.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import DB.DBConnection;

public class LoginForm extends JFrame {

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			LoginForm window = new LoginForm();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		LoginForm.id = id;
	}

	public static String getSecret() {
		return secret;
	}

	public static void setSecret(String secret) {
		LoginForm.secret = secret;
	}

	private LoginForm log;
	private JTextField username; // �α���â ���̵�
	private JPasswordField passwordField; // �α���â ��й�ȣ
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton nameCheckbtn;
	private JPanel loginPanel;
	private boolean idCheck = false;
	
	private static String id;
	private static String secret;

	public LoginForm() {
		setTitle("Mole Game");
		setSize(627, 401);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ������ �̹��� ����
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image icon = kit.getImage("img/moleicon.png");
		setIconImage(icon);

		loginPanel = new JPanel();
		loginPanel.setBackground(Color.WHITE);
		loginPanel.setBounds(0, 0, 611, 362);
		loginPanel.setLayout(null);

		btnNewButton = new JButton("Login");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(358, 302, 97, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user = username.getText();
				String pass = passwordField.getText();
				boolean result = false;
				//System.out.println(user + " " + pass);
				try {
					Connection con = DBConnection.makeConnection(); // DB����
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM gamer");
					while (rs.next()) { // �����ͺ��̽� ���̺� ���� ���� Ȯ��
						//System.out.println(rs.getString("ID") + " " + rs.getString("PASSWORD"));
						if (user.equals(rs.getString("ID")) && pass.equals(rs.getString("PASSWORDS"))) {
							result = true;
							break;
						}
					}
					if (result) {
						// JOptionPane.showMessageDialog(log, "you are successfully logined");
						id = rs.getString("ID");
						secret = rs.getString("PASSWORDS");
						dispose();
						new MainFrame();
					} else {
						JOptionPane.showMessageDialog(log, "Invalid username or password");
					}
					rs.close();
					st.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		loginPanel.add(btnNewButton);

		btnNewButton_1 = new JButton("SignUp");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(487, 302, 97, 23);
		loginPanel.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("���� ���", Font.BOLD, 54));
		lblNewLabel.setBounds(368, 4, 166, 82);
		loginPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(368, 108, 97, 15);
		loginPanel.add(lblNewLabel_1);

		username = new JTextField();
		username.setBounds(368, 141, 205, 21);
		loginPanel.add(username);
		username.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(368, 199, 97, 15);
		loginPanel.add(lblNewLabel_2);

		passwordField = new JPasswordField();
		passwordField.setBounds(368, 233, 205, 20);
		loginPanel.add(passwordField);

		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setBounds(12, 10, 320, 320);
		lblNewLabel_7.setIcon(new ImageIcon(LoginForm.class.getResource("/images/loginpages.png")));
		loginPanel.add(lblNewLabel_7);

		add(loginPanel);
		setVisible(true);

		SignUpForm sf = new SignUpForm();
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add(sf);
				loginPanel.setVisible(false);
				sf.setVisible(true);
			}
		});
		sf.getBtnNewButton_3().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sf.setVisible(false);
				loginPanel.setVisible(true);
			}
		});
		
		passwordField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String user = username.getText();
					String pass = passwordField.getText();
					boolean result = false;
					//System.out.println(user + " " + pass);
					try {
						Connection con = DBConnection.makeConnection(); // DB����
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM gamer");
						while (rs.next()) { // �����ͺ��̽� ���̺� ���� ���� Ȯ��
							//System.out.println(rs.getString("ID") + " " + rs.getString("PASSWORD"));
							if (user.equals(rs.getString("ID")) && pass.equals(rs.getString("PASSWORDS"))) {
								result = true;
								break;
							}
						}
						if (result) {
							// JOptionPane.showMessageDialog(log, "you are successfully logined");
							id = rs.getString("ID");
							secret = rs.getString("PASSWORDS");
							dispose();
							new MainFrame();
						} else {
							JOptionPane.showMessageDialog(log, "Invalid username or password");
						}
						rs.close();
						st.close();
					} catch (Exception a) {
						a.printStackTrace();
					}
				}
			}
			
		});
	}
}