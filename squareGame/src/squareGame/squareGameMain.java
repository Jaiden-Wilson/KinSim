package squareGame;

import javax.swing.*;

public class squareGameMain extends squareGamePanel
{

	public static void main(String[] args) 
	{
		// Variable Declarations and Initializations
		squareGamePanel p1 = new squareGamePanel();
		JFrame frame = new JFrame("Square Game");
		frame.setSize(3000,3000);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(p1);
	}//end main
}//end class
