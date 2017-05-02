package gui.menu;

import gui.StatusLabel;
import gui.XL;
import gui.XLList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import data.Spreadsheet;

public class XLMenuBar extends JMenuBar {
    
	private static final long serialVersionUID = 1L;
	
	private ClearMenuItem clearMenuItem;

	public XLMenuBar(XL xl, XLList xlList, StatusLabel statusLabel, Spreadsheet spreadsheet) {
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        file.add(new PrintMenuItem(xl, statusLabel));
        file.add(new SaveMenuItem(xl, statusLabel));
        file.add(new LoadMenuItem(xl, statusLabel));
        file.add(new NewMenuItem(xl));
        file.add(new CloseMenuItem(xl, xlList));
        ClearMenuItem clear = new ClearMenuItem();
        clear.addSubmitListener(spreadsheet);
        //System.out.println("XLMenuBar.addSubmitListener()");
        edit.add(clear);
        ClearAllMenuItem clearAll = new ClearAllMenuItem();
        clearAll.addSubmitListener(spreadsheet);
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