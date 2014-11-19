package net.jmreyes.tutelaserver.model.extra;

import java.util.ArrayList;
import java.util.Collection;

import net.jmreyes.tutelaserver.model.Treatment;

public class MyMedication {
    private String id;
    private String name;
    private String treatmentId;
    private String doctorId;
    private String doctorName;
    private String notes;

    public MyMedication(String id, String name, String treatmentId, String doctorId, String doctorName, String notes) {
        this.id = id;
        this.name = name;
        this.treatmentId = treatmentId;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.notes = notes;
    }
    
    public MyMedication() {    	
    }

    public static MyMedication createFromTreatment(Treatment treatment, String medicationId) {
        Collection<Treatment.EmbeddedMedication> embeddedMedication = treatment.getMedication();

        Treatment.EmbeddedMedication specificEm = null;
        for (Treatment.EmbeddedMedication em : embeddedMedication) {
            if (medicationId.equals(em.getMedicationId())) {
                specificEm = em;
                break;
            }
        }

        if (specificEm == null) return null;

        return new MyMedication(specificEm.getMedicationId(), specificEm.getMedicationName(),
                treatment.getId(), treatment.getDoctorId(),
                treatment.getDoctorName(), specificEm.getNotes());
    }

    public static ArrayList<MyMedication> createListFromTreatments(Collection<Treatment> treatments) {
        ArrayList<MyMedication> result = new ArrayList<MyMedication>();

        for (Treatment t : treatments) {
            Collection<Treatment.EmbeddedMedication> embeddedMedication = t.getMedication();

            for (Treatment.EmbeddedMedication em : embeddedMedication) {
                result.add(new MyMedication(em.getMedicationId(),
                        em.getMedicationName(), t.getId(), t.getDoctorId(), t.getDoctorName(), em.getNotes()));
            }
        }

        return result;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTreatmentId() {
		return treatmentId;
	}

	public void setTreatmentId(String treatmentId) {
		this.treatmentId = treatmentId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
    
    
}