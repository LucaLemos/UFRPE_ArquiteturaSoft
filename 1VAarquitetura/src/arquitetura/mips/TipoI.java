package arquitetura.mips;

public class TipoI extends mips {
	private String opcode;
	private String sourceReg;
	private String destinationReg;
	private String offset;

	public TipoI(String hexa, registradores reg) {
		super(hexa, reg);
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
		String luiV = offset + "0000000000000000";
		super.registrarHexa(super.binarioDecimal(destinationReg), super.binarioHexa(luiV));
		return "\"lui $" + super.binarioDecimal(destinationReg) + ", " + super.binarioDecimal(completar32(offset)) + "\",";
	}

	public String addi() {
		pcatt();
		long limUp = super.binarioDecimal("01111111111111111111111111111111");
		long limDown = super.binarioDecimal("10000000000000000000000000000000");
		long som1 = super.hexaDecimal(super.resgatar(super.binarioDecimal(sourceReg)));
		long som2 = super.binarioDecimal(completar32(offset));
		long soma = som1 + som2;
		if (soma > limUp || soma < limDown) {
			super.setStdout("\"overflow\"");
		}else {
			super.registrarLong(super.binarioDecimal(destinationReg), soma);
		}
		
		return "\"addi $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg) + 
				", " + super.binarioDecimal(completar32(offset)) + "\",";
	}
	
	public String slti() {
		pcatt();
		long slt1 = super.hexaDecimal(super.resgatar(super.binarioDecimal(sourceReg)));
		long slt2 = super.binarioDecimal(completar32(offset));
		if (slt1 < slt2) {
			super.registrarLong(super.binarioDecimal(destinationReg), 1);
		}else {
			super.registrarLong(super.binarioDecimal(destinationReg), 0);
		}
		
		return "\"slti $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg) +
				", " + super.binarioDecimal(completar32(offset)) + "\",";
	}
	
	public String andi() {
		pcatt();
		String andi1 = super.hexaBinario(super.resgatar(super.binarioDecimal(sourceReg)));
		String andi2 = completar32(offset);
		String andi = "";
		for(int i = 0; i < andi1.length(); i++) {
			if(andi1.charAt(i) == andi2.charAt(i)) {
				andi = andi + andi1.charAt(i);
			}else andi = andi + "0";
		}
		super.registrarInt(super.binarioDecimal(destinationReg), super.binarioDecimal(andi));
		
		return "\"andi $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg) +
				", " + super.binarioDecimal(completar32(offset)) + "\",";
	}
	
	public String ori() {
		pcatt();
		String ori1 = super.hexaBinario(super.resgatar(super.binarioDecimal(sourceReg)));
		String ori2 = completar32(offset);
		String ori = "";
		for(int i = 0; i < ori1.length(); i++) {
			if(ori1.charAt(i) == '1' || ori2.charAt(i) == '1') {
				ori = ori + "1";
			}else if (ori1.charAt(i) == 'x' || ori2.charAt(i) == 'x') {
				ori = ori + "x";
			}else ori = ori + "0";
		}
		super.registrarInt(super.binarioDecimal(destinationReg), super.binarioDecimal(ori));
		
		return "\"ori $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg) +
				", " + super.binarioDecimal(completar32(offset)) + "\",";
	}
	
	public String xori() {
		pcatt();
		String xori1 = super.hexaBinario(super.resgatar(super.binarioDecimal(sourceReg)));
		String xori2 = completar32(offset);
		String xori = "";
		for(int i = 0; i < xori1.length(); i++) {
			if(xori1.charAt(i) == '1' && xori2.charAt(i) == '0') {
				xori = xori + "1";
			}else if(xori1.charAt(i) == '0' && xori2.charAt(i) == '1') {
				xori = xori + "1";
			}else if (xori1.charAt(i) == 'x' || xori2.charAt(i) == 'x') {
				xori = xori + "x";
			}else xori = xori + "0";
		}
		super.registrarInt(super.binarioDecimal(destinationReg), super.binarioDecimal(xori));
		
		return "\"xori $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg) +
				", " + super.binarioDecimal(completar32(offset)) + "\",";
	}
	
	public String lw() {
		pcatt();
	
		return "\"lw $" + super.binarioDecimal(destinationReg) + ", " + super.binarioDecimal(completar32(offset)) +
				"($" + super.binarioDecimal(sourceReg) + ")\",";
	}
	
	public String sw() {
		pcatt();
		
		return "\"sw $" + super.binarioDecimal(destinationReg) + ", " + super.binarioDecimal(completar32(offset)) +
				"($" + super.binarioDecimal(sourceReg) + ")\",";
	}
	
	public String bltz() {
		int bltz1 = super.hexaDecimal(super.resgatar(super.binarioDecimal(destinationReg)));
		int bltzOff = super.binarioDecimal(completar32(offset));
		
		if (bltz1 < 0) {
			long valor = super.hexaDecimalUnisined(super.resgatar(32));
			valor = valor + bltzOff;
			super.registrarLong(32, valor);
		}else {
			pcatt();
		}
		
		return "\"bltz $" + super.binarioDecimal(destinationReg) + ", " + super.binarioDecimal(completar32(offset)) + "\",";
	}
	
	public String beq() {
		int beq1 = super.hexaDecimal(super.resgatar(super.binarioDecimal(sourceReg)));
		int beq2 = super.hexaDecimal(super.resgatar(super.binarioDecimal(destinationReg)));
		int beqOff = super.binarioDecimal(completar32(offset));
		
		if (beq1 == beq2) {
			long valor = super.hexaDecimalUnisined(super.resgatar(32));
			valor = valor + beqOff;
			super.registrarLong(32, valor);
		}else {
			pcatt();
		}
		
		return "\"beq $" + super.binarioDecimal(sourceReg) + ", $" + super.binarioDecimal(destinationReg) + 
				", " + super.binarioDecimal(completar32(offset)) + "\",";
	}
	
	public String bne() {
		int bne1 = super.hexaDecimal(super.resgatar(super.binarioDecimal(sourceReg)));
		int bne2 = super.hexaDecimal(super.resgatar(super.binarioDecimal(destinationReg)));
		int bneOff = super.binarioDecimal(completar32(offset));
		
		if (bne1 != bne2) {
			long valor = super.hexaDecimalUnisined(super.resgatar(32));
			valor = valor + bneOff;
			super.registrarLong(32, valor);
		}else {
			pcatt();
		}
		
		return "\"bne $" + super.binarioDecimal(sourceReg) + ", $" + super.binarioDecimal(destinationReg) + 
				", " + super.binarioDecimal(completar32(offset)) + "\",";
	}
	
	public String addiu() {
		pcatt();
		long som1 = super.hexaDecimalUnisined(super.resgatar(super.binarioDecimal(sourceReg)));
		long som2 = super.binarioDecimalUnisigned(completar32(offset));
		long soma = som1 + som2;
		super.registrarLong(super.binarioDecimal(destinationReg), soma);
		
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
	
	
	//atualiza o reg pc
	public void pcatt() {
		long valor = super.hexaDecimalUnisined(super.resgatar(32));
		valor = valor + 4;
		super.registrarLong(32, valor);
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
