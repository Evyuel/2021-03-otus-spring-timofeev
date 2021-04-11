package ru.otus.spring.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Класс QuestionCSV")
class QuestionCSVTest {

    public QuestionCSV getQuestion(){
        return new QuestionCSV(new String[]{"This is a question", "This is an correct answer", "Choice1", "Choice2", "Choice3"});
    }

    @DisplayName("Должен корректно назначать вопрос")
    @Test
    void shouldCreateWithCorrectQuestion() {
        QuestionCSV q = getQuestion();
        q.formFields();
        assertEquals("This is a question",q.getQuestion());
    }
    @DisplayName("Должен корректно назначать ответ")
    @Test
    void shouldCreateWithCorrectAnswer() {
        QuestionCSV q = getQuestion();
        q.formFields();
        assertEquals("This is an correct answer",q.getAnswer());

    }
    @DisplayName("Должен корректно назначать выбор вариантов")
    @Test
    void shouldCreateWithCorrectChoices() {
        QuestionCSV q = getQuestion();
        q.formFields();
        assertEquals("Choice1,Choice2,Choice3",q.getChoices());

    }
}