package gui.menu;

import gui.XL;
import gui.XLList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

class CloseMenuItem extends JMenuItem implements ActionListener {
    
	private static final long serialVersionUID = 1L;
	private XL xl;
    private XLList xlList;

    public CloseMenuItem(XL xl, XLList xlList) {
        super("Close");
        this.xl = xl;
        this.xlList = xlList;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        xlList.remove(xl);
        xl.dispose();
        if (xlList.isEmpty()) {
            System.exit(0);
        } else {
            xlList.last().toFront();
        }
    }
}
