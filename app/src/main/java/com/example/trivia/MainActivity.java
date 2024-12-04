package com.example.trivia;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;

import android.robot.speech.SpeechManager;
import android.robot.speech.SpeechManager.TtsListener;
import android.robot.speech.SpeechService;
import android.robot.motion.RobotMotion;

public class MainActivity extends AppCompatActivity {

    private QuestionManager questionManager;
    private TimerManager timerManager;
    private Handler handler;

    private TextView questionNumberTextView, questionTextView, timerTextView;
    private Button trueButton, falseButton, exitButtonMain;

    private SpeechManager mSpeechManager;
    private RobotMotion mRobotMotion;
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
        handler = new Handler();
        mRobotMotion = new RobotMotion();

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
                exitApp();
            }
        });

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
            trueButton.setEnabled(true);
            falseButton.setEnabled(true);

            questionNumberTextView.setText("Question #" + questionManager.getCurrentQuestionNumber());
            questionTextView.setText(currentQuestion.getQuestion());
            mSpeechManager.startSpeaking(currentQuestion.getQuestion());
            timerManager.startTimer(new Runnable() {
                @Override
                public void run() {
                    validateAnswer("Timeout");
                }
            });
        } else {
            endGame();
        }
    }

    private void validateAnswer(String userAnswer) {
        trueButton.setEnabled(false);
        falseButton.setEnabled(false);

        mSpeechManager.stopSpeaking(-1);
        timerManager.stopTimer();
        questionTextView.setText(questionManager.getNextQuestion().getExplanation());

        boolean isCorrect = questionManager.validateAnswer(userAnswer);
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
        // Launch the EndActivity
        Intent intent = new Intent(MainActivity.this, EndActivity.class);
        intent.putExtra("SCORE_TEXT", questionManager.finalScoreText());
        intent.putExtra("FEEDBACK", questionManager.getFinalFeedback());
        startActivity(intent);

        finish();
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