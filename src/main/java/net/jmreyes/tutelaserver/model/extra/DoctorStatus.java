package net.jmreyes.tutelaserver.model.extra;

import java.util.Collection;

import net.jmreyes.tutelaserver.model.Alert;

public class DoctorStatus {
	private String username;
	private String name;
	private Collection<Alert> alerts;	
	
	public DoctorStatus(String username, String name, Collection<Alert> alerts) {
		super();
		this.username = username;
		this.name = name;
		this.alerts = alerts;
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
	public Collection<Alert> getAlerts() {
		return alerts;
	}
	public void setAlerts(Collection<Alert> alerts) {
		this.alerts = alerts;
	}
}