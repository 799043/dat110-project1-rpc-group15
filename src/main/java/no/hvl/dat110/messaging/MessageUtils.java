package no.hvl.dat110.messaging;

import java.util.Arrays;

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

		int size = message.getData().length;
		data = new byte[size];
		segment = new byte[SEGMENTSIZE];

		for (int i=0; i<data.length; i++){
			data[i] = message.getData()[i];
		}

		//padding
//		for (int i=0; i<SEGMENTSIZE; i++){
//			segment[i] = 0;
//		}

		segment[0] = (byte)data.length;

		//fills in data
		for (int i=0; i<data.length; i++){
            segment[i+1] = data[i];
		}
			
		// TODO - END
		return segment;
		
	}

	public static Message decapsulate(byte[] segment) {

		Message message = null;
		
		// TODO - START
		// decapsulate segment and put received payload data into a message

		int size = 0;
		for (int i=0; i<SEGMENTSIZE; i++){
			if (segment[i] != 0){
				size += 1;
			}

			//failsafe for when size = 0
			if (segment[i] == 0 && size == 0){
				size = 1;
			}
		}

		size -= 1;

		byte[] data = new byte[size];

		for (int i=0; i<data.length; i++){
			data[i] = segment[i+1];
		}

		message = new Message(data);
		// TODO - END
		
		return message;
		
	}
	
}
