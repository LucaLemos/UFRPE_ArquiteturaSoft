package arquitetura.leitura;

import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
				mips m = new mips(reg);
				
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
				
				//executa para cada instrucao do array
				for (int i = 0; i < text.size(); i++) {
					//carrega a insformacao hexadecimal na classe mips
					m.setHexa(text.get(i).toString());
					
					//formatacao do arquivo de saida por instrucao
					ps.println("  {");
					ps.println("    \"hex\": \"" + text.get(i) + "\",");
					//usa o comando m.tipoInstrucao para imprimir a traducao do hexa
					ps.println("    \"text\": " + m.Instrucao() );
					//usa o mips para imprimir os registradores que estao com um valor diferente de 0
					ps.println("    \"regs\": " + "{");
					ps.println(m.mostrarReg() + "");
					ps.println("    },");
					
					ps.println("    \"mem\": " + "{},");
					ps.println("    \"stdout\": " + "{}");
					ps.print("  }");
					
					if(i+1 < text.size()) {ps.println(",");}
					else {ps.println();}
				}
				
				//finaliza a formatacao do arquivo e fecha o leitor
				ps.println("]");
				ps.close();
			}
		}
	} 
}