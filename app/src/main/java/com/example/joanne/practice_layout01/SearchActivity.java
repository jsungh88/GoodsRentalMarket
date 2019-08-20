package com.example.joanne.practice_layout01;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Joanne on 2018-01-31.
 */

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{

    ListView listview = null;
    ArrayList<Product> items;
    Gson gson;
    String storedArrayString;
    SharedPreference pref;
    SearchAdapter adapter;

    Type type;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_sub);

        gson = new Gson();
        pref = new SharedPreference( this );
        items = new ArrayList<Product>(  );

        loadItemsFromDB();

        listview = findViewById( R.id.search_listview );
        adapter = new SearchAdapter(this, R.layout.search_sub_result,items);
        listview.setAdapter( adapter );




        final EditText filter = findViewById( R.id.edit_search );
        filter.addTextChangedListener( new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String filterText = s.toString();
//                if(filterText.length() > 0){
//                    listview.setFilterText( filterText );
//                }else{
//                    listview.clearTextFilter();
//                }
                ((SearchAdapter)listview.getAdapter()).getFilter().filter(filterText) ;
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }


        } );

//        뒤로가기
        final View btn_back = findViewById(R.id.btn_back_search);
        btn_back.setOnClickListener(this);
        btn_back.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Animation anim = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.scale );
                        btn_back.startAnimation( anim );
                    case MotionEvent.ACTION_UP:
                        Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.scale2 );
                        btn_back.startAnimation( anim1 );
                }
                return false;
            }
        } );

//        검색버튼
        View btn_search = findViewById(R.id.btn_search);
        btn_search.setOnClickListener(this);



    }

    private void loadItemsFromDB() {

            if (items == null) {
                items = new ArrayList<Product>();
            }
            //Shared에서 불러온 arrayList인 products를 저장한다.
            //sharedpreference로부터 불러오기
            storedArrayString = pref.loadPreference( "item", "product_list" );
            //Log.e("storedArrayString", storedArrayString);
            // /json array로 변환.
            type = new TypeToken<ArrayList<Product>>() {
            }.getType();
            items = gson.fromJson( storedArrayString, type );

            Log.e( "array+db:", items.get( 0 ).getProid() );
            Log.e( "array+db:", items.get( 0 ).getName() );
            Log.e( "array+db:", items.get( 0 ).getRentValue() );
            Log.e( "array+db:", items.get( 0 ).getRentGijun() );
            Log.e( "array+db:", items.get( 0 ).getDepositValue() );
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_back_search:
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                finish();                break;

            case R.id.btn_search:
//                Intent intent1 = new Intent(SearchActivity.this, SearchResultActivity.class);
//                intent1.setAction(Intent.ACTION_VIEW);
//
//                EditText edit_search = findViewById(R.id.edit_search);
//                intent1.putExtra("search",edit_search.getText().toString());
//                startActivity(intent1);
//                finish();

                break;
        }
    }


}
