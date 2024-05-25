package com.example.usingfragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFrag extends Fragment {

private TextView tvDetails;

    public DetailsFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        tvDetails = view.findViewById(R.id.tvDescription);
        return view;
    }
    public void updateDetails(String details) {
        tvDetails.setText(details);
    }

}