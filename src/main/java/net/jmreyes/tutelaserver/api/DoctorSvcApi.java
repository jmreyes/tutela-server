package net.jmreyes.tutelaserver.api;

import java.util.Collection;

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
public interface DoctorSvcApi {
	public static final String DOCTOR_SVC_PATH = "/doctor";	

	public static final String DOCTOR_STATUS = DOCTOR_SVC_PATH + "/me/status";
	public static final String DOCTOR_ALERTS = DOCTOR_SVC_PATH + "/me/alerts";
	public static final String DOCTOR_ALERTS_MARK_SEEN = DOCTOR_SVC_PATH + "/me/alerts/seen";
	public static final String DOCTOR_PATIENTS = DOCTOR_SVC_PATH + "/me/patients";
	public static final String DOCTOR_SYMPTOMS = DOCTOR_SVC_PATH + "/me/symptoms";
	public static final String DOCTOR_MEDICATION = DOCTOR_SVC_PATH + "/me/medication";
	public static final String DOCTOR_TREATMENT = DOCTOR_SVC_PATH + "/me/treatment";	
}
