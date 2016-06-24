package com.jay.marqueetext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jay.marqueetext.widget.MarqueeText;

public class MainActivity extends AppCompatActivity {
    private MarqueeText marqueeText_left,marqueeText_right,marqueeText_rolled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    private void findViews() {
        marqueeText_left=(MarqueeText) findViewById(R.id.marqueeText_left);
        marqueeText_right=(MarqueeText) findViewById(R.id.marqueeText_right);
        marqueeText_rolled=(MarqueeText) findViewById(R.id.marqueeText_rolled);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.start_Button:
                marqueeText_left.startScroll();
                marqueeText_right.startScroll();
                marqueeText_rolled.startScroll();
                break;
            case R.id.pause_Button:
                marqueeText_left.pauseScroll();
                marqueeText_right.pauseScroll();
                marqueeText_rolled.pauseScroll();
                break;
            case R.id.restart_Button:
                marqueeText_left.restartScroll();
                marqueeText_right.restartScroll();
                marqueeText_rolled.restartScroll();
                break;
            default:
                break;
        }
    }

}
