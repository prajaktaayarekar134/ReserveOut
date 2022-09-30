package com.stackroute.chatservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stackroute.chatservice.model.Answer;
import com.stackroute.chatservice.model.Question;
import com.stackroute.chatservice.repository.AnswerRepository;
import com.stackroute.chatservice.repository.questionrepository;

@Service
public class Chatservice {
	@Autowired
	private questionrepository questionrepo;
	@Autowired
	private AnswerRepository answerRepository;

	//	to get all the Questions
	public List<Question> getallQuestions() {
		return questionrepo.findAll();
	}

	//	to get the User specific Questions
	public List<Question> getUserQuestions(int id) {
		return questionrepo.findUserQuestion(id);
	}

	//to add Question
	public void Addquestion(Question question) {
		questionrepo.save(question);
	}

	//to add answer
	public void addanswer(Answer answer) throws Exception {
		Question q = questionrepo.getById(answer.getQid());
		if (q != null) {
			q.addanswer(answer);
			answerRepository.save(answer);
		} else {
			throw new Exception("Question Not found");
		}
	}
	//	to delete the Question
	public ResponseEntity<String> deleteQuestion(int id) {
		Question q = questionrepo.getById(id);
		questionrepo.delete(q);
		return new ResponseEntity<>("success",HttpStatus.OK);
	}

}