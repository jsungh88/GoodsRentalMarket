package com.example.joanne.practice_layout01;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Button.OnClickListener {

    static Boolean app = true;

    //그리드 관련 객체 선언
    GridView gridView;

    ArrayList<Product> array;

    MainGridAdapter adapter;

    //상품등록데이터 가져오기.
    SharedPreference pref, fp;

    String storedArrayString, storedString2;
    Type type;

    Gson gson;

//    Integer favCount;

    //사용자 정보 불러오기
    UserItemAll login_user;
    String jsonString1, jsonString2;
    //불러온 데이터 저장
    String getImage, getName, getId, getPw, getEmail, getContact;

    //로그인사용자 셰어드
    SharedPreferences user;
    String from;

    //애니메이션
    //LinearLayout 11,12;
    //Button btnsub;
    //Animation uptodown;
    View main;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        Log.e( "onCreate:","MainActivity도착!" );
        //Toast.makeText(MainActivity.this,"Main_onCreate",Toast.LENGTH_SHORT).show();
        //product 생성자 생성: list 뿌릴 arrayList임.
//        products = new ArrayList<Product>();
//        Log.e("products:", String.valueOf(products));

        main = findViewById( R.id.main );

        //shared 생성자 생성.
        pref = new SharedPreference( this );
        fp = new SharedPreference( this );

        gson = new Gson();
        //array=new ArrayList<Product>();
        //DB에 저장.
        loadItemFromDB();
        //Log.e( "loadItemFromDB:",array.get( 0 ).getName() );
        gridView = (GridView) findViewById( R.id.main_grid );
        adapter = new MainGridAdapter( this, this.array );
        Log.e( "array:", String.valueOf( array ) );
        gridView.setAdapter( adapter );


        gridView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText( MainActivity.this, "" + position, Toast.LENGTH_SHORT ).show();

                //각 포지션에 데이터 불러오기
//                jsonArray = pref.loadPreference("item", "product");
//                // /json array로 변환.
//                type = new TypeToken<ArrayList<Product>>() { }.getType();
//                array = gson.fromJson(jsonArray, type);

                //애니메이션
//                int cx = gridView.getWidth();
//                int cy = gridView.getHeight();
//                float finalRadius = Math.max( cx, cy ) + 1.2f;
//                Animator reveal = null;
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    reveal = ViewAnimationUtils.createCircularReveal( gridView, (int) gridView.getX(), (int) gridView.getY(), 0f, finalRadius );
//                }
//                reveal.start();

                //인텐트 생성
                Intent intent = new Intent( MainActivity.this, GoodsInfoActivity.class );

                //위에서 만든 Bundle을 인텐트에 넣는다.
                intent.putExtra( "position", position );
                Log.e( "position:", String.valueOf( position ) );

                intent.putExtra( "favcount", array.get( position ).getFavCount() );
                intent.putExtra( "category", array.get( position ).getCategory() );
                intent.putExtra( "image", array.get( position ).getSimage() );
                intent.putExtra( "name", array.get( position ).getName() );
                intent.putExtra( "rent", array.get( position ).getRentValue() );
                intent.putExtra( "rentGijun", array.get( position ).getRentGijun() );
                intent.putExtra( "deposit", array.get( position ).getDepositValue() );
                intent.putExtra( "desc", array.get( position ).getDesc() );
                intent.putExtra( "location", array.get( position ).getLocTxt() );
                intent.putExtra( "state",array.get( position ).getState() );
                Log.e( "state!!!!!", array.get( position ).getState());
                intent.putExtra( "date",array.get( position ).getDate() );

                intent.putExtra( "userImage",array.get( position ).getProimage() );
                intent.putExtra( "userName",array.get( position ).getProname() );
                intent.putExtra( "userId",array.get( position ).getProid() );
                intent.putExtra( "userPw",array.get( position ).getPropw() );
                intent.putExtra( "userEmail",array.get( position ).getProemail() );
                intent.putExtra( "userContact",array.get( position ).getProcontact() );

                startActivity( intent );


            }
        } );

//        gridView.setOnTouchListener( new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                reveal(motionEvent);
//                return true;
//
//
//
//
//            }
//        } );

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );

        //drawer에 있는 이미지, 사용자명, email 셋팅하기.
        View headview = navigationView.getHeaderView( 0 );
        ImageView nav_image = headview.findViewById( R.id.nav_imageView );
        TextView nav_name = headview.findViewById( R.id.nav_name );
        TextView nav_id = headview.findViewById( R.id.nav_id );
        TextView nav_email = headview.findViewById( R.id.nav_email );
        Button logout = headview.findViewById( R.id.logout );
        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, LogInActivity.class );
                intent.setAction( Intent.ACTION_VIEW );
                startActivity( intent );
                finish();
            }
        } );


        //Drawer 프로필 셋팅하기.
        //셰어드에서 로그인유저 아이디 불러와서 그 아이디를 키 값으로 한 로그인 정보를 불러온다음 드로어프로필의 각 요소에 셋팅시킨다.
        user = getSharedPreferences( "loginuser", MODE_PRIVATE );
        getId = user.getString( "id",null );

        Log.e( "getId",getId );


        gson = new Gson();
        //셰어드 불러오기.
        user = getSharedPreferences( "loginuser", MODE_PRIVATE );
        Log.e( "user셰어드:", String.valueOf( user ) );
        jsonString2 = user.getString( getId, null );
        Type type = new TypeToken<UserItemAll>() {
        }.getType();
        login_user = gson.fromJson( jsonString2, type );
        Log.e( "셰어드 불러오기:", "OK" );
        Log.e( "셰어드 불러오기:", login_user.getId() );

        nav_image.setImageURI( Uri.parse( login_user.getImage() ) );
        nav_id.setText( login_user.getId() );
        nav_name.setText( login_user.getName() );
        nav_email.setText( login_user.getEmail() );
        Log.e( "nav_image:", String.valueOf( nav_image ) );
        Log.e( "nav_name:", String.valueOf( nav_name ) );
        Log.e( "nav_id:", String.valueOf( nav_id ) );
        Log.e( "nav_email:", String.valueOf( nav_email ) );


