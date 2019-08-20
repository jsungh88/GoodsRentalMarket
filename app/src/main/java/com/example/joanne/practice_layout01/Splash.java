package com.example.joanne.practice_layout01;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

/**
 * Created by Joanne on 2018-02-25.
 */

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.splash_intro );

        ImageView loading = findViewById( R.id.gif_image );
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget( loading );
        Glide.with( this ).load( R.drawable.loadong01 ).into( gifImage );


        Handler hd = new Handler();
        hd.postDelayed( new splashhandler(), 3000 ); //1초 후에 hd handler 실행 3000ms = 3초

    }

    private class splashhandler implements Runnable {

        @Override
        public void run() {
            startActivity( new Intent( getApplication(), MainActivity.class ) ); //로딩이 끝난 후, ChoiceFunction 이동
            Splash.this.finish(); //로딩페이지 Activity stack에서 제거
        }
    }

    @Override
    public void onBackPressed() {
        //초반 플래시 화면에서 넘어갈대 뒤로가기 버튼 못누르게 함
    }


}
