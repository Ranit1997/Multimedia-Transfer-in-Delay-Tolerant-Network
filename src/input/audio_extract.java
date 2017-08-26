package input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class audio_extract {
	
	public static String readFile(String filename) {

	    // variable representing a line of data in the mp3 file
	    String line ="";String s=null;

	    try {
	        BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
	    	
	    	s=br.readLine();
            //System.out.println(s);
	        while (s !=null) {
	          line=line+s;
	          line=line+'\n';
	          s=br.readLine();

	        
	}

}
	    catch (Exception e) {
            e.printStackTrace();
        }
	return line;
	}
}
	    
