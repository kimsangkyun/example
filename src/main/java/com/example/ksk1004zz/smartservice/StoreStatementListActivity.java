package com.example.ksk1004zz.smartservice;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.alllistview.adapter.StoreListAdapter;
import com.alllistview.vo.StoreItem;
import com.common.networkConnectiion.NetworkCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ksk1004zz on 2015-03-11.
 */
public class StoreStatementListActivity extends Activity implements NetworkCallback, TextWatcher, AdapterView.OnItemSelectedListener{
  //private NetworkConnection networkConnection;

  private EditText storeSearchTextEd;
  private StoreListAdapter storeListAdapter;
  private SearchListTask curSearchTask;
  private ArrayList<StoreItem> filterList;
  private ArrayList<StoreItem> storeItems;
  private boolean inSearchMode;
  private Object searchLock;
  private ListView expandLv;
  private Spinner storeSearchSp;
  String[] storeSearchAr;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
      //storeSearchAr
      setContentView(R.layout.storelist);
    setMInitValiable();
    /*networkConnection = NetworkConnection.getNetworkConnection(this);
    networkConnection.requestGetType("url");
    */
      showListView();

      setEvent();
      setSpinnerAdapter();
  }

  public void setMInitValiable(){
    storeSearchTextEd = (EditText)findViewById(R.id.storeSearchTextEt);
    storeSearchSp = (Spinner)findViewById(R.id.storeSearchSp);
    expandLv = (ListView)findViewById(R.id.storeLv);
    storeSearchAr = getResources().getStringArray(R.array.storeSearchAr);
    filterList = new ArrayList<StoreItem>();
    storeItems = new ArrayList<StoreItem>();
    searchLock = new Object();
  }

  public void setEvent(){
    storeSearchSp.setOnItemSelectedListener(this);
    storeSearchTextEd.addTextChangedListener(this);
  }

  public void setSpinnerAdapter(){
    ArrayAdapter<String> storeSearchAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, storeSearchAr);
    storeSearchSp.setAdapter(storeSearchAdapter);
  }

  public void showListView(/*JSONObject rrjo*/) {
    StoreItem item;
    for (int i = 0; i < 50; i++) {
      /*"http://comin.com:8680/nfc/upload/pj/artwork/thumbnail/%EC%8B%B8%EC%9D%B4%EC%BD%94%ED%8C%A8%EC%8A%A4.jpg", "이미지", "5,000", "이미지 입니다."*/
      item = new StoreItem("http://comin.com:8680/nfc/upload/pj/artwork/thumbnail/%EC%8B%B8%EC%9D%B4%EC%BD%94%ED%8C%A8%EC%8A%A4.jpg", "이미지" + i, "5,000", "이미지 입니다.", "aaaaaaaaaa", "aaaaaaaa");
      storeItems.add(item);
    }
    setListAdapter();
  }

  public void setListAdapter(){
    List<StoreItem> toggleList = inSearchMode ? filterList : storeItems;
    storeListAdapter = new StoreListAdapter(this, R.layout.storetlistcontent, toggleList);
    expandLv.setAdapter(storeListAdapter);
  }

  @Override
  public void networkCallback() {
    //showListView(networkConnection.getResResponseJsonObject());
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {

  }

  @Override
  public void afterTextChanged(Editable s) {
    String storeSearchText = storeSearchTextEd.getText().toString();

    if(curSearchTask != null && curSearchTask.getStatus() != AsyncTask.Status.FINISHED)//작업 취소
    {
      try{
        curSearchTask.cancel(true);
      }
      catch(Exception e){
        Log.i("test", "e : " + e.toString());
      }

    }

    curSearchTask = new SearchListTask();
    curSearchTask.execute(storeSearchText); // put it in a task so that ui is not freeze
  }

  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    String haha = (String)storeSearchSp.getSelectedItem();
    Toast.makeText(this, haha, Toast.LENGTH_LONG).show();
  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {

  }

  //검색하는 부분
  private class SearchListTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
      filterList.clear();

      String keyword = params[0];

      inSearchMode = (keyword.length() > 0);

      if (inSearchMode) {
        for (StoreItem item : storeItems) {
          //조건으로 검색 위치
          if ((item.getStoreMainName().toUpperCase().indexOf(keyword) > -1) ) {
            filterList.add(item);
          }

        }

      }
      return null;
    }

    protected void onPostExecute(String result) {

      synchronized(searchLock)
      {
        setListAdapter();
      }

    }


  }

}
