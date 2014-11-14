package org.magnum.mobilecloud.integration.test;

import static org.junit.Assert.*;

import java.util.Collection;

import net.jmreyes.tutelaserver.api.PatientSvcApi;
import net.jmreyes.tutelaserver.api.SecuredRestBuilder;
import net.jmreyes.tutelaserver.api.VideoSvcApi;
import net.jmreyes.tutelaserver.model.Video;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.magnum.mobilecloud.video.TestData;

import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.client.ApacheClient;

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
public class PatientSvcClientApiTest {

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

	private final String TEST_PATIENT_USERNAME = "test@a.com";
	private final String TEST_PATIENT_PASSWORD = "test";
	private final String TEST_DOCTOR_USERNAME = "test@b.com";
	private final String TEST_DOCTOR_PASSWORD = "test";

	private PatientSvcApi patientService = new SecuredRestBuilder()
			.setClient(new ApacheClient(UnsafeHttpsClient.createUnsafeClient()))
			.setEndpoint(TEST_URL)
			.setLoginEndpoint(TEST_URL + TOKEN_PATH)
			.setLogLevel(LogLevel.FULL)
			.setUsername(TEST_PATIENT_USERNAME)
			.setPassword(TEST_PATIENT_PASSWORD).setClientId(CLIENT_ID).build()
			.create(PatientSvcApi.class);

	private Video video = TestData.randomVideo();

	/**
	 * This test creates a Video and attempts to add it to the video service
	 * without logging in. The test checks to make sure that the request is
	 * denied and the client redirected to the login page.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetTreatments() throws Exception {
		//ErrorRecorder error = new ErrorRecorder();

		try {
			// This should fail because we haven't logged in!
			patientService.getTreatments();


		} catch (Exception e) {

			fail("testGetTreatments");
			
			// Ok, our security may have worked, ensure that
			// we got redirected to the login page
			//assertEquals(HttpStatus.SC_MOVED_TEMPORARILY, error.getError()
			//		.getResponse().getStatus());
		}
	}

	

}
