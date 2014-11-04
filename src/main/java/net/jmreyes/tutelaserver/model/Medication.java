package net.jmreyes.tutelaserver.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

/**
 * A simple object to represent a video and its URL for viewing.
 * 
 * @author jules
 * 
 */
@Document
public class Medication {

	@Id
	private String id;

	private String name;
	
	private String dummyField;	

	private String doctorName;
	
	private String frequency;
	
	private String notes;
	
	public Medication() {
	}

	public Medication(String name, String url, long duration) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@JsonIgnore
	public String getDummyField() {
		return dummyField;
	}
	
	public void setDummyField(String dummyField) {
		this.dummyField = dummyField;
	}
	
	public String getDoctorName() {
		return doctorName;
	}

	public void getDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	
	public String getFrequency() {
		return frequency;
	}

	public void getFrequency(String frequency) {
		this.frequency = frequency;
	}
	
	public String getNotes() {
		return notes;
	}

	public void getNotes(String notes) {
		this.notes = notes;
	}



	/**
	 * Two Videos will generate the same hashcode if they have exactly the same
	 * values for their name, url, and duration.
	 * 
	 */
	@Override
	public int hashCode() {
		// Google Guava provides great utilities for hashing
		return Objects.hashCode(name);
	}

	/**
	 * Two Videos are considered equal if they have exactly the same values for
	 * their name, url, and duration.
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Medication) {
			Medication other = (Medication) obj;
			// Google Guava provides great utilities for equals too!
			return Objects.equal(name, other.name);
		} else {
			return false;
		}
	}

}
