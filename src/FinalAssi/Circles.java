package FinalAssi;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Label;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

@SuppressWarnings("serial")
public class Circles extends JFrame implements ActionListener{

 public JPanel Panel,Panel1, Panel2, Panel3, Panel4, Panel5, Panel6, Panel7, BoardPanel;
 public JButton Redraw;
 public static String ans = "Two circles intersect? ";
 public static Label circlesIntersect;
 public JTextField x1txt, y1txt, r1txt, x2txt, y2txt, r2txt;
 public int x1,y1,r1,x2,y2,r2,d1,d2;
 public	Ellipse2D ellipse, ellipse2;
 boolean RedCircle;
 int cursorX, cursorY;
 CircleBoard circleBoard = new CircleBoard();
 MoveCircles dragcircles = new MoveCircles();
 
 public static void main(String[] args) {
     Circles circles = new Circles();
     circles.setSize(500,480);
     circles.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     circles.setVisible(true);
   
 }
 
 public Circles() {
    
     Panel = new JPanel();
     circlesIntersect = new Label(ans + "No");
	 Panel.add(circlesIntersect); // The intersection label
     
     Border blackline = BorderFactory.createLineBorder(Color.black);
     GridLayout layout = new GridLayout(1,1);
     layout.setHgap(0);

     GridLayout layout2 = new GridLayout(2,1);
     layout2.setHgap(0);
     
     Panel2 = new JPanel();
     Panel2.setLayout(layout2);
     Panel2.setBorder(blackline);
     
     
     // This panel  will contain the data of two circles
     Panel4 = new JPanel();
     Panel4.setLayout(layout);
    
     
     // This panel  will contain the information of circle one
     Panel5 = new JPanel();
     Panel5.setPreferredSize(new Dimension(70,110));
     Panel5.setBorder(blackline);

     
     JLabel lb2 = new JLabel("Enter circle 1 info :");
     JPanel SubPanel1 = new JPanel();
     SubPanel1.setLayout(new GridLayout(3,1));
     JLabel Centerx1 =  new JLabel("Center x : "); 
     x1txt = new JTextField(5);
     
     JLabel Centery1 =  new JLabel("Center y : ");
     y1txt = new JTextField(5);
     JLabel Centerr1 =  new JLabel("Radius r : ");
     r1txt = new JTextField(5);
     
     Panel5.add(lb2);
     SubPanel1.add(Centerx1);
     SubPanel1.add(x1txt);
     SubPanel1.add(Centery1);
     SubPanel1.add(y1txt);
     SubPanel1.add(Centerr1);
     SubPanel1.add(r1txt);
     Panel5.add(SubPanel1);
 
     
     // This panel  will contain the information of circle two
     Panel6 = new JPanel();
     Panel6.setPreferredSize(new Dimension(70,110));
     Panel6.setBorder(blackline);
     
     JLabel lb3 = new JLabel("Enter circle 2 info :");
     JLabel Centerx2 =  new JLabel("Center x : ");
     x2txt = new JTextField(5);
     JLabel Centery2 =  new JLabel("Center y : ");
     y2txt = new JTextField(5);
     JLabel Centerr2 =  new JLabel("Radius r : ");
     r2txt = new JTextField(5);
     
     Panel6.add(lb3);
     JPanel SubPanel2 = new JPanel();
     SubPanel2.setLayout(new GridLayout(3,1));
     SubPanel2.add(Centerx2);
     SubPanel2.add(x2txt);
     SubPanel2.add(Centery2);
     SubPanel2.add(y2txt);
     SubPanel2.add(Centerr2);
     SubPanel2.add(r2txt);
     Panel6.add(SubPanel2);
     
     
     // Adding both circles information panels
     Panel4.add(Panel5);
     Panel4.add(Panel6);
     
     // panel for the redraw button
     Panel7 = new JPanel();
     Redraw = new JButton("<html><span style='font-size:9px; color: black;'>"+"Redraw Circles"+"</span></html>");
     Redraw.setSize(new Dimension(90, 25));
     Redraw.setBackground(Color.LIGHT_GRAY);
     Panel7.add(Redraw);
     
     /*adding panel4 which contain both circles info and panel7 which contain the redraw button
     into panel2 */
     
     Panel2.add(Panel4);
     Panel2.add(Panel7, FlowLayout.CENTER);// using FlowLayout to center the button


     add(Panel, BorderLayout.NORTH);
     add(circleBoard,BorderLayout.CENTER);
     add(Panel2, BorderLayout.SOUTH);
     
   
     Redraw.addActionListener(this);
     circleBoard.addMouseListener(dragcircles);
     circleBoard.addMouseMotionListener(dragcircles);
 }

