package com.elias.game.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import com.elias.game.entities.Apple;
import com.elias.game.entities.BodyPart;

public class Screen extends JPanel implements Runnable, KeyListener{
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 500, HEIGHT = 500;
	private Thread thread;
	private boolean running;
	
	private BodyPart b;
	private ArrayList<BodyPart> snake;
	
	private Apple apple;
	private ArrayList<Apple> apples;
	
	private boolean right = true, left = false, up = false, down = false;	
	private int xCoor = 25, yCoor = 25, size = 150;	
	private int ticks = 0;
	private Random r;
	
	public Screen() {
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		addKeyListener(this);
		r = new Random();
		
		snake = new ArrayList<BodyPart>();
		apples = new ArrayList<Apple>();
		
		start();		
	}
	
	public void tick() {
		if (snake.size() == 0) {
			b = new BodyPart(xCoor, yCoor, 10);
			snake.add(b);
		}
		
		ticks++;
		
		if (ticks > 400000) {
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
		
		if (apples.size() == 0) {
			int xCoor = r.nextInt(49);
			int yCoor = r.nextInt(49);
			
			apple = new Apple(xCoor, yCoor, 10);
			apples.add(apple);
		}
		
		for (int i=0; i< apples.size(); i++) {
			if (xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()) {
				size++;
				apples.remove(i);
				i--;
			}
		}
		
		for (int i=0; i<snake.size(); i++) {
			if (xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()) {
				if (i != snake.size() - 1) {
					System.out.print("GAME OVER!");
					stop();
				}
			}
		}	
		
		if (xCoor <= 0) {
			xCoor += 50;
		}
		else if (xCoor >= 50) {
			xCoor -= 50;
		}
		else if (yCoor <= 0) {
			yCoor += 50;
		}
		else if (yCoor >= 50) {
			yCoor -= 50;
		}
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);	
		
		for (int i=0; i<snake.size(); i++) {
			snake.get(i).draw(g);
		}
		
		for (int i=0; i< apples.size(); i++) {
			apples.get(i).draw(g);
		}
	}
	
	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while (running) {
			tick();
			repaint();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT && !left) {
			right = true;			
			up = false;
			down = false;			
		}
		if (key == KeyEvent.VK_LEFT && !right) {
			left = true;			
			up = false;
			down = false;			
		}
		if (key == KeyEvent.VK_UP && !down) {
			up = true;			
			left = false;
			right = false;			
		}
		if (key == KeyEvent.VK_DOWN && !up) {
			down = true;
			left = false;	
			right = false;					
		}		
	}

	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	public void keyReleased(KeyEvent e) {		
	}
}
