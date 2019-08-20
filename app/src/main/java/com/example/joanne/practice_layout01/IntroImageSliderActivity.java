package com.example.joanne.practice_layout01;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class IntroImageSliderActivity extends AppCompatActivity {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN = {R.drawable.intro01, R.drawable.intro02, R.drawable.intro03};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();

    private ViewGroup buttonsContainer;
    private Button activeButton = null;
    private final int MAX_BUTTONS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.imageslider_intro );


        //초기화 시켜준다.
        init();

//        this.buttonsContainer = (ViewGroup)findViewById( R.id.buttonsContainer );

//        int buttonsSpacing = (int)getResources().getDimension( R.dimen.activity_horizontal_margin );
//        int buttonSize = (int)getResources().getDimension( R.dimen.button_size );
//
//        for(int i=0; i<MAX_BUTTONS; i++){
//            Button button = (Button)getLayoutInflater().inflate( R.layout. )
//        }


//        Animation animTranslate = AnimationUtils.loadAnimation( this, R.anim.button_elevation );
//        button.startAnimation( animTranslate );


        final Button enter = (Button) findViewById( R.id.btn_enter );

        //overridePendingTransition(R.anim.slide_left, R.anim.hold);
        enter.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN :
                        Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                        enter.startAnimation( anim );
                    case MotionEvent.ACTION_UP :
                        Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                        enter.startAnimation( anim1 );
                }
                return false;
            }
        } );

        enter.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( IntroImageSliderActivity.this, LogInActivity.class );
                intent.setAction( Intent.ACTION_VIEW );
                startActivity( intent );
                finish();
            }
        } );



//        enter.setOnTouchListener( new View.OnTouchListener() {
//            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
////                             int cx =enter.getWidth();
////                int cy =enter.getHeight();
////
////                float finalRadius = Math.max( cx, cy ) + 1.2f;
////                Animator reveal = ViewAnimationUtils.createCircularReveal(enter, (int) event.getX(), (int) event.getY(), 0f, finalRadius );
////
////                reveal.start();
//
//
//                return true;
//            }
//        } );

    }

    private void init() {
        for (int i = 0; i < XMEN.length; i++)
            XMENArray.add( XMEN[i] );

        mPager = (ViewPager) findViewById( R.id.pager );
        mPager.setAdapter( new IntroAdapter( IntroImageSliderActivity.this, XMENArray ) );
        CircleIndicator indicator = (CircleIndicator) findViewById( R.id.indicator );
        indicator.setViewPager( mPager );

        // 뷰페이저 자동 시작
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem( currentPage++, true );
            }
        };

        //백그라운드 스레드에서 2.5초 간격으로 반복 실행을 위한 작업을 2.5초 지연으로 시작하도록 예약한다.
        Timer swipeTimer = new Timer();
        swipeTimer.schedule( new TimerTask() {
            @Override
            public void run() {
                //handler를 사용하여 이미지슬라이더를 업데이트.
                handler.post( Update );
            }
        }, 2500, 2500 );
    }


}