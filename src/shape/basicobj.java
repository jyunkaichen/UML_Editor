package shape;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import editor.Canvas;

public class basicobj {
	
	protected int x, y, width, height;
	protected boolean selected = false;
	protected boolean incompositionobj = false;
	
	protected ArrayList<basicobj> insideobjs = new ArrayList<basicobj>();
	protected ArrayList<linerecorder> linerecords = new ArrayList<linerecorder>();
	
	protected String name = "name";
	protected Font font = new Font(Font.DIALOG, Font.BOLD, 16);
	
	public basicobj(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public void setname(String name) {this.name = name;}
	
	public void setselected() {this.selected = true;}
	public void unselected() {this.selected = false;}	
	public void setincompositionobj() {this.incompositionobj = true;}
	public void unsetincompositionobj() {this.incompositionobj = false;}
	public boolean incompositionobj() {return incompositionobj;}
	
	public void showports(Graphics g) { 
		g.drawRect(x+width/2-5, y-10, 10, 10);
		g.drawRect(x+width/2-5, y+height, 10, 10);
		g.drawRect(x+width, y+height/2-5, 10, 10);
		g.drawRect(x-10, y+height/2-5, 10, 10);
	}
	
	public void changename() {
		String name = JOptionPane.showInputDialog("Change Object Name");
		if(name == null) return;
		else {
			this.setname(name);
			Canvas.getInstance().repaint();
		}
	}
	
	public void resetpoint(int deltax, int deltay) {
		
		this.x += deltax;
		this.y += deltay;
		
		// reset the object's lines' point
		for(linerecorder linerecord : linerecords) {
			if(linerecord.start) 
				linerecord.line.resetpoint(deltax, deltay);
			else if(!linerecord.start) 
				linerecord.line.resetendpoint(deltax, deltay);
		}
		// reset all objects in composition objects
		for(basicobj obj : insideobjs) 
			obj.resetpoint(deltax, deltay);	
	}
	public void resetsize(int x, int y, int width, int height) {}

	public boolean mousepressedinobj(int x, int y) {
		if(x >= getX() && x <= getX()+getWidth() && 
	       y >= getY() && y <= getY()+getHeight())
			return true;
		return false;
	}
	
	public void groupobjs(ArrayList<basicobj> objsinselectedarea) {}
	public void ungroupobjs() {}
	public void setinsideobjs(ArrayList<basicobj> insideobjs) {
		this.insideobjs = insideobjs;
		for(basicobj obj : this.insideobjs) {
			obj.setincompositionobj();
		}
	}
	public ArrayList<basicobj> getinsideobjs(){
		return insideobjs;
	}
		
	public boolean cancreateline(int x, int y) {
		return mousepressedinobj(x, y);
	}
	public void linesadd(basicline line, boolean start) {
		linerecords.add(new linerecorder(line, start));
	}
	private class linerecorder {
		
		public basicline line;
		public boolean start;
		
		public linerecorder(basicline line, boolean start) {
			this.line = line;
			this.start = start;
		}	
	}
	
}
