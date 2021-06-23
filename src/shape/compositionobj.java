package shape;

import java.awt.*;
import java.util.ArrayList;

import editor.Canvas;

public class compositionobj extends basicobj{
	
	public compositionobj(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g) {
		if(selected) g.setColor(Color.orange);
		g.drawRect(x, y, width, height);
		if(selected) g.setColor(Color.black);
	}
	
	public void resetsize(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void groupobjs(ArrayList<basicobj> objsinselectedarea) {
		Canvas canvas = Canvas.getInstance();
		
		// 單一物件不能group
		if(objsinselectedarea.size() <= 1) { 
			canvas.resetcanvas();
			return;
		}

		// 設定composition objects大小
		int minx = 960, miny = 560, maxx = 0, maxy = 0;
		int cx, cy, cw, ch; // composition object's x, y, width, height
		
		for(basicobj obj : objsinselectedarea) {		
			if(obj.getX() < minx) minx = obj.getX();
			if(obj.getY() < miny) miny = obj.getY();
			if(obj.getX() + obj.getWidth() > maxx) maxx = obj.getX() + obj.getWidth();
			if(obj.getY() + obj.getHeight() > maxy) maxy = obj.getY() + obj.getHeight();
		}
		
		cx = minx-10;
		cy = miny-10;
		cw = maxx - minx + 20;
		ch = maxy - miny + 20;
		
		// reset composition objects
		basicobj compositionobj = canvas.getselectedarea();
		compositionobj.resetsize(cx, cy, cw, ch);
		compositionobj.setinsideobjs(objsinselectedarea);
		canvas.objs.add(compositionobj);
		
		canvas.resetcanvas();
	}
	
	public void ungroupobjs() {
		Canvas canvas = Canvas.getInstance();
		for(basicobj obj : getinsideobjs()) 
			obj.unsetincompositionobj();
		unselected();
		canvas.objs.remove(this);
		canvas.repaint();
	}
	
	public void setselected() {
		selected = true;
		for(basicobj obj : insideobjs) obj.setselected();
	}
	public void unselected() {
		selected = false;
		for(basicobj obj : insideobjs) obj.unselected();
	}
	
	public boolean cancreateline(int x, int y) {
		// composition objects cannot create line
		return false;
	}
	
	public void changename() {
		// composition objects cannot change name
		return;
	}
	
}
