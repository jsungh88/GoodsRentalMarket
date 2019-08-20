package com.example.joanne.practice_layout01;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Joanne on 2018-02-07.
 */

public class FavoriteListActivity extends AppCompatActivity {

    SharedPreference fpref;
    Gson gson;
    Type type;
    String storedArrayString,getId,jsonString,getName;
    ArrayList<FavoriteListItem> array;
    FavoriteListItem item;
    int size;
    String image, name, rent, rentGijun, deposit;
    FavoriteListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.favoritelist_sub );

        fpref = new SharedPreference( this );
        gson = new Gson();
        getId = fpref.loadPreference( "id" ,"loginuser");
        Log.e( "getId",getId );

//        jsonString = opref.loadPreference( getId,"loginuser");
//        Type type = new TypeToken<UserItemAll>() {
//        }.getType();
//        login_user = gson.fromJson( jsonString, type );
//        getName = login_user.getName();
//        Log.e( "셰어드 불러오기:", "OK" );
//        Log.e( "셰어드 불러오기:", login_user.getName() );
//        Log.e( "셰어드 불러오기:", getName );

        fpref = new SharedPreference( this );
        gson = new Gson();

        item = new FavoriteListItem();
        array = new ArrayList<FavoriteListItem>();

        final ListView listView;


        loadItemFromDB();
        listView = (ListView) findViewById( R.id.favoriteList );
        adapter = new FavoriteListAdapter( this, R.layout.favoritelist_item_sub, array );

        listView.setAdapter( adapter );
        ((FavoriteListAdapter)listView.getAdapter()).getFilter().filter( getId );

        //상단버튼 - 뒤로가기
        final View top_btn_back = findViewById( R.id.favo_btn_back );
        top_btn_back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( FavoriteListActivity.this, MainActivity.class );
                intent.setAction( Intent.ACTION_VIEW );
                startActivity( intent );
                finish();
            }
        } );
        top_btn_back.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                top_btn_back.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                top_btn_back.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                top_btn_back.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );
        //상단버튼 - 홈
        final View top_btn_home = findViewById( R.id.favo_top_btn_home );
        top_btn_home.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( FavoriteListActivity.this, MainActivity.class );
                intent.setAction( Intent.ACTION_VIEW );
                startActivity( intent );
                finish();
            }
        } );
        top_btn_home.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                top_btn_home.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                top_btn_home.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                top_btn_home.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );
        //전체선택
        final View selectall = findViewById( R.id.favo_select );
        selectall.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //클릭하면 전체선택 된다.
                //전체선택 되어있을 때 다시 클릭하면 선택 해제된다.

//                for ( int i=0; i< adapter.getCount(); i++ ) {
//                    listView.setItemChecked(i, true);
//                }



            }
        } );
        selectall.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                selectall.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                selectall.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                selectall.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );
        //삭제
        final View delete = findViewById( R.id.favo_delete );
        delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //체크박스 되어있을 때 클릭하면 삭제된다.
                //체크박스 표시되어 있으면, adapter에서 선택한 position 아이템 삭제.

                //그리고 notifychanged 해준다.

                //다시 셰어드에 저장한다.
                Log.e( "어디가문제야?","1" );

                for (int i = 0; i < adapter.filtered_array.size(); i++) {

                    Log.e( "어디가문제야?","2" );
                    Log.e( "어레이사이즈가 몇인데?", String.valueOf( adapter.filtered_array.size() ) );
                    item = (FavoriteListItem) adapter.getItem( i );
                    Log.e( "어디가문제야?","3" );
                    if (item.isChecked) {
                        Log.e( "어디가문제야?","4" );
                        array.remove( i );
                        Log.e( "어디가문제야?","5" );
                        listView.setAdapter( adapter );
                        adapter.notifyDataSetChanged();
                        String arrayJson = gson.toJson( array );
                        fpref.removePreference( "fitem", "favorite" );
                        fpref.savePreference( "fitem", arrayJson, "favorite" );
                    }
                    Log.e( "어디가문제야?","6" );
                }
                Toast.makeText( FavoriteListActivity.this, "선택한 상품이 삭제되었습니다.", Toast.LENGTH_SHORT ).show();
                loadItemFromDB();
                adapter = new FavoriteListAdapter( getApplicationContext(), R.layout.favoritelist_item_sub, array );
                listView.setAdapter( adapter );
                ((FavoriteListAdapter)listView.getAdapter()).getFilter().filter( getId );
            }
        } );
        delete.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                delete.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                delete.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                delete.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );

        gson = new Gson();

    }//OnCreate 끝


    @SuppressLint("WrongConstant")
    public void loadItemFromDB() {

        if (array == null) {
            array = new ArrayList<FavoriteListItem>();
        }else {
            storedArrayString = fpref.loadPreference( "fitem", "favorite" );
            type = new TypeToken<ArrayList<FavoriteListItem>>() {
            }.getType();
            array = gson.fromJson( storedArrayString, type );
            Log.e( "저장완료?:", "OK" );
            for(int i=0; i<array.size(); i++){
                Log.e( "array_subject",array.get( i ).getLoginId());
                Log.e( "array_subject",array.get( i ).getSubject());
            }

           // Log.e( "array", array.get( 0 ).getLoginId() );
            //Log.e( "array", array.get( 0 ).getSubject() );
        }

    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Toast.makeText(FavoriteListActivity.this,"onResume",Toast.LENGTH_SHORT).show();
//
//    }
//
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        Toast.makeText(FavoriteListActivity.this,"onStart",Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Toast.makeText(FavoriteListActivity.this,"onRestart",Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Toast.makeText(FavoriteListActivity.this,"onPause",Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Toast.makeText(FavoriteListActivity.this,"onStop",Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(FavoriteListActivity.this,"onDestroy",Toast.LENGTH_SHORT).show();
//    }

}
