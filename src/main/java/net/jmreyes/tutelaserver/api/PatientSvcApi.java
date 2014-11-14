package net.jmreyes.tutelaserver.api;

import java.util.Collection;

import net.jmreyes.tutelaserver.model.Patient;
import net.jmreyes.tutelaserver.model.Video;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * This interface defines an API for a VideoSvc. The
 * interface is used to provide a contract for client/server
 * interactions. The interface is annotated with Retrofit
 * annotations so that clients can automatically convert the
 * 
 * 
 * @author jules
 *
 */
public interface PatientSvcApi {
	public static final String PATIENT_SVC_PATH = "/patient";	

	public static final String PATIENT_TREATMENTS = PATIENT_SVC_PATH + "/me/treatments";
	public static final String PATIENT_DOCTORS = PATIENT_SVC_PATH + "/me/doctors";
	public static final String PATIENT_CHECKIN = PATIENT_SVC_PATH + "/me/checkin";
	
	@GET(PATIENT_TREATMENTS)
	public Collection<Patient> getTreatments();
}
