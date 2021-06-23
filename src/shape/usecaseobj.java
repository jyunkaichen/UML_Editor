package shape;

import java.awt.*;

public class usecaseobj extends basicobj{
	
	public usecaseobj(int x, int y) {
		super(x, y);
		width = 90;
		height = 60;
	}
	
	public void draw(Graphics g) {
		g.drawOval(x, y, width, height);
		g.setFont(font);
		g.drawString(name, x+width/2, y+height/3);
		if(selected) this.showports(g);
	}
	
	// 橢圓形需要特別處理
	public boolean mousepressedinobj(int x, int y) {
		int a = getWidth()/2, b = getHeight()/2;
		int centerx = getX() + a, centery = getY() + b;	
		double v = Math.pow(centerx - x , 2)/Math.pow (a , 2) + Math.pow(centery - y , 2)/Math.pow(b , 2);
    	return v < 1 ;
	}
	
}

