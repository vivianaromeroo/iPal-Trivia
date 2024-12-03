package com.example.trivia;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.os.CountDownTimer;
import android.content.Context;

import android.robot.speech.SpeechManager;
import android.robot.speech.SpeechManager.TtsListener;
import android.robot.speech.SpeechService;
import android.robot.motion.RobotMotion;

public class MainActivity extends AppCompatActivity {

    private QuestionManager questionManager;
    private TimerManager timerManager;
    private Handler handler = new Handler();

    private TextView questionNumberTextView, questionTextView, timerTextView;
    private Button trueButton, falseButton, exitButtonMain;

    private SpeechManager mSpeechManager;
    private RobotMotion mRobotMotion = new RobotMotion();
    private TtsListener mTtsListener = new TtsListener() {
        @Override
        public void onBegin(int requestId) {}

        @Override
        public void onEnd(int requestId) {}

        @Override
        public void onError(int error) {}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        questionNumberTextView = (TextView) findViewById(R.id.questionNumberTextView);
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        trueButton = (Button) findViewById(R.id.trueButton);
        falseButton = (Button) findViewById(R.id.falseButton);
        exitButtonMain = (Button) findViewById(R.id.exitButtonMain);

        // Initialize Helpers
        questionManager = new QuestionManager(new TriviaQuestions(), this);
        timerManager = new TimerManager(timerTextView);

        initSpeechManager();

        // Set Button Listeners
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
                // Exit the app when the button is clicked
                finish();
                System.exit(0);
            }
        });

        // Start the first question
        displayNextQuestion();
    }

    private void initSpeechManager() {
        mSpeechManager = (SpeechManager) getSystemService(SpeechService.SERVICE_NAME);
        if (mSpeechManager != null) {
            mSpeechManager.setTtsListener(mTtsListener);
        }
    }

    private void displayNextQuestion() {
        TriviaQuestion currentQuestion = questionManager.getNextQuestion();
        if (currentQuestion != null) {
            questionNumberTextView.setText("Question #" + questionManager.getCurrentQuestionNumber());
            questionTextView.setText(currentQuestion.getQuestion());
            mSpeechManager.startSpeaking(currentQuestion.getQuestion());
            timerManager.startTimer(new Runnable() {
                @Override
                public void run() {
                    questionManager.validateAnswer("Timeout");
                    mRobotMotion.shakeHead();
                    displayNextQuestion();
                }
            });
        } else {
            endGame();
        }
    }

    private void validateAnswer(String userAnswer) {
        mSpeechManager.stopSpeaking(-1);
        questionTextView.setText(questionManager.getNextQuestion().getExplanation());

        boolean isCorrect = questionManager.validateAnswer(userAnswer);
        timerManager.stopTimer();
        String feedback;

        if (isCorrect) {
            mRobotMotion.nodHead();

            feedback = "Good job!";
            timerTextView.setText(feedback);
            mSpeechManager.startSpeaking(feedback);
        } else {
            mRobotMotion.shakeHead();

            feedback = "Better luck next time.";
            timerTextView.setText(feedback);
            mSpeechManager.startSpeaking(feedback);
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRobotMotion.stop();

                displayNextQuestion();
            }
        }, 5000);
    }

    private void endGame() {
        String feedback = questionManager.getFinalFeedback();
        questionTextView.setText(feedback);
        mSpeechManager.startSpeaking(feedback);

        if (feedback.contains("Excellent")) {
            mRobotMotion.doAction(RobotMotion.Action.CHEER);
        }
        else if (feedback.contains("Great")) {
            mRobotMotion.doAction(RobotMotion.Action.CLAP);
        }
        else if (feedback.contains("Good")) {
            mRobotMotion.doAction(RobotMotion.Action.HIGHFIVE);
        }
        else {
            mRobotMotion.doAction(RobotMotion.Action.NO);
        }

        questionNumberTextView.setText("Game Over!");
        questionTextView.setText(questionManager.finalScoreText());
    }

    private void exitApp() {
        mSpeechManager.stopSpeaking(-1);
        finish();
        System.exit(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSpeechManager.stopSpeaking(-1);
    }
}