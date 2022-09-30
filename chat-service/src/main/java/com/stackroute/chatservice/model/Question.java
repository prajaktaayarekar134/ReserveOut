package com.stackroute.chatservice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String question;
	private int cid;
	private String cname;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="question_id")
	private List<Answer> answers;
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Question(String question, int cid, String cname) {
		super();
		this.question = question;
		this.cid = cid;
		this.cname = cname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	public void addanswer(Answer answer) {
		if(this.answers == null) {
			this.answers= new ArrayList<Answer>();
		}
		this.answers.add(answer);
	}



}