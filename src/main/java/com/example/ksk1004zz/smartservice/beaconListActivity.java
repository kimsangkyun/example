package com.example.ksk1004zz.smartservice;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

/**
 * Created by ksk1004zz on 2015-03-09.
 */
public class beaconListActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.expandlist);
    showListView();
  }

  public void showListView() {
    ExpandableListView expandLv = (ExpandableListView)findViewById(R.id.expandLv);
    expandLv.setAdapter();
  }
}
