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
public interface MedicationSvcApi {
	
	public static final String NAME_PARAMETER = "name";
	
	// The path where we expect the MedicationSvc to live
	public static final String MEDICATION_SVC_PATH = "/medication";
	
}
