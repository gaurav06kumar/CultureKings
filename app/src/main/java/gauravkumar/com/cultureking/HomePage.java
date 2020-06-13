package gauravkumar.com.cultureking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import adapters.AlertAdapter;
import adapters.ImageLoader;
import adapters.ItemsAdapter;
import adapters.SharedPreps;
import adapters.WebApiAdapter;

public class HomePage extends AppCompatActivity {

    protected  MainApplication mainApplication;

    ImageView menuButton,searchButton,profilePic,homepagePic;
    EditText searchET;
    TextView userName,menButton,womenButton;
    ProgressBar mProgressBar,wProgressBar;
    LinearLayout profileMenuButton,savedMenuButton,supportMenuButton,logOutMenuButton,menu_layout,back_layout;
    RelativeLayout view;
    int visibilityConstant=8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mainApplication = (MainApplication)this.getApplicationContext();
        mainApplication.setCurrentActivity(this);

        menuButton = (ImageView) findViewById(R.id.menu_button_imageview);
        searchButton = (ImageView) findViewById(R.id.search_button_imageview);
        homepagePic = (ImageView) findViewById(R.id.homepage_center_pic);

        mProgressBar = (ProgressBar) findViewById(R.id.men_progrss_bar);
        wProgressBar = (ProgressBar) findViewById(R.id.women_progrss_bar);

        ImageLoader.getObject(getApplicationContext()).LoadImageFromUrl(homepagePic,"main_screen.png");

        searchET = (EditText) findViewById(R.id.search_edittext);

        view = (RelativeLayout) findViewById(R.id.menu_items_layout);

        userName = (TextView) view.findViewById(R.id.username_in_menu);

        userName.setText(SharedPreps.getStaticObject(getApplicationContext()).getUserFName());
        profilePic = (ImageView) view.findViewById(R.id.profie_pic_in_menu);
        profileMenuButton = (LinearLayout) view.findViewById(R.id.my_profile_menu_button);
        profileMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this,Profile.class);
                startActivity(i);
                finish();
            }
        });
        supportMenuButton = (LinearLayout) view.findViewById(R.id.support_menu_button);
        supportMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this,Support.class);
                startActivity(i);
                finish();
            }
        });


        savedMenuButton = (LinearLayout) view.findViewById(R.id.saved_menu_button);
        savedMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this,SavedItem.class);
                startActivity(i);
                finish();
            }
        });
        logOutMenuButton = (LinearLayout) view.findViewById(R.id.signout_menu_button);
        logOutMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreps.getStaticObject(getApplicationContext()).signout();
                Intent i = new Intent(HomePage.this,Login.class);
                startActivity(i);
                finish();
            }
        });
        menu_layout = (LinearLayout) findViewById(R.id.menu_layout);
        back_layout = (LinearLayout) findViewById(R.id.back_layout);

        menButton = (TextView) findViewById(R.id.men_button_textview);
        menButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    mainApplication.selectedGender("Men");
                Intent i =new Intent(HomePage.this,Collection.class);
                startActivity(i);
                finish();
            }
        });
        womenButton = (TextView) findViewById(R.id.women_button_textview);
        womenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    mainApplication.selectedGender("Women");
                Intent i =new Intent(HomePage.this,Collection.class);
                startActivity(i);
                finish();
            }
        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                menu_layout.setVisibility(View.VISIBLE);
            }
        });

        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu_layout.setVisibility(View.GONE);
            }
        });

        if(ItemsAdapter.getObject(HomePage.this).isItemsPresent()==false) {
            WebApiAdapter.getObject(HomePage.this).fireServerApi(5, null);
        }else{
            menButton.setEnabled(true);
            mProgressBar.setVisibility(View.GONE);
            menButton.setVisibility(View.VISIBLE);
            womenButton.setEnabled(true);
            wProgressBar.setVisibility(View.GONE);
            womenButton.setVisibility(View.VISIBLE);
        }

    }

    public void onResponseReceive(String response) {
            if(response.contains("error"))
            {
                AlertAdapter.getObject(HomePage.this).createMessageAlert("Please restart app!!");
                menButton.setEnabled(false);
                mProgressBar.setVisibility(View.GONE);
                menButton.setVisibility(View.VISIBLE);
                menButton.setText("error");
                womenButton.setEnabled(false);
                wProgressBar.setVisibility(View.GONE);
                womenButton.setVisibility(View.VISIBLE);
                womenButton.setText("error");
            }
            else{
                ItemsAdapter.getObject(HomePage.this).saveAllItems(response);
                menButton.setEnabled(true);
                mProgressBar.setVisibility(View.GONE);
                menButton.setVisibility(View.VISIBLE);
                womenButton.setEnabled(true);
                wProgressBar.setVisibility(View.GONE);
                womenButton.setVisibility(View.VISIBLE);
                ItemsAdapter.getObject(HomePage.this).setItemHereTrue();
            }
    }


}
