package data;

public class DummySlot implements Slot {

	
	public DummySlot(){

	}

	@Override
	public String getContent() {
		throw new XLException("Dummy Content Requested");
	}

	@Override
	public double value(Spreadsheet sheet) {
		throw new XLException("Loop Found");
	}

}
