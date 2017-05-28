package gui;

import java.util.Observable;

import model.Spreadsheet;

public class SubmitHandler extends Observable implements SubmitListener {
	
	public SubmitHandler(){

	}

	@Override
	public void submitEventOccured(SubmitEvent submit) {
		
		// Ignore the Submit Event because bad syntax.
		if(submit.badSyntax()){
			
		// SubmitEvent from SaveMenuItem
		}else if(submit.isSave()){
			setChanged();
			notifyObservers("save="+submit.getContent());
		
		// SubmitEvent from LoadMenuItem
		}else if(submit.isLoad()){
			setChanged();
			notifyObservers("load="+submit.getContent());
			
		// SubmitEvent from ClearMenuItem
		}else if(submit.getContent() == null){
			setChanged();
			notifyObservers("clear="+submit.getCurrentSlot());
		
		// SubmitEvent from ClearAllMenuItem
		}else if(submit.getCurrentSlot()==null){
			setChanged();
			notifyObservers("clearAll=");
		
		// SubmitEvent from Editor
		}else{
			StringBuilder builder = new StringBuilder();
			builder.append(submit.getCurrentSlot());
			builder.append("=");
			builder.append(submit.getContent());
			setChanged();
			notifyObservers(builder.toString()); 
		}
	}

}
