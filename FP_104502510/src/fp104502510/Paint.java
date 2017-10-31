package fp104502510;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;


public class Paint extends JPanel implements Runnable{
	
	public ImageIcon background2 = new ImageIcon("地形.png"); JLabel label = new JLabel();
	public ImageIcon background1 = new ImageIcon("初始畫面.jpg");
	//
	public Tortule tortule;
	public ImageIcon tortuleright = new ImageIcon("tortule.png"); 
	public ImageIcon tortuleleft = new ImageIcon("tortuleleft.png");
	
	public BufferedImage b2;public BufferedImage tortuler;public BufferedImage tortulel;
	//
	public ImageIcon stone = new ImageIcon("石頭貓.png");
	public Image stonepicture;
	public ImageIcon trap = new ImageIcon("刺刺der.png");
	public Image trappicture;
	//
	public Map map;
	public Enemy enemy;
	public Roleskill roleskill;
	public Block block;
	public Boss boss;
	//
	public static boolean R_or_L =true;	//判斷烏龜面向右邊還左邊

	public static int x = 0;public static int dx = 0;public static int y = 400;	//x和x2用來紀錄背景以無線滾動 Y為記錄烏龜高度
	public static int x2 = 1000;				//dx為背景滾動速率
	public static int xstone = -1;public static int xtrap = 0;	//紀錄起始點如果烏龜往左走方便從新定位

	int time = 0;	//背景自動往右時的控制速率
	private final int pause = 3;
	private Thread thread;
	Font font = new Font("OK", 1, 20);
	Font font2 = new Font("OK", 1, 30);
	Font font3 = new Font("OK", 1, 100);
	int dietime = 0;
	
	Paint(GUI gui){
		this.setVisible(true);
		tortule = new Tortule();
		
		int width = (int) (background2.getIconWidth());
		int height = (int) (background2.getIconHeight());
		this.setPreferredSize(new Dimension(width, height));
		this.b2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.b2.createGraphics().drawImage(background2.getImage(), 0, 0, width, height, null);
		
		stonepicture = stone.getImage();
		trappicture = trap.getImage();
		
		tortule = new Tortule();
		thread = new Thread(this);
		thread.start();
		
		map = new Map();
		enemy = new Enemy();
		roleskill = new Roleskill();
		block = new Block();
		boss = new Boss();
	}
	
