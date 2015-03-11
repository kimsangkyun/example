package com.example.ksk1004zz.smartservice;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.alllistview.adapter.StoreListAdapter;
import com.alllistview.vo.StoreItem;
import com.common.networkConnectiion.NetworkCallback;

import java.util.ArrayList;

/**
 * Created by ksk1004zz on 2015-03-11.
 */
public class StoreStatementListActivity extends Activity implements NetworkCallback{
  //private NetworkConnection networkConnection;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.storelist);
    showListView();
    /*networkConnection = NetworkConnection.getNetworkConnection(this);
    networkConnection.requestGetType("url");*/
  }

  public void showListView(/*JSONObject rrjo*/) {
    ListView expandLv = (ListView)findViewById(R.id.storeLv);
    ArrayList<StoreItem> parentItem = new ArrayList<StoreItem>();
    StoreItem item;


    for (int i = 0; i < 50; i++) {
      /*"http://comin.com:8680/nfc/upload/pj/artwork/thumbnail/%EC%8B%B8%EC%9D%B4%EC%BD%94%ED%8C%A8%EC%8A%A4.jpg", "이미지", "5,000", "이미지 입니다."*/
      item = new StoreItem("http://comin.com:8680/nfc/upload/pj/artwork/thumbnail/%EC%8B%B8%EC%9D%B4%EC%BD%94%ED%8C%A8%EC%8A%A4.jpg", "이미지", "5,000", "이미지 입니다.", "aaaaaaaaaa", "aaaaaaaa");
      parentItem.add(item);
    }

    expandLv.setAdapter(new StoreListAdapter(this, R.layout.storetlistcontent, parentItem));

  }

  @Override
  public void networkCallback() {
    //showListView(networkConnection.getResResponseJsonObject());
  }

}
