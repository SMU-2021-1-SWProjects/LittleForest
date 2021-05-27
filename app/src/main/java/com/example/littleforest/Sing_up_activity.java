package com.example.littleforest;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
//로그인 회원가입을 하면 사용자 건강 정보를 건드려야하는데
//시간이 될지 몰라서 일단 보류 (기능 삽입 X)


//회원가입 action 클래스

public class Sing_up_activity extends AppCompatActivity {

    EditText userName, userEmail, userPsw, pswCheck;
    private DatabaseReference mDatabase;

    protected void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.sign_up_layout);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기

        userName = findViewById(R.id.sign_up_name);
        userEmail = findViewById(R.id.sign_up_email);
        userPsw = findViewById(R.id.sign_up_psw);
        pswCheck = findViewById(R.id.sign_up_pswC);

        //수정 버튼 클릭시
        Button signup = findViewById(R.id.sign_up_button);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String getUserAccount_Name = userName.getText().toString().trim();
                String getUserAccount_email = userEmail.getText().toString().trim();
                String getUserAccount_Psw = userPsw.getText().toString().trim();

                //edit text 중에 공백이 있을 경우
                if(userName != null &&
                        userEmail != null &&
                        userPsw != null&&
                        pswCheck != null)
                { Toast.makeText(Sing_up_activity.this, "빈칸 입력을 완료해주세요", Toast.LENGTH_SHORT).show();}


                //비밀번호와 비밀번호 확인이 동일한지 확인
                if(userPsw.equals(pswCheck)) {
                    //haspmap 만들기 (데이터 베이스에 삽입)
                    HashMap result = new HashMap<>();
                    result.put("name", getUserAccount_Name);
                    result.put("emal", getUserAccount_email);
                    result.put("psw", getUserAccount_Psw);

                    Toast.makeText(Sing_up_activity.this, "회원가입 되었습니다.", Toast.LENGTH_SHORT).show();


                }

                writeAccount(getUserAccount_email, getUserAccount_Name, getUserAccount_Psw);



            }

        });
    }

    private void  writeAccount(String email, String userName, String userPsw) {
        User user = new User(userEmail, userName, userPsw);

        mDatabase.child("UserAccount").child(String.valueOf(userEmail)).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // 데이터 저장 성공
                        Toast.makeText(getApplicationContext(), "가입 완료 되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // 데이터 저장 실패
                        Toast.makeText(getApplicationContext(), "가입이 실패되었습니다.", Toast.LENGTH_SHORT).show();
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
