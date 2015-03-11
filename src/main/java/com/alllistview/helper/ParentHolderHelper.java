package com.alllistview.helper;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by ksk1004zz on 2015-03-11.
 */
public class ParentHolderHelper {
  @SuppressWarnings (value="unchecked")
  public static <T extends View> T get(View convertView, int id) {
    SparseArray<View> goodsItemHolder = (SparseArray) convertView.getTag();

    if (goodsItemHolder == null) {
      goodsItemHolder = new SparseArray();
      convertView.setTag(goodsItemHolder);
    }

    View childView = goodsItemHolder.get(id);

    if (childView == null) {
      childView = convertView.findViewById(id);
      goodsItemHolder.put(id, childView);
    }

    return (T) childView;
  }
}
