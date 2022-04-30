package arquitetura.mips;

import java.math.BigInteger;

public class TipoR extends mips {
	private String opcode;
	private String sourceReg1;
	private String sourceReg2;
	private String destinationReg;
	private String shiftAmount;
	private String opcodeExt;
	
	public TipoR(String hexa, registradores reg, memoria mem) {
		super(hexa, reg, mem);
		atribuir(super.getBin());
	}
	
	//funcao para qunado inicializar dividir o codico binario em seus respectivos cortes
	public void atribuir(String bin) {
		opcode = super.retiraOpcode();
		sourceReg1 = bin.substring(6, 11);
		sourceReg2 = bin.substring(11, 16);
		destinationReg = bin.substring(16, 21);
		shiftAmount = bin.substring(21, 26);
		opcodeExt = bin.substring(26, 32);
	}
	
	//funcao que identifica qual instrucao deve ser utilizada e executa sua respectiva funcao
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
		pcatt();
		long limUp = super.binarioDecimal("01111111111111111111111111111111");
		long limDown = super.binarioDecimal("10000000000000000000000000000000");
		long som1 = super.hexaDecimal(super.resgatar(super.binarioDecimal(sourceReg1)));
		long som2 = super.hexaDecimal(super.resgatar(super.binarioDecimal(sourceReg2)));
		long soma = som1 + som2;
		if (soma > limUp || soma < limDown) {
			super.setStdout("\"overflow\"");
		}else {
			super.registrarLong(super.binarioDecimal(destinationReg), soma);
		}
		
		return "\"add $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String sub() {
		pcatt();
		long limUp = super.binarioDecimal("01111111111111111111111111111111");
		long limDown = super.binarioDecimal("10000000000000000000000000000000");
		long sub1 = super.hexaDecimal(super.resgatar(super.binarioDecimal(sourceReg1)));
		long sub2 = super.hexaDecimal(super.resgatar(super.binarioDecimal(sourceReg2)));
		long sub = sub1 - sub2;
		if (sub > limUp || sub < limDown) {
			super.setStdout("\"overflow\"");
		}else {
			super.registrarLong(super.binarioDecimal(destinationReg), sub);
		}
		
		return "\"sub $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String slt() {
		pcatt();
		long slt1 = super.hexaDecimal(super.resgatar(super.binarioDecimal(sourceReg1)));
		long slt2 = super.hexaDecimal(super.resgatar(super.binarioDecimal(sourceReg2)));
		if (slt1 < slt2) {
			super.registrarLong(super.binarioDecimal(destinationReg), 1);
		}else {
			super.registrarLong(super.binarioDecimal(destinationReg), 0);
		}
		
		return "\"slt $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String and() {
		pcatt();
		String and1 = super.hexaBinario(super.resgatar(super.binarioDecimal(sourceReg1)));
		String and2 = super.hexaBinario(super.resgatar(super.binarioDecimal(sourceReg2)));
		String and = "";
		for(int i = 0; i < and1.length(); i++) {
			if(and1.charAt(i) == and2.charAt(i)) {
				and = and + and1.charAt(i);
			}else and = and + "0";
		}
		super.registrarInt(super.binarioDecimal(destinationReg), super.binarioDecimal(and));
		
		return "\"and $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String or() {
		pcatt();
		String or1 = super.hexaBinario(super.resgatar(super.binarioDecimal(sourceReg1)));
		String or2 = super.hexaBinario(super.resgatar(super.binarioDecimal(sourceReg2)));
		String or = "";
		for(int i = 0; i < or1.length(); i++) {
			if(or1.charAt(i) == '1' || or2.charAt(i) == '1') {
				or = or + "1";
			}else if (or1.charAt(i) == 'x' || or2.charAt(i) == 'x') {
				or = or + "x";
			}else or = or + "0";
		}
		super.registrarInt(super.binarioDecimal(destinationReg), super.binarioDecimal(or));
		
		return "\"or $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String xor() {
		pcatt();
		String xor1 = super.hexaBinario(super.resgatar(super.binarioDecimal(sourceReg1)));
		String xor2 = super.hexaBinario(super.resgatar(super.binarioDecimal(sourceReg2)));
		String xor = "";
		for(int i = 0; i < xor1.length(); i++) {
			if(xor1.charAt(i) == '1' && xor2.charAt(i) == '0') {
				xor = xor + "1";
			}else if(xor1.charAt(i) == '0' && xor2.charAt(i) == '1') {
				xor = xor + "1";
			}else if (xor1.charAt(i) == 'x' || xor2.charAt(i) == 'x') {
				xor = xor + "x";
			}else xor = xor + "0";
		}
		super.registrarInt(super.binarioDecimal(destinationReg), super.binarioDecimal(xor));
		
