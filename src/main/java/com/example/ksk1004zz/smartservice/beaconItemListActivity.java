package com.example.ksk1004zz.smartservice;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.expandlistview.adapter.CustomEListViewAdapter;
import com.expandlistview.vo.GoodsItem;
import com.expandlistview.vo.TitleNameItem;

import java.util.ArrayList;

/**
 * Created by ksk1004zz on 2015-03-09.
 */
public class beaconItemListActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.expandlist);
    showListView();
  }

  public void showListView() {
    ExpandableListView expandLv = (ExpandableListView)findViewById(R.id.expandLv);
    ArrayList<TitleNameItem> parentItem = new ArrayList<TitleNameItem>();
    TitleNameItem item;
    ArrayList<GoodsItem> goodsItemArrayList;

    for(int i=0;i<50;i++){
      item = new TitleNameItem("대분류");
      goodsItemArrayList = item.getGoodsItemList();
      for(int j = 0; j < 50; j++){
        goodsItemArrayList.add(new GoodsItem("http://comin.com:8680/nfc/upload/pj/artwork/thumbnail/%EC%8B%B8%EC%9D%B4%EC%BD%94%ED%8C%A8%EC%8A%A4.jpg","이미지","5,000","이미지 입니다."));
      }

      parentItem.add(item);
    }

    expandLv.setAdapter(new CustomEListViewAdapter(this, parentItem));

  }
}
