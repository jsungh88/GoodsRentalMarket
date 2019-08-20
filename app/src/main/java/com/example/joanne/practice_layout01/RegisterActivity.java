package com.example.joanne.practice_layout01;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Joanne on 2018-01-30.
 */

public class RegisterActivity extends AppCompatActivity {

    //셋팅필드
    EditText proName, proRent, proDesc, proDeposit, proLocation;
    TextView proCategory;

    Uri uri;

    //입력항목
    String putCategory, putImage, putName, putRentGijun, putDesc, putLocation, putRent, putDeposit;
    String category, image, name, rent, rentGijun, desc, location, deposit;
    //myshop_edit 경로로 올 경우, adapter수정할때 position값 필요.
    int position_edit;
    //기간 저장
    String period_transfer;

    ImageView proImage;
    Button categoryBtn;

    static boolean modify,edit;

    Button period_day, period_week, period_month;

    //    boolean dayFlag, weekFlag,monthFlag;
    String from,getId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.register_sub );

        // 카테고리, 이미지, 상품명, 설명, 대여료, 기준기간, 보증금, 위치   변환!
        // >>> 카테고리, 이미지, 기간선택 TBD

        proCategory = findViewById( R.id.txt_category );
        proImage = (ImageView) findViewById( R.id.imagearea );
        proName = findViewById( R.id.txt_goodsname_edit );
        proDesc = findViewById( R.id.txtbox_description_edit );
        proRent = findViewById( R.id.txt_rentfee_edit );
        proDeposit = findViewById( R.id.txt_deposit_edit );
        proLocation = findViewById( R.id.txt_location_edit );


        //카테고리 버튼
        final Button categoryBtn = findViewById( R.id.btn_category );
        categoryBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다이아로그 선언(카테고리)
                DialogRadio();
            }
        } );

        categoryBtn.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                categoryBtn.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                categoryBtn.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                categoryBtn.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );

        //        이미지 업로드
        final Button img_upload = findViewById( R.id.btn_image_upload );
        img_upload.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이미지 주소값 가져오기!!!!
                Intent intent = new Intent( Intent.ACTION_PICK );
                intent.setType( MediaStore.Images.Media.CONTENT_TYPE );
                intent.setData( MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
                startActivityForResult( intent, 100 );

            }

        } );

        img_upload.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                img_upload.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                img_upload.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                img_upload.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );

        period_day = findViewById( R.id.btn_period_day );
        period_day.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                period_day.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                period_day.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                period_day.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );

        period_week = findViewById( R.id.btn_period_week );
        period_week.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                period_week.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                period_week.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                period_week.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );

        period_month = findViewById( R.id.btn_period_month );
        period_month.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                period_month.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                period_month.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                period_month.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );

        //RegisterConfirmActivity에서 넘어왔을 떄
        if (rentGijun == "[ 1일 ]") {
            period_day.setBackgroundColor( getApplicationContext().getResources().getColor( R.color.colorAccent ) );
            period_week.setBackgroundColor( getApplicationContext().getResources().getColor( R.color.colorGray ) );
            period_month.setBackgroundColor( getApplicationContext().getResources().getColor( R.color.colorGray ) );
        } else if (rentGijun == "[ 1개월 ]") {
            period_week.setBackgroundColor( getApplicationContext().getResources().getColor( R.color.colorAccent ) );
            period_day.setBackgroundColor( getApplicationContext().getResources().getColor( R.color.colorGray ) );
            period_month.setBackgroundColor( getApplicationContext().getResources().getColor( R.color.colorGray ) );
        } else if (rentGijun == "[ 1주일 ]") {
            period_day.setBackgroundColor( getApplicationContext().getResources().getColor( R.color.colorGray ) );
            period_month.setBackgroundColor( getApplicationContext().getResources().getColor( R.color.colorAccent ) );
            period_week.setBackgroundColor( getApplicationContext().getResources().getColor( R.color.colorGray ) );
        }


        //        대여기간 - 일
        period_day = findViewById( R.id.btn_period_day );
        period_day.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                period_transfer = "[ 1일 ]";
                Intent intent = new Intent( RegisterActivity.this, RegisterConfirmActivity.class );
                intent.putExtra( "peroid", period_transfer );

                period_day.setBackgroundColor( getApplicationContext().getResources().getColor( R.color.colorAccent ) );
                period_week.setBackgroundColor( getApplicationContext().getResources().getColor( R.color.colorGray ) );
                period_month.setBackgroundColor( getApplicationContext().getResources().getColor( R.color.colorGray ) );


            }


        } );


