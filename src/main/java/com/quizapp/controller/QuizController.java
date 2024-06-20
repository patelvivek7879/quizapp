package com.quizapp.controller;

import com.quizapp.model.Question;
import com.quizapp.model.QuestionWrapper;
import com.quizapp.model.Response;
import com.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam Integer numQ, @RequestParam String title ){
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("get/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> get(@PathVariable Integer quizId){
        return quizService.getQuizQuestions(quizId);
    }

    @PostMapping("submit/{quizId}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer quizId, @RequestBody List<Response> responses){
        return quizService.calculateResult(quizId, responses);
    }
}
