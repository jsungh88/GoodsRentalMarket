package com.example.joanne.practice_layout01;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Joanne on 2018-02-12.
 */

public class MainGridItem extends LinearLayout{

    //ImageView favIcon;
    TextView favCount;

    ImageView image;
    TextView name;
    TextView rentValue;
    TextView rentGijun;
    TextView depositValue;
    TextView desc;

    //ImageView locIcon;
    TextView locTxt;
    TextView state;



    public MainGridItem(Context context) {
        super(context);
        init(context);
    }


    public void init(Context context) {

//        View view = LayoutInflater.from(context).inflate(R.layout.grid_item_main, this);
//        //favIcon = (ImageView) findViewById(R.id.img_favorite01);
//        favCount = (TextView) findViewById(R.id.txt_favoritecount);
//        image = (ImageView) findViewById(R.id.img_goods01);
//        name = (TextView) findViewById(R.id.goods_name01);
//        //rentKey = (TextView) findViewById(R.id.goods_rentfee_key01);
//        rentValue = (TextView) findViewById(R.id.goods_rentfee_value01);
//        //rentUnit = (TextView) findViewById(R.id.goods_rentfee_unit01);
//        rentGijun = (TextView) findViewById(R.id.goods_rentfee_standard01);
//        //depositKey = (TextView) findViewById(R.id.goods_deposit_key01);
//        depositValue = (TextView) findViewById(R.id.goods_deposit_value01);
//        //depositUnit = (TextView) findViewById(R.id.goods_deposit_unit01);
//        desc = (TextView)findViewById(R.id.txtbox_description_edit_confirm);
//
//        //locIcon = (ImageView) findViewById(R.id.img_loc01);
//        locTxt = (TextView) findViewById(R.id.txt_loc01);
//        //state = (TextView) findViewById(R.id.goods_state01);


    }

    //좋아요갯수, 상품이미지, 상품명, 대여료, 대여료기준(1일/1주일/1개월), 보증금, 위치, 대여상태
    public void setData(Product one) {

//        //favIcon.setImageDrawable(one.getFavIcon());
//        favCount.setText(Integer.toString((one.getFavCount())));
//        image.setImageURI( Uri.parse(one.getSimage()));
//        name.setText(one.getName());
//        rentValue.setText(one.getRentValue());
//        rentGijun.setText(one.getRentGijun());
//        depositValue.setText(one.getDepositValue());
////        desc.setText(one.getDesc());
//        //locIcon.setImageDrawable(one.getLocIcon());
//        locTxt.setText(one.getLocTxt());
//        //state.setText(one.getState());
    }
}

