package com.tense.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tense.R;
import com.tense.databinding.ActivityChooseTenseBinding;

public class ChooseTense extends AppCompatActivity {
    ActivityChooseTenseBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_choose_tense);
        binding.present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseTense.this,TutorialActivity.class).putExtra("tense","present"));
            }
        });
        binding.past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseTense.this,TutorialActivity.class).putExtra("tense","past"));
            }
        });
        binding.future.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseTense.this,TutorialActivity.class).putExtra("tense","future"));
            }
        });
    }
}
