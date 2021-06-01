package com.example.littleforest.InputPage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleforest.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddMenuActivity extends AppCompatActivity{
    // implements View.OnClickListener

    private static final String TAG = "AddMenuActivity";

    // 데이터베이스
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private RecyclerView rv_menu;
    private RecyclerView.Adapter adapter_menu;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Diet> data_menu;

    // 페이지, 날짜, 시간
    private String date;
    private String time;

    private TextView txv_toolbar;
    private TextView tv_date;
    private TextView tv_time;

    // 버튼 : 메뉴 추가
    private ImageButton btn_save;
    private Button btn_addMenu;

    /*private ArrayList<String> diet_data;
    private ArrayAdapter<String> listView_adapter;
    private ListView lv_menu;*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_menu);

        //---------- toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기

        //---------- 페이지 이름 & 날짜/시간
        Intent gettedIntent = getIntent();
        date = gettedIntent.getStringExtra("date");
        time = gettedIntent.getStringExtra("time");

        txv_toolbar = (TextView) findViewById(R.id.txv_toolbar);
        tv_time = (TextView) findViewById(R.id.tv_time);
        txv_toolbar.setText(date + " 식단");
        tv_time.setText(time);

        //---------- findViewById
        // 데이터베이스
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Diet");

        rv_menu = findViewById(R.id.rv_menu);
        rv_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv_menu.setLayoutManager(layoutManager);

        data_menu = new ArrayList<>();

        // 버튼
        btn_save = (ImageButton) findViewById(R.id.btn_save);
        btn_addMenu = (Button) findViewById(R.id.btn_addMenu);

        //lv_menu = (ListView) findViewById(R.id.lv_menu);

        //---------- 버튼~리스너 연결
        //btn_save.setOnClickListener(this);
        //btn_addMenu.setOnClickListener(this);

        //---------- 식단 리스트뷰
        /*diet_data = new ArrayList<String>();
        listView_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, diet_data);

        lv_menu.setAdapter(listView_adapter);

        // 리스트뷰 내 아이템 short 클릭 시 -> 다이어그램으로 수정 가능
        lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lv_shortClick(position);
            }
        });

        // 리스트뷰 내 아이템 long 클릭 시 -> 다이어그램으로 삭제 가능
        lv_menu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                lv_longClick(position);
                return true;
            }
        });*/

        //----------- 메뉴 보기
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_menu.clear();

                for(DataSnapshot s : snapshot.getChildren()){
                    Diet diet = s.getValue(Diet.class);

                    if((diet.getDate().equals(date)) && (diet.getTime().equals(time))){
                        data_menu.add(diet);
                    }
                }
                adapter_menu.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddMenuActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
            }
        });

        adapter_menu = new MenuAdapter(data_menu, this);
        rv_menu.setAdapter(adapter_menu);
    }

    /*@Override
    public void onClick(View view){
        switch (view.getId()){
            // save 버튼 : 리스트뷰 -> 데이터베이스에 저장
            case R.id.btn_save :
                saveDiet();
                break;

            // add 버튼 : 리스트뷰에 메뉴 추가
            case R.id.btn_addMenu :
                addMenu();
                break;
        }
    }*/

    /**
     * 데이터베이스에 데이터를 저장하는 함수
     */
    /*public void saveDiet(){
        if(diet_data.size() != 0){
            Diet diet = new Diet(date, time, diet_data);

            databaseReference.push().setValue(diet)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AddMenuActivity.this, "저장했습니다", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddMenuActivity.this, "저장에 실패했습니다", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }*/

    /**
     * 리스트뷰에 메뉴를 추가하는 함수
     */
    /*public void addMenu(){
        //다이어로그 불러오기
        EditText editText = new EditText(AddMenuActivity.this);

        AlertDialog.Builder builder = new AlertDialog.Builder(AddMenuActivity.this);
        builder.setMessage("추가할 메뉴를 입력해주세요");
        builder.setView(editText);

        //Add 버튼 : 입력한 메뉴를 리스트뷰에 추가 -> 다이어로그 종료
        builder.setNegativeButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String menu = editText.getText().toString();

                if(menu.length() != 0){
                    // 입력란이 공백이 아닐 시, 메뉴 추가
                    diet_data.add(menu);
                    listView_adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(AddMenuActivity.this, "메뉴를 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                dialog.cancel();
            }
        });

        //Cancal 버튼 : 다이어로그 종료
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        //다이어로그 실행
        builder.show();
    }*/

    /**
     * 리스트뷰 내 아이템 수정하는 함수
     * @param position
     */
    /*public void lv_shortClick(int position){
        // 다이어로그 불러오기
        EditText editText = new EditText(AddMenuActivity.this);
        String beforeMenu = diet_data.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(AddMenuActivity.this);
        builder.setMessage("이 메뉴를 수정하시겠습니까?");
        builder.setView(editText);
        editText.setText(beforeMenu);

        // Yes 버튼 : 메뉴 수정
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String afterMenu = editText.getText().toString();
                diet_data.set(position, afterMenu);
                listView_adapter.notifyDataSetChanged();
            }
        });

        // No 버튼 : 메뉴 수정 취소
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // 다이어로그 실행
        builder.show();
    }*/

    /**
     * 리스트뷰 내 아이템 삭제하는 함수
     * @param //position
     */
    /*public void lv_longClick(int position){
        // 다이어로그 불러오기
        AlertDialog.Builder builder = new AlertDialog.Builder(AddMenuActivity.this);
        builder.setMessage("이 메뉴를 삭제하시겠습니까?");

        // Yes 버튼 : 메뉴 삭제
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                diet_data.remove(position);
                listView_adapter.notifyDataSetChanged();
            }
        });

        // No 버튼 : 메뉴 삭제 취소
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // 다이어로그 실행
        builder.show();
    }*/

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