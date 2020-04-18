package com.tense.model.questionModel;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;

import java.util.ArrayList;

public class QuestionModelQuestionsQuestion extends BaseObservable implements Parcelable {
    public static final Creator<QuestionModelQuestionsQuestion> CREATOR = new Creator<QuestionModelQuestionsQuestion>() {
        @Override
        public QuestionModelQuestionsQuestion createFromParcel(Parcel source) {
            QuestionModelQuestionsQuestion var = new QuestionModelQuestionsQuestion();
            var.correct = source.readString();
            var.values = source.readString();
            var.answers = source.createStringArrayList();
            return var;
        }

        @Override
        public QuestionModelQuestionsQuestion[] newArray(int size) {
            return new QuestionModelQuestionsQuestion[size];
        }
    };
    private String correct;
    private String values;
    private ArrayList<String> answers;

    public String getCorrect() {
        return this.correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getValues() {
        return this.values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public ArrayList<String> getAnswers() {
        return this.answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.correct);
        dest.writeString(this.values);
        dest.writeStringList(this.answers);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
