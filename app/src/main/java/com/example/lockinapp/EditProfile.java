package com.example.lockinapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.util.HashMap;
import java.util.UUID;

import okhttp3.internal.cache.DiskLruCache;

public class EditProfile extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference refer;
    private String ID;
    private EditText uname, mail;
    //private ImageView PP;
    private Button update;

    //public Uri imageUri;

    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
//            imageUri = data.getData();
//            PP.setImageURI(imageUri);
//            uploadPict();
//        }
//    }
//
//    private void uploadPict() {
//        final ProgressDialog prodi = new ProgressDialog(this);
//        prodi.setTitle("Uploading image ...");
//        prodi.show();
//
//        final String random = UUID.randomUUID().toString();
//        StorageReference stoRef = mStorageRef.child("images/" + random);
//
//        stoRef.putFile(imageUri)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        prodi.dismiss();
//                        Snackbar.make(findViewById(android.R.id.content), "Image uploaded.", Snackbar.LENGTH_LONG).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception exception) {
//                        prodi.dismiss();
//                        Toast.makeText(EditProfile.this, "Failed to upload.", Toast.LENGTH_LONG).show();
//                    }
//                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//                double progress = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
//                prodi.setMessage("percentage: " + (int) progress + "%");
//            }
//        });
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mAuth = FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        user = FirebaseAuth.getInstance().getCurrentUser();
        refer = FirebaseDatabase.getInstance().getReference("Users");
        ID = user.getUid();

        uname = findViewById(R.id.usernameBoxEP);
        mail = findViewById(R.id.emailBoxEP);

//        PP = findViewById(R.id.profilePictEP);
//        PP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                choosePict();
//            }
//
//            private void choosePict() {
//                Intent intentCh = new Intent();
//                intentCh.setType("image/*");
//                intentCh.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(intentCh, 1);
//            }
//        });

        update = findViewById(R.id.buttonSave);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = uname.getText().toString().trim();
                String email = mail.getText().toString().trim();

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

                HashMap up = new HashMap();
                up.put("username", username);
                up.put("email", email);

                refer.child(ID).updateChildren(up).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditProfile.this, "Update Succesfully", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}