package shape;

import java.awt.*;

public class basicline {
	
	protected int x, y, endx, endy;
	protected Point startpoint, endpoint;

	public basicline(Point startpoint, Point endpoint) {
		this.startpoint = startpoint;
		this.endpoint = endpoint;
		this.x = startpoint.x;
		this.y = startpoint.y;
		this.endx = endpoint.x;
		this.endy = endpoint.y;
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getendX() {return endx;}
	public int getendY() {return endy;}	
	public void draw(Graphics g) {}
	public void resetpoint(int deltax, int deltay) {
		this.x += deltax;
		this.y += deltay;
	}
	public void resetendpoint(int deltax, int deltay) {
		this.endx += deltax;
		this.endy += deltay;
	}

}
