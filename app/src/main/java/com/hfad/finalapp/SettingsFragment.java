package com.hfad.finalapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {
    private ArrayList<ColorItem> mColorList;
    private ColorAdapter mAdapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onAttach(Context context){
        sharedPreferences = context.getSharedPreferences("usersFile", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();

        initList();

        Spinner spinner = getView().findViewById(R.id.spinner);
        mAdapter = new ColorAdapter(getActivity(), mColorList);
        spinner.setAdapter(mAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ColorItem clickedItem = (ColorItem) parent.getItemAtPosition(position);
                String clickedColorName = clickedItem.getColorName();
                if(!clickedColorName.equals("Change background")){
                    Toast.makeText(getActivity(), clickedColorName + " selected", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), TopLevelActivity.class);
                    editor.remove("currentColor").commit();
                    editor.putString("currentColor", clickedColorName);
                    editor.apply();
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        CardView share = getView().findViewById(R.id.cardViewShare);
        CardView logout = getView().findViewById(R.id.cardViewLogout);


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, "Share using: "));
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManagement sessionManagement = new SessionManagement(getActivity());
                sessionManagement.removeSession();
                Toast.makeText(getActivity(), "Logged out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initList(){
        mColorList = new ArrayList<>();
        mColorList.add(new ColorItem(R.drawable.ic_baseline_color_lens_24, "Change background"));
        mColorList.add(new ColorItem(R.drawable.grad_green,"Forest"));
        mColorList.add(new ColorItem(R.drawable.grad_blue,"Sea"));
        mColorList.add(new ColorItem(R.drawable.grad_yellow, "Sun"));
        mColorList.add(new ColorItem(R.drawable.grad_white, "Moon"));
        mColorList.add(new ColorItem(R.color.white, "Default"));
    }
}
