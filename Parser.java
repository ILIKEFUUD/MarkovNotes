import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
	
	private static Scanner fileReader;

	
	public static ArrayList<Double> csvTimes(String file) throws FileNotFoundException{
		ArrayList<String> ticks = new ArrayList<String>();
		ArrayList<Double> times = new ArrayList<Double>();
		fileReader = new Scanner(new File(file));
		
		//bear
		String tickBear = "";
		while (fileReader.hasNextLine()){
			//bear
			String cur = fileReader.nextLine();
			// System.out.println(cur);
			for(int i = 0; i < cur.length()-1; i++){
				if(cur.charAt(i) != ',')
					tickBear += cur.charAt(i);
				else
					break;//bad habit idc
			}
			//System.out.println(tickBear);
			ticks.add(tickBear);
			tickBear = "";
		}
		

		for (int i = 1; i < ticks.size(); i++)
			times.add(Double.parseDouble(ticks.get(i))-Double.parseDouble(ticks.get(i-1)));
		return times;
	}
	
	public static ArrayList<String> csvNotesBear(String file) throws FileNotFoundException{
		//convert 
		ArrayList<String> notes = new ArrayList<String>();
		Scanner fileReader = new Scanner(new File(file));
		String note = "";
		
		
		
		while(fileReader.hasNext()){
			String cur = fileReader.nextLine();
			int start = cur.indexOf(',')+1;
			int end = cur.indexOf("(") - 2;
			
			note = cur.substring(start, end);
			notes.add(note);
		}
		
		return notes;
		
	}
	
	
	public static void writeCSV(ArrayList<String> notes, ArrayList<Double> length) throws IOException{
		File file = new File ("C:\\Users\\MLum\\Desktop\\file.txt");
	    PrintWriter printWriter = new PrintWriter ("file.txt");
	    printWriter.println ("hello");
	    printWriter.close ();
		
	}
}
