package arquitetura.mips;

public class TipoR extends mips {
	private String opcode;
	private String sourceReg1;
	private String sourceReg2;
	private String destinationReg;
	private String shiftAmount;
	private String opcodeExt;
	
	public TipoR(String hexa) {
		super(hexa);
		atribuir(super.getBin());
	}
	
	public void atribuir(String bin) {
		opcode = super.retiraOpcode();
		sourceReg1 = bin.substring(6, 11);
		sourceReg2 = bin.substring(11, 16);
		destinationReg = bin.substring(16, 21);
		shiftAmount = bin.substring(21, 26);
		opcodeExt = bin.substring(26, 32);
	}
	
	public String intrucao() {
		String inst = this.getDeco().buscarInstR(this.opcodeExt);
		switch (inst) {
		case "add": 
			return add();
		default:
			return inst;
		}
	}
	
	public String add() {
		return "add $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2);
	}

	/*funçoes gets*/
	public String getOpcode() {
		return opcode;
	}

	public String getSourceReg1() {
		return sourceReg1;
	}

	public String getSourceReg2() {
		return sourceReg2;
	}

	public String getDestinationReg() {
		return destinationReg;
	}

	public String getShiftAmount() {
		return shiftAmount;
	}

	public String getOpcodeExt() {
		return opcodeExt;
	}


	
	
}
