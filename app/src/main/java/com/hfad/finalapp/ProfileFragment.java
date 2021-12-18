package com.hfad.finalapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();

        TextView profileHeader = getView().findViewById(R.id.userNameInCard);
        TextView currentDate = getView().findViewById(R.id.currentDate);

        sharedPreferences = getContext().getSharedPreferences("usersFile", Context.MODE_PRIVATE);

        profileHeader.setText(sharedPreferences.getString("username", ""));
        currentDate.setText(sharedPreferences.getString("date", ""));

    }
}
