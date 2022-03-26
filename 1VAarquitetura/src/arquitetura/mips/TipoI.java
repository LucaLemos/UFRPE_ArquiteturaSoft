package arquitetura.mips;

public class TipoI extends mips {
	private String opcode;
	private String sourceReg;
	private String destinationReg;
	private String offset;

	public TipoI(String hexa) {
		super(hexa);
		atribuir(super.getBin());
	}
	
	public void atribuir(String bin) {
		opcode = super.retiraOpcode();
		sourceReg = bin.substring(6, 11);
		destinationReg = bin.substring(11, 16);
		offset = bin.substring(16, 32);
	}

	public String getOpcode() {
		return opcode;
	}

	public String getSourceReg() {
		return sourceReg;
	}

	public String getDestinationReg() {
		return destinationReg;
	}

	public String getOffset() {
		return offset;
	}
	
}