 @Override
 public void actionPerformed(ActionEvent e) {
   
	 try {
     x1    = Integer.parseInt(x1txt.getText());
     y1    = Integer.parseInt(y1txt.getText());
     r1    = Integer.parseInt(r1txt.getText());
     x2    = Integer.parseInt(x2txt.getText());
     y2    = Integer.parseInt(y2txt.getText());
     r2    = Integer.parseInt(r2txt.getText());
    	  
            
            if (e.getSource() == Redraw) {
   			 
            	circleBoard.repaint(); 
        
   		 	}
	 }
	 catch(Exception e1) {
		  
		//If there are any empty blanks this message will pop up
		 JOptionPane.showMessageDialog(null, "Fill in all blanks.");
	
     }
        	
 }
 
class MoveCircles extends MouseAdapter{
	
	 public void mousePressed(MouseEvent e) {
         
		 int x = e.getX();
         int y = e.getY();	 

         if (x >= x2 && x < x2 + d2 && y >= y2 && y < y2 + d2) {
             
        	 RedCircle = false;
             cursorX = x - x2;
             cursorY = y - y2;
         }

         if (x >= x1 && x < x1 + d1 && y >= y1 && y < y1 + d1) {
        	 
        	 RedCircle = true;
             cursorX = x - x1;
             cursorY = y - y1;
         }
     }
	 public void mouseDragged(MouseEvent e) {
         int x = e.getX();
         int y = e.getY();

         // Drag red circle
         
         if (RedCircle) {
        	 

             x1 = x - cursorX;
             y1 = y - cursorY;
        	 
             
         } 
         
         // Drag black circle
         else {
         
        	   x2 = x - cursorX;
               y2 = y - cursorY;
         }
         
         circleBoard.repaint();

         // Sets the new values for x1,y1,x2,y2 
         x1txt.setText("" + x1);
         y1txt.setText("" + y1);

         x2txt.setText("" + x2);
         y2txt.setText("" + y2);
         

	        // Check Intersections
	        
	        if (ellipse.intersects(ellipse2.getX(), ellipse2.getY(), 
	        	ellipse2.getWidth(), ellipse2.getHeight())) 
	        {
	           
	            circlesIntersect.setText(ans + "Yes");
	            
	        } else {
	        	circlesIntersect.setText(ans + "No");
	        }
     }
	
}
	
 class CircleBoard extends JPanel{

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		d1 = r1 *2;
		d2 = r2 * 2;
		ellipse = new Ellipse2D.Double(x1, y1, r1, r1);
        ellipse2 = new Ellipse2D.Double(x2, y2, r2, r2);
		
        
        Graphics2D g2 = (Graphics2D)g;
		
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.red);
		g2.draw(ellipse);
		g2.setColor(Color.black);
        g2.draw(ellipse2);
       
        
        // Check Intersections
        if (ellipse.intersects(ellipse2.getX(), ellipse2.getY(), 
            ellipse2.getWidth(), ellipse2.getHeight())) 
        {
           
            circlesIntersect.setText(ans + "Yes");
        } else {
        	circlesIntersect.setText(ans + "No");
        }
	}

}

}

