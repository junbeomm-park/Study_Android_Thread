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
import android.widget.TextView;
import android.widget.Toast;

public class HandlerExamActivity01 extends AppCompatActivity {
    TextView textView;
    Button msgbtn;
    Handler handler1;
    Handler msghandler;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_exam);
        textView = findViewById(R.id.numView);
        msgbtn = findViewById(R.id.msgbtn);
        handler1 = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                textView.setText("진행률"+num+"%");
                if(num==10){
                    Toast.makeText(HandlerExamActivity01.this,
                            "작업이 완료", Toast.LENGTH_LONG).show();
                }
            }
        };

        msghandler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(msg.what==1){
                    textView.setText("진행률"+msg.arg1+"%");
                    if(msg.arg1==10) {
                        Toast.makeText(HandlerExamActivity01.this,
                                "작업이 완료", Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
        msgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=1;i<=10;i++){
                            //변경할 뷰의 정보나 handler에게 전달할 데이터를 직접
                            //Message객체로 만들어서 handler에게 전달
                            Message msg = new Message();
                            //Message객체의 what은 handler에게 작업을 의뢰한 쓰레드로 구분하기 위한 값을 전달
                            msg.what = 1;
                            msg.arg1 = i; //전달할 데이터
                            //Message객체를 전달하며 handler에게 작업을 의뢰뢰
                           msghandler.sendMessage(msg);
                            SystemClock.sleep(1000);
                        }
                    }
                }).start();
            }
        });
    }


    public void btn_click(View view){
        new Thread(new MyThread2()).start();
    }

    class MyThread2 implements Runnable{

        @Override
        public void run() {
            for(int i=1;i<=10;i++){
                num = i;
                handler1.sendMessage(handler1.obtainMessage());
                SystemClock.sleep(1000);
            }
        }
    }
}