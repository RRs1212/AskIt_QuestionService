package com.AskIt.QuestionService.Repositories;


import com.AskIt.EntityService.Models.Question;
import com.AskIt.EntityService.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    Optional<List<Question>>findByUser(User user);


}
