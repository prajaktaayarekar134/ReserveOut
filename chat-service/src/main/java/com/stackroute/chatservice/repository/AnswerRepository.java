package com.stackroute.chatservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.chatservice.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

}