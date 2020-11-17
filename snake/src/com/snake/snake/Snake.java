package com.snake.snake;

import javax.swing.JFrame;

public class Snake {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(400, 200, 900, 720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Snake");
		SnakePanel panel = new SnakePanel();
		frame.add(panel);
		
		frame.setVisible(true);
		

	}

}
