package com.derysudrajat.mylocalization;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.tv_goal)
    TextView tvGoal;
    @BindView(R.id.tv_plural)
    TextView tvPlural;
    @BindView(R.id.tv_xliff)
    TextView tvXliff;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);

        int goal1 = 2;
        int goal2 = 0;
        String footbal = String.format(getResources().getString(R.string.goal), "Lilypali", "Irfan Bachdim", goal1, goal2);
        tvGoal.setText(footbal);

        int songCount = 5;
        String pluralText = getResources().getQuantityString(R.plurals.numberOfSongsAvailable, songCount, songCount);
        tvPlural.setText(pluralText);

        tvXliff.setText(getResources().getString(R.string.app_homeurl));
        return root;
    }

}
