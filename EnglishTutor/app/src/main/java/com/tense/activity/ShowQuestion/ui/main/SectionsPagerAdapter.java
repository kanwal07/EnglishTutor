package com.tense.activity.ShowQuestion.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.tense.model.questionModel.QuestionModelQuestions;
import com.tense.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentStateAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;
    QuestionModelQuestions questions;

    public SectionsPagerAdapter(Context context, FragmentActivity fm, QuestionModelQuestions questions) {
        super(fm);
        mContext = context;
        this.questions=questions;
    }

    public void setQuestions(QuestionModelQuestions questions) {
        this.questions = questions;
        notifyDataSetChanged();
    }
//    @Override
//    public Fragment getItem(int position) {
//        // getItem is called to instantiate the fragment for the given page.
//        // Return a PlaceholderFragment (defined as a static inner class below).
//        return PlaceholderFragment.newInstance(position,questions);
//    }

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mContext.getResources().getString(TAB_TITLES[0]);
//    }
//
//    @Override
//    public int getCount() {
//        // Show 2 total pages.
//        return questions.getQuestion().size();
//    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return PlaceholderFragment.newInstance(position,questions);
    }

    @Override
    public int getItemCount() {
        return questions.getQuestion().size();
    }
}