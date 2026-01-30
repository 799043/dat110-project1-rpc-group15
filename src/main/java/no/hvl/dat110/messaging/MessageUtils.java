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
		segment = ArrayUtils.insert(0, data, size); //Adds size to front
		segment = Arrays.copyOf(segment, SEGMENTSIZE); //Makes segment 128 bytes long
		
		// TODO - END
		return segment;

	}

	public static Message decapsulate(byte[] segment) {

		Message message = null;

		// TODO - START
		// decapsulate segment and put received payload data into a message

		int size = (int)segment[0];
		byte[] data = Arrays.copyOfRange(segment, 1, size+1);
		message = new Message(data);
		
		// TODO - END

		return message;

	}

}
