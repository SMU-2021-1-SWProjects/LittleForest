package com.example.littleforest;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FoodSearchActivity extends AppCompatActivity {

    private Button search_btn;
    private List<String> list;
    private ListView listView;
    private EditText editSearch;
    private SearchAdapter adapter;
    private ArrayList<String> arraylist;
    List<FoodList> mfoodList = new ArrayList<FoodList>();
    private List<String> listTitle;
    private ArrayList<String> arraylistfood;

    public class FoodList {
        public String foodname;
        public String goodfood;
        public String badfood;
    }

    private void settingFoodList() {
        FoodList food = new FoodList();

        food.foodname = "돼지고기";
        food.goodfood = "새우젓, 배추, 굴, 호박, 표고버섯";
        food.badfood = "도라지";
        mfoodList.add(food);

        food = new FoodList();

        food.foodname = "소고기";
        food.goodfood = "들깻잎, 팽이버섯, 배, 무, 파인애플, 키위, 참기름";
        food.badfood = "고구마, 부추, 버터";
        mfoodList.add(food);

        food = new FoodList();

        food.foodname = "우유";
        food.goodfood = "식초, 레몬, 닭고기, 생선, 딸기, 옥수수";
        food.badfood = "설탕, 초콜릿";
        mfoodList.add(food);

        food = new FoodList();

        food.foodname = "도라지";
        food.goodfood = "감초, 칡뿌리, 귤껍질, 치자";
        food.badfood = "돼지고기";
        mfoodList.add(food);

        food = new FoodList();

        food.foodname = "고구마";
        food.goodfood = "마, 귤, 김치";
        food.badfood = "땅콩, 소고기";
        mfoodList.add(food);

        food = new FoodList();

        food.foodname = "당근";
        food.goodfood = "기름, 레몬, 강낭콩, 된장";
        food.badfood = "오이, 양배추";
        mfoodList.add(food);


        settingSearchList();
    }

    private void settingSearchList() {
        for (int i = 0; i < mfoodList.size(); i++) {
            list.add(mfoodList.get(i).foodname + "의 상생음식\n" + mfoodList.get(i).goodfood);
            listTitle.add(mfoodList.get(i).foodname);
        }
        for (int i = 0; i < mfoodList.size(); i++) {
            list.add(mfoodList.get(i).foodname + "의 상극음식\n" + mfoodList.get(i).badfood);
            listTitle.add(mfoodList.get(i).foodname);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_serach_result_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기


        editSearch = (EditText) findViewById(R.id.editSearch);
        listView = (ListView) findViewById(R.id.listView);

        list = new ArrayList<String>();

        listTitle = new ArrayList<String>();
        settingFoodList();

        arraylistfood = new ArrayList<String>();
        arraylistfood.addAll(listTitle);

        ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();
        arraylist = new ArrayList<String>();
        arraylist.addAll(list);

        adapter = new SearchAdapter(list, this);

        listView.setAdapter(adapter);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editSearch.getText().toString();
                search(text);
            }
        });
        list.clear();
    }

    public void search(String charText) {

        list.clear();

        if (charText.length() == 0) {
            list.clear();
        }
        else {
            for (int i = 0; i < arraylist.size(); i++) {
                if (arraylistfood.get(i).toLowerCase().contains(charText)) {
                    list.add(arraylist.get(i));
                }
            }
        }
        adapter.notifyDataSetChanged();
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