package arquitetura.mips;

public class mips {
	/*variavies*/
	private String hexa;
	private String bin;
	private decodificador deco = new decodificador();
	private registradores regs = new registradores();

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
	public String intStringHexa(String dec) {
		int deci = Integer.parseInt(dec);
		String hex = Integer.toHexString(deci);
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
	
	/*Hexa para decimal*/
	public int hexaDecimal(String hex) {
		int num;
		num = binarioDecimal(transformarBinario(hex));
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
			return instR.intrucao();
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
	
	
	public String mostrarReg () {
		String registros = "";
		for(int i = 0; i < regs.getRegs().length; i++){	
			if (regs.getRegs()[i] != "0x00000000") {
				if (i == 32) {
					registros= registros + "        \"$pc\": " + hexaDecimal(regs.getRegs()[i]);
				}
				else if (i == 33) {
					registros= registros + (",\n");
					registros= registros + ("        \"$hi\": " + hexaDecimal(regs.getRegs()[i]));
				}
				else if (i == 34) {
					registros= registros + (",\n");
					registros= registros + ("        \"$lo\": " + hexaDecimal(regs.getRegs()[i]));
				}
                else {
                	registros= registros + ("        \"$" + i + "\": " + hexaDecimal(regs.getRegs()[i]) + ",\n");
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

	public void setHexa(String hexa) {
		this.hexa = hexa;
		bin = hexaBinario();
	}

}
