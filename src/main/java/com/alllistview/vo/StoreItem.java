package com.alllistview.vo;

/**
 * Created by ksk1004zz on 2015-03-11.
 */
public class StoreItem {
  private String storeIamge;
  private String storeMainName;
  private String storeSubmainName;
  private String storeAddr;
  private String storeTel;
  private String storeAccessDate;

  public StoreItem(String storeIamge, String storeMainName, String storeSubmainName, String storeAddr, String storeTel, String storeAccessDate) {
    this.storeIamge = storeIamge;
    this.storeMainName = storeMainName;
    this.storeSubmainName = storeSubmainName;
    this.storeAddr = storeAddr;
    this.storeTel = storeTel;
    this.storeAccessDate = storeAccessDate;
  }

  public String getStoreIamge() {
    return storeIamge;
  }

  public String getStoreMainName() {
    return storeMainName;
  }

  public String getStoreSubmainName() {
    return storeSubmainName;
  }

  public String getStoreAddr() {
    return storeAddr;
  }

  public String getStoreTel() {
    return storeTel;
  }

  public String getStoreAccessDate() {
    return storeAccessDate;
  }
}
