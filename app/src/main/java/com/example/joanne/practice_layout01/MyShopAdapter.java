package com.example.joanne.practice_layout01;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Joanne on 2018-02-07.
 */

public class MyShopAdapter extends BaseAdapter implements Filterable {

    Context context;
    ArrayList<Product> array = new ArrayList<>();
    ArrayList<Product> filtered_array = array;
    Filter listFilter;
    LayoutInflater inflater;
    int resourceId;
    int position, i,delete_position;
    String save_id,save_name,save_date;
//    public void setmodyBtnState(boolean bstate){
//        modyBtnState = bstate;
//        notifyDataSetChanged();
//    }

    public MyShopAdapter(Context context,  int resource, ArrayList<Product> product) {
        this.context = context;
        this.array = product;
        this.resourceId = resource;
        inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    }

    @Override
    public int getCount() {
        return filtered_array.size();
    }


    //position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        int j = 0;
        Log.e( "myshopadapter:", "진입" + j );
        j++;
        Log.e( "array데이터", filtered_array.get( 0 ).getName() );

        if (convertView == null) {

            convertView = inflater.inflate( R.layout.myshop_item_sub, parent, false );

        }

        //화면에 표시될 View (Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        final CheckBox chk = convertView.findViewById( R.id.myshop_chk_checkbox );
        ImageView image = convertView.findViewById( R.id.myshop_image );
        TextView product = convertView.findViewById( R.id.myshop_subject );
        TextView rent = convertView.findViewById( R.id.myshop_rent_value );
        TextView deposit = convertView.findViewById( R.id.myshop_deposit_value );
        TextView state = convertView.findViewById( R.id.myshop_state_value );
        TextView date = convertView.findViewById( R.id.myshop_date_value );
        ImageView modify = convertView.findViewById( R.id.myshop_modify );
        //didTapButton(convertView);
        convertView.setTag( convertView );

        //Data Set(filtered_array)에서 position에 위치한 데이터 참조 획득
        final Product items = (Product) getItem( position );

        //아이템 내 각 위젯에 데이터 반영.
        try {
            image.setImageURI( Uri.parse( items.getSimage() ) );
            Log.e( "이미지", items.getSimage() );

            product.setText( items.getName() );
            Log.e( "제목", items.getName() );
            rent.setText( items.getRentValue() );
            Log.e( "대여료", items.getRentValue() );
            deposit.setText( items.getDepositValue() );
            Log.e( "보증금", items.getDepositValue() );
            state.setText( items.getState() );
            Log.e( "상테", items.getState() );
            date.setText( items.getDate() );
            Log.e( "날짜", items.getDate() );
        } catch (Exception e) {
            Log.e( "데이터없음", "없습니다." );
        }
        if (MyShopActivity.modyBtnState == true) {
            modify.setVisibility( View.VISIBLE );
        } else if (MyShopActivity.modyBtnState == false) {
            modify.setVisibility( View.INVISIBLE );
        }


//        if(MyShopActivity.visibleFlag)modify.setVisibility( View.VISIBLE );
//        else modify.setVisibility( View.GONE );

