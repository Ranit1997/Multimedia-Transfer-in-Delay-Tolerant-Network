/* 
 * Copyright 2010 Aalto University, ComNet
 * Released under GPLv3. See LICENSE.txt for details. 
 */
package report;

import java.util.List;

import core.DTNHost;
import core.Message;
import core.MessageListener;

/**
 * Report information about all delivered messages. Messages created during
 * the warm up period are ignored.
 * For output syntax, see {@link #HEADER}.
 */
public class DeliveredMessagesReport extends Report implements MessageListener {
	public static String HEADER = "# time        ID        Message_Size        HopCount        DeliveryTime  " + "      Host        Destination        Path ";

	/**
	 * Constructor.
	 */
	public DeliveredMessagesReport() {
		init();
	}
	
	@Override
	public void init() {
		super.init();
		write(HEADER);
	}

	/** 
	 * Returns the given messages hop path as a string
	 * @param m The message
	 * @return hop path as a string
	 */
	private String getPathString(Message m) {
		List<DTNHost> hops = m.getHops();
		int i;

		String str = m.getFrom().toString();	
		
		
		for (i=1; i<hops.size()-1; i++) {
			
				str += "->" + hops.get(i);
		
		}
		 str += "->"+ hops.get(i)/*+"("+ m.getContent()+")"*/;
		
		
		return str;
	}
	
	public void messageTransferred(Message m, DTNHost from, DTNHost to, 
			boolean firstDelivery) {
		if (!isWarmupID(m.getId()) && firstDelivery) {
			//int ttl = m.getTtl();
			write(format(getSimTime()) + "    " + m.getId() + "        " + 
					m.getSize() + "               " + m.getHopCount() + "              " + 
					format(getSimTime() - m.getCreationTime()) + "              " + 
					m.getFrom() + "           " + m.getTo() + "           "+ getPathString(m) + "        " /*+ m.getContent()*/);
		}
	}

	public void newMessage(Message m) {
		if (isWarmup()) {
			addWarmupID(m.getId());
		}
	}
	
	// nothing to implement for the rest
	public void messageDeleted(Message m, DTNHost where, boolean dropped) {}
	public void messageTransferAborted(Message m, DTNHost from, DTNHost to) {}
	public void messageTransferStarted(Message m, DTNHost from, DTNHost to) {}

	@Override
	public void done() {
		super.done();
	}
}
