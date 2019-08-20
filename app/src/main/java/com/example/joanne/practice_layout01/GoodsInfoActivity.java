package com.example.joanne.practice_layout01;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by Joanne on 2018-01-29.
 */

public class GoodsInfoActivity extends AppCompatActivity implements Button.OnClickListener {

    //찜목록으로 넘기기 위해 필요한 Views
    //상품 이미지, 이름, 대여료, 대여기준, 보증금, 판매자 연락처
    private ImageView img,profile;
    private TextView category, name, rent, gijun, rentGijun, deposit, location, desc, dial,username,contact,uemail,state;
    private FavoriteListItem fitem;
    private MyOrderListItem oitem;



    private ArrayList<FavoriteListItem> arrFavorite;
    private ArrayList<MyOrderListItem> arrOrder;


    private String storedArrayString1, arrayJson1;
    private String storedArrayString2, arrayJson2;
    private String storedArrayString3, arrayJson3;

    //fpref: 찜목록, opref:대여목록, cpref:컨펌화면 , pref:좋아요카운트
    private SharedPreference fpref;
    private SharedPreference opref;
    private SharedPreference cpref;
    private SharedPreference pref;

    //저장 값. - sLocation~sDate는 추후 로그인 만들면 사용자 정보에 적용할 내용.(주문번호... )
    //sState,sProfileImg,sUserName,sUserId,sUserPw,sUserEmail,sUserContact
    private String sfavCount,num, sCategory, sImage, sName, sRent, sRentGijun, sDeposit, sDesc, sLocation, sDial, sUser, sDate, sSMS, sRentName, sEmail ;
    private String sState,sProfileImg,sUserName,sUserId,sUserPw,sUserEmail,sUserContact;
    private int position,favCount;
    private Type type;
    private Gson gson;



    String mm; long mNow;
    Date mDate;
    SimpleDateFormat mFormat;


    //좋아요
    Product item,item_modistate;
    ArrayList<Product> items;
    MainGridAdapter adapter;
    UserItemAll login_user;

    String id;

    int i;

    View like;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.goodsinfo_sub );

        mFormat = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss" );
        //mm = mFormat.format( mDate );


//        상단바:뒤로가기,홈이동,찾기,시세확인
        final View btn_back = findViewById( R.id.btn_back );
        btn_back.setOnClickListener( (View.OnClickListener) this );

        btn_back.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn_back.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                btn_back.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                btn_back.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );
        final View btn_home = findViewById( R.id.btn_home );
        btn_home.setOnClickListener( (View.OnClickListener) this );
        btn_home.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn_home.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                btn_home.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                btn_home.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );

        final View btn_search = findViewById( R.id.btn_search );
        btn_search.setOnClickListener( (View.OnClickListener) this );
        btn_search.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn_search.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                btn_search.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                btn_search.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );


        final View btn_sise = findViewById( R.id.btn_sise );
        btn_sise.setOnClickListener( (View.OnClickListener) this );
        btn_sise.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn_sise.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                btn_sise.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                btn_sise.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );


//        하단바:문의하기, 대여하기
        final Button question = findViewById( R.id.btn_question );
        question.setOnClickListener( this );
        question.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                question.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                question.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                question.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );


        final Button btn_rent = findViewById( R.id.btn_rent );
        btn_rent.setOnClickListener( this );
        btn_rent.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn_rent.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                btn_rent.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                btn_rent.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );





        //기능: 연락하기,메일보내기,공유하기,찜하기
        View dial = findViewById( R.id.detail_contact );
        dial.setOnClickListener( this );
        View email = findViewById( R.id.detail_email );
        email.setOnClickListener( this );
        View share = findViewById( R.id.btn_share );
        share.setOnClickListener( this );
        like = findViewById( R.id.btn_like );
        like.setOnClickListener( this );




        //데이터 받을 요소 셋팅
        img = (ImageView) findViewById( R.id.goods_image01 );
        category = (TextView) findViewById( R.id.txt_category_path );
        name = (TextView) findViewById( R.id.txt_goodsname01 );
        rent = (TextView) findViewById( R.id.txt_price );
        gijun = (TextView) findViewById( R.id.txt_period );
        deposit = (TextView) findViewById( R.id.txt_deposit_value );
        //location = (TextView)findViewById( R.id.txt_category_path);
        desc = (TextView) findViewById( R.id.btn_detail );
        profile = (ImageView)findViewById(R.id.detail_img  );
        username = (TextView)findViewById( R.id.detail_name );
        uemail = (TextView)findViewById( R.id.detail_email );
        contact = (TextView)findViewById( R.id.detail_contact );
        state = findViewById( R.id.goods_state );


        didTapButton( img );
        Log.e( "적용되나용","두둥" );




        //인텐트로 data 불러오기 : 좋아요, 카테고리, 이미지, 상품명, 대여료, 대여기준, 보증금, 위치, // 상태, 날자, 로그인유저이미지,로그인유저id,pw,email,연락처.
        Intent intent = getIntent();
        position = intent.getIntExtra(  "position" ,0 );
        Log.e( "position:", String.valueOf( position ) );
        sfavCount = intent.getStringExtra( "favcount" );
        sCategory = intent.getStringExtra( "category" );
        sImage = intent.getStringExtra( "image" );
        sName = intent.getStringExtra( "name" );
        sRent = intent.getStringExtra( "rent" );
        sRentGijun = intent.getStringExtra( "rentGijun" );
        sDeposit = intent.getStringExtra( "deposit" );
        sDesc = intent.getStringExtra( "desc" );
        sLocation = intent.getStringExtra( "location" );
        Log.e( "sCategory:", sCategory );

        sState = intent.getStringExtra( "state" );
        Log.e("sState",sState);
        sDate = intent.getStringExtra( "date" );
        sProfileImg = intent.getStringExtra( "userImage" );
        sUserName = intent.getStringExtra( "userName" );
        sUserId = intent.getStringExtra( "userId" );
        sUserPw = intent.getStringExtra( "userPw" );
        sUserEmail = intent.getStringExtra( "userEmail" );
        sUserContact = intent.getStringExtra( "userContact" );


        //불러온 data 맵핑
        state.setText( sState );
