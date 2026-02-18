package no.hvl.dat110.messaging;

import java.util.Arrays;

import org.apache.maven.surefire.shared.lang3.ArrayUtils;

import no.hvl.dat110.TODO;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {
		
		byte[] segment = null;
		byte[] data;
		
		// TODO - START
		
		// encapulate/encode the payload data of the message and form a segment
		// according to the segment format for the messaging layer

		data = message.getData();
		byte size = (byte) data.length;
		segment = ArrayUtils.addFirst(data, (byte) size);
		segment = Arrays.copyOf(segment, SEGMENTSIZE);  // Adds padding (if necessary) so that the total size is SEGMENTSIZE
			
		// TODO - END
		return segment;
		
	}

	public static Message decapsulate(byte[] segment) {

		Message message = null;
		
		// TODO - START
		// decapsulate segment and put received payload data into a message

		
		int size = segment[0];
		byte[] data = new byte[size];

		for (int i=1; i<=size; i++) {
			data[i-1] = segment[i];
		}
		
		// Kan bruke copyOfRange i stedet for for-løkke 
		// byte[] data = Arrays.copyOfRange(segment, 1, size+1); // Copies the elements from segment index 1 to index size+1 into data
		
		message = new Message(data);
		
		// TODO - END
		
		return message;
		
	}
	
}
