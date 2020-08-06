package com.example.geoquiz.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.geoquiz.R;
import com.example.geoquiz.model.Question;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String BUNDLE_KEY_CURRENT_INDEX = "currentIndex";

    private TextView mTextViewQuestion;
    private Button mButtonTrue;
    private Button mButtonFalse;
    private Button mButtonNext;
    private Button mButtonPrev;

    private int mCurrentIndex = 0;
    private Question[] mQuestionBank = {
            new Question(R.string.question_australia, false),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, true),
            new Question(R.string.question_americas, false),
            new Question(R.string.question_asia, false)
    };

    /**
     * This method is used to crete ui for activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + mCurrentIndex);

        if (savedInstanceState != null) {
            Log.d(TAG, "savedInstanceState: " + savedInstanceState);

            mCurrentIndex = savedInstanceState.getInt(BUNDLE_KEY_CURRENT_INDEX, 0);
        } else
            Log.d(TAG, "savedInstanceState is NULL!!");

        //this method will create the layout
        //inflate: creating object of xml layout
        setContentView(R.layout.activity_quiz);

        //if we want to change logic we must first find the view objects (it must have "id")
        findViews();
        setListeners();

        updateQuestion();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart: " + mCurrentIndex);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume: " + mCurrentIndex);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause: " + mCurrentIndex);
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop: " + mCurrentIndex);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestory: " + mCurrentIndex);
    }

    /**
     * it will save bundle before it will be destroyed
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: " + mCurrentIndex);

        outState.putInt(BUNDLE_KEY_CURRENT_INDEX, mCurrentIndex);
    }

    private void findViews() {
        mTextViewQuestion = findViewById(R.id.txtview_question_text);
        mButtonTrue = findViewById(R.id.btn_true);
        mButtonFalse = findViewById(R.id.btn_false);
        mButtonNext = findViewById(R.id.btn_next);
        mButtonPrev = findViewById(R.id.btn_prev);
    }

    private void setListeners() {
        mButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        mButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mButtonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex - 1 + mQuestionBank.length) % mQuestionBank.length;
                updateQuestion();
            }
        });
    }

    private void updateQuestion() {
        int questionTextResId = mQuestionBank[mCurrentIndex].getQuestionTextResId();
        mTextViewQuestion.setText(questionTextResId);
    }

    private void checkAnswer(boolean userPressed) {
        if (mQuestionBank[mCurrentIndex].isAnswerTrue() == userPressed) {
            Toast.makeText(QuizActivity.this, R.string.toast_correct, Toast.LENGTH_LONG)
                    .show();
        } else {
            Toast.makeText(QuizActivity.this, R.string.toast_incorrect, Toast.LENGTH_SHORT)
                    .show();
        }
    }
}