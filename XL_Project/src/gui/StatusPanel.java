package gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

public class StatusPanel extends BorderPanel {
    
	private static final long serialVersionUID = 1L;

	protected StatusPanel(StatusLabel statusLabel, CurrentLabel currentLabel) {
        add(WEST, currentLabel);
        add(CENTER, statusLabel);
    }
}