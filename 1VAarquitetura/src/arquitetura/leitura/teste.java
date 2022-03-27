package arquitetura.leitura;

import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import arquitetura.mips.mips;

public class teste {
	
	public static void main(String[] args) throws Exception {
		//carrega os arquivos da pasta no folder
		File folder = new File("input");
		//inicializa a classe mips
		mips m = new mips();
		
		//executa para cada arquivo da pasta que nao seja uma pasta
		for (File file : folder.listFiles()) {
			if (!file.isDirectory()) {
				//ajusta o nome do arquivo de saida
				String nome = "output/GrupoC." + file.getName().replace("input", "output");
				PrintStream ps = new PrintStream(nome);
				
				//comeca a formatacao do arquivo de saida
				ps.println("[");
				
				//carrega os dados do arquivo no formato JSONObject
				Object ob = new JSONParser().parse(new FileReader(file));
				JSONObject js = (JSONObject) ob;
				
				//salva os dados de text em um JSONArray para leitura
				JSONArray h = (JSONArray) js.get("text");
				
				//executa para cada instrucao do array
				for (int i = 0; i < h.size(); i++) {
					//carrega a insformacao hexadecimal na classe mips
					m.setHexa(h.get(i).toString());
					
					//formatacao do arquivo de saida por instrucao
					ps.println("  {");
					ps.println("    \"hex\": \"" + h.get(i) + "\",");
					//usa o comando m.tipoInstrucao para imprimir a traducao do hexa
					ps.println("    \"text\": " + m.tipoInstrucao() );
					ps.println("    \"regs\": " + "{},");
					ps.println("    \"mem\": " + "{},");
					ps.println("    \"stdout\": " + "{}");
					ps.print("  }");
					if(i+1 < h.size()) {ps.println(",");}
					else {ps.println();}
				}
				
				//finaliza a formatacao do arquivo e fecha o leitor
				ps.println("]");
				ps.close();
			}
		}
	} 
}