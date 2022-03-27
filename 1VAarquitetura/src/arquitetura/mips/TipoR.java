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
		case "sub": 
			return sub();
		case "slt": 
			return slt();
		case "and": 
			return and();
		case "or": 
			return or();
		case "xor": 
			return xor();
		case "nor": 
			return nor();
		case "mfhi": 
			return mfhi();
		case "mflo": 
			return mflo();
		case "addu": 
			return addu();
		case "subu":
			return subu();
		case "mult": 
			return mult();
		case "multu": 
			return multu();
		case "div": 
			return div();
		case "divu":
			return divu();
		case "sll": 
			return sll();
		case "srl":
			return srl();
		case "sra":
			return sra();
		case "sllv": 
			return sllv();
		case "srlv":
			return srlv();
		case "srav":
			return srav();
		case "jr":
			return jr();
		case "syscall":
			return syscall();
		default:
			return inst;
		}
	}
	
	/*implementacao da formatacao das instrucoes*/
	public String add() {
		return "\"add $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String sub() {
		return "\"sub $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String slt() {
		return "\"slt $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String and() {
		return "\"and $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String or() {
		return "\"or $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String xor() {
		return "\"xor $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String nor() {
		return "\"nor $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String mfhi() {
		return "\"mfhi $" + super.binarioDecimal(destinationReg) + "\","; 
	}
	
	public String mflo() {
		return "\"mflo $" + super.binarioDecimal(destinationReg) + "\",";
	}
	
	public String addu() {
		return "\"addu $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String subu() {
		return "\"subu $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String mult() {
		return "\"mult $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String multu() {
		return "\"multu $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String div() {
		return "\"div $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String divu() {
		return "\"divu $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String sll() {
		return "\"sll $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg2) + ", " + super.binarioDecimal(shiftAmount) + "\",";
	}
	
	public String srl() {
		return "\"srl $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg2) + ", " + super.binarioDecimal(shiftAmount) + "\",";
	}
	
	public String sra() {
		return "\"sra $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg2) + ", " + super.binarioDecimal(shiftAmount) + "\",";
	}
	
	public String sllv() {
		return "\"sllv $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg2) + ", $" + super.binarioDecimal(sourceReg1) + "\",";
	}
	
	public String srlv() {
		return "\"srlv $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg2) + ", $" + super.binarioDecimal(sourceReg1) + "\",";
	}
	
	public String srav() {
		return "\"srav $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg2) + ", $" + super.binarioDecimal(sourceReg1) + "\",";
	}
	
	public String jr() {
		return "\"jr $" + super.binarioDecimal(sourceReg1) + "\",";
	}
	
	public String syscall() {
		return "\"syscall\",";
	}

	/*funcoes gets*/
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
