package com.example.lockinapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class MyAccount extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference refer;
    //private DatabaseReference daref;
    private String ID;
    private Button editP;
    //private ImageView Profpict;

    private StorageReference mStorageRef;
    //private DatabaseReference DR = daref.child("images/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        user = FirebaseAuth.getInstance().getCurrentUser();
        refer = FirebaseDatabase.getInstance().getReference("Users");
        ID = user.getUid();

        final TextView uname = (TextView) findViewById(R.id.usernameBox);
        final TextView mail = (TextView) findViewById(R.id.emailBox);

        refer.child(ID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User myAccount = snapshot.getValue(User.class);

                if (myAccount != null) {
                    String username = myAccount.username;
                    String email = myAccount.email;

                    uname.setText(username);
                    mail.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MyAccount.this, "Error. Failed to display user profile.", Toast.LENGTH_LONG).show();
            }
        });

        //Profpict = findViewById(R.id.profilePict);

        editP = findViewById(R.id.editProfil);
        editP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ep = new Intent(MyAccount.this, EditProfile.class);
                startActivity(ep);
            }
        });
    }
}

//    @Override
//    protected void onStart() {
//        super.onStart();
//        DR.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String pict = snapshot.getValue(String.class);
//                Picasso.get().load(pict).into(Profpict);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(MyAccount.this, "Something wrong.", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//}