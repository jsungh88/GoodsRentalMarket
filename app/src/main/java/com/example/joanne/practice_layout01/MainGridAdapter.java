package com.example.joanne.practice_layout01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Joanne on 2018-02-12.
 */

public class MainGridAdapter extends BaseAdapter {

    Context context;
    ArrayList<Product> product = new ArrayList<>();

    SharedPreference pref;
    Integer favCountt;
    String count;
    int j = 0;
    public MainGridAdapter(Context context, ArrayList<Product> product) {
        this.context = context;
        this.product = product;

    }

    @Override
    public int getCount() {
        return product.size();
    }

    @Override
    public Product getItem(int position) {
        return product.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;


    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

        j++;
        Log.e( "j", String.valueOf( j ) );

        if (convertView == null){
            convertView = new MainGridItem( context );
        convertView = inflater.inflate( R.layout.grid_item_main, parent, false );
        }
        //favIcon = (ImageView) findViewById(R.id.img_favorite01);
        //TextView favCount = convertView.findViewById(R.id.txt_favoritecount);
        ImageView image = convertView.findViewById(R.id.img_goods01);
        TextView name = convertView.findViewById(R.id.goods_name01);
        TextView rentValue = convertView.findViewById(R.id.goods_rentfee_value01);
        TextView rentGijun = convertView.findViewById(R.id.goods_rentfee_standard01);
        TextView depositValue = convertView.findViewById(R.id.goods_deposit_value01);
        TextView locTxt = convertView.findViewById(R.id.txt_loc01);
        TextView state = convertView.findViewById(R.id.goods_state01);

        //현재 favCount값을 불러오고.

        //favCount.setText(product.get( position ).getFavCount());
        image.setImageURI( Uri.parse(product.get( position ).getSimage()));
        name.setText(product.get( position ).getName());
        rentValue.setText(product.get( position ).getRentValue());
        rentGijun.setText(product.get( position ).getRentGijun());
        depositValue.setText(product.get( position ).getDepositValue());
        locTxt.setText(product.get( position ).getLocTxt());
        state.setText(product.get( position ).getState());
//        if(state.getText().equals(product.get( position ).getState())){
//            state.setBackgroundColor( R.color.colorAccent);
//        }else {
//            state.setBackgroundColor( R.color.colorPrimary);
//        }


        return convertView;



    }//getView 끝

//    public void addFavCount(Activity context,int position) {
//
//        pref = new SharedPreference(context);
//
//        //현재 favCount값을 불러오고.
//        favCountt = pref.loadPreference2( "position"+position,0,"favCount" );
//        Log.e( "favCount["+position+"]:", String.valueOf( favCountt ) );
//
//        favCountt=+1;
//        Log.e( "favCount["+position+"]:", String.valueOf( favCountt ) );
//        //셰어드프리퍼런스의 key를 position값으로 놓고, 그 포지션의 favcount가 올라가게!
//        pref.savePreference("position"+position,favCountt,"favcount");
//
////        count = String.valueOf( favCountt );
//
//
//        product.get( position ).setFavCount(favCountt);
//        notifyDataSetChanged();
//
//    }
//
//    public  void loadFavCount(Activity context){
//
//        for(int i=0; i>getCount(); i++){
//            pref = new SharedPreference(context);
//            //현재 favCount값을 불러오고.
//            favCountt = pref.loadPreference2( "position"+i,0,"favCount" );
//            Log.e( "favCount["+i+"]:", favCountt.toString());
//
//            product.get( i ).setFavCount( favCountt );
//        }
//
//
//
//    }
}

