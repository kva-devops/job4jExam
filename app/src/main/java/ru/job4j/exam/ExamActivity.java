package ru.job4j.exam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.job4j.exam.store.QuestionStore;

public class ExamActivity extends AppCompatActivity {

    private static final String TAG = "ExamActivity";
    public static final String HINT_FOR = "hint_for";
    public static final String RESULT_MAP = "result_map";
    private static int counterTurn = 0;
    private final QuestionStore store = QuestionStore.getInstance();
    private int position = 0;
    private String[] result = new String[store.size()];


    private void fillForm() {
        findViewById(R.id.previous).setEnabled(position != 0);
        findViewById(R.id.next).setEnabled(false);
        final TextView text = findViewById(R.id.question);
        Question question = this.store.get(this.position);
        text.setText(question.getText());
        RadioGroup variants = findViewById(R.id.variants);
        variants.setOnCheckedChangeListener(this::radioCheck);
        for (int index = 0; index != variants.getChildCount(); index++) {
            RadioButton button = (RadioButton) variants.getChildAt(index);
            Option option = question.getOptions().get(index);
            button.setId(option.getId());
            button.setText(option.getText());
        }
    }

    private void showAnswer() {
        RadioGroup variants = findViewById(R.id.variants);
        int id = variants.getCheckedRadioButtonId();
        Question question = this.store.get(this.position);
        if (id == this.store.get(position).getAnswer()) {
            result[position] = (position + 1) + " question is correct";
        } else {
            result[position] = (position + 1) + " question is incorrect";
        }
        Toast.makeText(
                this, "Your answer is " + id + ", correct is " + question.getAnswer(),
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.fillForm();
        Button next = findViewById(R.id.next);
        next.setOnClickListener(this::nextBtn);
        Button previous = findViewById(R.id.previous);
        previous.setOnClickListener(this::prevBtn);
        Button hint = findViewById(R.id.hint);
        hint.setOnClickListener(this::hintBtn);
        Log.d(TAG, "Turn Counter: " + String.valueOf(counterTurn));
        Log.d(TAG, "onCreate");
    }

    private void hintBtn(View view) {
        Intent intent = new Intent(ExamActivity.this, HintActivity.class);
        intent.putExtra(HINT_FOR, position);
        startActivity(intent);
    }

    private void prevBtn(View view) {
        position--;
        fillForm();
    }

    private void nextBtn(View view) {
        RadioGroup variants = findViewById(R.id.variants);
        int id = variants.getCheckedRadioButtonId();
        if (id != -1 && position < store.size() - 1) {
            showAnswer();
            position++;
            fillForm();
        } else {
            showAnswer();
            fillForm();
            Intent intent = new Intent(ExamActivity.this, ResultActivity.class);
            intent.putExtra(RESULT_MAP, result);
            startActivity(intent);
        }
    }

    private void radioCheck(RadioGroup radioGroup, int i) {
        if (position != store.size()) {
            findViewById(R.id.next).setEnabled(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        counterTurn++;
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}