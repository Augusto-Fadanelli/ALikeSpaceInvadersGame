import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Files{

	private String resolutions[] = new String[2];

	public void read(String path) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String line = "";
		int i = 0;
		while (true) {
			line = buffRead.readLine();
			if (line != null) {
				this.resolutions[i] = line;
				i++;
			} else
				break;
		}
		buffRead.close();
	}

	public void write(String path) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		String line = "";
		Scanner in = new Scanner(System.in);
		System.out.println("Escreva algo: ");
		line = in.nextLine();
		buffWrite.append(line + "\n");
		buffWrite.close();
	}

	public int getResolutions(int pos){
		return Integer.parseInt(this.resolutions[pos]);
	}

}