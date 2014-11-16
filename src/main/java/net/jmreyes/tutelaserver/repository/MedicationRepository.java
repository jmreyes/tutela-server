package net.jmreyes.tutelaserver.repository;

import java.util.Collection;

import net.jmreyes.tutelaserver.model.Medication;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/medication")
public interface MedicationRepository extends MongoRepository<Medication, String>{

	public Collection<Medication> findByDoctorId(String doctorId);
	public Medication findByIdAndDoctorId(String id, String doctorId);
}
