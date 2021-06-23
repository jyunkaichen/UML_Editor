package editor;

import java.awt.*; 
import javax.swing.*;

public class EditorFrame extends JFrame {
	
	private Canvas canvas;
	private ToolBar toolbar;
	private MenuBar menubar; 
	
	public EditorFrame() {
		
		canvas = Canvas.getInstance(); // Canvas is singleton 
		toolbar = new ToolBar();
		menubar = new MenuBar();
		
		add(toolbar,BorderLayout.WEST);
		add(menubar,BorderLayout.NORTH);
		add(canvas,BorderLayout.CENTER);
		
		this.setTitle("UML editor");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setSize(960, 560);
		this.setLocationRelativeTo(null);
		this.setVisible(true);	
	}
	
}
