package model;

import util.XLException;

public class DummySlot implements Slot {

	
	public DummySlot(){

	}

	@Override
	public String getContent() {
		throw new XLException("Loop Found");
	}

	@Override
	public double value(Spreadsheet sheet) {
		throw new XLException("Loop Found");
	}

}
