/* Saya Muhammad Aditya Hasta Pratama (ILKOM C2) dengan NIM 2000360 
mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah 
Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya 
maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.  */

package model;

import java.awt.Graphics;

// untuk memberikan fungsi setiap item yang digunakan 
// player, platform/batang
public abstract class Item {
	protected byte ID;
	public Item(byte ID) {
		this.ID = ID;
	}
        
	// fungsi untuk menampilkan/menjalankan
	public abstract void Render(Graphics G);
        
	// fungsi memberiksn waktu/frame
	public abstract void tick();
}
