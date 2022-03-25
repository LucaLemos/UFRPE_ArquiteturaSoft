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
		
		File folder = new File("input");
		mips m = new mips();
		
		for (File file : folder.listFiles()) {
			if (!file.isDirectory()) {
				PrintStream ps = new PrintStream("output/GrupoTL." + file.getName());
				
				ps.println("[");
				
				Object ob = new JSONParser().parse(new FileReader(file));
				JSONObject js = (JSONObject) ob;
				
				JSONArray h = (JSONArray) js.get("text");
				
				for (int i = 0; i < h.size(); i++) {
					
					m.setHexa(h.get(i).toString());
					
					ps.println("  {");
					ps.println("    \"hex\": \"" + h.get(i) + "\",");
					ps.println("    \"text\": " + m.getBin() + ",");
					ps.println("    \"regs\": " + "{},");
					ps.println("    \"mem\": " + "{},");
					ps.println("    \"stdout\": " + "{}");
					ps.print("  }");
					if(i+1 < h.size()) {ps.println(",");}
					else {ps.println();}
				}
				
				ps.println("]");
				ps.close();
			}
		}
	} 
}