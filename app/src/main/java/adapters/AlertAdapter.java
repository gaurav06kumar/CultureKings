package adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class AlertAdapter {

    View view;
    RelativeLayout holder;
    AlertDialog.Builder builder;
    AlertDialog alert;
   static Context cont;
    static AlertAdapter alertAdapter;

    public AlertAdapter(Context context)
    {
        cont = context;
    }

    public static synchronized AlertAdapter getObject(Context context)
    {
        cont = context;
        if(alertAdapter==null)
        {
            alertAdapter = new AlertAdapter(context);
        }
        return  alertAdapter;
    }

    public void createMessageAlert(String message)
    {

        builder = new AlertDialog.Builder(cont);
        alert = builder.create();
        alert.setTitle(message);
        alert.setCancelable(true);
        alert.show();

    }
    public void createToastAlert(String message,long milliseconds)
    {

        builder = new AlertDialog.Builder(cont);
        alert = builder.create();
        alert.setTitle(message);
        alert.setCancelable(false);
        alert.show();

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                alert.dismiss();
            }
        },milliseconds);

    }

    public void createAlert()
    {
        builder = new AlertDialog.Builder(cont);
        alert = builder.create();
        alert.setTitle("Please Wait..");
        ProgressBar progressBar = new ProgressBar(cont);
        alert.setView(progressBar);
        alert.setCancelable(false);
        alert.show();
    }

    public void createShadowAlert()
    {
        builder = new AlertDialog.Builder(cont);
        alert = builder.create();
        alert.setTitle("Please Wait..");
        ProgressBar progressBar = new ProgressBar(cont);
        alert.setView(progressBar);
        alert.setCancelable(false);
    }




    public void dismissAlert()
    {
        alert.dismiss();
    }
}
