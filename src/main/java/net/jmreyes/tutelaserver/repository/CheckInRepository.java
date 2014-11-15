package net.jmreyes.tutelaserver.repository;

import net.jmreyes.tutelaserver.model.CheckIn;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface CheckInRepository extends MongoRepository<CheckIn, String> {


}
