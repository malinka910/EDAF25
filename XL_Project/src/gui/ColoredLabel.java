package gui;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ColoredLabel extends JLabel {
    
	private static final long serialVersionUID = 1L;

	public ColoredLabel(String text) {
        this(text, Color.WHITE, SwingConstants.LEFT);
    }

    public ColoredLabel(String text, Color color) {
        this(text, color, SwingConstants.LEFT);
    }

    public ColoredLabel(String text, Color color, int alignment) {
        super(text, alignment);
        setBackground(color);
        setOpaque(true);
    }
}