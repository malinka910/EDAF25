package gui;

public class StatusSelectListener implements SelectListener {
	
	private CurrentLabel currentLabel;
	
	public StatusSelectListener(CurrentLabel currentLabel){
		this.currentLabel = currentLabel;
	}

	@Override
	public void selectEventOccured(SelectEvent event) {
		currentLabel.setText(event.getLabelName());
	}

}
