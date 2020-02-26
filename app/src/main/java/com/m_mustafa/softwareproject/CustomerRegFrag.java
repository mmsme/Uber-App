package com.m_mustafa.softwareproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerRegFrag extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private EditText userName;
    private EditText userEmail;
    private EditText userPassword;
    private EditText userPhone;
    private Button customerSubmit;

    public CustomerRegFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_reg, container, false);

        //---------------------- check if user already sign in ----------------------------------------
        mAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(getActivity(), mapActivity.class);
                    startActivity(intent);
                    return;
                }
            }
        };
        //----------------------------------------------------------------------------------------------

        userName = (EditText) view.findViewById(R.id.signin_name);
        userEmail = (EditText) view.findViewById(R.id.signin_email);
        userPassword = (EditText) view.findViewById(R.id.signin_password);
        userPhone = (EditText) view.findViewById(R.id.signin_phone);
        customerSubmit = (Button) view.findViewById(R.id.Sign_cutomer_btn);

        //-------------------------------- Customer Sign up listener -----------------------------------
        customerSubmit = view.findViewById(R.id.Sign_cutomer_btn);
        customerSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = userName.getText().toString();
                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                final String phone = userPhone.getText().toString();

                //----------------- check if EditText Data --------------------------------------------
                if (name.isEmpty()) {
                    userName.setError("Please Enter Your Name");
                    return;
                }

                if (email.isEmpty()) {
                    userEmail.setError("Please Enter Your Email");
                    return;
                } else if (!isEmailValid(email)) {
                    userEmail.setError("Please Enter Valid Email");
                    return;
                }

                if (password.isEmpty()) {
                    userPassword.setError("Please Enter Your Password");
                    return;
                } else if (password.length() > 64 || password.length() <= 7) {
                    userPassword.setError("Password Length 8:64");
                    return;
                }

                //-------------------------------- create new user function ---------------------------
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
                        } else {
                            String user_id = mAuth.getCurrentUser().getUid();
                            DatabaseReference currentUser = FirebaseDatabase.getInstance().getReference().child("User").child("Customer").child(user_id);

                            Map userInfo = new HashMap();
                            userInfo.put("Name", name);
                            userInfo.put("Email", email);
                            userInfo.put("Phone Number", phone);

                            currentUser.setValue(userInfo);
                        }
                    }
                });
            }
        });
        //---------------------------------------------------------------------------------------------

        return view;
    }

    //------------------------------- check if user sign in  ------------------------------------------
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthListener);
    }

    public void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);
    }

    //------------------------------- check data pattern ----------------------------------------------
    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPhoneNumValid(CharSequence phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }
}
