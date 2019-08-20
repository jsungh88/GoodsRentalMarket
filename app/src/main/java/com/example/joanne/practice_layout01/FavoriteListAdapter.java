package com.example.joanne.practice_layout01;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Joanne on 2018-02-07.
 */

public class FavoriteListAdapter extends BaseAdapter implements Filterable {
    Context context;
    LayoutInflater inflater;
    ArrayList<FavoriteListItem> array = new ArrayList<>();
    ArrayList<FavoriteListItem> filtered_array = new ArrayList<>();
    Filter listFilter;


    public interface ListBtnClickListener {
        void onListBtnClick(int position);
    }

    int resourceId;
    private ListBtnClickListener listBtnClickListener;


    public FavoriteListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<FavoriteListItem> list) {
        this.context = context;
        this.array = list;
        inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        this.resourceId = resource;
    }

    @Override
    public int getCount() {
        return filtered_array.size();
    }

    @Override
    public Object getItem(int position) {
        return filtered_array.get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final int pos = position;

        View view = convertView;
        FavoriteViewHolder holder = null;

        if (view == null) {
            view = inflater.inflate( this.resourceId, parent, false );

            holder = new FavoriteViewHolder();
            holder.chk = (CheckBox) view.findViewById( R.id.favo_chk_checkbox );
            holder.image = (ImageView) view.findViewById( R.id.favo_img_image );
            holder.subject = (TextView) view.findViewById( R.id.favo_cont_subject );

            holder.rentValue = (TextView) view.findViewById( R.id.favo_cont_rent_value );
            holder.rentGijun = (TextView) view.findViewById( R.id.favo_cont_rent_gijun );
            holder.depositValue = (TextView) view.findViewById( R.id.favo_cont_deposit_value );

            holder.btnQuestion = (Button) view.findViewById( R.id.favo_cont_btn_quest );
            holder.btnRent = (Button) view.findViewById( R.id.favo_cont_btn_rent );
            ;

            view.setTag( holder );

        }

        holder = (FavoriteViewHolder) view.getTag();

        final FavoriteListItem favoriteListItem = (FavoriteListItem) getItem( position );


        holder.image.setImageURI( Uri.parse( favoriteListItem.getsImage() ) );
        holder.subject.setText( favoriteListItem.getSubject() );
        holder.rentValue.setText( favoriteListItem.getRentValue() );
        holder.rentGijun.setText( favoriteListItem.getRentGijun() );
        holder.depositValue.setText( favoriteListItem.getDepositValue() );

        holder.chk.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //이건 전체선택 임!ㅋㅋ
                if (favoriteListItem.isChecked) {
                    favoriteListItem.isChecked = false;
                } else {
                    favoriteListItem.isChecked = true;
                }

                notifyDataSetChanged();
            }
        } );

        //굉장한 영향을 줌......... ㅠㅠ
        holder.chk.setChecked( favoriteListItem.isChecked );
        return view;
    }

    @Override
    public Filter getFilter() {
        if (listFilter == null) {
            listFilter = new ListFilter();
            Log.e( "getFilter:", "ㅇㅇ" );
        }

        return listFilter;
    }

    private class ListFilter extends Filter {
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
                ArrayList<FavoriteListItem> itemList = new ArrayList<FavoriteListItem>();
                Log.e( "여기도 들렀나?","ㅇㅇ" );
                for (FavoriteListItem item : array) {
                    Log.e( "포문?","ㅇㅇ" );
                    Log.e( "필터item:", item.getLoginId() );
                    if (item.getLoginId().toUpperCase().contains( constraint.toString().toUpperCase() )) {
                        itemList.add( item );
                        Log.e( "필터item:", String.valueOf( item ) );
                        Log.e( "필터item:", item.getLoginId() );
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
            filtered_array = (ArrayList<FavoriteListItem>) results.values;

            //notify
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetChanged();
            }
        }
    }
}




