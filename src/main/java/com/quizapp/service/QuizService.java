package com.quizapp.service;

import com.quizapp.dao.QuestionDao;
import com.quizapp.dao.QuizDao;
import com.quizapp.model.Question;
import com.quizapp.model.QuestionWrapper;
import com.quizapp.model.Quiz;
import com.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer quizId){
            Optional<Quiz> quiz = quizDao.findById(quizId);
            List<Question> questionFromDB = quiz.get().getQuestions();

            List<QuestionWrapper> questionsForUser = new ArrayList<>();

            for(Question qus: questionFromDB){
                QuestionWrapper qw = new QuestionWrapper(qus.getId(), qus.getQuestionTitle(), qus.getOption1(), qus.getOption2(), qus.getOption3(), qus.getOption4());
                questionsForUser.add(qw);
            }
            return new ResponseEntity<List<QuestionWrapper>>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer quizId, List<Response> responses) {
        Quiz quiz = quizDao.findById(quizId).get();
        List<Question> questions = quiz.getQuestions();

        int right = 0;
        int i = 0;
        for(Response response: responses){
                if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                        right++;
                }
                i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
