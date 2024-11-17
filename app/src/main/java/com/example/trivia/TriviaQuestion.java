package com.example.trivia;

public class TriviaQuestion {
    private String question;
    private String correctAnswer;
    private String explanation;

    // Constructor to initialize the trivia question
    public TriviaQuestion(String question, String correctAnswer, String explanation) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
    }

    // Getter methods
    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getExplanation() {
        return explanation;
    }

}
