package com.example.a9900won_hackathon_duksung_postoffice;

import android.content.Intent;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private EditText editTextID; // 학번
    private EditText editTextPassword;
    private EditText editTextName;
    private EditText editTextMajor;
    private EditText editTextEmail;
    private EditText editTextCode; // 인증번호

    private Button join_button; // 가입하기
    private Button sendCode_button; // 인증코드 보내기
    private Button certify_button; // 인증하기

    String nums = "";
    Boolean numCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        editTextID = (EditText) findViewById(R.id.editTextID);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextMajor = (EditText) findViewById(R.id.editTextMajor);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextCode = (EditText) findViewById(R.id.editTextCode);

        join_button = (Button) findViewById(R.id.join_button);
        certify_button = (Button) findViewById(R.id.certify_button);
        sendCode_button = (Button) findViewById(R.id.sendCode_button);

        join_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 회원가입 처리
                // 이메일과 비밀번호가 공백이 아닌 경우
                if (!editTextEmail.getText().toString().equals("") && !editTextPassword.getText().toString().equals("")) {
                    // 비밀번호는 6자리 이상
                    if (editTextPassword.getTextSize() < 6) {
                        Toast.makeText(SignInActivity.this, "비밀번호는 6자리 이상으로 설정해주세요.", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (numCheck) {
                        String modifyText = editTextID.getText().toString() + "@example.com";
                        createUser(modifyText, editTextPassword.getText().toString());
                    } else {
                        Toast.makeText(SignInActivity.this, "학교 이메일 인증을 해주세요.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // 이메일과 비밀번호가 공백인 경우
                    Toast.makeText(SignInActivity.this, "계정과 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
                }
            }
        });

        sendCode_button.setOnClickListener(new View.OnClickListener() {
            // 이메일 인증 처리
            @Override
            public void onClick(View v) {
                GMailSender sender = new GMailSender("id@gmail.com", "storm200");
                makeRandomNums();
                try {
                    new Thread(() -> {
                        try {
                            sender.sendMail(
                                    "덕성 사서함 학교 이메일 인증을 해주세요.",   //subject.getText().toString(),
                                    "인증번호 : " + nums,           //body.getText().toString(),
                                    "gmlone21130043@gmail.com",          //from.getText().toString(),
                                    editTextEmail.getText().toString()
                            );
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }).start();

                    Toast.makeText(SignInActivity.this, "인증 번호가 전송되었습니다.", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }
        });

        certify_button.setOnClickListener(new View.OnClickListener() {
            // 이메일 인증 확인 처리
            @Override
            public void onClick(View v) {
                if (editTextCode.getText().toString().equals(nums)) {
                    Toast.makeText(SignInActivity.this, "인증되었습니다.", Toast.LENGTH_LONG).show();
                    numCheck = true;
                } else {
                    Toast.makeText(SignInActivity.this, "인증에 실패하였습니다.", Toast.LENGTH_LONG).show();
                    editTextCode.setText("");
                    numCheck = false;
                }
            }
        });
    }

    // FirebaseAuth에 계정 생성
    private void createUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 회원가입 성공시
                            Toast.makeText(SignInActivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                            addUser(editTextID.getText().toString(), editTextName.getText().toString(), editTextMajor.getText().toString());
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // 계정이 중복된 경우
                            Toast.makeText(SignInActivity.this, "회원가입 실패ㅠ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // Firebase Realtime에 계정 생성
    private void addUser(String id, String name, String major) {
        User user = new User (id, name, major);
        databaseReference.child("User").child(id).setValue(user);
    }

    private void makeRandomNums() {
        int randomNum;
        nums = "";

        for (int i = 0 ; i < 6 ; i++) {
            randomNum = (int) (Math.random() * 10);
            nums += Integer.toString(randomNum);
        }
    }
}