package org.stream.SDM.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ThreadedServer {

	public static final int PORT = 4000;

	public void createThread(){
		 ServerSocket serverSocket = null;
	        Socket socket = null;
	//        System.out.println("window size: "+wsize);

	        try {
	            serverSocket = new ServerSocket(PORT);
	            
	        } catch (IOException e) {
	            e.printStackTrace();

	        }
	        while(true){
	            try {
	                socket = serverSocket.accept();
	            } catch (IOException e) {
	                System.out.println("I/O error: " + e);
	            }
	            // new thread for a client
	            new SDMServer(socket).start();
	        }
	}
	
	
}
