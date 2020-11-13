package Mole.Game;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

class QuestionFrame extends JFrame { // 도움말 버튼을 클릭했을 때 나오는 프레임
	private QuestionBG questionBG = new QuestionBG();
	private JButton back;
	private JPanel board;
	private JLabel label1;
	private JTabbedPane tab;
	private JLabel lab1;

	ImageIcon back_img = new ImageIcon("img/back.png");
	ImageIcon back1_img = new ImageIcon("img/back1.png");

	QuestionFrame() {
		CustomCursor();
		setTitle("도움말"); // 타이틀
		setSize(800, 600); // 프레임의 크기
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrame이 정상적으로 종료되게 함.

		questionBG.setLayout(null);

		// 아이콘 이미지 설정
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image icon = kit.getImage("img/moleicon.png");
		setIconImage(icon);
		
		// 폰트 설정
		Font wr = new Font("HY견고딕", Font.PLAIN, 18); 

		
		
		tab = new JTabbedPane(); // 생성
		tab.setBounds(50, 50, 700, 450);
		JTextArea p1 = new JTextArea(); // 패널 1 - 공용 규칙 및 설명
		p1.setEditable(false);
		p1.setFont(wr);
		try{
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream("lib/playrule.txt"),"UTF8"));
            String line = "";
            line.getBytes(StandardCharsets.UTF_8);
            while((line = bufReader.readLine()) != null) { //readLine()은 끝에 개행문자를 읽지 않는다. 
                p1.append(line + "\n");
            }
                       
            bufReader.close();
        }catch (Exception e) { // FileNotFoundException, IOException
        	e.printStackTrace();
        }
		
		JTextArea p2 = new JTextArea(); // 패널 2 - 인간 조작 및 아이템
		p2.setEditable(false);
		p2.setFont(wr);
		try{
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream("lib/humanrule.txt"),"UTF8"));
            String line = "";
            while((line = bufReader.readLine()) != null) { //readLine()은 끝에 개행문자를 읽지 않는다.
                p2.append(line + "\n");
            }
                        
            bufReader.close();
        }catch (Exception e) { // FileNotFoundException, IOException
        	e.printStackTrace();
        }
		
		JTextArea p3 = new JTextArea(); // 패널 3 - 두더지 조작 및 아이템
		p3.setEditable(false);
		p3.setFont(wr);
		try{
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream("lib/molerule.txt"),"UTF8"));
            String line = "";
            line.getBytes(StandardCharsets.UTF_8);
            while((line = bufReader.readLine()) != null) { //readLine()은 끝에 개행문자를 읽지 않는다. 
                p3.append(line + "\n");
            }
                       
            bufReader.close();
        }catch (Exception e) { // FileNotFoundException, IOException
        	e.printStackTrace();
        }
		
		JTextArea p4 = new JTextArea(); // 패널 4 - etc(여유분)
		

		tab.addTab("공통",p1);
		tab.addTab("인간",p2);
		tab.addTab("두더지",p3);
		tab.addTab("ETC",p4);
		
		questionBG.add(tab);

		back = new JButton(back_img);
		back.setBorderPainted(false);
		back.setFocusPainted(false);
		back.setContentAreaFilled(false);
		back.setBounds(720, 0, 50, 50);
		back.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				back.setIcon(back1_img);
			}

			public void mouseExited(MouseEvent e) {
				back.setIcon(back_img);
			}

			public void mousePressed(MouseEvent e) {
				System.out.println("도움말 닫기");
				setVisible(false);
				new MainFrame();
			}
		});
		questionBG.add(back);

		add(questionBG);
		setVisible(true);
	}

	public void CustomCursor() { // 커스텀 커서(마우스 커서)

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image cursorimage = tk.getImage("img/cropcursor.png");
		Point point = new Point(20, 20);
		Cursor cursor = tk.createCustomCursor(cursorimage, point, "crop");
		questionBG.setCursor(cursor);
	}

	class QuestionBG extends JPanel {
		private BufferedImage backs;

		public QuestionBG() {
			try {
				backs = ImageIO.read(new File("img/threemoles.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backs, 0, 0, null);
		}
	}
}