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

    @Override
    public void onStart() {
        super.onStart();
        checkSession();
    }

    private void checkSession() {
        //check if user is logged in
        //if user is logged in --> move to mainActivity
        SessionManagement sessionManagement = new SessionManagement(getActivity());
        String username = sessionManagement.getSession();

        if(!username.equals("")){
            moveToMainActivity();
        }
        else{
            //do nothing
        }
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


//        if(!sharedPreferences.getString("username", "defValue").equals("defValue")){
//            Intent intent = new Intent(getActivity(), TopLevelActivity.class);
//            intent.putExtra("LoggedUsername", sharedPreferences.getString("username", "defValue"));
//            startActivity(intent);
//        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                userName = etUserName.getText().toString();
                email = etEmail.getText().toString();
                pass = etPassword.getText().toString();

                editor.putString("username", userName);
                editor.apply();

                User user = new User(userName, email, pass);


                String noWhiteSpace = "^[^\\d\\s]+$";
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(pass))
                    Toast.makeText(getActivity(), "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
                else if(!userName.matches(noWhiteSpace)){
                    Toast.makeText(getActivity(),  "Username can't contain a space!", Toast.LENGTH_SHORT).show();
                }else if(!email.matches(emailPattern)){
                    Toast.makeText(getActivity(), "Invalid email! example -> test@gmail.com", Toast.LENGTH_SHORT).show();
                }else if(pass.length() < 8){
                    Toast.makeText(getActivity(), "Password length must be at least 8!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkUser = DB.checkUsername(userName);
                    if(!checkUser){
                        Boolean insert = DB.insertData(userName, email, pass);
                        if(insert){
                            Toast.makeText(getActivity(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            SessionManagement sessionManagement = new SessionManagement(getActivity());
                            sessionManagement.saveSession(user);
                            Intent intent = new Intent(getActivity(), TopLevelActivity.class);
                            intent.putExtra("LoggedUsername", sharedPreferences.getString("username", "defValue"));
                            // Getting and setting formatted data
                            Date d = new Date();
                            String date = "";
                            date += d.getDate() + ".";
                            date += d.getMonth() + 1 + ".";
                            date += (d.getYear() + 1900);

                            intent.putExtra("CurrentDate", date);
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

    private void moveToMainActivity() {
        Intent intent = new Intent(getActivity(), TopLevelActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
