package com.example.a9900won_hackathon_duksung_postoffice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
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

    private Uri uri;
    private ImageView clipIv;
    private TextView textView;
    private Spinner spinner1, spinner2, spinner3;
    private ArrayAdapter<String> arrayAdapter;
    private String spinnerText2, spinnerText1, spinnerText3 = "";

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;

    private String writerName; // 게시글 작성자 명
    private String postType; // 분류
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        write_title_et = (EditText) findViewById(R.id.write_title_et);
        write_context_et = (EditText) findViewById(R.id.write_context_et);
        btn_write = (Button) findViewById(R.id.btn_write);
        anonymity = (CheckBox) findViewById(R.id.anonymity);

        clipIv = findViewById(R.id.write_clip_iv);
        textView = findViewById(R.id.write_clip_tv);
        spinner1 = findViewById(R.id.spinner_1);
        spinner2 = findViewById(R.id.spinner_2);
        spinner3 = findViewById(R.id.spinner_3);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        storage = FirebaseStorage.getInstance();

        writerName = "";
        postType = "";

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");

        ArrayAdapter Adapter6 = ArrayAdapter.createFromResource(this, R.array.pick_2_2_3, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter Adapter5 = ArrayAdapter.createFromResource(this, R.array.pick_2_2_2, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter Adapter4 = ArrayAdapter.createFromResource(this, R.array.pick_2_2_1, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter Adapter3 = ArrayAdapter.createFromResource(this, R.array.pick_2_2, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter Adapter2 = ArrayAdapter.createFromResource(this,R.array.pick_1_2, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter Adapter1 = ArrayAdapter.createFromResource(this,R.array.pick_1, android.R.layout.simple_spinner_dropdown_item);

        Adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(Adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerText1 = spinner1.getSelectedItem().toString();
                Log.d(spinnerText1, "spinner text1: " + spinnerText1);

                //일반 선택시
                if(spinnerText1.equals("일반")) {
                    spinner2.setAdapter(Adapter2);
                    spinner3.setVisibility(View.INVISIBLE);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            spinnerText2 = spinner2.getSelectedItem().toString();
                            postType = spinnerText2; // 행정, 시설 ...
                            Log.d(spinnerText2, "spinner text2: " +spinnerText2);
                            //Spinner1Text , Spinner2Text  -> 일반 선택시 저장된값 >> 데이터 베이스에 보내야함
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            Toast.makeText(getApplicationContext(), "항목을 선택해주세요", Toast.LENGTH_SHORT).show();
                        }
                    });

                    //학과 선택시
                } else {
                    spinner2.setAdapter(Adapter3);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            spinnerText2 = spinner2.getSelectedItem().toString();
                            Log.d(spinnerText2, "spinner text2:" + spinnerText2);

                            spinner3.setVisibility(View.VISIBLE);
                            if(spinnerText2.equals("글로벌융합대학")) {
                                spinner3.setAdapter(Adapter4);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        spinnerText3 = spinner3.getSelectedItem().toString();
                                        postType = spinnerText3; // 세부 학과
                                        Log.d(spinnerText1 + spinnerText2 + spinnerText3, "메뉴 선택 : ");
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                        Log.d(spinnerText1 + spinnerText2 + spinnerText3, "메뉴 선택 : ");
                                    }
                                });

                            } else if(spinnerText2.equals("과학기술대학")) {
                                spinner3.setAdapter(Adapter5);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        spinnerText3 = spinner3.getSelectedItem().toString();
                                        postType = spinnerText3; // 세부 학과
                                        Log.d(spinnerText1 + spinnerText2 + spinnerText3, "메뉴 선택 : ");
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                        Toast.makeText(getApplicationContext(), "항목을 선택해주세요", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            } else if(spinnerText2.equals("Art-Design대학")) {
                                spinner3.setAdapter(Adapter6);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        spinnerText3 = spinner3.getSelectedItem().toString();
                                        postType = spinnerText3; // 세부 학과
                                        Log.d(spinnerText1 + spinnerText2 + spinnerText3, "메뉴 선택 : ");
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                        Toast.makeText(getApplicationContext(), "항목을 선택해주세요", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            Toast.makeText(getApplicationContext(), "항목을 선택해주세요", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            };
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "항목을 선택해주세요", Toast.LENGTH_SHORT).show();
            }
        });

        // 이미지 첨부
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
            }
        });

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean IsAnonymity = anonymity.isChecked();

                if (write_title_et.getText().toString().equals("")) {
                    Toast.makeText(WriteActivity.this, "제목을 입력해주세요.",
                            Toast.LENGTH_SHORT).show();
                    return;

                } else if (write_context_et.getText().toString().equals("")) {
                    Toast.makeText(WriteActivity.this, "내용을 입력해주세요.",
                            Toast.LENGTH_SHORT).show();
                    return;

                } else {
                    // 현재 로그인한 User 정보 받아오기
                    databaseReference.child("User").child("202222").child("name").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String value = snapshot.getValue(String.class);
                            writerName = value;
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) { }
                    });

                    writePost(writerName, userId, postType, write_title_et.getText().toString(), write_context_et.getText().toString(), false,  IsAnonymity);
                }

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
            }
        });
    }

    private void writePost (String postWriterName, String postWriterUid, String postType, String postTitle, String postContent, Boolean postHasPhoto, Boolean postIsAnonymity) {
        String value = databaseReference.push().getKey();
        FirebaseMessaging.getInstance().subscribeToTopic(value).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d("FCM_Service", value);
            }
        });
        Post post = new Post (postWriterName, postWriterUid, postType, postTitle, postContent, postHasPhoto, value, postIsAnonymity);
        databaseReference.child("Post").child(value).setValue(post);
    }

    private void writePostWithImg (String postWriterName, String postType, String postTitle, String postContent, Boolean postHasPhoto, String postPhotoName, Boolean postIsAnonymity) {
        String value = databaseReference.push().getKey();
        databaseReference.child("Post").push(); // postTopic 자동 생성 함수
        Post post = new Post (postWriterName, "테스트", postType, postTitle, postContent, postHasPhoto, value, postIsAnonymity);
        databaseReference.child("Post").child(databaseReference.child("Post").getKey()).setValue(post);
    }
}