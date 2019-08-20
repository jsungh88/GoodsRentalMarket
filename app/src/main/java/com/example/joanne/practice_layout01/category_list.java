package com.example.joanne.practice_layout01;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class category_list extends Dialog {

    public category_list(@NonNull Context context) {
        super( context );
    }

    private RadioGroup rg;
    private RadioButton btn1;
    private RadioButton btn2;
    private RadioButton btn3;
    private RadioButton btn4;
    private RadioButton btn5;
    private RadioButton btn6;
    private RadioButton btn7;

    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        this.requestWindowFeature( Window.FEATURE_NO_TITLE);
        setContentView( R.layout.activity_category_list );

        rg = (RadioGroup)findViewById( R.id.radioGroup );
        btn1 = (RadioButton)findViewById( R.id.radioBtn1 );
        btn2 = (RadioButton)findViewById( R.id.radioBtn2 );
        btn3 = (RadioButton)findViewById( R.id.radioBtn3 );
        btn4 = (RadioButton)findViewById( R.id.radioBtn4 );
        btn5 = (RadioButton)findViewById( R.id.radioBtn5 );
        btn6 = (RadioButton)findViewById( R.id.radioBtn6 );
        btn7 = (RadioButton)findViewById( R.id.radioBtn7 );


        RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                 if(checkedId == R.id.radioBtn1){
                     category = "취미/도서/티켓";
                     Log.e("category:","취미/도서/티켓");
                 }else if(checkedId == R.id.radioBtn2){
                     category = "가구/생활/주방/식품";
                     Log.e("category:","가구/생활/주방/식품");
                 }else if(checkedId == R.id.radioBtn3){
                     category = "출산/유아동";
                     Log.e("category:","출산/유아동");
                 }else if(checkedId == R.id.radioBtn4){
                     category = "가전/디지털/컴퓨터";
                     Log.e("category:","가전/디지털/컴퓨터");
                 }else if(checkedId == R.id.radioBtn5){
                     category = "패션/뷰티";
                     Log.e("category:","패션/뷰티");
                 }else if(checkedId == R.id.radioBtn6){
                     category = "스포츠/레저용품";
                     Log.e("category:","스포츠/레저용품");
                 }else if(checkedId == R.id.radioBtn7){
                     category = "명품";
                     Log.e("category:","명품");
                 }
                Intent intent = new Intent();
                intent.putExtra( "카테고리",category );
                Log.e( "라디오버튼:","데이터보내기 성공" );
            }
        };

    }//onCreate 끝

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }




}


//    Dialog dialog = new Dialog(this);
//        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE);
//                dialog.getWindow().setBackgroundDrawable( new ColorDrawable( Color.TRANSPARENT ) );
////        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
////        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//                dialog.setContentView( R.layout.activity_category_list );