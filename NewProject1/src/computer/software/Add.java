package computer.software;

import computer.hardware.Word;

public class Add extends BinExpression {

	protected Add(Operand o1, Operand o2, Operand address) {
		super(o1, o2, address, "ADD");
	}

	@Override
	protected Word expression(Word o1, Word o2) {
		return o1.add(o2);
	}

}
