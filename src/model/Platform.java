/* Saya Muhammad Aditya Hasta Pratama (ILKOM C2) dengan NIM 2000360 
mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah 
Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya 
maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.  */


package model;

import java.awt.Color;
import java.awt.Graphics;

// set objek yang tidak bergerak
public class Platform extends Item{
	
        // variabel untuk lebar dan tinggi objek
	public int x,y,width,height;
        
        // untuk mengatur warna
	public Color c;
	
	// fungsi memanggil objek tidak bergerak
	public Platform(byte ID, int x, int y ,int width, int height, Color c) {
		
		super(ID);
		this.x = x;
		this.c = c;
		this.y = y;
		this.width = width;
		this.height = height;
	}

        // mengatur frame
	public void tick() {
            
	}
	
        // menampilkan
	public void Render(Graphics g) {
		g.setColor(c);
		g.fillRect(x, y, width, height);
	}
	
	
}
