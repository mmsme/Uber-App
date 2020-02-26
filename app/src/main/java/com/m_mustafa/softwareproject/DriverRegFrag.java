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
public class DriverRegFrag extends Fragment {
    private EditText driverName;
    private EditText driverEmail;
    private EditText driverPassword;
    private EditText driverPhone;
    private EditText driverCarType;
    private EditText driverCarModel;
    private Button driverSubmit;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    public DriverRegFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_driver_reg, container, false);

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
        //---------------------------------------------------------------------------------------------

        driverName = (EditText) view.findViewById(R.id.signin_driver_name);
        driverEmail = (EditText) view.findViewById(R.id.signin_driver_email);
        driverPassword = (EditText) view.findViewById(R.id.signin_driver_password);
        driverPhone = (EditText) view.findViewById(R.id.signin_driver_phone);
        driverCarType = (EditText) view.findViewById(R.id.signin_driver_car);
        driverCarModel = (EditText) view.findViewById(R.id.signin_driver_car_model);
        driverSubmit = (Button) view.findViewById(R.id.Sign_driver_btn);

        //-------------------------------- Customer Sign up listener -----------------------------------
        driverSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = driverName.getText().toString();
                final String email = driverEmail.getText().toString();
                final String password = driverPassword.getText().toString();
                final String phone = driverPhone.getText().toString();
                final String carType = driverCarType.getText().toString();
                final String carModel = driverCarModel.getText().toString();

                //----------------- check if EditText Data --------------------------------------------
                if (name.isEmpty()) {
                    driverName.setError("Please Enter Your Name");
                    return;
                }

                if (email.isEmpty()) {
                    driverEmail.setError("Please Enter Your Email");
                    return;
                } else {
                    if (!isEmailValid(driverEmail.getText().toString())) {
                        driverEmail.setError("Please Enter Valid Email");
                        return;
                    }
                }

                if (password.isEmpty()) {
                    driverPassword.setError("Please Enter Your Password");
                    return;
                } else {
                    if (password.length() > 64 || password.length() <= 7) {
                        driverPassword.setError("Password Length 8:64");
                        return;
                    }
                }


                if (carType.isEmpty()) {
                    driverCarType.setError("Please Enter Your Car Type");
                    return;
                }

                if (carModel.isEmpty()) {
                    driverCarModel.setError("Please Enter Your Car Model");
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
                            DatabaseReference currentUser = FirebaseDatabase.getInstance().getReference().child("User").child("Driver").child(user_id);

                            Map userInfo = new HashMap();
                            userInfo.put("Name", name);
                            userInfo.put("Email", email);
                            userInfo.put("Phone Number", phone);
                            userInfo.put("Car Type", carType);
                            userInfo.put("Car Model", carModel);

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
