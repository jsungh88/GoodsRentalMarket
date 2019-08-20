package com.example.joanne.practice_layout01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Joanne on 2018-01-31.
 */


public class RegisterConfirmActivity extends AppCompatActivity {

    static boolean modify;
    static final public int RequestCode = 1;

    //xml 셋팅 필드
    ImageView image;
    TextView category,txtimage, name, rent, rentGijun, deposit, desc, location,state;

    Uri uri;

    String putState="대여가능";
    String putFavCount="0";

    //입력항목
    String putCategory, putImage, putName, putRentGijun, putDesc, putLocation, putRent, putDeposit,putDate;

    //intent를 통해 가져오는 필드
    String getCategory, getImage, getName, getRentGijun, getDesc, getLocation, getRent, getDeposit;
    //수정시 보내는 position값
    int position;

    //하나의 상품정보를 .ArrayList에 저장.-shared 불러와서 저장하는 용도.
    ArrayList<Product> array;

    //상품 저장객체
    Product item;
    //ProductRegit item;

    //Gson을 이용해 json 형태로 변환.
    Gson gson;

    //arrayJason: arrayItems가 저장된 변수 , storedArrayString:json->string으로 load된 변수,
    String arrayJson, storedArrayString;
    String arrayJson1, storedArrayString1;


    //sharedpreference 객체
    SharedPreference pref;
    Type type;

    String period_transfer = "[ 1일 ]";//"[ 1개월 ]"//"[ 1주일 ]"

    static final String TAG = "RegisterComfirmActivity";

    //마이샵에서 왔니? 메인에서 바로 왔니? shared 전달 값.
    String from;


    //등록일자
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat;

    //로그인유저 id 변수
    String loginUserId;
    //로그인 shared정보 풀때 사용하는 json 변수.
    String jsonString, storedString;
    UserItemAll login_user;

    String userImage,userName,userId,userPw,userEmail,userContact;
    SharedPreferences user;


    MyShopAdapter myAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_confirm_sub);


        //xml 셋팅. : 상품명,설명,대여비,대여기준,보증금,위치
        category = findViewById( R.id.txt_category_confirm );
        image = findViewById( R.id.imagearea_confirm );
        txtimage = findViewById(R.id.txt_imagepath_confirm);
        name = findViewById(R.id.txt_goodsname_edit_confirm);
        desc = findViewById(R.id.txtbox_description_edit_confirm);
        rent = findViewById(R.id.txt_rentfee_edit_confirm);
        rentGijun = findViewById(R.id.txt_period_confirm);
        deposit = findViewById(R.id.txt_deposit_edit_confirm);
        location = findViewById(R.id.txt_location_edit_confirm);


        //Register로부터 intent 가져오기.
        Intent intent = getIntent();

        //registerconfirm에 intent값 가져오기
        //카테고리, 이미지, 상품명, 설명, 대여료, 대여료기준, 보증금, 위치

        from = intent.getStringExtra( "from" );
        Log.e( "from",from );

        position = intent.getIntExtra( "position_edit",0 );
        Log.e( "position", String.valueOf( position ) );

        getCategory = intent.getStringExtra( "category" );
        category.setText( getCategory );
        putCategory = getCategory;
        Log.e("putCategory:", putCategory);

        getImage = intent.getStringExtra("image");
        image.setImageURI( Uri.parse( getImage ));
        putImage = getImage;
        Log.e("putImage:", putImage);

        getName = intent.getStringExtra("name");
        name.setText(getName);
        putName = getName;
        Log.e("name:", putName);

        getDesc = intent.getStringExtra("desc");
        desc.setText(getDesc);
        putDesc = getDesc;
        Log.e("putDesc:", putDesc);

        getRent = intent.getStringExtra("rent");
        rent.setText(getRent);
        putRent = getRent;
        Log.e("putRent:", String.valueOf(putRent));

        getRentGijun = intent.getStringExtra("rentGijun");
        rentGijun.setText(getRentGijun);
        putRentGijun = getRentGijun;
        Log.e("putRentGijun:", putRentGijun);


        getDeposit = intent.getStringExtra("deposit");
        deposit.setText(getDeposit.toString());
        putDeposit = getDeposit;
        Log.e("putDeposit:", String.valueOf(putDeposit));

        getLocation = intent.getStringExtra("location");
        location.setText(getLocation);
        putLocation = getLocation;
        Log.e("putLocation:", putLocation);

        //등록일자 포멧 생성자.
        mFormat = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss" );
        //상품정보 불러오는 shared 생성자.
        pref = new SharedPreference(this);

        putDate = getTime();
        Log.e( "putDate:",putDate);

        //로그인 사용자 정보 불러오기 ( 상품 정보에 같이 담아야함)
        user = getSharedPreferences( "loginuser", MODE_PRIVATE );
        loginUserId = user.getString( "id",null );
        Log.e( "getId",loginUserId );
        gson = new Gson();

        //셰어드 불러오기. - 로그인사용자 정보 전부 불러오기.
        Log.e( "user셰어드:", String.valueOf( user ) );
        jsonString = user.getString( loginUserId, null );
        type = new TypeToken<UserItemAll>() {}.getType();
        login_user = gson.fromJson( jsonString, type );
        Log.e( "로그인정보 셰어드 불러오기:", "OK" );
        Log.e( "loginId:", login_user.getId() );

        //전역변수에 저장.
        userImage = login_user.getImage();
        userName = login_user.getName();
        userId = login_user.getId();
        userPw = login_user.getPw();
        userEmail = login_user.getEmail();
        userContact = login_user.getContact();
        Log.e( "userImage",userImage );
        Log.e( "userName",userName );
        Log.e( "userId", userId);
        Log.e( "userPw", userPw);
        Log.e( "userEmail",userEmail );
        Log.e( "userContact",userContact );


        //데이터 추가 메소드!
        myAdapter = new MyShopAdapter(this,R.layout.myshop_item_sub,this.array);

