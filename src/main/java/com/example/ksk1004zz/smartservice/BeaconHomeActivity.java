package com.example.ksk1004zz.smartservice;

import android.app.Activity;
import android.os.Bundle;

import com.common.imageslide.ImageSlider.ImageSlider;
import com.common.networkConnectiion.NetworkConnection;

/**
 * Created by ksk1004zz on 2015-03-09.
 */
public class BeaconHomeActivity extends Activity {

  private NetworkConnection networkConnection;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.imageslider);
    new ImageSlider(this);
  }
}
