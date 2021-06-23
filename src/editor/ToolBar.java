package editor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import mode.*;

public class ToolBar extends JToolBar{
	
	private Canvas canvas; 
	private int ToolBarNum = 6;	
	private JButton holdBtn = null;
	
	public ToolBar() {	
		
		canvas = Canvas.getInstance(); // Canvas is singleton 

		setLayout(new GridLayout(ToolBarNum, 1, 3, 3));
		this.setBackground(new Color(0, 0, 0));
		
		ToolBtn selectBtn = new ToolBtn(new ImageIcon("img/select.png"), new selectmode("select"));
		add(selectBtn);
			 
		ToolBtn associateBtn = new ToolBtn(new ImageIcon("img/associate.png"), new createline("associationline"));
		add(associateBtn);
			 
		ToolBtn generalBtn = new ToolBtn(new ImageIcon("img/general.png"), new createline("generalline"));
		add(generalBtn);
		
		ToolBtn compositeBtn = new ToolBtn(new ImageIcon("img/composite.png"), new createline("compositionline"));
		add(compositeBtn);
		 		 
		ToolBtn classBtn = new ToolBtn(new ImageIcon("img/class.png"), new createobj("class"));
		add(classBtn);
		 
		ToolBtn usecaseBtn = new ToolBtn(new ImageIcon("img/usecase.png"), new createobj("usecase"));
		add(usecaseBtn);
	}
	
	private class ToolBtn extends JButton{
		
		private basicmode toolmode;
		
		public ToolBtn(ImageIcon icon, basicmode Toolmode) {
			toolmode = Toolmode;		
			setIcon(icon);
			setFocusable(false);
			setBackground(new Color(255, 255, 255));
			setBorderPainted(false);
			setRolloverEnabled(true);
			addActionListener(new toolListener());
		}
		
		class toolListener implements ActionListener {			
			@Override
		    public void actionPerformed(ActionEvent e) {
				if(holdBtn != null)
					holdBtn.setBackground(new Color(255, 255, 255));
				holdBtn = (JButton) e.getSource();
				holdBtn.setBackground(new Color(80, 80, 80));
				canvas.setcurrentmode(toolmode);
			}		
		}	
	}
	
}
