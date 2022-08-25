package com.example.a9900won_hackathon_duksung_postoffice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyPage extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        databaseReference.child("User").child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue(String.class);
                String major = snapshot.child("major").getValue(String.class);
                String id = snapshot.child("id").getValue(String.class);

                /*
                // 내가 쓴 글 누르면 -> myPosts에 접근해서 post Topic 접근해서 post Title과 content 가져와서 보여줘야 함
                String myPosts = snapshot.child("myPosts").getValue(String.class); // 게시글 uid 저장
                datareference.child("Post").child(myPosts).child("postTitle").getValue(String.class);...
                String myLikes = snapshot.child("myLikes").getValue(String.class);
                String myScraps = snapshot.child("myScraps").getValue(String.class);
                String myReplies = snapshot.child("myReplies").getValue(String.class);
                */
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
}