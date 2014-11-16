package net.jmreyes.tutelaserver.model.extra;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import net.jmreyes.tutelaserver.model.Symptom;
import net.jmreyes.tutelaserver.model.Treatment;
import net.jmreyes.tutelaserver.model.Treatment.EmbeddedMedication;
import net.jmreyes.tutelaserver.model.Treatment.EmbeddedSymptom;


public class CheckInProposal {
	
	private String treatmentId;
	private String patientId;

	private Collection<EmbeddedMedicationProposal> medication;
	private Collection<EmbeddedSymptomProposal> symptoms;

	public CheckInProposal(String treatmentId, String patientId,
			Collection<EmbeddedMedicationProposal>  medication,
			Collection<EmbeddedSymptomProposal> symptoms) {
		super();
		this.treatmentId = treatmentId;
		this.patientId = patientId;
		this.medication = medication;
		this.symptoms = symptoms;
	}
	
	public static Collection<CheckInProposal> createFromTreatments(Collection<Treatment> treatments, HashMap<String, Symptom> symptoms) {
		Collection<CheckInProposal> checkInProposals = new ArrayList<CheckInProposal>();

		for (Treatment t : treatments) {
			CheckInProposal cp = new CheckInProposal();
			
			cp.setTreatmentId(t.getId());
			cp.setPatientId(t.getPatientId());
			
			ArrayList<EmbeddedMedicationProposal> emps = new ArrayList<EmbeddedMedicationProposal>();
			for (EmbeddedMedication em : t.getMedication()) {
				EmbeddedMedicationProposal emp = new EmbeddedMedicationProposal(em.getMedicationId(), em.getMedicationName());
				emps.add(emp);				
			}
			cp.setMedication(emps);
			
			ArrayList<EmbeddedSymptomProposal> esps = new ArrayList<EmbeddedSymptomProposal>();
			for (EmbeddedSymptom es : t.getSymptoms()) {
				Symptom s = symptoms.get(es.getSymptomId());				
				if (s == null) continue;				
				String question = s.getQuestion();
				Collection<Answer> answers = s.getAnswers();
				EmbeddedSymptomProposal esp = new EmbeddedSymptomProposal(es.getSymptomId(), es.getSymptomName(), question, answers);
				esps.add(esp);
			}
			cp.setSymptoms(esps);		

			checkInProposals.add(cp);
		}
		
		return checkInProposals;		
	}
	
	public CheckInProposal() {
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

	public Collection<EmbeddedMedicationProposal>  getMedication() {
		return medication;
	}

	public void setMedication(Collection<EmbeddedMedicationProposal>  medication) {
		this.medication = medication;
	}

	public Collection<EmbeddedSymptomProposal> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(Collection<EmbeddedSymptomProposal> symptoms) {
		this.symptoms = symptoms;
	}
	
	public static class EmbeddedMedicationProposal {
		private String medicationId;
		private String medicationName;
		
		public EmbeddedMedicationProposal(String medicationId,
				String medicationName) {
			super();
			this.medicationId = medicationId;
			this.medicationName = medicationName;
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
	}
	
	public static class EmbeddedSymptomProposal {
		private String symptomId;
		private String sypmtomName;
		private String question;
		private Collection<Answer> answers;
		
		public EmbeddedSymptomProposal(String symptomId, String sypmtomName,
				String question,
				Collection<Answer> answers) {
			super();
			this.symptomId = symptomId;
			this.sypmtomName = sypmtomName;
			this.question = question;
			this.answers = answers;
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
		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public Collection<Answer> getAnswers() {
			return answers;
		}

		public void setAnswers(Collection<Answer> answers) {
			this.answers = answers;
		}
	}


}
