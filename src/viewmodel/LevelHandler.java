/* Saya Muhammad Aditya Hasta Pratama (ILKOM C2) dengan NIM 2000360 
mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah 
Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya 
maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.  */

package viewmodel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import model.Item;
import model.ObjectIDS;
import model.Platform;
import model.Player;
import view.Window;
import view.Menu;

public class LevelHandler {
	
	public double Gravity = 4; // kecepatan jatuh
	public Window w; // memanggil window
        public Menu m = null; // memanggil menu
           
        // memanggil item dalam bentuk list
	public LinkedList<Item> items = new LinkedList<Item>();
	
	public double CameraX, CameraY; // gerakan window game
	public int StartingX=250, StartingY=300; // posisi awal player
	
	// Game Object
	public Player player = null;
        
        // banyaknya 1 loop objek
        public int manyObject = 50;
	
	//Runs when a new level is created
	public LevelHandler(Window w) {
            
                // inisiasi window
		this.w = w;
		
		//Set Random
		Random r = new Random();
		
                // setting player
		player = new Player(w,StartingX,StartingY,32,32);
                
                //loop
		for(int i=0; i<manyObject; i++) {
                    
                    // value random
                    int []ran = {250,300,350,400,450,225,175,200,325,425};
                    
                    // batas kiri
                    items.add(new Platform(ObjectIDS.Floor, 0, (i*500), 10, 500 ,Color.black));

                    //(Position x, Position y, lebar, panjangnya)
                    int seed1 = r.nextInt(10);
                    int seed2 = r.nextInt(10);
                    int seed3 = r.nextInt(10);
                    int seed4 = r.nextInt(10);
                    int seed5 = r.nextInt(10);
                    
                    // mengatur warna , lebar dan tinggi dari rintangan/ dinding pembatas
                    items.add(new Platform(ObjectIDS.Platform, 10, (i*500)+400, 700-ran[seed2], 50 ,Color.gray));
                    items.add(new Platform(ObjectIDS.Platform, 0, (i*500)+500, 700-ran[seed3], 50 ,Color.orange));
                    items.add(new Platform(ObjectIDS.Platform, 0, (i*500)+600, 700-ran[seed4], 50 ,Color.blue));
                    items.add(new Platform(ObjectIDS.Platform, 0, (i*500)+700, 700-ran[seed5], 50 ,Color.pink));
                    items.add(new Platform(ObjectIDS.Platform, 0, (i*500)+800, 700-ran[seed1], 50 ,Color.yellow));
                }
	}
        
        //render
	public void Render(Graphics g) {
                // insiasi kamera
		g.translate(-(int)CameraX, -(int)CameraY);
                
                // render item
		for(Item i: items) {
			i.Render(g);
		}
		player.Render(g);
		g.translate((int)CameraX, (int)CameraY);
	}
	
        // tick (agar kamera bergerak kebawah)
	public void tick() {
		CameraX = 0;
		CameraY = CameraY+1;
		for(Item i:items) {
			i.tick();
		}
                
                // agar player bisa bergerak
		player.tick();
                
                // kondisi jika player melebihi bagian atas , maka gameover (tertinggal)
		if(CameraY >= player.y) {
                    RestartLevel();
		}
                
                // kondisi player paling bawah
                if(CameraY + 600 - player.height*2 < player.y){
                    RestartLevel();
                }
	}
	
        // jika game over
	public void RestartLevel() {
            
                //posisi player dan kamera ke awal
		player.x= StartingX;
		player.y= StartingY;
		player.velx = 0;
		CameraX = 0;
		CameraY = 0;
                
                //Ke Menu Utama
                new Menu().setVisible(true);
                
                // menutup frame game
                this.w.stop();         
	}
}
