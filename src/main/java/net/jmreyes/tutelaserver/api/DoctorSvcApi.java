package net.jmreyes.tutelaserver.api;

import java.util.Collection;

import net.jmreyes.tutelaserver.model.Alert;
import net.jmreyes.tutelaserver.model.Medication;
import net.jmreyes.tutelaserver.model.Patient;
import net.jmreyes.tutelaserver.model.PatientDetails;
import net.jmreyes.tutelaserver.model.Symptom;
import net.jmreyes.tutelaserver.model.Treatment;
import net.jmreyes.tutelaserver.model.extra.DoctorStatus;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
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
public interface DoctorSvcApi {
	public static final String DOCTOR_SVC_PATH = "/doctor";	

	public static final String DOCTOR_STATUS = DOCTOR_SVC_PATH + "/me/status";
	public static final String DOCTOR_ALERTS = DOCTOR_SVC_PATH + "/me/alerts";
	public static final String DOCTOR_ALERTS_MARK_SEEN = DOCTOR_SVC_PATH + "/me/alerts/seen";
	public static final String DOCTOR_PATIENTS = DOCTOR_SVC_PATH + "/me/patients";
	public static final String DOCTOR_SYMPTOMS = DOCTOR_SVC_PATH + "/me/symptoms";
	public static final String DOCTOR_MEDICATION = DOCTOR_SVC_PATH + "/me/medication";
	public static final String DOCTOR_TREATMENT = DOCTOR_SVC_PATH + "/me/treatment";
	
	@GET(DOCTOR_STATUS)
	public DoctorStatus getStatus();
	
	@GET(DOCTOR_ALERTS)
	public Collection<Alert> getAlerts();
	
	@POST(DOCTOR_ALERTS_MARK_SEEN)
	public Void postAlertsMarkAsSeen();
	
	@GET(DOCTOR_PATIENTS)
	public Collection<PatientDetails> getPatients();
	
	@GET(DOCTOR_PATIENTS + "/{id}")
	public Patient getPatient(@Path("id") String id);
	
	@PUT(DOCTOR_PATIENTS + "/{id}")
	public Void updatePatient(@Path("id") String id, @Body PatientDetails patientDetails);
	
	@GET(DOCTOR_SYMPTOMS)
	public Collection<Symptom> getSymptoms();
	
	@POST(DOCTOR_SYMPTOMS)
	public Void postSymptom(@Body Symptom symptom);
	
	@GET(DOCTOR_SYMPTOMS + "/{id}")
	public Symptom getSymptom(@Path("id") String id);
	
	@GET(DOCTOR_MEDICATION)
	public Collection<Medication> getMedication();
	
	@POST(DOCTOR_MEDICATION)
	public Void postMedication(@Body Medication medication);
	
	@GET(DOCTOR_MEDICATION + "/{id}")
	public Medication getOneMedication(@Path("id") String id);
	
	@GET(DOCTOR_TREATMENT + "/{id}")
	public Treatment getTreatment(@Path("id") String id);
	
	@POST(DOCTOR_TREATMENT)
	public Void postTreatment(@Body Treatment medication);
}
