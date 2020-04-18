package com.tense.activity.ShowQuestion.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.tense.activity.ShowQuestion.ShowQuestionViewPager;
import com.tense.model.questionModel.QuestionModelQuestions;
import com.tense.R;
import com.tense.databinding.FragmentShowQuestionViewPagerBinding;


/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    QuestionModelQuestions questions;
    FragmentShowQuestionViewPagerBinding binding;
    int index = 0;
    int score;
    View root;


    public static PlaceholderFragment newInstance(int index, QuestionModelQuestions questions) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        bundle.putParcelable("ques", questions);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
            questions = getArguments().getParcelable("ques");
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_show_question_view_pager, container, false);

        root = binding.getRoot();
        score=questions.getScore();
        binding.questionNo.setText(index + 1 + "/" + questions.getNum_of_ques());
        binding.score.setText(score+ "");
        binding.setQuestion(questions.getQuestion().get(index));
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = binding.radioGroup.getCheckedRadioButtonId();
                if (selectedId > 0) {
                    RadioButton radioButton = root.findViewById(selectedId);
                    if (radioButton != null) {
                        if (questions.getQuestion().get(index).getCorrect().equals(radioButton.getText().toString())) {
                            Toast.makeText(getActivity(),   "Correct", Toast.LENGTH_SHORT).show();
                            score += 1;
                        }
                    }
                }
                ((ShowQuestionViewPager) getActivity()).onNextPressed(index,score);
            }
        });
        return root;
    }
}