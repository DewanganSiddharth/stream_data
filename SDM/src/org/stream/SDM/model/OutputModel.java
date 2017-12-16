package org.stream.SDM.model;

import java.util.ArrayList;
import java.util.List;

public class OutputModel {
	private List <Result> rlist;
	private String attribute;
	public List <Result> getRlist() {
		return rlist;
	}

	public void setRlist(List <Result> rlist) {
		this.rlist = rlist;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	} 

}
