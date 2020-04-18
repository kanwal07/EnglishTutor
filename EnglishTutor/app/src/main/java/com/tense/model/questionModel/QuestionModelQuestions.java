package com.tense.model.questionModel;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;

import java.util.ArrayList;

public class QuestionModelQuestions extends BaseObservable implements Parcelable {
    public static final Creator<QuestionModelQuestions> CREATOR = new Creator<QuestionModelQuestions>() {
        @Override
        public QuestionModelQuestions createFromParcel(Parcel source) {
            QuestionModelQuestions var = new QuestionModelQuestions();
            var.question = source.createTypedArrayList(QuestionModelQuestionsQuestion.CREATOR);
            var.num_of_ques = source.readString();
            var.tense = source.readString();
            var.score = source.readInt();
            return var;
        }

        @Override
        public QuestionModelQuestions[] newArray(int size) {
            return new QuestionModelQuestions[size];
        }
    };
    private ArrayList<QuestionModelQuestionsQuestion> question;
    private String num_of_ques;
    private String tense;
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<QuestionModelQuestionsQuestion> getQuestion() {
        return this.question;
    }

    public void setQuestion(ArrayList<QuestionModelQuestionsQuestion> question) {
        this.question = question;
    }

    public String getNum_of_ques() {
        return this.num_of_ques;
    }

    public void setNum_of_ques(String num_of_ques) {
        this.num_of_ques = num_of_ques;
    }

    public String getTense() {
        return this.tense;
    }

    public void setTense(String tense) {
        this.tense = tense;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.question);
        dest.writeString(this.num_of_ques);
        dest.writeString(this.tense);
        dest.writeInt(this.score);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
