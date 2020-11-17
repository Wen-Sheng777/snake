package com.snake.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.ProcessBuilder.Redirect;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePanel extends JPanel implements KeyListener,ActionListener {
	//�[���Ҧ����Ϥ�
	ImageIcon up = new ImageIcon("up.png");
	ImageIcon down = new ImageIcon("down.png");
	ImageIcon left = new ImageIcon("left.png");
	ImageIcon right = new ImageIcon("right.png");
	ImageIcon title = new ImageIcon("title.jpg");
	ImageIcon food = new ImageIcon("food.png");
	ImageIcon body = new ImageIcon("body.png");
	
	//�D���ƾڳ]�p
	int[] snakex =new int[750];
	int[] snakey =new int[750];
	int len=3;
	String direction ="R";//R�k L�� U�W D�U
	
	//�������ƾ�
	Random r = new Random();
	int foodx = r.nextInt(34)*25+25;
	int foody = r.nextInt(24)*25+75;
	
	
	
	
	//�C���O�_�}�l
	boolean isStarted = false;
	
	//�C���O�_����
	boolean isFaild = false;
	
	Timer timer = new Timer(100, this);
	
	//�έp����
	int score = 0;
	
	
	public SnakePanel() {
		this.setFocusable(true);
		initSnake();//��m�R�A�D
		this.addKeyListener(this);//��L��ť
		timer.start();
	}
	
	//��l�ƳD
	public void initSnake() {
		isStarted = false;
		isFaild = false;
		score = 0;
		len = 3;
		direction="R";
		snakex[0]=100;
		snakey[0]=100;
		snakex[1]=75;
		snakey[1]=100;
		snakex[2]=50;
		snakey[2]=100;
		
	}
	
	
	public void paint(Graphics g) {
		//�]�m�e�����I���C��
		this.setBackground(Color.BLACK);
		g.fillRect(25, 75, 850, 600);
		//�]�m���D
		title.paintIcon(this, g, 25, 11);
		
		//�e�D�Y
		if(direction.equals("R")) {
			right.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("L")) {
			left.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("U")) {
			up.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("D")) {
			down.paintIcon(this, g, snakex[0], snakey[0]);
		}
		//�e�D��
		for(int i=1;i<len;i++) {
			body.paintIcon(this, g, snakex[i], snakey[i]);
			
		}
		
		//�e�}�l���ܻy
		if(!isStarted) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", Font.BOLD, 30));
			g.drawString("Press Space to Start/Pause", 300, 300);
			
		}
		
		//�e���Ѵ��ܻy
		if(isFaild) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", Font.BOLD, 30));
			g.drawString("Game Over, Press Space to Start", 300, 300);
		}
		//�e����
		food.paintIcon(this, g, foodx, foody);
		
		
		//���ƩM���ת��έp
		g.setColor(Color.WHITE);
		g.setFont(new Font("arail",Font.PLAIN,15));
		g.drawString("Score:"+score, 750, 30);
		g.drawString("Length:"+len, 750, 50);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_SPACE) {
			if(isFaild) {
				initSnake();
			}else{
			isStarted = !isStarted;
			}
			
//			repaint();
			
		}else if(keyCode == KeyEvent.VK_UP && !direction.equals("D")) {
			direction="U";
		}else if(keyCode == KeyEvent.VK_DOWN && !direction.equals("U")) {
			direction="D";
		}else if(keyCode == KeyEvent.VK_LEFT && !direction.equals("R")) {
			direction="L";
		}else if(keyCode == KeyEvent.VK_RIGHT && !direction.equals("L")) {
			direction="R";
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * 1.���s�]�w�x��
	 * 2.�D����
	 * 3.���e
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		timer.start();
		
		if(isStarted && !isFaild) {
			//���ʨ���
			for(int i=len;i>0;i--) {
				snakex[i] = snakex[i-1];
				snakey[i] = snakey[i-1];
			}
			//�����Y ��e��
			if(direction.equals("R")) {
				//���+25
				snakex[0] = snakex[0]+25;
				if(snakex[0] >850) {
					snakex[0] = 25;
				}
				
			}else if(direction.equals("L")) {
				//���-25
				snakex[0] = snakex[0]-25;
				if(snakex[0] <25) {
					snakex[0] = 850;
				}
			}else if(direction.equals("U")) {
				//�a����-25
				snakey[0] = snakey[0]-25;
				if(snakey[0] <75) {
					snakey[0] = 650;
				}
			}else if(direction.equals("D")) {
				//�a����+25
				snakey[0] = snakey[0]+25;
				if(snakey[0] >650) {
					snakey[0] = 75;
				}
			}
			//�Y�������޿�
			if(snakex[0] == foodx && snakey[0] == foody) {
				len++;
				score++;
				foodx = r.nextInt(34)*25+25;
				foody = r.nextInt(24)*25+75;
			}
			
			//���`�P�_
			for(int i=1;i<len;i++) {
				if(snakex[0] == snakex[i] && snakey[0] == snakey[i]) {
					isFaild = true;
				}
			}
		}
		repaint();
		
	}
	
}
