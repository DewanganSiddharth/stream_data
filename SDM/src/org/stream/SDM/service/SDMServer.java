package org.stream.SDM.service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;
import java.util.LinkedList;
import java.util.Random;

import org.stream.SDM.database.Database;
import org.stream.SDM.model.StreamInfo;
import org.stream.SDM.model.StreamInput;
import org.stream.SDM.resource.SDMResource;

public class SDMServer  extends Thread{

	private static final int PORT = 4000;
	public static int windowSize=5;
	
	public SDMServer(Socket clientSocket) {
	
		this.clientSocket=clientSocket;
		
		// TODO Auto-generated constructor stub
	
	}

	public Socket clientSocket = null;
	public void run() {

		try {
			
		
			System.out.println("Socket Established...");
			ObjectOutputStream outToClient = new ObjectOutputStream(
					clientSocket.getOutputStream());
			ObjectInputStream inFromClient = new ObjectInputStream(
					clientSocket.getInputStream());

			

			StreamInfo info ;
		//	System.out.println("im MSG GOT");

			info = (StreamInfo) inFromClient.readObject();
			Random rand = new Random();
			int id=rand.nextInt(1000);
			info.setId(id);
			info.setLocation(clientSocket.getInetAddress().getHostAddress());
			Database.addStreamInfo(info);
			

			//System.out.println("inmsg  " + info.getName());
			int i =0;
			//while(SDMResource.id!=id);
				Database.createTable(info.getId(),info.getAttributes());
			boolean flag=true;
			while(true){
				if(clientSocket.isClosed())
					break;
				System.out.println("satus = "+clientSocket.isClosed());
				StreamInput input = new StreamInput();
			//	System.out.println("hello input");
				input = (StreamInput) inFromClient.readObject();
				input.setId(id);
				/*if((int)(SDMResource.inp.getTemperature())==0)
					input.setTemperature(0);
				if((int)(SDMResource.inp.getHumidity())==0)
					input.setHumidity(0);
				if((int)(SDMResource.inp.getCo2())==0)
					input.setCo2(0);
				if((int)(SDMResource.inp.getWindspeed())==0)
					input.setWindspeed(0);
				*/
				
				if(flag==true)//flag = 1 means for data less than window size
				{
					
					
					
					Database.addTouple(input,info.getAttributes());
					i++;
					/*list.add(line);
					sum = sum +line;
					i++;
					max = Collections.max(list);
					min =  Collections.min(list);
					avg = sum/list.size();*/
					if(i==windowSize)
						{
							//Computation.main(list);
							flag=false;
						}
			
				}
				else  if(flag==false)//now for data that is equal to window size
				{
					
					Database.deleteTouple(input.getId());
					Database.addTouple(input,info.getAttributes());
				}
				
			//	System.out.println("inmsg  " + info.getLocation());

				if(input == null)
					break;
			}
		

			clientSocket.close();

		} catch (Exception e) {
			System.err.println("Server Error: " + e.getMessage());
			System.err.println("Localized: " + e.getLocalizedMessage());
			System.err.println("Stack Trace: " + e.getStackTrace());
			System.err.println("To String: " + e.toString());
		}
	}

}
