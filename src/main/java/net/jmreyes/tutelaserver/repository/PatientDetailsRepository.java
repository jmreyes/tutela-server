package net.jmreyes.tutelaserver.repository;

import java.util.Collection;

import net.jmreyes.tutelaserver.model.PatientDetails;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientDetailsRepository extends MongoRepository<PatientDetails, String> {

	public Collection<PatientDetails> findByPatientId(String patientId);
	public Collection<PatientDetails> findByDoctorId(String doctorId);
	public PatientDetails findByPatientIdAndDoctorId(String patientId, String doctorId);
	public Collection<PatientDetails> findByDoctorIdAndFirstNameStartingWithIgnoreCaseOrLastNameStartingWithIgnoreCase(String doctorId, String query1, String query2);
	
}
