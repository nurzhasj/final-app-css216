package com.hfad.finalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TopLevelActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new CharFragment()).commit();

        sharedPreferences = getSharedPreferences("usersFile", Context.MODE_PRIVATE);

        FrameLayout frameLayout = findViewById(R.id.fragment_container);

        String selectedColor = sharedPreferences.getString("currentColor", "White");

        if(selectedColor.equals("Forest")){
            frameLayout.setBackgroundResource(R.drawable.grad_green);
        }else if(selectedColor.equals("Sea")){
            frameLayout.setBackgroundResource(R.drawable.grad_blue);
        }else if(selectedColor.equals("Sun")){
            frameLayout.setBackgroundResource(R.drawable.grad_yellow);
        }else if(selectedColor.equals("Moon")){
            frameLayout.setBackgroundResource(R.drawable.grad_white);
        }else if(selectedColor.equals("Default")){
            frameLayout.setBackgroundResource(R.color.white);
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener(){
                @Override
                public boolean onNavigationItemSelected(MenuItem item){
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.nav_char:
                            selectedFragment = new CharFragment();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("LoggedUser", getIntent().getStringExtra("LoggedUsername"));
                            bundle.putString("CurrentDate", getIntent().getStringExtra("CurrentDate"));
                            selectedFragment.setArguments(bundle);
                            break;
                        case R.id.nav_settings:
                            selectedFragment = new SettingsFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}