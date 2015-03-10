package com.common.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

import com.example.ksk1004zz.smartservice.R;

/**
 * Created by ksk1004zz on 2015-03-09.
 */

public class DialogManager {

  private static Context context;
  private Dialog dialog;
  private volatile static DialogManager dialogManage = null;

  private DialogManager(){}

  public static DialogManager getDialogManage(Context context) {
    DialogManager.context = context;
    if (dialogManage == null) {
        synchronized (DialogManager.class) {
            if (dialogManage == null) {
                dialogManage = new DialogManager();
            }
        }
    }
    return dialogManage;
  }

  public void waitDialogOpen(String title){
    dialog = new ProgressDialog(context);
    dialog.setTitle(title);
    dialog.show();
  }

  public void waitDialogClose(){
    dialog.dismiss();
  }

  public Dialog helpInforDialog() {
    dialog = new Dialog(context);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.beaconrealizedialog);
    dialog.show();
    return dialog;
  }



}
