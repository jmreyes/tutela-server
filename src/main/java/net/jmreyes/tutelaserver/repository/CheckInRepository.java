package net.jmreyes.tutelaserver.repository;

import java.util.Collection;

import net.jmreyes.tutelaserver.model.CheckIn;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface CheckInRepository extends MongoRepository<CheckIn, String> {
	public Collection<CheckIn> findByTreatmentId(String treatmentId);
}
