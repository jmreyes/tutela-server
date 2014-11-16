package net.jmreyes.tutelaserver.model;

import java.util.Collection;

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

	private Collection<EmbeddedMedication> medication;
	private Collection<EmbeddedSymptom> symptoms;
	
	public Treatment(String id, String patientId, String patientName,
			String doctorId, Collection<EmbeddedMedication> medication,
			Collection<EmbeddedSymptom> symptoms) {
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
	public Collection<EmbeddedMedication> getMedication() {
		return medication;
	}
	public void setMedication(Collection<EmbeddedMedication> medication) {
		this.medication = medication;
	}
	public Collection<EmbeddedSymptom> getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(Collection<EmbeddedSymptom> symptoms) {
		this.symptoms = symptoms;
	}

	public static class EmbeddedMedication {
		private String medicationId;
		private String medicationName;
		private String notes;
		
		public EmbeddedMedication(String medicationId, String medicationName,
				String notes) {
			super();
			this.medicationId = medicationId;
			this.medicationName = medicationName;
			this.notes = notes;
		}

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
	
	public static class EmbeddedSymptom {
		private String symptomId;
		private String symptomName;
		
		public EmbeddedSymptom(String symptomId, String symptomName) {
			super();
			this.symptomId = symptomId;
			this.symptomName = symptomName;
		}

		public EmbeddedSymptom() {
		}
		
		public String getSymptomId() {
			return symptomId;
		}
		public void setSymptomId(String symptomId) {
			this.symptomId = symptomId;
		}

		public String getSymptomName() {
			return symptomName;
		}

		public void setSymptomName(String symptomName) {
			this.symptomName = symptomName;
		}
		
	}
}
