package com.tense.activity.ShowQuestion;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tense.model.questionModel.QuestionModelQuestions;
import com.tense.R;
import com.tense.activity.ShowQuestion.ui.main.SectionsPagerAdapter;
import com.tense.model.questionModel.QuestionModelQuestionsQuestion;

import java.util.ArrayList;

public class ShowQuestionViewPager extends FragmentActivity implements OnSubmit {
    DatabaseReference mQuestion;
    DatabaseReference mChild;
    QuestionModelQuestions questionModel, genericModel;
    ViewPager2 viewPager;
    ProgressBar progressBar;
    ArrayList<String> allType;
    SectionsPagerAdapter sectionsPagerAdapter;
    String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_question_view_pager);

        viewPager = findViewById(R.id.view_pager);
        progressBar = findViewById(R.id.progressBar);
        allType = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey("type")) {
                type = bundle.getString("type");
                fetchQuestionFromFirebase(type, false);
            } else if (bundle.containsKey("tense")) {
                type = bundle.getString("tense");
                fetchAllQuestion(type);
            }
        } else {
            fetchAllQuestion("");
        }

    }

    void fetchAllQuestion(final String isFilter) {
        mChild = FirebaseDatabase.getInstance().getReference().child("question");
        mChild.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    if (isFilter.equals("")){
                        fetchQuestionFromFirebase(key, true);
                    }else{
                        if (key.contains(isFilter)){
                            fetchQuestionFromFirebase(key, true);
                        }
                    }

//                    Log.e("Get Data", key);
                }
            }

            @Override

            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    void fetchQuestionFromFirebase(String type, boolean isAppend) {
        mQuestion = FirebaseDatabase.getInstance().getReference().child("question").child(type);
        mQuestion.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getValue() != null) {
                    if (questionModel == null) {
                        questionModel = dataSnapshot.getValue(QuestionModelQuestions.class);
                    } else {
                        ArrayList<QuestionModelQuestionsQuestion> list = new ArrayList<>(questionModel.getQuestion());
                        genericModel = dataSnapshot.getValue(QuestionModelQuestions.class);
                        list.addAll(genericModel.getQuestion());
                        int prevQues = Integer.valueOf(questionModel.getNum_of_ques());
                        int nowQues = Integer.valueOf(genericModel.getNum_of_ques());
                        questionModel.setQuestion(list);
                        questionModel.setNum_of_ques(prevQues + nowQues + "");

                    }
                    if (sectionsPagerAdapter == null) {
                        sectionsPagerAdapter = new SectionsPagerAdapter(ShowQuestionViewPager.this, ShowQuestionViewPager.this, questionModel);
                        viewPager.setAdapter(sectionsPagerAdapter);
                    } else {
                        sectionsPagerAdapter.setQuestions(questionModel);
                    }
//                    viewPager.setPageTransformer(new ZoomOutPageTransformer());
                    viewPager.setUserInputEnabled(false);
                    progressBar.setVisibility(View.GONE);
//                    TabLayout tabs = findViewById(R.id.tabs);
////                    tabs.setupWithViewPager(viewPager);
//                    new TabLayoutMediator(tabs, viewPager,
//                            new TabLayoutMediator.TabConfigurationStrategy() {
//                                @Override
//                                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                                    tab.setText("OBJECT " + (position + 1));
//                                }
//                            }
//                    ).attach();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onNextPressed(int position, int score) {
        if (questionModel.getQuestion().size() > position + 1) {
            questionModel.setScore(score);
            sectionsPagerAdapter.setQuestions(questionModel);
            viewPager.setCurrentItem(position + 1, true);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Score")
                    .setMessage("Your score is " + score + ". Do you want to retry?")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();

                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.

//                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            score = 0;
        }
    }
}