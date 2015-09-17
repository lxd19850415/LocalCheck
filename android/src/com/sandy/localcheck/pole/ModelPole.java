package com.sandy.localcheck.pole;

public class ModelPole {
	public int id;
	public String time;
	public String name;
	public double lat;
	public double lon;
	
	
	public ModelPole(int id,String time, String name, double lat, double lon) {
		super();
		this.id = id;
		this.time = time;
		this.name = name;
		this.lat = lat;
		this.lon = lon;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
}
