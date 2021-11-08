package com.example.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        progressBar =  findViewById(R.id.progressBar);
        textView =   findViewById(R.id.textView);

       
    }

  
    public void btnNoThread(View view){
       
    }
   
    public void useThread(View view){
       
    }
    
    public void useHandler(View view){
        
    }
   
    public void useMessageHandler(View view){
        
    }
}







