package com.example.trivia;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.os.CountDownTimer;

public class MainActivity extends AppCompatActivity {

    // Text Views
    private TextView questionNumberTextView;
    private TextView questionTextView;
    private TextView timerTextView;

    //Buttons
    private Button trueButton;
    private Button falseButton;
    private Button exitButtonMain;

    // Variables to keep track of question # + correct answers
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;

    // List of trivia questions
    private TriviaQuestions triviaQuestions = new TriviaQuestions();

    // For countdown + delay between questions
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 10000;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the views
        questionNumberTextView = (TextView) findViewById(R.id.questionNumberTextView);
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        trueButton = (Button) findViewById(R.id.trueButton);
        falseButton = (Button) findViewById(R.id.falseButton);
        exitButtonMain = (Button) findViewById(R.id.exitButtonMain);

        // Display the first question
        displayNextQuestion(); // GAME STARTS HERE

        // Set button listeners for user input
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAnswer("True");
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAnswer("False");
            }
        });

        exitButtonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Exit the app when the button is clicked
            }
        });
    }

    // Display the next question from the list
    private void displayNextQuestion() {

        if (currentQuestionIndex < triviaQuestions.getSize()) {
            // Enable true/false buttons
            trueButton.setEnabled(true);
            falseButton.setEnabled(true);

            String text = "Question #" + (currentQuestionIndex + 1);
            questionNumberTextView.setText(text);

            TriviaQuestion currentQuestion = triviaQuestions.getQuestion(currentQuestionIndex);
            questionTextView.setText(currentQuestion.getQuestion());

            timerTextView.setText("Time left: 10 seconds");
            timeLeftInMillis = 10000;
            startTimer();

        } else {
            endGame();
        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // Update the timer display each second
                timeLeftInMillis = millisUntilFinished;
                int secondsLeft = (int) (timeLeftInMillis / 1000);
                timerTextView.setText("Time left: " + secondsLeft + " seconds");
            }

            @Override
            public void onFinish() {
                // When the time finishes, show a message and move to the next question
                timerTextView.setText("Time's up!");
                validateAnswer("Timeout");
            }
        }.start();
    }

    // Validate the user's answer
    private void validateAnswer(String userAnswer) {
        if (countDownTimer != null) {
            countDownTimer.cancel(); // Stop the timer when the answer is selected
        }

        // If answer is correct, add to score
        TriviaQuestion currentQuestion = triviaQuestions.getQuestion(currentQuestionIndex);
        if (userAnswer.equalsIgnoreCase(currentQuestion.getCorrectAnswer())) {
            correctAnswers++;

            timerTextView.setText("Good job!");
        } else {
            timerTextView.setText("Better luck next time.");
        }

        // Show answer + explanation
        String explanation = currentQuestion.getExplanation();
        questionTextView.setText("Answer: " + explanation);

        // Disable buttons until next question
        trueButton.setEnabled(false);
        falseButton.setEnabled(false);

        // Move to the next question after 5 seconds
        currentQuestionIndex++;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                displayNextQuestion();
            }
        }, 5000);
    }

    private void endGame() {
        // Show the results
        questionNumberTextView.setText("Game Over!");
        String results = "Correct answers: " + correctAnswers + " / " + triviaQuestions.getSize();
        questionTextView.setText(results);

        timerTextView.setText("");
    }
}
