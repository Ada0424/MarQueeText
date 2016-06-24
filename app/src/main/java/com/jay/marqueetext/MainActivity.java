package com.jay.marqueetext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jay.marqueetext.widget.MarqueeText;

public class MainActivity extends AppCompatActivity {
    private MarqueeText mMarqueeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    private void findViews() {
        mMarqueeText=(MarqueeText) findViewById(R.id.marqueeText);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.start_Button:
                mMarqueeText.startScroll();
                break;
            case R.id.pause_Button:
                mMarqueeText.pauseScroll();
                break;
            case R.id.restart_Button:
                mMarqueeText.restartScroll();
                break;
            default:
                break;
        }
    }
}
