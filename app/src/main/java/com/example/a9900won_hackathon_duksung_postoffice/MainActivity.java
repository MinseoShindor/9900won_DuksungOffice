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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private EditText editTextID; // 학번
    private EditText editTextPassword;
    private EditText editTextName;
    private EditText editTextMajor;
    private EditText editTextEmail;
    private EditText editTextCode; // 인증번호

    private Button buttonJoin;
    private Button buttonJoin2;
    private Button buttonJoin3;

    String nums = "";
    Boolean numCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        editTextEmail = (EditText) findViewById(R.id.editText_email);
        editTextPassword = (EditText) findViewById(R.id.editText_passWord);
        editTextName = (EditText) findViewById(R.id.editText_name);

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


                    if (numCheck) {
                        addUser("20180433", "허희원", "아미공", "뿡");
                        // addUser(editTextID.getText().toString(), editTextName.getText().toString(), editTextMajor.getText().toString(), firebaseAuth.getUid());

                    } else {
                        Toast.makeText(MainActivity.this, "학교 이메일 인증을 해주세요.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // 이메일과 비밀번호가 공백인 경우
                    Toast.makeText(MainActivity.this, "계정과 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(MainActivity.this, value, Toast.LENGTH_LONG).show();

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
                            Toast.makeText(MainActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            // 계정이 중복된 경우
                            Toast.makeText(MainActivity.this, "실패ㅠ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void addUser(String id, String name, String major, String uid) {
        User user = new User (id, name, major, uid);

        databaseReference.child(id).child("info").setValue(user);
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


