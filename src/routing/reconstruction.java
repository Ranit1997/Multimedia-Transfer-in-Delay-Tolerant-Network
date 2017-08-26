package routing;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
public class reconstruction {
	public static void grayscale(String des)throws IOException
	{
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat frame=new Mat();int i=0,j=0;String strr,str,strrr;double row,col,gray;String readcon="";
		Mat frame_1;
		
			 strr="C:\\Mini_Project\\One Simulator Media\\src\\input\\content\\white.jpg";
	        frame = Highgui.imread(strr);
	        frame_1=frame.clone();
			
			Size size =frame_1.size();
			row=size.height;col=size.width;
			FileInputStream fis = new FileInputStream("C:\\Mini_Project\\One Simulator Media\\src\\Game\\"+des+"\\image_data.txt");
			InputStreamReader isr=new InputStreamReader(fis);
			BufferedReader br=new BufferedReader(isr);
			readcon=br.readLine();
			Scanner s= new Scanner(readcon);
			
			for (i = 0; i <row; i++)
			{
			    for (j = 0; j <col; j++) {
			        
			    	String word=s.next();
			    	System.out.println(word);
			    	gray=Double.parseDouble(word);
			    	double[] data = frame.get(i,j);
			         //Editing image
			        data[0]=data[1]=data[2]=gray;
			       frame_1.put(i,j,data);
			        
					
			       }
			}
			
			Highgui.imwrite("C:\\Mini_Project\\One Simulator Media\\src\\Game\\"+des+"\\image_reconstructed.jpg",frame_1);
			br.close();
			 isr.close();
			 fis.close();		
	}
	}