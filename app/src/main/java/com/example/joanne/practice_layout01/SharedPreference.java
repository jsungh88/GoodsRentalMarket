package com.example.joanne.practice_layout01;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

/**
 * Created by Joanne on 2018-02-11.
 */

//이 클래스 망... 그냥 참고용으로 쓰시오


public class SharedPreference implements View.OnClickListener{

    Context context;
    Activity activity;
    SharedPreferences pref;

    //기본은 false 셋팅.
    static Boolean setsp = false;


    public SharedPreference(Activity activity) {
        this.activity = activity;
    }
    public SharedPreference(Context context) {
        this.context = context;
    }


    public void savePreference(String key, String value, String sp_name){
        setsp(sp_name);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
        Log.i("(save_key,data:",key+","+value);

    }

    public void savePreference_regit(String key, String value, String sp_name){
        setsp01(sp_name);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
        Log.i("(save_key,data:",key+","+value);

    }

    //상품 item 저장!
    public void savePreference(int key, String value, String sp_name){
        setsp(sp_name);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(String.valueOf(key), value);
        editor.commit();
        Log.i("(save_key,data:",key+","+value);

    }

    //상품번호저장!(+좋아요 포지션값 저장!!)
    public void savePreference(String key, Integer value,String sp_name){
        setsp01(sp_name);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key,value);
        editor.commit();
        Log.i("(save_key,data:",key+","+value);

    }



    //String return
    public String loadPreference(String key, String default_vlaue, String sp_name){
        setsp(sp_name);
        Log.e("load_key:", key);
        String str = pref.getString(key,default_vlaue);
        Log.e("load_value:", str);
        return str;

    }

    //Integer key - 상품 불러오기!
    public String loadPreference(int key, String default_vlaue, String sp_name){
        setsp(sp_name);
        Log.e("load_key:", String.valueOf(key));
        String str = pref.getString(String.valueOf(key),default_vlaue);
        Log.e("load_value:", str);
        return str;

    }

    //Integer key - 상품번호 리턴!
    public String loadPreference(String key, String sp_name){
        setsp(sp_name);
        Log.e("load_key:", String.valueOf(key));
        String str = pref.getString(key,null);
     /*   Log.e("load_value:", str);*/
        return str;
    }

    //Integer key - 상품번호 리턴!
    public String loadPreference_regit(String key, String sp_name){
        setsp01(sp_name);
        Log.e("load_key:", String.valueOf(key));
        String str = pref.getString(key,null);
     /*   Log.e("load_value:", str);*/
        return str;
    }

    //Integer key - 상품번호 리턴!
    public int loadPreference(String key, Integer default_vlaue, String sp_name){
        setsp01(sp_name);
        Log.e("load_key:", String.valueOf(key));
        Integer i = pref.getInt(key,default_vlaue);
        Log.e("load_value:", String.valueOf(i));
        return i;
    }

    //Integer key - 좋아요 갯수!
    public int loadPreference2(String key, Integer default_vlaue, String sp_name){
        setsp01(sp_name);
        Log.e("load_key:", String.valueOf(key));
        Integer i = pref.getInt(key,default_vlaue);
        Log.e("load_value:", String.valueOf(i));
        return i;
    }

    public void removePreference(String key,String sp_name){
        setsp(sp_name);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.commit();

    }

    public void removePreference(String sp_name){
        setsp(sp_name);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();

    }


    public void setsp(String name){

        pref = this.activity.getSharedPreferences(name,Activity.MODE_PRIVATE);
    }





    public void setsp01(String name){

        pref = this.context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }


    @Override
    public void onClick(View v) {

    }


}
