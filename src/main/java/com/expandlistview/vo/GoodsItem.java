package com.expandlistview.vo;

/**
 * Created by ksk1004zz on 2015-03-09.
 */
public class GoodsItem {

  private String urlImage;
  private String name;
  private String price;
  private String description;

  public String getName() {
    return name;
  }

  public String getPrice() {
    return price;
  }

  public String getDescription() {
    return description;
  }

  public String getUrlImage() {

    return urlImage;
  }

  public GoodsItem(String urlImage, String name, String price, String description) {
    this.urlImage = urlImage;
    this.name = name;
    this.price = price;
    this.description = description;
  }

}
