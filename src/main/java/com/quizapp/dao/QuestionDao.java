package com.quizapp.dao;

import com.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);


    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() limit :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, Integer numQ);
}
