package net.jmreyes.tutelaserver.repository;

import net.jmreyes.tutelaserver.model.Doctor;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoctorRepository extends MongoRepository<Doctor, String> {
	
	public Doctor findByUsername(String username);
	public Doctor findById(String id);	
}