//
////        상단버튼 - 뒤로가기,홈이동
//        View top_btn_back = findViewById(R.id.top_btn_back_confirm);
//        top_btn_back.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(RegisterConfirmActivity.this,RegisterActivity.class);
//                intent.setAction(Intent.ACTION_VIEW);
//                startActivity(intent);
//                finish();
//            }
//        });

        //하단버튼 - 확인: 입력값을 Sharedpreference에 저장.
        View top_btn_ok = findViewById(R.id.btn_ok_register_confirm);
        top_btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                //마이샵에서왔니? 메인에서 바로 왔니?
                SharedPreferences pref = getSharedPreferences( "from",MODE_PRIVATE);
                from = pref.getString( "from",null );
                Log.e( "from",from );


                //intent key,value:"main" - 메인으로 이동
                //intent key,value:"myshop" - 마이샵으로 이동
                if(from.equals( "myshop" )) {
                    myAdapter.addItem( putFavCount,putCategory,putImage, putName, putRent, putRentGijun, putDeposit, putDesc, putLocation, putState, putDate,userImage,userName,userId,userPw,userEmail,userContact );

                    Log.e( "from:myshop","myshop으로 이동" );
                    //마이샵으로도 이동!
                    Intent intentToMyShop = new Intent( RegisterConfirmActivity.this, MyShopActivity.class );
                    intentToMyShop.setAction( intentToMyShop.ACTION_VIEW );
                    startActivity( intentToMyShop );
                    finish();

                }else if(from.equals( "main" )) {
                    myAdapter.addItem( putFavCount,putCategory,putImage, putName, putRent, putRentGijun, putDeposit, putDesc, putLocation, putState, putDate,userImage,userName,userId,userPw,userEmail,userContact );

                    Log.e( "from:main","main으로 이동" );
                    //메인 리스트로 이동!
                    Intent intent = new Intent( RegisterConfirmActivity.this, MainActivity.class );
                    intent.setAction( intent.ACTION_VIEW );
                    startActivity( intent );
                    finish();

                }else if(from.equals( "myshop_edit" )){

                    /**
                     * 수정 전의 ID,Name,Data 불러오기.
                     * myshopAdapter의 수정메소드에서 수정전 position값을 찾는데 필요한 data.
                     * @param : save_id, save_name, save_date
                     */

                    SharedPreferences pref1 = getSharedPreferences( "modify",MODE_PRIVATE );
                    String save_id = pref1.getString( "save_id",null );
                    String save_name = pref1.getString( "save_name",null );
                    String save_date = pref1.getString( "save_date",null );
                    Log.e( "save_id", save_id);
                    Log.e( "save_name",save_name );
                    Log.e( "save_date", save_date );

                    Log.e("from:myshop","myshop으로 이동");
                    Intent intent = new Intent( RegisterConfirmActivity.this, MyShopActivity.class );
                    //데이터 막 담아서 보내.
                    myAdapter.modifyItem(save_id,save_name,save_date,putFavCount,putCategory,putImage, putName, putRent, putRentGijun, putDeposit, putDesc, putLocation, putState, putDate,userImage,userName,userId,userPw,userEmail,userContact   );
                    startActivity( intent );
                    finish();


                }

            }
        });

//        하단버튼 - 뒤로가기
        View btn_cancel_register = findViewById(R.id.btn_cancel_register_confirm);
        btn_cancel_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //registerconfirm에 intent값 가져오기
                //상품명, 설명, 대여료, 보증금, 위치
                Intent intent2 = new Intent(RegisterConfirmActivity.this, RegisterActivity.class);
                intent2.putExtra("modify", true);
                intent2.putExtra( "position_edit",position );
                intent2.putExtra( "from",from );
                intent2.putExtra("category", putCategory);
                intent2.putExtra("image", putImage);
                intent2.putExtra("name", putName);
                intent2.putExtra("rent", putRent);
                intent2.putExtra("rentGijun", putRentGijun);
                intent2.putExtra("deposit", putDeposit);
                intent2.putExtra("desc", putDesc);
                intent2.putExtra("location", putLocation);


                Log.e("cancel버튼 클릭!", "클릮!!!");
                Log.e("image:", putImage);
                Log.e("category:", putCategory);
                Log.e("name:", putName);
                Log.e("desc:", putDesc);
                Log.e("rent:", putRent);
                Log.e("rentGijun:", putRentGijun);
                Log.e("rentGijun:", putDeposit);
                Log.e("location:", putLocation);
                Log.e("position_edit:", String.valueOf( position ) );

                startActivity(intent2);
                finish();

            }
        });

    }//onCreate 끝


    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format( mDate );
    }
}