        chk.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (items.isChecked) {
                    items.isChecked= false ;
                    Log.e( "item 체크", "X" );

                } else {  items.isChecked= true;
                    Log.e( "item 체크", "O" );

                    SharedPreferences pref = context.getSharedPreferences( "delete", Context.MODE_PRIVATE );
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString( "save_id", items.getProid() );
                    editor.putString( "save_name", items.getName() );
                    editor.putString( "save_date", items.getDate() );
                    editor.commit();

                    Log.e( "save_id", items.getProid() );
                    Log.e( "save_name", items.getName() );
                    Log.e( "save_date", items.getDate() );

                }
                notifyDataSetChanged();
            }
        } );
        chk.setChecked( items.isChecked );

        convertView.getTag();
        return convertView;
    }//getView 끝

    @Override
    public Object getItem(int position) {
        return filtered_array.get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //아이템 추가하기 메소드
    public void addItem(String favCount, String category, String simage, String name, String rentValue, String rentGijun,
                        String depositValue, String desc, String locTxt, String state, String date,
                        String userimage, String username, String userid, String userpw, String useremail, String usercontact) {

        Product item = new Product();

        item.setFavCount( favCount );
        item.setCategory( category );
        item.setSimage( simage );
        item.setName( name );
        item.setRentValue( rentValue );
        item.setRentGijun( rentGijun );
        item.setDepositValue( depositValue );
        item.setDesc( desc );
        item.setLocTxt( locTxt );
        item.setState( state );
        item.setDate( date );
        item.setProimage( userimage );
        item.setProname( username );
        item.setProid( userid );
        item.setPropw( userpw );
        item.setProemail( useremail );
        item.setProcontact( usercontact );

        Log.e( "좋아요갯수", favCount );
        Log.e( "카테고리", category );
        Log.e( "이미지", simage );
        Log.e( "상품명", name );
        Log.e( "대여료", rentValue );
        Log.e( "대여기준", rentGijun );
        Log.e( "보증금", depositValue );
        Log.e( "설명", desc );
        Log.e( "지역", locTxt );
        Log.e( "상태", state );
        Log.e( "날짜", date );
        Log.e( "유저이미지", userimage );
        Log.e( "유저이름", username );
        Log.e( "유저아이디", userid );
        Log.e( "유저비밀버놓", userpw );
        Log.e( "유저이메일", useremail );
        Log.e( "유저연락처", usercontact );

        //array.add(0,item);

        SharedPreference pref;
        Gson gson = new Gson();
        pref = new SharedPreference( context );
//        String storedArrayString1 = pref.loadPreference_regit( "item", "products" );
//        if (storedArrayString1 == null) {
//            array = new ArrayList<Product>();
//        } else {
//            Type type = new TypeToken<ArrayList<Product>>() {
//            }.getType();
//            array = gson.fromJson( storedArrayString1, type );
//            Log.e( "array:", String.valueOf( array ) );
//        }
//        //상품정보 리스트 Shared에서 불러와서 '0'번째에 저장하기.
//        array.add( 0, item );
//        Log.e( "array:", String.valueOf( array ) );
//        String arrayJson1 = gson.toJson( array );
//        pref.savePreference_regit( "item", arrayJson1, "products" );
//        Log.e( "array아이템", array.get( 0 ).getName() );
//
//
//

        String storedArrayString2 = pref.loadPreference_regit( "item", "product_list" );
        if (storedArrayString2 == null) {
            array = new ArrayList<Product>();
        } else {
            Type type = new TypeToken<ArrayList<Product>>() {
            }.getType();
            array = gson.fromJson( storedArrayString2, type );
            Log.e( "array:", String.valueOf( array ) );
        }
        //상품정보 리스트 Shared에서 불러와서 '0'번째에 저장하기.
        array.add( 0, item );
        Log.e( "array:", String.valueOf( array ) );
        String arrayJson2 = gson.toJson( array );
        pref.savePreference_regit( "item", arrayJson2, "product_list" );
        Log.e( "array아이템", array.get( 0 ).getName() );




    }

    public void modifyItem(String save_id, String save_name, String save_date, String favCount, String category, String simage, String name, String rentValue, String rentGijun,
                           String depositValue, String desc, String locTxt, String state, String date,
                           String userimage, String username, String userid, String userpw, String useremail, String usercontact) {

        Product item = new Product();

        item.setFavCount( favCount );
        item.setCategory( category );
        item.setSimage( simage );
        item.setName( name );
        item.setRentValue( rentValue );
        item.setRentGijun( rentGijun );
        item.setDepositValue( depositValue );
        item.setDesc( desc );
        item.setLocTxt( locTxt );
        item.setState( state );
        item.setDate( date );
        item.setProimage( userimage );
        item.setProname( username );
        item.setProid( userid );
        item.setPropw( userpw );
        item.setProemail( useremail );
        item.setProcontact( usercontact );

        Log.e( "좋아요갯수", favCount );
        Log.e( "카테고리", category );
        Log.e( "이미지", simage );
        Log.e( "상품명", name );
        Log.e( "대여료", rentValue );
        Log.e( "대여기준", rentGijun );
        Log.e( "보증금", depositValue );
        Log.e( "설명", desc );
        Log.e( "지역", locTxt );
        Log.e( "상태", state );
        Log.e( "날짜", date );
        Log.e( "유저이미지", userimage );
        Log.e( "유저이름", username );
        Log.e( "유저아이디", userid );
        Log.e( "유저비밀버놓", userpw );
        Log.e( "유저이메일", useremail );
        Log.e( "유저연락처", usercontact );

        Log.e( "save_id", save_id );
        Log.e( "save_name", save_name );
        Log.e( "save_date", save_date );
        /**
         * 불러온 셰어드 를 size로 for문 돌리고,
         * 수정 전의 아이디, 상품 이름, 날짜가 일치하면
         * 그 상품의 position 값을 따로 변수(position)에 저장한다.
         *
         * 그래서 그 포지션 값에 set 하고 저장하면 끝!!! 꺄 ! ~
         */


        SharedPreference pref;
        Gson gson = new Gson();
        pref = new SharedPreference( context );

        String storedArrayString1 = pref.loadPreference_regit( "item", "product_list" );
        if (storedArrayString1 == null) {
            array = new ArrayList<Product>();
        } else {
            Type type = new TypeToken<ArrayList<Product>>() {
            }.getType();
            array = gson.fromJson( storedArrayString1, type );
            Log.e( "array1:", String.valueOf( array ) );


            for (i = 0; i < array.size(); i++) {
                Log.e( "for문", "ㅇㅇ" );
                Log.e( "array.size:", String.valueOf( array.size() ) );
                int j = i;
                Log.e( "j", String.valueOf( j ) );
                if (save_id.equals( array.get( j ).getProid() ) && save_name.equals( array.get( j ).getName() ) && save_date.equals( array.get( j ).getDate() )) {
                    Log.e( "id", array.get( j ).getProid() );
                    Log.e( "name", array.get( j ).getName() );
                    Log.e( "date", array.get( j ).getDate() );
                    position = j;
                    Log.e( "position", String.valueOf( position ) );
                } else {
                    Log.e( "해당안됨.", "ㅇㅇ" );
                }
            }
        }


        //상품정보 리스트 Shared에서 불러와서 'position'번째에 set하기.
        array.set( position, item );
        Log.e( "array2:", String.valueOf( array ) );
        Log.e( "array-name", array.get( position ).getName() );
        Log.e( "array-category", array.get( position ).getCategory() );
        Log.e( "array-id", array.get( position ).getProid() );
        Log.e( "array-deposit", array.get( position ).getDepositValue() );
        Log.e( "array-deposit", String.valueOf( array.size() ) );

        String arrayJson1 = gson.toJson( array );
        pref.savePreference_regit( "item", arrayJson1, "product_list" );
        Log.e( "array아이템", array.get( position ).getName() );

        notifyDataSetChanged();
    }

//    public void deleteItem() {
//        //Product item = new Product();
//        //chk(true)일 경우, shared에 "delete"에 save_id,save_name,save_date 저장함.
//        SharedPreferences pref1 = context.getSharedPreferences( "delete", Context.MODE_PRIVATE );
//        save_id = pref1.getString( "save_id", null );
//        save_name = pref1.getString( "save_name", null );
//        save_date = pref1.getString( "save_date", null );
//        Log.e( "save_id", save_id );
//        Log.e( "save_name", save_name );
//        Log.e( "save_date", save_date );
//
//        //**
//        // 원래 shared 불러와서, save_id,save_name,save_date가 있는 item의 position 값을 변수 delete_position에 저장한다.
//        SharedPreference pref;
//        Gson gson = new Gson();
//        pref = new SharedPreference( context );
//
//        String storedArrayString1 = pref.loadPreference_regit( "item", "products" );
//        if (storedArrayString1 == null) {
//            array = new ArrayList<Product>();
//        } else {
//            Type type = new TypeToken<ArrayList<Product>>() {
//            }.getType();
//            array = gson.fromJson( storedArrayString1, type );
//            Log.e( "array1:", String.valueOf( array ) );
//
//            for (int i = 0; i < array.size(); i++) {
//                Log.e( "for문", "시작" );
//                int j = i;
//                if (save_id.equals( array.get( j ).getProid() )
//                        && save_name.equals( array.get( j ).getName() )
//                        && save_date.equals( array.get( j ).getDate() )) {
//
//                    Log.e( "id", array.get( j ).getProid() );
//                    Log.e( "name", array.get( j ).getName() );
//                    Log.e( "date", array.get( j ).getDate() );
//
//                    delete_position = j;
//                    Log.e( "delete_position", String.valueOf( position ) );
//
//                } else {
//                    Log.e( "해당 없음", "ㅇㅇ" );
//                }
//            }
//
//        }//if문 끝
//
//        //delete_position item 삭제!!
//        array.remove( delete_position );
//        Log.e( "array-name", array.get( delete_position ).getName() );
//
//        //다시 상품리스트Shared 에 저장!!!
//        String arrayJson1 = gson.toJson( array );
//        pref.savePreference_regit( "item", arrayJson1, "products" );
//        Log.e( "array아이템", array.get( position ).getName() );
//
//        notifyDataSetChanged();
//
//    }//deleteitem 끝.

    @Override
    public Filter getFilter() {
        if (listFilter == null) {
            listFilter = new ListFilter();
            Log.e( "getFilter:", "ㅇㅇ" );
        }
        return listFilter;
    }

//    public void didTapButton(View view) {
//        //img = (ImageView)findViewById(R.id.goods_image01);
//        final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
//
//        // Use bounce interpolator with amplitude 0.2 and frequency 20 //진폭과 이탈횟수
//        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
//        myAnim.setInterpolator(interpolator);
//
//        view.startAnimation(myAnim);
//    }


    private class ListFilter extends Filter {

        //여기 이상해 안될 수도 있어...
        Context context;

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                results.values = array;
                results.count = array.size();
            } else {
                ArrayList<Product> itemList = new ArrayList<Product>();

                for (Product item : array) {
                    if (item.getProid().toUpperCase().contains( constraint.toString().toUpperCase() )) {
                        itemList.add( item );
                        Log.e( "필터item:", String.valueOf( item ) );
                        Log.e( "필터item:", item.getName() );
                        Log.e( "필터item:", item.getProemail() );
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
