package org.dzq.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.dzq.entity.Plane;
import org.dzq.entity.Shell;
import org.dzq.util.Constant;
import org.dzq.util.Explode;
import org.dzq.util.GameUtil;

public class MainFrame extends Frame{
	Image bg =GameUtil.getImage("images/bg.jpg");
	Image planeimg =GameUtil.getImage("images/plane.png");
	int  planeX=300,planeY=300;
	Plane plane=new Plane(planeimg,planeX,planeY,planeimg.getWidth(null),planeimg.getHeight(null),3);
	Shell[] shells=new Shell[50];
	Explode explode;
	Date startTime=new Date();
	Date endTime;
	double liveTime;
	Font font=new Font("宋体",Font.BOLD,20);
	JButton restart=new JButton("重新开始游戏");
	//双缓冲解决Frame闪烁
	Image offScreenIamge=null;
	public void update(Graphics g) {
		if(offScreenIamge==null) {
			offScreenIamge=this.createImage(Constant.FRAME_WIDTH,Constant.FRAME_HEIGHT);
		}
		Graphics gOff=offScreenIamge.getGraphics();
		paint(gOff);
		g.drawImage(offScreenIamge, 0, 0, null);
	}
	
	
	
	@Override
	public void paint(Graphics g) {//该方法会自动被调用,g相当画笔
		g.drawImage(bg, 0, 0, null);
		restart.setBounds(600, 300, 100, 100);
		plane.drawSelf(g);
		for(Shell she:shells) {
			 she.drawShell(g);
			 //碰撞检测
			 boolean crash = she.getRect().intersects(plane.getRect());
			 if(crash) {
				 plane.live=false;
				 if(explode==null) {
				 explode =new Explode(plane.x,plane.y);
				 endTime=new Date();
				 liveTime=endTime.getTime()-startTime.getTime();
				 }
				 explode.draw(g);
				 
			 }
			 if(!plane.live) {
				 g.setColor(Color.YELLOW);
				 g.setFont(font);
				 g.drawString("存活时间:"+liveTime+"毫秒",200,300);
			 }
			 
		}
	}
	class KeyMonitor extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			plane.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			plane.cancelDirection(e);
		}
		
	}
	class PaintThread extends Thread{
		
		@Override
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void launchFrame() {
		this.setTitle("飞机躲子弹");
		this.setVisible(true);
		this.setSize(Constant.FRAME_WIDTH,Constant.FRAME_HEIGHT);
		this.setLocation(600, 300);
		
		//设置关闭窗口按钮
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		new PaintThread().start();
		addKeyListener(new KeyMonitor());//监听键盘
		for(int i=0;i<shells.length;i++) {
			 shells[i]=new Shell();
		}
		
	}
	public static void main(String[] args) {
		MainFrame mainframe =new MainFrame();
		mainframe.launchFrame();
	}
}
