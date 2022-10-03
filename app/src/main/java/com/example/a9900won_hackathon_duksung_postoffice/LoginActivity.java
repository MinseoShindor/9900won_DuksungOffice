package com.example.a9900won_hackathon_duksung_postoffice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private EditText login_id_et; // 학번
    private EditText login_password_et;
    private Button btn_loginBtn;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        login_id_et = (EditText) findViewById(R.id.login_id_et);
        login_password_et = (EditText) findViewById(R.id.login_password_et);

        btn_loginBtn = findViewById(R.id.btn_loginBtn);
        btn_loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signInWithEmailAndPassword(login_id_et.getText().toString()+"@example.com", login_password_et.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // 로그인 성공시
                                    Log.d(TAG, "로그인 성공");
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    databaseReference.child("User").child(login_id_et.getText().toString()).child("uid").setValue(firebaseAuth.getUid());

                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.putExtra("userId", login_id_et.getText().toString());
                                    startActivity(intent);
                                } else {
                                    // 로그인 실패시
                                    Log.w(TAG, "로그인 실패", task.getException());
                                    Toast.makeText(LoginActivity.this, "학번과 비밀번호를 확인해주세요.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            };
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if(currentUser != null){
            currentUser.reload();
        }
    }
}