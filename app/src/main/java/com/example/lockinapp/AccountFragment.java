package com.example.lockinapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class AccountFragment extends Fragment {

    View view;

    private FirebaseUser user;
    private DatabaseReference refer;
    private String ID;
    private Button editP;

    private StorageReference mStorageRef;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_account, container, false);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        user = FirebaseAuth.getInstance().getCurrentUser();
        refer = FirebaseDatabase.getInstance().getReference("Users");
        ID = user.getUid();

         TextView uname = (TextView) view.findViewById(R.id.usernameBoxAF);
         TextView mail = (TextView) view.findViewById(R.id.emailBoxAF);

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
                Toast.makeText(getActivity(), "ERROR.", Toast.LENGTH_LONG).show();
            }
        });

        //Profpict = findViewById(R.id.profilePict);

        editP = view.findViewById(R.id.editProfilAF);
        editP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ep = new Intent(getActivity(), EditProfile.class);
                startActivity(ep);
            }
        });

        return view;
    }
}