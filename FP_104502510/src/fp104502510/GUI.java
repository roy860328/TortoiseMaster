package fp104502510;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI extends JFrame implements ActionListener , KeyListener{

	public static GUI gui;
	public Paint paint;
	public JPanel JP = new JPanel();
	JButton button = new JButton("開始遊戲");	
	public ImageIcon background2 = new ImageIcon("地形.png"); JLabel label = new JLabel();
	public ImageIcon background1 = new ImageIcon("封面.png");
	//
	public ImageIcon tortuleright = new ImageIcon("tortule.png"); JLabel tortule = new JLabel();
	public ImageIcon tortuleleft = new ImageIcon("tortuleleft.png");
	//
	int x =250;int y = 400;int i=10;
	public final int yfinal = 400;
	public static boolean start = false;	//是否按下開始遊戲
	public static boolean jump = false;	//是否按鍵盤的上
	public static boolean NEVERDIE = false;
	static int yup = 24;	//彈跳距離
	static int cut = 2;	//彈跳遞減速率
	static int down = 0;
	Timer time = new Timer(50 , this);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.遊戲還未開始畫面
	 */
	public GUI() {
		setTitle("忍者龜");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		setResizable(true);
		setFocusable(true);	//??
		
		paint = new Paint(this);
		JP.add(paint);
		
		this.addKeyListener(this);
		button.addActionListener(this);
		button.addKeyListener(this);
		button.setBounds(459, 271, 99, 27);
		button.setFocusable(true);//?
		add(button);	//比add(label)先
		setFocusable(true);	//?
		tortule.setIcon(tortuleright);
		tortule.setBounds(x, y, 60, 75);
		add(tortule);
		tortule.setVisible(false);
		label.setIcon(background1);
		add(label);
		time.start();
	}
	//遊戲開始畫面
	public void startgame(){
		//label.setIcon(background2);
		label.setVisible(false);
		button.setVisible(false);
		tortule.setVisible(false);
		add(JP);
	}
	public void repaintt(){
		paint.repaint();
		//label.setIcon(background2);
		//add(JP);
		revalidate();//不用拉畫面   沒用JP.revalidate();paint.revalidate();
		repaint();	//有用
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			start =true;
			startgame();
		}
		if(e.getKeyCode() == KeyEvent.VK_UP){
			if(!Tortule.tortuledie){
				if(!jump)jump=true;
				y = y-10;
				tortule.setBounds(x, y, 60, 75);
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			if(!Tortule.tortuledie){
				if(Tortule.down){
					Tortule.down = false;
					//paint.y = 400;
				}
				else{
					Tortule.down = true;
					//paint.y = 450;
				}
				y = y+10;
				tortule.setBounds(x, y, 60, 75);
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			tortule.setIcon(tortuleleft);
			if(!Tortule.tortuledie){
				Tortule.walk = true;
				x = x-10;
				if(!Tortule.tortuledie)paint.setdx(1);
				else paint.setdx(0);
				paint.R_or_L = false;
				tortule.setBounds(x, y, 60, 75);
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			tortule.setIcon(tortuleright);
			if(!Tortule.tortuledie){
				Tortule.walk = true;
				if(paint.xstone == -1)paint.xstone = 0;
				x = x+10;
				if(!Tortule.tortuledie)paint.setdx(-1);
				else paint.setdx(0);
				paint.R_or_L = true;
				tortule.setBounds(x, y, 60, 75);
			}
		}
		if(e.getKeyChar() == KeyEvent.VK_SPACE){
			Roleskill.ifattack = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_P){
			if(start){
				start = false;
				time.stop();
				System.out.print(start);
			}
			else{
				start = true;
				time.start();
			}
		}
		if(e.getKeyChar() == KeyEvent.VK_D){
			Tortule.tortuledie = true;
		}
		if(e.getKeyChar() == KeyEvent.VK_Q){
			if(NEVERDIE){
				NEVERDIE = false;
			}else{
				NEVERDIE = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			Tortule.walk = false;
			paint.dx = 0;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			Tortule.walk = false;
			paint.dx = 0;
		}
		if(e.getKeyChar() == KeyEvent.VK_SPACE){
			//Roleskill.ifattack = false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(start){
			repaintt();
		}
		if(jump){
			jump();
		}else if(!Tortule.tortuledie && Paint.y != 400 && !Block.ifontheblock){
			if(cut>0)cut = -2;
			
			paint.y = paint.y + down;
			down = down + 2;
			if(Paint.y >= 400){
				Paint.y = 400;
				Block.ifhittheblock = false;
				jump = false;
				cut = 2;
			}
		}else{
			down = 0;
		}
		// TODO Auto-generated method stub
		/*if(x>940 || x<0){
			i = -i;
		}
		x = x +i;
		tortule.setBounds(x, y, 60, 75);
		*/
		if(e.getSource() == button){
			start =true;
			startgame();
		}
	}

	public void jump(){
		if(yup>0 && cut>0 && !Tortule.tortuledie && !Block.ifhittheblock){
			yup = yup - 2;
			paint.y = paint.y - yup;
			down = 0;
			if(yup == 0){
				jump = false;
				yup = 24;
			}
		}else{
			jump = false;
		}
	}
}
