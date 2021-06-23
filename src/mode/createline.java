package mode;

import shape.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class createline extends basicmode{
	
    private Point startpoint, endpoint;
    private basicobj objwithline = null, startobj = null, endobj = null;
    
	public createline(String shape) {
		toolshape = shape;
	}
	
	public void mousePressed(MouseEvent e) {
		startpoint = findconnectionobj(e);
		if(startpoint != null) {
			startobj = objwithline;
		}
		endpoint = startpoint;
	}
	
	public void mouseReleased(MouseEvent e) {	
		endpoint = findconnectionobj(e);
		if(endpoint != null) {
			endobj = objwithline;
		}
		
		if(startpoint != null && endpoint != null && startobj != endobj) {
			generateline(toolshape, startpoint, endpoint);
			basicline templine = canvas.lines.get(canvas.lines.size()-1);
			startobj.linesadd(templine, true);
	    	endobj.linesadd(templine, false);
		}  		
	    
	    canvas.repaint();
	    
	    startpoint = endpoint;    
	}
	
	private Point findconnectionobj(MouseEvent e) {
		
		for(int i = canvas.objs.size()-1 ; i >= 0 ; i--) {
			
			basicobj obj = canvas.objs.get(i);
			
			// 判斷該objects是否可以create line(composition objects不行)
			if(!obj.cancreateline(e.getX(), e.getY())) {continue;} 
		
			int objx = obj.getX();
			int objy = obj.getY();
			int objw = obj.getWidth();
			int objh = obj.getHeight();
		
			Point temppoint = new Point();
			
			// (left, right)*(up, down)、center
			Point lu = new Point(); lu.x = objx; lu.y = objy;
			Point ld = new Point(); ld.x = objx; ld.y = objy + objh;
			Point ru = new Point(); ru.x = objx + objw; ru.y = objy;
			Point rd = new Point(); rd.x = objx + objw; rd.y = objy + objh;
			Point c = new Point(); c.x = objx + objw/2; c.y = objy + objh/2;
			
			// 找連到上、下、左、右哪個port
			if(intriangel(e.getPoint(), lu, ru, c)) {
				temppoint.x = objx + objw/2;
				temppoint.y = objy;
			}
			else if(intriangel(e.getPoint(), rd, ru, c)) {
				temppoint.x = objx + objw;
				temppoint.y = objy + objh/2;
			}
			else if(intriangel(e.getPoint(), ld, rd, c)) {
				temppoint.x = objx + objw/2;
				temppoint.y = objy + objh;
			}
			else if(intriangel(e.getPoint(), lu, ld, c)) {
				temppoint.x = objx;
				temppoint.y = objy + objh/2;
			}
			
			objwithline = obj;
			
			return temppoint;
		}
		return null;
	}
	
	private boolean intriangel(Point P, Point A, Point B, Point C) {
		
		int a = 0, b = 0, c = 0;  
        
        Point MA = new Point(P.x - A.x,P.y - A.y);  
        Point MB = new Point(P.x - B.x,P.y - B.y);  
        Point MC = new Point(P.x - C.x,P.y - C.y);  
              
        a = MA.x * MB.y - MA.y * MB.x;  
        b = MB.x * MC.y - MB.y * MC.x;  
        c = MC.x * MA.y - MC.y * MA.x;  
          
        if((a <= 0 && b <= 0 && c <= 0)||  
            (a > 0 && b > 0 && c > 0))  
            return true;     
        return false;  
	}

}
