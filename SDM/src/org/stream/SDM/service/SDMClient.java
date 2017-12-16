package org.stream.SDM.service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.stream.SDM.model.StreamInfo;
import org.stream.SDM.model.StreamInput;

public class SDMClient {
	private static final int PORT = 4000;

	public static void main(String[] args) {
		try {
			// Create the socket
			Socket clientSocket = new Socket("localhost", PORT);
			// Create the input & output streams to the server
			ObjectOutputStream outToServer = new ObjectOutputStream(
					clientSocket.getOutputStream());
			ObjectInputStream inFromServer = new ObjectInputStream(
					clientSocket.getInputStream());

			/* Create The Message Object to send */
			StreamInfo info = new StreamInfo();
			info.setName("IIITB");
			//info.setLocation("Bangalore");
			info.setOwnerName("Sourabh Patel");
			// msg.setAverage(5.5f);
			System.out.println("msg sent..s");
			List <String> attr = new ArrayList<>();
			attr.add("temperature");
			attr.add("humidity");
			attr.add("co2");
			attr.add("windspeed");
			info.setAttributes(attr);
			
			/* Send the StreamINfo Object to the server */
			outToServer.writeObject(info);
			Random rand = new Random();
			int i=0;
			while(true){
	          	float temp = rand.nextInt(100)+rand.nextFloat();
	          	float humidity = rand.nextInt(100)+rand.nextFloat();
	          	float co2 = rand.nextInt(100)+rand.nextFloat();
	          	float windspeed = rand.nextInt(100)+rand.nextFloat();
				StreamInput info1 = new StreamInput();
				info1.setTemperature(temp);
				info1.setHumidity(humidity);
				info1.setCo2(co2);
				info1.setWindspeed(windspeed);
				Thread.currentThread().sleep(2000);

				outToServer.writeObject(info1);	
				System.out.println("msg sent..s");
				//i++;
				if(info1==null)
					break;
			}
					clientSocket.close();

		} catch (Exception e) {
			System.err.println("Client Error: " + e.getMessage());
			System.err.println("Localized: " + e.getLocalizedMessage());
			System.err.println("Stack Trace: " + e.getStackTrace());
		}
	}

}
