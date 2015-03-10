package com.common.imageslide.ImageSlider;

import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.common.networkConnectiion.NetworkCallback;
import com.common.networkConnectiion.NetworkConnection;
import com.example.ksk1004zz.smartservice.R;
import com.squareup.picasso.Picasso;

/**
 * Created by ksk1004zz on 2015-03-06.
 */
public class ImageSlider implements NetworkCallback{

    private final int COUNT = 10;                    //아이템 갯수
    
    private int beforePosition;                        //이전에 선택되었던 포지션 값
    private ViewPager mPager;                    //뷰 페이저
    
    private LinearLayout circleIndicatorLayout;            //현재 몇 페이지 인지 나타내는 뷰
    private Context context;
    private ImageView leftSlideIv;
    private ImageView rightSlideIv;
    private CircleIndicator circleIndicator;
    private NetworkConnection networkConnection;

    public ImageSlider(Context context){
        this.context = context;
        initViewAndVari();
        setEventListener();
        networkConnection = NetworkConnection.getNetworkConnection(context);
        networkConnection.requestGetType("", this);
    }

    public void setEventListener(){
        View.OnClickListener lAndRListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int bothWay = -1;
                if(v.getId() == R.id.leftSlideIv){
                    bothWay = 1;
                }

                mPager.setCurrentItem(mPager.getCurrentItem() + bothWay, true);
            }
        };

        leftSlideIv.setOnClickListener(lAndRListener);

        rightSlideIv.setOnClickListener(lAndRListener);

        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {    //아이템이 변경되면, gallery나 listview의 onItemSelectedListener와 비슷
            @Override
            public void onPageSelected(int position) {
                slideImage(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffest, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void setViewPagerAdapter(){
        //뷰 페이저
        mPager.setAdapter(new BkPagerAdapter(context.getApplicationContext()));//PagerAdapter로 설정
        mPager.setCurrentItem(COUNT);            //무한 스크롤 하기 위해서는 아이템을 3배로 가지고 있고 그 중 가운데 범위의 아이템만 보이게 한다

    }
    
    public void initViewAndVari(){
        Activity activity = (Activity)context;
        circleIndicatorLayout = (LinearLayout) activity.findViewById(R.id.page_mark);//상 하단에 나타나는 뷰
        mPager = (ViewPager) activity.findViewById(R.id.pager);
        leftSlideIv = (ImageView) activity.findViewById(R.id.leftSlideIv);
        rightSlideIv = (ImageView) activity.findViewById(R.id.rightSlideIv);
        beforePosition = 0;

    }
    
    public void slideImage(int position) {
        if (position < COUNT) {                                                //3배의 아이템중 앞쪽이면 뷰페이져의 포지션을 가운데 범위로 이동시킨다
            mPager.setCurrentItem(position + COUNT, true);
        } else if (position >= COUNT * 2) {                                        //3배의 아이템 중 뒤쪽이면 뷰페이져의 포지션을 가운데 범위로 이동시킨다
            mPager.setCurrentItem(position - COUNT, true);
        } else {                                                                    //가운데 범위이면 상단의 페이지 표시를 변경한다
            position -= COUNT;
            //아이템이 선택이 되었으면
            circleIndicator.setNotPage(beforePosition);
            circleIndicator.setSelectPage(position);
            beforePosition = position;                //이전 포지션 값을 현재로 변경
        }
    }


    public void hideButton(){//양쪽으로 움직일수 있게 하는 뷰 숨김
        leftSlideIv.setVisibility(View.GONE);
        rightSlideIv.setVisibility(View.GONE);
    }

    @Override
    public void networkCallback() {
        circleIndicator = new CircleIndicator(context, COUNT, circleIndicatorLayout);
        //networkConnection.getResponseJSON();
        setViewPagerAdapter();
    }

    //Pager 아답터 구현
    private class BkPagerAdapter extends PagerAdapter {
        private Context mContext;

        public BkPagerAdapter(Context con) {
            super();
            mContext = con;
        }

        @Override
        public int getCount() {
            return COUNT * 3;
        }

        //뷰페이저에서 사용할 뷰객체 생성/등록
        @Override
        public Object instantiateItem(View pager, int position) {
            position %= COUNT;
            Log.i("test", "instantiateItem : " + position);
            ImageView tv = new ImageView(mContext);                    //텍스트뷰
            Picasso.with(mContext).load("http://comin.com:8680/nfc/upload/pj/artwork/thumbnail/%E3%85%87%EB%8A%90%E3%85%8F%E3%84%B9.png").into(tv);
            ((ViewPager) pager).addView(tv, 0);        //뷰 페이저에 추가

            return tv;
        }

        //뷰 객체 삭제.
        @Override
        public void destroyItem(View pager, int position, Object view) {
            ((ViewPager) pager).removeView((View) view);
        }

        // instantiateItem메소드에서 생성한 객체를 이용할 것인지
        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }
    }
}
