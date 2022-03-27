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
	
	public String intrucao() {
		String inst = this.getDeco().buscarInst(opcode);
		switch (inst) {
		case "j": 
			return j();
		case "jal": 
			return jal();
		default:
			return inst;
		}
	}

	public String j() {
			return "\"j " + super.binarioDecimal(jumpTarget) + "\","; 
	}
	
	public String jal() {
			return "\"jal " + super.binarioDecimal(jumpTarget) + "\","; 
	}
		
	/*get*/	
	public String getOpcode() {
		return opcode;
	}

	public String getJumpTarget() {
		return jumpTarget;
	}
	

}
