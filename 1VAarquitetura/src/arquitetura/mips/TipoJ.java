package arquitetura.mips;

public class TipoJ extends mips {
	private String opcode;
	private String jumpTarget;
	
	public TipoJ(String hexa, registradores reg) {
		super(hexa, reg);
		atribuir(super.getBin());
	}
	
	//funcao para qunado inicializar dividir o codico binario em seus respectivos cortes
	public void atribuir(String bin) {
		opcode = super.retiraOpcode();
		jumpTarget = bin.substring(6, 32);
	}
	
	//funcao que identifica qual instrucao deve ser utilizada e executa sua respectiva funcao
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

	/*implementacao da formatacao das instrucoes*/
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
