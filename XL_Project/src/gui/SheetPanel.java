package gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

import data.Spreadsheet;
import gui.menu.ClearMenuItem;
import gui.menu.XLMenuBar;

public class SheetPanel extends BorderPanel {
    
	private static final long serialVersionUID = 1L;

	public SheetPanel(int rows, int columns, CurrentLabel currentLabel, Editor editor, Spreadsheet spreadsheet, StatusLabel statusLabel, ClearMenuItem clearMenuItem) {
        add(WEST, new RowLabels(rows));
        add(CENTER, new SlotLabels(rows, columns, currentLabel, editor, spreadsheet, statusLabel, clearMenuItem));
    }
}