//        if(state.getText().equals(sState)){
//            state.setBackgroundColor( R.color.colorAccent);
//        }else {
//            state.setBackgroundColor( R.color.colorPrimary);
//        }
        category.setText( sCategory );
        img.setImageURI( Uri.parse( sImage ) );
        name.setText( sName );
        rent.setText( sRent );
        gijun.setText( sRentGijun );
        deposit.setText( sDeposit );
        //location.setText( sLocation );
        desc.setText( sDesc );
        profile.setImageURI( Uri.parse( sProfileImg ) );
        username.setText( sUserName );
        uemail.setText( sUserEmail );
        contact.setText( sUserContact );



        //fpref: favoritelist , opref: orderlist
        fpref = new SharedPreference( this );
        opref = new SharedPreference( this );
        cpref = new SharedPreference( this );
        pref = new SharedPreference( this );
//        like.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//
//        } );

        gson = new Gson();

        //좋아요를 위한 생성자들.
        item = new Product(  );
        item_modistate = new Product(  );
        items = new ArrayList<>(  );
        adapter = new MainGridAdapter(this,items);

        //Drawer 프로필 셋팅하기.
        //셰어드에서 로그인유저 아이디 불러와서 그 아이디를 키 값으로 한 로그인 정보를 불러온다음 드로어프로필의 각 요소에 셋팅시킨다.
        SharedPreferences user = getSharedPreferences( "loginuser", MODE_PRIVATE );
        id = user.getString( "id",null );

        Log.e( "getId",id );


        //셰어드 불러오기.
        Log.e( "user셰어드:", String.valueOf( user ) );
        String jsonString2 = user.getString( id, null );
        Type type = new TypeToken<UserItemAll>() {
        }.getType();
        login_user= new UserItemAll();
        login_user = gson.fromJson( jsonString2, type );
        Log.e( "셰어드 불러오기:", "OK" );
        Log.e( "셰어드 불러오기:", login_user.getId() );


    }//onCreate 끝

    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format( mDate );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            //뒤로가기
            case R.id.btn_back:
                onBackPressed();



                break;
            //홈으로 이동
            case R.id.btn_home:
                Intent intent_view1 = new Intent( GoodsInfoActivity.this, MainActivity.class );
                intent_view1.setAction( Intent.ACTION_VIEW );
                startActivity( intent_view1 );
                finish();
                break;
            //검색하기
            case R.id.btn_search:
                Intent search = new Intent();
                Intent view = new Intent( GoodsInfoActivity.this, SearchActivity.class );
                view.setAction( Intent.ACTION_VIEW );
                startActivity( view );
                break;
            //시세 알아보기
            case R.id.btn_sise:
                Intent intent_search = new Intent();
                intent_search.setAction( Intent.ACTION_SEARCH );
                intent_search.putExtra( SearchManager.QUERY, "상품명01" );
                startActivity( intent_search );
                break;
            //문의하기
            case R.id.btn_question:
                Uri uri = Uri.parse( "smsto:01012345678" );
                Intent it = new Intent( Intent.ACTION_SENDTO, uri );
                it.putExtra( "sms_body", "안녕하세요, 물품대여 문의드립니다." );
                startActivity( it );
                break;
            //대여하기
            case R.id.btn_rent:

                //0. product 셰어드 불러와서 sate 상태 변경!!
                //1. Product 불러오기.
                //2. loginuser 불러오기.
                //3. 불러온 정보 변수저장.
                //4. 주문서 Shared 생성.
                //5. 주문서에 (0,add) 저장.

                /**
                 * 0. state 상태 바꾸기
                 */

                /**
                 * 불러온 셰어드 를 size로 for문 돌리고,
                 * 수정 전의 아이디, 상품 이름, 날짜가 일치하면
                 * 그 상품의 position 값을 따로 변수(position)에 저장한다.
                 *
                 * 그래서 그 포지션 값에 set 하고 저장하면 끝!!! 꺄 ! ~
                 */

                SharedPreferences pref1 = getSharedPreferences( "modify",MODE_PRIVATE );
                String save_id = pref1.getString( "save_id",null );
                String save_name = pref1.getString( "save_name",null );
                String save_date = pref1.getString( "save_date",null );
