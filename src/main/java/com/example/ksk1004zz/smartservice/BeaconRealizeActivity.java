package com.example.ksk1004zz.smartservice;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.common.dialog.DialogManager;
import com.common.networkConnectiion.NetworkCallback;
import com.common.networkConnectiion.NetworkConnection;

/**
 * Created by ksk1004zz on 2015-03-06.
 */
public class BeaconRealizeActivity extends Activity implements NetworkCallback{


  private boolean dialogOpen = true;
  private DialogManager dialogMangaer;
  private NetworkConnection networkConnection;
  private Dialog helpInforDialog;

  private View.OnClickListener clickEventListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {

      switch (view.getId()) {
        case R.id.closeBt :
          helpInforDialog();
          break;
        case R.id.beaconMainImage :
          //startActivity(new Intent(,));
        default :
          requestAfterValidCheck();
          break;
      }

    }
  };

  private void requestAfterValidCheck() {
    RadioGroup usefulGroup = (RadioGroup)helpInforDialog.findViewById(R.id.usefulGroup);
    int checkedRadioButtonInt = usefulGroup.getCheckedRadioButtonId();

    if (checkedRadioButtonInt < 0 ) {
      Toast.makeText(BeaconRealizeActivity.this, BeaconRealizeActivity.this.getString(R.string.sexCheck_T_Sentence), Toast.LENGTH_LONG).show();
      usefulGroup.requestFocus();
      return;
    }

    /*RadioButton radioButton = (RadioButton)BeaconRealizeActivity.this.findViewById(checkedRadioButtonInt);
    networkConnection.requestGetType("url");*/
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.beaconrealize);
    /*
    * 나중에 넷트워크 작업을 통해서 현재 비콘으로 보여지는 정보를 유용하다고 했는지 확인 필요함
    * 만약에 그렇게 했을경우에는 보여질 필요 없음*/
    networkConnection = NetworkConnection.getNetworkConnection(this);
    dialogMangaer = DialogManager.getDialogManage(this);

    Button closeBt = (Button)findViewById(R.id.closeBt);
    ImageView beaconMainImage = (ImageView)findViewById(R.id.beaconMainImage);

    beaconMainImage.setOnClickListener(clickEventListener);
    closeBt.setOnClickListener(clickEventListener);
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();

    if (dialogOpen) {
      helpInforDialog();
    }

    dialogOpen = false;

  }

  public void helpInforDialog() {
    helpInforDialog = dialogMangaer.helpInforDialog();
    Button confirmBt = (Button)helpInforDialog.findViewById(R.id.inforDialogConfirmBt);
    confirmBt.setOnClickListener(clickEventListener);

  }

  @Override
  public void networkCallback() {
    //데이터 가져옴
    //networkConnection.getResponseJSON();
  }
}
