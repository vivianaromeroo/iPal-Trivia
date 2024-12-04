package com.example.trivia;
import android.widget.TextView;
import android.os.CountDownTimer;

public class TimerManager {
    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private QuestionManager qManager;

    public TimerManager(TextView timerTextView) {
        this.timerTextView = timerTextView;
    }

    public void startTimer(final Runnable onTimeout) {
        countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Time left: " + millisUntilFinished / 1100 + " seconds");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("Time's up!");
                onTimeout.run();
            }
        }.start();
    }

    public void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}

