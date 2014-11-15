package net.jmreyes.tutelaserver.repository;

import java.util.Collection;

import net.jmreyes.tutelaserver.model.Treatment;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface TreatmentRepository extends MongoRepository<Treatment, String> {

	public Collection<Treatment> findByPatientId(String patientId);
	public Treatment findByIdAndPatientId(String id, String patientId);
}
