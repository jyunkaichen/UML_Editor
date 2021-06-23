package mode;

import java.awt.event.MouseEvent;
import shape.*;

public class createobj extends basicmode {
	
	public createobj(String shape) {
		toolshape = shape;
	}
	
	public void mousePressed(MouseEvent e) {
		generateobj(toolshape, e.getX(), e.getY());
		canvas.repaint();
	}
	
}
