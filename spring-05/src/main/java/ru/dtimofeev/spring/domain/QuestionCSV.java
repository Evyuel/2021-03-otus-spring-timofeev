package ru.dtimofeev.spring.domain;


public final class QuestionCSV implements Question {
    private final String question;
    private final String answer;
    private final String choices;
    private boolean isRightAnswer;

    public QuestionCSV(String question, String answer, String choices) {
        this.question = question;
        this.answer = answer;
        this.choices = choices;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    @Override
    public String getChoices() {
        return choices;
    }

    @Override
    public String toString() {
        return "Q: " + question + ", A: " + answer + ", Ch: " + choices;
    }

    @Override
    public boolean isRightAnswer() {
        return isRightAnswer;
    }

    @Override
    public void setRightAnswer(boolean rightAnswer) {
        isRightAnswer = rightAnswer;
    }
}