	public void setdx(int dx){	//地圖位移量
		this.dx = dx;
	}
	public void setbackgrounds(){//設定背景位置
		if(xstone>=0 ){	//限制一開始只能往右走
			xstone = xstone - dx;
			xtrap = xtrap -dx;
			x = x+dx;	
			x2 = x2+dx;
		}
		time++;
		/*if(!Tortule.tortuledie && time % 10==0){
			xstone = xstone +1;
			xtrap = xtrap +1;
			x = x-1;	
			x2 = x2-1;
		}*/
		if(x<-1000 || x2<-1000){
			x = 0;
			x2 = 1000;
		}
		if(dx != 0){
			if(x == -1000 && x2 == 0 && dx == -1){	//循環背景
				x = 1000;
			}else if(x2 == -1000 && x == 0 && dx == -1){
				x2 = 1000;
			}else if(x2 == 1000 && x == 0 && dx == 1){
				x2 = -1000;
			}else if(x  == 1000 && x2 == 0 && dx == 1){
				x = -1000;
			}
		}else{
			if(x == -1000 && x2 == 0){	//循環背景
				x = 1000;
			}else if(x2 == -1000 && x == 0){
				x2 = 1000;
			}else if(x2 == 1000 && x == 0 ){
				x2 = -1000;
			}else if(x  == 1000 && x2 == 0){
				x = -1000;
			}
		}
		
		
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setbackgrounds();
		block.iftoachblock();
		boss.attack();
		boss.ifdarttoachboss();
		boss.ifattacktoachtortule();
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(b2, null, x, 0);		//畫背景
		g2d.drawImage(b2, null, x2, 0);
		g2d.drawImage(stonepicture, 0 - xstone, 450, null);
		
		for(int i=0;i<Map.countr;i++){
			g2d.drawImage(trappicture,  Map.trapxy[0][i]*100 - xstone, Map.trapxy[1][i]*92, null);
		}
		if(block.ifontheblock){
			g2d.drawImage(tortule.gettortuleImage(), 300, y, null);//畫烏龜
		}else if(block.ifhittheblock){
			g2d.drawImage(tortule.gettortuleImage(), 300, y, null);//畫烏龜
		}else{
			g2d.drawImage(tortule.gettortuleImage(), 300, y, null);//畫烏龜
		}
		//System.out.println(xstone);
		for(int i=0;i<Map.countenemy;i++){
			g2d.drawImage(enemy.good(i),  enemy.enemyrun(i)+25, Map.enemyxy[1][i], null);
			g2d.setColor(Color.green);
			g2d.fillRect(enemy.enemyrun(i)+25, Map.enemyxy[1][i]+95, Enemy.enemyblood[i], 10);
		}
		for(int i=0;i<Map.countblock;i++){
			g2d.drawImage(block.blockpicture,  Map.blockxy[0][i] - xstone, Map.blockxy[1][i], null);
		}
		if(roleskill.ifattack){
			g2d.drawImage(roleskill.attack(), roleskill.getdartx(), roleskill.getdarty(), null);
		}
		//
		if(!Boss.bossdie){
			g2d.drawImage(boss.bosspicture, boss.bossx() - xstone, boss.bossy(), null);
			g2d.fillRect( boss.bossx() - xstone, boss.bossy()+ 300, boss.bossblood()/5, 10);
			g2d.drawImage(boss.bossattackpicture, boss.attackx(), boss.attacky(), null);
			
			g2d.setColor(Color.yellow);
			if(boss.circle<100){
				g2d.fillOval(boss.bossx() - xstone -80, boss.bossy() +50 , boss.circle, boss.circle);
			}else{
				g2d.fillRect(0, boss.bossy() +100, boss.bossx() - xstone -30, boss.bossR);
				if(boss.bossR == 0){
					boss.bossR = 80;
					boss.circle = 0;
				}
			}
			
		}else{
			g2d.setColor(Color.RED);
			g2d.setFont(font3);
			g2d.drawString("WIN!!", 350, 300);
		}
		//
		g2d.setColor(Color.blue);
		g2d.fillRect(70, 10, Tortule.power, 20);
		g2d.setColor(Color.red);
		g2d.setFont(font);
		g2d.drawString("Power", 10, 30);
		g2d.setColor(Color.black);
		g2d.setFont(font2);
		g2d.drawString("Die Time : " + dietime , 800, 50);
		//repaint();
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			if(GUI.start)repaint();
			if(Tortule.tortuledie && !GUI.NEVERDIE){
				restartgame();
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				}
				dietime++;
				x = 0;
				x2 = 1000;
				dx = 0;
				y = 400;
				xstone = -1;
				xtrap = 0;
				GUI.yup = 24;
				GUI.cut = 2;
				Roleskill.dartx = 360;
				Tortule.power = Tortule.setpower;
				Tortule.tortuledie = false;
				R_or_L = true;
				Tortule.walk = false;
				Tortule.down = false;
				GUI.jump = false;
				Roleskill.ifattack = false;
				Block.ifhittheblock = false;
				Block.ifontheblock = false;
				Boss.bossblood = 1000;
				Boss.bossdie = false;
				Boss.circle = 0;
				Boss.bossR = 80;
				Boss.circle = 0;
				int k = 0;
				int blood = 50;
				for(int i = 0; i<6; i++){
					for(int j = 0; j<Map.charmap[0].length; j++){
						if(Map.charmap[i][j] == 'e'){
							Map.enemyxy[0][k] = j*100;
							Map.enemyxy[1][k] = i*80;
							Enemy.test[k] = 0;
							Enemy.enemyR_or_L[k] = true;
							k++;
							
						}
					}
					Enemy.enemyblood[i] = 100 + blood;
					blood = blood +50;
				}
			}
			try {
				Thread.sleep(pause);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}	
		}
	}

	private void restartgame() {
		// TODO Auto-generated method stub
		
	}
}
