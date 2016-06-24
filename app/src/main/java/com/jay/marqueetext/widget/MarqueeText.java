package com.jay.marqueetext.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.jay.marqueetext.R;
import com.jay.marqueetext.utils.ScreenUtils;


/**
 * Created by Jay on 2016/6/23 0023.
 */
public class MarqueeText extends TextView implements Runnable {
    public static final int MT_GO_LEFT = 0;
    public static final int MT_GO_RIGHT = 1;
    public static final int MT_GO_ROLLED = 2;
    //文字宽度
    private int contentWidth = 0;
    //水平位移
    private int scrollToX = 0;
    private boolean isStop = false;
    private boolean isRun = true;
    private boolean isMeasureContentWidth = false;
    private boolean goRight = true;
    private int mode = 0;
    //默认速度：50
    private int speed = 50;

    private Context context;

    public MarqueeText(Context context) {
        super(context);
        this.context = context;
    }

    public MarqueeText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.MarqueeText);
        mode = typedArray.getInt(R.styleable.MarqueeText_mode,0);
        speed = typedArray.getInt(R.styleable.MarqueeText_speed,50);
    }

    public MarqueeText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MarqueeText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    @Override

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //只需获取一次文字宽度
        if (!isMeasureContentWidth) {
            getTextWidth();
            isMeasureContentWidth = true;
        }
    }

    //获取文字长度
    private void getTextWidth() {
        Paint paint = this.getPaint();
        String content = this.getText().toString();
        contentWidth = (int) paint.measureText(content);
    }

    @Override
    public void run() {
        if (isRun ) {
            switch(mode){
                case MT_GO_LEFT:
                    go_left();
                    break;
                case MT_GO_RIGHT:
                    go_right();
                    break;
                case MT_GO_ROLLED:
                    go_rolled();
                    break;
            }
            start();
        }
    }

    //来回滚动
    private void go_rolled(){
        if(goRight){
            //向右平移
            scrollToX = scrollToX - 5;
        }else{
            //向左位移
            scrollToX = scrollToX + 5;
        }
        if (scrollToX < -ScreenUtils.getScreenWidth(context)) {
            goRight = false;
        }else if(scrollToX >= 0){
            goRight = true;
        }
    }

    private void go_left(){
        //向左位移
        scrollToX = scrollToX + 5;
        //左移不在显示时，重新设置文字的位置，继续移动
        if(scrollToX > contentWidth){
            scrollToX = -ScreenUtils.getScreenWidth(context);
        }
    }

    private void go_right(){
        //向右位移
        scrollToX = scrollToX - 5;
        //右移不在显示时，重新设置文字的位置，继续移动
        if(scrollToX < -ScreenUtils.getScreenWidth(context)){
            scrollToX = contentWidth;
        }
    }

    private void start(){
        System.out.println("X位移:"+scrollToX);
        System.out.println("内容大小:"+contentWidth);
        System.out.println("屏幕宽度:"+ScreenUtils.getScreenWidth(context));
        scrollTo(scrollToX, 0);
        postDelayed(this, speed);
    }

    // 点击开始,执行线程
    public void startScroll() {
        isRun = true;
        post(this);
    }

    // 点击暂停
    public void pauseScroll() {
        isRun = false;
    }

    // 点击重新开始
    public void restartScroll() {
        isRun = true;
        scrollToX = 0;
        startScroll();
    }
}
