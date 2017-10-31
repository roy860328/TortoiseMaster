package fp104502510;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Map {
	String[] map = new String[7];
	static char[][] charmap = new char[9][];
	static int trapnumber = 0;	//�p����������ƶq
	static int enemynumber = 0;	//�p��Ǫ��ƶq
	static int blocknumber = 0;	//�p���l�ƶq
	static int[][] trapxy = new int[2][];	//�p�⳴����X�MY
	static int[][] enemyxy = new int[2][];	//�p��Ǫ���X�MY
	static int[] betweenenemy;	//�p��̤U�h��������m
	static int[][] blockxy = new int[2][];	//�p���l��X�MY
	static int[][] bossxy = new int[2][1];	//�p��boss��X�MY
	//
	static int countr=0;	//���������H�s�Jtrapxy
	//
	static int countenemy = 0;	//�����Ǫ��H�s�Jenemyxy
	static int countblock = 0;
	
	static int countr5=0;	//�p��̤U�h�������Ӽ�
	
	Map(){
		try {
			// FileReader���w�]�۹���|�O������JAVA project, �ҥHshort.txt�O��bA6_Example���U
			FileReader fr = new FileReader("map.txt");
			BufferedReader br = new BufferedReader(fr);
			String str,str2;
			int i=0;
			while( (str = br.readLine()) != null){
				//str = str.trim().split(" ");
				if(i == 0)charmap = new char[9][str.length()];
				map[i] = str;
				for(int j=0;j<str.length();j++){
					charmap[i][j] = str.charAt(j);
					if(charmap[i][j] == 't'){
						trapnumber++;
						if(i==5)countr5++;
					}else if(charmap[i][j] == 'e'){
						enemynumber++;
					}else if(charmap[i][j] == 'b'){
						blocknumber++;
					}
					System.out.print(charmap[i][j]);
				}
				System.out.println(str.length());
				i++;
				
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		counttrapenemy();
	}
	//
	public void counttrapenemy(){
		trapxy = new int[2][trapnumber];
		enemyxy = new int[2][enemynumber];
		betweenenemy = new int[countr5];
		blockxy = new int[2][blocknumber];
		int count = 0;
		for(int i = 0; i<6; i++){
			for(int j = 0; j<charmap[0].length; j++){
				if(charmap[i][j] == 't'){
					trapxy[0][countr] = j;
					trapxy[1][countr] = i;
					if(i==5){
						betweenenemy[count]=j*100;
						count++;
					}
					countr++;
				}else if(charmap[i][j] == 'e'){
					enemyxy[0][countenemy] = j*100;
					enemyxy[1][countenemy] = i*80;
					countenemy++;
				}else if(charmap[i][j] == 'b'){
					blockxy[0][countblock] = j*90;
					blockxy[1][countblock] = i*85;
					countblock++;
				}else if(charmap[i][j] == 'k'){
					bossxy[0][0] = j*100;
					bossxy[1][0] = i*92;
				}
			}
		}
	}
	//
}
