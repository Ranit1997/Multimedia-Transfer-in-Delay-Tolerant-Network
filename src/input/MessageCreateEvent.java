/* 
 * Copyright 2010 Aalto University, ComNet
 * Released under GPLv3. See LICENSE.txt for details. 
 */
package input;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import core.DTNHost;
import core.Message;
import core.World;

/**
 * External event for creating a message.
 */
public class MessageCreateEvent extends MessageEvent {
	private int size;
	private int responseSize;
	//RANIT
	private Scanner s = null;	
	private String content;
	//RANIT
	//private File content;
	/**
	 * Creates a message creation event with a optional response request
	 * @param from The creator of the message
	 * @param to Where the message is destined to
	 * @param id ID of the message
	 * @param size Size of the message
	 * @param responseSize Size of the requested response message or 0 if
	 * no response is requested
	 * @param time Time, when the message is created
	 */
	public MessageCreateEvent(int from, int to, String id, int size,int responseSize, double time) {
		super(from,to, id, time);
		this.size = size;
		this.responseSize = responseSize;
		//RANIT
		String k;
		
		if(id.startsWith("I"))
		{
			try 
			{
				extract.grayscale();
				s =  new Scanner(new File("input/content/image_data.txt"));
				
				//content= new File("input/content/military.txt");
			} 
			catch(Exception e)
			{
				System.out.println("ERROR");
			}	
			content = s.nextLine();
		}
		else if(id.startsWith("A"))
		{
			content=audio_extract.readFile("input/content/audio.txt");
		}
			try
			{
				if((from>=0)&&(from<=1))
				{
					k="ALPHA"+Integer.toString(from);
				}
				else if((from>=2)&&(from<=3))
				{
					k="BETA"+Integer.toString(from);
				}
				else
				{
					k="GAMMA"+Integer.toString(from);	
				}
			
			if(id.startsWith("I"))
			{
				FileOutputStream fos = new FileOutputStream("C:\\Mini_Project\\One Simulator Media\\src\\Game\\"+k+"\\image_data.txt");
				OutputStreamWriter osw=new OutputStreamWriter(fos);
				BufferedWriter bw=new BufferedWriter(osw);
				bw.write(content);
				 bw.close();
				 osw.close();
				 fos.close();
			}
			else if(id.startsWith("A"))
			{
				FileOutputStream fos = new FileOutputStream("C:\\Mini_Project\\One Simulator Media\\src\\Game\\"+k+"\\audio_data.txt");
				OutputStreamWriter osw=new OutputStreamWriter(fos);
				BufferedWriter bw=new BufferedWriter(osw);
				bw.write(content);
				 bw.close();
				 osw.close();
				 fos.close();
			}
			
			}
			catch(Exception e)
			{
				//System.out.println("RELAX");
			}	
			
				
		

		//RANIT

	}

	
	/**
	 * Creates the message this event represents. 
	 */
	@Override
	public void processEvent(World world) {
		DTNHost to = world.getNodeByAddress(this.toAddr);
		DTNHost from = world.getNodeByAddress(this.fromAddr);			
		//RANIT
		Message m = new Message(from, to, this.id, this.size, content);
		//RANIT
		m.setResponseSize(this.responseSize);
		from.createNewMessage(m);
	}
	
	@Override
	public String toString() {
		return super.toString() + " [" + fromAddr + "->" + toAddr + "] " +
		"size:" + size + " CREATE";
	}
}
