package adapters;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import gauravkumar.com.cultureking.MainApplication;

public class CustomBroadcastReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        MainApplication application = (MainApplication) context.getApplicationContext();
        Activity activity;



        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);

            if (noConnectivity) {
                SharedPreps.getStaticObject(context).setConnectivity(false);
                 activity = application.getCurrentActivity();

                if(activity.getLocalClassName().equals("Splash")==false)
                {
                    Intent i = new Intent();
                    i.setClassName("gauravkumar.com.cultureking", "gauravkumar.com.cultureking.Splash");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }

            } else {
                SharedPreps.getStaticObject(context).setConnectivity(true);
                activity = application.getCurrentActivity();

                if(application.IsSplashRunningForFirstTime())
                {
                }
                else {
                    if(activity.getLocalClassName().equals("Splash"))
                    {
                        activity.finish();
                    }
                }
            //    Toast.makeText(context,"connection available",Toast.LENGTH_SHORT).show();

            }

        }
    }
}
