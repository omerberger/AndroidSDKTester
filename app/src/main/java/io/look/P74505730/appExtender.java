package io.look.P74505730;

import android.app.Application;
import android.os.Build;
import com.liveperson.mobile.android.service.ApplicationLifecycleHandler;
/**
 * Created by oberger on 8/26/16.
 */
public class appExtender extends Application {


    @Override
    public void onCreate() {
        super.onCreate();



        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.ICE_CREAM_SANDWICH){
            registerActivityLifecycleCallbacks(new ApplicationLifecycleHandler(this));
        }

    }


    public void customAppMethod()
    {
        // Custom application method
    }









}
