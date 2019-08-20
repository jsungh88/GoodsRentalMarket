package com.example.joanne.practice_layout01;

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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Joanne on 2018-02-07.
 */

public class OrderListActivity extends AppCompatActivity {
    OrderListAdapter adapter;
    String orderName, dateValue, productName, rentdateValue,btnDial;
    String getId, storedArrayString,jsonString,getName;
    UserItemAll login_user;
    SharedPreference opref;
    ArrayList<MyOrderListItem> items;
    //ArrayList<OrderListItem> array;
    Gson gson;

    Type type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderlist_sub);

        opref = new SharedPreference( this );
        gson = new Gson();
        getId = opref.loadPreference( "id" ,"loginuser");
        Log.e( "getId",getId );

        jsonString = opref.loadPreference( getId,"loginuser");
        Type type = new TypeToken<UserItemAll>() {
        }.getType();
        login_user = gson.fromJson( jsonString, type );
        getName = login_user.getName();
        Log.e( "셰어드 불러오기:", "OK" );
        Log.e( "셰어드 불러오기:", login_user.getName() );
        Log.e( "셰어드 불러오기:", getName );



        ListView listView;

        items = new ArrayList<MyOrderListItem>();


        loadItemsFromDB();
        listView = findViewById(R.id.olderList);
        adapter = new OrderListAdapter(  this,R.layout.orderlist_item_sub,items);


        listView.setAdapter(adapter);
        ((OrderListAdapter)listView.getAdapter()).getFilter().filter( getName );

        //상단버튼 - 뒤로가기
       final View backBtn = findViewById(R.id.order_btn_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderListActivity.this, MainActivity.class);
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
        final View homeBtn = findViewById(R.id.order_top_btn_home);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
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

    }//OnCreate 끝

    public void loadItemsFromDB(){
        //각 getId키로 관리되기 떄문에, 로그인 유저 id를 불러온다.


        if(items == null){
            items=new ArrayList<MyOrderListItem>();
        }

        storedArrayString = opref.loadPreference( "all","orderlist" );

            Log.e( "아님 바로 넘어왔니?","ㅇㅇ" );
            type = new TypeToken<ArrayList<MyOrderListItem>>() {
            }.getType();
            items = gson.fromJson( storedArrayString, type );
            Log.e( "items",items.get( 0 ).getOrdername() );
        //}
        Log.e("불러오기:","OK");


//
//        //아이템 생성
//        orderListItem = new OrderListItem();
//        orderListItem.setOrdername("사용자명01");
//        orderListItem.setDateValue("2018-02-05");
//        orderListItem.setProductName("네셔널지오그래픽 여행캐리어 24인치(새상품)");
//        orderListItem.setRentdateValue("1주일");
//        list.add(orderListItem);
//
//        //아이템 생성
//        orderListItem = new OrderListItem();
//        orderListItem.setOrdername("사용자명02");
//        orderListItem.setDateValue("2018-02-05");
//        orderListItem.setProductName("상품명02");
//        orderListItem.setRentdateValue("1개월");
//        list.add(orderListItem);
//
//        //아이템 생성
//        orderListItem = new OrderListItem();
//        orderListItem.setOrdername("사용자명03");
//        orderListItem.setDateValue("2018-02-05");
//        orderListItem.setProductName("상품명03");
//        orderListItem.setRentdateValue("1일");
//        list.add(orderListItem);

//        //아이템 생성
//        orderListItem = new OrderListItem();
//        orderListItem.setOrdername("사용자명04");
//        orderListItem.setDateValue("2018-02-05");
//        orderListItem.setProductName("상품명04");
//        orderListItem.setRentdateValue("1주일");
//        list.add(orderListItem);
//
//        //아이템 생성
//        orderListItem = new OrderListItem();
//        orderListItem.setOrdername("사용자명05");
//        orderListItem.setDateValue("2018-02-05");
//        orderListItem.setProductName("상품명05");
//        orderListItem.setRentdateValue("1개월");
//        list.add(orderListItem);
//
//        //아이템 생성
//        orderListItem = new OrderListItem();
//        orderListItem.setOrdername("사용자명06");
//        orderListItem.setDateValue("2018-02-05");
//        orderListItem.setProductName("상품명06");
//        orderListItem.setRentdateValue("6일");
//        list.add(orderListItem);
//
//        //아이템 생성
//        orderListItem = new OrderListItem();
//        orderListItem.setOrdername("사용자명07");
//        orderListItem.setDateValue("2018-02-05");
//        orderListItem.setProductName("상품명07");
//        orderListItem.setRentdateValue("10일");
//        list.add(orderListItem);

    }
}