//                Log.e( "save_id", save_id);
//                Log.e( "save_name",save_name );
//                Log.e( "save_date", save_date );


                SharedPreference pref;
                Gson gson = new Gson();
                pref = new SharedPreference( this );

                String storedArrayString1 = pref.loadPreference( "item", "product_list" );
                if (storedArrayString1 == null) {
                    items = new ArrayList<Product>();
                } else {
                    Type type = new TypeToken<ArrayList<Product>>() {
                    }.getType();
                    items = gson.fromJson( storedArrayString1, type );
                    Log.e( "array1:", String.valueOf( items ) );


                    for (i = 0; i < items.size(); i++) {
                        Log.e( "for문", "ㅇㅇ" );
                        Log.e( "array.size:", String.valueOf( items.size() ) );
                        int j = i;
                        Log.e( "j", String.valueOf( j ) );
                        if (save_id.equals( items.get( j ).getProid() ) && save_name.equals( items.get( j ).getName() ) && save_date.equals( items.get( j ).getDate() )) {
                            Log.e( "id", items.get( j ).getProid() );
                            Log.e( "name", items.get( j ).getName() );
                            Log.e( "date", items.get( j ).getDate() );
                            position = j;
                            Log.e( "position", String.valueOf( position ) );
                        } else {
                            Log.e( "해당안됨.", "ㅇㅇ" );
                        }
                    }
                }

                item_modistate = new Product(sfavCount,sCategory,sImage, sName, sRent, sRentGijun, sDeposit, sDesc, sLocation, "대여불가", sDate,sProfileImg,sUserName,sUserId,sUserPw,sUserEmail,sUserContact);

                //상품정보 리스트 Shared에서 불러와서 'position'번째에 set하기.
                items.set( position, item_modistate );
                Log.e( "array2:", String.valueOf( items ) );
                Log.e( "array-name", items.get( position ).getName() );
                Log.e( "array-category", items.get( position ).getCategory() );
                Log.e( "array-id", items.get( position ).getProid() );
                Log.e( "array-deposit", items.get( position ).getDepositValue() );
                Log.e( "array-deposit", String.valueOf( items.size() ) );

                String arrayJson1 = gson.toJson( items );
                pref.savePreference( "item", arrayJson1, "product_list" );
                Log.e( "array아이템", items.get( position ).getName() );















                //주문서 : 주문번호, 주문일자, 주문자명, 상품이미지, 상품명, 대여료, 대여기간, 보증금, 대여자명, 대여자이메일주소, 대여자연락처,연락처, SMS(13)
                num = "201802280001";
                sDate = getTime();
                sUser = login_user.getName();
                sDial = login_user.getContact();
                sSMS = login_user.getContact();

                oitem=new MyOrderListItem(num,sDate,sUser,sImage,sName,sRent,sRentGijun,sDeposit,sUserName,sUserEmail,sUserContact,sDial,sSMS);
                Log.e( "num:", num );
                Log.e( "sDate:", sDate );
                Log.e( "sUser:", sUser );
                Log.e( "sImage:", sImage );
                Log.e( "sDial:", sDial );
                Log.e( "sSMS:", sSMS );
                Log.e( "sName:", sName );
                Log.e( "sRent:", sRent );
                Log.e( "sRentGijun:", sRentGijun );
                Log.e( "sDeposit:", sDeposit );
                Log.e( "sUserName:", sUserName );
                Log.e( "sUserEmail:", sUserEmail );
                Log.e( "sUserContact:", sUserContact );

                /**
                 * 셰어드 2개 저장. key: id, order 전체.
                 */
                //opref.removePreference( "orderlist" );
                storedArrayString3 = opref.loadPreference( "all", "orderlist" );
                if (storedArrayString3 == null) {
                    arrOrder = new ArrayList<MyOrderListItem>();
                    Log.e( "arrOrder11:", String.valueOf( arrOrder ) );
                } else {
                    type = new TypeToken<ArrayList<MyOrderListItem>>() {
                    }.getType();
                    arrOrder = gson.fromJson( storedArrayString3, type );
                }
                arrOrder.add( 0, oitem );
                arrayJson3 = gson.toJson( arrOrder );
