package com.alllistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alllistview.helper.ChildItemHolderHelper;
import com.alllistview.vo.StoreItem;
import com.example.ksk1004zz.smartservice.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ksk1004zz on 2015-03-11.
 */
public class StoreListAdapter extends ArrayAdapter<StoreItem>{

  private Context context;

  public StoreListAdapter(Context context, int resourceId,
                               List<StoreItem> items) {
    super(context, resourceId, items);
    this.context = context;
  }
  
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    StoreItem storeItem = getItem(position);

    if(convertView == null){
      LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = inflater.inflate(R.layout.storetlistcontent, null);
    }

    TextView storeMainNameTv = ChildItemHolderHelper.get(convertView, R.id.storeMainNameTv);
    TextView storeSubmainNameTv = ChildItemHolderHelper.get(convertView, R.id.storeMainNameTv);
    TextView storeAddrTv = ChildItemHolderHelper.get(convertView, R.id.storeSubmainTv);
    TextView storeTelTv = ChildItemHolderHelper.get(convertView, R.id.storeAddrTv);
    TextView storeAccessDate = ChildItemHolderHelper.get(convertView, R.id.storeAccessDateTv);
    ImageView storeImageIv = ChildItemHolderHelper.get(convertView, R.id.storeImageIv);

    storeMainNameTv.setText(storeItem.getStoreMainName());
    storeSubmainNameTv.setText(storeItem.getStoreSubmainName());
    storeAddrTv.setText(storeItem.getStoreAddr());
    storeTelTv.setText(storeItem.getStoreTel());
    storeAccessDate.setText(storeItem.getStoreAccessDate());
    Picasso.with(context).load(storeItem.getStoreIamge()).into(storeImageIv);

    return convertView;
  }
}
