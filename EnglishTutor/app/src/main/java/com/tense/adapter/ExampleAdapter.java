package com.tense.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tense.R;
import com.tense.model.exampleModel.ExampleModelModels;

import java.util.ArrayList;
import java.util.Locale;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ViewHolder> {
    Context context;
    ArrayList<ExampleModelModels> exampleList;

    public ExampleAdapter(Context context, ArrayList<ExampleModelModels> exampleList) {
        this.context = context;
        this.exampleList = exampleList;
    }

    public void setExampleList(ArrayList<ExampleModelModels> exampleList) {
        this.exampleList = exampleList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_example_header, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = exampleList.get(position).getName();
        if (name.contains("_")) {
            name = name.replace("_", " ");
        }

        holder.header.setText(toCamelCaseSentence(name));
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setAdapter(new ExampleListAdapter(context, exampleList.get(position).getArray()));
    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView header;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.header);
            recyclerView = itemView.findViewById(R.id.recycler);
        }
    }
    public static String toCamelCaseSentence(String s) {

        if (s != null) {
            String[] words = s.split(" ");

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < words.length; i++) {
                sb.append(toCamelCaseWord(words[i]));
            }

            return sb.toString().trim();
        } else {
            return "";
        }
    }
    public static String toCamelCaseWord(String word) {
        if (word ==null){
            return "";
        }

        switch (word.length()) {
            case 0:
                return "";
            case 1:
                return word.toUpperCase(Locale.getDefault()) + " ";
            default:
                char firstLetter = Character.toUpperCase(word.charAt(0));
                return firstLetter + word.substring(1).toLowerCase(Locale.getDefault()) + " ";
        }
    }
}
