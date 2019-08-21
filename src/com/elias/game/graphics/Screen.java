package com.elias.game.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.elias.game.entities.BodyPart;

public class Screen extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 800, HEIGHT = 800;
	private Thread thread;
	private boolean running;
	
	private BodyPart b;
	private ArrayList<BodyPart> snake;
	
	private boolean right = true, left = false, up = false, down = false;
	
	private int xCoor = 10, yCoor = 10, size = 5;
	
	private int ticks = 0;
	
	public Screen() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		snake = new ArrayList<BodyPart>();
		
		start();		
	}
	
	public void tick() {
		if (snake.size() == 0) {
			b = new BodyPart(xCoor, yCoor, 10);
			snake.add(b);
		}
		
		ticks++;
		
		if (ticks > 250000) {
			if (right) xCoor++;
			if (left) xCoor--;
			if (up) yCoor--;
			if (down) yCoor++;
			
			ticks = 0;
			b = new BodyPart(xCoor, yCoor, 10);
			snake.add(b);
			
			if (snake.size() > size) {
				snake.remove(0);
			}
		}
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.BLACK);
		for (int i=0; i<WIDTH/10; i++) {
			g.drawLine(i * 10, 0, i * 10, HEIGHT);
		}
		
		for (int i=0; i<HEIGHT/10; i++) {
			g.drawLine(0, i * 10, WIDTH, i * 10);
		}
		
		for (int i=0; i<snake.size(); i++) {
			snake.get(i).draw(g);
		}
	}
	
	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
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
