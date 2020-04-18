package com.tense.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tense.R;
import com.tense.activity.ShowQuestion.ShowQuestionViewPager;
import com.tense.databinding.ActivityTenseBinding;

public class TenseActivity extends AppCompatActivity {
    ActivityTenseBinding binding;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tense);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            type = bundle.getString("type");
        }
        binding.exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TenseActivity.this, ShowQuestionViewPager.class)
                        .putExtra("type", type));
            }
        });
        binding.example.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TenseActivity.this, ShowExample.class)
                        .putExtra("ruleOrExample","example")
                        .putExtra("type", type));
            }
        });
        binding.rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TenseActivity.this, ShowExample.class)
                        .putExtra("ruleOrExample","rule")
                        .putExtra("type", type));
            }
        });
    }
}
