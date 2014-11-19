package net.jmreyes.tutelaserver.model.extra;

import java.util.ArrayList;
import java.util.Collection;

import net.jmreyes.tutelaserver.model.PatientDetails;

public class MyDoctor {
	private String id;
    private String name;

    public MyDoctor(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public MyDoctor() {
    }

    public static ArrayList<MyDoctor> createListFromPatientDetails(Collection<PatientDetails> patientDetails) {
        ArrayList<MyDoctor> result = new ArrayList<MyDoctor>();

        for (PatientDetails p : patientDetails) {
            result.add(new MyDoctor(p.getDoctorId(),p.getDoctorName()));
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
}
