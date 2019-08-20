package com.example.joanne.practice_layout01;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Joanne on 2018-02-07.
 */

public class OrderListAdapter extends BaseAdapter implements Filterable{

    Context context;
    ArrayList<MyOrderListItem> array = new ArrayList<>(  );
    ArrayList<MyOrderListItem> filtered_array = array;
    Filter listFilter;

    LayoutInflater inflater;
    int resourceId;

    public OrderListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MyOrderListItem> list) {
        this.context = context;
        this.array = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resourceId=resource;
    }

    @Override
    public int getCount() {
        return filtered_array.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Log.e( "진입했습니가?","네" );
        final int pos = position;

        if(convertView==null){
            convertView = inflater.inflate(resourceId,parent,false);

        }
        //CheckBox chkBtn = (CheckBox)convertView.findViewById(R.id.ordlist_checkbox);
        //ImageView image = convertView.findViewById( R.id.ordlist_Image );
        TextView product = (TextView)convertView.findViewById(R.id.ordlist_productname);
        TextView user = (TextView)convertView.findViewById(R.id.ordlsit_ordername);
        TextView rent = (TextView)convertView.findViewById(R.id.ordlist_rentdate_value);
        TextView date = (TextView)convertView.findViewById(R.id.ordlist_date_value);
        ImageView dial = (ImageView)convertView.findViewById( R.id.ordlist_btn_dial );
        ImageView sms = (ImageView)convertView.findViewById( R.id.ordlist_btn_sms );

        convertView.setTag(convertView);


        final MyOrderListItem item = (MyOrderListItem) getItem(position);

        //image.setImageURI( Uri.parse( item.getSimage() ) );
        user.setText(item.getOrdername());
        product.setText(item.getProductName());
        rent.setText(item.getRentGigan());
        date.setText(item.getRentDate());
        dial.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri_dial = Uri.parse("tel:"+item.getBtnDial() );
                Intent intent_dial = new Intent( Intent.ACTION_DIAL, uri_dial );
                context.startActivity( intent_dial );
            }
        } );
        sms.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse( item.getBtnDial() );
                Intent it = new Intent( Intent.ACTION_SENDTO, uri );
                it.putExtra( "sms_body", "안녕하세요, 주문해주셔서 감사합니다. " );
                context.startActivity(it);
            }
        } );


//        chkBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(item.isChecked){
//                    item.isChecked=false;
//                }else{
//                    item.isChecked=true;
//                }
//
//                notifyDataSetChanged();
//
//            }
//        });

        convertView.getTag();
//        chkBtn.setChecked(item.isChecked);
//        convertView.setTranslationY(100); // 원래 등장하는 위치보다 조금 더 내려주고..
//        convertView.setAlpha(0.f); // visibility 를 꺼준 후..
//        convertView.animate()
//                .translationY(0)
//                .alpha(1.f)
//                .setStartDelay(delayEnterAnimation ? 20 * (position) : 0) // position 마다 시간차를 조금 주고..
//                .setInterpolator(new DecelerateInterpolator(2.f))
//                .setDuration(300)
//                .setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        animationsLocked = true; // 진입시에만 animation 하도록 하기 위함
//                    }
//                })
//                .start();
        return convertView;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return filtered_array.get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        if (listFilter == null) {
            listFilter = new ListFilter();
            Log.e( "getFilter:", "ㅇㅇ" );
        }
        return listFilter;
    }


    public class ListFilter extends Filter {
        Context context;

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            Log.e( "여기도 들렀나?","ㅇㅇ" );

            if (constraint == null || constraint.length() == 0) {
                results.values = array;
               results.count = array.size();
                Log.e( "설마 여기?","ㅇㅇ" );
            } else {
                ArrayList<MyOrderListItem> itemList = new ArrayList<MyOrderListItem>();
                Log.e( "여기도 들렀나?","ㅇㅇ" );
                for (MyOrderListItem item : array) {
                    Log.e( "포문?","ㅇㅇ" );
                    Log.e( "필터item:", item.getRentname() );
                    if (item.getRentname().toUpperCase().contains( constraint.toString().toUpperCase() )) {
                        itemList.add( item );
                        Log.e( "필터item:", String.valueOf( item ) );
                        Log.e( "필터item:", item.getRentname() );
                    }
                }
                results.values = itemList;
                results.count = itemList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            //update listview by filtered data list.
            filtered_array = (ArrayList<MyOrderListItem>) results.values;

            //notify
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetChanged();
            }
        }
    }
}
