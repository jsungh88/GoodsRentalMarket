package com.example.joanne.practice_layout01;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class IntroAdapter extends PagerAdapter {

    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;

    public IntroAdapter(Context context, ArrayList<Integer> images) {
        this.context = context;
        this.images=images;
        inflater = LayoutInflater.from(context);
    }


    //destroyItem(ViewGroup,int,Object):
    //컨테이너에서 지정된 위치의 페이지를 제거한다. 여기에서는 단순히 removeview()를 사용하여 객체를 제거함.
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    //getCount(): ViewPager에서 사용 가능한 뷰 수를 반환한다.
    @Override
    public int getCount() {
        return images.size();
    }

    //instantiateItem(ViewGroup,int):
    //인수로 전달된 위치에 대한 페이지를 만들어야한다.
    //안드로이드 이미지 슬라이더를 생성하기 위해 image_item.xml 레이아웃을 inflate()하여 이미지 뷰에 이미지 리소스를 설정한다.
    //마지막으로 inflate한 뷰는 addView()를 사용하여 ViewPager에 추가되고 리턴된다.

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = inflater.inflate(R.layout.slide_item_intro, view, false);
        ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.image);
        myImage.setImageResource(images.get(position));
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    //isViewFromObject(View,Object)
    //이 메서드는 전달된 뷰가 instantiateIte()에서 반환한 키와 연관이 되어있는지 확인한다.
    //이 방법은 PagerAdapter가 제대로 작동하는데 중요하다. 두 입력보기와 키를 비교하여 결과를 반환하기만하면 된다.
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}