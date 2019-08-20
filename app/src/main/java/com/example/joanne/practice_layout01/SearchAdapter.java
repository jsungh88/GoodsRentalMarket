package com.example.joanne.practice_layout01;

import android.content.Context;
import android.net.Uri;
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
 * Created by Joanne on 2018-03-08.
 */

public class SearchAdapter extends BaseAdapter implements Filterable {

    Context context;
    ArrayList<Product> array = new ArrayList<>();
    ArrayList<Product> filtered_array = array;
    Filter listFilter;
    LayoutInflater inflater;
    int resourceId;



    public SearchAdapter(Context context,  int resource, ArrayList<Product> product) {
        this.context = context;
        this.array = product;
        inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        this.resourceId=resource;
    }


    @Override
    public int getCount() {
        return filtered_array.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.e( "오긴오니?","어댑터" );
        if(convertView==null){
            //inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            convertView = inflater.inflate( R.layout.search_sub_result,parent,false );

        }

        ImageView img = convertView.findViewById( R.id.search_img );
        TextView  name = convertView.findViewById( R.id.search_name );
        TextView  rent = convertView.findViewById( R.id.search_rent );
        TextView  gijun = convertView.findViewById( R.id.search_gijun );
        TextView  deposit = convertView.findViewById( R.id.search_deposit );
        TextView  state = convertView.findViewById( R.id.search_state );

        convertView.setTag( convertView );

        final Product item = (Product) getItem( position );

        img.setImageURI( Uri.parse( item.getSimage() ) );
        name.setText( item.getName() );
        rent.setText( item.getRentValue() );
        gijun.setText( item.getRentGijun() );
        deposit.setText( item.getDepositValue() );
        state.setText( item.getState() );

        convertView.getTag();

        return convertView;
    }

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
                ArrayList<Product> itemList = new ArrayList<Product>();
                Log.e( "여기도 들렀나?ddd","ㅇㅇ" );
                Log.e( "포문?","ㅇㅇ" );

                for (Product item : array) {
                    Log.e( "포문?","ㅇㅇ" );
                    Log.e( "필터item:", item.getName() );
                    Log.e( "필터item:", item.getRentValue() );
                    Log.e( "필터item:", item.getRentGijun() );
                    Log.e( "필터item:", item.getDepositValue() );
                    Log.e( "필터item:", item.getState() );
                    if (item.getName().toUpperCase().contains( constraint.toString().toUpperCase() )||
                            item.getRentValue().toUpperCase().contains( constraint.toString().toUpperCase() )||
                            item.getRentGijun().toUpperCase().contains( constraint.toString().toUpperCase() )||
                            item.getDepositValue().toUpperCase().contains( constraint.toString().toUpperCase() )||
                            item.getState().toUpperCase().contains( constraint.toString().toUpperCase() )
                            ) {
                        itemList.add( item );
                        Log.e( "필터item:", String.valueOf( item ) );
                        Log.e( "필터item:", item.getName() );
                        Log.e( "필터item:", item.getRentValue() );
                        Log.e( "필터item:", item.getRentGijun() );
                        Log.e( "필터item:", item.getDepositValue() );
                        Log.e( "필터item:", item.getState() );
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
            filtered_array = (ArrayList<Product>) results.values;

            //notify
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetChanged();
            }
        }
    }
}
