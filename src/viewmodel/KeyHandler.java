/* Saya Muhammad Aditya Hasta Pratama (ILKOM C2) dengan NIM 2000360 
mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah 
Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya 
maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.  */


package viewmodel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import view.GameState;
import view.Window;

public class KeyHandler implements KeyListener {
	
	private Window w;
        
        // tombol yang akan ditekan (menerima input)
	public KeyHandler(Window w) {
		this.w = w;
		w.addKeyListener(this);
	}
	public void keyTyped(KeyEvent e) {
	}
	
        // inisialisasi
	private boolean MovingLeft = false;
        
        // setting ketika menekan tombol
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
                
                // hanya berlaku di window game
		if(w.gs == GameState.Game) {
			if(key == KeyEvent.VK_D) {
				w.level.player.velx = w.level.player.speed;
				MovingLeft = false;
			}
			if(key == KeyEvent.VK_A) {
				w.level.player.velx = -w.level.player.speed;
				MovingLeft = true;
			}
			if(key == KeyEvent.VK_W) {
				//Kondisi untuk hanya dapat melompat di object
				if(w.level.player.vely == 0) {
					w.level.player.vely = -w.level.player.Jump;
				}
			}
		}
	}
	
        // setting ketika tombol dilepas
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(w.gs == GameState.Game) {
			if(key == KeyEvent.VK_D && !MovingLeft) {
                                //maka tidak akan bergerak
				w.level.player.velx = 0;
			}
			if(key == KeyEvent.VK_A && MovingLeft) {
                                //maka tidak akan bergerak
				w.level.player.velx = 0;
			}
		}
	}	
}
