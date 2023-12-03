package com.example.telusko.service.impl;

import com.example.telusko.entity.Question;
import com.example.telusko.entity.QuestionDto;
import com.example.telusko.entity.Quiz;
import com.example.telusko.entity.Response;
import com.example.telusko.repository.QuestionRepository;
import com.example.telusko.repository.QuizRepository;
import com.example.telusko.service.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    ModelMapper modelMapper;

    public ResponseEntity<String> createQuiz(String category,int numQ,String title) {
        Quiz quiz = new Quiz();
        List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, numQ);
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @Override
    public void save(Quiz quiz) {
        quizRepository.save(quiz);
    }


    @Override
    public ResponseEntity<String> calculateResult(Long quizId, List<Response> responses) {
        int i = 0;
        int j = 0;
        var quiz = quizRepository.findById(quizId);
        var questions = quiz.get().getQuestions();
        for(Response r : responses) {
            if (r.getResponse().equalsIgnoreCase(questions.get(j).getRightAnswer())) {
                i++;
            }
            j++;
        }
        return new ResponseEntity<>("your result: "+i+"/"+quiz.get().getQuestions().size(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<QuestionDto>> getQuizById(Long id) {
        var x = quizRepository.findById(id);
        List<Question>questions = x.get().getQuestions();
        List<QuestionDto>questionsDto = questions
                .stream()
                .map(e-> modelMapper.map(e,QuestionDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(questionsDto,HttpStatus.OK);
    }
}
