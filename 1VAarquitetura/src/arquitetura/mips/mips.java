package arquitetura.mips;

import java.math.BigInteger;

public class mips {
	/*variavies*/
	private String hexa;
	private String bin;
	private decodificador deco = new decodificador();
	private registradores regs = new registradores();
	private memoria mem = new memoria();
	private String stdout = "{}";
	private int pcBase = hexaDecimal(regs.resgatar(32));
	protected int index = hexaDecimal(regs.resgatar(32));

	/*inicializando mips*/
	public mips(registradores reg, memoria mem) {
		this.regs = reg;
		this.mem = mem;
		this.pcBase = hexaDecimal(regs.resgatar(32));
		this.index = hexaDecimal(regs.resgatar(32));
	}
	public mips(String Hexa, registradores reg, memoria mem) {
		hexa = Hexa;
		bin = TransformahexaBinario();
		this.regs = reg;
		this.mem = mem;
		this.pcBase = hexaDecimal(regs.resgatar(32));
		this.index = hexaDecimal(regs.resgatar(32));
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
	/*pega um BigInteger e transforma em binario mult*/
	public String transformarBinarioMult(BigInteger valor) {
		long hdec = valor.longValue();
		String bina = Long.toBinaryString(hdec);
		while (bina.length() < 64){
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
		while (hex.length() > 8){
			hex = hex.substring(hex.length() - 8, hex.length());
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
		while (hex.length() > 8){
			hex = hex.substring(hex.length() - 8, hex.length());
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
		while (hex.length() > 8){
			hex = hex.substring(hex.length() - 8, hex.length());
		}
		hex = "0x" + hex;
		return hex;
	}
	/*Faz verificacao e transformacao em binario*/
	public String TransformahexaBinario() {
		if(tamanhoHexa(this.hexa )== true) {
		String bin = transformarBinario(this.hexa);
		return bin;
		}
		else {
			return "erro de entrada";
		}
	}
	
	//Hexa para binario
	public String hexaBinario(String hex) {
		if(tamanhoHexa(hex)== true) {
		String bin = transformarBinario(hex);
		return bin;
		}
		else {
			return "erro de entrada";
		}
	}
	
	/*Binario para hexa*/
	public String binarioHexa(String bina) {
		long bdec = Long.parseLong(bina, 2);
		String h = longHexa(bdec);
		return h;
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
	//hexa para decimal unisined long
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
		String intrucao;
		switch (qualTipo()) {
		case "R": 
			TipoR instR = new TipoR(hexa, regs, mem);
			intrucao = instR.intrucao();
			stdout = instR.getStdout();
			index = instR.getIndex();
			break;
		case "I": 
			TipoI instI = new TipoI(hexa, regs, mem);
			intrucao = instI.intrucao();
			stdout = instI.getStdout();
			index = instI.getIndex();
			break;
		case "J": 
			TipoJ instJ = new TipoJ(hexa, regs, mem);
			intrucao = instJ.intrucao();
			stdout = instJ.getStdout();
			index = instJ.getIndex();
			break;
		default:
			intrucao = "\"NULL\",";
			break;
		}
		return intrucao;
	}
	
	//registra nos registradores a partir de um valor int em string
	public void registrar(int posi, String valor) {
		regs.registrar(posi, intStringHexa(valor));
	}
	//registra nos registradores a partir de um valor int
	public void registrarInt(int posi, int valor) {
		regs.registrar(posi, intHexa(valor));
	}
	//registra nos registradores a partir de um valor long
	public void registrarLong(int posi, long valor) {
		regs.registrar(posi, longHexa(valor));
	}
	//registra nos registradores a partir de um valor long
		public void registrarHexa(int posi, String valor) {
			regs.registrar(posi, valor);
		}
	//pega um valor dos registradores
	public String resgatar(int posi) {
		return regs.resgatar(posi);
	}
	
	//print dos valores dos registradores diferentes de 0
	public String mostrarReg () {
		String registros = "";
		for(int i = 0; i < regs.getRegs().length; i++){	
			if (!regs.getRegs()[i].equals("0x00000000")) {
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
	
	public String mostrarMem() {
		return this.mem.mostrar();
	}
	
	public void atualizaIndex() {
		this.index = hexaDecimal(regs.resgatar(32));
		this.pcBase = hexaDecimal(regs.resgatar(32));
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
	
	public memoria getMem() {
		return mem;
	}
	
	public String getStdout() {
		return stdout;
	}
	
	public int getPcBase() {
		return pcBase;
	}
	
	public int getIndex() {
		return index;
	}
	
	public int getIndexNum() {
		int num = (index - pcBase)/4;
		return num;
	}

	public void setHexa(String hexa) {
		this.hexa = hexa;
		bin = TransformahexaBinario();
	}
	
	public void setStdout(String out) {
		this.stdout = out;
	}

}
