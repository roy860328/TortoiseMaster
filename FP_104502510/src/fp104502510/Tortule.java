package fp104502510;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Tortule {
	
	public ImageIcon tortuleright = new ImageIcon("tortule.png"); 
	public ImageIcon tortuleleft = new ImageIcon("tortuleleft.png");
	//
	public ImageIcon tortulerightwalk = new ImageIcon("tortulewalk.png"); 
	public ImageIcon tortuleleftwalk = new ImageIcon("tortuleleftwalk.png");
	
	public Image tortulerw;
	public Image tortulelw;
	//
	public ImageIcon tortulerightdie = new ImageIcon("tortuledie.png"); 
	public ImageIcon tortuleleftdie = new ImageIcon("tortuleleftdie.png");
	
	public Image tortulerdie;
	public Image tortuleldie;
	
	//
	
	public ImageIcon tortulerightdown = new ImageIcon("down.png"); 
	public ImageIcon tortuleleftdown = new ImageIcon("downleft.png");
	
	public Image tortulerdown;
	public Image tortuleldown;
	
	//
	public BufferedImage tortuler;public BufferedImage tortulel;
	
	public static boolean walk = false;	//判斷是否正在走路 用來做烏龜動畫
	public static boolean tortuledie = false;	//烏龜是否死了
	public static boolean down = false;	//烏龜是否有變龜殼而無敵
	
	int time = 0;	//控制烏龜動畫改變速率
	static int power = 500; static int setpower = 500;	//烏龜無敵的魔力
	
	Tortule(){
		int width1 = (int) (tortuleleft.getIconWidth());
		int height1 = (int) (tortuleleft.getIconHeight());
		this.tortulel = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_ARGB);
		this.tortulel.createGraphics().drawImage(tortuleleft.getImage(), 0, 0, width1, height1, null);
		
		this.tortuler = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_ARGB);
		this.tortuler.createGraphics().drawImage(tortuleright.getImage(), 0, 0, width1, height1, null);
		//
		tortulerw = tortulerightwalk.getImage();
		tortulelw = tortuleleftwalk.getImage();
		
		tortulerdie = tortulerightdie.getImage();
		tortuleldie = tortuleleftdie.getImage();
		
		tortulerdown = tortulerightdown.getImage();
		tortuleldown = tortuleleftdown.getImage();
	}
	
	public Image gettortuleImage(){//畫烏龜
		if(power == 0)down = false;
		iftouchtrap();
		iftouchenemy();
		if(!tortuledie){// || Paint.y){	//是否碰到陷阱
			if(walk){
				time++;
				if(down){
					if(power > 0)power = power - 1;
					if(time <= 40){
						if(Paint.R_or_L)return (Image)tortulerdown;
						else return (Image)tortuleldown;
					}else{
						if(time == 80){
							time = 0;
						}
						if(Paint.R_or_L)return tortuleldown;
						else return tortulerdown;
					}
				}
				else if(time <= 20){
					if(power < setpower)power = power + 1;
					if(Paint.R_or_L)return (Image)tortuler;
					else return (Image)tortulel;
				}else{
					if(power < setpower)power = power + 1;
					if(time >= 40){
						time = 0;
					}
					if(Paint.R_or_L)return tortulerw;
					else return tortulelw;
				}
			}else{
				if(down){
					//System.out.println("456");
					if(power > 0)power = power -1;
					if(Paint.R_or_L)return (Image) tortulerdown;
					else return (Image) tortuleldown;
				}
				if(power < setpower)power = power + 1;
				if(Paint.R_or_L)return (Image)tortuler;
				else return (Image)tortulel;
			}
		}else if(down && !tortuledie){
			time++;
			if(power > 0)power = power -1;
			if(time <= 40){
				if(Paint.R_or_L)return (Image)tortulerdown;
				else return (Image)tortuleldown;
			}else{
				if(time == 80){
					time = 0;
				}
				if(Paint.R_or_L)return tortuleldown;
				else return tortulerdown;
			}
		}else{
			Paint.dx = 0;
			if(Paint.R_or_L)return (Image)tortulerdie;
			else return (Image)tortulerdie;
		}
	}
	
	public void iftouchtrap(){
		for(int i = 0; i<Map.trapnumber; i++){
			 if( Map.trapxy[0][i]*100 - Paint.xstone < 350 && Map.trapxy[0][i]*100 +120 - Paint.xstone> 300 
					 && Map.trapxy[1][i]*92 <= Paint.y+90 && Map.trapxy[1][i]*92 + 10 >= Paint.y && !down && !GUI.NEVERDIE){
				 tortuledie = true;
			 }
		}
	}
	public void iftouchenemy(){
		for(int i = 0; i<Map.countenemy; i++){
			if( Map.enemyxy[0][i] - Paint.xstone < 350 && Map.enemyxy[0][i] - Paint.xstone +75 > 300 
					&& Map.enemyxy[1][i] <= Paint.y +70
					&& Map.enemyxy[1][i] + 50 >= Paint.y && !GUI.NEVERDIE){
				tortuledie = true;
			}
		}
	}
}
