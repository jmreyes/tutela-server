package net.jmreyes.tutelaserver.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import net.jmreyes.tutelaserver.api.CustomDateSerializer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * A simple object to represent a video and its URL for viewing.
 * 
 * @author jules
 * 
 */
@Document
public class CheckIn {

	@Id
	private String id;
	
	private String treatmentId;
	private String patientId;	

	@JsonSerialize(using = CustomDateSerializer.class)
	private Date date;

	private Collection<EmbeddedMedication> medication;
	private Collection<EmbeddedSymptom> symptoms;

	public CheckIn(String treatmentId, String patientId, Date date,
			Collection<EmbeddedMedication> medication,
			Collection<EmbeddedSymptom> symptoms) {
		super();
		this.treatmentId = treatmentId;
		this.patientId = patientId;
		this.date = date;
		this.medication = medication;
		this.symptoms = symptoms;
	}
	
	public CheckIn() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTreatmentId() {
		return treatmentId;
	}

	public void setTreatmentId(String treatmentId) {
		this.treatmentId = treatmentId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
		private boolean taken;
		@JsonSerialize(using = CustomDateSerializer.class)
		private Date date;
		
		public EmbeddedMedication() {			
		}
		
		public EmbeddedMedication(String medicationId, String medicationName,
				boolean taken, Date date) {
			super();
			this.medicationId = medicationId;
			this.medicationName = medicationName;
			this.taken = taken;
			this.date = date;
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
		
		public boolean getTaken() {
			return taken;
		}
		
		public void setTaken(boolean taken) {
			this.taken = taken;
		}
		
		public Date getDate() {
			return date;
		}
		
		public void setDate(Date date) {
			this.date = date;
		}
	}
	
	public static class EmbeddedSymptom {
		private String symptomId;
		private String symptomName;
		private String ansText;
		private int ansIndex;
		
		public EmbeddedSymptom() {			
		}
		
		public EmbeddedSymptom(String symptomId, String sypmtomName,
				String ansText, int ansIndex) {
			super();
			this.symptomId = symptomId;
			this.symptomName = sypmtomName;
			this.ansText = ansText;
			this.ansIndex = ansIndex;
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
		
		public String getAnsText() {
			return ansText;
		}
		
		public void setAnsText(String ansText) {
			this.ansText = ansText;
		}
		
		public int getAnsIndex() {
			return ansIndex;
		}
		
		public void setAnsIndex(int ansIndex) {
			this.ansIndex = ansIndex;
		}
	}
}
