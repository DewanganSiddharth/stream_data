package org.stream.SDM.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.stream.SDM.model.*;
import org.stream.SDM.model.StreamInput;


public class Database {

	public static void addStreamInfo(StreamInfo info) {
		try {
			DbAccess connect = new DbAccess();
			boolean check = false;
			while (check == false) {
				check = connect.start();
			}
			String sql = "insert into stream_info values(?,?,?,?)";
			PreparedStatement stmt = connect.con.prepareStatement(sql);
			stmt.setInt(1, info.getId());
			stmt.setString(3, info.getName());
			stmt.setString(4, info.getOwnerName());
			stmt.setString(2, info.getLocation());
			stmt.executeUpdate();
			connect.con.close();
		} catch (Exception e) {
			System.out.println("exception at line 34 in Database.java " + e);

			// TODO: handle exception
		}

	}

	public static void createTable(int id,List<String> columns) {
		
		int i = 0;
		String s = "create table stream" + id + " (stream_id int , ";
		while (i < columns.size()) {
			if (i == columns.size() - 1)
				s = s + columns.get(i) + " FLOAT(5,2)) ";
			else
				s = s + columns.get(i) + " FLOAT(5,2), ";
			i++;

		}
		try {
			DbAccess connect = new DbAccess();
			boolean check = false;
			while (check == false) {
				check = connect.start();
			}
			Statement stmt = connect.con.createStatement();
			stmt.executeUpdate(s);
			connect.con.close();

		} catch (Exception e) {
			System.out.println("exception at line 62 in Database.java " + e);

			// TODO: handle exception
		}

	}

	public static List<StreamInfo> getStreamInfo() {
		List<StreamInfo> sinfo = new ArrayList<>();
		try {

			DbAccess connect = new DbAccess();
			boolean check = false;
			while (check == false) {
				check = connect.start();
			}
			String sql = "select * from stream_info";
			Statement stmt = connect.con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				StreamInfo info = new StreamInfo();
				info.setId(rs.getInt("stream_id"));
				info.setLocation(rs.getString("stream_location"));
				info.setName(rs.getString("stream_name"));
				info.setOwnerName(rs.getString("stream_owner"));
				sinfo.add(info);

			}
			connect.con.close();

		} catch (Exception e) {
			System.out.println("exception at line 48 in Database.java " + e);
			// TODO: handle exception
		}

		return sinfo;

	}

	public static void addTouple(StreamInput input,List<String> attributes) {
		// List<String> cols = new ArrayList<>();
		System.out.println("attributes "+attributes);

		List<Float> fl = new ArrayList<Float>();
		try {
			DbAccess connect = new DbAccess();
			boolean check = false;
			while (check == false) {
				check = connect.start();
			}
			int i=0;
			
			while(i<attributes.size()){
				if(attributes.get(i).equalsIgnoreCase("temperature"))
					fl.add(input.getTemperature());
				if(attributes.get(i).equalsIgnoreCase("humidity"))
					fl.add(input.getHumidity());
				if(attributes.get(i).equalsIgnoreCase("co2"))
					fl.add(input.getCo2());
				if(attributes.get(i).equalsIgnoreCase("windspeed"))
					fl.add(input.getWindspeed());
				if(attributes.get(i).equalsIgnoreCase("precipitation"))
					fl.add(input.getPrecipitation());
				if(attributes.get(i).equalsIgnoreCase("rain"))
					fl.add(input.getRain());
				i++;
			}
			System.out.println("fl size = "+fl.size());
			System.out.println("fl=  "+fl);
			String p = "";
			i=0;
			while(i<fl.size()){
				p = p + ","+fl.get(i);
				
				i++;
			}
			
			String sql = "insert into " + "stream" + input.getId() + " values("
					+ input.getId() + p+")";
			Statement stmt1 = connect.con.createStatement();
			stmt1.executeUpdate(sql);
			connect.con.close();

		} catch (Exception e) {
			System.out.println("exception at line 150 in Database.java " + e);

			// TODO: handle exception
		}

	}

	public static void deleteTouple(int id) {

		try {
			DbAccess connect = new DbAccess();
			boolean check = false;
			while (check == false) {
				check = connect.start();
			}
			String sql = "delete from stream" + id + " LIMIT 1";
			Statement stmt1 = connect.con.createStatement();
			stmt1.executeUpdate(sql);
			connect.con.close();
		} catch (Exception e) {
			System.out.println("exception at deleteTouple in Database.java "
					+ e);

			// TODO: handle exception
		}
	}

	public static void addStreamAttributes(StreamInput input) {

		try {
			DbAccess connect = new DbAccess();
			boolean check = false;
			while (check == false) {
				check = connect.start();
			}
			String sql = "delete from stream_attributes where stream_id = "+input.getId();
			PreparedStatement stmt1 = connect.con.prepareStatement(sql);
			stmt1.executeUpdate();

			sql = "insert into stream_attributes values(?,?,?,?,?)";
			PreparedStatement stmt = connect.con.prepareStatement(sql);
			stmt.setInt(1,input.getId());
			stmt.setFloat(2,input.getTemperature());
			stmt.setFloat(3,input.getHumidity());
			stmt.setFloat(4,input.getCo2());
			stmt.setFloat(5,input.getWindspeed());
			stmt.executeUpdate();
			connect.con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static StreamInput getAttributes(int id) {
	
		StreamInput inp = new StreamInput();
		try {
			DbAccess connect = new DbAccess();
			boolean check = false;
			while (check == false) {
				check = connect.start();
			}
		String sql = "select * from stream_attributes where stream_id = "+id;
		Statement stmt = connect.con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()){
			inp.setId(rs.getInt(1));
			inp.setTemperature(rs.getFloat(2));
			inp.setHumidity(rs.getFloat(3));
			inp.setCo2(rs.getFloat(4));
			inp.setWindspeed(rs.getFloat(5));			
		}
		connect.con.close();
		
		}catch (Exception e) {
			// TODO: handle exception
		}
		return inp;
	}
	public static Result Compute(Result result) {
	//	System.out.println(result.getAttribute());
		DbAccess connect = new DbAccess();
		Result r = new Result();
		boolean check = false;
		while (check == false) {
			check = connect.start();
		}
		try {
			String sql = "select min("+result.getAttribute()+"),max("+result.getAttribute()+"),avg("+result.getAttribute()+"),sum("+result.getAttribute()+") from stream"+result.getStream_id();
			Statement stmt = connect.con.createStatement();
			ResultSet rs1 = stmt.executeQuery(sql);
			if (rs1.next()) {
				if((int)result.getMin()==1)
					r.setMin(rs1.getFloat(1));
				else
					r.setMin(0);
				if((int)result.getMax()==1)
					r.setMax(rs1.getFloat(2));
				else
					r.setMax(0);
				if((int)result.getAvg()==1)
					r.setAvg(rs1.getFloat(3));
				else
					r.setAvg(0);
				if((int)result.getSum()==1)
					r.setSum(rs1.getFloat(4));
				else 
					r.setSum(0);
				r.setStream_id(result.getStream_id());
				r.setAttribute(result.getAttribute());
				
			}
			connect.con.close();	

		} catch (Exception e) {
			// TODO: handle exception
		}

		return r;
	}

}
