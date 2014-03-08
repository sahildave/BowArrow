package Screens;


import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;

public class GameScreen3 extends JApplet implements MouseListener,MouseMotionListener,Runnable,ActionListener

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
	JLabel label;
	JButton play,quit;
	
	public void init()
	{
		System.out.println("init");
		setLayout(null);
		
		setSize(539,329);
	
		ImageIcon lb = new ImageIcon("back.PNG"); 
	    label = new JLabel(lb); 
		
	    label.setBounds(0,0,539,329);
		add(label);
		
		play=new JButton("PLAY",new ImageIcon ("playimage.png"));
		quit= new JButton("QUIT",new ImageIcon ("quitimage.png"));
		
		play.setBounds(343, 237, 171, 63);
		quit.setBounds(50, 237, 171, 63);
		
		label.add(play);
		label.add(quit);
		
		play.addActionListener(this);
		quit.addActionListener(this);
		
		System.out.println("init2");
		
	}
			
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("action");
		
		if(e.getSource()==quit)
		{
			System.out.println("quit");
			System.exit(0);
		}
		if(e.getSource()==play)
		{
			System.out.println("play");
			game();
		}
	}	
	
	public void game()
	{
		System.out.println("game");

		String imageString[]={"S20.png","S20 (1).png","S20 (2).png","S20 (3).png","S20 (4).png",
				"S20 (5).png","S30.png","S30 (1).png","S40.png","S40 (1).png","H50.png","B-10.png"};
		String imageString2[]={"ex.jpg","ex1.jpg"};
		
		for(int i=0;i<imageString.length;i++)
		{
			pics[i] = getImage(getDocumentBase(),imageString[i]);	
		}
		
		back=getImage(getDocumentBase(),"STAGE I - Morning Glory 2.JPG");
		arrow=getImage(getDocumentBase(),"1.PNG");
		
		for(int i=0;i<imageString2.length;i++)
		{
			burst[i] = getImage(getDocumentBase(),imageString2[i]);
		}
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		System.out.println("start calling");
		t= new Thread(this);
		t.start();
	}
	
	//start
	/*public void start()
	{
		System.out.println("start");
		t= new Thread(this);
		//t.start();
	}*/
	
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
		paint1(g);
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
		showStatus("mouse moved");
	}
	public void mouseClicked(MouseEvent e) 
	{
		pos=e.getY();
		cursor=1;
		showStatus("mouse clicked");
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	
	//graphics
	BufferedImage image=new BufferedImage(400,400,BufferedImage.TYPE_INT_ARGB);
    Graphics g=image.getGraphics();
	
	public void paint1 (Graphics g)
	{	
			
			System.out.println("paint1");
			g.drawImage(back,0,0,this);
			setSize(1024,668);
			
			g.drawString("arrow at "+i+","+posY,0, 620);
			g.drawString("Balloon at "+posX+","+(548-j), 0, 600);
			g.drawString("BALLOON NO. "+num, 0, 610);
			g.drawString("TOTAL SCORE "+score, 480, 30);
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
			System.out.println("paint1 end");
			//paint1(g);
		
	}
}