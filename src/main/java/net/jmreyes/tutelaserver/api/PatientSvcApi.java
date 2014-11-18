package net.jmreyes.tutelaserver.api;

import java.util.Collection;

import net.jmreyes.tutelaserver.model.CheckIn;
import net.jmreyes.tutelaserver.model.Doctor;
import net.jmreyes.tutelaserver.model.PatientDetails;
import net.jmreyes.tutelaserver.model.Treatment;
import net.jmreyes.tutelaserver.model.extra.CheckInProposal;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

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
	public static final String PATIENT_PATIENTDETAILS = PATIENT_SVC_PATH + "/me/details";
	public static final String PATIENT_DOCTORS = PATIENT_SVC_PATH + "/me/doctors";
	public static final String PATIENT_CHECKIN = PATIENT_SVC_PATH + "/me/checkin";
	
	@GET(PATIENT_TREATMENTS)
	public Collection<Treatment> getTreatments();
	
	@GET(PATIENT_TREATMENTS + "/{id}")
	public Treatment getTreatment(@Path("id") String id);
	
	@GET(PATIENT_PATIENTDETAILS)
	public Collection<PatientDetails> getPatientDetails();
	
	@GET(PATIENT_DOCTORS + "/{id}")
	public Doctor getDoctor(@Path("id") String id);
	
	@GET(PATIENT_CHECKIN)
	public Collection<CheckInProposal> getCheckInProposal();
	
	@POST(PATIENT_CHECKIN)
	public Void postCheckIn(@Body Collection<CheckIn> checkIns);
}
