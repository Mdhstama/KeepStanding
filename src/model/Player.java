/* Saya Muhammad Aditya Hasta Pratama (ILKOM C2) dengan NIM 2000360 
mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah 
Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya 
maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.  */

package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import view.Window;

// mengatur objek yang akan kita gerakan 
public class Player {
	
        // deklarasi variabel yang digunakan 
	public Window w;
	
	public int width, height; //ukuran player
	public double x,y; // posisi player
	public double velx,vely; // gerakan player
	
	public double speed = 4; // kecepatan player
	public double Jump = 4; // kekuatan lompatan
	public boolean Falling = false; // status
	
	// construct setting untuk player
	public Player (Window w, double x, double y, int width, int height) {
		this.w = w;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;	
	}
	
        // setting frame untuk player
	public void tick() {
		x+=velx;
		y+=vely;
		
		if(vely < w.level.Gravity && Falling) {
                        // kondisi ketika di udara
			vely+=0.1;
		}
		else if(!Falling && vely > 0) {
                        // konidisi ketika jatuh di objek
			vely= 0;
		}
		
		Collisions();
	}
	
        // setting kondisi ketika player bertemu dengan objek yang tidak bergerak
	public void Collisions() {
                //status
		Falling = true;
                
                //loop
		for(Item i: w.level.items) {
                    
                        // jika player bertemu dengan batang
			if(i.ID == ObjectIDS.Platform) {
                            
				Platform p = (Platform)i; 
                               
                                // jika player bersentuhan dengan batang
 				if(new Rectangle((int)x,(int)y+(int)vely,width,height).intersects(p.x,p.y,p.width,p.height)) {
					Falling = true;
                                        // kondisi player di atas batang
                                        if(y + height <= p.y+1) {

                                                // maka fallingnya false
                                                Falling = false;

                                                // kondisi untuk membuat player di atas batang
                                                if(vely >0) {
                                                        vely = 0;
                                                        y = p.y - height; //penempatan player di atas batang
                                                }
                                        }

                                        // kondisi menyentuh sisi selain bagian atas batang
                                        if(y > p.y-1) {
                                                // maka player akan menuju sisi atas batang
                                                y = p.y - 50;
                                                vely = 2;
                                                velx = 0;
                                        }
							
				}
				
			}
		}
	}
	
        // render player
	public void Render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, width, height);
	}
	
}
