package fp104502510;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Enemy {
	
	public ImageIcon good = new ImageIcon("�n�δ�.png");
	public Image goodpicture;
	public ImageIcon goodblood = new ImageIcon("�n�δμQ��.png");
	public Image goodbloodpicture;
	public ImageIcon good2 = new ImageIcon("�n�δ�2.png");
	public Image goodpicture2;
	public ImageIcon goodblood2 = new ImageIcon("�n�δμQ��2.png");
	public Image goodbloodpicture2;
	int xright = 1;	//�Ǫ������t�v
	int xleft = -1;
	int[] time;	//����Ǫ������t��
	static int[] test;	//�ΨӬ����C�@�өǪ��ҭn�樫���Z��
	public static boolean ifenemytoachdart = false;	//�p�G�Z���I��ĤH
	public static boolean[] enemyR_or_L;	//�C�ӼĤH��������V
	public static int[] enemyblood;	//�]�w�ĤH���
	int blood = 0;
	Enemy(){
		goodpicture = good.getImage();
		goodbloodpicture = goodblood.getImage();
		goodpicture2 = good2.getImage();
		goodbloodpicture2 = goodblood2.getImage();
		
		test = new int[Map.countenemy];
		time = new int[Map.countenemy];
		enemyR_or_L = new boolean[Map.countenemy];
		enemyblood = new int[Map.countenemy];
		
		for(int i = 0;i<Map.countenemy;i++){
			enemyblood[i] = 100 + blood;
			blood = blood +50;
		}
	}
	public Image good(int i){
		if(Roleskill.ifattack)ifdarttouchenemy(i);
		if(ifenemytoachdart && !enemyR_or_L[i]){
			return goodbloodpicture;
		}else if(ifenemytoachdart && enemyR_or_L[i]){
			return goodbloodpicture2;
		}else if(enemyR_or_L[i]){
			return goodpicture2;
		}
		else return goodpicture;
	}
	public int enemyrun(int i){
		time[i]++;
		if(time[i]%3 == 0){
			for(int j=0;j<Map.countr5-1;j++){
				if(Map.enemyxy[0][i] != -10000){
					if(Map.betweenenemy[j]+10 < Map.enemyxy[0][i] && Map.enemyxy[0][i] < Map.betweenenemy[j+1]-75
							&& test[i] >=0){
						enemyR_or_L[i] = true;
						Map.enemyxy[0][i] = Map.enemyxy[0][i] + xright;
						test[i]++;
						if(Map.enemyxy[0][i] == Map.betweenenemy[j+1]-1-75){
							test[i] = test[i]*-1;
						}
					}else if(Map.betweenenemy[j]+10 < Map.enemyxy[0][i] && Map.enemyxy[0][i] < Map.betweenenemy[j+1]-75 
							&& test[i] <0){
						enemyR_or_L[i] = false;
						Map.enemyxy[0][i] = Map.enemyxy[0][i] + xleft;
						test[i]++;
						
					}
				}
				
			}
			time[i]=0;
		}
		return Map.enemyxy[0][i] - Paint.xstone;
	}
	public void ifdarttouchenemy(int i){
		if(Map.enemyxy[0][i] - Paint.xstone <= Roleskill.dartx +40 && 
				Map.enemyxy[0][i] - Paint.xstone >= Roleskill.dartx -70 &&
				Map.enemyxy[1][i] <= Roleskill.darty +50 &&
				Map.enemyxy[1][i] >= Roleskill.darty -50){
			enemyblood[i]--;
			ifenemytoachdart = true;
			if(enemyblood[i]==0){	//�ĤH���`
				Map.enemyxy[0][i] = -10000;
			}
		}else{
			ifenemytoachdart = false;
		}
	}
}
