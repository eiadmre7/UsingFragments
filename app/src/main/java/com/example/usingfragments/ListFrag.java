package com.example.usingfragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListFrag extends ListFragment {
    ItemSelected activity;
    //on item clicked interface
    public interface ItemSelected{
        void onItemSelected(int index);
    }
    private String[] pieces;

    public ListFrag() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity=(ItemSelected) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pieces=getResources().getStringArray(R.array.pieces);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,pieces);
        setListAdapter(adapter);
        activity.onItemSelected(0);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        activity.onItemSelected(position);
    }
}