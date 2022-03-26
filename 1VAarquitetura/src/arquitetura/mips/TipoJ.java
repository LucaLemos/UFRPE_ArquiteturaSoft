package arquitetura.mips;

public class TipoJ extends mips {
	private String opcode;
	private String jumpTarget;
	
	public TipoJ(String hexa) {
		super(hexa);
		atribuir(super.getBin());
	}
	
	public void atribuir(String bin) {
		opcode = super.retiraOpcode();
		jumpTarget = bin.substring(6, 32);
	}

	public String getOpcode() {
		return opcode;
	}

	public String getJumpTarget() {
		return jumpTarget;
	}
	

}
