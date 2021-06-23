package shape;

import java.awt.Graphics;
import java.awt.Point;

public class associationline extends basicline{

	private int arrowW = 10, arrowH = 10;
	
	public associationline(Point startpoint, Point endpoint) {
		super(startpoint, endpoint);
	}
	
	public void draw(Graphics g) {
		g.drawLine(x, y, endx, endy);
		
		// 三角形的點, 考慮線條角度
		int dx = endx - x, dy = endy - y;
		double D = Math.sqrt(dx*dx + dy*dy);
		double xm = D - arrowW, xn = xm, ym = arrowH, yn = -arrowH, xt;
		double sin = dy/D, cos = dx/D;
		
		xt = xm*cos - ym*sin + x;
        ym = xm*sin + ym*cos + y;
        xm = xt;

        xt = xn*cos - yn*sin + x;
        yn = xn*sin + yn*cos + y;
        xn = xt;

        g.drawLine((int)xm, (int)ym, endx, endy);
        g.drawLine((int)xn, (int)yn, endx, endy);		
	}
	
}
