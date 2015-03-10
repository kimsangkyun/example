package com.example.ksk1004zz.smartservice;

import android.app.Activity;
import android.os.Bundle;

import com.common.imageslide.ImageSlider.ImageSlider;

/**
 * 10개의 페이지가 있는데 1번에서 10번으로 10번에서 다시 1번으로 순화하는 뷰 페이저
 *
 * @author SeolBK
 */
public class HomeActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageslider);
        new ImageSlider(this);
    }


}