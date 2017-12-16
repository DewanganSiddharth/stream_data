package org.stream.SDM.resource;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.Session;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.stream.SDM.database.Database;
import org.stream.SDM.model.OutputModel;
import org.stream.SDM.model.Result;
import org.stream.SDM.model.StreamInfo;
import org.stream.SDM.model.StreamInput;
import org.stream.SDM.service.SDMServer;
import org.stream.SDM.service.ThreadedServer;
import org.stream.SDM.service.WebSocket;

import com.google.gson.Gson;
@Path("/SDM")
public class SDMResource {

	public static int id =-1;
	public static StreamInput inp;
	
	@Path("/sendUserInput")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void sendUserInput(StreamInput input){
		
		
		
		System.out.println("id = "+input.getId()+" temp =  "+input.getTemperature()+" hum = "+input.getHumidity()+" co2 = "+input.getCo2()+" speed = "+input.getWindspeed());
		inp=input;
		id = input.getId();
		Database.addStreamAttributes(input);
	}

	@Path("/getStreamInfo")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<StreamInfo> getStreamInfo(){
		return Database.getStreamInfo();
		
	}
	
	@POST
	@Path("/{wsize}")
	public void setWindowSize(@PathParam ("wsize") int wsize){
	
		SDMServer.windowSize=wsize;
	
	
	}
	@GET
	@Path("/getAttributes/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public StreamInput getAttributes(@PathParam ("id") int id){
	
		return Database.getAttributes(id);
	
	
	}
	@GET
	@Path("/startServer/{id}")
	@Produces(MediaType.APPLICATION_JSON)

	public boolean startServer(@PathParam("id") String wsize){
		System.out.println("server started");
		SDMServer.windowSize = Integer.parseInt(wsize);
		System.out.println("Window Size= "+SDMServer.windowSize);
		new ThreadedServer().createThread();
		return true;
	}
	@Path("/getResult/{id}/{max}/{min}/{avg}/{sum}/{attr}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<OutputModel>  getResult(@PathParam("id") int id,@PathParam("max") float max,@PathParam("min") float min,@PathParam("avg") float avg,@PathParam("sum") float sum,@PathParam("attr") String attr){
		List<OutputModel> output1 = new ArrayList<>();
		String a[] = attr.split(":");
		int j=0;
		while(j<a.length){
			OutputModel m = new OutputModel();
			Result result = new Result();
			result.setMax(max);
			result.setMin(min);
			result.setAvg(avg);
			result.setSum(sum);
			result.setAttribute(a[j]);
			result.setStream_id(id);
			System.out.println(" stream "+result.getAttribute());
			int i=0;
			List <Result> rlist = new ArrayList<Result>();
			while(i<50){
				Result rs = Database.Compute(result);
				/*try {
				//	Thread.currentThread().sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				rlist.add(rs);
			i++;
			}
			m.setAttribute(a[j]);
			m.setRlist(rlist);
			j++;
			output1.add(m);
		}
return output1;
	}
	
	
	
	@POST
	@Path("/computeResult")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public List<Result> computeResult(Result result){
		System.out.println("sending stream");
		List <Result> rlist = new ArrayList<>(); 
		//WebSocket w = new WebSocket();
	//	w.open();
		/*Gson gson = new Gson();
		String json = gson.toJson(result);
		WebSocket.sendAll(json);*/
		int i=0;
		while(i<10){
		Result rs = Database.Compute(result);
		rlist.add(rs);
		//Gson gson1 = new Gson();
	//	String json1 = gson1.toJson(rs);
		//WebSocket.sendAll(json1);
		try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//if(result.getAttribute().equals("stop"))
		//break;
		i++;
		}
	return rlist;
	}
	
}
