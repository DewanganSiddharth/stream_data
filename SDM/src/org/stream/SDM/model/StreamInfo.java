package org.stream.SDM.model;

import java.io.Serializable;
import java.util.List;

public class StreamInfo implements Serializable {

	private String name; // stream name eg. temp
	private String location; // client port or ip address
	private String ownerName; // client name eg. twitter
	private int id;
	List <String> attributes;
	
	public List<String> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}

	public StreamInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StreamInfo(String name, String location, String ownerName,int id) {
		super();
		this.name = name;
		this.location = location;
		this.ownerName = ownerName;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
