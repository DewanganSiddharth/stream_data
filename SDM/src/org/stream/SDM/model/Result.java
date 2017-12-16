package org.stream.SDM.model;

public class Result {

	private int stream_id;
	private float max;
	private float min;
	private float avg;
	private float sum;
	private String attribute;	
	public Result() {
		super();
	}
	public Result(int stream_id, float max, float min, float avg, float sum,String attribute) {
		super();
		this.stream_id = stream_id;
		this.max = max;
		this.min = min;
		this.avg = avg;
		this.sum = sum;
		this.attribute=attribute;
	}
	public int getStream_id() {
		return stream_id;
	}
	public void setStream_id(int stream_id) {
		this.stream_id = stream_id;
	}
	public float getMax() {
		return max;
	}
	public void setMax(float max) {
		this.max = max;
	}
	public float getMin() {
		return min;
	}
	public void setMin(float min) {
		this.min = min;
	}
	public float getAvg() {
		return avg;
	}
	public void setAvg(float avg) {
		this.avg = avg;
	}
	public float getSum() {
		return sum;
	}
	public void setSum(float sum) {
		this.sum = sum;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
	
}
