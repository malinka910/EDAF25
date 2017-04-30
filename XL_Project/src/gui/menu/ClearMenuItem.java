package gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

class ClearMenuItem extends JMenuItem implements ActionListener {
    
	private static final long serialVersionUID = 1L;

	public ClearMenuItem() {
        super("Clear");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        // TODO
    }
}