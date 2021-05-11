package ru.job4j.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        String[] buff = intent.getStringArrayExtra("result_map");
        StringBuilder rsl = new StringBuilder();
        for (String s : buff) {
            rsl.append(s);
            rsl.append("\n");
        }
        TextView textView = findViewById(R.id.result_text_view);
        textView.setText(rsl.toString());
    }
}