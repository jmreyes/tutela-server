package net.jmreyes.tutelaserver.model.extra;

import java.util.Collection;

import net.jmreyes.tutelaserver.model.Alert;

public class DoctorStatus {
	private String username;
	private String name;
	private int unseenAlerts;	
	
	public DoctorStatus(String username, String name, int unseenAlerts) {
		super();
		this.username = username;
		this.name = name;
		this.unseenAlerts = unseenAlerts;
	}
	
	public DoctorStatus() {
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUnseenAlerts() {
		return unseenAlerts;
	}

	public void setUnseenAlerts(int unseenAlerts) {
		this.unseenAlerts = unseenAlerts;
	}
}