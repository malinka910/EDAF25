package computer.software;

import computer.hardware.*;

public class Mul extends BinExpression {

	protected Mul(Operand o1, Operand o2, Operand address) {
		super(o1, o2, address, "MUL");
	}

	@Override
	protected Word expression(Word o1, Word o2) {
		return o1.mul(o2);
	}

}