		return "\"xor $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String nor() {
		pcatt();
		String nor1 = super.hexaBinario(super.resgatar(super.binarioDecimal(sourceReg1)));
		String nor2 = super.hexaBinario(super.resgatar(super.binarioDecimal(sourceReg2)));
		String nor = "";
		for(int i = 0; i < nor1.length(); i++) {
			if(nor1.charAt(i) == '0' && nor2.charAt(i) == '0') {
				nor = nor + "1";
			}else if (nor1.charAt(i) == 'x' || nor2.charAt(i) == 'x') {
				nor = nor + "x";
			}else nor = nor + "0";
		}
		super.registrarInt(super.binarioDecimal(destinationReg), super.binarioDecimal(nor));
		
		return "\"nor $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String mfhi() {
		pcatt();
		super.registrarInt(super.binarioDecimal(destinationReg), super.hexaDecimal(super.resgatar(33)));
		
		return "\"mfhi $" + super.binarioDecimal(destinationReg) + "\","; 
	}
	
	public String mflo() {
		pcatt();
		super.registrarInt(super.binarioDecimal(destinationReg), super.hexaDecimal(super.resgatar(34)));
		
		return "\"mflo $" + super.binarioDecimal(destinationReg) + "\",";
	}
	
	public String addu() {
		pcatt();
		long som1 = super.hexaDecimalUnisined(super.resgatar(super.binarioDecimal(sourceReg1)));
		long som2 = super.hexaDecimalUnisined(super.resgatar(super.binarioDecimal(sourceReg2)));
		long soma = som1 + som2;
		super.registrarLong(super.binarioDecimal(destinationReg), soma);
		
		return "\"addu $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String subu() {
		pcatt();
		long sub1 = super.hexaDecimalUnisined(super.resgatar(super.binarioDecimal(sourceReg1)));
		long sub2 = super.hexaDecimalUnisined(super.resgatar(super.binarioDecimal(sourceReg2)));
		long sub = sub1 - sub2;
		super.registrarLong(super.binarioDecimal(destinationReg), sub);
		
		return "\"subu $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String mult() {
		pcatt();
		long mul1 = super.hexaDecimal(super.resgatar(super.binarioDecimal(sourceReg1)));		
		long mul2 = super.hexaDecimal(super.resgatar(super.binarioDecimal(sourceReg2)));
		BigInteger mulInt = BigInteger.valueOf(mul1*mul2);
		String mulBin= super.transformarBinarioMult(mulInt);	
		if (mulBin.length() > 64) {
			super.setStdout("\"overflow\"");
		}else {
			String mfhi = mulBin.substring(0, 32);
			String mflo = mulBin.substring(32, 64);
			super.registrarHexa(33, super.binarioHexa(mfhi));
			super.registrarHexa(34, super.binarioHexa(mflo));
		}
				
		return "\"mult $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String multu() {
		pcatt();
		long mul1 = super.hexaDecimalUnisined(super.resgatar(super.binarioDecimal(sourceReg1)));		
		long mul2 = super.hexaDecimalUnisined(super.resgatar(super.binarioDecimal(sourceReg2)));
		BigInteger mulInt = BigInteger.valueOf(mul1*mul2);
		String mulBin= super.transformarBinarioMult(mulInt);	
		String mfhi = mulBin.substring(mulBin.length() - 64, mulBin.length() - 32);
		String mflo = mulBin.substring(mulBin.length() - 32, mulBin.length());
		super.registrarHexa(33, super.binarioHexa(mfhi));
		super.registrarHexa(34, super.binarioHexa(mflo));
		
		return "\"multu $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String div() {
		pcatt();
		long limUp = super.binarioDecimal("01111111111111111111111111111111");
		long limDown = super.binarioDecimal("10000000000000000000000000000000");
		long div1 = super.hexaDecimal(super.resgatar(super.binarioDecimal(sourceReg1)));		
		long div2 = super.hexaDecimal(super.resgatar(super.binarioDecimal(sourceReg2)));
		if(div2 == 0) {
			super.setStdout("\"overflow\"");
		}else {
			long resto = div1%div2;
			long quociente = div1/div2;
			if (resto > limUp || resto < limDown) {
				super.setStdout("\"overflow\"");
			}else if(quociente > limUp || quociente < limDown){
				super.setStdout("\"overflow\"");
			}else {
				super.registrarLong(33, resto);
				super.registrarLong(34, quociente);
			}
		}
		
		
		return "\"div $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String divu() {
		pcatt();
		long div1 = super.hexaDecimalUnisined(super.resgatar(super.binarioDecimal(sourceReg1)));		
		long div2 = super.hexaDecimalUnisined(super.resgatar(super.binarioDecimal(sourceReg2)));
		if(div2 == 0) {
			super.setStdout("\"overflow\"");
		}else {
			long resto = div1%div2;
			long quociente = div1/div2;
			super.registrarLong(33, resto);
			super.registrarLong(34, quociente);
			
		}
		
		return "\"divu $" + super.binarioDecimal(sourceReg1) + ", $" + super.binarioDecimal(sourceReg2) + "\",";
	}
	
	public String sll() {
		pcatt();
		String sll = super.hexaBinario(super.resgatar(super.binarioDecimal(sourceReg2)));
		int desloc = super.binarioDecimal(shiftAmount);
		for(int i = 0; i < desloc; i++) {
			sll = sll.substring(1, sll.length()) + '0';
		}
		super.registrarHexa(super.binarioDecimal(destinationReg), super.binarioHexa(sll));
		
		return "\"sll $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg2) + ", " + super.binarioDecimal(shiftAmount) + "\",";
	}
	
	public String srl() {
		pcatt();
		String srl = super.hexaBinario(super.resgatar(super.binarioDecimal(sourceReg2)));
		int desloc = super.binarioDecimal(shiftAmount);
		for(int i = 0; i < desloc; i++) {
			srl = '0' + srl.substring(0, srl.length() - 1);
		}
		super.registrarHexa(super.binarioDecimal(destinationReg), super.binarioHexa(srl));
		
		return "\"srl $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg2) + ", " + super.binarioDecimal(shiftAmount) + "\",";
	}
	
	public String sra() {
		pcatt();
		String sra = super.hexaBinario(super.resgatar(super.binarioDecimal(sourceReg2)));
		int desloc = super.binarioDecimal(shiftAmount);
		for(int i = 0; i < desloc; i++) {
			sra = sra.substring(0, 1) + sra.substring(0, 1) + sra.substring(1, sra.length() - 1);
		}
		super.registrarHexa(super.binarioDecimal(destinationReg), super.binarioHexa(sra));
		
		return "\"sra $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg2) + ", " + super.binarioDecimal(shiftAmount) + "\",";
	}
	
	public String sllv() {
		pcatt();
		String sllv = super.hexaBinario(super.resgatar(super.binarioDecimal(sourceReg2)));
		long desloc = super.hexaDecimal(super.resgatar(super.binarioDecimal(sourceReg1)));
		for(int i = 0; i < desloc; i++) {
			sllv = sllv.substring(1, sllv.length()) + '0';
		}
		super.registrarHexa(super.binarioDecimal(destinationReg), super.binarioHexa(sllv));
		
		return "\"sllv $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg2) + ", $" + super.binarioDecimal(sourceReg1) + "\",";
	}
	
	public String srlv() {
		pcatt();
		String srlv = super.hexaBinario(super.resgatar(super.binarioDecimal(sourceReg2)));
		long desloc = super.hexaDecimal(super.resgatar(super.binarioDecimal(sourceReg1)));
		for(int i = 0; i < desloc; i++) {
			srlv = '0' + srlv.substring(0, srlv.length() - 1);
		}
		super.registrarHexa(super.binarioDecimal(destinationReg), super.binarioHexa(srlv));
		
		return "\"srlv $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg2) + ", $" + super.binarioDecimal(sourceReg1) + "\",";
	}
	
	public String srav() {
		pcatt();
		String srav = super.hexaBinario(super.resgatar(super.binarioDecimal(sourceReg2)));
		long desloc = super.hexaDecimal(super.resgatar(super.binarioDecimal(sourceReg1)));
		for(int i = 0; i < desloc; i++) {
			srav = srav.substring(0, 1) + srav.substring(0, 1) + srav.substring(1, srav.length() - 1);
		}
		super.registrarHexa(super.binarioDecimal(destinationReg), super.binarioHexa(srav));
		
		return "\"srav $" + super.binarioDecimal(destinationReg) + ", $" + super.binarioDecimal(sourceReg2) + ", $" + super.binarioDecimal(sourceReg1) + "\",";
	}
	
	public String jr() {
		index = (super.hexaDecimal(super.resgatar(super.binarioDecimal(sourceReg1))));
		super.registrarLong(32, super.getIndex());
		pcatt();
		return "\"jr $" + super.binarioDecimal(sourceReg1) + "\",";
	}
	
	public String syscall() {
		pcatt();
		return "\"syscall\",";
	}
	
	
	//atualiza o reg pc
	public void pcatt() {
		index = index + 4;
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
