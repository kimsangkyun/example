package com.alllistview.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alllistview.helper.ChildItemHolderHelper;
import com.alllistview.helper.ParentHolderHelper;
import com.example.ksk1004zz.smartservice.R;
import com.alllistview.vo.GoodsItem;
import com.alllistview.vo.TitleNameItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

@SuppressLint("ResourceAsColor") public class GoodsListAdapter extends BaseExpandableListAdapter {

  Context context;
  private ArrayList<TitleNameItem> goodsItemList = null;

  public GoodsListAdapter(Context context, ArrayList<TitleNameItem> goodsItemList) {
    this.context = context;
    this.goodsItemList = goodsItemList;
  }

  @Override
  public Object getChild(int groupPosition, int childPosition) {
    // TODO Auto-generated method stub
    TitleNameItem titleNameItem = goodsItemList.get(groupPosition);
    return titleNameItem.getGoodsItemList().get(childPosition);
  }

  @Override
  public long getChildId(int groupPosition, int childPosition) {
    return childPosition;
  }

  @Override
  public View getChildView(int groupPosition, int childPosition,
                           boolean isLastChild, View convertView, ViewGroup parent) {
    // TODO Auto-generated method stub
    try{
      GoodsItem goodItem = (GoodsItem)getChild(groupPosition, childPosition);

      if(convertView == null){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.goodslistchild, null);
      }

      TextView goodsNameTv = ChildItemHolderHelper.get(convertView, R.id.goodsNameTv);
      TextView goodsPriceTv = ChildItemHolderHelper.get(convertView, R.id.goodsPriceTv);
      TextView goodsDescriptionTv = ChildItemHolderHelper.get(convertView, R.id.goodsDescriptionTv);
      ImageView goodsImageIv = ChildItemHolderHelper.get(convertView, R.id.goodsImageIv);

      goodsNameTv.setText(goodItem.getGoodsName() + groupPosition + ":" + childPosition);
      goodsPriceTv.setText(goodItem.getGoodsPrice());
      goodsDescriptionTv.setText(goodItem.getGoodsDescription());
      Picasso.with(context).load(goodItem.getGoodsImage()).into(goodsImageIv);

    }catch(Exception e){
      Log.i("test","list error : "+e.toString());
    }
    return convertView;
  }
  
  @Override
  public int getChildrenCount(int groupPosition) {//������ŭ getChildView ȣ��
    // TODO Auto-generated method stub
    TitleNameItem titleNameItem = goodsItemList.get(groupPosition);
    int count = 0;
    if(titleNameItem.getGoodsItemList().size() != 0){
      count = titleNameItem.getGoodsItemList().size();
    }

    return count;
  }

  @Override
  public Object getGroup(int groupPosition) {
    return goodsItemList.get(groupPosition);
  }

  @Override
  public int getGroupCount() {//������ŭ getGroupView ȣ��
    // TODO Auto-generated method stub
    return goodsItemList.size();
  }

  @Override
  public long getGroupId(int groupPosition) {
    // TODO Auto-generated method stub
    return groupPosition;
  }


  @Override
  public View getGroupView(int groupPosition, boolean isExpanded,
                           View convertView, ViewGroup parent) {
    try{

      TitleNameItem titleNameItem = (TitleNameItem)getGroup(groupPosition);
      LayoutInflater mInflater = (LayoutInflater) context
              .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

      if(convertView == null) {
        convertView = mInflater.inflate(R.layout.goodslistparent, null);
      }

      TextView titleNameTv = ParentHolderHelper.get(convertView, R.id.titleNameTv);
      titleNameTv.setText(titleNameItem.getTitleName());

    }catch(Exception e){Log.i("123123",e.getLocalizedMessage());}
    return convertView;
  }


  @Override
  public boolean hasStableIds() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isChildSelectable(int groupPosition, int childPosition) {
    // TODO Auto-generated method stub
    return true;
  }

}
