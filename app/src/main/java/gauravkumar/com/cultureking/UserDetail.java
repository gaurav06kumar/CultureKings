package gauravkumar.com.cultureking;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import adapters.AlertAdapter;
import adapters.SharedPreps;
import adapters.Validation;
import adapters.WebApiAdapter;

public class UserDetail extends AppCompatActivity {


    protected  MainApplication mainApplication;

    TextView saveButton;
    ImageView backButton;
    TextInputLayout textInputFirstName;
    TextInputLayout textInputLastName;
    TextInputLayout textInputEmail;
    TextInputLayout textInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        mainApplication = (MainApplication)this.getApplicationContext();
        mainApplication.setCurrentActivity(this);

        textInputFirstName = (TextInputLayout) findViewById(R.id.fname_edittext_layout);
        textInputLastName = (TextInputLayout) findViewById(R.id.lname_edittext_layout);
        textInputEmail = (TextInputLayout) findViewById(R.id.email_edittext_layout);
        textInputPassword = (TextInputLayout) findViewById(R.id.password_edittext_layout);

        textInputPassword.getEditText().setText(SharedPreps.getStaticObject(getApplicationContext()).getPassword());
        textInputFirstName.getEditText().setText(SharedPreps.getStaticObject(getApplicationContext()).getUserFName());
        textInputLastName.getEditText().setText(SharedPreps.getStaticObject(getApplicationContext()).getUserLName());
        textInputEmail.getEditText().setText(SharedPreps.getStaticObject(getApplicationContext()).getUserEmail());

        saveButton = (TextView) findViewById(R.id.save_button_textview);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(validate())
               {
                   Bundle bundle = new Bundle();
                   bundle.putString("UID", SharedPreps.getStaticObject(getApplicationContext()).getUID());
                   bundle.putString("fname",textInputFirstName.getEditText().getText().toString().trim());
                   bundle.putString("lname",textInputLastName.getEditText().getText().toString().trim());
                   bundle.putString("email",textInputEmail.getEditText().getText().toString().trim());
                   bundle.putString("password",textInputPassword.getEditText().getText().toString().trim());


                   AlertAdapter.getObject(UserDetail.this).createAlert();
                   WebApiAdapter.getObject(UserDetail.this).fireServerApi(3,bundle);
                 //  Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
               }
            }
        });

        backButton = (ImageView) findViewById(R.id.back_button_userdetail);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserDetail.this,Profile.class);
                startActivity(i);
                finish();
            }
        });
    }

    public boolean validate()
    {
        int error = 0;

        if(Validation.getObject(getApplicationContext()).validateString(textInputFirstName.getEditText().getText().toString().trim()))
        {
            textInputFirstName.setError(null);
        }
        else{
            textInputFirstName.setError("Invalid firstName");
            error++;
        }

        if(Validation.getObject(getApplicationContext()).validateString(textInputLastName.getEditText().getText().toString().trim()))
        {
            textInputLastName.setError(null);
        }
        else{
            textInputLastName.setError("Invalid LastName");
            error++;
        }

        if(Validation.getObject(getApplicationContext()).validateEmail(textInputEmail.getEditText().getText().toString().trim()))
        {
            textInputEmail.setError(null);
        }
        else{
            textInputEmail.setError("Invalid Email");
            error++;
        }


        if(Validation.getObject(getApplicationContext()).validateString(textInputPassword.getEditText().getText().toString().trim()))
        {
            textInputPassword.setError(null);
        }
        else{
            textInputPassword.setError("Invalid Password");
            error++;
        }

        if(error==0)
        {
            return true;
        }
        return false;
    }

    public void onResponseReceive(String response) {
        AlertAdapter.getObject(getApplicationContext()).dismissAlert();
        if(response.contains("error"))
        {
            AlertAdapter.getObject(UserDetail.this).createMessageAlert(""+response.toString());
        }else {
            AlertAdapter.getObject(UserDetail.this).createMessageAlert(""+response.toString());
            SharedPreps.getStaticObject(getApplicationContext()).setUserFName(textInputFirstName.getEditText().getText().toString().trim());
            SharedPreps.getStaticObject(getApplicationContext()).setUserLName(textInputLastName.getEditText().getText().toString().trim());
            SharedPreps.getStaticObject(getApplicationContext()).setUserEmail(textInputEmail.getEditText().getText().toString().trim());
            SharedPreps.getStaticObject(getApplicationContext()).setPassword(textInputPassword.getEditText().getText().toString().trim());
        }
    }
}
