package gui.menu;

import gui.StatusLabel;
import gui.XL;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;

class LoadMenuItem extends OpenMenuItem {
 
	private static final long serialVersionUID = 1L;

	public LoadMenuItem(XL xl, StatusLabel statusLabel) {
        super(xl, statusLabel, "Load");
    }

    protected void action(String path) throws FileNotFoundException {
        // TODO
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showOpenDialog(xl);
    }
}