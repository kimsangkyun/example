package com.alllistview.vo;

/**
 * Created by ksk1004zz on 2015-03-09.
 */
public class GoodsItem {

  private String goodsImage;
  private String goodsName;
  private String goodsPrice;
  private String goodsDescription;

  public GoodsItem(String goodsImage,
                   String goodsName,
                   String goodsPrice,
                   String goodsDescription) {
    this.goodsImage = goodsImage;
    this.goodsName = goodsName;
    this.goodsPrice = goodsPrice;
    this.goodsDescription = goodsDescription;
  }

  public String getGoodsImage() {
    return goodsImage;
  }

  public String getGoodsName() {
    return goodsName;
  }

  public String getGoodsPrice() {
    return goodsPrice;
  }

  public String getGoodsDescription() {
    return goodsDescription;
  }
}
