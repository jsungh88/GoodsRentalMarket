package com.example.joanne.practice_layout01;

import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Joanne on 2018-02-07.
 */

public class MyShopActivity extends AppCompatActivity {

    private Button btn_add, btn_modify, btn_delete;
    CheckBox chk;

    ArrayList<Product> array;
    MyShopAdapter adapter;
    Product item;
    ListView listView;

    String product, rent, deposit, state;
    int size, position; //아이템 갯수, 체크된 아이템 번호

    int delete_count = 0;

    static final public int RequestCode1 = 1;
    static final public int RequestCode2 = 2;

    SharedPreference pref;
    Gson gson;
    String storedArrayString;
    Type type;

    String getId, jsonString2;
    UserItemAll login_user;
    static public boolean modyBtnState;

    int i = 0;

    //삭제 선택 item & position
    String save_id, save_name, save_date;
    int delete_position;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.myshop_sub );


//        data = new MyShopItem();
//        adapter= new MyShopAdapter();
//        array_items = new ArrayList<>();

        array = new ArrayList<Product>();
        item = new Product();
        SharedPreferences user = getSharedPreferences( "loginuser", MODE_PRIVATE );
        getId = user.getString( "id", null );

        Log.e( "getId", getId );

        gson = new Gson();

        //셰어드 불러오기.
        user = getSharedPreferences( "loginuser", MODE_PRIVATE );
        Log.e( "user셰어드:", String.valueOf( user ) );
        jsonString2 = user.getString( getId, null );
        Type type = new TypeToken<UserItemAll>() {
        }.getType();
        login_user = gson.fromJson( jsonString2, type );
        Log.e( "셰어드 불러오기:", "OK" );
        Log.e( "셰어드 불러오기:", login_user.getName() );


        pref = new SharedPreference( this );
        gson = new Gson();

        //내가 올린 상품 리스트 불러오기
        loadItemFromDB();

        listView = findViewById( R.id.myshopList );
        adapter = new MyShopAdapter( this, R.layout.myshop_item_sub, array );
