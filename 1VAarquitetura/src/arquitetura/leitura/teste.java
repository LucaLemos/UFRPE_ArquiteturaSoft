package arquitetura.leitura;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class teste {
	
	public static void main(String[] args) throws IOException {
		File folder = new File("input");
		
		for (File file : folder.listFiles()) {
			if (!file.isDirectory()) {
				InputStream fis = new FileInputStream(file);
				Reader isr = new InputStreamReader(fis);
				BufferedReader br = new BufferedReader(isr);
				
				System.out.println("output/GrupoTL." + file.getName());
				OutputStream fos = new FileOutputStream("output/GrupoTL." + file.getName());
				Writer osw = new OutputStreamWriter(fos);
				BufferedWriter bw = new BufferedWriter(osw);
				
				String linha = br.readLine();
				
				while(linha != null && !linha.isEmpty()) {	
					bw.write(linha);
					bw.newLine();
					bw.flush();
					linha = br.readLine();
				}
				
				br.close();
				bw.close();
			}
		}
	}
}