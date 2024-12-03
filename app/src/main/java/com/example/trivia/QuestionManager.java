package com.example.trivia;
import android.content.Context;

public class QuestionManager {
    private TriviaQuestions questions;
    private int questionIndex = 0;
    private int correctAnswers = 0;

    public QuestionManager(TriviaQuestions questions, Context context) {
        this.questions = questions;
    }

    public TriviaQuestion getNextQuestion() {
        if (questionIndex < questions.getSize()) {
            return questions.getQuestion(questionIndex);
        } else {
            return null;
        }
    }

    public boolean validateAnswer (String userAnswer) {
        TriviaQuestion currentQuestion = questions.getQuestion(questionIndex);
        questionIndex++;

        if (userAnswer.equalsIgnoreCase(currentQuestion.getCorrectAnswer())) {
            correctAnswers++;
            return true;
        } else {
            return false;
        }
    }

    public String getFinalFeedback() {
        if (correctAnswers >= 9) return "Excellent! You nailed it!";
        if (correctAnswers >= 7) return "Great job! You're almost there!";
        if (correctAnswers >= 5) return "Good effort! High five!";
        return "Better luck next time. Keep practicing!";
    }

    public int getCurrentQuestionNumber() {
        return questionIndex + 1;
    }

    public String finalScoreText() {
        return "Correct answers: " + correctAnswers + " / " + questions.getSize();
    }
}