//        Log.e( "loadItemFromDB:",array.get( 0 ).getDate() );

        listView.setAdapter( adapter );
        ((MyShopAdapter) listView.getAdapter()).getFilter().filter( getId );


        //리스트뷰 활성화
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e( "myshop아이템 클릭", "ㅇㅇ" );
                //인텐트 생성
                Intent intent = new Intent( MyShopActivity.this, GoodsInfoActivity.class );
                //위에서 만든 Bundle을 인텐트에 넣는다.
                intent.putExtra( "position", position );
                Log.e( "position:", String.valueOf( position ) );
                intent.putExtra( "favcount", adapter.filtered_array.get( position ).getFavCount() );
                intent.putExtra( "category", adapter.filtered_array.get( position ).getCategory() );
                intent.putExtra( "image", adapter.filtered_array.get( position ).getSimage() );
                intent.putExtra( "name", adapter.filtered_array.get( position ).getName() );
                intent.putExtra( "rent", adapter.filtered_array.get( position ).getRentValue() );
                intent.putExtra( "rentGijun", adapter.filtered_array.get( position ).getRentGijun() );
                intent.putExtra( "deposit", adapter.filtered_array.get( position ).getDepositValue() );
                intent.putExtra( "desc", adapter.filtered_array.get( position ).getDesc() );
                intent.putExtra( "location", adapter.filtered_array.get( position ).getLocTxt() );
                intent.putExtra( "state", adapter.filtered_array.get( position ).getState() );
                intent.putExtra( "date", adapter.filtered_array.get( position ).getDate() );

                intent.putExtra( "userImage", adapter.filtered_array.get( position ).getProimage() );
                intent.putExtra( "userName", adapter.filtered_array.get( position ).getProname() );
                intent.putExtra( "userId", adapter.filtered_array.get( position ).getProid() );
                intent.putExtra( "userPw", adapter.filtered_array.get( position ).getPropw() );
                intent.putExtra( "userEmail", adapter.filtered_array.get( position ).getProemail() );
                intent.putExtra( "userContact", adapter.filtered_array.get( position ).getProcontact() );

                Log.e( "category", adapter.filtered_array.get( position ).getCategory() );
                startActivity( intent );

            }
        } );

        /**
         * 추가,수정,삭제버튼.
         */
        btn_add = findViewById( R.id.myshop_add );
        btn_modify = findViewById( R.id.myshop_modify );
        btn_delete = findViewById( R.id.myshop_delete );

        //추가
        btn_add.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //상품등록이, myshop과 main에서 접근 가능하기 때문에, 어디서 유입되었는지 알려줘야함.
                //그런데 상품등록이 상품등록>상품확인>메인 3단계를 거치기 때문에 intent가 아닌 shared에 저장해서 알려준다.
                SharedPreferences pref = getSharedPreferences( "from", MODE_PRIVATE );
                SharedPreferences.Editor editor = pref.edit();
                editor.putString( "from", "myshop" );
                editor.commit();
                String myshop = pref.getString( "from", null );
                Log.e( "셰어드저장:", myshop );

                //상품등록 페이지로 이동.
                Intent intent = new Intent( MyShopActivity.this, RegisterActivity.class );
                intent.setAction( Intent.ACTION_VIEW );
                startActivity( intent );
                finish();

            }
        } );
        btn_add.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn_add.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                btn_add.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                btn_add.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );


        /**수정버튼
         * 버튼을 한번 누르면 편집 모드, 다시 한번 누르면 일반 모드
         * 편집 모드 : item 클릭 시, 수정 화면으로 이동.
         * 일반 모드 : item 클릭 시, 해당 상세화면으로 이동.
         */
        btn_modify.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 1 - i;
                //버튼 선택 X
                if (i == 0) {
                    btn_modify.setSelected( !btn_modify.isSelected() );
                    modyBtnState = false;

                    listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Log.e( "myshop아이템 클릭", "ㅇㅇ" );
                            //상세화면으로 보낼 정보 intent에 담기.
                            Intent intent = new Intent( MyShopActivity.this, GoodsInfoActivity.class );

                            intent.putExtra( "position", position );
                            Log.e( "position:", String.valueOf( position ) );
                            intent.putExtra( "favcount", adapter.filtered_array.get( position ).getFavCount() );
                            intent.putExtra( "category", adapter.filtered_array.get( position ).getCategory() );
                            intent.putExtra( "image", adapter.filtered_array.get( position ).getSimage() );
                            intent.putExtra( "name", adapter.filtered_array.get( position ).getName() );
                            intent.putExtra( "rent", adapter.filtered_array.get( position ).getRentValue() );
                            intent.putExtra( "rentGijun", adapter.filtered_array.get( position ).getRentGijun() );
                            intent.putExtra( "deposit", adapter.filtered_array.get( position ).getDepositValue() );
                            intent.putExtra( "desc", adapter.filtered_array.get( position ).getDesc() );
                            intent.putExtra( "location", adapter.filtered_array.get( position ).getLocTxt() );
                            intent.putExtra( "state", adapter.filtered_array.get( position ).getState() );
                            intent.putExtra( "date", adapter.filtered_array.get( position ).getDate() );

                            intent.putExtra( "userImage", adapter.filtered_array.get( position ).getProimage() );
                            intent.putExtra( "userName", adapter.filtered_array.get( position ).getProname() );
                            intent.putExtra( "userId", adapter.filtered_array.get( position ).getProid() );
                            intent.putExtra( "userPw", adapter.filtered_array.get( position ).getPropw() );
                            intent.putExtra( "userEmail", adapter.filtered_array.get( position ).getProemail() );
                            intent.putExtra( "userContact", adapter.filtered_array.get( position ).getProcontact() );

                            Log.e( "category", adapter.filtered_array.get( position ).getCategory() );
                            startActivity( intent );
                            finish();

                        }
                    } );


                    //버튼 선택 O
                } else {
                    btn_modify.setSelected( true );
                    modyBtnState = true;

                    listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent( MyShopActivity.this, RegisterActivity.class );

                            //상품등록이, myshop과 main에서 접근 가능하기 때문에, 어디서 유입되었는지 알려줘야함.
                            //그런데 상품등록이 상품등록>상품확인>메인 3단계를 거치기 때문에 intent가 아닌 shared에 저장해서 알려준다.
                            SharedPreferences pref = getSharedPreferences( "from", MODE_PRIVATE );
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString( "from", "myshop_edit" );
                            editor.commit();
                            String myshop_edit = pref.getString( "from", null );
                            Log.e( "셰어드저장:", myshop_edit );

                            //해당 데이터 고스란히 담아서 register화면으로 이동
                            //구분 intent를 보내야할 것.
                            Log.e( "수정하기 클릭", "ㅇㅇ" );

                            //modify = true를 보내야, register에서, 보낸 값을 담아줌.
                            intent.putExtra( "from", myshop_edit );
                            intent.putExtra( "modify", true );
                            intent.putExtra( "position_edit", position );
                            Log.e( "position_edit(수정):", String.valueOf( adapter.getItemId( position ) ) );

                            intent.putExtra( "favcount", adapter.filtered_array.get( position ).getFavCount() );
                            intent.putExtra( "category", adapter.filtered_array.get( position ).getCategory() );
                            intent.putExtra( "image", adapter.filtered_array.get( position ).getSimage() );
                            intent.putExtra( "name", adapter.filtered_array.get( position ).getName() );
                            intent.putExtra( "rent", adapter.filtered_array.get( position ).getRentValue() );
                            intent.putExtra( "rentGijun", adapter.filtered_array.get( position ).getRentGijun() );
                            intent.putExtra( "deposit", adapter.filtered_array.get( position ).getDepositValue() );
                            intent.putExtra( "desc", adapter.filtered_array.get( position ).getDesc() );
                            intent.putExtra( "location", adapter.filtered_array.get( position ).getLocTxt() );
                            intent.putExtra( "state", adapter.filtered_array.get( position ).getState() );
                            intent.putExtra( "date", adapter.filtered_array.get( position ).getDate() );

                            intent.putExtra( "userImage", adapter.filtered_array.get( position ).getProimage() );
                            intent.putExtra( "userName", adapter.filtered_array.get( position ).getProname() );
                            intent.putExtra( "userId", adapter.filtered_array.get( position ).getProid() );
                            intent.putExtra( "userPw", adapter.filtered_array.get( position ).getPropw() );
                            intent.putExtra( "userEmail", adapter.filtered_array.get( position ).getProemail() );
                            intent.putExtra( "userContact", adapter.filtered_array.get( position ).getProcontact() );

                            Log.e( "name", adapter.filtered_array.get( position ).getName() );
                            Log.e( "category", adapter.filtered_array.get( position ).getCategory() );
                            Log.e( "location", adapter.filtered_array.get( position ).getLocTxt() );


                            /**
                             * 수정 전의 ID,Name,Date를 저장.
                             * myshopAdapter의 수정메소드에서 수정전 position값을 찾는데 필요한 data.
                             */
                            SharedPreferences pref1 = getSharedPreferences( "modify", MODE_PRIVATE );
                            SharedPreferences.Editor editor1 = pref1.edit();
                            editor1.putString( "save_id", adapter.filtered_array.get( position ).getProid() );
                            editor1.putString( "save_name", adapter.filtered_array.get( position ).getName() );
                            editor1.putString( "save_date", adapter.filtered_array.get( position ).getDate() );
                            editor1.commit();

                            Log.e( "save_id", adapter.filtered_array.get( position ).getProid() );
                            Log.e( "save_name", adapter.filtered_array.get( position ).getName() );
                            Log.e( "save_date", adapter.filtered_array.get( position ).getDate() );

                            startActivity( intent );
                            finish();

                        }
                    } );
                }
                listView.setAdapter( adapter );
                adapter.notifyDataSetChanged();
            }
        } );

        btn_modify.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn_modify.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                btn_modify.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                btn_modify.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );

        //삭제
        btn_delete.setOnClickListener( new View.OnClickListener() {
            /**
             * chk 선택한 아이템의 position 값을 가져와서
             * 변수 position에 넣고 삭제!!
             * @param v
             */

            @Override
            public void onClick(View v) {

                Log.e( "삭제버튼 클릭", "ㅇㅇ" );
                SharedPreferences pref1 = getSharedPreferences( "delete", Context.MODE_PRIVATE );
                save_id = pref1.getString( "save_id", null );
                save_name = pref1.getString( "save_name", null );
                save_date = pref1.getString( "save_date", null );
                Log.e( "save_id", save_id );
                Log.e( "save_name", save_name );
                Log.e( "save_date", save_date );

                //**
                // 원래 shared 불러와서, save_id,save_name,save_date가 있는 item의 position 값을 변수 delete_position에 저장한다.
                SharedPreference pref;
                Gson gson = new Gson();
                pref = new SharedPreference( MyShopActivity.this );

                String storedArrayString1 = pref.loadPreference( "item", "product_list" );
                if (storedArrayString1 == null) {
                    array = new ArrayList<Product>();
                } else {
                    Type type = new TypeToken<ArrayList<Product>>() {
                    }.getType();
                    array = gson.fromJson( storedArrayString1, type );
                    Log.e( "array1:", String.valueOf( array ) );

                    for (int d = 0; d < array.size(); d++) {
                        Log.e( "for문", "시작" );
                        int j = d;
                        Log.e( "j", String.valueOf( j ) );
                        if (save_id.equals( array.get( j ).getProid() )
                                && save_name.equals( array.get( j ).getName() )
                                && save_date.equals( array.get( j ).getDate() )) {
                            Log.e( "id", array.get( j ).getProid() );
                            Log.e( "name", array.get( j ).getName() );
                            Log.e( "date", array.get( j ).getDate() );
                            delete_position = j;
                            Log.e( "delete_position", String.valueOf( delete_position ) );

                        } else {
                            Log.e( "해당 없음", "ㅇㅇ" );
                        }
                    }

                }//if문 끝

                //delete_position item 삭제!!
//                item = (Product) adapter.getItem( delete_position );
                Log.e( "아이템 불러오기", "여긴오니" );
                //if(item.isChecked){
                Log.e( "delete_position", String.valueOf( delete_position ) );
                array.remove( delete_position );
                Log.e( "삭제완료", "ㅇㅇ" );
                //}

                //다시 상품리스트Shared 에 저장!!!
                String arrayJson1 = gson.toJson( array );
                pref.savePreference( "item", arrayJson1, "product_list" );
                Log.e( "array아이템", array.get( delete_position ).getName() );


                loadItemFromDB();
                adapter = new MyShopAdapter( getApplicationContext(), R.layout.myshop_item_sub, array );

                listView.setAdapter( adapter );
                ((MyShopAdapter) listView.getAdapter()).getFilter().filter( getId );

                Toast.makeText( MyShopActivity.this, "선택한 상품이 삭제되었습니다.", Toast.LENGTH_SHORT ).show();

            }


        } );//버튼 끝.

        adapter.notifyDataSetChanged();
//        Toast.makeText( MyShopActivity.this, "Myshop_onCreate", Toast.LENGTH_SHORT ).show();

        btn_delete.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn_delete.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN :
                                Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale );
                                btn_delete.startAnimation( anim );
                            case MotionEvent.ACTION_UP :
                                Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.scale2 );
                                btn_delete.startAnimation( anim1 );
                        }
                        return false;
                    }
                } );

                return false;
            }
        } );


    }//onCreate


    private void loadItemFromDB() {
        try {
            if (array == null) {
                array = new ArrayList<Product>();
            }
            //Shared에서 불러온 arrayList인 products를 저장한다.
            //sharedpreference로부터 불러오기
            storedArrayString = pref.loadPreference( "item", "product_list" );
            //Log.e("storedArrayString", storedArrayString);
            // /json array로 변환.
            type = new TypeToken<ArrayList<Product>>() {
            }.getType();
            array = gson.fromJson( storedArrayString, type );


            Log.e( "array+db:", array.get( 0 ).getProid() );
        } catch (Exception e) {
            Log.e( "알림", "등록된 상품이 없습니다." );
        }

        //array.get( 0 ).setCategory( "가전/디지털/컴퓨터" );
    }

}
