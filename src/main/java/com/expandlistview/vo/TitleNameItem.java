package com.expandlistview.vo;

import java.util.ArrayList;

/**
 * Created by ksk1004zz on 2015-03-09.
 */
public class TitleNameItem {
 private String titleName;
 private ArrayList<GoodsItem> goodsItemList;


  /**
   * Gets title name.
   *
   * @return the title name
   */
  public String getTitleName() {
    return titleName;
  }

  /**
   * Instantiates a new Title name item.
   *
   * @param name the name
   */
  public TitleNameItem(String titleName) {
    this.titleName = titleName;
    goodsItemList = new ArrayList<GoodsItem>();
  }

  /**
   * Get goods item list.
   *
   * @return the array list
   */
  public ArrayList<GoodsItem> getGoodsItemList(){
    return goodsItemList;
  }


}
