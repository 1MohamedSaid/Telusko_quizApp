package com.example.telusko.repository;

import com.example.telusko.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findByCategory(String category);

    @Query(value="SELECT * FROM Question q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ",nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(@Param("category") String category, @Param("numQ") int numQ);
}
