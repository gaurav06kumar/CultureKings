package gauravkumar.com.cultureking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import adapters.AlertAdapter;
import adapters.SharedPreps;
import adapters.WebApiAdapter;

public class Login extends AppCompatActivity {

    protected MainApplication mainApplication;
    TextView loginButton,forgotButton,signupButton,facebookButton,googleButton;
    EditText emailET,passwordET;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mainApplication = (MainApplication)this.getApplicationContext();
        mainApplication.setCurrentActivity(this);
        mainApplication.setSplashRunnigForTheFirstTimeFalse();

        bundle = new Bundle();

        loginButton= (TextView)findViewById(R.id.login_button_textview);
        forgotButton = (TextView)findViewById(R.id.forgot_button_textview);
        signupButton = (TextView)findViewById(R.id.signup_button_textview);
        facebookButton = (TextView)findViewById(R.id.facebook_button_textview);
        googleButton = (TextView)findViewById(R.id.google_button_textview);
        emailET = (EditText)findViewById(R.id.email_edittext);
        passwordET = (EditText)findViewById(R.id.password_edittext);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,SignUp.class);
                startActivity(i);
                finish();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailET.getText().toString();
                if (email.contains("@gmail.com") || email.contains("@live.com") || email.contains("@hotmail.com") || email.contains("@yahoo.in")) {
                    if (passwordET.getText().length() > 4) {
                        AlertAdapter.getObject(Login.this).createAlert();
                        bundle.putString("password",passwordET.getText().toString());
                        bundle.putString("email",emailET.getText().toString());
                        try {
                            WebApiAdapter.getObject(Login.this).fireServerApi(2, bundle);
                        }
                        catch (Exception e)
                        {
                          AlertAdapter.getObject(Login.this).createMessageAlert(e.getMessage());
                               }
                    }
                }

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        mainApplication.setCurrentActivity(this);
    }
    public void onResponseReceive(String response)
    {
        if(response.equals("fail"))
        {
            AlertAdapter.getObject(Login.this).dismissAlert();
            AlertAdapter.getObject(Login.this).createMessageAlert("Please Check your email or password !!");
        }else{

            try {
                JSONArray jsonArray = new JSONArray(response);

                JSONObject jsonObject = jsonArray.getJSONObject(0);

                SharedPreps.getStaticObject(Login.this).setUID(jsonObject.getString("UID"));
                SharedPreps.getStaticObject(Login.this).setUserFName(jsonObject.getString("firstname"));
                SharedPreps.getStaticObject(Login.this).setUserLName(jsonObject.getString("lastname"));
                SharedPreps.getStaticObject(Login.this).setUserEmail(jsonObject.getString("email"));
                SharedPreps.getStaticObject(Login.this).setPassword(jsonObject.getString("password"));
                SharedPreps.getStaticObject(Login.this).setAddress(jsonObject.getString("address"));
                SharedPreps.getStaticObject(Login.this).setCountry(jsonObject.getString("country"));
                SharedPreps.getStaticObject(Login.this).setLoginStatus(true);


                 Toast.makeText(getApplicationContext(),"Ready to shop "+ SharedPreps.getStaticObject(getApplicationContext()).getUserFName().toUpperCase()+" !!",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(Login.this,HomePage.class);
                startActivity(i);
                finish();

            }
            catch (Exception e)
            {
                AlertAdapter.getObject(Login.this).createMessageAlert(e.getMessage());
            }

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
