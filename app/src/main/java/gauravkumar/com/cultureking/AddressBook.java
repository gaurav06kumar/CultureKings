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

public class AddressBook extends AppCompatActivity {



    protected  MainApplication mainApplication;

    TextView saveButton;
    ImageView backButton;
    TextInputLayout textInputAddress;
    TextInputLayout textInputCountry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book);

        mainApplication = (MainApplication)this.getApplicationContext();
        mainApplication.setCurrentActivity(this);

        textInputAddress = (TextInputLayout) findViewById(R.id.address_edittext_layout);
        textInputCountry = (TextInputLayout) findViewById(R.id.country_edittext_layout);

        textInputCountry.getEditText().setText(SharedPreps.getStaticObject(getApplicationContext()).getCountry());
        textInputAddress.getEditText().setText(SharedPreps.getStaticObject(getApplicationContext()).getAddress());

        saveButton = (TextView) findViewById(R.id.save_address_textview);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate())
                {
                    Bundle bundle = new Bundle();
                    bundle.putString("UID", SharedPreps.getStaticObject(getApplicationContext()).getUID());
                    bundle.putString("address",textInputAddress.getEditText().getText().toString().trim());
                    bundle.putString("country",textInputCountry.getEditText().getText().toString().trim());


                    AlertAdapter.getObject(AddressBook.this).createAlert();
                    WebApiAdapter.getObject(AddressBook.this).fireServerApi(4,bundle);
                    //  Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton = (ImageView) findViewById(R.id.back_button_address);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddressBook.this,Profile.class);
                startActivity(i);
                finish();
            }
        });

    }


    public boolean validate()
    {
        int error = 0;

        if(Validation.getObject(getApplicationContext()).validateString(textInputAddress.getEditText().getText().toString().trim()))
        {
            textInputAddress.setError(null);
        }
        else{
            textInputAddress.setError("Invalid address");
            error++;
        }

        if(Validation.getObject(getApplicationContext()).validateString(textInputCountry.getEditText().getText().toString().trim()))
        {
            textInputCountry.setError(null);
        }
        else{
            textInputCountry.setError("Invalid country");
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
            AlertAdapter.getObject(AddressBook.this).createMessageAlert(""+response.toString());
        }else {
            AlertAdapter.getObject(AddressBook.this).createMessageAlert(""+response.toString());
            SharedPreps.getStaticObject(getApplicationContext()).setAddress(textInputAddress.getEditText().getText().toString().trim());
            SharedPreps.getStaticObject(getApplicationContext()).setCountry(textInputCountry.getEditText().getText().toString().trim());
            }
    }
}
