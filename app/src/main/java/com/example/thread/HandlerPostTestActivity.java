package com.example.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//Handler객체를 재 정의하면서 handleMessage를 오버라이딩해서 처리하지 않고 Handler 객체의
//post메소드를 호출하며 UI를 변경하는 쓰레드를 직접 만들어서 넘겨주는 방법
public class HandlerPostTestActivity extends AppCompatActivity {
    ImageView iv;
    Button btn1;
    Button btn2;
    TextView nv;
    Handler handler;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_exam_post);
        init();
        iv.setImageResource(R.drawable.d1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new NumThread().start();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ImageThread().start();
            }
        });
        handler = new Handler(Looper.myLooper());
    }

    //btn2를 누르면 이미지를 교차해서 보여지도록(홀수 짝수에 따라) 쓰레드 적용하기
    //숫자를 만드는 쓰레드를 생성
    //ImageThread
    class NumThread extends Thread {
        public void run() {
            for (int i = 1; i <= 10; i++) {
                //핸들러에게 UI를 변경하는 쓰레드를 전달하면서 요청
                num = i;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //TextView를 변경하는 작업 처리
                        nv.setText(num + "");
                    }
                });
                SystemClock.sleep(500);
            }
        }
    }


    class ImageThread extends Thread {
        public void run() {
            for (int i = 1; i <= 100; i++) {
                if (i % 2 == 0) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            iv.setImageResource(R.drawable.d1);
                        }
                    });
                    SystemClock.sleep(1000);
                } else if (i % 2 != 0) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            iv.setImageResource(R.drawable.d2);
                        }
                    });
                    SystemClock.sleep(1000);
                }
            }
        }
    }

        public void init() {
            btn1 = findViewById(R.id.button);
            btn2 = findViewById(R.id.button2);
            iv = findViewById(R.id.imageView);
            nv = findViewById(R.id.numView);
        }

}