//                opref.savePreference( "oitem", arrayJson2, "order_list" );
//                Log.e( "주문리스트 저장:", arrayJson2 );

                opref.savePreference( "all", arrayJson3, "orderlist" );
                Log.e( "주문리스트 저장:", arrayJson3 );






                //opref.removePreference( "oitem","order" );
                storedArrayString2 = opref.loadPreference( id, "orderlist" );
                if (storedArrayString2 == null) {
                    arrOrder = new ArrayList<MyOrderListItem>();
                    Log.e( "arrOrder11:", String.valueOf( arrOrder ) );
                } else {
                    type = new TypeToken<ArrayList<MyOrderListItem>>() {
                    }.getType();
                    arrOrder = gson.fromJson( storedArrayString2, type );
                }
                arrOrder.add( 0, oitem );
                arrayJson2 = gson.toJson( arrOrder );
//                opref.savePreference( "oitem", arrayJson2, "order_list" );
//                Log.e( "주문리스트 저장:", arrayJson2 );

                opref.savePreference( id, arrayJson2, "orderlist" );
                Log.e( "주문리스트 저장:", arrayJson2 );


                //날짜도 저장해야해!!!!
                //날짜는, 따로 액티비티 띄워서 입력 받아야해,
                cpref.savePreference( "num",num,"confirm" );
                cpref.savePreference( "date",sDate,"confirm" );
                cpref.savePreference( "user",sUser,"confirm" );
                cpref.savePreference( "sImage",sImage,"confirm" );
                cpref.savePreference( "dial",sDial,"confirm" );
                cpref.savePreference( "sms",sSMS,"confirm" );
                cpref.savePreference( "name",sName,"confirm" );
                cpref.savePreference( "rent",sRent,"confirm" );
                cpref.savePreference( "rentgijun",sRentGijun,"confirm" );
                cpref.savePreference( "deposit",sDeposit,"confirm" );
                cpref.savePreference( "lendor",sUserName,"confirm" );
                cpref.savePreference( "email",sUserEmail,"confirm" );
                cpref.savePreference( "lendorcontact",sUserContact,"confirm" );

                Intent intent = new Intent( GoodsInfoActivity.this, ProgressDialogActivity.class );
                intent.setAction( Intent.ACTION_VIEW );
                startActivity( intent );

                /**
                 * 주문하시겠습니까? 팝업창 띄우기
                 */
                //show();

                break;

            //판매자에게 연락
            case R.id.detail_contact:

                Uri uri_dial = Uri.parse("tel:"+sDial );
                Intent intent_dial = new Intent( Intent.ACTION_DIAL, uri_dial );
                startActivity( intent_dial );
                break;
            //이메일 전송. 망
            case R.id.detail_email:

//                Intent intent_email = new Intent(Intent.ACTION_SEND);
//                intent_email.putExtra(Intent.EXTRA_EMAIL, "shareworld@naver.com");
//                intent_email.putExtra(Intent.EXTRA_TEXT, "물품대여 문의드립니다.");
//                intent_email.setType("text/plain");
//                startActivity(Intent.createChooser(intent_email, "Choose Email Client"));

