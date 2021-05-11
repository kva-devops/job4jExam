package ru.job4j.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import ru.job4j.exam.store.QuestionStore;

public class HintActivity extends AppCompatActivity {

    //private final Map<Integer, String> answer = new HashMap<Integer, String>();
    private final QuestionStore store = QuestionStore.getInstance();
    public static int question;

//    public HintActivity() {
//        this.answer.put(0, "Hint 1");
//        this.answer.put(1, "Hint 2");
//        this.answer.put(2, "Hint 3");
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hint_activity);
        Button back = findViewById(R.id.back);
        back.setOnClickListener(this::backBtn);
        TextView text = findViewById(R.id.hint);
        question = getIntent().getIntExtra(ExamActivity.HINT_FOR, 0);
        int answerRsl = this.store.get(question).getAnswer();
        text.setText(this.store.get(question).getText());
        Button showAnswer = findViewById(R.id.show_answer);
        showAnswer.setOnClickListener(this::showAnswerBtn);
    }

    private void backBtn(View view) {
        onBackPressed();
    }

    private void showAnswerBtn(View view) {
        TextView textAnswer =  findViewById(R.id.hint);
        textAnswer.setText(String.valueOf(this.store.get(question).getAnswer()));
    }
}