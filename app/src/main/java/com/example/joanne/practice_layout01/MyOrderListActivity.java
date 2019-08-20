package com.example.joanne.practice_layout01;

import android.content.Intent;
import android.content.SharedPreferences;
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
 * Created by Joanne on 2018-02-12.
 */

public class MyOrderListActivity extends AppCompatActivity {

    SharedPreference opref;

    String num,rentDate,user,image,dial,sms,proName,rent,rentGijun,deposit,rentName,email;
    Gson gson;
    Type type;
    String storedArrayString;
    ArrayList<MyOrderListItem> items;
    MyOrderListItem item;
    MyOrderListAdapter adapter;
    String getId;
    ListView listView;
    public boolean loadItemsFromDB(){
        //각 getId키로 관리되기 떄문에, 로그인 유저 id를 불러온다.
        getId = opref.loadPreference( "id" ,"loginuser");
        Log.e( "getId",getId );

        if(items == null){
            items = new ArrayList<MyOrderListItem>(  );
        }else {
            storedArrayString = opref.loadPreference( getId, "orderlist" );
            type = new TypeToken<ArrayList<MyOrderListItem>>() {
            }.getType();
            items = gson.fromJson( storedArrayString, type );
            Log.e( "불러오기:", "OK" );
            Log.e( "getId", getId );
            Log.e("items", String.valueOf(items));
        }


//        MyOrderListItem myOrderListItem;
//        if(list == null){
//            list=new ArrayList<MyOrderListItem>();
//        }
//        myOrderListItem = new MyOrderListItem();
//        myOrderListItem.setNum( num );
//        myOrderListItem.setRentDate( rentDate );
//        myOrderListItem.setOrdername( user );
//        myOrderListItem.setSimage(image);
//        myOrderListItem.setBtnDial( dial );
//        myOrderListItem.setBtnSms( sms );
//        myOrderListItem.setProductName( proName );
//        myOrderListItem.setRentValue( rent );
//        myOrderListItem.setRentGigan( rentGijun );
//        myOrderListItem.setDeposit( deposit );
//        myOrderListItem.setRentname( rentName );
//        myOrderListItem.setEmail( email );
//        list.add( myOrderListItem );



        return true;

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myorderlist_sub);


        items = new ArrayList<MyOrderListItem>(  );
        item = new MyOrderListItem(  );

        //각 getId키로 관리되기 떄문에, 로그인 유저 id를 불러온다.
        SharedPreferences user = getSharedPreferences( "loginuser", MODE_PRIVATE );
        String id = user.getString( "id",null );
        Log.e( "getId",id );

        opref = new SharedPreference(this);
        gson = new Gson();
        loadItemsFromDB();

        adapter = new MyOrderListAdapter(this,R.layout.myorderlist_item_sub,items);
        listView = findViewById(R.id.myorderList);
        listView.setAdapter( adapter );

//        View empty = findViewById( R.id.empty);
//        listView.setEmptyView( empty );
//        if(items.size()>0){
//            listView.setAdapter( adapter );
//        }else{
//            empty.setVisibility( View.VISIBLE );
//        }


//        if(listView==null) {
//            listView.setEmptyView( empty );
//        }
        //상단버튼 - 뒤로가기
        final View backBtn = findViewById(R.id.myorder_btn_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyOrderListActivity.this, MainActivity.class);
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                finish();
            }
        });
        backBtn.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                backBtn.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                backBtn.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                backBtn.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );
        //상단버튼 - 홈
        final View homeBtn = findViewById(R.id.myorder_top_btn_home);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyOrderListActivity.this, MainActivity.class);
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                finish();
            }
        });
        homeBtn.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                homeBtn.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                homeBtn.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                homeBtn.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );
        //전체선택
        final View selectall = findViewById( R.id.myorder_select );
        selectall.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //클릭하면 전체선택 된다.
                //전체선택 되어있을 때 다시 클릭하면 선택 해제된다.


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
        final View delete = findViewById( R.id.myorder_delete );
        delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //체크박스 되어있을 때 클릭하면 삭제된다.
                //체크박스 표시되어 있으면, adapter에서 선택한 position 아이템 삭제.

                //그리고 notifychanged 해준다.
                //다시 셰어드에 저장한다.

                for (int i = 0; i < items.size(); i++) {
                    item = (MyOrderListItem) adapter.getItem( i );
                    if (item.isChecked) {
                        items.remove( i );
                    }
                }
                listView.setAdapter( adapter );
                adapter.notifyDataSetChanged();

                String arrayJson = gson.toJson( items );
                opref.savePreference( getId, arrayJson, "orderlist" );

                Toast.makeText( MyOrderListActivity.this, "선택한 주문건이 삭제되었습니다.", Toast.LENGTH_SHORT ).show();


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

    }//OnCreate 끝

//    @Override
//    public void onContentChanged() {
//        super.onContentChanged();
//
//        View empty = findViewById(R.id.empty);
//        listView.setEmptyView(empty);
//    }
}
