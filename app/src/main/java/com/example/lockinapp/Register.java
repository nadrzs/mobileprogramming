package com.example.lockinapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private TextView registAccount;
    private EditText uname, mail, pass;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        uname = findViewById(R.id.editUnameTextRGS);
        mail = findViewById(R.id.editEmailTextRGS);
        pass = findViewById(R.id.editPassTextRGS);

        registAccount = findViewById(R.id.RegistBtn);
        registAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAcc();
            }

            private void registerAcc() {
                final String username = uname.getText().toString().trim();
                final String email = mail.getText().toString().trim();
                String password = pass.getText().toString().trim();

                if (username.isEmpty()){
                    uname.setError("Username must be filled.");
                    uname.requestFocus();
                    return;
                }
                if (email.isEmpty()){
                    mail.setError("Email must be filled.");
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

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    User user = new User(username, email);

                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(Register.this,
                                                        "Register was succesfull.", Toast.LENGTH_LONG).show();
                                            }else {
                                                Toast.makeText(Register.this, "Register Failed",
                                                        Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                }else {
                                    Toast.makeText(Register.this, "Register Failed",
                                            Toast.LENGTH_LONG).show();
                                }

                            }
                        });
            }
        });
    }
}