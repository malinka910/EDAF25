package gui;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class GridPanel extends JPanel {
    
	private static final long serialVersionUID = 1L;

	public GridPanel(int rows, int columns) {
        super(new GridLayout(rows, columns, 2, 2));
        setBackground(Color.BLACK);
    }
}