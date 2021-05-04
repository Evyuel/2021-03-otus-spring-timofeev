package ru.dtimofeev.spring.service.component;

import ru.dtimofeev.spring.domain.Question;

import java.util.List;

public interface QuestionsListCreator {

    List<Question> getAllQuestions();
}
