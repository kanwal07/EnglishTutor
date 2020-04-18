package com.tense.model.exampleModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ExampleModelModels implements Parcelable {
    public static final Creator<ExampleModelModels> CREATOR = new Creator<ExampleModelModels>() {
        @Override
        public ExampleModelModels createFromParcel(Parcel source) {
            ExampleModelModels var = new ExampleModelModels();
            var.array = source.createStringArrayList();
            var.name = source.readString();
            return var;
        }

        @Override
        public ExampleModelModels[] newArray(int size) {
            return new ExampleModelModels[size];
        }
    };
    private ArrayList<String> array;
    private String name;

    public ArrayList<String> getArray() {
        return this.array;
    }

    public void setArray(ArrayList<String> array) {
        this.array = array;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.array);
        dest.writeString(this.name);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
