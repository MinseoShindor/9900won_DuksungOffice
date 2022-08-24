package com.example.a9900won_hackathon_duksung_postoffice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.UploadTask;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DB extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;

    private EditText editTextID; // 학번
    private EditText editTextPassword;
    private EditText editTextName;
    private EditText editTextMajor;
    private EditText editTextEmail;
    private EditText editTextCode; // 인증번호

    private Button buttonJoin;
    private Button buttonJoin2;
    private Button buttonJoin3;
    private Button buttonJoin4;

    String nums = "";
    Boolean numCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        storage = FirebaseStorage.getInstance();

        editTextEmail = (EditText) findViewById(R.id.editText_email);
        editTextPassword = (EditText) findViewById(R.id.editText_passWord);
        editTextName = (EditText) findViewById(R.id.editText_name);

        ArrayList<Integer> nums = new ArrayList<Integer>();

        buttonJoin = (Button) findViewById(R.id.btn_join);
        buttonJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextEmail.getText().toString().equals("") && !editTextPassword.getText().toString().equals("")) {
                    // 이메일과 비밀번호가 공백이 아닌 경우
                    String modifyText = editTextEmail.getText().toString() + "@example.com";
                    createUser(modifyText, editTextPassword.getText().toString(), editTextName.getText().toString());

                    // 숫자 8자리만 입력 받기

                    // 비밀번호는 6자리 이상
                    if (editTextPassword.getTextSize() < 6) {
                        Toast.makeText(DB.this, "비밀번호는 6자리 이상으로 설정해주세요.", Toast.LENGTH_LONG).show();
                    }

                    if (numCheck) {
                        addUser("20180433", "허희원", "아미공", "뿡");
                        // addUser(editTextID.getText().toString(), editTextName.getText().toString(), editTextMajor.getText().toString(), firebaseAuth.getUid());

                    } else {
                        Toast.makeText(DB.this, "학교 이메일 인증을 해주세요.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // 이메일과 비밀번호가 공백인 경우
                    Toast.makeText(DB.this, "계정과 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonJoin2 = (Button) findViewById(R.id.btn_join2);
        buttonJoin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GMailSender sender = new GMailSender("id@gmail.com", "storm200");
                try {
                    new Thread(() -> {
                        try {
                            sender.sendMail(
                                    "덕성 사서함 학교 이메일 인증을 해주세요.",   //subject.getText().toString(),
                                    "인증번호 : " + makeRandomNums(),           //body.getText().toString(),
                                    "gmlone21130043@gmail.com",          //from.getText().toString(),
                                    "heedal99@naver.com"          //to.getText().toString()
                            );
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }).start();
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }
        });

        buttonJoin3 = (Button) findViewById(R.id.btn_join3);
        buttonJoin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser("20180433", "허희원", "아미공", "뿡");

                databaseReference.child("20180433").child("info").child("name").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        Toast.makeText(DB.this, value, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
                    }
                });

                /*
                if (editTextName.getText().toString().equals(nums)) {
                    Toast.makeText(MainActivity.this, "인증되었습니다.", Toast.LENGTH_LONG).show();
                    numCheck = true;
                } else {
                    Toast.makeText(MainActivity.this, "인증에 실패하였습니다.", Toast.LENGTH_LONG).show();
                    editTextName.setText("");
                    numCheck = false;
                }
                */
            }
        });

        buttonJoin4 = (Button) findViewById(R.id.btn_join4);
        buttonJoin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                // 이미지 첨부가 됐다면...
                if (true) {
                    writePostWithImg("허희원", "A = 분야", "안녕하세요", "되새겨보아요.", true, "", "", false);
                    // final Uri file = Uri.fromFile(new File(Uripath)); // 절대경로uri를 file에 할당
                    StorageReference storageReference = storage.getReference().child("PostImages").child(""); // 게시글 postTopic 기준
                    /*
                    storageReference.putFile(uriUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            final Task<Uri> imageUrl = task.getResult().getStorage().getDownloadUrl();
                            while (!imageUrl.isComplete()) ;
                        }
                    });
                } else {

                }
                */

                // postWriterName, postType, postTitle, postContent, postHasPhoto, postUID, postIsAnonymity
                // writePost("허희원", "A = 분야", "안녕하세요", "되새겨보아요.", false, "이건 내 글이야", false);

                databaseReference.child("Post").child("이건 내 글이야").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            String key = postSnapshot.getKey();
                            String value = postSnapshot.child("postType").getValue(String.class);
                            int num = postSnapshot.child("postLikeCount").getValue(Integer.class);
                            int date = postSnapshot.child("postTime").getValue(Integer.class);

                            /* 분야별 불러오기
                            if (value.equals("글로벌융합대학")) {
                                String text = postSnapshot.child("postContent").getValue(String.class);
                                Toast.makeText(DB.this, text, Toast.LENGTH_LONG).show();
                            }
                            */

                            // 추천순 불러오기 (2개)
                            if (num > 99) {
                                nums.add(num);
                            }

                            // 최신순 불러오기 (3개)
                            // 이미 파이어 베이스에 제일 최신글이 맨 위에 있음 불러올 때도 맨 위 데이터부터 가져옴
                            // count 세어서 3번 넘어가면 끝
                        }

                        /*
                        String value = dataSnapshot.getValue(String.class);
                        Toast.makeText(DB.this, value, Toast.LENGTH_LONG).show();
                        dataSnapshot.getChildren();

                       \for (DataSnapshot postSnapshot2: postSnapshot.getChildren()) {
                              String value = postSnapshot2.getValue(String.class);
                         }
                        */

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
                    }
                });

                databaseReference.child("Post").child("이건 내 글이야").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            Collections.sort(nums, Collections.reverseOrder());

                            int num = postSnapshot.child("postLikeCount").getValue(Integer.class);

                            // 이 코드 위까지 탐색 해야 하고, 괄호 닫고 실행될 기능이 아래
                            if (num == nums.get(1)) {
                                String text = postSnapshot.child("postContent").getValue(String.class);
                                Toast.makeText(DB.this, text, Toast.LENGTH_LONG).show();
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) { }
                });
            }
        });

        /*
        // 로그인 처리
        // signInWithEmailAndPassword 메서드를 사용하여 이메일 주소와 비밀번호를 가져와 유효성을 검사한 후 사용자를 로그인
        firebaseAuth.signInWithEmailAndPassword(//사용자가 입력한 아이디 editTextID.getText().toString()+"@example.com"
                        // , 입력한 password)
                .addOnCompleteListener(this) { task ->
            if()

            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_LONG).show()
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show()
            }
        }
            */
    }


    private void createUser(String email, String password, String name) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 회원가입 성공시
                            Toast.makeText(DB.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            // 계정이 중복된 경우
                            Toast.makeText(DB.this, "실패ㅠ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void addUser(String id, String name, String major, String uid) {
        User user = new User (id, name, major, uid);

        databaseReference.child("User").child(id).child("UserInfo").setValue(user);
    }


    private void writePost (String postWriterName, String postType, String postTitle, String postContent, Boolean postHasPhoto, Boolean postIsAnonymity) {
        databaseReference.child("Post").push(); // postTopic 자동 생성 함수
        Post post = new Post (postWriterName, postType, postTitle, postContent, postHasPhoto, databaseReference.child("Post").getKey(), postIsAnonymity);
        databaseReference.child("Post").child(databaseReference.child("Post").getKey()).setValue(post);
    }


    private void writePostWithImg (String postWriterName, String postType, String postTitle, String postContent, Boolean postHasPhoto, String postPhotoName, Boolean postIsAnonymity) {
        databaseReference.child("Post").push(); // postTopic 자동 생성 함수
        Post post = new Post (postWriterName, postType, postTitle, postContent, postHasPhoto, postPhotoName, databaseReference.child("Post").getKey(), postIsAnonymity);
        databaseReference.child("Post").child(databaseReference.child("Post").getKey()).setValue(post);
    }


    private String makeRandomNums() {
        int randomNum;

        for (int i = 0 ; i < 6 ; i++) {
            randomNum = (int) (Math.random() * 10);
            nums += Integer.toString(randomNum);
        }

        return nums;
    }
}


