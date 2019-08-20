package com.example.joanne.practice_layout01;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Joanne on 2018-02-07.
 */

public class FavoriteViewHolder {

    //아이템 구성요소 정의 - 체크박스,이미지,제목, 컨텐츠(대여료,보증금 관련), 문의하기 버튼, 대여하기 버튼
    public CheckBox chk;
    public ImageView image;
    public TextView subject;

    public TextView rentValue;
    public TextView rentGijun;
    public TextView depositValue;

    public Button btnQuestion;
    public Button btnRent;
}
