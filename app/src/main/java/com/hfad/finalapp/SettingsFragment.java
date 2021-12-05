package com.hfad.finalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();

        CardView cardView = getView().findViewById(R.id.cardViewChangeBack);
//        CharFragment charFragment = new CharFragment();


//        cardView.setOnClickListener((view) -> {
//            Bundle bundle = new Bundle();
//            bundle.putString("randColor", "black");
//            charFragment.setArguments(bundle);
//        });

    }
}
