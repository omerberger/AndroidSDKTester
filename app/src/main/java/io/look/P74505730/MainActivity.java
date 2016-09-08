package io.look.P74505730;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liveperson.mobile.android.LivePerson;
import com.liveperson.mobile.android.LPMobileLog;
import com.liveperson.mobile.android.service.LPMobileEndChatCallback;
import com.liveperson.mobile.android.service.ServicesSharedPreferences;
import com.liveperson.mobile.android.service.chat.ChatServiceFactory;
import com.liveperson.mobile.android.ui.LPMobileDelegateAPIImp;

import java.util.concurrent.atomic.AtomicBoolean;


public class MainActivity extends AppCompatActivity {


    private static final int OVERLAY_PERMISSION_REQ_CODE = 1234;

    static boolean isLogoShow = false;
    LPMobileDelegateAPIImp delegateAPI;
    AtomicBoolean permissionChecked = new AtomicBoolean(false);
    boolean canDraw = false;

    @Override
    protected void onStart() {

        super.onStart();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LivePerson.init(this);
        ServicesSharedPreferences.saveIsProductionEnv(getApplicationContext(), false);

        delegateAPI = new LPMobileDelegateAPIImp(){
            @Override
            public boolean canDrawTabOverlay() {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(MainActivity.this)) {
                    if (!permissionChecked.getAndSet(true)) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                Uri.parse("package:" + getPackageName()));
                        startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
                    }
                }else{
                    canDraw = true;
                }
                return canDraw;
            }
        };

        ChatServiceFactory.getInstance().setDelegateAPI(delegateAPI);
        setContentView(R.layout.activity_main);




//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(MainActivity.this)) {
//
//            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
//                    Uri.parse("package:" + getPackageName()));
//            startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
//        }
//


        setContentView(R.layout.activity_main);
    }







}
