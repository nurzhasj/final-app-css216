package com.hfad.finalapp;

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

        Bundle bundle = this.getArguments();
        String userName = bundle.getString("LoggedUser");
        String currentDateString = bundle.getString("CurrentDate");

        profileHeader.setText(userName);
        currentDate.setText(currentDateString);

    }

}
