package editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar{
	
	private Canvas canvas;
	private JMenu editmenu;  
	private JMenuItem i1, i2, i3;
    
    public MenuBar() {
    		
    	canvas = Canvas.getInstance(); // Canvas is singleton 
    	
    	editmenu = new JMenu("Edit"); 
    	
    	i1 = new JMenuItem("Group");
    	i1.addActionListener(new GroupListener());
    	editmenu.add(i1); 
    	
    	i2 = new JMenuItem("UnGroup");
        i2.addActionListener(new UnGroupListener());
        editmenu.add(i2);
        
        i3 = new JMenuItem("Change object name");
        i3.addActionListener(new ChangeobjnameListener());
        editmenu.add(i3);  
      
        this.add(editmenu);  
    }    
    
    public class GroupListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			canvas.Groupobjs();
		}
	}
    
    public class UnGroupListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			canvas.UnGroupobjs();
		}
	}
    
    public class ChangeobjnameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			canvas.Changeobjname();
		}
	} 
    
}
