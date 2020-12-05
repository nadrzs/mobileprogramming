package com.example.lockinapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private TextView register, forgotpass;
    private EditText mail, pass;
    private Button Login;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        register = findViewById(R.id.Regist);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister = new Intent(Login.this, Register.class);
                startActivity(intentRegister);
            }
        });

        mail = findViewById(R.id.editEmailTextLGN);
        pass = findViewById(R.id.editPassTextLgn);

        Login = findViewById(R.id.LoginBtn);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }

            private void userLogin() {
                String email = mail.getText().toString().trim();
                String password = pass.getText().toString().trim();

                if (email.isEmpty()){
                    mail.setError("Username must be filed.");
                    mail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    mail.setError("Please enter the correct email.");
                    mail.requestFocus();
                    return;
                }
                if (password.isEmpty()){
                    pass.setError("Password must be filled.");
                    pass.requestFocus();
                    return;
                }
                if (password.length() < 6){
                    pass.setError("Minimum password length must 6 characters.");
                    pass.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    startActivity(new Intent(Login.this, MainActivity.class));
                                }else{
                                    Toast.makeText(Login.this,"Login Failed", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        forgotpass = findViewById(R.id.ForPass);
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentForpass = new Intent(Login.this, ForgotPassword.class);
                startActivity(intentForpass);
            }
        });
    }
}