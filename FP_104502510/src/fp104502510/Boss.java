package fp104502510;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Boss {
	public ImageIcon boss = new ImageIcon("boss.png");
	public Image bosspicture;
	public ImageIcon bossattack = new ImageIcon("bossattack.png");
	public Image bossattackpicture;
	
	public static int bossblood = 1000;
	public static int circle = 0;
	public static int bossR = 80;
	int dx = 0;
	int dy = 0;
	int storey = 1000;
	
	int time = 0;	//延遲加農砲
	int time2 = 0;	//延遲飛彈Y
	int time3 = 0;	//延遲飛彈X
	int time4 = 0;  //加農砲持續時間
	public boolean ifdxstop = false;
	public boolean ifdystop = false;
	public static boolean bossdie = false;
	Boss(){
		bosspicture = boss.getImage();
		bossattackpicture = bossattack.getImage();
	}
	
	public int bossx(){
		return Map.bossxy[0][0];
	}
	public int bossy(){
		return Map.bossxy[1][0];
	}
	public Image bossimage(){
		return bosspicture;
	}
	public Image bossattackimage(){
		return bossattackpicture;
	}
	public int bossblood(){
		return bossblood;
	}
	public void attack(){	//加農砲
		if(bossblood < 400 && circle <100 && bossx() - Paint.xstone -80 <700){
			time++;
			if(circle < 100 && time%2 == 0){
				circle++;
				time = 0;
			}
		}else if(circle == 100 && bossx() - Paint.xstone -80 <700){
			time4++;
			if(time4 > 50){
				time4 = 0;
				bossR--;
				bossR--;
			}
		}
		if(Paint.y > bossy() +100 && Paint.y < bossy() +100 + bossR 
				&& bossx() - Paint.xstone -80 <700 && bossR != 0 && bossblood<400 && circle == 100){
			Tortule.tortuledie = true;
		}
	}
	public void ifdarttoachboss(){
		if(Roleskill.ifattack){
			if( bossx() - Paint.xstone <= Roleskill.dartx +40 && 
					bossx() - Paint.xstone +100 >= Roleskill.dartx &&
					bossy() <= Roleskill.darty + 40 &&
					bossy()+200 >= Roleskill.darty ){
				bossblood--;
				if(bossblood==0){	//敵人死亡
					bossdie = true;
				}
			}
		}
	}
	public int attackx(){	//康達姆飛詮
		if(bossx() - Paint.xstone -50 <=1000){
			ifattackstop();
			time3++;
			if(bossx() - Paint.xstone -50 -dx >300  )dx++;
		}
		return bossx() - Paint.xstone -50 -dx;
	}
	public int attacky(){	//康達姆飛詮
		if(bossx() - Paint.xstone -50 <=1000){
			ifattackstop();
			if(storey == 1000){
				storey = Paint.y - bossy();
				
			}
			time2++;
			if(dy != storey && time2%3 == 0){
				if(storey >= dy){
					dy++;
				}else if(storey <=dy){
					dy--;
				}
				time2 = 0;
			}
		}
		return bossy() + dy;
	}
	public void ifattackstop(){	//康達姆飛詮
		if(dy == storey){
			ifdystop = true;
		}else{
			ifdystop = false;
		}
		if(bossx() - Paint.xstone -50 -dx <= 300){
			ifdxstop = true;
		}else{
			ifdxstop = false;
		}
		if(ifdystop && ifdxstop){
			dx = 0;
			dy = 0;
			storey = 1000;
		}
	}
	public void ifattacktoachtortule(){
		if(bossx() - Paint.xstone -50 -dx -40 <=300 
				&& bossy() + dy + 20 >= Paint.y && bossy() + dy <= Paint.y +50 && !Tortule.down && !GUI.NEVERDIE){
			dx = 0;
			dy = 0;
			storey = 1000;
			Tortule.tortuledie = true;
		}
	}
}
