package net.jmreyes.tutelaserver.repository;

import java.util.Collection;

import net.jmreyes.tutelaserver.model.Alert;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface AlertRepository extends MongoRepository<Alert, String> {

	public Collection<Alert> findByDoctorId(String doctorId);
	public Collection<Alert> findByDoctorIdAndSeenFalse(String doctorId);
}
