package net.jmreyes.tutelaserver.repository;

import java.util.Collection;

import net.jmreyes.tutelaserver.model.Symptom;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface SymptomRepository extends MongoRepository<Symptom, String> {
	
	public Collection<Symptom> findByDoctorId(String doctorId);
	public Symptom findByIdAndDoctorId(String id, String doctorId);
	public Collection<Symptom> findByIdIn(Collection<String> ids);
}
