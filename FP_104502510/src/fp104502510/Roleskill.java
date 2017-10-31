package fp104502510;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Roleskill {
	public ImageIcon darts = new ImageIcon("飛鏢.png");
	public Image dartspicture;
	public ImageIcon darts2 = new ImageIcon("飛鏢2.png");
	public Image dart2spicture;
	int time = 0;	//飛鏢動畫改變速率
	static int dartx = 360;	//飛鏢的X
	static int darty = 400;	//飛鏢的Y
	
	public static boolean ifattack = false;
	
	Roleskill(){
		dartspicture = darts.getImage();
		dart2spicture = darts2.getImage();
	}
	
	public Image attack(){
		time++;
		if(time<=15){
			return dartspicture;
		}else{
			if(time == 30)time = 0;
			return dart2spicture;
		}
	}
	public int getdartx(){
		if(Paint.R_or_L){
			dartx = dartx + 1;
			if(dartx - 300 > 400){
				ifattack = false;
				dartx = 360;
			}
		}else{
			dartx = dartx - 1;
			if(dartx + 300 < 400){
				ifattack = false;
				dartx = 360;
			}
		}
		return dartx;
	}
	public int getdarty(){
		darty = Paint.y;
		return darty;
	}
}
