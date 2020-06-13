package gauravkumar.com.cultureking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import adapters.AlertAdapter;
import adapters.WebApiAdapter;

public class SignUp extends AppCompatActivity {

    protected MainApplication mainApplication;
    TextView signInLink,signupButton,termsConditionButton;
    EditText emailET,passwordET,firstNameET,lastNAmeET;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mainApplication = (MainApplication)this.getApplicationContext();
        mainApplication.setCurrentActivity(this);

        bundle = new Bundle();

        signInLink = (TextView) findViewById(R.id.login_link_button_textview);
        signupButton = (TextView) findViewById(R.id.signup_button_textview);
        termsConditionButton = (TextView) findViewById(R.id.terms_button_textview);

        emailET = (EditText) findViewById(R.id.email_signup_edittext);
        passwordET= (EditText) findViewById(R.id.password_signup_edittext);
        firstNameET = (EditText) findViewById(R.id.firstname_edittext);
        lastNAmeET = (EditText) findViewById(R.id.lastname_edittext);

        signInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUp.this,Login.class);
                startActivity(i);
                finish();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString();
                if (email.contains("@gmail.com") || email.contains("@live.com") || email.contains("@hotmail.com") || email.contains("@yahoo.in")) {
                    if (passwordET.getText().length() > 4) {
                        AlertAdapter.getObject(SignUp.this).createAlert();
                        bundle.putString("fname",firstNameET.getText().toString());
                        bundle.putString("lname",lastNAmeET.getText().toString());
                        bundle.putString("password",passwordET.getText().toString());
                        bundle.putString("email",emailET.getText().toString());
                       try {
                           WebApiAdapter.getObject(SignUp.this).fireServerApi(1, bundle);
                       }
                       catch (Exception e)
                       {
                           AlertAdapter.getObject(getApplicationContext()).createMessageAlert("Signup.java - "+e.getMessage());
                       }
                    }
                }
            }
        });
    }

    public void onResponseReceive(String response)
    {
        if(response.contains("error"))
        {
            try {
                AlertAdapter.getObject(getApplicationContext()).createMessageAlert(response);
            }
            catch (Exception e)
            {
                AlertAdapter.getObject(getApplicationContext()).createMessageAlert("error in inputs");
            }
        }
        else {
            Toast.makeText(getApplicationContext(),"Registered",Toast.LENGTH_SHORT).show();

            Intent i = new Intent(SignUp.this, Login.class);
            startActivity(i);
            finish();
        }
    }
}
