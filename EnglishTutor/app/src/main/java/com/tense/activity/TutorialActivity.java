package com.tense.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tense.R;
import com.tense.activity.ShowQuestion.ShowQuestionViewPager;
import com.tense.databinding.ActivityTutorialBinding;

public class TutorialActivity extends AppCompatActivity {
    ActivityTutorialBinding binding;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tutorial);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            type = bundle.getString("tense");
        }
        binding.perfect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TutorialActivity.this, TenseActivity.class).putExtra("type", type + "_perfect"));
            }
        });
        binding.contineous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TutorialActivity.this, TenseActivity.class).putExtra("type", type + "_continuous"));
            }
        });
        binding.perfectContineous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TutorialActivity.this, TenseActivity.class).putExtra("type", type + "_perfect_continuous"));
            }
        });
        binding.indefinate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TutorialActivity.this, TenseActivity.class).putExtra("type", type + "_indefinite"));
            }
        });
        binding.tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TutorialActivity.this, ShowQuestionViewPager.class).putExtra("tense", type));
            }
        });
        binding.example.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TutorialActivity.this, ShowExample.class)
                        .putExtra("ruleOrExample","example")
                        .putExtra("tense", type));
            }
        });
    }
}
