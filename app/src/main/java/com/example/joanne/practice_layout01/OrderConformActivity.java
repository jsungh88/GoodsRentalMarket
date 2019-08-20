package com.example.joanne.practice_layout01;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Joanne on 2018-02-25.
 */

public class OrderConformActivity extends AppCompatActivity implements Button.OnClickListener {


    String num, rentDate, user, image, dial, sms, proName, rent, rentGijun, deposit, rentName, email;
    String getNum, getRentDate, getUser, getDial, getSms, getProName, getRent, getRentGijun, getDeposit, getUserName, getUserEmail,getUserContact;
    SharedPreference cpref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.orderconfirm_sub );

        final View backBtn = findViewById( R.id.order_btn_back );
        backBtn.setOnClickListener( this );
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


        final View homeBtn = findViewById( R.id.order_top_btn_home );
        homeBtn.setOnClickListener( this );
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
        final View sms = findViewById( R.id.draft_btn_sms );
        sms.setOnClickListener( this );
        sms.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                sms.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                sms.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                sms.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );
        final View dial = findViewById( R.id.draft_btn_dial );
        dial.setOnClickListener( this );
        dial.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dial.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                dial.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                dial.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );
        final Button myorderlist = findViewById( R.id.draft_btn_order );
        myorderlist.setOnClickListener( this );
        myorderlist.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                myorderlist.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                myorderlist.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                myorderlist.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );
        final Button main = findViewById( R.id.draft_btn_main );
        main.setOnClickListener( this );
        main.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                main.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                main.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                main.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );

        TextView ordernum = findViewById( R.id.draft_ordernum );
        TextView ordername = findViewById( R.id.draft_ordername );
        TextView orderdate = findViewById( R.id.draft_orderdate );
        TextView productname = findViewById( R.id.draft_productname );
        //TextView rentdate = findViewById( R.id.draft_rentdate );
        TextView gigan = findViewById( R.id.draft_gigan );
        TextView rent = findViewById( R.id.draft_rent );
        TextView deposit = findViewById( R.id.draft_deposit );
        TextView lendor = findViewById( R.id.draft_lendor );
        TextView email = findViewById( R.id.draft_email );
        TextView contact = findViewById( R.id.draft_contact );

        cpref= new SharedPreference( this );

        getNum = cpref.loadPreference( "num","confirm" );
        getRentDate=cpref.loadPreference( "date","confirm" );
        getUser=cpref.loadPreference( "user","confirm" );
        getDial=cpref.loadPreference( "dial","confirm" );
        getSms=cpref.loadPreference( "sms","confirm" );
        getProName=cpref.loadPreference( "name","confirm" );
        getRent=cpref.loadPreference( "rent","confirm" );
        getRentGijun=cpref.loadPreference( "rentgijun","confirm" );
        getDeposit=cpref.loadPreference( "deposit","confirm" );
        getUserName=cpref.loadPreference( "lendor","confirm" );
        getUserEmail=cpref.loadPreference( "email","confirm" );
        getUserContact=cpref.loadPreference( "lendorcontact","confirm" );
        //cpref.loadPreference( "sImage","confirm" );

        ordernum.setText( getNum );
        Log.e("getNum",getNum);

        orderdate.setText( getRentDate );
        Log.e("getRentDate",getRentDate);

        ordername.setText( getUser );
        Log.e("getUser",getUser);

        contact.setText( getDial );
        Log.e("getDial",getDial);

        Log.e("getSms",getSms);

        productname.setText( getProName );
        Log.e("getProName",getProName);

        rent.setText( getRent );
        Log.e("getRent",getRent);

        gigan.setText( getRentGijun );
        Log.e("getRentGijun",getRentGijun);

        deposit.setText( getDeposit );
        Log.e("getDeposit",getDeposit);

        lendor.setText( getUserName );
        Log.e("getRentName",getUserName);

        email.setText( getUserEmail );
        Log.e("getRentName",getUserEmail);

        contact.setText( getUserContact );
        Log.e("getUserContact",getUserContact);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //대여해주는사람에게 SMS 전송
            case R.id.draft_btn_sms:
                Uri uri = Uri.parse( "smsto:"+getUserContact );
                Intent sms = new Intent( Intent.ACTION_SENDTO, uri );
                sms.putExtra( "sms_body", "[물품대여마켓] " );
                startActivity( sms );
                break;

            //대여해주는사람에게 dial 연결
            case R.id.draft_btn_dial:
                Uri uri_dial = Uri.parse( "tel:"+getUserContact );
                Intent dial = new Intent( Intent.ACTION_DIAL, uri_dial );
                startActivity( dial );
                break;

            //내 주문내역 페이지로 이동
            case R.id.draft_btn_order:
                Intent intent = new Intent( this, MyOrderListActivity.class );
                intent.setAction( Intent.ACTION_VIEW );
                startActivity( intent );
                finish();
                break;

            //주문 완료!! 팝업 메시지 이후, 내 주문내역 페이지로 이동
            case R.id.draft_btn_main:
                show();

                break;

            case R.id.order_btn_back:
                onBackPressed();
                Log.e( "뒤로가기" ,"ㅇㅇ");
                overridePendingTransition(R.anim.slide_left, R.anim.hold);
                break;
            case R.id.order_top_btn_home:
                Intent intent_view1 = new Intent( OrderConformActivity.this, MainActivity.class );
                intent_view1.setAction( Intent.ACTION_VIEW );
                startActivity( intent_view1 );
                overridePendingTransition(R.anim.slide_left, R.anim.hold);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle( "대여주문하기" );
        builder.setMessage( "[ "+getProName+" ]을(를) 정말로 대여주문 하시겠습니까?" );
        builder.setPositiveButton( "예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent( OrderConformActivity.this, MyOrderListActivity.class );
                intent.setAction( Intent.ACTION_VIEW );
                startActivity( intent );
                overridePendingTransition(R.anim.slide_left, R.anim.hold);
                finish();


            }
        } );

        builder.setNegativeButton( "아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onBackPressed();
            }
        } );
        builder.show();
    }
}

