package gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.menu.XLMenuBar;
import model.Spreadsheet;

public class XL extends JFrame implements Printable {
    
	private static final long serialVersionUID = 1L;
	private static final int ROWS = 10, COLUMNS = 8;
    private XLCounter counter;
    private XLList xlList;
    
    private StatusLabel statusLabel = new StatusLabel();
    private Editor editor = new Editor(statusLabel);
    private CurrentLabel currentLabel = new CurrentLabel();
    private Spreadsheet spreadsheet; 
    private XLMenuBar xlMenuBar;
    private Controller controller = new Controller();

    public XL(XL oldXL) {
        this(oldXL.xlList, oldXL.counter);
    }

    public XL(XLList xlList, XLCounter counter) {
        super("Untitled-" + counter);
        this.xlList = xlList;
        this.counter = counter;
        xlList.add(this);
        counter.increment();

        spreadsheet = new Spreadsheet();
        controller.addObserver(spreadsheet);
        
        // Create the menu bar so we can get at the ClearMenuItem
        xlMenuBar = new XLMenuBar(this, xlList, statusLabel, controller);
        
        // Add observers and ExceptionListeners to the spreadsheet.
        spreadsheet.addObserver(statusLabel);// clear the error line when submission excepted.
        spreadsheet.addObserver(editor); // needs to know what to display when the model changes state.
        
        // Add SubmitListeners and ExceptionListeners to the editor.
        editor.addSubmitListener(controller);
        editor.addExceptionListener(statusLabel);// listens for exceptions in the GUI.
        
        // Construct the GUI.
        JPanel statusPanel = new StatusPanel(statusLabel, currentLabel);
        // Pass all of the SelectListeners to the SheetPanel constructor. These will be added to the SlotLabels as 
        // SelectListeners as they are constructed.
        JPanel sheetPanel = new SheetPanel(ROWS, COLUMNS, currentLabel, editor, spreadsheet, statusLabel, xlMenuBar.getClearMenuItem());
        
        // Put everything together.
        add(NORTH, statusPanel);
        add(CENTER, editor);
        add(SOUTH, sheetPanel);
        setJMenuBar(xlMenuBar);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public int print(Graphics g, PageFormat pageFormat, int page) {
        if (page > 0)
            return NO_SUCH_PAGE;
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        printAll(g2d);
        return PAGE_EXISTS;
    }

    public void rename(String title) {
        setTitle(title);
        xlList.setChanged();
    }

    public static void main(String[] args) {
        new XL(new XLList(), new XLCounter());
    }
}