package com.example.joanne.practice_layout01;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Joanne on 2018-02-07.
 */

public class MyShopEditActivity extends AppCompatActivity {

    Button btn_image, btn_cancel, btn_ok;
    EditText product_edit, rent_edit, deposit_edit;

    String edit = "2";
    int position;
    int get_position;

    MyShopAdapter adapter;
    MyShopItem item;
    ArrayList<MyShopItem> items;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        //setContentView(R.layout.register_sub);
       // setContentView( R.layout.myshopedit_sub );

        product_edit = (EditText) findViewById( R.id.txt_goodsname_edit );
        rent_edit = (EditText) findViewById( R.id.txt_rentfee_edit );
        deposit_edit = (EditText) findViewById( R.id.txt_deposit_edit );
        //btn_image = (Button) findViewById(R.id.btn_image_upload);
        btn_cancel = (Button) findViewById( R.id.btn_cancel_register );
        btn_ok = (Button) findViewById( R.id.btn_ok_register );

        //adapter = new MyShopAdapter();
        items = new ArrayList<>();

        Intent intent = getIntent();
        edit = intent.getStringExtra( "edit" );


        if (edit.equals( "1" )) {
            String product = intent.getStringExtra( "product" );
            String rent = intent.getStringExtra( "rent" );
            String deposit = intent.getStringExtra( "deposit" );
            //+ 그리고 기준도...
            get_position = intent.getIntExtra( "position", 0 );

            product_edit.setText( product );
            rent_edit.setText( rent );
            deposit_edit.setText( deposit );
        }


        btn_ok.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //수정일 때
                if (edit.equals( "1" )) {

                    Log.e( "추가", "넘어왔니?" );
                    //데이터 입력해서 변수 저장
                    String product = product_edit.getText().toString();
                    String rent = rent_edit.getText().toString();
                    String deposit = deposit_edit.getText().toString();

                    Intent intent = new Intent();
                    intent.putExtra( "product", product );
                    intent.putExtra( "rent", rent );
                    intent.putExtra( "deposit", deposit );
                    intent.putExtra( "position", get_position );
                    setResult( RESULT_OK, intent );
                    finish();
                    Toast.makeText( MyShopEditActivity.this, "저장완료", Toast.LENGTH_SHORT ).show();
                    Log.e( "product", product );
                    Log.e( "rent", rent );
                    Log.e( "deposit", deposit );


                    //추가일떄
                } else if (edit.equals( "2" )) {

                    //수정데이터 입력해서 변수 저장
                    String product = product_edit.getText().toString();
                    String rent = rent_edit.getText().toString();
                    String deposit = deposit_edit.getText().toString();

                    //원래 인텐트 가져오고, 거기에 값 담아서 셋팅해서 돌려보내!
                    Intent intent2 = getIntent();

                    intent2.putExtra( "product", product );
                    intent2.putExtra( "rent", rent );
                    intent2.putExtra( "deposit", deposit );

                    setResult( RESULT_OK, intent2 );
                    finish();

                    Log.e( "수정데이터", "저장완료" );

                }
            }
        } );


    }
}
