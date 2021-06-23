package mode;

import shape.*;
import java.awt.event.MouseEvent;

public class selectmode extends basicmode {
	
	private int sasx, sasy, saex, saey; // selected area start and end x, y
	
	public selectmode(String shape) {
		toolshape = shape;
	}
	
	public void mousePressed(MouseEvent e) {
		
		sasx = e.getX();
		sasy = e.getY();
		
		canvas.resetcanvas();
		
		// find selected objects
		for(int i = canvas.objs.size()-1 ; i >= 0 ; i-- ) {
			if(canvas.objs.get(i).mousepressedinobj(e.getX(), e.getY())) {
				canvas.setselectedobj(canvas.objs.get(i));			
				break;
			}	
		}	
		canvas.repaint();
	}
	
	public void mouseDragged(MouseEvent e) {
		
		saex = e.getX();
		saey = e.getY();
		
		basicobj selectedobj = canvas.getselectedobj();
		
		// 有選到objects，進行Drag
		if(selectedobj != null) {
			selectedobj.resetpoint(saex - sasx, saey - sasy);
			sasx = saex;
			sasy = saey;
		}	
		// 沒有選到objects，要拉selected area
		else {
			canvas.setselectedarea(sasx, sasy, saex, saey);	
			// find the objects in selected area
			for(basicobj obj : canvas.objs) { 
				if(inselectedarea(obj) && !obj.incompositionobj()) {
					obj.setselected();
				    canvas.objsinselectedarea.add(obj);	
				}
			}
		}		
		canvas.repaint();
	}
	
	public boolean inselectedarea(basicobj obj) {
		int ax = Math.min(sasx,saex), ay = Math.min(sasy,saey);
		int aw = Math.abs(sasx-saex), ah = Math.abs(sasy-saey);
		if(obj.getX() >= ax && obj.getY() >= ay &&
		   obj.getX() + obj.getWidth() <= ax + aw && 
		   obj.getY() + obj.getHeight() <= ay + ah) {
			return true;
		}
		return false;
	}
		
}
