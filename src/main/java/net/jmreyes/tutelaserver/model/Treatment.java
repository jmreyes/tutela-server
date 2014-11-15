package net.jmreyes.tutelaserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A simple object to represent a video and its URL for viewing.
 * 
 * @author jules
 * 
 */
@Document
public class Treatment {

	@Id
	private String id;
	
	private String patientId;
	private String patientName;
	private String doctorId;

	private EmbeddedMedication medication[];
	private EmbeddedSymptom symptoms[];
	
	public Treatment(String id, String patientId, String patientName,
			String doctorId, EmbeddedMedication[] medication,
			EmbeddedSymptom[] symptoms) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.patientName = patientName;
		this.doctorId = doctorId;
		this.medication = medication;
		this.symptoms = symptoms;
	}
	
	public Treatment() {
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public EmbeddedMedication[] getMedication() {
		return medication;
	}
	public void setMedication(EmbeddedMedication[] medication) {
		this.medication = medication;
	}
	public EmbeddedSymptom[] getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(EmbeddedSymptom[] symptoms) {
		this.symptoms = symptoms;
	}

	public class EmbeddedMedication {
		private String medicationId;
		private String medicationName;
		private String notes;
		
		public EmbeddedMedication() {			
		}
		
		public String getMedicationId() {
			return medicationId;
		}
		public void setMedicationId(String medicationId) {
			this.medicationId = medicationId;
		}
		public String getMedicationName() {
			return medicationName;
		}
		public void setMedicationName(String medicationName) {
			this.medicationName = medicationName;
		}
		public String getNotes() {
			return notes;
		}
		public void setNotes(String notes) {
			this.notes = notes;
		}
	}
	
	public class EmbeddedSymptom {
		private String symptomId;
		private String sypmtomName;
		
		public EmbeddedSymptom() {
		}
		
		public String getSymptomId() {
			return symptomId;
		}
		public void setSymptomId(String symptomId) {
			this.symptomId = symptomId;
		}
		public String getSypmtomName() {
			return sypmtomName;
		}
		public void setSypmtomName(String sypmtomName) {
			this.sypmtomName = sypmtomName;
		}
	}
}
