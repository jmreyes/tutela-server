package net.jmreyes.tutelaserver.repository;

import net.jmreyes.tutelaserver.api.VideoSvcApi;
import net.jmreyes.tutelaserver.model.Doctor;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface DoctorRepository extends MongoRepository<Doctor, String> {
	
	public Doctor findByUsername(@Param(VideoSvcApi.TITLE_PARAMETER) String username);
	public Doctor findById(String id);	
}
