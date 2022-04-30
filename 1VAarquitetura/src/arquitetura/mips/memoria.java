package arquitetura.mips;

import java.util.Arrays;

public class memoria {
	private long[][] mem = new long[2][1000];
	int index = 0;
	
	public memoria() {
	}
	
	public void registrar (long posi, long valor) {
			mem[0][index] = posi;
			mem[1][index] = valor;
			index++;
	}
	
	public long resgatarValor (long posi) {
		for(int i = 0; i < 1000; i++){
			if(mem[0][i] == posi) {
				return mem[1][i];
			}
		}
		return 0;
	}
	
	public long[][] getMem() {
		return mem;
	}
	
	public String mostrar() {
		long[][] memTemp = new long[2][1000];
		String memoria = "";
		
		for(int i = 0; i < 1000; i++){
			if(mem[1][i] != 0) {
				memTemp[0][i] = mem[0][i];
				memTemp[1][i] = mem[1][i];
			}
		}
		Arrays.sort( memTemp[0] );
		for(int i = 0; i < 1000; i++){
			if(memTemp[0][i] != 0) {
				if(!memoria.equals(""))memoria = memoria + ",\n";
				memoria = memoria + "        \"" + memTemp[0][i] + "\": " + resgatarValor(memTemp[0][i]);
				
			}
		}
		
		return memoria;
	}
}
