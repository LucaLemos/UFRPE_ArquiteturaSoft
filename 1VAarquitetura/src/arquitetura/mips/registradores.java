package arquitetura.mips;

public class registradores {
	private String[] regs = new String[35];
	
	public registradores() {
		inicializar ();
	}
	
	public void inicializar () {
		for(int i = 0; i < 35; i++){
			regs[i] = "0x00000000";
		}
		regs[28] = "0x10008000";
		regs[29] = "0x7fffeffc";
		regs[32] = "0x00400000";
	}
	
	public void registrar (int posi, String valor) {
			regs[posi] = valor;
	}
	
	public String resgatar (int posi) {
			return regs[posi];
	}
	
	public String[] getRegs() {
		return regs;
	}
	
	public void mostrar () {
		for(int i = 0; i < 35; i++){
			System.out.println(regs[i]);
		}
	}
	
}
