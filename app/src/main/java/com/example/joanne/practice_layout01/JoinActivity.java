package com.example.joanne.practice_layout01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Joanne on 2018-03-04.
 */

public class JoinActivity extends AppCompatActivity {


    EditText edit_name, edit_id, edit_pw, edit_email, edit_contact;
    String image, name, id, pw, email, contact;
    Uri uri;
    ImageView edit_image;

    String storedString, jsonString;
    ArrayList<UserItem> arr_user;
    Gson gson;

    SharedPreferences users;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.joinactivity_intro );

        //arr_user = new ArrayList<UserItem>();
        gson = new Gson();


        edit_image = findViewById( R.id.join_profile );
        edit_name = findViewById( R.id.join_name );
        edit_id = findViewById( R.id.join_id );
        edit_pw = findViewById( R.id.join_pw );
        PasswordTransformationMethod PTM = new PasswordTransformationMethod();
        edit_pw.setTransformationMethod(PTM);
        edit_email = findViewById( R.id.join_email );
        edit_contact = findViewById( R.id.join_contact );
        Button joinBtn = findViewById( R.id.joinbtn );

        edit_image.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Intent.ACTION_PICK );
                intent.setType( MediaStore.Images.Media.CONTENT_TYPE );
                intent.setData( MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
                startActivityForResult( intent, 100 );


            }
        } );


        joinBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    image = uri.toString();
                    Log.e( "Image:", image );
                    name = edit_name.getText().toString();
                    Log.e( "name:", name );
                    id = edit_id.getText().toString();
                    Log.e( "id:", id );
                    pw = edit_pw.getText().toString();
                    Log.e( "pw:", pw );
                    email = edit_email.getText().toString();
                    Log.e( "email:", email );
                    contact = edit_contact.getText().toString();
                    Log.e( "contact:", contact );
                    Log.e( "회원가입 요소 입력 완료:", "OK" );

                    if (image == null || name == null || id == null || pw == null || email == null || contact == null) {
                        //image 가 null 일 때
                        if (image == null && name != null && id != null && pw != null && email != null && contact != null) {
                            Toast.makeText( JoinActivity.this, "Profile Image를 등록해주세요.", Toast.LENGTH_SHORT ).show();

                            //name이 null 일 때
                        } else if (image != null && name == null && id != null && pw != null && email != null && contact != null) {
                            Toast.makeText( JoinActivity.this, "이름을 입력해주세요.", Toast.LENGTH_SHORT ).show();

                            //id 가 null 일 때
                        } else if (image != null && name != null && id == null && pw != null && email != null && contact != null) {
                            Toast.makeText( JoinActivity.this, "ID를 입력해주세요.", Toast.LENGTH_SHORT ).show();

                            //pw 가 null 일 때
                        } else if (image != null && name != null && id != null && pw == null && email != null && contact != null) {
                            Toast.makeText( JoinActivity.this, "Password를 입력해주세요.", Toast.LENGTH_SHORT ).show();

                            //email이 null 일 때
                        } else if (image != null && name != null && id != null && pw != null && email == null && contact != null) {
                            Toast.makeText( JoinActivity.this, "email을 입력해주세요.", Toast.LENGTH_SHORT ).show();

                            //contact 가 null 일 때
                        } else if (image != null && name != null && id != null && pw != null && email != null && contact == null) {
                            Toast.makeText( JoinActivity.this, "연락처를 입력해주세요.", Toast.LENGTH_SHORT ).show();


                        }

                        Toast.makeText( JoinActivity.this, "모든 항목을 입력했는지 확인해주세요.", Toast.LENGTH_SHORT ).show();

                        //모든 항목을 입력하였을 때, 로그인 화면으로 이동.
                    } else if (image != null && name != null && id != null && pw != null && email != null && contact != null) {
                        Toast.makeText( JoinActivity.this, "축하합니다! 회원가입이 완료되었습니다.", Toast.LENGTH_SHORT ).show();

                        UserItem user = new UserItem( image, name, id, pw, email, contact );


                        //먼저 shared에 있는 데이터 불러오기.
                        users = getSharedPreferences( "users", MODE_PRIVATE );

//                    SharedPreferences.Editor editor = users.edit();
//                    editor.remove( "user" );
//                    editor.commit();
//                    Log.e( "셰어드 삭제","OK" );
//                    Log.e( "셰어드", String.valueOf( editor.remove( "user" ) ) );
                        jsonString = users.getString( "user", null );
                        if (jsonString == null) {
                            arr_user = new ArrayList<UserItem>();
                        } else {
                            Type type = new TypeToken<ArrayList<UserItem>>() {
                            }.getType();
                            arr_user = gson.fromJson( jsonString, type );
                        }
                        arr_user.add( user );
                        storedString = gson.toJson( arr_user );
                        SharedPreferences.Editor editor = users.edit();
                        editor.putString( "user", storedString );
                        editor.commit();

                        Log.e( "셰어드 저장완료:", "OK" );
                        Log.e( "셰어드 저장완료:", storedString );


                        Intent intent = new Intent( JoinActivity.this, LogInActivity.class );
                        intent.putExtra( "image", image );
                        intent.putExtra( "name", name );
                        intent.putExtra( "id", id );
                        intent.putExtra( "pw", pw );
                        intent.putExtra( "email", email );
                        intent.putExtra( "contact", contact );
                        intent.putExtra( "join", true);

                        startActivity( intent );
                        finish();


                        Log.e( "인텐트 전달 완료:", "OK" );

                    }


            }//onclick
        } );//버튼


    }//oncreate


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == 100) {
            try {
                uri = data.getData();
                edit_image.setImageURI( uri );
                Log.e( "이미지 등록", "완료" );

            } catch (Exception e) {
                Log.e( "이미지:", "이미지 등록 취소" );
            }
        }
    }
}
