package com.stackroute.chatservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stackroute.chatservice.model.Question;

@Repository
public interface questionrepository extends JpaRepository<Question, Integer> {

	@Query("select m from Question m where m.cid = :id")
	List<Question> findUserQuestion(@Param("id") int id);

	Question getById(int qid);
}