//        //위치버튼
//        View btn_location = findViewById( R.id.btn_location );
//        btn_location.setOnClickListener( this );
//        View txt_location = findViewById( R.id.txt_location );
//        txt_location.setOnClickListener( this );

//        Animation animTranslate = AnimationUtils.loadAnimation( this,R.anim.slide_down_and_scale);
//        gridView.startAnimation(animTranslate);
//        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
//        11.setAnimation(uptodown);
//        View gridItem = findViewById(  )
//
        drawer.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        } );


    }//onCreate

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

    //MAIN메뉴, 이건 지금 당장 필요없음...
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }

    //이건 우측에 있던 메뉴에 대한 내용이니 나에겐 필요가 없음
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cate01) {
            //상품 검색
            Intent view = new Intent( MainActivity.this, SearchActivity.class );
            view.setAction( Intent.ACTION_VIEW );
            startActivity( view );


        } else if (id == R.id.nav_cate02) {
            //상품 등록

            //상품등록이, myshop과 main에서 접근 가능하기 때문에, 어디서 유입되었는지 알려줘야함.
            //그런데 상품등록이 상품등록>상품확인>메인 3단계를 거치기 때문에 intent가 아닌 shared에 저장해서 알려준다.
            SharedPreferences pref = getSharedPreferences( "from", MODE_PRIVATE );
            SharedPreferences.Editor editor = pref.edit();
            editor.putString( "from","main" );
            editor.commit();
            from=pref.getString( "from",null );
            Log.e( "셰어드저장:",from);

            Intent view = new Intent( MainActivity.this, RegisterActivity.class );
            view.setAction( Intent.ACTION_VIEW );
            startActivity( view );

        } else if (id == R.id.nav_cate03) {
            //MY SHOP
            Intent view = new Intent( MainActivity.this, MyShopActivity.class );
            view.setAction( Intent.ACTION_VIEW );
            startActivity( view );

        } else if (id == R.id.nav_cate04) {
            //주문 조회
            Intent view = new Intent( MainActivity.this, OrderListActivity.class );
            view.setAction( Intent.ACTION_VIEW );
            startActivity( view );


        } else if (id == R.id.nav_cate05) {
            //내 주문 내역
            Intent view = new Intent( MainActivity.this, MyOrderListActivity.class );
            view.setAction( Intent.ACTION_VIEW );
            startActivity( view );

        } else if (id == R.id.nav_cate06) {
            //찜목록
            Intent view = new Intent( MainActivity.this, FavoriteListActivity.class );
            view.setAction( Intent.ACTION_VIEW );
            startActivity( view );

        }

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }

    @Override
    public void onClick(View v) {

//        switch (v.getId()) {
//            case R.id.btn_location:
//                Intent intentloc1 = new Intent( Intent.ACTION_VIEW, Uri.parse( "geo:37.5662952,126.9779451" ) );
//                startActivity( intentloc1 );
//
//                break;
//            case R.id.txt_location:
//                Intent intentloc = new Intent( Intent.ACTION_VIEW, Uri.parse( "geo:37.5662952,126.9779451" ) );
//                startActivity( intentloc );
//
//                break;
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == 30 && requestCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get( "data" );
            ImageView resultImageView = null;
            resultImageView.setImageBitmap( bitmap );

        }
    }



    //그리드리스트 아이템DB
    public void loadItemFromDB() {

        //Shared에서 불러온 arrayList인 products를 저장한다.
        //sharedpreference로부터 불러오기
        storedArrayString = pref.loadPreference( "item", "product_list" );
        //Log.e("storedArrayString", storedArrayString);
        // /json array로 변환.
        if(storedArrayString == null){
            array = new ArrayList<Product>();
        }else {
            type = new TypeToken<ArrayList<Product>>() {
            }.getType();
            array = gson.fromJson( storedArrayString, type );
            Log.e( "array", String.valueOf( array ) );
        }

    }


    //애니메이션

    public void reveal(MotionEvent e) {
        int cx = main.getHeight();
        int cy = main.getHeight();

        float finalRadius = Math.max( cx, cy ) + 1.2f;
        Animator reveal = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            reveal = ViewAnimationUtils.createCircularReveal( gridView, (int) e.getX(), (int) e.getY(), 0f, finalRadius );
        }

        reveal.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
      //  Toast.makeText(MainActivity.this,"Main_onResume",Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(MainActivity.this,"Main_onStart",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
       // Toast.makeText(MainActivity.this,"Main_onRestart",Toast.LENGTH_SHORT).show();

        //DB에 저장.
        loadItemFromDB();
        //Log.e( "loadItemFromDB:",array.get( 0 ).getName() );
        gridView = (GridView) findViewById( R.id.main_grid );
        adapter = new MainGridAdapter( this, this.array );
        Log.e( "array:", String.valueOf( array ) );
        gridView.setAdapter( adapter );


    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Toast.makeText(MainActivity.this,"Main_onPause",Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Toast.makeText(MainActivity.this,"Main_onStop",Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(MainActivity.this,"Main_onDestroy",Toast.LENGTH_SHORT).show();
//    }

}



