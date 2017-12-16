package org.stream.SDM.service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.stream.SDM.model.StreamInfo;
import org.stream.SDM.model.StreamInput;

public class SDMClient1 {
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
			info.setName("SPAM");
			//info.setLocation("Bangalore");
			info.setOwnerName("Tarun Patel");
			// msg.setAverage(5.5f);
			System.out.println("msg sent..s");
			List <String> attr = new ArrayList<>();
			attr.add("temperature");
			attr.add("windspeed");
			attr.add("precipitation");
			info.setAttributes(attr);
			/* Send the Message Object to the server */
			outToServer.writeObject(info);
			Random rand = new Random();
			int i=0;
			while(true){
	          	float temp = rand.nextInt(100)+rand.nextFloat();
	          	float ppt = rand.nextInt(30)+rand.nextFloat();
	          	float windspeed = rand.nextInt(100)+rand.nextFloat();
				StreamInput info1 = new StreamInput();
				info1.setTemperature(temp);
				info1.setPrecipitation(ppt);
				info1.setWindspeed(windspeed);

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
