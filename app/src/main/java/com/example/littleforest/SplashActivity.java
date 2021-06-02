package com.example.littleforest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

//메인 로딩 화면

public class SplashActivity extends Activity {

    protected void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        Intent page = new Intent(this, MainActivity.class);

        startActivity(page);
        finish();

    }

}
