import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class ArrowMouse2 extends Applet implements MouseListener,MouseMotionListener,Runnable
{
	Image arrow;
	int i=0,j;
	Thread t;
	int posY,cursor=0;
	
	public void init()
	{
		addMouseListener(this);
		addMouseMotionListener(this);
		arrow=getImage(getDocumentBase(),"1.PNG");
	}
	
	public void mouseMoved(MouseEvent e)
	{
		posY=e.getY();
		cursor=1;
		showStatus("mouse moved");
		
	}
	public void mouseClicked(MouseEvent e) 
	{
		
	//	posY=e.getY();
	//	showStatus("mouse clicked");
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	
	
	
	public void paint(Graphics g)
	{
		setSize(1024,668);
		g.drawImage(arrow,0, posY, null);
		g.drawString(i+","+posY,i+0, posY);
		g.drawString("arrow at "+i+","+posY,i+100, posY);
		g.drawImage(arrow,i+0, posY, null);
	}
	public void start()
	{
	
		try
		{
			t=new Thread(this);
			t.start();
		}
		catch(Exception e){}
		}
	

	public void run()
	{

	
		for(i=0;i<3000;i+=10)
		{
			repaint();
			try
			{
				Thread.sleep(50);
			}
			catch(Exception e){}
		}
		
	}
}
