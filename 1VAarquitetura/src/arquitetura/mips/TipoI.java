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
	
	//funcao para qunado inicializar dividir o codico binario em seus respectivos cortes
	public void atribuir(String bin) {
		opcode = super.retiraOpcode();
		sourceReg = bin.substring(6, 11);
		destinationReg = bin.substring(11, 16);
		offset = bin.substring(16, 32);
	}
	
	//funcao para completar os 32 bits do offset para se traduzir corretamente para decimal
	public String completar32(String bin) {
		while (bin.length() < 32){
			bin = bin.charAt(0) + bin;
		}
		return bin;
	}
	
	//funcao que identifica qual instrucao deve ser utilizada e executa sua respectiva funcao
	public String intrucao() {
		String inst = this.getDeco().buscarInst(opcode);
		switch (inst) {
		case "lui": 
			return lui();
		case "addi": 
			return addi();
		case "slti": 
			return slti();
		case "andi": 
			return andi();
		case "ori": 
			return ori();
		case "xori": 
			return xori();
		case "lw": 
			return lw();
		case "sw": 
			return sw();
		case "bltz": 
			return bltz();
		case "beq": 
			return beq();
		case "bne": 
			return bne();
		case "addiu": 
			return addiu();
		case "lb": 
			return lb();
		case "lbu": 
			return lbu();
		case "sb": 
			return sb();
		default:
			return inst;
		}
	}
	
	/*implementacao da formatacao das instrucoes*/
	public String lui() {
		return "\"lui $" + super.binarioDecimal(destinationReg) + ", " + super.binarioDecimal(completar32(offset)) + "\",";
	}

	public String addi() {
		return "\"addi $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg) + 
				", " + super.binarioDecimal(completar32(offset)) + "\",";
	}
	
	public String slti() {
		return "\"slti $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg) +
				", " + super.binarioDecimal(completar32(offset)) + "\",";
	}
	
	public String andi() {
		return "\"andi $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg) +
				", " + super.binarioDecimal(completar32(offset)) + "\",";
	}
	
	public String ori() {
		return "\"ori $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg) +
				", " + super.binarioDecimal(completar32(offset)) + "\",";
	}
	
	public String xori() {
		return "\"xori $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg) +
				", " + super.binarioDecimal(completar32(offset)) + "\",";
	}
	
	public String lw() {
		return "\"lw $" + super.binarioDecimal(destinationReg) + ", " + super.binarioDecimal(completar32(offset)) +
				"($" + super.binarioDecimal(sourceReg) + ")\",";
	}
	
	public String sw() {
		return "\"sw $" + super.binarioDecimal(destinationReg) + ", " + super.binarioDecimal(completar32(offset)) +
				"($" + super.binarioDecimal(sourceReg) + ")\",";
	}
	
	public String bltz() {
		return "\"bltz $" + super.binarioDecimal(destinationReg) + ", " + super.binarioDecimal(completar32(offset)) + "\",";
	}
	
	public String beq() {
		return "\"beq $" + super.binarioDecimal(sourceReg) + ", $" + super.binarioDecimal(destinationReg) + 
				", " + super.binarioDecimal(completar32(offset)) + "\",";
	}
	
	public String bne() {
		return "\"bne $" + super.binarioDecimal(sourceReg) + ", $" + super.binarioDecimal(destinationReg) + 
				", " + super.binarioDecimal(completar32(offset)) + "\",";
	}
	
	public String addiu() {
		return "\"addiu $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg) + 
				", " + super.binarioDecimal(completar32(offset)) + "\",";
	}
	
	public String lb() {
		return "\"lb $" + super.binarioDecimal(destinationReg) + ", " + super.binarioDecimal(completar32(offset)) +
				"($" + super.binarioDecimal(sourceReg) + ")\",";
	}
	
	public String lbu() {
		return "\"lbu $" + super.binarioDecimal(destinationReg) + ", " + super.binarioDecimal(completar32(offset)) +
				"($" + super.binarioDecimal(sourceReg) + ")\",";
	}
	
	public String sb() {
		return "\"sb $" + super.binarioDecimal(destinationReg) + ", " + super.binarioDecimal(completar32(offset)) +
				"($" + super.binarioDecimal(sourceReg) + ")\",";
	}

	/*funcoes gets*/
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