//        period_day.setOnTouchListener( new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//
//                return false;
//            }
//        } );

        //        대여기간 - 주
        period_week = findViewById( R.id.btn_period_week );
        period_week.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                period_transfer = "[ 1주일 ]";
                Intent intent = new Intent( RegisterActivity.this, RegisterConfirmActivity.class );
                intent.putExtra( "peroid", period_transfer );

                period_week.setBackgroundColor( getApplicationContext().getResources().getColor( R.color.colorAccent ) );
                period_day.setBackgroundColor( getApplicationContext().getResources().getColor( R.color.colorGray ) );
                period_month.setBackgroundColor( getApplicationContext().getResources().getColor( R.color.colorGray ) );

            }
        } );
        //        대여기간 - 월
        period_month = findViewById( R.id.btn_period_month );
        period_month.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                period_transfer = "[ 1개월 ]";
                Intent intent = new Intent( RegisterActivity.this, RegisterConfirmActivity.class );
                intent.putExtra( "peroid", period_transfer );

                period_day.setBackgroundColor( getApplicationContext().getResources().getColor( R.color.colorGray ) );
                period_month.setBackgroundColor( getApplicationContext().getResources().getColor( R.color.colorAccent ) );
                period_week.setBackgroundColor( getApplicationContext().getResources().getColor( R.color.colorGray ) );
            }
        } );




        // 확인 버튼
        final Button buttonStartActivity = findViewById( R.id.btn_ok_register );
        buttonStartActivity.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                buttonStartActivity.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                buttonStartActivity.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                buttonStartActivity.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );

        buttonStartActivity.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //마이샵에서왔니? 메인에서 바로 왔니?
                SharedPreferences pref = getSharedPreferences( "from",MODE_PRIVATE);
                from = pref.getString( "from",null );
                Log.e( "from",from );






                // 카테고리, 이미지, 상품명, 설명, 대여료, 기준기간, 보증금, 위치   변환!
                // >>> 카테고리, 이미지, 기간선택 TBD


                //사용자 입력값을 전역 변수에 저장.
                putCategory = category.toString();

                if (modify) {
                    putImage = image;
                } else {
                    putImage = uri.toString();
                }
                putName = proName.getText().toString();
                putDesc = proDesc.getText().toString();
                putRent = proRent.getText().toString();
                //하루,일주일,한달 단위..
                if (modify) {
                    putRentGijun = rentGijun;
                } else {
                    putRentGijun = period_transfer.toString();
                }
                putDeposit = proDeposit.getText().toString();
                putLocation = proLocation.getText().toString();
                Log.e( "putCategory:", putCategory );
                Log.e( "putImage:", putImage );
                Log.e( "putName:", putName );
                Log.e( "putDesc:", putDesc );
                Log.e( "putRent:", String.valueOf( putRent ) );
                Log.e( "putRentGijun:", String.valueOf( putRentGijun ) );
                Log.e( "putDeposit:", String.valueOf( putDeposit ) );
                Log.e( "putLocation:", putLocation );

                //컨펌 화면으로 보내기 (RegisterconfirmActivity)
                Intent intent = new Intent( RegisterActivity.this, RegisterConfirmActivity.class );
                intent.putExtra( "from",from );
                intent.putExtra( "position_edit",position_edit );
                intent.putExtra( "category", putCategory );
                intent.putExtra( "image", putImage );
                intent.putExtra( "name", putName );
                intent.putExtra( "desc", putDesc );
                intent.putExtra( "rent", putRent );
                intent.putExtra( "rentGijun", putRentGijun );
                intent.putExtra( "deposit", putDeposit );
                intent.putExtra( "location", putLocation );

                Log.e( "OK버튼 클릭!", "클릮!!!" );
                Log.e( "position_edit", String.valueOf( position_edit ) );
                Log.e( "from", from );
                Log.e( "category", putCategory );
                Log.e( "image:", putImage );
                Log.e( "name:", putName );
                Log.e( "desc:", putDesc );
                Log.e( "rent:", String.valueOf( putRent ) );
                Log.e( "rentGijun:", String.valueOf( putRent ) );
                Log.e( "rentGijun:", String.valueOf( putDeposit ) );
                Log.e( "location:", putLocation );

                startActivity( intent );
                finish();
            }
        } );


        //만약 RegisterconfirmActivity에서 넘어온 경우, 넘어온 데이터로 setText 한다.
        Intent intent2 = getIntent();
        Log.e( "intent2:", String.valueOf( intent2 ) );
        modify = intent2.getBooleanExtra( "modify", false );
        if (modify||edit) {
            //사용자 입력값을 전역 변수에 저장.
            from = intent2.getStringExtra( "from" );
            category = intent2.getStringExtra( "category" );
            image = intent2.getStringExtra( "image" );
            name = intent2.getStringExtra( "name" );
            desc = intent2.getStringExtra( "desc" );
            rent = intent2.getStringExtra( "rent" );
            rentGijun = intent2.getStringExtra( "rentGijun" );
            deposit = intent2.getStringExtra( "deposit" );
            location = intent2.getStringExtra( "location" );
            //수정할때 보내는 position값.
            position_edit = intent2.getIntExtra(  "position_edit",0);

            Log.e( "from(modi):", from );
            Log.e( "category(modi):", category );
            Log.e( "image(modi):", image );
            Log.e( "name(modi):", name );
            Log.e( "desc(modi):", desc );
            Log.e( "rent(modi):", rent );
            Log.e( "rentGijun(modi):", rentGijun );
            Log.e( "deposit(modi):", deposit );
            Log.e( "location(modi):", location );
            Log.e( "position_edit(modi):", String.valueOf( position_edit ) );

            proCategory.setText( category );
            proImage.setImageURI( Uri.parse( image ) );
            proName.setText( name );
            proDesc.setText( desc );
            putRentGijun = rentGijun;
            proRent.setText( rent );
            proDeposit.setText( deposit );
            proLocation.setText( location );

            Log.e( "에딧텍스트에 데이터 넣기", "성공!!" );
        }


        //        상단버튼 - 뒤로가기,홈이동
        final View top_btn_back = findViewById( R.id.top_btn_back );
        top_btn_back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( RegisterActivity.this, MainActivity.class );
                intent.setAction( Intent.ACTION_VIEW );
                startActivity( intent );
                overridePendingTransition(R.anim.slide_left, R.anim.hold);
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
        final View top_btn_home = findViewById( R.id.top_btn_home );
        top_btn_home.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( RegisterActivity.this, MainActivity.class );
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


