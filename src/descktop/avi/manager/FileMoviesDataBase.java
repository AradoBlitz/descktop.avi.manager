package descktop.avi.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileMoviesDataBase {

	public void saveTo(String string, String[][] strings) throws Exception {
		FileWriter writer = new FileWriter(string, true);
		BufferedWriter bfw = new BufferedWriter(writer);
		PrintWriter pw = new PrintWriter(bfw);
		try{
			for(int i = 0; i < strings.length; i++)
				pw.println(strings[i][0] + "," + strings[i][1] + "," + strings[i][2]);
			
		}finally{
			pw.close();
		}
	}

	public String[][] loadMelodies(String string) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(string));
		List<String> buffer = new ArrayList<>();
		
		try{
			while(reader.ready())
				buffer.add(reader.readLine());
			
		}finally{
			reader.close();
		}
		
		String[][] result = new String[buffer.size()][3];
		for(int i = 0; i<buffer.size();i++)
			result[i] = buffer.get(i).split(",");
		
		return result;
	}

}
