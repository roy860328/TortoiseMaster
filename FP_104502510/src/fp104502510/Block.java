package fp104502510;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Block {
	public ImageIcon nemo = new ImageIcon("nemo.png");
	public Image nemopicture;
	public ImageIcon block = new ImageIcon("block.png");
	public Image blockpicture;
	
	public static boolean ifontheblock = false;
	public static boolean ifhittheblock = false;
	
	Block(){
		blockpicture = block.getImage();
	}
	
	public Image blockimage(){
		return blockpicture;
	}
	
	public void iftoachblock(){
		for(int i=0;i<Map.blocknumber;i++){
			if(Map.blockxy[0][i] - Paint.xstone < 340 && Map.blockxy[0][i] - Paint.xstone +30 > 300 
					&& Map.blockxy[1][i] >= Paint.y +55 && Paint.y +60 >= Map.blockxy[1][i] && ifontheblock){
				//Paint.dx = -1;
				if(GUI.jump){
					ifontheblock = false;
					GUI.yup = 24;
					GUI.cut = 2;
				}
				break;
			}else if(Map.blockxy[0][i] - Paint.xstone < 340 && Map.blockxy[0][i] - Paint.xstone +30 > 300 
					&& Map.blockxy[1][i] >= Paint.y +55 && Paint.y +60 >= Map.blockxy[1][i]&& GUI.cut == -2){
				//Paint.dx = -1;
				GUI.jump = false;
				ifontheblock = true;
				break;
			}else if(Map.blockxy[0][i] - Paint.xstone < 340 && Map.blockxy[0][i] - Paint.xstone +50 > 300 
					&& Map.blockxy[1][i] + 40>= Paint.y && Map.blockxy[1][i] <= Paint.y  && !ifontheblock){
				ifhittheblock = true;
				GUI.yup = 24;
				break;
			}else if(Map.blockxy[0][i] - Paint.xstone < 350 && Map.blockxy[0][i] - Paint.xstone +50 > 290){
				
				ifontheblock = false;
				ifhittheblock = false;
			}
		}
	}
}
