package com.common.imageslide.ImageSlider;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.ksk1004zz.smartservice.R;

/**
 * Created by ksk1004zz on 2015-03-06.
 */
public class CircleIndicator {

    LinearLayout circleIndicatorLayout;

    public CircleIndicator(Context context, final int COUNT, LinearLayout circleIndicatorLayout){
        this.circleIndicatorLayout = circleIndicatorLayout;
        //상단의 현재 페이지 표시하는 뷰 초기화 o o o o o o o o o o
            for (int i = 0; i < COUNT; i++) {
                ImageView iv = new ImageView(context.getApplicationContext());    //페이지 표시 이미지 뷰 생성
                iv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                //첫 페이지 표시 이미지 이면 선택된 이미지로
                if (i == 0)
                    iv.setBackgroundResource(R.drawable.page_select);
                else    //나머지는 선택안된 이미지로
                    iv.setBackgroundResource(R.drawable.page_not);

                //LinearLayout에 추가
                circleIndicatorLayout.addView(iv);
            }
    }

    public void setNotPage(int beforePosition){
        circleIndicatorLayout.getChildAt(beforePosition).setBackgroundResource(R.drawable.page_not);    //이전 페이지에 해당하는 페이지 표시 이미지 변경
    }

    public void setSelectPage(int recentPosition){
        circleIndicatorLayout.getChildAt(recentPosition).setBackgroundResource(R.drawable.page_select);        //현재 페이지에 해당하는 페이지 표시 이미지 변경
    }
}
