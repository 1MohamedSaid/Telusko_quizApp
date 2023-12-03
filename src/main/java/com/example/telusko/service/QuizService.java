package com.example.telusko.service;

import com.example.telusko.entity.QuestionDto;
import com.example.telusko.entity.Quiz;
import com.example.telusko.entity.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {

    ResponseEntity<String> createQuiz(String category, int numQ, String title);

    void save(Quiz quiz);

    ResponseEntity<String> calculateResult(Long questionId, List<Response> responses);

    ResponseEntity<List<QuestionDto>> getQuizById(Long id);
}
