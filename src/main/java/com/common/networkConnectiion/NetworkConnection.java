package com.common.networkConnectiion;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.common.dialog.DialogManager;
import com.example.ksk1004zz.smartservice.R;

import org.json.JSONObject;

/**
 * Created by ksk1004zz on 2015-03-09.
 */
public class NetworkConnection {

  private volatile static NetworkConnection networkConnection;
  private JSONObject responseJsonObject = null;
  private static Context context;

  private NetworkConnection(){}

  public static NetworkConnection getNetworkConnection(Context context) {
    NetworkConnection.context = context;
    if (networkConnection == null) {
      synchronized (NetworkConnection.class) {
        if (networkConnection == null) {
          networkConnection = new NetworkConnection();
        }
      }
    }

    return networkConnection;
  }

  public void requestGetType(String url) {
    requestGetType(url, null);
  }

  public void requestGetType(String url, final NetworkCallback networkCallback) {

    final DialogManager dialog = DialogManager.getDialogManage(context);
    dialog.waitDialogOpen(context.getString(R.string.wait_T_Sentence));
    RequestQueue requestQueue = Volley.newRequestQueue(context);
    requestQueue.add(new JsonObjectRequest(Request.Method.GET, "", null,
      new Response.Listener<JSONObject>() {
        public void onResponse(JSONObject jsonRoot) {
          responseJsonObject = jsonRoot;

          if (context instanceof NetworkCallback) {
            ((NetworkCallback)context).networkCallback();
          } else if (networkCallback != null) {
            networkCallback.networkCallback();
          }

          dialog.waitDialogClose();
        }
      },
      new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.i("test", " " + error);
        }
      }
    ));
  }

  public JSONObject getResResponseJsonObject() {
    return responseJsonObject;
  }
}
