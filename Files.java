import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.Scanner;

public class Files{

	private String vet[] = new String[10];

	public void read(String path) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String line = "";
		int i = 0;
		while (true) {
			line = buffRead.readLine();
			if (line != null) {
				this.vet[i] = line;
				i++;
			} else{
				break;
			}
			if(i == 10){
				break;
			}
		}
		buffRead.close();
	}

	public void write(String path, String width, String height) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		String line = "";
		//Scanner in = new Scanner(System.in);
		//System.out.println("Escreva algo: ");
		//line = in.nextLine();
		line = width;
		buffWrite.append(line + "\n");
		line = height;
		buffWrite.append(line + "\n");
		buffWrite.close();
	}

	public int getResolutions(int pos){
		return Integer.parseInt(this.vet[pos]);
	}
}