package com.example.joanne.practice_layout01;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Joanne on 2018-02-12.
 */

public class MyOrderListAdapter extends ArrayAdapter {
    LayoutInflater inflater;
    int resoureId;

    //    public interface ListBtnClickListener{
    //        void onListBtnClick(int position);
    //    }
    //    private ListBtnClickListener listBtnClickListener;


    public MyOrderListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MyOrderListItem> list) {
        super(context, resource, list);

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resoureId = resource;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(resoureId, parent, false);
        }
        //ImageView img = convertView.findViewById(R.id.myorder_img_image);
        CheckBox chkBtn = convertView.findViewById(R.id.myorder_chk_checkbox);
        ImageView image = convertView.findViewById(R.id.myorder_img_image);
        TextView product = convertView.findViewById(R.id.myorder_cont_subject);
        TextView rentDate = convertView.findViewById(R.id.myorder_cont_peroid_value);
        TextView peroid = convertView.findViewById(R.id.myorder_cont_period);
        TextView rentSum = convertView.findViewById(R.id.myorder_cont_rent_value);
        TextView deposit = convertView.findViewById(R.id.myorder_cont_deposit_value);
        Button questBtn = convertView.findViewById(R.id.myorder_cont_btn_quest);

        convertView.setTag(convertView);

        final MyOrderListItem myOrderListItem = (MyOrderListItem) getItem(position);

        image.setImageURI( Uri.parse( myOrderListItem.getSimage() ) );
        product.setText(myOrderListItem.getProductName());
        rentDate.setText(myOrderListItem.getRentDate());
        peroid.setText(myOrderListItem.getRentGigan());
        rentSum.setText(myOrderListItem.getRentValue());
        deposit.setText(myOrderListItem.getDeposit());
        chkBtn.setOnClickListener(new View.OnClickListener() {

            //클릭했을때, 체크되어있으면 체크해제하고, 체크안되어 있으면 체크해라
            @Override
            public void onClick(View v) {
                if (myOrderListItem.isChecked) {
                    myOrderListItem.isChecked = false;
                } else {
                    myOrderListItem.isChecked = true;
                }
                notifyDataSetChanged();
            }
        });

        //문의하기 버튼: 해당상품 올린 판매자 dial 정보 끌어오기 - dial 인텐트 실행해야함.
        questBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri_dial = Uri.parse("tel:"+myOrderListItem.getBtnDial());
                Intent intent_dial = new Intent( Intent.ACTION_DIAL, uri_dial );
                view.getContext().startActivity(intent_dial);

            }
        });

        convertView.getTag();
        //체크버튼은, 체크상태를 셋팅한다.
        chkBtn.setChecked(myOrderListItem.isChecked);

        return convertView;

    }
}