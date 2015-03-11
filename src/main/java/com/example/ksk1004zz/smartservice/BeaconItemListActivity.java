package com.example.ksk1004zz.smartservice;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import com.alllistview.adapter.GoodsListAdapter;
import com.alllistview.vo.GoodsItem;
import com.alllistview.vo.TitleNameItem;
import com.common.networkConnectiion.NetworkCallback;
import com.common.networkConnectiion.NetworkConnection;

import java.util.ArrayList;

/**
 * Created by ksk1004zz on 2015-03-11.
 */
public class BeaconItemListActivity extends Activity implements NetworkCallback{

  private NetworkConnection networkConnection;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.goodslist);
    showListView();
    networkConnection = NetworkConnection.getNetworkConnection(this);
    networkConnection.requestGetType("url");
  }

  public void showListView(/*JSONObject rrjo*/) {
    ExpandableListView expandLv = (ExpandableListView)findViewById(R.id.expandLv);
    ArrayList<TitleNameItem> parentItem = new ArrayList();
    TitleNameItem item;
    ArrayList<GoodsItem> goodsItemArrayList;

    for(int i=0;i<50;i++){
      item = new TitleNameItem("대분류");
      goodsItemArrayList = item.getGoodsItemList();
      try {
        for (int j = 0; j < 50; j++) {
          goodsItemArrayList.add(new GoodsItem("http://comin.com:8680/nfc/upload/pj/artwork/thumbnail/%EC%8B%B8%EC%9D%B4%EC%BD%94%ED%8C%A8%EC%8A%A4.jpg", "이미지", "5,000", "이미지 입니다."));
        }
      }catch(Exception e){
        Log.i("test", "e:" + e.toString());}

      parentItem.add(item);
    }

    expandLv.setAdapter(new GoodsListAdapter(this, parentItem));

  }

  @Override
  public void networkCallback() {
    //showListView(networkConnection.getResResponseJsonObject());
  }
}
