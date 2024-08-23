package com.example.food_planner_iti.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.food_planner_iti.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText emailEditText, passwordEditText ,confirmPassword;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        confirmPassword=findViewById(R.id.confirmPasswordEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signUpButton = findViewById(R.id.signUpButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String confirm_password = confirmPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(SignupActivity.this, "Enter email address!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!email.endsWith("@app.com")) {
                    Toast.makeText(SignupActivity.this, "Enter email address like yourName@app.com", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(SignupActivity.this, "Enter password!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(SignupActivity.this, "Password too short, enter minimum 6 characters!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password.equals(confirm_password)) {
                    Toast.makeText(SignupActivity.this, "Password is not confirmed !", Toast.LENGTH_LONG).show();
                    return;
                }
                // Create user with email and password
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign up success, update UI with the signed-in user's information
                                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                    finishAffinity();
                                    Log.d("SignUp", "createUserWithEmail:success");
                                    Toast.makeText(SignupActivity.this, "Sign up successful.", Toast.LENGTH_SHORT).show();
                                    // You can navigate to another activity here if needed
                                } else {
                                    // If sign up fails, display a message to the user.
                                    Log.w("SignUp", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignupActivity.this, "Authentication failed. Check internet connection" ,
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}