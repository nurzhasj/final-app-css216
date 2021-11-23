package com.hfad.finalapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class FragmentRegister extends Fragment {

    Button btnRegister;
    EditText etUserName, etPassword, etEmail;
    String userName, email, pass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    DBHelper DB;

    @Override
    public void onAttach(Context context){
        sharedPreferences = context.getSharedPreferences("usersFile", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment, container, false);

        DB = new DBHelper(getActivity());

        etUserName = view.findViewById(R.id.etUserName);
        etPassword = view.findViewById(R.id.etPassword);
        etEmail = view.findViewById(R.id.etEmail);

        btnRegister = view.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                userName = etUserName.getText().toString();
                email = etEmail.getText().toString();
                pass = etPassword.getText().toString();

//                editor.putString("userName", userName);
//                editor.putString("pass", pass);
//                editor.putString("email", email);
//                editor.apply();
//                Toast.makeText(getContext(), "Registered", Toast.LENGTH_SHORT).show();

                if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(pass))
                    Toast.makeText(getActivity(), "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUser = DB.checkUsername(userName);
                    if(!checkUser){
                        Boolean insert = DB.insertData(userName, email, pass);
                        if(insert){
                            Toast.makeText(getActivity(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), TopLevelActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getActivity(), "Registration failed!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getActivity(), "User already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }
}
