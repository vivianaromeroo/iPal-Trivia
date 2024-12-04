package com.example.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.robot.motion.RobotMotion;
import android.robot.speech.SpeechManager;
import android.robot.speech.SpeechService;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

public class EndActivity extends AppCompatActivity {

    private SpeechManager mSpeechManager;
    private RobotMotion mRobotMotion = new RobotMotion();
    private SpeechManager.TtsListener mTtsListener = new SpeechManager.TtsListener() {
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
        setContentView(R.layout.activity_end);

        mSpeechManager = (SpeechManager) getSystemService(SpeechService.SERVICE_NAME);
        mSpeechManager.setTtsListener(mTtsListener);

        // Retrieve data passed from MainActivity
        Intent intent = getIntent();
        String scoreText = intent.getStringExtra("SCORE_TEXT");
        String feedback = intent.getStringExtra("FEEDBACK");

        // Set the views with the data
        TextView scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        scoreTextView.setText(scoreText);

        TextView feedbackTextView = (TextView) findViewById(R.id.feedbackTextView);
        feedbackTextView.setText(feedback);

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

        // Restart game button
        Button restartButton = (Button) findViewById(R.id.restartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent restartIntent = new Intent(EndActivity.this, MainActivity.class);
                restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(restartIntent);
                finish();
            }
        });

        Button exitButtonEnd = (Button) findViewById(R.id.exitButtonEnd);
        exitButtonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }
}
