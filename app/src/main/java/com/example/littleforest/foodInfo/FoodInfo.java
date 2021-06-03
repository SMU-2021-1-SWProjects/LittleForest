package com.example.littleforest.foodInfo;

import com.google.firebase.firestore.Exclude;

public class FoodInfo {
    private String documentId;
    // private int foodId;
    private String name; // 음식 이름
    private int calorie; // 열량(kcal)
    private String calUnit; // 열량 단위(kcal)
    private int carbohydrate; // 탄수화물(g)
    private int sugar; //당(g)
    private int protein; // 단백질(g)
    private int cholesterol; // 콜레스테롤(g)
    private int dietaryFiber; // 식이섬유(g)
    private int na; // 나트륨(mg)
    private int k; // 칼륨(mg)

    // 추가 성분
    private int servingSize; // 1회 제공량, 음식 영양성분 기준 단위
    private String servingUnit; // 1회 제공량 단위
    private int fat;// 지방(g)

    public FoodInfo() {
        //public no-arg constructor needed
    }

    public FoodInfo(String name, int calorie, String calUnit, int carbohydrate,
                    int sugar, int protein, int cholesterol, int dietaryFiber, int na, int k,
                    int servingSize, String servingUnit, int fat){

        this.name = name;
        this.calorie = calorie;
        this.calUnit = calUnit;
        this.carbohydrate = carbohydrate;
        this.sugar = sugar;
        this.protein = protein;
        this.cholesterol = cholesterol;
        this.dietaryFiber = dietaryFiber;
        this.na = na;
        this.k = k;

        this.servingSize = servingSize;
        this.servingUnit = servingUnit;
        this.fat = fat;
    }

    @Exclude
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public String getCalUnit() {
        return calUnit;
    }

    public void setCalUnit(String calUnit) {
        this.calUnit = calUnit;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(int cholesterol) {
        this.cholesterol = cholesterol;
    }

    public int getDietaryFiber() {
        return dietaryFiber;
    }

    public void setDietaryFiber(int dietaryFiber) {
        this.dietaryFiber = dietaryFiber;
    }

    public int getNa() {
        return na;
    }

    public void setNa(int na) {
        this.na = na;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public String getServingUnit() {
        return servingUnit;
    }

    public void setServingUnit(String servingUnit) {
        this.servingUnit = servingUnit;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }
}
