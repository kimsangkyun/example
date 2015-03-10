package com.expandlistview.vo;

import java.util.ArrayList;

/**
 * Created by ksk1004zz on 2015-03-09.
 */
public class TitleNameItem {
  private String name;
  private ArrayList<GoodsItem> goodsItemList;

  public TitleNameItem(String name) {
    goodsItemList = new ArrayList<GoodsItem>();
  }

  public ArrayList<GoodsItem> getGoodsItemList(){
    return goodsItemList;
  }
}
