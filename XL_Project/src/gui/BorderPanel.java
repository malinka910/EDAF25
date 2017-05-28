package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

public class BorderPanel extends JPanel {
    
	private static final long serialVersionUID = 1L;

	protected BorderPanel() {
        super(new BorderLayout(2, 2));
        setBackground(Color.BLACK);
    }
}
