package com.hgz.test.customviewcirclerotate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hgz.test.customviewcirclerotate.view.MyCircle;

public class MainActivity extends AppCompatActivity {

    private int color = 0xffff0000;
    private MyCircle myCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myCircle = (MyCircle) findViewById(R.id.circle);
        Button btnChangeCircleColor = (Button) findViewById(R.id.btn_change_circle_color);
        Button btnJiaSu = (Button) findViewById(R.id.btn_jiasu);
        Button btnJianSu = (Button) findViewById(R.id.btn_jiansu);
        Button pause= (Button) findViewById(R.id.btn_pause);
        btnChangeCircleColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCircle.setColor(color);
            }
        });
        btnJiaSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCircle.speed();
            }
        });
        btnJianSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCircle.slowDown();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCircle.pause();
            }
        });
    }
}
