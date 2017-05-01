package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingConstants;

import data.Spreadsheet;

public class SlotLabels extends GridPanel {
    
	private static final long serialVersionUID = 1L;
	
	private List<SlotLabel> labelList;
    private SlotLabel currentSlot;

    public SlotLabels(int rows, int cols, CurrentLabel currentLabel, Editor editor, Spreadsheet spreadsheet, StatusLabel statusLabel) {
        super(rows + 1, cols);
        labelList = new ArrayList<SlotLabel>(rows * cols);
        for (char ch = 'A'; ch < 'A' + cols; ch++) {
            add(new ColoredLabel(Character.toString(ch), Color.LIGHT_GRAY,
                    SwingConstants.CENTER));
        }
        for (int row = 1; row <= rows; row++) {
            for (char ch = 'A'; ch < 'A' + cols; ch++) {
            	StringBuilder builder = new StringBuilder();
            	builder.append(ch);
            	builder.append(row);
                SlotLabel label = new SlotLabel(builder.toString());
                label.addSelectListener(editor);
                label.addSelectListener(statusLabel);
                label.addSelectListener(currentLabel);
                label.addSelectListener(new SelectListener(){
                	public void selectEventOccured(SelectEvent select){
                		String labelName = select.getLabelName();
                		setCurrentSlot(labelList.get(labelIndex(labelName)));
                	}
                });
                add(label);
                spreadsheet.addObserver(label);
                labelList.add(label);
            }
        }
        currentSlot = labelList.get(0);
        currentSlot.setBackground(Color.YELLOW);
    }
    
    private void setCurrentSlot(SlotLabel selected){
    	currentSlot.setBackground(Color.WHITE);
    	currentSlot = selected;
    	currentSlot.setBackground(Color.YELLOW);
    }
    
    private int labelIndex(String labelName){
    	char letter = labelName.charAt(0);
    	int number = Integer.parseInt(labelName.substring(1));
    	int index = (letter-'A') + ((number-1)*8);
    	return index;
    }
	
    
}
