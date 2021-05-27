package com.example.littleforest.InputPage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.littleforest.MainActivity;
import com.example.littleforest.R;
import com.example.littleforest.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

//건강정보
public class HealthInformation  extends AppCompatActivity {


    EditText readName, readWeight, readStature;
    Spinner dspinner, bspinner;

    TextView NaviUserName;
    private DatabaseReference mDatabase;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fragment_health_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기actionBar.setHomeAsUpIndicator(R.drawable.back_button);




        //건강정보 입력 텍스트 박스 불러오기
        readName = findViewById(R.id.edit_name);
        readWeight = findViewById(R.id.edit_weight);
        readStature = findViewById(R.id.edit_stature);

        //혈압, 질병 드롭다운
        bspinner = findViewById(R.id.blood_spinner);
        dspinner = findViewById(R.id.disease_spinner);

        //네비바 위에 유저 이름
        NaviUserName = findViewById(R.id.username);

        //firebase 정의
        mDatabase = FirebaseDatabase.getInstance().getReference();


        //혈압 선택
        ArrayAdapter bloodAdapter = ArrayAdapter.createFromResource(this, R.array.blood, android.R.layout.simple_spinner_dropdown_item);
        bloodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bspinner.setAdapter(bloodAdapter);

        bspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //질병 선택
        ArrayAdapter diseaseAdapter = ArrayAdapter.createFromResource(this, R.array.disease, android.R.layout.simple_spinner_dropdown_item);
        diseaseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dspinner.setAdapter(diseaseAdapter);

        dspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        //수정 버튼 클릭시
        Button modify = findViewById(R.id.healty_modify);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUserName = readName.getText().toString();
                String getStature = readStature.getText().toString();
                String getWeight = readWeight.getText().toString();
                String getBlood = bspinner.getSelectedItem().toString();
                String getDisease = dspinner.getSelectedItem().toString();

                //haspmap 만들기 (데이터 베이스에 삽입)
                HashMap result = new HashMap<>();
                result.put("name", getUserName);
                result.put("stature", getStature);
                result.put("weight", getWeight);
                result.put("blood", getBlood);
                result.put("disease", getDisease);



                writeHeathInformation("1", getUserName, getStature, getWeight, getBlood, getDisease);

            }

        });



    }

    private void  writeHeathInformation(String id, String name, String stature, String weight, String blood, String disease) {
        User user = new User(name, stature, weight, blood, disease);

        mDatabase.child("users").child(id).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // 저장 성공
                        Toast.makeText(getApplicationContext(), "수정 완료되었습니다.", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // 저정 실패
                        Toast.makeText(getApplicationContext(), "수정이 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 뒤로가기 버튼 눌렀을 때
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);


    }


}
