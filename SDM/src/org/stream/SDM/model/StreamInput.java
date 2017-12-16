package org.stream.SDM.model;

import java.io.Serializable;

public class StreamInput implements Serializable {

	private int id=0;
	private float temperature=0;
	private float humidity=0;
	private float co2=0;
	private float windspeed=0;
	private float precipitation=0;
	private float rain=0;  //in inches
	private float param1=0;
	private float param2=0;
	private float param3=0;
	
	
	
	public float getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(float precipitation) {
		this.precipitation = precipitation;
	}

	public float getRain() {
		return rain;
	}

	public void setRain(float rain) {
		this.rain = rain;
	}

	public float getParam1() {
		return param1;
	}

	public void setParam1(float param1) {
		this.param1 = param1;
	}

	public float getParam2() {
		return param2;
	}

	public void setParam2(float param2) {
		this.param2 = param2;
	}

	public float getParam3() {
		return param3;
	}

	public void setParam3(float param3) {
		this.param3 = param3;
	}

	

	public StreamInput() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StreamInput(int id,float temperature, float windspeed, float humidity, float co2) {
		super();
		this.id = id;
		this.temperature = temperature;
		this.windspeed = windspeed;
		this.humidity = humidity;
		this.co2 = co2;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getWindspeed() {
		return windspeed;
	}

	public void setWindspeed(float windspeed) {
		this.windspeed = windspeed;
	}

	public float getHumidity() {
		return humidity;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public float getCo2() {
		return co2;
	}

	public void setCo2(float co2) {
		this.co2 = co2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
