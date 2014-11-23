package net.jmreyes.tutelaserver.model;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A simple object to represent a video and its URL for viewing.
 * 
 * @author jules
 * 
 */
@Document
public class Alert {

	@Id
	private String id;
	
	private String doctorId;
	private String patientId;
	private String patientName;
	private String treatmentId;
	private boolean seen;
	private String symptomName;
	private int hours;
	private String ansText;
	private Date date;
	
	public Alert(String id, String doctorId, String patientId,
			String patientName, String treatmentId, boolean seen, String symptomName,
			int hours, String ansText, Date date) {
		super();
		this.id = id;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.patientName = patientName;
		this.treatmentId = treatmentId;
		this.seen = seen;
		this.symptomName = symptomName;
		this.hours = hours;
		this.ansText = ansText;
		this.date = date;
	}
	
	public Alert() {
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getTreatmentId() {
		return treatmentId;
	}
	public void setTreatmentId(String treatmentId) {
		this.treatmentId = treatmentId;
	}
	public boolean isSeen() {
		return seen;
	}
	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	public String getSymptomName() {
		return symptomName;
	}

	public void setSymptomName(String symptomName) {
		this.symptomName = symptomName;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public String getAnsText() {
		return ansText;
	}
	public void setAnsText(String ansText) {
		this.ansText = ansText;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
