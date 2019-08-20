package com.elias.game.graphics;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable{
	
	public static final int WIDTH = 800, HEIGHT = 800;
	private Thread thread;
	private boolean running;
	
	public Screen() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		start();
	}
	
	public void tick() {
		
	}
	
	public void paint(Graphics g) {
		
	}
	
	public void start() {
		running = true;
		thread = new Thread(this, "Game Loop");
	}
	
	public void stop() {
		
	}
	
	public void run() {
		while (running) {
			tick();
			repaint();
		}
	}

}
