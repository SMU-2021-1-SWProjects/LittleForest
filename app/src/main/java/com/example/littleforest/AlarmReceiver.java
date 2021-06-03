package com.example.littleforest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context.getApplicationContext(), "약 알람", Toast.LENGTH_SHORT).show();    // AVD 확인용
        Log.e("AlarmReceiver","알람 로그 확인 ");    // 로그 확인용
    }

}
