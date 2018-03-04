package Application;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Renderer {
	public static double netForce = 0, netVector =0;
	private JFrame frame; 
public Renderer(JFrame frame) {
	 this .frame = frame;
 }
 public void render(){
	Graphics g = frame.getGraphics();
	g.translate(500, 300);
	
	ArrayList<Force> forceArray = new ArrayList<>();
	for(int i =1 ;i < ForceList.listModel.size(); i++)
	{
		forceArray.add(ForceList.forceMap.get(ForceList.listModel.elementAt(i)));
		
	}
	g.setColor(Color.GREEN); 
	for(int i=0; i<forceArray.size(); i++)
	{
		float newtons = forceArray.get(i).getForce();
		double rads = toRad(forceArray.get(i).getVector());
		/* if (rads > 2 * Math.PI){
			rads =- (rads/(2* Math.PI))*(2*Math.PI); 
		}*/
		int xInset = Math.round((float) (50 * Math.cos(rads)));  // why multiply 50?
		int yInset = -Math.round((float) (50 * Math.sin(rads))); 
		int x = Math.round((float) (xInset + newtons * Math.cos(rads)));
		int y = Math.round((float) (yInset- newtons * Math.sin(rads)));
		g.drawLine(0, 0, x, y);
		
	}
		ArrayList<Force> xComp = new ArrayList<>();
		ArrayList<Force> yComp = new ArrayList<>();
		double netForceX = 0, netForceY = 0;
		for(int i =0; i< forceArray.size(); i++)
		{
			xComp.add(forceArray.get(i).getCompForceX());
			yComp.add(forceArray.get(i).getCompForceY());
			netForceX += xComp.get(i).getForce();
			netForceY += yComp.get(i).getForce();
		}
		netForce = Math.sqrt(Math.pow(netForceX,2) + Math.pow(netForceY, 2));
		
		/*
		double vec1 = forceArray.get(1).getVector(); //
		double vec2 = forceArray.get(2).getVector();
		
			if (Math.abs(vec1 - vec2) < 180) {
				netVector = toDeg(Math.acos(netForceX /netForce)); 
			}
			
			else { netVector = toDeg(Math.asin(netForceY /netForce));}
			
			if (forceArray.size() > 2) {
				for (int i=3; i < forceArray.size(); i++) 
				{ 
					double vec = forceArray.get(i).getVector();
					if (Math.abs(netVector - vec) < 180) {
						netVector = toDeg(Math.acos(netForceX /netForce)); 
					}
					
					else { netVector = toDeg(Math.asin(netForceY /netForce));}
				}
			} */
			netVector = toDeg(Math.atan2(netForceY, netForceX));
		//netVector = toDeg(Math.atan(netForceY /netForceX));
		//	netVector = toDeg(Math.asin(netForceY /netForce));
		//netVector = toDeg(Math.acos(netForceX /netForce));  // change trig to change angle 
		if(netVector <0){netVector += 360;}
		
		double rads = toRad(netVector);
		int xInset = Math.round((float) (50 * Math.cos(rads)));
		int yInset = -Math.round((float) (50 * Math.sin(rads))); 
		int x = Math.round((float) (xInset + netForce * Math.cos(rads)));
		int y = Math.round((float) (yInset- netForce * Math.sin(rads)));
		
		
		NetPanel.forceLabel.setText("Net Force =" +(float)Renderer.netForce + "N");
		NetPanel.vectorLabel.setText("Net Vector = " + (float)Renderer.netVector +"°");
		
		g.setColor(Color.BLUE);
		g.drawLine(0, 0, x, y);
	
	g.setColor(Color.gray);
	//g.fillOval(-50, -50, 120, 120);
	g.fillRect(-50, -50, 100, 100);
	g.drawLine(-200, 50, 200, 50);
	g.dispose(); 
 }
 	private double atan2(double netForceY, double netForceX) {
	// TODO Auto-generated method stub
	return 0;
}
	public static double toRad(double deg) {return deg * Math.PI / 180;}
 	public static double toDeg (double rad) {return rad * 180 / Math.PI;}
 	
 	
}
