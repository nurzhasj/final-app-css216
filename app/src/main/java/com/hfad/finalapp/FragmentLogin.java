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

import java.util.Date;

public class FragmentLogin extends Fragment {
    Button btnLogin, btnRegister;
    EditText etUserName, etPassword;
    CallbackFragment callbackFragment;
    String userName, pass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    DBHelper DB;

    @Override
    public void onAttach(Context context){
        sharedPreferences = context.getSharedPreferences("usersFile", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
        checkSession();
    }

    private void checkSession() {
        SessionManagement sessionManagement = new SessionManagement(getActivity());
        String username = sessionManagement.getSession();

        if(!username.equals("")){
            moveToMainActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        etUserName = view.findViewById(R.id.etUserName);
        etPassword = view.findViewById(R.id.etPassword);

        btnLogin = view.findViewById(R.id.btnLogin);
        btnRegister = view.findViewById(R.id.btnRegister);

        DB = new DBHelper(getActivity());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                userName = etUserName.getText().toString();
                pass = etPassword.getText().toString();

                editor.putString("username", userName);
                Date d = new Date();
                String date = "";
                date += d.getDate() + ".";
                date += d.getMonth() + 1 + ".";
                date += (d.getYear() + 1900);
                editor.putString("date", date);
                editor.apply();

                User user = new User(userName,pass);

                if(TextUtils.isEmpty(userName) | TextUtils.isEmpty(pass))
                    Toast.makeText(getActivity(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUserPass = DB.checkUsernamePassword(userName, pass);
                    if(checkUserPass){
                        Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                        SessionManagement sessionManagement = new SessionManagement(getActivity());
                        sessionManagement.saveSession(user);
                        Intent intent = new Intent(getActivity(), TopLevelActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(callbackFragment != null){
                    callbackFragment.changeFragment();
                }
            }
        });

        return view;
    }

    public void setCallbackFragment(CallbackFragment callbackFragment){
        this.callbackFragment = callbackFragment;
    }

    private void moveToMainActivity() {
        Intent intent = new Intent(getActivity(), TopLevelActivity.class);
        startActivity(intent);
    }
}
