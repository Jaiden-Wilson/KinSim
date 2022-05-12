package squareGame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D.Double;
import javax.swing.Timer;

import javax.swing.JPanel;
public class squareGamePanel extends JPanel implements KeyListener, ActionListener
{
	//Private Instance Variables
	Rectangle2D mainSquare = new Rectangle2D.Double();	
	Rectangle2D platform = new Rectangle2D.Double();
	int x = 120;
	int y=250;
	int velx;
	int vely;
	int accelx;
	int g=1;
	/*m1 = mass of the mainSquare
	 * fg = simulated force of gravity
	 * fn = simulated normal force
	 * netForceY1 = the net force on the main square in the vertical plane 
	 */
	int m1 =2;
	int fg = m1 *g;
	int fn=0;
	int netForceY1 = fg-fn;  
	Timer t = new Timer(5,this);
	boolean jump = false, moveRight = false;
	//Default Constructor
	public squareGamePanel()
	{
		t.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);	
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent( g);
		Graphics2D g3 = (Graphics2D) g, g4 = (Graphics2D) g;
		mainSquare = new Rectangle2D.Double(x,y,10,10);
		platform = new Rectangle2D.Double(100,260,800,40);
		g3.fill(platform);
		g4.setColor(Color.RED);
		g4.fill(mainSquare);
		repaint();
		//System.out.println(y);
	}
	// Movement Methods
	public void left()
	{
		velx=-5;
		x+=velx;
		accelx=-2;
	}
	public void right()
	{
		velx=5;
		x+=velx;

		accelx=2;
		moveRight = true;
	}
	public void up()
	{
		if(restsOn(mainSquare,platform)==true)
		{	
		vely=-20;
		jump =true;
		}
	}
	
	public void projectRight()
	{
		up();
		velx=5;
		
	}
	public void projectLeft()
	{
		up();
		velx=-5;
	}
	// This method will check whether the main square is in contact with a surface
	public boolean restsOn(Rectangle2D r, Rectangle2D r2)
	{
		if(r.getMaxY()==r2.getMinY()&&(r.getMinX()>=r2.getMinX()&&r.getMaxX()<=r2.getMaxX()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	// This method will simulate static equilibrium if restsOn evaluates to true
	public void stationary()
	{
		if(restsOn(mainSquare, platform)==true)
		{
			fn=fg;
			
		}
		else
		{
			fn=0;
		}
	}
	// This method will simulate acceleration due to gravity
	public void gravity()
	{
		if((Math.abs(fg-fn))>0)
		{
			vely+=g;
		}
		else
		{
			vely+=0;
		}
	}
	//This method will cause the square to stop once it hits the platform
	public void stop()
	{
		if(restsOn(mainSquare, platform)==true &&jump==false)
		{
			vely=0;
		}
		if(restsOn(mainSquare, platform)==true &&jump==false&&moveRight==false)
		{
			velx=0;
		}
	}
	//Update method
	public void actionPerformed(ActionEvent e)
	{
		stop();
		stationary();
		gravity();
		y+=vely;
		x+=velx;
		
	}
	
	//User Input method
	public void keyPressed(KeyEvent k)
	{
		int code = k.getKeyCode();
		if(code==k.VK_A)
		{
			left();
		}
		if(code==k.VK_D)
		{
			right();
		}
		if(code==k.VK_W)
		{
			up();
		}
		
		if(code==k.VK_W&&code==k.VK_D)
		{
			projectRight();
		}
		if(code==k.VK_W&&code==k.VK_A)
		{
			projectLeft();
		}
	}
	public void keyTyped(KeyEvent k)
	{
		
	}
	public void keyReleased(KeyEvent k)
	{
		int code = k.getKeyCode();
		if(code==k.VK_W)
		{
			jump=false;
		}
		if(code==k.VK_D)
		{
			moveRight = false;
		}
	}
}
