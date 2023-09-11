/* Saya Muhammad Aditya Hasta Pratama (ILKOM C2) dengan NIM 2000360 
mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah 
Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya 
maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.  */


package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import viewmodel.KeyHandler;
import viewmodel.LevelHandler;

// setting tampilan window (main game)
public class Window extends Canvas implements Runnable{
	
	//variable
	private static final long serialVersionUID = 1L;
	private Thread thread;
	public boolean running = false;
        private String name;
	
	private KeyHandler Klistener = new KeyHandler(this); // mengatur input keyboard
	public LevelHandler level = new LevelHandler(this); // mengatur logika game
	public GameState gs = GameState.Game; // menentukan state yang sekarang
	
	// Menjalankan window
	public Window(String Title) {
		JFrame frame = new JFrame(Title);
		
		//set size
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//set visible
		frame.setResizable(false);
		frame.setVisible(true);
		frame.add(this);
	}
	
	// Memulai game
	public void start() {
		running = true;
		//membuat thread baru
		thread = new Thread(this);
		thread.start();
	}
	
        // Stop game
	public void stop() {
		running = false;
		// stop thread
		try {
			thread.join();
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}

        // setting ketika berjalan
	public void run() {
            
                // deklarasi variabell
		long lastTime = System.nanoTime();
		double amountsOfTicks = 60.0;
		double ns = 1000000000 / amountsOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		
                // ketika game berjalan
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			Render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	
        // tick game
	public void tick() {
		level.tick();
	}
        
        // get set username
        public void Setusername(String n){
            this.name = n;
        }
        public String Getusername(){
            return this.name;
        }
	
        // render window 
	public void Render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(2);
			bs = this.getBufferStrategy();
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(48, 25, 52));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		
		level.Render(g);
		
		bs.show();
		g.dispose();
	}
}
