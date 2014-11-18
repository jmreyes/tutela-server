package org.magnum.mobilecloud.integration.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import net.jmreyes.tutelaserver.api.DoctorSvcApi;
import net.jmreyes.tutelaserver.api.SecuredRestBuilder;
import net.jmreyes.tutelaserver.model.Medication;
import net.jmreyes.tutelaserver.model.PatientDetails;
import net.jmreyes.tutelaserver.model.Symptom;
import net.jmreyes.tutelaserver.model.Symptom.EmbeddedAlert;
import net.jmreyes.tutelaserver.model.Treatment;
import net.jmreyes.tutelaserver.model.Treatment.EmbeddedMedication;
import net.jmreyes.tutelaserver.model.Treatment.EmbeddedSymptom;
import net.jmreyes.tutelaserver.model.extra.Answer;

import org.junit.Test;

import retrofit.ErrorHandler;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.client.ApacheClient;
import retrofit.converter.GsonConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * This integration test sends a POST request to the VideoServlet to add a new
 * video and then sends a second GET request to check that the video showed up
 * in the list of videos. Actual network communication using HTTP is performed
 * with this test.
 * 
 * The test requires that the VideoSvc be running first (see the directions in
 * the README.md file for how to launch the Application).
 * 
 * To run this test, right-click on it in Eclipse and select
 * "Run As"->"JUnit Test"
 * 
 * Pay attention to how this test that actually uses HTTP and the test that just
 * directly makes method calls on a VideoSvc object are essentially identical.
 * All that changes is the setup of the videoService variable. Yes, this could
 * be refactored to eliminate code duplication...but the goal was to show how
 * much Retrofit simplifies interaction with our service!
 * 
 * @author jules
 *
 */
public class DoctorSvcClientApiTest {

	private class ErrorRecorder implements ErrorHandler {

		private RetrofitError error;

		@Override
		public Throwable handleError(RetrofitError cause) {
			error = cause;
			return error.getCause();
		}

		public RetrofitError getError() {
			return error;
		}
	}

	private final String TEST_URL = "http://localhost:8443";
	private final String TOKEN_PATH = "/oauth/token";
	private final String CLIENT_ID = "mobile";

	private final String TEST_DOCTOR_USERNAME = "test@b.com";
	private final String TEST_DOCTOR_PASSWORD = "test";
	
	Gson gson = new GsonBuilder()
	.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	.create();

	private DoctorSvcApi doctorService = new SecuredRestBuilder()
			.setClient(new ApacheClient(UnsafeHttpsClient.createUnsafeClient()))
			.setEndpoint(TEST_URL)
			.setLoginEndpoint(TEST_URL + TOKEN_PATH)
			//.setLogLevel(LogLevel.FULL)
			.setLogLevel(LogLevel.FULL)
			.setConverter(new GsonConverter(gson))
			.setUsername(TEST_DOCTOR_USERNAME)
			.setPassword(TEST_DOCTOR_PASSWORD).setClientId(CLIENT_ID).build()
			.create(DoctorSvcApi.class);

	/**
	 * This test creates a Video and attempts to add it to the video service
	 * without logging in. The test checks to make sure that the request is
	 * denied and the client redirected to the login page.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetStatus() {
		try {
			doctorService.getStatus();
		} catch (Exception e) {
			fail("testGetStatus");
		}
	}
	
	@Test
	public void testGetAlerts() {
		try {
			doctorService.getAlerts();
		} catch (Exception e) {
			fail("testGetAlerts");
		}
	}
	
	@Test
	public void testPostAlertsMarkAsSeen() {
		try {
			doctorService.postAlertsMarkAsSeen();
		} catch (Exception e) {
			fail("postAlertsMarkAsSeen");
		}
	}
	
	@Test
	public void testGetPatients() {
		try {
			doctorService.getPatients();
		} catch (Exception e) {
			e.printStackTrace();
			fail("testGetPatients");
		}
	}
	
	@Test
	public void testGetPatient() {
		try {
			doctorService.getPatient("543da48171d2272d61e32153");
		} catch (Exception e) {
			e.printStackTrace();
			fail("testGetPatient");
		}
	}
	
	@Test
	public void testPutPatient() {
		try {
			PatientDetails pd = new PatientDetails("54674178c8e1368bc081d22f",
					"543da48171d2272d61e32153", "543da48171d2272d61e32159",
					"John Malkovich", null, "asdas", "John", "Cobra",
					"asomdas");
			
			doctorService.updatePatient("543da48171d2272d61e32153", pd);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testPutPatient");
		}
	}
	
	@Test
	public void testGetSymptoms() {
		try {
			doctorService.getSymptoms();
		} catch (Exception e) {
			e.printStackTrace();
			fail("testGetSymptoms");
		}
	}
	
	@Test
	public void testPostSymptom() {
		try {
			ArrayList<Answer> answers = new ArrayList<Answer>();
			answers.add(new Answer("YES", 1));
			answers.add(new Answer("NO", 2));
			
			ArrayList<EmbeddedAlert> alerts = new ArrayList<EmbeddedAlert>();
			alerts.add(new EmbeddedAlert(8, 1));
			alerts.add(new EmbeddedAlert(16, 2));
			
			Symptom s = new Symptom("546805c593f4bf54dd318fe0", null, "Throat pain", "Does it hurttt?", answers, alerts);
			
			doctorService.postSymptom(s);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testPostSymptom");
		}
	}
	
	@Test
	public void testGetSymptom() {
		try {
			doctorService.getSymptom("54679598c8e1368bc081d231");
		} catch (Exception e) {
			e.printStackTrace();
			fail("testGetSymptom");
		}
	}
	
	@Test
	public void testGetMedication() {
		try {
			doctorService.getMedication();
		} catch (Exception e) {
			e.printStackTrace();
			fail("testGetMedication");
		}
	}
	
	@Test
	public void testPostMedication() {
		try {			
			Medication m = new Medication("54680b1c93f474396b790be3", "546805c593f4bf54dd318fe0", "Vineger");
			
			doctorService.postMedication(m);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testPostMedication");
		}
	}
	
	@Test
	public void testGetOneMedication() {
		try {
			doctorService.getOneMedication("54680b1c93f474396b790be3");
		} catch (Exception e) {
			e.printStackTrace();
			fail("testGetOneMedication");
		}
	}
	
	@Test
	public void testGetTreatment() {
		try {
			doctorService.getTreatment("5468199493f4336f67e26741");
		} catch (Exception e) {
			e.printStackTrace();
			fail("testGetTreatment");
		}
	}
	
	@Test
	public void testPostTreatment() {
		try {
			EmbeddedMedication em = new EmbeddedMedication("aMedicationId", "SomeMed", "adadsjaksjdkajsdkjak");
			ArrayList<EmbeddedMedication> ems = new ArrayList<EmbeddedMedication>();
			ems.add(em);
			
			EmbeddedSymptom es = new EmbeddedSymptom("aSymptomId", "A symptom name");
			ArrayList<EmbeddedSymptom> ess = new ArrayList<EmbeddedSymptom>();
			ess.add(es);
			
			Treatment t = new Treatment(null, "543da48171d2272d61e32153", "John Cobra", null, null, ems, ess);
			
			doctorService.postTreatment(t);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testPostTreatment");
		}
	}
}
