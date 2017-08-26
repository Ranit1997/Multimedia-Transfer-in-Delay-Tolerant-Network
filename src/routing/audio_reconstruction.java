package routing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class audio_reconstruction {
	public static void reconstruction(String path) throws IOException
	{
		String data;
		FileInputStream fis;
		try {
			fis = new FileInputStream(path+"audio_data.txt");
			InputStreamReader isr=new InputStreamReader(fis);
			BufferedReader br=new BufferedReader(isr);
			FileOutputStream fos = new FileOutputStream(path+"audio.mp3");
		     OutputStreamWriter osw=new OutputStreamWriter(fos);
		     BufferedWriter bw=new BufferedWriter(osw);
		     data=br.readLine();
		     while(data!=null)
		     {
		      bw.write(data);
		      bw.write("\n");
		      data=br.readLine();
		     }
		     bw.close();
		     osw.close();
		     fos.close();
		     br.close();
		     isr.close();
		     fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
