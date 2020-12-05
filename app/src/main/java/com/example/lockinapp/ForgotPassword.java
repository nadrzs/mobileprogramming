package com.example.lockinapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText mail;
    private Button reset;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mAuth = FirebaseAuth.getInstance();

        mail = findViewById(R.id.editEmailRST);

        reset = findViewById(R.id.ResetBtn);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }

            private void resetPassword() {
                String email = mail.getText().toString().trim();

                if (email.isEmpty()) {
                    mail.setError("Email must be filled.");
                    mail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    mail.setError("Please enter the correct email.");
                    mail.requestFocus();
                    return;
                }

                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ForgotPassword.this,
                                    "Check your email to reset password.", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(ForgotPassword.this,
                                    "Something error.    Try again.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}