package com.example.littleforest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

    public class searchfood extends AppCompatActivity
    {
        DatabaseReference mref;
        private ListView listdata;
        private AutoCompleteTextView txtsearch;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_searchfood);

            mref = FirebaseDatabase.getInstance().getReference("Food");
            listdata=(ListView)findViewById(R.id.listData);
            txtsearch=(AutoCompleteTextView)findViewById(R.id.txtsearch);
            ValueEventListener event = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot) {
                    populateSearch(snapshot);
                }

                @Override
                public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {

                }
            };

            mref.addListenerForSingleValueEvent(event);
        }
        private void populateSearch(DataSnapshot snapshot)
        {
            ArrayList<String> names =new ArrayList<>();
            if(snapshot.exists())
            {
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    String name = ds.child("name").getValue(String.class);
                    names.add(name);
                }

                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
                txtsearch.setAdapter(adapter);
                txtsearch.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        String name= txtsearch.getText().toString();
                        searchFood(name);
                    }

                    private void searchFood(String name) {
                        Query query=mref.orderByChild("name").equalTo(name);
                        query.addListenerForSingleValueEvent(new ValueEventListener()
                        {
                            public void onDataChange(@NonNull DataSnapshot snapshot)
                            {
                                if(snapshot.exists())
                                {
                                    ArrayList<String> listfood = new ArrayList<>();
                                    for(DataSnapshot ds:snapshot.getChildren())
                                    {
                                        Food food=new Food(ds.child("name").getValue(String.class)
                                                ,ds.child("goodfood").getValue(String.class)
                                                ,ds.child("badfood").getValue(String.class)
                                                ,ds.child("disease").getValue(String.class));

                                        if(food.getgoodfood() != null)
                                        {
                                            listfood.add(food.getname() + "의 상성음식 \n" + food.getgoodfood());
                                        }
                                        if(food.getbadfood() != null)
                                        {
                                            listfood.add(food.getname() + "의 상극음식 \n" + food.getbadfood());
                                        }
                                        if(food.getdisease() != null)
                                        {
                                            listfood.add(food.getdisease() + "에 좋지 않은 음식입니다.");
                                        }
                                    }
                                    ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,listfood);
                                    listdata.setAdapter(arrayAdapter);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull @NotNull DatabaseError error) {

                            }
                        });
                    }
                    class Food
                    {
                        public Food(String name, String goodfood, String badfood, String disease)
                        {
                            this.name = name;
                            this.goodfood = goodfood;
                            this.badfood = badfood;
                            this.disease = disease;
                        }
                        public Food(){}

                        public String getname() {
                            return name;
                        }

                        public String getgoodfood() {
                            return goodfood;
                        }

                        public String getbadfood() {
                            return badfood;
                        }

                        public String getdisease() {
                            return disease;
                        }

                        public String name;
                        public String goodfood;
                        public String badfood;
                        public String disease;
                    }
                });
            }
            else
            {
                Log.d("Food,", "No data found");
            }
        }
    }