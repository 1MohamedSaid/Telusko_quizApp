package com.example.telusko.service.impl;

import com.example.telusko.repository.QuestionRepository;
import com.example.telusko.entity.Question;
import com.example.telusko.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public ResponseEntity<List<Question>> getAllQuestions() {
         try {
             return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
         }catch (Exception e){
             e.printStackTrace();
         }
         return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionRepository.save(question);
            return new ResponseEntity<String>("Question Added",HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Couldn't create Question",HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deleteQuestion(Long questionId) {
        try {
            questionRepository.deleteById(questionId);
            return new ResponseEntity<>("Question deleted", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Couldn't delete question", HttpStatus.BAD_REQUEST);
    }
}
