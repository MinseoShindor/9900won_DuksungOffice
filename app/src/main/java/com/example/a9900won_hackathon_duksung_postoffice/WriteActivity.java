package com.example.a9900won_hackathon_duksung_postoffice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class WriteActivity extends AppCompatActivity {

    private EditText write_title_et;
    private EditText write_context_et;
    private Button btn_write;
    private CheckBox anonymity;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;

    private String writerName; // 게시글 작성자 명

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        write_title_et = (EditText) findViewById(R.id.write_title_et);
        write_context_et = (EditText) findViewById(R.id.write_title_et);
        btn_write = (Button) findViewById(R.id.btn_write);
        anonymity = (CheckBox) findViewById(R.id.anonymity);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        storage = FirebaseStorage.getInstance();
        writerName = "";

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean IsAnonymity = anonymity.isChecked();


                /*
                // 이미지 첨부가 됐다면...
                if (true) {
                    writePostWithImg("허희원", "A = 분야", "안녕하세요", "되새겨보아요.", true, "", "", false);
                    // final Uri file = Uri.fromFile(new File(Uripath)); // 절대경로uri를 file에 할당
                    StorageReference storageReference = storage.getReference().child("PostImages").child(""); // 게시글 postTopic 기준
                    storageReference.putFile(uriUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            final Task<Uri> imageUrl = task.getResult().getStorage().getDownloadUrl();
                            while (!imageUrl.isComplete()) ;
                        }
                    });
                } else { }
                 */

                writePost("허희원", "분야 -> 스피널로 받아와야 함", write_title_et.getText().toString(), write_context_et.getText().toString(), false,  IsAnonymity);
            }
        });
    }

    private void writePost (String postWriterName, String postType, String postTitle, String postContent, Boolean postHasPhoto, Boolean postIsAnonymity) {
        String value = databaseReference.push().getKey();
        //fcm topic 구독
        FirebaseMessaging.getInstance().subscribeToTopic(value).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d("FCM_Service", value);
            }
        });
        //여기까지 -현민-
        Post post = new Post (postWriterName, postType, postTitle, postContent, postHasPhoto, value, postIsAnonymity);
        databaseReference.child("Post").child(value).setValue(post);
        Toast.makeText(WriteActivity.this, value, Toast.LENGTH_SHORT).show();
    }

    private void writePostWithImg (String postWriterName, String postType, String postTitle, String postContent, Boolean postHasPhoto, String postPhotoName, Boolean postIsAnonymity) {
        databaseReference.child("Post").push(); // postTopic 자동 생성 함수
        Post post = new Post (postWriterName, postType, postTitle, postContent, postHasPhoto, postPhotoName, databaseReference.child("Post").getKey(), postIsAnonymity);
        databaseReference.child("Post").child(databaseReference.child("Post").getKey()).setValue(post);
    }
}