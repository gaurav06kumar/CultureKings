package gauravkumar.com.cultureking;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import adapters.SharedPreps;

public class Splash extends AppCompatActivity {

    protected MainApplication mainApplication;
    ProgressBar progressBar;
    TextView connectivityText,retryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        connectivityText = (TextView) findViewById(R.id.connectivity_text);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        retryText = (TextView) findViewById(R.id.retry_text);

        mainApplication = (MainApplication)this.getApplicationContext();
        mainApplication.setCurrentActivity(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                if(SharedPreps.getStaticObject(getApplicationContext()).getConnectivity())
                {
                    connectivityText.setText("");
                    connectivityText.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if(SharedPreps.getStaticObject(getApplicationContext()).getLoginStatus())
                            {
                                Intent i = new Intent(Splash.this,HomePage.class);
                                startActivity(i);
                                finish();
                            }else
                            {
                                Intent i = new Intent(Splash.this,Login.class);
                                startActivity(i);
                                finish();
                            }
                        }
                    },1000);

                }
                else
                {
                    connectivityText.setText("Internet Connection Required");
                    connectivityText.setVisibility(View.VISIBLE);
                    retryText.setVisibility(View.VISIBLE);
                }
            }
        },2000);



        retryText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectivityText.setVisibility(View.GONE);
                retryText.setEnabled(false);
                progressBar.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        if(SharedPreps.getStaticObject(getApplicationContext()).getConnectivity())
                        {
                            connectivityText.setText("");
                            connectivityText.setVisibility(View.VISIBLE);
                            retryText.setVisibility(View.GONE);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    if(SharedPreps.getStaticObject(getApplicationContext()).getLoginStatus())
                                    {
                                        Intent i = new Intent(Splash.this,Login.class);
                                        startActivity(i);
                                        finish();
                                    }else
                                    {
                                        Intent i = new Intent(Splash.this,Login.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }
                            },1000);

                        }
                        else
                        {
                            connectivityText.setText("Internet Connection Required");
                            connectivityText.setVisibility(View.VISIBLE);
                            retryText.setVisibility(View.VISIBLE);
                            retryText.setEnabled(true);
                        }
                    }
                },2000);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
