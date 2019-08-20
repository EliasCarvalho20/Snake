package com.elias.game;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class Frame extends JFrame{
	
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Snake");
		setResizable(false);
		
		init();
	}
	
	public void init() {
		setLayout(new GridLayout(1, 1, 0, 0));	
		
		pack();
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Frame();
	}

}
