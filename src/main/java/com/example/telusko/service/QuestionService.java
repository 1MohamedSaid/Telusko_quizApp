package com.example.telusko.service;

import com.example.telusko.entity.Question;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface QuestionService {
    ResponseEntity<List<Question>> getAllQuestions();
    ResponseEntity<List<Question>> getAllQuestionsByCategory(String category);
    ResponseEntity<String> addQuestion(Question question);
    ResponseEntity<String> deleteQuestion(Long questionId);
}
