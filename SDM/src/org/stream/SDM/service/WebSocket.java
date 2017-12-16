package org.stream.SDM.service;


import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.*;
@ServerEndpoint("/rateserv")
public class WebSocket {
	private static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();
	// private static Thread rateThread ; //rate publisher thread
@OnOpen
public void open(Session session) {
 queue.add(session);
 System.out.println("New session opened: "+session.getId());
}
 @OnError
public void error(Session session, Throwable t) {
 queue.remove(session);
 System.err.println("Error on session "+session.getId());  
}
@OnClose
public void closedConnection(Session session) { 
 queue.remove(session);
 System.out.println("session closed: "+session.getId());
}


public static void sendAll(String msg) {
 try {
  /* Send the new rate to all open WebSocket sessions */  
  ArrayList<Session > closedSessions= new ArrayList<>();
  for (Session session : queue) {
   if(!session.isOpen())
   {
    System.err.println("Closed session: "+session.getId());
    closedSessions.add(session);
   }
   else
   {
	   System.out.println("Sending "+msg+" to "+queue.size()+" clients");
	   session.getBasicRemote().sendText(msg);
	   
   }    
  }
  queue.removeAll(closedSessions);
 } catch (Throwable e) {
  e.printStackTrace();
 }
}


}

		
