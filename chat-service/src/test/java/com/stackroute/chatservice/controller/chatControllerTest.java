package com.stackroute.chatservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.chatservice.model.Answer;
import com.stackroute.chatservice.model.Question;
import com.stackroute.chatservice.service.Chatservice;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {chatController.class})
@ExtendWith(SpringExtension.class)
class chatControllerTest {
    @Autowired
    private chatController chatController;

    @MockBean
    private Chatservice chatservice;

    /**
     * Method under test: {@link chatController#getallQuestions()}
     */
    @Test
    void testGetallQuestions() throws Exception {
        when(chatservice.getallQuestions()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/getallQuestions");
        MockMvcBuilders.standaloneSetup(chatController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link chatController#getallQuestions()}
     */
    @Test
    void testGetallQuestions2() throws Exception {
        when(chatservice.getallQuestions()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/getallQuestions");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(chatController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link chatController#getUserQuestions(int)}
     */
    @Test
    void testGetUserQuestions() throws Exception {
        when(chatservice.getUserQuestions(anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/UserQuestions/{id}", 1);
        MockMvcBuilders.standaloneSetup(chatController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link chatController#getUserQuestions(int)}
     */
    @Test
    void testGetUserQuestions2() throws Exception {
        when(chatservice.getUserQuestions(anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/UserQuestions/{id}", 1);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(chatController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link chatController#addQuestion(Question)}
     */
    @Test
    void testAddQuestion() throws Exception {
        doNothing().when(chatservice).Addquestion((Question) any());

        Question question = new Question();
        question.setAnswers(new ArrayList<>());
        question.setCid(1);
        question.setCname("Cname");
        question.setId(1);
        question.setQuestion("Question");
        String content = (new ObjectMapper()).writeValueAsString(question);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/AddChat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(chatController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link chatController#addanswer(Answer)}
     */
    @Test
    void testAddanswer() throws Exception {
        doNothing().when(chatservice).addanswer((Answer) any());

        Answer answer = new Answer();
        answer.setAnswer("Answer");
        answer.setCid(1);
        answer.setCname("Cname");
        answer.setId(1);
        answer.setQid(1);
        String content = (new ObjectMapper()).writeValueAsString(answer);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/Addreply")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(chatController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link chatController#deleteQuestion(int)}
     */
    @Test
    void testDeleteQuestion() throws Exception {
        when(chatservice.deleteQuestion(anyInt())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/DeleteQuestion/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(chatController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }
}
