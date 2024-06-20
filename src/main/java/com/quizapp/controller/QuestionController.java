package com.quizapp.controller;

import com.quizapp.model.Question;
import com.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
     QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{categoryType}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable("categoryType") String category){
        return questionService.getAllQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        questionService.addQuestion(question);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}

