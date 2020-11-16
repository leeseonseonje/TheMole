package Mole.Game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DB.DBConnection;
import MoleServer.MoleClient;

public class SignUpForm extends JPanel {
	
	private LoginForm log;
	private JTextField idField; // 가입창 아이디
	private JPasswordField passwordField_1; // 가입창 비밀번호
	private JPasswordField passwordField_2; // 가입창 비밀번호 재입력
	private JButton SignUpButton;
	private JButton backButton;
	private boolean idcheck = false;
	
	public SignUpForm() {
		setBackground(Color.WHITE);
		setBounds(0, 0, 611, 362);
		setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Username");
		lblNewLabel_3.setBounds(352, 37, 95, 15);
		add(lblNewLabel_3);
		
		idField = new JTextField();
		idField.setBounds(352, 73, 201, 21);
		add(idField);
		idField.setColumns(10);
		
		JButton nameCheckbtn = new JButton("Check");
        nameCheckbtn.setBounds(480, 100, 72, 23);
        JLabel checkLabel = new JLabel("");
        checkLabel.setForeground(Color.RED);
        checkLabel.setBounds(420,85, 80, 50);
        add(checkLabel);
        nameCheckbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*try {
                    Connection con = DBConnection.makeConnection();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM gamer");
                    ArrayList<String> idList = new ArrayList<String>();
                    while(rs.next()) {
                        idList.add(rs.getString("id"));
                    }
                    int index = Collections.binarySearch(idList, textField_1.getText());
                    if(index >= 0 )
                        checkLabel.setText("중복");

                    else {
                        checkLabel.setText("중복아님");
                        idcheck = true;
                    }
     
                    rs.close();
                    st.close();
                    
                } catch (Exception a) {
                    System.out.println("데이터 베이스 연결 오류 : " + a.getMessage());
                    a.printStackTrace();
                }*/
            	try {
					MoleClient moleclient = new MoleClient();
					moleclient.future = moleclient.serverChannel.writeAndFlush("[DUPLICATE]" + "," + idField.getText());
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
            }
        });
        add(nameCheckbtn);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(352, 125, 95, 15);
		add(passwordLabel);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(352, 156, 201, 21);
		add(passwordField_1);
		
		JLabel repeatPasswordLabel = new JLabel("Repeat Password");
		repeatPasswordLabel.setBounds(352, 206, 116, 15);
		add(repeatPasswordLabel);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(352, 236, 201, 21);
		add(passwordField_2);
		
		SignUpButton = new JButton("SignUp");
		SignUpButton.setBackground(Color.LIGHT_GRAY);
		SignUpButton.setBounds(352, 300, 97, 23);
		SignUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = idField.getText();
				String password = passwordField_1.getText();
				String passwordcheck = passwordField_2.getText();
				if (password.equals(passwordcheck) && (name != null && password != null)) { // 패스워드가 재확인 패스워드와 일치할시에
					try {
						Connection con = DBConnection.makeConnection(); // DB연결
						Statement st = con.createStatement();
						String s = String.format("INSERT INTO gamer VALUES('%s','%s',0,0,0,1000);", name, password);
						// System.out.println(s); // 확인용
						st.executeUpdate(s);
						JOptionPane.showMessageDialog(log, "you signed up successfully!!!");
						st.close();
						con.close();
					} catch (Exception a) {
						JOptionPane.showMessageDialog(log, "정보가 일치하지 않습니다.\n fail to register");
					}
				}
			}
		});
		add(SignUpButton);
		
		backButton = new JButton("Back");
		backButton.setBackground(Color.LIGHT_GRAY);
		backButton.setBounds(486, 300, 97, 23);
		add(backButton);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(12, 10, 320, 320);
		lblNewLabel_6.setIcon(new ImageIcon(LoginForm.class.getResource("/images/signpages.png")));
		add(lblNewLabel_6);
		
	}
	public JButton getBackButton() {
		return backButton;
	}
}