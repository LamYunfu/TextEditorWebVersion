package javaExp10.po;

import java.sql.Timestamp;

public class LogEntity {
//	private String userID;
	private String operation;
	private Timestamp time;
	
	
	public LogEntity(String operation, Timestamp time) {
		super();
		this.operation = operation;
		this.time = time;
	}
	
	public String getOperation() {
		return operation;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public Timestamp getTime() {
		return time;
	}
	
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}
