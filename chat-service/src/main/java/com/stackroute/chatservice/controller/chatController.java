package com.stackroute.chatservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stackroute.chatservice.model.Answer;
import com.stackroute.chatservice.model.Question;
import com.stackroute.chatservice.service.Chatservice;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class chatController {

	@Autowired
	private Chatservice chatservice;

	@GetMapping(value = "getallQuestions")
	public List<Question> getallQuestions() {
		return chatservice.getallQuestions();
	}

	@GetMapping(value = "UserQuestions/{id}")
	public List<Question> getUserQuestions(@PathVariable("id") int id) {
		return chatservice.getUserQuestions(id);
	}
	@PostMapping(value="AddChat")
	public void addQuestion(@RequestBody Question question) {
		chatservice.Addquestion(question);
	}
	@PostMapping(value="Addreply")
	public void addanswer(@RequestBody Answer answer) throws Exception {
		chatservice.addanswer(answer);
	}
	@DeleteMapping(value = "DeleteQuestion/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable("id") int id){
		return chatservice.deleteQuestion(id);
	}
}