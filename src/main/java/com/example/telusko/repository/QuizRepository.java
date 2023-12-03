package com.example.telusko.repository;

import com.example.telusko.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;;

public interface QuizRepository extends JpaRepository<Quiz,Long> {

}
