package net.jmreyes.tutelaserver.repository;

import net.jmreyes.tutelaserver.api.PatientSvcApi;
import net.jmreyes.tutelaserver.model.Patient;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = PatientSvcApi.PATIENT_SVC_PATH)
public interface PatientRepository extends MongoRepository<Patient, String> {

	public Patient findByUsername(String username);	
}
