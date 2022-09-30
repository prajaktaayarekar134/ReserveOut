package com.stackroute.chatservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.chatservice.model.Answer;
import com.stackroute.chatservice.model.Question;
import com.stackroute.chatservice.repository.AnswerRepository;
import com.stackroute.chatservice.repository.questionrepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {Chatservice.class})
@ExtendWith(SpringExtension.class)
class ChatserviceTest {
    @MockBean
    private AnswerRepository answerRepository;

    @Autowired
    private Chatservice chatservice;

    @MockBean
    private questionrepository questionrepository;

    /**
     * Method under test: {@link Chatservice#getallQuestions()}
     */
    @Test
    void testGetallQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        when(questionrepository.findAll()).thenReturn(questionList);
        List<Question> actualGetallQuestionsResult = chatservice.getallQuestions();
        assertSame(questionList, actualGetallQuestionsResult);
        assertTrue(actualGetallQuestionsResult.isEmpty());
        verify(questionrepository).findAll();
    }

    /**
     * Method under test: {@link Chatservice#getUserQuestions(int)}
     */
    @Test
    void testGetUserQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        when(questionrepository.findUserQuestion(anyInt())).thenReturn(questionList);
        List<Question> actualUserQuestions = chatservice.getUserQuestions(1);
        assertSame(questionList, actualUserQuestions);
        assertTrue(actualUserQuestions.isEmpty());
        verify(questionrepository).findUserQuestion(anyInt());
    }

    /**
     * Method under test: {@link Chatservice#Addquestion(Question)}
     */
    @Test
    void testAddquestion() {
        Question question = new Question();
        ArrayList<Answer> answerList = new ArrayList<>();
        question.setAnswers(answerList);
        question.setCid(1);
        question.setCname("Cname");
        question.setId(1);
        question.setQuestion("Question");
        when(questionrepository.save((Question) any())).thenReturn(question);

        Question question1 = new Question();
        question1.setAnswers(new ArrayList<>());
        question1.setCid(1);
        question1.setCname("Cname");
        question1.setId(1);
        question1.setQuestion("Question");
        chatservice.Addquestion(question1);
        verify(questionrepository).save((Question) any());
        assertEquals(answerList, question1.getAnswers());
        assertEquals("Question", question1.getQuestion());
        assertEquals(1, question1.getId());
        assertEquals("Cname", question1.getCname());
        assertEquals(1, question1.getCid());
    }

    /**
     * Method under test: {@link Chatservice#addanswer(Answer)}
     */
    @Test
    void testAddanswer() throws Exception {
        Answer answer = new Answer();
        answer.setAnswer("Answer");
        answer.setCid(1);
        answer.setCname("Cname");
        answer.setId(1);
        answer.setQid(1);
        when(answerRepository.save((Answer) any())).thenReturn(answer);

        Question question = new Question();
        question.setAnswers(new ArrayList<>());
        question.setCid(1);
        question.setCname("Cname");
        question.setId(1);
        question.setQuestion("Question");
        when(questionrepository.getById(anyInt())).thenReturn(question);

        Answer answer1 = new Answer();
        answer1.setAnswer("Answer");
        answer1.setCid(1);
        answer1.setCname("Cname");
        answer1.setId(1);
        answer1.setQid(1);
        chatservice.addanswer(answer1);
        verify(answerRepository).save((Answer) any());
        verify(questionrepository).getById(anyInt());
    }

    /**
     * Method under test: {@link Chatservice#deleteQuestion(int)}
     */
    @Test
    void testDeleteQuestion() {
        Question question = new Question();
        question.setAnswers(new ArrayList<>());
        question.setCid(1);
        question.setCname("Cname");
        question.setId(1);
        question.setQuestion("Question");
        when(questionrepository.getById(anyInt())).thenReturn(question);
        doNothing().when(questionrepository).delete((Question) any());
        ResponseEntity<String> actualDeleteQuestionResult = chatservice.deleteQuestion(1);
        assertEquals("success", actualDeleteQuestionResult.getBody());
        assertEquals(HttpStatus.OK, actualDeleteQuestionResult.getStatusCode());
        assertTrue(actualDeleteQuestionResult.getHeaders().isEmpty());
        verify(questionrepository).getById(anyInt());
        verify(questionrepository).delete((Question) any());
    }
}