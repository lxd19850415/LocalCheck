package com.sandy.localcheck.path;

public class ModelPath {
	public int id;
	String startTime;
	String endTime;
	String name;
	String pathList;
	
	
	public ModelPath(int id,String startTime,String endTime, String name, String pathList) {
		this.id = id;

		this.startTime = startTime;
		this.endTime = endTime;
		this.name = name;
		this.pathList = pathList;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPathList() {
		return pathList;
	}
	public void setPathList(String pathList) {
		this.pathList = pathList;
	}
}
