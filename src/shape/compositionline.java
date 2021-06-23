package shape;

import java.awt.Graphics;
import java.awt.Point;

public class compositionline extends basicline{

	private int diamondW = 10, diamondH = 10;
	
	public compositionline(Point startpoint, Point endpoint) {
		super(startpoint, endpoint);
	}
	
	public void draw(Graphics g) {
		
		g.drawLine(x, y, endx, endy);
		
		// 三角形的點, 考慮線條角度
		int dx = endx - x, dy = endy - y;
		double D = Math.sqrt(dx*dx + dy*dy);
		double xm = D - diamondW, xn = xm, ym = diamondH, yn = -diamondH, xt;
		double sin = dy/D, cos = dx/D;
				
		xt = xm*cos - ym*sin + x;
        ym = xm*sin + ym*cos + y;
		xm = xt;

		xt = xn*cos - yn*sin + x;
		yn = xn*sin + yn*cos + y;
		xn = xt;
		        
		// 分點公式算出線上的點, 三角形的三個點與這個點連線即為一個菱形
        double xq = (diamondH*2/D)*x + ((D-diamondH*2)/D)*endx;
        double yq = (diamondH*2/D)*y + ((D-diamondH*2)/D)*endy;
   
        int[] xpoints = {endx, (int) xm, (int) xq, (int) xn};
        int[] ypoints = {endy, (int) ym, (int) yq, (int) yn};

        g.fillPolygon(xpoints, ypoints, 4);        
	}
	
}
