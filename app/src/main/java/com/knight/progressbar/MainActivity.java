package com.knight.progressbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private HorizontalProgressBar horizontalProgress;
    private VerticalProgressBar verticalProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        horizontalProgress = findViewById(R.id.horizontalProgress);
        verticalProgress = findViewById(R.id.verticalProgress);
        runH();
        runV();
    }


    /**
     * 测试progressbar
     */
    private void runH() {
        new Thread() {
            public void run() {
                try {
                    for (int i = 0; i <= 100; i++) {
                        Thread.sleep(50);//休息50毫秒
                        horizontalProgress.setProgress(i);//更新进度条进度
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void runV() {
        new Thread() {
            public void run() {
                try {
                    for (int i = 0; i <= 100; i++) {
                        Thread.sleep(50);//休息50毫秒
                        verticalProgress.setProgress(i);//更新进度条进度
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
