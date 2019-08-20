package com.example.joanne.practice_layout01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

public class LogInActivity extends AppCompatActivity {

    //join에서 가져온 값.
    String getImage, getName, getId, getPw, getEmail, getContact;
    //입력 ID,PW값
    String id, pw, email, image, name, contact;
    String sid, spw, semail, simage, sname, scontact;
    //셰어드에서 가져온 ID,PW값
    String storedId, storedPw, storedEmail, storedImage, storedName, storedContact;
    //셰어드 풀 때 필요한 값.
    String jsonString, storedString;
    ArrayList<UserItem> arr_user;
    UserItem login;
    Gson gson;

    static boolean fromJoin, ok;
    SharedPreferences loginUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.loginactivity_intro );

        ImageView imaged = findViewById( R.id.login_ci );
        Animation anim = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.shake );
        imaged.startAnimation( anim );



        final EditText edit_id = findViewById( R.id.login_id );
        final EditText edit_pw = findViewById( R.id.login_pw );
        PasswordTransformationMethod PTM = new PasswordTransformationMethod();
        edit_pw.setTransformationMethod(PTM);

        final Button btn_login = findViewById( R.id.loginbtn );
        final Button btn_join = findViewById( R.id.joinbtn );

        //overridePendingTransition(R.anim.fade, R.anim.hold);
        //회원가입해서 바로 넘어왔을 때 Id,PW 셋팅 용도.
        Intent intent = getIntent();
        //getImage = intent.getStringExtra( "image" );
        //getName = intent.getStringExtra( "name" );
        getId = intent.getStringExtra( "id" );
        getPw = intent.getStringExtra( "pw" );
        //getEmail = intent.getStringExtra( "email" );
        //getContact = intent.getStringExtra( "contact" );
        fromJoin = intent.getBooleanExtra( "join", false );
        Log.e( "JointoLogin", "ok" );
        //Log.e( "getImage:", getImage );

        if (fromJoin) {
            edit_id.setText( getId );
            edit_pw.setText( getPw );
        }


        arr_user = new ArrayList<UserItem>();
        gson = new Gson();

        //로그인 사용자 셰어드 생성.
        loginUser = getSharedPreferences( "loginuser", MODE_PRIVATE );


        //회원가입 셰어드 불러오기.
        SharedPreferences users = getSharedPreferences( "users", MODE_PRIVATE );
        jsonString = users.getString( "user", null );
        if (jsonString == null) {
            arr_user = new ArrayList<UserItem>();
        }
        Type type = new TypeToken<ArrayList<UserItem>>() {
        }.getType();
        arr_user = gson.fromJson( jsonString, type );
        Log.e( "셰어드 불러오기:", "OK" );

        btn_login.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Animation anim = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.scale );
                        btn_login.startAnimation( anim );
                    case MotionEvent.ACTION_UP:
                        Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.scale2 );
                        btn_login.startAnimation( anim1 );
                }
                return false;
            }
        } );

        btn_login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //회원가입을 거쳐 로그인 할 경우,(fromJoin : 가입할때 넘어온 boolean 값.)
                if (fromJoin) {

                    Log.e( "회원가입 거침:", "OK" );
                    int j = 0;
                    //입력 안했을 경우 toast.
                    if (getId.isEmpty() || getPw.isEmpty()) {
                        Toast.makeText( LogInActivity.this, "ID 또는 PASSWORD 를 입력해주세요.", Toast.LENGTH_SHORT ).show();
                    }

                    //arr_user에 회원 데이터가 없을 경우,
                    if (arr_user.isEmpty()) {
                        Toast.makeText( LogInActivity.this, "가입된 회원 데이터가 없습니다", Toast.LENGTH_SHORT ).show();
                        //arr_user에 회원 데이터가 있을 경우,
                        Log.e( "arr_user", "null" );
                    } else {
                        Log.e( "arr_user", "null아님" );
                        for (int i = 0; i < arr_user.size(); i++) {
                            if (!getId.equals( arr_user.get( i ).getId() )) {
                                storedId = arr_user.get( i ).getId();
                                storedPw = arr_user.get( i ).getPw();
                                storedName = arr_user.get( i ).getName();
                                storedEmail = arr_user.get( i ).getEmail();
                                storedContact = arr_user.get( i ).getContact();
                                storedImage = arr_user.get( i ).getImage();
                                Log.e( "셰어드id:" + i, storedId );
                                Log.e( "셰어드pw:" + i, storedPw );
                                Log.e( "셰어드name:" + i, storedName );
                                Log.e( "셰어드email:" + i, storedEmail );
                                Log.e( "셰어드contact:" + i, storedContact );
                                Log.e( "셰어드image:" + i, storedImage );
                                continue;
                            } else {
                                Log.e( "id:", getId );
                                getId = arr_user.get( i ).getId();
                                getPw = arr_user.get( i ).getPw();
                                getName = arr_user.get( i ).getName();
                                getEmail = arr_user.get( i ).getEmail();
                                getContact = arr_user.get( i ).getContact();
                                getImage = arr_user.get( i ).getImage();
                                Log.e( "id", getId );
                                Log.e( "pw", getPw );
                                Log.e( "name", getName );
                                Log.e( "email", getEmail );
                                Log.e( "contact", getContact );
                                Log.e( "image", getImage );
                            }
                        }//for문 끝.
                        //ID와 PASSWORD가 null이 아니면 메인 화면으로 이동.
                        //ID와 PASSWORD가 회원정보에 등록되어 있지 않은 경우, 다시한번 확인해달라는 Toast, 등록되어있는 경우 MAin으로 이동.
                        if (getId.equals( storedId ) && getPw.equals( storedPw ))
                            Log.e( "id&pw 일치", "OK" );
                        Log.e( "getId:", getId );
                        Log.e( "getId:", getPw );
                        Intent intent = new Intent( LogInActivity.this, MainActivity.class );
                        intent.setAction( Intent.ACTION_VIEW );
//                        intent.putExtra( "id", getId );
//                        intent.putExtra( "pw", getPw );
//                        intent.putExtra( "name", getName );
//                        intent.putExtra( "email", getEmail );
//                        intent.putExtra( "contact", getContact );
//                        intent.putExtra( "image", getImage );

//                        Log.e( "id:", getId );
//                        Log.e( "getPw:", getPw );
//                        Log.e( "name:", getName );
//                        Log.e( "email:", getEmail );
//                        Log.e( "contact:", getContact );
//                        Log.e( "image:", getImage );

                        login = new UserItem( getImage, getName, getId, getPw, getEmail, getContact );
                        Log.e( "login객체:", String.valueOf( login ) );
                        storedString = gson.toJson( login );
                        SharedPreferences.Editor editor = loginUser.edit();
                        //유저 ID로 셰어드 저장.
                        editor.putString( login.getId(), storedString );
                        editor.commit();

                        Log.e( "셰어드 저장완료:", "OK" );
                        Log.e( "셰어드 저장완료:", storedString );

                        editor.putString( "id",getId );
                        editor.commit();
                        Log.e( "id:",getId );


                        startActivity( intent );

                        //로그인 회원 정보 shared 저장
                        //먼저 shared에 있는 데이터 불러오기.
//                    SharedPreferences.Editor editor = users.edit();
//                    editor.remove( "user" );
//                    editor.commit();
//                    Log.e( "셰어드 삭제","OK" );
//                    Log.e( "셰어드", String.valueOf( editor.remove( "user" ) ) );


//                        if (getId != storedId && getPw == storedPw)
//                            Log.e( "id 일치x", "OK" );
//                            Toast.makeText( LogInActivity.this, "ID를 다시 한 번 확인해주세요.", Toast.LENGTH_SHORT ).show();
//                        if (getPw != storedPw && getId == storedId)
//                            Log.e( "pw 일치x", "OK" );
//                            Toast.makeText( LogInActivity.this, "PASSWORD를 다시 한 번 확인해주세요.", Toast.LENGTH_SHORT ).show();
                    }

                    //회원가입을 거치지 않고 바로 입력했을 경우,

                } else if (!fromJoin) {
                    Log.e( "회원가입 안거침:", "OK" );
                    int j = 0;

                    id = edit_id.getText().toString();
                    pw = edit_pw.getText().toString();
                    Log.e( "입력한 id:", id );
                    Log.e( "입력한 pw:", pw );

                    //ID, PW 입력 안했을 경우 toast.
                    if (id.isEmpty() || pw.isEmpty()) {
                        Toast.makeText( LogInActivity.this, "ID 또는 PASSWORD 를 입력해주세요.", Toast.LENGTH_SHORT ).show();
                    }

                    //shared의 회원정보와 대조해서 정보가 있으면 main으로 이동, 아니면 에러 알림 표시.
                    //arr_user에 회원 데이터가 없을 경우,
                    if (arr_user.isEmpty()) {
                        Toast.makeText( LogInActivity.this, "가입된 회원 데이터가 없습니다", Toast.LENGTH_SHORT ).show();
                        //arr_user에 회원 데이터가 있을 경우,
                        Log.e( "arr_user", "null" );
                    }

                    Log.e( "arr_user", "null아님" );
                    for (int i = 0; i < arr_user.size(); i++) {
                        Log.e( "id:" + i, id );

                        if (!id.equals( arr_user.get( i ).getId() )) {
                            storedId = arr_user.get( i ).getId();
                            storedPw = arr_user.get( i ).getPw();
                            storedName = arr_user.get( i ).getName();
                            storedEmail = arr_user.get( i ).getEmail();
                            storedContact = arr_user.get( i ).getContact();
                            storedImage = arr_user.get( i ).getImage();
                            Log.e( "셰어드id:" + i, storedId );
                            Log.e( "셰어드pw:" + i, storedPw );
                            Log.e( "셰어드name:" + i, storedName );
                            Log.e( "셰어드email:" + i, storedEmail );
                            Log.e( "셰어드contact:" + i, storedContact );
                            Log.e( "셰어드image:" + i, storedImage );
                            continue;
                        } else {
                            Log.e( "id:", id );
                            id = arr_user.get( i ).getId();
                            pw = arr_user.get( i ).getPw();
                            name = arr_user.get( i ).getName();
                            email = arr_user.get( i ).getEmail();
                            contact = arr_user.get( i ).getContact();
                            image = arr_user.get( i ).getImage();
                            Log.e( "id", id );
                            Log.e( "pw", pw );
                            Log.e( "name", name );
                            Log.e( "email", email );
                            Log.e( "contact", contact );
                            Log.e( "image", image );
                        }//for문 끝
                    }
                    Log.e( "여기로 오니?", "ㅇㅇ" );
                    //ID와 PASSWORD가 null이 아니면 메인 화면으로 이동.
                    //ID와 PASSWORD가 회원정보에 등록되어 있지 않은 경우, 다시한번 확인해달라는 Toast, 등록되어있는 경우 MAin으로 이동.
                    Log.e( "입력id:", id );
                    Log.e( "입력pw:", pw );
                    Log.e( "입력name:", name );
                    Log.e( "입력email:", email );
                    Log.e( "입력contact:", contact );
                    Log.e( "입력image:", image );

                    if (id.equals( storedId ) && pw.equals( storedPw ))
//                        Log.e( "id&pw 일치", "OK" );

//                    intent.putExtra( "id", id );
//                    intent.putExtra( "pw", pw );
//                    intent.putExtra( "name", name );
//                    intent.putExtra( "email", email );
//                    intent.putExtra( "contact", contact );
//                    intent.putExtra( "image", image );
//                    Log.e( "id", id );
//                    Log.e( "pw", pw );
//                    Log.e( "name", name );
//                    Log.e( "email", email );
//                    Log.e( "contact", contact );
//                    Log.e( "image", image );




                    Toast.makeText( LogInActivity.this, "환영합니다!", Toast.LENGTH_SHORT ).show();
//                        if (id != storedId && pw == storedPw)
//                            Log.e( "id 일치x", "OK" );
//                        Toast.makeText( LogInActivity.this, "ID를 다시 한 번 확인해주세요.", Toast.LENGTH_SHORT ).show();
//                        if (pw != storedPw && id == storedId)
//                            Log.e( "pw 일치x", "OK" );
//                        Toast.makeText( LogInActivity.this, "PASSWORD를 다시 한 번 확인해주세요.", Toast.LENGTH_SHORT ).show();
                    login = new UserItem( image, name, id, pw, email, contact );
                    Log.e( "login객체:", String.valueOf( login ) );
                    Log.e( "getImage",image );
                    storedString = gson.toJson( login );
                    SharedPreferences.Editor editor = loginUser.edit();
                    //유저 ID로 셰어드 저장.
                    editor.putString( login.getId(), storedString );
                    editor.commit();

                    Log.e( "셰어드 저장완료:", "OK" );
                    Log.e( "셰어드 저장완료:", storedString );


                    editor.putString( "id",id );
                    editor.commit();
                    Log.e( "id:",id );

                    Intent intent = new Intent( LogInActivity.this, MainActivity.class );
                    intent.setAction( Intent.ACTION_VIEW );
                    startActivity( intent );
                    overridePendingTransition(R.anim.push_up_in, R.anim.hold);
                    //overridePendingTransition(R.anim.slide_left, R.anim.hold);
                }
            }
        } );

        btn_join.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Animation anim = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.scale );
                        btn_join.startAnimation( anim );
                    case MotionEvent.ACTION_UP:
                        Animation anim1 = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.scale2 );
                        btn_join.startAnimation( anim1 );
                }
                return false;
            }
        } );

        btn_join.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( LogInActivity.this, JoinActivity.class );
                intent.setAction( Intent.ACTION_VIEW );
                startActivity( intent );

            }
        } );


    }


}
