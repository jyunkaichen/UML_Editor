package mode;

import java.awt.Point;
import java.awt.event.*;
import editor.*;
import shape.*;

public class basicmode implements MouseListener, MouseMotionListener, objandlinegenerator{
	
	protected String toolshape;
	
	protected Canvas canvas = Canvas.getInstance(); // Canvas is singleton 
	
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseDragged(MouseEvent e) {
	}
	public void mouseClicked(MouseEvent e) {
	}
	public void mouseMoved(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void generateobj(String toolshape, int x, int y) {
		switch (toolshape) {
			case "class":
				canvas.objs.add(new classobj(x, y));
				break;
			case "usecase":
				canvas.objs.add(new usecaseobj(x, y));
				break;
			default:
				break;
		}
	}
	public void generateline(String toolshape, Point x, Point y) {
		switch (toolshape) {
	    	case "associationline":
	    		canvas.lines.add(new associationline(x, y));
	    		break;	
	    	case "generalline":
	    		canvas.lines.add(new generalline(x, y));
	    		break;
	    	case "compositionline":
	    		canvas.lines.add(new compositionline(x, y));
	    		break;
	    	default:
	    		break;
	    }
	}
}

interface objandlinegenerator
{
	public void generateobj(String toolshape, int x, int y);
	public void generateline(String toolshape, Point x, Point y);
}
