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

                if(TextUtils.isEmpty(userName) | TextUtils.isEmpty(pass))
                    Toast.makeText(getActivity(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUserPass = DB.checkUsernamePassword(userName, pass);
                    if(checkUserPass){
                        Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), TopLevelActivity.class);
                        intent.putExtra("LoggedUsername", userName);
                        intent.putExtra("CurrentDate", new Date().toString());
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
}
