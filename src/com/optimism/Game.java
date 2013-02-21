package com.optimism;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends Canvas {
	
	
	
	private static Game game = new Game();
	
	private JFrame frame = new JFrame("Frame");

	private Graphics2D g;
	private BufferStrategy buffStrategy;
	
	private int frameWidth = 800;
	private int frameHeight = 600;
	
	
	
	public static void main(String[] args) {
		
		game.run();

	}
	
	
	
	public Game() {
		
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(800,600));
		panel.setLayout(null);
		
		setBounds(0,0,800,600);
		panel.add(this);
		
		setIgnoreRepaint(true);
		
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		requestFocus();
		
		createBufferStrategy(2);
		buffStrategy = getBufferStrategy();
		
		g = (Graphics2D) buffStrategy.getDrawGraphics();
		
		
		//Centre frame.
		frame.setLocationRelativeTo(null);
		
	}
	
	
	
	public void run() {
		
		while (true) {
			
			clear();
			buffStrategy.show();
			
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {}
			
		}
		
	}
	
	
	
	public void clear(){
		
		g.setColor(Color.black);
		g.fillRect(0, 0, frameWidth, frameHeight);
		
	}
	
	
	
}
