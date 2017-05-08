package gui.menu;

import gui.StatusLabel;
import gui.SubmitListener;
import gui.XL;
import gui.XLList;
import model.Spreadsheet;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class XLMenuBar extends JMenuBar {
    
	private static final long serialVersionUID = 1L;
	
	private ClearMenuItem clearMenuItem;

	public XLMenuBar(XL xl, XLList xlList, StatusLabel statusLabel, SubmitListener controller) {
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        file.add(new PrintMenuItem(xl, statusLabel));
        SaveMenuItem save = new SaveMenuItem(xl, statusLabel);
        save.addSubmitListener(controller);
        file.add(save);
        LoadMenuItem load = new LoadMenuItem(xl, statusLabel);
        load.addSubmitListener(controller);
        file.add(load);
        file.add(new NewMenuItem(xl));
        file.add(new CloseMenuItem(xl, xlList));
        ClearMenuItem clear = new ClearMenuItem();
        clear.addSubmitListener(controller);
        edit.add(clear);
        ClearAllMenuItem clearAll = new ClearAllMenuItem();
        clearAll.addSubmitListener(controller);
        edit.add(clearAll);
        add(file);
        add(edit);
        add(new WindowMenu(xlList));
        this.clearMenuItem = clear;
    }
	
	public ClearMenuItem getClearMenuItem(){
		return clearMenuItem;
	}
	
}