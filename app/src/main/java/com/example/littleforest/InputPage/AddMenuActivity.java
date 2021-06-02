package com.example.littleforest.InputPage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.BarringInfo;
import android.view.Menu;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AddMenuActivity";

    public static Context addMenuContext;

    // 데이터베이스
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private RecyclerView rv_menu;
    private RecyclerView.Adapter adapter_menu;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> data_menu;

    // 페이지, 날짜, 시간
    private String date;
    private String time;

    private TextView txv_toolbar;
    private TextView tv_time;

    // 버튼 : 메뉴 추가, 저장
    private Button btn_addMenu;
    private ImageButton btn_save;

    private Boolean flag = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_menu);

        addMenuContext = this;

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
        databaseReference = database.getReference();

        rv_menu = findViewById(R.id.rv_menu);
        rv_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv_menu.setLayoutManager(layoutManager);

        data_menu = new ArrayList<>();

        // 버튼
        btn_addMenu = (Button) findViewById(R.id.btn_addMenu);
        btn_save = (ImageButton) findViewById(R.id.btn_save);

        //---------- 버튼~리스너 연결
        btn_addMenu.setOnClickListener(this);
        btn_save.setOnClickListener(this);

        //----------- 메뉴 보기
        seeMenu();
    }

    /**
     * 메뉴를 보여주는 함수
     */
    public void seeMenu(){
        databaseReference.child("Diet").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_menu.clear();

                for(DataSnapshot s : snapshot.getChildren()){
                    Diet diet = s.getValue(Diet.class);

                    if((diet.getDate().equals(date)) && (diet.getTime().equals(time))){
                        ArrayList<String> menu = diet.getMenu();

                        for(String m : menu){
                            data_menu.add(m);
                        }
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

    /**
     * 버튼(메뉴 추가, 저장) 클릭 이벤트를 발생시키는 함수
     * @param view
     */
    @Override
    public void onClick(View view){
        switch (view.getId()){
            // add 버튼 : 리사이클러뷰에 메뉴 추가
            case R.id.btn_addMenu :
                addMenu();
                break;

            // save 버튼 : 데이터베이스에 리사이클러뷰 저장
            case R.id.btn_save :
                saveDiet();
                break;
        }
    }

    /**
     * 리사이클러뷰 아이템을 추가하는 함수
     */
    public void addMenu(){
        //다이어로그 불러오기
        EditText editText = new EditText(AddMenuActivity.this);

        AlertDialog.Builder builder = new AlertDialog.Builder(AddMenuActivity.this);
        builder.setMessage("추가할 메뉴를 입력해주세요");
        builder.setView(editText);

        //Add 버튼 : 입력한 메뉴를 리사이클러뷰에 추가 -> 다이어로그 종료
        builder.setNegativeButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String menu = editText.getText().toString();

                if(menu.length() != 0){
                    /*if(data_menu.get(0).equals("")){
                        data_menu.remove(0);
                    }*/
                    data_menu.add(menu);
                    adapter_menu.notifyDataSetChanged();
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
    }

    /**
     * 데이터베이스에 데이터를 저장하는 함수
     */
    public void saveDiet(){
        /*String string_null = "null";
        ArrayList<String> arrayList_null = new ArrayList<>();
        arrayList_null.add("null");
        Diet diet_null = new Diet(string_null, string_null, arrayList_null);
        databaseReference.child("Diet").push().setValue(diet_null);*/

        if(data_menu.size() != 0){
            databaseReference.child("Diet").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot s : snapshot.getChildren()){
                        String id = s.getKey();
                        Diet diet_saved = s.getValue(Diet.class);

                        if( (diet_saved.getDate().equals(date)) && (diet_saved.getTime().equals(time)) ){
                            databaseReference.child("Diet").child(id).removeValue();
                            //.child("menu").setValue(data_menu);
                        }
                    }

                    Diet diet = new Diet(date, time, data_menu);
                    databaseReference.child("Diet").push().setValue(diet);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(AddMenuActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 데이터 자장/실패를 알려주는 함수
     * @param toast
     */
    public void toastData(Task<Void> toast){
        toast.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AddMenuActivity.this, "저장했습니다", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddMenuActivity.this, "저장에 실패했습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 뒤로가기 버튼을 눌렀을 때 페이지를 종료하는 함수
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 뒤로가기 버튼 눌렀을 때
                finish();
                ((AddDietActivity)AddDietActivity.addDietContext).seeDiet();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}