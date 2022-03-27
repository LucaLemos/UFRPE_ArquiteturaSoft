package arquitetura.mips;

public class mips {
	/*variavies*/
	private String hexa;
	private String bin;
	private decodificador deco = new decodificador();

	/*inicializando mips*/
	public mips() {
	}
	
	public mips(String Hexa) {
		hexa = Hexa;
		bin = hexaBinario();
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
	public String tipoInstrucao() {
		switch (qualTipo()) {
		case "R": 
			TipoR instR = new TipoR(hexa);
			return instR.intrucao();
		case "I": 
			TipoI instI = new TipoI(hexa);
			return "IIIII";
		case "J": 
			TipoJ instJ = new TipoJ(hexa);
			return "JJJJJ";
		default:
			return "NULL";
		}
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
