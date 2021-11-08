package com.example.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class HandlerExamActivity02 extends AppCompatActivity {
    ImageView iv;
    Button btn1;
    Button btn2;
    TextView tv;
    ProgressBar pb;
    Handler handler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_exam2);
        init();
        //d1.jpg를 액티비티가 실행되면 ImageView에 설정하기
        iv.setImageResource(R.drawable.d1);

        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {

                    pb.setProgress(msg.arg1);
                    iv.setImageResource(R.drawable.d1);

                    if (msg.arg1 == 100) {
                        Toast.makeText(HandlerExamActivity02.this,
                                "작업이 완료", Toast.LENGTH_LONG).show();
                    }
                }else if(msg.what == 2){
                    pb.setProgress(msg.arg1);
                    iv.setImageResource(R.drawable.d2);

                    if (msg.arg1 == 100) {
                        Toast.makeText(HandlerExamActivity02.this,
                                "작업이 완료", Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=1;i<=100;i++){
                            if(i%2==0){
                                Message msg = new Message();
                                msg.what = 1;
                                msg.arg1 = i;
                                handler.sendMessage(msg);
                                SystemClock.sleep(1000);
                            }else if(i%2!=0){
                                Message msg = new Message();
                                msg.what = 2;
                                msg.arg1 = i;
                                handler.sendMessage(msg);
                                SystemClock.sleep(1000);
                            }
                        }
                    }
                }).start();
            }
        });
    }
    public void init() {
        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn2.setEnabled(false);
        iv = findViewById(R.id.imageView);
        tv = findViewById(R.id.textView);
        pb = findViewById(R.id.progressBar);
    }


}