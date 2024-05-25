package com.example.usingfragments;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements ListFrag.ItemSelected {
    private String[] details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        details=getResources().getStringArray(R.array.descriptions);
        //device in portrait mode...
        if(findViewById(R.id.Layout_portrait)!=null){
            FragmentManager manager=this.getSupportFragmentManager();
            manager.beginTransaction()
                    .show(Objects.requireNonNull(manager.findFragmentById(R.id.listFrag)))
                    .hide(Objects.requireNonNull(manager.findFragmentById(R.id.detailFrag)))
                    .addToBackStack(null)
                    .commit();
            // Optionally, you can remove the fragment container view for detailsfrag
            // to make listFrag occupy the entire screen
            findViewById(R.id.detailFrag).setVisibility(GONE);
        }
        //device in landscape mode...
        if(findViewById(R.id.Layout_land)!=null){
            FragmentManager manager=this.getSupportFragmentManager();
            manager.beginTransaction()
                    .show(Objects.requireNonNull(manager.findFragmentById(R.id.listFrag)))
                    .show(Objects.requireNonNull(manager.findFragmentById(R.id.detailFrag)))
                    .commit();
        }
    }

    @Override
    public void onItemSelected(int index) {
        DetailsFrag detailsFrag = (DetailsFrag) getSupportFragmentManager().findFragmentById(R.id.detailFrag);
        if (detailsFrag != null) {
            detailsFrag.updateDetails(details[index]);
        }

        if(findViewById(R.id.Layout_portrait)!=null){
            FragmentManager manager=this.getSupportFragmentManager();

            // Hide the listFrag
            manager.beginTransaction()
                    .hide(Objects.requireNonNull(manager.findFragmentById(R.id.listFrag)))
                    .commit();
            findViewById(R.id.detailFrag).setVisibility(VISIBLE);

            // Show the detailFrag and occupy the entire screen
            manager.beginTransaction()
                    .show(Objects.requireNonNull(manager.findFragmentById(R.id.detailFrag)))
                    .addToBackStack(null)
                    .commit();

            // Optionally, you can remove the fragment container view for listFrag
            // to make detailFrag occupy the entire screen
            findViewById(R.id.listFrag).setVisibility(GONE);
        }
    }
    @Override
    public void onBackPressed() {
        // Check if the detailFrag is visible and listFrag is hidden
        if (findViewById(R.id.Layout_portrait) != null &&
                getSupportFragmentManager().findFragmentById(R.id.detailFrag).isVisible() &&
                getSupportFragmentManager().findFragmentById(R.id.listFrag).isHidden()) {

            // Show the listFrag and hide the detailFrag
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .hide(Objects.requireNonNull(manager.findFragmentById(R.id.detailFrag)))
                    .show(Objects.requireNonNull(manager.findFragmentById(R.id.listFrag)))
                    .commit();
            findViewById(R.id.detailFrag).setVisibility(GONE);
            // Optionally, restore visibility of the fragment container view for listFrag
            findViewById(R.id.listFrag).setVisibility(VISIBLE);
        } else {
            // Default behavior for back button
            super.onBackPressed();
        }
    }


}