package com.bx.wavelineview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bx.waveview.WaveView;

/**
*@author small white
*@date 2018/6/16
*@fuction 示例
*/
public class MainActivity extends AppCompatActivity {

    private WaveView waveView,waveViewLine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        waveView = findViewById(R.id.waveview);
        waveViewLine = findViewById(R.id.waveviewline);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waveView.startAnimator();
                waveViewLine.startAnimator();
            }
        });

        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waveView.closeAnimator();
                waveViewLine.closeAnimator();
            }
        });
    }
}
