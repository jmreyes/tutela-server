package net.jmreyes.tutelaserver.model;

import java.util.Collection;

import net.jmreyes.tutelaserver.model.extra.Answer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A simple object to represent a video and its URL for viewing.
 * 
 * @author jules
 * 
 */
@Document
public class Symptom {

	@Id
	private String id;

	private String doctorId;
	private String name;	
	private String question;	
	private Collection<Answer> answers;	
	private Collection<EmbeddedAlerts> alerts;

	public Symptom(String id, String doctorId, String name, String question,
			Collection<Answer> answers,
			Collection<EmbeddedAlerts> alerts) {
		super();
		this.id = id;
		this.doctorId = doctorId;
		this.name = name;
		this.question = question;
		this.answers = answers;
		this.alerts = alerts;
	}
	
	public Symptom() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Collection<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Collection<Answer> answers) {
		this.answers = answers;
	}

	public Collection<EmbeddedAlerts> getAlerts() {
		return alerts;
	}

	public void setAlerts(Collection<EmbeddedAlerts> alerts) {
		this.alerts = alerts;
	}
	
	public class EmbeddedAlerts {
		private String hours;
		private int ansIndex;
		
		public EmbeddedAlerts(){			
		}
	}
}
