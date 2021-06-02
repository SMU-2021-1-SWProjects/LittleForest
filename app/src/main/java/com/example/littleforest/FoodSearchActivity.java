package com.example.littleforest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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
            public String disease;
        }

        private void settingSearchList() {
            for (int i = 0; i < mfoodList.size(); i++) {
                if (mfoodList.get(i).goodfood != "") {
                    list.add(mfoodList.get(i).foodname + "의 상생음식\n" + mfoodList.get(i).goodfood);
                    listTitle.add(mfoodList.get(i).foodname);
                } else {
                    continue;
                }
            }
            for (int i = 0; i < mfoodList.size(); i++) {
                if (mfoodList.get(i).badfood != "") {
                    list.add(mfoodList.get(i).foodname + "의 상극음식\n" + mfoodList.get(i).badfood);
                    listTitle.add(mfoodList.get(i).foodname);
                } else {
                    continue;
                }
            }
            for (int i = 0; i < mfoodList.size(); i++) {
                if (mfoodList.get(i).disease == "역류성식도염") {
                    list.add(mfoodList.get(i).disease + "에 좋지 않은 음식입니다.");
                    listTitle.add(mfoodList.get(i).foodname);
                } else {
                    break;
                }
            }
        }

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.food_search_result_page);

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
            } else {
                for (int i = 0; i < arraylist.size(); i++) {
                    if (arraylistfood.get(i).toLowerCase().contains(charText)) {
                        list.add(arraylist.get(i));
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }

        private void settingFoodList() {
            FoodList food = new FoodList();

            food.foodname = "돼지고기";
            food.goodfood = "새우젓, 배추, 굴, 호박, 표고버섯";
            food.badfood = "도라지";
            food.disease = "역류성식도염";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "소고기";
            food.goodfood = "들깻잎, 팽이버섯, 배, 무, 파인애플, 키위, 참기름";
            food.badfood = "고구마, 부추, 버터";
            food.disease = "역류성식도염";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "우유";
            food.goodfood = "식초, 레몬, 닭고기, 생선, 딸기, 옥수수";
            food.badfood = "설탕, 초콜릿";
            food.disease = "역류성식도염";

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


            food = new FoodList();

            food.foodname = "조개";
            food.goodfood = "쑥갓, 시금치";
            food.badfood = "옥수수";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "홍어";
            food.goodfood = "막걸리";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "라임";
            food.goodfood = "진";
            food.badfood = "역류성식도염";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "레몬";
            food.goodfood = "굴";
            food.badfood = "";
            food.disease = "역류성식도염";
            mfoodList.add(food);
            food = new FoodList();

            food.foodname = "된장";
            food.goodfood = "부추";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "가지";
            food.goodfood = "기름";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "선지";
            food.goodfood = "우거지";
            food.badfood = "홍차";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "사과식초";
            food.goodfood = "꿀";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "약밥";
            food.goodfood = "대추";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "콩";
            food.goodfood = "다시마";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "커피";
            food.goodfood = "치즈, 치커리";
            food.badfood = "크림";
            food.disease = "역류성식도염";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "냉면";
            food.goodfood = "식초";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "쌀";
            food.goodfood = "쑥";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "인삼";
            food.goodfood = "벌꿀";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "수정과";
            food.goodfood = "잣";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "호박";
            food.goodfood = "강낭콩";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "녹두묵";
            food.goodfood = "미나리";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "테킬라";
            food.goodfood = "소금";
            food.badfood = "";
            food.disease = "역류성식도염";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "솔잎";
            food.goodfood = "쌀";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "불고기";
            food.goodfood = "들깻잎";
            food.badfood = "";
            food.disease = "역류성식도염";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "초콜릿";
            food.goodfood = "아몬드";
            food.badfood = "우유";
            food.disease = "역류성식도염";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "청주";
            food.goodfood = "은행";
            food.badfood = "";
            food.disease = "역류성식도염";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "스테이크";
            food.goodfood = "파인애플";
            food.badfood = "버터";
            food.disease = "역류성식도염";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "바나나";
            food.goodfood = "파인애플";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "김치";
            food.goodfood = "고구마";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "설렁탕";
            food.goodfood = "깍두기";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "보신탕";
            food.goodfood = "들깨";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "방어";
            food.goodfood = "파슬리, 식용유";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "고등어";
            food.goodfood = "무, 식초, 소금";
            food.badfood = "";
            food.disease = "역류성식도염";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "포도";
            food.goodfood = "포도씨";
            food.badfood = "";
            food.disease = "역류성식도염";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "청국장";
            food.goodfood = "신김치";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "닭고기";
            food.goodfood = "인삼, 단너삼 뿌리, 당귀, 동충하초, 밤, 은행, 해삼, 잉어, 옻, 우유";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "생강";
            food.goodfood = "찹쌀";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "육회";
            food.goodfood = "배";
            food.badfood = "";
            food.disease = "역류성식도염";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "간";
            food.goodfood = "레몬, 우유, 청주, 매실, 식초";
            food.badfood = "수정과";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "달걀";
            food.goodfood = "피망, 시금치, 식초, 청주, 구기자";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "가지";
            food.goodfood = "식물성 기름, 조개";
            food.badfood = "";
            food.disease = "역류성식도염";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "감자";
            food.goodfood = "돼지 콩팥, 우유, 치즈";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "고구마";
            food.goodfood = "마, 귤, 김치";
            food.badfood = "땅콩, 소고기";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "냉이";
            food.goodfood = "질경이, 결명자, 식초";
            food.badfood = "국수";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "달래";
            food.goodfood = "꿀, 식초, 다시마";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "당근";
            food.goodfood = "기름, 레몬, 강낭콩, 된장";
            food.badfood = "오이, 양배추";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "더덕";
            food.goodfood = "고추장, 돼지고기";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "두릅";
            food.goodfood = "초장, 소고기";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "무";
            food.goodfood = "찹쌀, 메밀, 밀, 보리, 무화과, 두부, 꿀, 식초, 생강, 감, 소금, 배추, 양상추, 고등어, 꽁치, 소고기";
            food.badfood = "오이";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "순무";
            food.goodfood = "쌀";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "미나리";
            food.goodfood = "쑥갓, 생선, 복어, 겨자";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "배추";
            food.goodfood = "김치, 된장, 무, 식초, 부추, 갓, 우유, 생강";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "부추";
            food.goodfood = "식초, 사과, 토마토, 후추, 우유, 다시마, 된장, 생강, 돼지고기, 참깨, 재첩, 염소고기, 배추, 장어, 청어";
            food.badfood = "술, 꿀";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "브로콜리";
            food.goodfood = "치즈, 양파, 게, 고기, 달걀, 호두, 기름, 아몬드, 아보카도";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "상추";
            food.goodfood = "생강, 민들레, 오이";
            food.badfood = "꿀";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "양상추";
            food.goodfood = "팥, 무, 파슬리, 샐러리, 닭간";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "샐러리";
            food.goodfood = "후추, 사과, 대추, 당근, 다시마, 콩, 메밀";
            food.badfood = "마요네즈";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "시금치";
            food.goodfood = "조개, 붉은살 생선, 달걀, 참깨, 당근, 우유, 두유, 바나나, 귤, 사과";
            food.badfood = "뱀장어, 멸치, 두부";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "쑥";
            food.goodfood = "당귀, 달걀, 생강, 결명자, 검은콩, 연근, 국화, 율무";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "쑥갓";
            food.goodfood = "두부, 씀바귀, 샐러리, 솔잎, 아스파라거스, 조기, 굴, 미나리, 조개";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "양배추";
            food.goodfood = "자몽, 당근, 식초, 파인애플, 우유, 사과, 오징어";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "양파";
            food.goodfood = "식초, 수삼, 오미자, 간, 고기, 뼈, 당근, 호박, 콩, 사과";
            food.badfood = "";
            food.disease = "역류성식도염";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "연근";
            food.goodfood = "생강, 소금, 아보카도, 요구르트, 인삼, 식초";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "오이";
            food.goodfood = "소금, 식초, 둥굴레, 꿀, 우유, 사과, 술";
            food.badfood = "무";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "우엉";
            food.goodfood = "쌀뜨물, 도라지, 다시마, 육류";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "죽순";
            food.goodfood = "호두, 해삼, 꿀";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "가자미";
            food.goodfood = "좁쌀, 기름";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "갈치";
            food.goodfood = "목이버섯";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "게";
            food.goodfood = "식초, 술, 오미자";
            food.badfood = "감, 꿀";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "굴";
            food.goodfood = "레몬, 식초, 통밀, 돼지고기, 쑥갓, 낙지";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "김";
            food.goodfood = "기름, 소금, 파래, 식초";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "꽁치";
            food.goodfood = "귤, 열무, 청주, 무, 파슬리, 레몬, 검은깨";
            food.badfood = "";
            food.disease = "역류성식도염";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "낙지";
            food.goodfood = "콩나물, 생강, 식초, 굴";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "넙치";
            food.goodfood = "해조류, 아욱";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "다시마";
            food.goodfood = "마늘, 찹쌀, 토란, 검은콩";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "달팽이";
            food.goodfood = "버터, 연꽃";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "대구";
            food.goodfood = "들기름, 마늘, 생강, 쌀, 석류껍질";
            food.badfood = "";
            mfoodList.add(food);
            food = new FoodList();

            food.foodname = "도미";
            food.goodfood = "산수유";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "메기";
            food.goodfood = "참깨, 머위";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "멸치";
            food.goodfood = "우유, 피망, 된장, 미역, 식초, 잣";
            food.badfood = "시금치";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "북어";
            food.goodfood = "밀가루, 쌀뜨물, 콩나물, 더덕, 팥, 새우젓, 미나리";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "메기";
            food.goodfood = "참깨, 머위";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "문어";
            food.goodfood = "생강, 식초, 무";
            food.badfood = "고사리";
            food.disease = "역류성식도염";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "미꾸라지";
            food.goodfood = "우엉, 산초, 양파, 구기자, 설탕, 제피";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "미역";
            food.goodfood = "버섯, 육류, 두부, 참기름, 식초, 멸치";
            food.badfood = "파";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "민어";
            food.goodfood = "쑥";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "병어";
            food.goodfood = "귤껍질, 당귀";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "붕어";
            food.goodfood = "당귀, 구기자, 머위, 팥, 무";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "새우";
            food.goodfood = "생강, 무, 아욱, 파, 돼지고기, 닭고기, 치자";
            food.badfood = "";
            food.disease = "역류성식도염";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "숭어";
            food.goodfood = "매실장아찌, 머위, 파슬리";
            food.badfood = "";
            mfoodList.add(food);


            food = new FoodList();

            food.foodname = "연어";
            food.goodfood = "버터";
            food.badfood = "";
            food.disease = "역류성식도염";
            mfoodList.add(food);


            food = new FoodList();

            food.foodname = "은어";
            food.goodfood = "생강";
            food.badfood = "";
            mfoodList.add(food);


            food = new FoodList();

            food.foodname = "잉어";
            food.goodfood = "팥, 닭고기, 인삼, 유자, 된장";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "부추, 돼지 뼈";
            food.goodfood = "복숭아";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "전갱이";
            food.goodfood = "생강, 무";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "전복";
            food.goodfood = "소고기, 냉이, 참깨, 마늘, 치자";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "청어";
            food.goodfood = "부추, 마늘";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "해삼";
            food.goodfood = "인삼, 닭고기, 죽순";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "해파리";
            food.goodfood = "토란, 무, 설탕, 참기름";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "홍합";
            food.goodfood = "바지락, 육류, 해조류";
            food.badfood = "";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "맥주";
            food.goodfood = "";
            food.badfood = "땅콩";
            food.disease = "역류성식도염";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "토마토";
            food.goodfood = "";
            food.badfood = "설탕";
            food.disease = "역류성식도염";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "감";
            food.goodfood = "";
            food.badfood = "도토리묵";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "수박";
            food.goodfood = "";
            food.badfood = "튀김";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "바지락";
            food.goodfood = "";
            food.badfood = "우엉";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "메밀";
            food.goodfood = "";
            food.badfood = "우렁이";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "팥";
            food.goodfood = "";
            food.badfood = "소다";
            mfoodList.add(food);

            food = new FoodList();

            food.foodname = "치즈";
            food.goodfood = "";
            food.badfood = "콩류";
            mfoodList.add(food);

            settingSearchList();
        }
}