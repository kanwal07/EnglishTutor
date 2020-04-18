package com.tense.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tense.R;
import com.tense.adapter.ExampleAdapter;
import com.tense.model.exampleModel.ExampleModelModels;

import java.util.ArrayList;

public class ShowExample extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ExampleAdapter adapter;
    DatabaseReference mExample, mChild;
    ExampleModelModels list;
    ArrayList<ExampleModelModels> arrayList;
    String type;
    String ruleOrExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_example);
        recyclerView = findViewById(R.id.recycler);
        progressBar = findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey("ruleOrExample")) {
                ruleOrExample = bundle.getString("ruleOrExample");
            }
            if (bundle.containsKey("type")) {
                type = bundle.getString("type");
                fetchQuestionFromFirebase(type);
            } else if (bundle.containsKey("tense")) {
                type = bundle.getString("tense");
                fetchAllExample(type);
            }
            else {
                fetchAllExample("");
            }
        }

    }

    void fetchAllExample(final String filter) {
        mChild = FirebaseDatabase.getInstance().getReference().child(ruleOrExample);
        mChild.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    if (key.contains(filter)) {
                        fetchQuestionFromFirebase(key);
                        Log.e("Get Data", key);
                    }
                }
            }

            @Override

            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void fetchQuestionFromFirebase(String key) {
        mExample = FirebaseDatabase.getInstance().getReference().child(ruleOrExample).child(key);
        mExample.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getValue() != null) {

                    list = dataSnapshot.getValue(ExampleModelModels.class);
                    arrayList.add(list);
                    if (adapter == null) {
                        adapter = new ExampleAdapter(ShowExample.this, arrayList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        adapter.setExampleList(arrayList);
                    }
                    progressBar.setVisibility(View.GONE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        mExample.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists() && dataSnapshot.getValue() != null) {
//
//                    list = dataSnapshot.getValue(ExampleModelModels.class);
//                    arrayList.add(list);
//                    if (adapter == null) {
//                        adapter = new ExampleAdapter(ShowExample.this, arrayList);
//                        recyclerView.setAdapter(adapter);
//                    } else {
//                        adapter.setExampleList(arrayList);
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }
}
