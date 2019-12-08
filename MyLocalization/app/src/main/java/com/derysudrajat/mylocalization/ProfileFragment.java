package com.derysudrajat.mylocalization;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.iv_profile)
    ImageView logoProfile;
    @BindView(R.id.iv_github)
    ImageView logoGithub;
    @BindView(R.id.iv_ig)
    ImageView logoInstagram;
    @BindView(R.id.iv_medium)
    ImageView logoMedium;
    @BindView(R.id.iv_backdrop)
    ImageView imgBackdrop;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, root);
        if (getActivity() != null) {
            Glide.with(getActivity()).load(R.drawable.derys).into(logoProfile);
            Glide.with(getActivity()).load(R.drawable.ic_github).into(logoGithub);
            Glide.with(getActivity()).load(R.drawable.ic_instagram).into(logoInstagram);
            Glide.with(getActivity()).load(R.drawable.ic_medium).into(logoMedium);
            Glide.with(getActivity()).load(R.drawable.gd).into(imgBackdrop);

            logoGithub.setOnClickListener(this);
            logoInstagram.setOnClickListener(this);
            logoMedium.setOnClickListener(this);
        }
        return root;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.iv_github:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/derysudrajat"));
                break;
            case R.id.iv_ig:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/derysudrajat"));
                break;
            case R.id.iv_medium:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://medium.com/@dery.io"));
                break;
        }
        startActivity(intent);
    }
}
