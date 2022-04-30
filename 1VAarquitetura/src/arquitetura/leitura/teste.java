package arquitetura.leitura;

import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import arquitetura.mips.memoria;
import arquitetura.mips.mips;
import arquitetura.mips.registradores;

public class teste {
	
	public static void main(String[] args) throws Exception {
		//carrega os arquivos da pasta no folder
		File folder = new File("input");

		//executa para cada arquivo da pasta que nao seja uma pasta
		for (File file : folder.listFiles()) {
			if (!file.isDirectory()) {
				//inicializa a classe mips
				registradores reg = new registradores();
				memoria mem = new memoria();
				mips m = new mips(reg, mem);
				int index = 0;
				
				//ajusta o nome do arquivo de saida
				String nome = "output/GrupoC." + file.getName().replace("input", "output");
				PrintStream ps = new PrintStream(nome);
				
				//comeca a formatacao do arquivo de saida
				ps.println("[");
				
				//carrega os dados do arquivo no formato JSONObject
				JSONParser parser = new JSONParser();
				FileReader reader = new FileReader(file);
				Object ob = parser.parse(reader);
				JSONObject js = (JSONObject) ob;
				
				//separa as partes de config e regs do json
				JSONObject config = (JSONObject) js.get("config");
				JSONObject regs = (JSONObject) config.get("regs");
				
				//separa a parte de mem do json
				JSONObject mems = (JSONObject) config.get("mem");
				Object[] memsEntrada = mems.keySet().toArray();

				//atualiza os registradores com os valores em mem
				if (mems != null) {
					for (int i = 0; i < mems.size(); i++) {
						Long long1 = Long.parseLong((String) memsEntrada[i]);
						Long long2 = (Long) mems.get(memsEntrada[i]);
						m.getMem().registrar(long1, long2);
					}
				}
				
				//separa aa parte de data do json
				JSONObject data = (JSONObject) js.get("data");
				Object[] dataEntrada = data.keySet().toArray();
				
				//atualiza os registradores com os valores em data
				if (data != null) {
					for (int i = 0; i < data.size(); i++) {
						Long long1 = Long.parseLong((String) dataEntrada[i]);
						Long long2 = Long.parseLong((String) data.get(dataEntrada[i]));
						m.getMem().registrar(long1, long2);
					}
				}
				
				//atualiza os registradores com os valores em regs
				if (regs != null) {
					for (int i = 0; i < 32; i++) {
						if (regs.get("$" + i) != null) {
							m.registrar(i, regs.get("$" + i).toString());
						}
					}
					if (regs.get("pc") != null) {
						m.registrar(32, regs.get("pc").toString());
					}
					if (regs.get("hi") != null) {
						m.registrar(33, regs.get("hi").toString());
					}
					if (regs.get("lo") != null) {
						m.registrar(34, regs.get("lo").toString());
					}
				}
				
				//salva os dados de text em um JSONArray para leitura
				JSONArray text = (JSONArray) js.get("text");
				
				
				m.atualizaIndex();
				index = m.getIndexNum();
				int lim = 0;
				
				mem.mostrar();
				
				//executa para cada instrucao do array
				while (index < text.size() && lim < 100) {
					//carrega a insformacao hexadecimal na classe mips
					m.setHexa(text.get(index).toString());
					
					//formatacao do arquivo de saida por instrucao
					ps.println("  {");
					ps.println("    \"hex\": \"" + text.get(index) + "\",");
					//usa o comando m.tipoInstrucao para imprimir a traducao do hexa
					ps.println("    \"text\": " + m.Instrucao() );
					//usa o mips para imprimir os registradores que estao com um valor diferente de 0
					ps.println("    \"regs\": " + "{");
					ps.println(m.mostrarReg() + "");
					ps.println("    },");
					//usa o mips para imprimir as memorias que estao com um valor diferente de 0
					ps.println("    \"mem\": " + "{");
					ps.println(m.mostrarMem());
					ps.println("    },");
					
					ps.println("    \"stdout\": " + m.getStdout());
					ps.print("  }");
					
					if(index+1 < text.size()) {ps.println(",");}
					else {ps.println();}
					
					index = m.getIndexNum();
					m.registrarLong(32, m.getIndex());
					lim++;
				}
				
				//finaliza a formatacao do arquivo e fecha o leitor
				ps.println("]");
				ps.close();
			}
		}
	} 
}