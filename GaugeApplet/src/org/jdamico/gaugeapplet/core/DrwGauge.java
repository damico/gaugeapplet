package org.jdamico.gaugeapplet.core;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.RenderingHints;

public class DrwGauge extends Applet {

	private static final long serialVersionUID = -4599033642290356349L;
	private int xBase = 100;
	private int yBase = 100;
	private double degrees = .0;
	private Color bColor = null;
	
    public DrwGauge() {
        this.setBackground(Color.WHITE);
        this.setForeground(Color.DARK_GRAY);
    }
    
    
    public void init(){
    	
    	double percentValue = Double.parseDouble(getParameter("percentValue"));
    	degrees = (180 * percentValue) / 100;
    	
    	String hexColor = getParameter("pointerColor");
		bColor = getColorParameter(hexColor);
    	
    	
    	setLayout(null);
    	
    	Label label_0 = new Label("0");
    	label_0.setBackground(new Color(230, 230, 230));
    	label_0.setBounds(12, 95, 20, 10);
    	add(label_0);
    	
    	Label label_25 = new Label("25");
    	label_25.setBackground(new Color(230, 230, 230));
    	label_25.setBounds(34, 40, 18, 10);
    	add(label_25);
    	
    	
    	Label label_50 = new Label("50");
    	label_50.setBackground(new Color(230, 230, 230));
    	label_50.setBounds(92, 14, 20, 10);
    	add(label_50);
    	
    	Label label_75 = new Label("75");
    	label_75.setBackground(new Color(230, 230, 230));
    	label_75.setBounds(146, 40, 18, 10);
    	add(label_75);
    	
    	Label label_100 = new Label("100");
    	label_100.setBackground(new Color(230, 230, 230));
    	label_100.setBounds(160, 95, 30, 10);
    	add(label_100);
    	
    	Label label_total = new Label(" "+percentValue+" %");
    	label_total.setBackground(new Color(230, 230, 230));
    	label_total.setBounds(77, 135, 50, 20);
    	add(label_total);
    }
    
    public void paint(Graphics g) {

    	Graphics2D g2d = (Graphics2D)g;
    	
    	g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
    	g2d.setRenderingHint( RenderingHints.KEY_RENDERING,    RenderingHints.VALUE_RENDER_QUALITY );

    	g2d.setColor(bColor);
    	g2d.fillArc(6, 6, 188, 188, 0, 360);
        g2d.setColor(new Color(230, 230, 230));
        g2d.fillArc(10, 10, 180, 180, 0, 360);
        g2d.setColor(Color.GRAY);
        g2d.fillArc(10, 10, 180, 180, 190, 160);
        drawPointer(65, degrees, g2d);
  
    }
    
    public void drawPointer(int pointerSize, double angle, Graphics2D g2d){
    	
    	g2d.setColor(Color.BLACK);
    	g2d.fillArc(85, 85, 32, 32, 0, 360);
    	g2d.setColor(bColor);
    	g2d.fillArc(85, 85, 30, 30, 0, 360);
    	g2d.drawLine(30, 70, 19, 64);
        g2d.drawLine(170, 70, 187, 64);
        g2d.drawLine(70, 30, 64, 19);
        g2d.drawLine(130, 30, 137, 14);
    	int a = pointerSize;
    	int c = pointerSize;
    	double h = .0;
    	double b = .0;
    	b = Math.sqrt((Math.pow(a, 2) + Math.pow(c, 2)) - (2 * a * c * Math.cos(Math.toRadians(angle))));
    	double cosC = (Math.pow(a, 2) - Math.pow(b, 2) - Math.pow(c, 2)) / - (2*b*c);
    	double x = b * cosC;
    	h = Math.sqrt(Math.pow(c, 2) - Math.pow((a - x), 2));
    	x = pointerSize - x;
    	int nX = 100 - (int)Math.round(x);
    	int nY = 100 - (int)Math.round(h);
    	g2d.fillArc(nX-5, nY-5, 10, 10, 0, 360);
    	g2d.drawLine(xBase, yBase, nX, nY);
    	//System.out.println("[100,100]["+nX+","+nY+"]   "+a+"   "+b+"   "+c+"   x= "+(int)Math.round(x)+"   h= "+(int)Math.round(h)+"  "+Math.cos(Math.toRadians(angle)));

    	    	
    }
    
    public Color getColorParameter(String strColor) {

		int hexConverted = 0;
		hexConverted = Integer.parseInt(strColor, 16);
		Color returnColor = Color.decode(String.valueOf(hexConverted));

		return returnColor;
	}
}