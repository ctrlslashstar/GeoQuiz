package com.example.geoquiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private TextView mTextViewQuestion;
    private Button mButtonTrue;
    private Button mButtonFalse;
    private Button mButtonNext;

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
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //this method will create the layout
        //inflate: creating object of xml layout
        setContentView(R.layout.activity_quiz);

        //if we want to change logic we must first find the view objects (it must have "id")
        findViews();
        setListeners();

        updateQuestion();
    }

    private void findViews() {
        mTextViewQuestion = findViewById(R.id.txtview_question_text);
        mButtonTrue = findViewById(R.id.btn_true);
        mButtonFalse = findViewById(R.id.btn_false);
        mButtonNext = findViewById(R.id.btn_next);
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