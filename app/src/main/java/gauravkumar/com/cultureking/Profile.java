package gauravkumar.com.cultureking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import adapters.SharedPreps;

public class Profile extends AppCompatActivity {


    protected  MainApplication mainApplication;

    TextView initials;
    ImageView backButton;
    LinearLayout detailButton,orderButton,addressButton,signOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mainApplication = (MainApplication)this.getApplicationContext();
        mainApplication.setCurrentActivity(this);


        initials = (TextView) findViewById(R.id.name_initials_textview);
        initials.setText(SharedPreps.getStaticObject(getApplicationContext()).getUserInitials().toUpperCase());
        backButton = (ImageView) findViewById(R.id.back_button_profile);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profile.this,HomePage.class);
                startActivity(i);
                finish();
            }
        });
        detailButton = (LinearLayout) findViewById(R.id.my_profile_button_layout);
        detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profile.this,UserDetail.class);
                startActivity(i);
                finish();
            }
        });
        orderButton = (LinearLayout) findViewById(R.id.my_order_button_layout);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profile.this,Orders.class);
                startActivity(i);
                finish();
            }
        });
        addressButton = (LinearLayout) findViewById(R.id.address_book_button_layout);
        addressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profile.this,AddressBook.class);
                startActivity(i);
                finish();
            }
        });

        signOutButton = (LinearLayout) findViewById(R.id.sign_out_button_layout);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreps.getStaticObject(getApplicationContext()).signout();
                Intent i = new Intent(Profile.this,Login.class);
                startActivity(i);
                finish();
            }
        });

    }
}