//        하단버튼 - 뒤로가기
        final View btn_cancel_register = findViewById( R.id.btn_cancel_register );
        btn_cancel_register.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent( RegisterActivity.this, MainActivity.class );
                intent.setAction( Intent.ACTION_VIEW );
                startActivity( intent );
                finish();
            }
        } );
        btn_cancel_register.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn_cancel_register.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                btn_cancel_register.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                btn_cancel_register.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );



    }//onCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        if (requestCode == 100) {

            try {
                //앨범 URI 변수,
                uri = data.getData();
                proImage.setImageURI( uri );
            } catch (Exception e) {
                Log.e( "이미지", "이미지 등록 취소" );
            }
//
//            try{
//                proImage = MediaStore.Images.Media.getContentUri(this.geturi);
//            }catch (IOException e){
//                e.printStackTrace();
//            }

//            if (requestCode == 100) {
//                //앨범 URI 변수,
//                uri = data.getData();
//                proImage.setImageURI( uri );
//
//                uri = MediaStore.Images.Media.getContentUri( String.valueOf( uri ) );
//
//                Log.e( "이거", String.valueOf( uri ) );
//                proImage.setImageURI( uri );
//
//            }

//            try {
//                bitmap = MediaStore.Images.Media.getBitmap( this.getContentResolver(),uri );
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            Log.e( "이거", String.valueOf( uri ) );
//            imageView.setImageBitmap( bitmap );

        }
    }

    @Override
    protected void onStop() {
        super.onStop();

//        if (data == null)
//            Toast.makeText(this, "내용이 임시저장 되었습니다.", Toast.LENGTH_LONG).show();

    }


    private void DialogRadio() {
        final CharSequence[] arr = {"취미/도서/티켓", "가구/생활/주방/식품", "출산/유아동", "가전/디지털/컴퓨터", "패션/뷰티", "스포츠/레저용품", "명품"};
        AlertDialog.Builder alert = new AlertDialog.Builder( this );
        alert.setTitle( "카테고리 선택" );
        alert.setSingleChoiceItems( arr, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText( getApplicationContext(), arr[which], Toast.LENGTH_SHORT ).show();

                proCategory.setText( arr[which] );
                category = (String) arr[which];
                Log.e( "카테고리선택", category );
                dialog.cancel();
            }
        } );
        AlertDialog aalert = alert.create();
        aalert.show();
    }


}