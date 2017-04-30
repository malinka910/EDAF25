package gui;

public class EditorSelectListener implements SelectListener {
	
	private Editor editor;
	
	EditorSelectListener(Editor editor){
		this.editor = editor;
	}

	@Override
	public void selectEventOccured(SelectEvent event) {
		editor.setCurrentSlot(event.getLabelName());
	}

}
