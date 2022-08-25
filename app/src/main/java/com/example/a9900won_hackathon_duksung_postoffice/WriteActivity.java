package com.example.a9900won_hackathon_duksung_postoffice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class WriteActivity extends AppCompatActivity {

    Uri uri;
    ImageView clipIv;
    TextView textView;
    private Spinner spinner1, spinner2, spinner3;
    private ArrayAdapter<String> arrayAdapter;
    String spinnerText2, spinnerText1, spinnerText3 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        clipIv = findViewById(R.id.write_clip_iv);
        textView = findViewById(R.id.write_clip_tv);
        spinner1 = findViewById(R.id.spinner_1);
        spinner2 = findViewById(R.id.spinner_2);
        spinner3 = findViewById(R.id.spinner_3);

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

        

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
            }
        });


    }

}