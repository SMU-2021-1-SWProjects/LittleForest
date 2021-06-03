package com.example.littleforest.InputPage;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleforest.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AddMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AddMenuActivity";

    public static Context addMenuContext;

    // 데이터베이스
    private FirebaseDatabase database = FirebaseDatabase.getInstance();;
    private DatabaseReference databaseReference = database.getReference();

    private RecyclerView rv_menu;
    private RecyclerView.Adapter adapter_menu;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> data_menu;

    //-----
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference foodRef = db.collection("Food");

    // 페이지, 날짜, 시간
    private String date;
    private String time;

    private TextView txv_toolbar;
    private TextView tv_time;

    // 버튼 : 메뉴 추가, 저장
    private Button btn_addMenu;
    private ImageButton btn_save;

    // 다이얼로그
    private Dialog dialog_addMenu;
    private EditText et_add_menu;

    private TextView tv_eatingfood;
    private TextView tv_warning;
    private TextView tv_eatingfood_add;
    private TextView tv_eatingfood_cancel;

    private EatingFoodSearchAdapter adapter_search;
    private ListView lv_search;

    private Boolean flag = false;

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

        //---------- 데이터베이스
        rv_menu = findViewById(R.id.rv_menu);
        rv_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv_menu.setLayoutManager(layoutManager);

        data_menu = new ArrayList<>();

        //---------- 버튼
        btn_addMenu = (Button) findViewById(R.id.btn_addMenu);
        btn_save = (ImageButton) findViewById(R.id.btn_save);

        btn_addMenu.setOnClickListener(this);
        btn_save.setOnClickListener(this);

        //----------- 메뉴 보기
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

        //---------- 다이얼로그
        dialog_addMenu = new Dialog(AddMenuActivity.this);
        dialog_addMenu.setContentView(R.layout.eatingfood_dialog);

        et_add_menu = dialog_addMenu.findViewById(R.id.et_add_menu);
        et_add_menu.setText(null);

        tv_eatingfood = dialog_addMenu.findViewById(R.id.tv_eatingfood);
        tv_warning = dialog_addMenu.findViewById(R.id.tv_warning);
        tv_eatingfood_add = dialog_addMenu.findViewById(R.id.tv_eatingfood_add);
        tv_eatingfood_cancel = dialog_addMenu.findViewById(R.id.tv_eatingfood_cancel);

        adapter_search = new EatingFoodSearchAdapter();

        lv_search = dialog_addMenu.findViewById(R.id.lv_eatingfood_search);
        lv_search.setAdapter(adapter_search);

        // 파이어베이스에 있는 데이터의 "name"을 리스트에 추가
        foodRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot s : task.getResult()){
                        FoodInfo food = s.toObject(FoodInfo.class);
                        food.setDocumentId(s.getId());

                        String food_name = food.getName();
                        adapter_search.addItem(food_name);
                    }
                } else {
                    Toast.makeText(AddMenuActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
                addMenu("add");
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
    public void addMenu(String menuCase){
        // 다이얼로그 불러오기
        dialog_addMenu.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_addMenu.show();

        /*switch (menuCase){
            case "add" :
                tv_eatingfood.setText("추가할 메뉴를 입력해주세요");
                break;

            case "modify" :
                tv_eatingfood.setText("이 메뉴를 수정하시겠습니까?");
                break;
        }*/

        et_add_menu.setText("");
        tv_warning.setText("");

        // 입력창
        et_add_menu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String filterText = s.toString();
                ((EatingFoodSearchAdapter)lv_search.getAdapter()).getFilter().filter(filterText);
            }
        });

        // Add 버튼 -> 공백이 아닐 경우 메뉴를 추가하고 다이얼로그 종료
        tv_eatingfood_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String menu = et_add_menu.getText().toString();

                if(menu.length() != 0){
                    if(flag){
                        data_menu.add(menu);
                        adapter_menu.notifyDataSetChanged();
                        flag = false;

                        dialog_addMenu.cancel();
                    }else{
                        tv_warning.setText("※ 음식 리스트 중 하나를 선택해주세요");
                    }

                }else{
                    tv_warning.setText("※ 음식 리스트 중 하나를 선택해주세요");
                    //Toast.makeText(AddMenuActivity.this, , Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Cancel 버튼 -> 다이얼로그 종료
        tv_eatingfood_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_addMenu.cancel();
            }
        });
    }

    /**
     * 선택한 리스트뷰의 텍스트를 텍스트에디터에 전달하는 함수
     * - EatingFoodAdapter에서 사용
     * @param clicked_food_name
     */
    public void messageFoodName(String clicked_food_name){
        et_add_menu.setText(clicked_food_name);
        flag = true;
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
                    databaseReference.child("Diet").push().setValue(diet)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
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

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(AddMenuActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
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