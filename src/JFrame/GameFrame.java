package JFrame;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameFrame extends JFrame implements MouseListener,MouseMotionListener,Runnable

{
	Image pics[]=new Image[12];
	Image burst[]=new Image[2];
	Image current,currentEx,arrow,back;
	Thread t;
	
	int num,num2,posX,posY,i=0,j=0,cursor=0;
	int posAX;
	int boom=0;
	int pos=-100;
	int posBurstY,posBurstX;
	int score=0;
	
	public static void main(String [] args)
	{
		GameFrame obj = new GameFrame();
		obj.setVisible(true);
		obj.setSize(1024,668);
		System.out.println("main");
	}
	
	public void GameFrame()
	{
		System.out.println("cons called");
		ImageIcon BallonArray[]={new ImageIcon("S20.png"),new ImageIcon("S20 (1).png"),new ImageIcon("S20 (2).png"),
				new ImageIcon("S20 (3).png"),new ImageIcon("S20 (4).png"),new ImageIcon("S20 (5).png"),
				new ImageIcon("S30.png"),new ImageIcon("S30 (1).png"),new ImageIcon("S40.png"),
				new ImageIcon("S40 (1).png"),new ImageIcon("H50.png"),new ImageIcon("B-10.png")};		
		ImageIcon back=new ImageIcon("STAGE I - Morning Glory 2.JPG");
		ImageIcon arrow=new ImageIcon("1.PNG");
		ImageIcon BurstArray[]={new ImageIcon("ex.jpg"), new ImageIcon("ex1.jpg")};
		addMouseListener(this);
		addMouseMotionListener(this);
		System.out.println("cons end");
	}
	
	//start
	public void start()
	{
		System.out.println("start");
		t= new Thread(this);
		t.start();
	}
	
	//run
	public void run()
	{
		System.out.println("run");
		while(boom!=1)
		{
			num=(int)((Math.random())*12);					// for random balloon
			num2=(int)((Math.random())*2);
			loadpic(num,num2);	
		}
	}
	
	//loadpic
	public void loadpic(int i,int k)
	{
		System.out.println("loadpic");
		current=pics[i];
		currentEx=burst[k];
		posX=(int)(Math.random()*750)+200;						//	for random position of balloon
		j=0;
		while(j<751&&boom==2)
		{
			pause(100);
			j=j+30;
		}
		
	}
	
	//pause
	public void pause(int t)
	{
		try 
		{
			Thread.sleep(t);
		}
		catch (InterruptedException e) {}
	}
	
	//mouseEvents
	public void mouseMoved(MouseEvent e)
	{
		posY=e.getY();
	//	showStatus("mouse moved");
	}
	public void mouseClicked(MouseEvent e) 
	{
		pos=e.getY();
		cursor=1;
	//	showStatus("mouse clicked");
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	
	//graphics
	public void paint(Graphics g)
	{	
			System.out.println("paint line 113");
			
			g.drawImage(back,0,0,this);
			
			g.drawString("arrow at "+i+","+posY,0, 620);
			g.drawString("Balloon at "+posX+","+(548-j), 0, 600);
			g.drawString("BALLOON NO. "+num, 0, 610);
			g.drawString("TOTAL SCORE "+score, 480, 30);
			
			System.out.println("paint line 122");
			
			int tip =posAX+arrow.getWidth(null);
			for(int x=0;x<current.getHeight(null);x++)
			{															//	For bursting of balloon,
				for(int y=0;y<current.getWidth(null);y++)
				{
					if(tip==(posX+y)&&(548-j+x)==pos)
					{										//	if the coordinate matches
						//	System.out.println((548-j)+"and"+x);
						boom=1;								//	in specific region the 
						posBurstY  = 548-j+x;				//	image of explosion would 
						posBurstX=posX;						//	be drawn.
					}
				}
			}
			System.out.println("paint line 138");
			if(boom==1)
			{
				g.drawImage(arrow,0, posY, null);
				g.drawImage(currentEx,posBurstX, posBurstY,this);	//drawing image of explosion			
				boom=3;
				if(num==1||num==2||num==3||num==4||num==5||num==0)
				{
					score=score+20;
					g.drawString("SCORE +20", 0, 630);
				}
				if(num==7||num==6)
				{
					score=score+30;
					g.drawString("SCORE +30", 0, 630);
				}
				if(num==9||num==8)
				{
					score=score+40;
					g.drawString("SCORE +40", 0, 630);
				}
				if(num==10)
				{
					score=score+50;
					g.drawString("SCORE +50", 0, 630);
				}
				if(num==11)
				{
					score=score-10;
					g.drawString("SCORE -10", 0, 630);
				}
			
				pause(500);
			}
			else
			{
				//balloon
				g.drawImage(current, posX, 548-j, this);
				//arrow
				g.drawImage(arrow,0, posY, null);		//	arrow always at the left of screen
				posAX=i;	
				boom=2;
				if(cursor==1&&i<1000)
				{
					i+=8;
					g.drawImage(arrow,i+0, pos, null);  //	moving arrow
				}
				if(i>=990)
				{
					i=0;
					cursor=2;
				}
			}
			System.out.println("paint end");
			repaint();
		
	}
}