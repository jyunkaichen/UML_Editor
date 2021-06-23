package shape;

import java.awt.*;

public class classobj extends basicobj{ 
	
	public classobj(int x, int y) {
		super(x, y);
		this.width = 80;
		this.height = 90;
	}
	
	public void draw(Graphics g) {
		g.drawRect(x, y, width, height);
		g.drawLine(x, y+height/3, x+width, y+height/3);
		g.drawLine(x, y+(height/3)*2, x+width, y+(height/3)*2);
		
		g.setFont(font);
		g.drawString(name, x+width/2, y+height/3);
		
		if(selected) this.showports(g);
	}
	
}