//                Intent intent_email = new Intent(Intent.ACTION_SEND);
//                String[] tos = {"me@abc.com"};
//                String[] ccs = {"you@abc.com"};
//                intent_email.putExtra(Intent.EXTRA_EMAIL, tos);
//                intent_email.putExtra(Intent.EXTRA_CC, ccs);
//                intent_email.putExtra(Intent.EXTRA_TEXT, "The email body text");
//                intent_email.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
//                intent_email.setType("message/rfc822");
//                startActivity(Intent.createChooser(intent_email, "Choose Email Client"));

                break;

            //공유하기
            case R.id.btn_share:
                String subject = "메시지 제목";
                String text = "공유앱이 메시지하나 뿐이라...ㅜㅜ";

                Intent intent_share = new Intent( Intent.ACTION_SEND );
                intent_share.setType( "text/plain" );
                intent_share.putExtra( Intent.EXTRA_SUBJECT, subject );
                intent_share.putExtra( Intent.EXTRA_TEXT, text );
                Intent chooser = Intent.createChooser( intent_share, "타이틀" );
                startActivity( chooser );


                break;

            //찜하기
            case R.id.btn_like:
                //찜버튼을 누르면, 메인클래스의 숫자가 바뀌어야하는데..
                //해당 포지션의 favCount 값이 올라가야함...

                Animation myAnim = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.push_up_out);
                like.startAnimation( myAnim );

                Log.e( "position111:", String.valueOf( position ) );
                //adapter.addFavCount(position);
  //              adapter.notifyDataSetChanged();

               //item = adapter.getItem(position);
               // adapter.addFavorite();

                //adapter.notifyDataSetChanged();


                //현재 favCount값을 불러오고.
//                favCount = pref.loadPreference2( "position"+position,0,"favCount" );
//                Log.e( "favCount[1]:", String.valueOf( favCount ) );
//
//                favCount=+1;
//                Log.e( "favCount[2]:", String.valueOf( favCount ) );
//                //셰어드프리퍼런스의 key를 position값으로 놓고, 그 포지션의 favcount가 올라가게!
//                pref.savePreference("position"+position,favCount,"favcount");



                //이 위에 뭔가, name, rent, deposit, gijun, dial 에 input 값을 넣는 메소드가 들어와야해!!!!!
                //즉, 애초에 상품등록할 때 들어오는 인자값에다가 적용을 시켜야한다는 거임.

                //찜하기 버튼을 누르면, SharedPreference에 저장이 된다.

                //상품 정보를 json으로 변환하여 shared에 저장!
                fitem = new FavoriteListItem( id,sImage, sName, sRent, sRentGijun, sDeposit );
                Log.e( "id:",id );
                Log.e( "sImage:", sImage );
                Log.e( "sName:", sName );
                Log.e( "sRent:", sRent );
                Log.e( "sRentGijun:", sRentGijun );
                Log.e( "sDeposit:", sDeposit );

                //fpref.removePreference( "favorite" );
                //먼저 이전에 있던 favorite 셰어드를 불러와서 storedArrayString1에 저장.
                //fpref.removePreference( "favorite" );
                storedArrayString1 = fpref.loadPreference( "fitem", "favorite" );
                Log.e( "여기니?", "ㅇㅇ" );
                //만약 storedArrayString1이 null값일 경우에, new ArrayList<FavoritelistItem>()생성!
                //null이 아닐 경우, arrFavorite에다가 storedArrayString1을 저장.

                gson = new Gson();
                if (storedArrayString1 == null) {
                    arrFavorite = new ArrayList<FavoriteListItem>();
                } else {
                    type = new TypeToken<ArrayList<FavoriteListItem>>() {
                    }.getType();
                    Log.e( "type", String.valueOf( type ) );
                    arrFavorite = gson.fromJson( storedArrayString1, type );
                    Log.e( "같은문제3", "..." );
                }

                //그리고 arrFavorite에 item 추가.
                arrFavorite.add( 0, fitem );
                Log.e( "같은문제1", "..." );

                gson = new Gson();
                //json으로 변환하여 arrayJson1(string)에 저장후,
                arrayJson1 = gson.toJson( arrFavorite );
                Log.e( "같은문제2", "..." );
                //savePreference에 저장!
                fpref.savePreference( "fitem", arrayJson1, "favorite" );
                Log.e( "저장완료?", "OK" );

                //추가알림 Toast
                Toast.makeText( GoodsInfoActivity.this, "찜목록에 추가되었습니다.", LENGTH_LONG ).show();

                //여기서 찜목록으로 이동하시겠습니까? 팝업창 하나 넣어주면 좋지...

                break;


        }
    }//onClick 끝




    //찜목록에 로드 시키기:...

    private void rentInit() {
        name = (TextView) findViewById( R.id.txt_goodsname01 );
        rent = (TextView) findViewById( R.id.txt_price );
        deposit = (TextView) findViewById( R.id.txt_deposit_value );
        //gijun=(TextView)findViewById(R.id.txt_period);
        dial = (TextView) findViewById( R.id.detail_contact );


        //이 부분에, 총 대여료 계산하는 것 넣어줘야함.. rentSum, calendar, period 표시형식 또는 계산 조정 필요함


    }

    public void didTapButton(View view) {
        //img = (ImageView)findViewById(R.id.goods_image01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20 //진폭과 이탈횟수
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        view.startAnimation(myAnim);



    }


}
