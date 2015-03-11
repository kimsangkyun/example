package com.expandlistview.adapter;

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

import com.example.ksk1004zz.smartservice.R;
import com.expandlistview.vo.GoodsItem;
import com.expandlistview.vo.TitleNameItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

@SuppressLint("ResourceAsColor") public class CustomEListViewAdapter extends BaseExpandableListAdapter {

  Context context;
  private int gubun = 0; //0�̸� ������ 1�̸� �׺������ 2�̸� �� �� ����Ʈ
  private ArrayList<TitleNameItem> goodsItemList = null;

  public CustomEListViewAdapter(Context context, ArrayList<TitleNameItem> goodsItemList) {
    this.context = context;
    this.goodsItemList = goodsItemList;
  }

  /*private view holder class*/
  private class ViewHolder {
    ImageView productImageIv;
    TextView productNameTv;
    TextView productPriceTv;
    TextView productDescriptionTv;
  }

  /*private view holder class*/
  private class TitleViewHolder {
    TextView titleNameTv;
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

  ViewHolder holder;

  @Override
  public View getChildView(int groupPosition, int childPosition,
                           boolean isLastChild, View convertView, ViewGroup parent) {
    // TODO Auto-generated method stub
    try{
      GoodsItem goodItem = (GoodsItem)getChild(groupPosition, childPosition);
      if(convertView == null){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.expandlistchild, null);
        holder = new ViewHolder();
        convertView.setTag(holder);
      }else{
        holder = (ViewHolder)convertView.getTag();
      }

      holder.productNameTv = (TextView)convertView.findViewById(R.id.productNameTv);
      holder.productPriceTv = (TextView)convertView.findViewById(R.id.productPriceTv);
      holder.productImageIv = (ImageView)convertView.findViewById(R.id.productImageIv);
      holder.productDescriptionTv = (TextView)convertView.findViewById(R.id.productDescriptionTv);

      holder.productNameTv.setText(goodItem.getName());
      holder.productPriceTv.setText(goodItem.getPrice());
      holder.productDescriptionTv.setText(goodItem.getDescription());
      Picasso.with(context).load(goodItem.getUrlImage()).into(holder.productImageIv);

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

  TitleViewHolder titleHolder = null;

  @Override
  public View getGroupView(int groupPosition, boolean isExpanded,
                           View convertView, ViewGroup parent) {
    try{

      TitleNameItem titleNameItem = (TitleNameItem)getGroup(groupPosition);
      LayoutInflater mInflater = (LayoutInflater) context
              .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

      if(convertView == null) {
        convertView = mInflater.inflate(R.layout.expandlistparent, null);
        titleHolder = new TitleViewHolder();
        convertView.setTag(titleHolder);
      }else{
        titleHolder = (TitleViewHolder)convertView.getTag();
      }

      titleHolder.titleNameTv = (TextView)convertView.findViewById(R.id.titleNameTv);
      titleHolder.titleNameTv.setText(titleNameItem.getTitleName());

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
