package com.example.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class Logo extends AppCompatActivity {
    Handler handler;
    //2초 후에 처리해야 하는 작업을 쓰레드로 정의
    Runnable delayThread = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(Logo.this, HandlerPostTestActivity.class);
            startActivity(intent);
            finish();
            //HandlerPostTestActivity로 전환될때 애니메이션효과
            //다른 작업을 처리 - 네트워크 연결해서 서버의 디비데이터를 받아오기,네트워크 연결해서 리스닝
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        handler = new Handler(Looper.myLooper());
        handler.postDelayed(delayThread, 2000);
    }
}