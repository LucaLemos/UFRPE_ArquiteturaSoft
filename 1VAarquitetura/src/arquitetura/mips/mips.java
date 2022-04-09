package arquitetura.mips;

public class mips {
	/*variavies*/
	private String hexa;
	private String bin;
	private decodificador deco = new decodificador();
	private registradores regs = new registradores();
	private String instrucao;
	private String stdout = "{}";

	/*inicializando mips*/
	public mips(registradores reg) {
		regs = reg;
	}
	
	public mips(String Hexa, registradores reg) {
		hexa = Hexa;
		bin = hexaBinario();
		regs = reg;
	}
	
	/*verifica tamanho de hexa*/
	public boolean tamanhoHexa(String hexa) {
		if(hexa.length() == 10) {
			return true;
		}else {
			return false;
		}
	}
	/*retira 0x de hexa*/
	public String retiraPrefixo(String Ox) {
		Ox = Ox.replace("0x","");
		return Ox;
	}
	/*pega o hexa e transforma em binario*/
	public String transformarBinario(String hex) {
		long hdec = Long.parseLong(retiraPrefixo(hex), 16);
		String bina = Long.toBinaryString(hdec);
		while (bina.length() < 32){
			bina = "0" + bina;
		}
		return bina;
	}
	/*pega o decimal e transforma em hexa*/
	public String intHexa(int dec) {
		String hex = Integer.toHexString(dec);
		while (hex.length() < 8){
			hex = "0" + hex;
		}
		hex = "0x" + hex;
		return hex;
	}
	/*pega o decimal e transforma em hexa*/
	public String longHexa(long dec) {
		String hex = Long.toHexString(dec);
		while (hex.length() < 8){
			hex = "0" + hex;
		}
		hex = "0x" + hex;
		return hex;
	}
	/*pega o decimal e transforma em hexa*/
	public String intStringHexa(String dec) {
		long deci = Long.parseLong(dec);
		String hex = Long.toHexString(deci);
		while (hex.length() < 8){
			hex = "0" + hex;
		}
		hex = "0x" + hex;
		return hex;
	}
	/*Faz verificacao e transformacao em binario*/
	public String hexaBinario() {
		if(tamanhoHexa(this.hexa )== true) {
		String bin = transformarBinario(this.hexa);
		return bin;
		}
		else {
			return "erro de entrada";
		}
	}
	
	/*Binario para decimal*/
	public int binarioDecimal(String bina) {
		long bdec = Long.parseLong(bina, 2);
		int bdecI = (int) bdec;
		return bdecI;
	}
	/*Binario para decimal positivo*/
	public long binarioDecimalUnisigned(String bina) {
		long bdec = Long.parseUnsignedLong(bina, 2);
		return bdec;
	}
	
	/*Hexa para decimal*/
	public int hexaDecimal(String hex) {
		int num;
		num = binarioDecimal(transformarBinario(hex));
		return num;
	}
	public long hexaDecimalUnisined(String hex) {
		long num;
		num = binarioDecimalUnisigned(transformarBinario(hex));
		return num;
	}
	
	/*retira 6 primeiros binarios*/
	public String retiraOpcode() {
		bin = bin.substring(0, 6);
		return bin;
	}
	
	/*identifica o tipo da instrucao*/
	public String qualTipo() {
		return deco.buscarTipo(retiraOpcode());
	}
	
	/*ira retornar a instrucao que foi passada para o mips*/
	public String Instrucao() {
		switch (qualTipo()) {
		case "R": 
			TipoR instR = new TipoR(hexa, regs);
			instrucao = instR.intrucao();
			stdout = instR.getStdout();
			return instrucao;
		case "I": 
			TipoI instI = new TipoI(hexa, regs);
			return instI.intrucao();
		case "J": 
			TipoJ instJ = new TipoJ(hexa, regs);
			return instJ.intrucao();
		default:
			return "\"NULL\",";
		}
	}
	
	public void registrar(int posi, String valor) {
		regs.registrar(posi, intStringHexa(valor));
	}
	
	public void registrarInt(int posi, int valor) {
		regs.registrar(posi, intHexa(valor));
	}
	
	public void registrarLong(int posi, long valor) {
		regs.registrar(posi, longHexa(valor));
	}
	
	public String resgatar(int posi) {
		return regs.resgatar(posi);
	}
	
	
	public String mostrarReg () {
		String registros = "";
		for(int i = 0; i < regs.getRegs().length; i++){	
			if (regs.getRegs()[i] != "0x00000000") {
				if (i == 32) {
					registros= registros + "        \"$pc\": " + hexaDecimalUnisined(regs.getRegs()[i]);
				}
				else if (i == 33) {
					registros= registros + (",\n");
					registros= registros + ("        \"$hi\": " + hexaDecimalUnisined(regs.getRegs()[i]));
				}
				else if (i == 34) {
					registros= registros + (",\n");
					registros= registros + ("        \"$lo\": " + hexaDecimalUnisined(regs.getRegs()[i]));
				}
                else {
                	registros= registros + ("        \"$" + i + "\": " + hexaDecimalUnisined(regs.getRegs()[i]) + ",\n");
                }
			}
		}
		return registros;
	}
    
	/*get e set*/
	public String getHexa() {
		return hexa;
	}
	
	public String getBin() {
		return bin;
	}
	
	public decodificador getDeco() {
		return deco;
	}
	
	public String getStdout() {
		return stdout;
	}

	public void setHexa(String hexa) {
		this.hexa = hexa;
		bin = hexaBinario();
	}
	
	public void setStdout(String out) {
		this.stdout = out;
	}

}
