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
	//加載所有的圖片
	ImageIcon up = new ImageIcon("up.png");
	ImageIcon down = new ImageIcon("down.png");
	ImageIcon left = new ImageIcon("left.png");
	ImageIcon right = new ImageIcon("right.png");
	ImageIcon title = new ImageIcon("title.jpg");
	ImageIcon food = new ImageIcon("food.png");
	ImageIcon body = new ImageIcon("body.png");
	
	//蛇的數據設計
	int[] snakex =new int[750];
	int[] snakey =new int[750];
	int len=3;
	String direction ="R";//R右 L左 U上 D下
	
	//食物的數據
	Random r = new Random();
	int foodx = r.nextInt(34)*25+25;
	int foody = r.nextInt(24)*25+75;
	
	
	
	
	//遊戲是否開始
	boolean isStarted = false;
	
	//遊戲是否失敗
	boolean isFaild = false;
	
	Timer timer = new Timer(100, this);
	
	//統計分數
	int score = 0;
	
	
	public SnakePanel() {
		this.setFocusable(true);
		initSnake();//放置靜態蛇
		this.addKeyListener(this);//鍵盤監聽
		timer.start();
	}
	
	//初始化蛇
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
		//設置畫布的背景顏色
		this.setBackground(Color.BLACK);
		g.fillRect(25, 75, 850, 600);
		//設置標題
		title.paintIcon(this, g, 25, 11);
		
		//畫蛇頭
		if(direction.equals("R")) {
			right.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("L")) {
			left.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("U")) {
			up.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("D")) {
			down.paintIcon(this, g, snakex[0], snakey[0]);
		}
		//畫蛇身
		for(int i=1;i<len;i++) {
			body.paintIcon(this, g, snakex[i], snakey[i]);
			
		}
		
		//畫開始提示語
		if(!isStarted) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", Font.BOLD, 30));
			g.drawString("Press Space to Start/Pause", 300, 300);
			
		}
		
		//畫失敗提示語
		if(isFaild) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", Font.BOLD, 30));
			g.drawString("Game Over, Press Space to Start", 300, 300);
		}
		//畫食物
		food.paintIcon(this, g, foodx, foody);
		
		
		//分數和長度的統計
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
	 * 1.重新設定鬧鐘
	 * 2.蛇移動
	 * 3.重畫
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		timer.start();
		
		if(isStarted && !isFaild) {
			//移動身體
			for(int i=len;i>0;i--) {
				snakex[i] = snakex[i-1];
				snakey[i] = snakey[i-1];
			}
			//移動頭 穿畫面
			if(direction.equals("R")) {
				//橫坐標+25
				snakex[0] = snakex[0]+25;
				if(snakex[0] >850) {
					snakex[0] = 25;
				}
				
			}else if(direction.equals("L")) {
				//橫坐標-25
				snakex[0] = snakex[0]-25;
				if(snakex[0] <25) {
					snakex[0] = 850;
				}
			}else if(direction.equals("U")) {
				//縱坐標-25
				snakey[0] = snakey[0]-25;
				if(snakey[0] <75) {
					snakey[0] = 650;
				}
			}else if(direction.equals("D")) {
				//縱坐標+25
				snakey[0] = snakey[0]+25;
				if(snakey[0] >650) {
					snakey[0] = 75;
				}
			}
			//吃食物的邏輯
			if(snakex[0] == foodx && snakey[0] == foody) {
				len++;
				score++;
				foodx = r.nextInt(34)*25+25;
				foody = r.nextInt(24)*25+75;
			}
			
			//死亡判斷
			for(int i=1;i<len;i++) {
				if(snakex[0] == snakex[i] && snakey[0] == snakey[i]) {
					isFaild = true;
				}
			}
		}
		repaint();
		
	}
	
}
