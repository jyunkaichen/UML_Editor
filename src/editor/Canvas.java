package editor;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import mode.*;
import shape.*;

public class Canvas extends JPanel {
	
	private static Canvas instance = null; // for singleton
	
	private basicmode currentmode = null;
	
	public ArrayList<basicobj> objs = new ArrayList<basicobj>(); // for all objects on the canvas
	public ArrayList<basicline> lines = new ArrayList<basicline>(); // for all lines on the canvas
	
	private basicobj selectedobj = null;
	private basicobj selectedarea = null;
	public ArrayList<basicobj> objsinselectedarea = new ArrayList<basicobj>();
	
	public Canvas() {		
		this.setLayout(null);
        this.setOpaque(true);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));         
	}
	
	@Override
    public void paintComponent(Graphics g) {
		
        super.paintComponent(g);
        
        // draw all objects and lines 
        for(basicobj obj : objs) obj.draw(g);
        for(basicline line : lines) line.draw(g);
    }
	
	public void resetcanvas() { 	
		// unselected previous selected objects
		unselectedobj();
	    // unselected previous selected area
		unselectedarea();
		// repaint canvas
		repaint();
	}
	
	public void setcurrentmode(basicmode mode) {
		resetcanvas();
		removeMouseListener(currentmode);
		removeMouseMotionListener(currentmode);
		this.currentmode = mode;
		addMouseListener(currentmode);
		addMouseMotionListener(currentmode);
	}
	
	public void setselectedobj(basicobj obj) {
		selectedobj = obj;
		if(selectedobj != null) 	
			selectedobj.setselected();
	}
	public void unselectedobj() {
		if(selectedobj != null) 
			selectedobj.unselected(); 
		selectedobj = null;
	}
	public basicobj getselectedobj() {
		return selectedobj;
	}
	
	public void setselectedarea(int sasx, int sasy, int saex, int saey) {
		resetcanvas();	
		selectedarea = new compositionobj(sasx, sasy, saex, saey);
	}
	public void unselectedarea() {
		for(basicobj obj : objsinselectedarea)
			if(obj != null) obj.unselected();
		objsinselectedarea = new ArrayList<basicobj>();
	}
	public basicobj getselectedarea() {
		return selectedarea;
	}
	
	public void Groupobjs() {
		if(selectedarea != null) 
			selectedarea.groupobjs(objsinselectedarea);
	}
	
	public void UnGroupobjs() {
		if(selectedobj != null) {
			selectedobj.ungroupobjs();
			selectedobj = null;
		}
	}
	
	public void Changeobjname() {
		if(selectedobj != null) 
			selectedobj.changename();
	}
	
	public static Canvas getInstance() {
		if (instance == null) {
			instance = new Canvas();
		}
		return instance;
	}

}
