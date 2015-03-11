package com.example.ksk1004zz.smartservice;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by ksk1004zz on 2015-03-04.
 */
public class UserInforActivity extends Activity implements View.OnClickListener{

  EditText birthYearEt;
  RadioGroup sexRg;
  EditText phoneFirstEt;
  EditText phoneMiddleEt;
  EditText phoneLastEt;
  Button confirmBt;
  RadioButton clickedRadioBt;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.userinfor);
    setFindByViewAndEvent();
  }


  public void setFindByViewAndEvent() {
    birthYearEt = (EditText)findViewById(R.id.birthYearEt);
    sexRg = (RadioGroup)findViewById(R.id.sexRg);
    phoneFirstEt = (EditText)findViewById(R.id.phoneFirstEt);
    phoneMiddleEt = (EditText)findViewById(R.id.phoneMidlleEt);
    phoneLastEt = (EditText)findViewById(R.id.phoneLastEt);
    confirmBt = (Button)findViewById(R.id.confirmBt);
    confirmBt.setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    String birthYearText = birthYearEt.getText().toString();

    if (birthYearText.length() < 3 ) {
        Toast.makeText(this, getString(R.string.birthYear_T_Sentence),Toast.LENGTH_LONG).show();
        birthYearEt.requestFocus();
        return;
    }

    int checkedRadioButtonInt = sexRg.getCheckedRadioButtonId();

    if (checkedRadioButtonInt < 0 ) {
        Toast.makeText(this, getString(R.string.sexCheck_T_Sentence),Toast.LENGTH_LONG).show();
        sexRg.requestFocus();
        return;
    }

    String phoneFirstText = phoneFirstEt.getText().toString();
    String phoneMiddleText = phoneMiddleEt.getText().toString();
    String phoneLastText = phoneLastEt.getText().toString();

    if (phoneFirstText.length() < 3 || phoneMiddleText.length() < 4 || phoneLastText.length() < 4) {
        Toast.makeText(this, getString(R.string.phone_T_Sentence),Toast.LENGTH_LONG).show();
        return;
    }

    clickedRadioBt = (RadioButton)findViewById(checkedRadioButtonInt);

    postDataServer();
}

    public void postDataServer(){
        ProgressDialog loadingPd = new ProgressDialog(this);
        loadingPd.setMessage(getString(R.string.wait_T_Sentence));
        loadingPd.show();
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        mRequestQueue.add(new JsonObjectRequest(Request.Method.GET, "", null,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject jsonRoot) {
                        userInforSaveSuccess();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", " "+ error);
                    }
                }
        ));
    }

    public void userInforSaveSuccess(){
        SharedPreferences userInfoSaveSuccess = getSharedPreferences("userInfoSaveSuccess", MODE_PRIVATE);
        SharedPreferences.Editor userInfoSaveSuccessEdit = userInfoSaveSuccess.edit();
        userInfoSaveSuccessEdit.putBoolean("userInfoSaveSuccess", true);

        /*
        꺼내기
        SharedPreferences userInfoSaveSuccess = getSharedPreferences("userInfoSaveSuccess", MODE_PRIVATE);

        if(userInfoSaveSuccess.getBoolean("userInfoSaveSuccess", false) == true){
            //true
        }

        //false*/
    }

}
