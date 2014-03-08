import java.applet.*;
import java.awt.*;


public class Background extends Applet
{
	Image bg,man;
	
	public void init()
	{
		bg = getImage(getDocumentBase(),"STAGE I - Morning Glory 2.JPG");
		man=getImage(getDocumentBase(),"man.JPG");
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(bg, 0, 0, this);
		g.drawImage(man, 0, 0, this);
		setSize(1024,668);
	}
}
