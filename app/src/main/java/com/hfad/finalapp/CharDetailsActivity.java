package com.hfad.finalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class CharDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_details);

        getMyIntent();
    }

    private void getMyIntent(){
       if(checkExtras("image_url") && checkExtras("char_name")){
           String imageUrl = getIntent().getStringExtra("image_url");
           String charName = getIntent().getStringExtra("char_name");
           String charNick = getIntent().getStringExtra("char_nickName");
           String charBirthday = getIntent().getStringExtra("char_birthday");
           String[] charOccupation = getIntent().getStringArrayExtra("char_occupation");

           setContent(imageUrl, charName, charNick, charBirthday, charOccupation);
       }
    }

    private void setContent(String imageUrl, String charName, String charNick, String charBirthday, String[] charOccupation){
        TextView name = findViewById(R.id.char_name);
        name.setText(charName);

        TextView nick = findViewById(R.id.char_nickname);
        nick.setText(charNick);

        TextView birthday = findViewById(R.id.char_birthday);
        birthday.setText(charBirthday);

        TextView occupation = findViewById(R.id.char_occupation);

        String occ = "";

        for(String s : charOccupation){
            occ += s + "\n";
        }

        occupation.setText(occ);

        ImageView image = findViewById(R.id.imageUrl);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);
    }

    private boolean checkExtras(String name){
        return getIntent().hasExtra(name);
    }

}