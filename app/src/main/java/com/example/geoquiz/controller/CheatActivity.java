package com.example.geoquiz.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.geoquiz.R;

public class CheatActivity extends AppCompatActivity {

    private TextView mTextViewAnswer;
    private Button mButtonShowAnswer;

    private boolean mIsAnswerTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mIsAnswerTrue = getIntent().getBooleanExtra(QuizActivity.EXTRA_QUESTION_ANSWER, false);

        findViews();
        setListeners();
    }

    private void findViews() {
        mTextViewAnswer = findViewById(R.id.txtview_answer);
        mButtonShowAnswer = findViewById(R.id.btn_show_answer);
    }

    private void setListeners() {
        mButtonShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAnswerTrue)
                    mTextViewAnswer.setText(R.string.button_true);
                else
                    mTextViewAnswer.setText(R.string.button_false);
            }
        });
    }
}