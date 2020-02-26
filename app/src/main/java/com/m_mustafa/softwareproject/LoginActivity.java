package com.m_mustafa.softwareproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private EditText userEmail;
    private EditText userPassword;
    private TabLayout myTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //------------------------------ listener to chick if user had sign in ------------------------
        mAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, mapActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };
        //---------------------------------------------------------------------------------------------

        myTab = (TabLayout) findViewById(R.id.login_type);
        myTab.getTabAt(0).setText(R.string.customer).setIcon(R.drawable.person_icon);
        myTab.getTabAt(1).setText(R.string.driver).setIcon(R.drawable.person_icon);

        userEmail = (EditText) findViewById(R.id.login_email);
        userPassword = (EditText) findViewById(R.id.login_password);

        //-------------------------------- log in function --------------------------------------------
        Button button = findViewById(R.id.login_act_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emil = userEmail.getText().toString();
                final String password = userPassword.getText().toString();

                if (emil.isEmpty()) {
                    userEmail.setError("Enter Your Email");
                    return;
                } else {
                    if (!isEmailValid(emil)) {
                        userEmail.setError("Please Enter Valid Email");
                        return;
                    }
                }

                if (password.isEmpty()) {
                    userPassword.setError("Please Enter Your Password");
                    return;
                } else {
                    if (password.length() > 64 || password.length() <= 8) {
                        userPassword.setError("Password Length 8:64");
                        return;
                    }
                }
                mAuth.signInWithEmailAndPassword(emil, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_LONG).show();
                        } else {
                            if (myTab.getSelectedTabPosition() == 0) {
                                String userId = mAuth.getCurrentUser().getUid();
                                DatabaseReference current_user = FirebaseDatabase.getInstance().getReference().child("User").child("Driver").child(userId);
                                current_user.setValue(true);
                            } else if (myTab.getSelectedTabPosition() == 1) {
                                String userId = mAuth.getCurrentUser().getUid();
                                DatabaseReference current_user = FirebaseDatabase.getInstance().getReference().child("User").child("Customer").child(userId);
                                current_user.setValue(true);
                            }
                        }
                    }
                });
            }
        });
        //---------------------------------------------------------------------------------------------
    }

    //------------------------------- check if user sign in  ------------------------------------------
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    public void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);
    }

    //------------------------------------ check email pattern ----------------------------------------
    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
