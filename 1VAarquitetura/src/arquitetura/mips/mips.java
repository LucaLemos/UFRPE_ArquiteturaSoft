package arquitetura.mips;

public class mips {
	/*variavies*/
	public String hexa;
	public String bin;
	
	/*inicializando mips*/
	public mips() {
	}
	
	public mips(String He) {
		hexa = He;
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
    
	/*get e set*/
	public String getHexa() {
		return hexa;
	}
	
	public String getBin() {
		return bin;
	}

	public void setHexa(String hexa) {
		this.hexa = hexa;
		bin = hexaBinario();
	}

}
