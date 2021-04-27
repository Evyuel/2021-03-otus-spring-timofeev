package ru.dtimofeev.spring.domain;


public final class QuestionCSV {
    private final String question;
    private final String answer;
    private final String choices;
    private boolean isRightAnswer;

    public QuestionCSV(String question, String answer, String choices) {
        this.question = question;
        this.answer = answer;
        this.choices = choices;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getChoices() {
        return choices;
    }

    public String toString() {
        return "Q: " + question + ", A: " + answer + ", Ch: " + choices;
    }

    public boolean isRightAnswer() {
        return isRightAnswer;
    }

    public void setRightAnswer(boolean rightAnswer) {
        isRightAnswer = rightAnswer;
    }
}
